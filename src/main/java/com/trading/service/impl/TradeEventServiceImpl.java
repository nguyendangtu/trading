package com.trading.service.impl;

import com.trading.domain.SecurityPosition;
import com.trading.domain.SecurityPositionTradeMapping;
import com.trading.domain.Trade;
import com.trading.repository.SecurityPositionRepository;
import com.trading.repository.SecurityPositionTradeMappingRepository;
import com.trading.repository.TradeRepository;
import com.trading.rule.PositionRule;
import com.trading.service.TradeEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("tradeEventService")
public class TradeEventServiceImpl implements TradeEventService {

    @Autowired
    private SecurityPositionTradeMappingRepository tradeMappingRepository;

    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private SecurityPositionRepository positionRepository;

    @Override
    @Transactional
    public void processIncomingEvent(Trade incomingTrade) {
        Trade existingTrade = tradeRepository.findByTradeId(incomingTrade.getTradeId());

        //existingTrade is not existed
        if (existingTrade == null) {
            handleNotExistingTrade(incomingTrade);
            return;
        }

        //check incoming version
        if (incomingTrade.getVersion() <= existingTrade.getVersion()) {
            handleIncomingTradeVersionLowerThanExistingOne(incomingTrade);
            return;
        }

        //if trade id already existing into system, position must be exist base on account and security identifier
        SecurityPosition existingPosition = positionRepository.findByAccountAndInstrument(existingTrade.getAccount(),

                                                                                          existingTrade.getSecurityIdentifier());
        //existing position always existing, if not exist, somebody had manual deleted from database
        if (existingPosition == null) {
            throw new RuntimeException("Some error happened. Must have existing position when having existing trade.");
        }

        boolean samePosition = isIncomingTradeHasTheSamePositionWithExisingTrade(incomingTrade, existingTrade);
        int incomingQuantity = PositionRule.CALCULATE_QUANTITY(incomingTrade);
        int existingQuantity = PositionRule.CALCULATE_QUANTITY(existingTrade);

        if (samePosition) {
            handleExistingTradeAndSamePosition(existingPosition, incomingQuantity, existingQuantity);
        } else {
            handleExistingTradeAndNotSamePosition(incomingTrade,
                                                  existingPosition,
                                                  incomingQuantity,
                                                  existingQuantity);
        }

        //update existing trade by incoming trade
        tradeRepository.save(incomingTrade);
    }

    private void handleIncomingTradeVersionLowerThanExistingOne(Trade incomingTrade) {
        //create a new position if not existed
        SecurityPosition incomingPosition = positionRepository.findByAccountAndInstrument(incomingTrade.getAccount(),
                                                                                          incomingTrade.getSecurityIdentifier());

        //If incoming position is null, we need to save to database.
        //If position already exist, we do not need to save to database when incoming version lover than existing version.
        if (incomingPosition == null) {
            incomingPosition = new SecurityPosition(incomingTrade.getAccount(),
                                                    incomingTrade.getSecurityIdentifier());
            positionRepository.save(incomingPosition);
        }
        //Save position-trade mapping
        SecurityPositionTradeMapping mapping = tradeMappingRepository.findBySecurityPositionIdAndTradeId(
                incomingPosition.getId(),
                incomingTrade.getTradeId());
        if (mapping == null) {
            mapping = new SecurityPositionTradeMapping(incomingPosition.getId(),
                                                       incomingTrade.getTradeId());
            tradeMappingRepository.save(mapping);
        }
    }

    void handleNotExistingTrade(Trade incomingTrade) {
        //save new trade
        incomingTrade = tradeRepository.save(incomingTrade);

        int incomingQuantity = PositionRule.CALCULATE_QUANTITY(incomingTrade);

        //find position if exists
        SecurityPosition incomingPosition = positionRepository.findByAccountAndInstrument(incomingTrade.getAccount(),
                                                                                          incomingTrade.getSecurityIdentifier());

        //create a new position if not existed
        incomingPosition = handleNewPosition(incomingTrade, incomingQuantity, incomingPosition);

        //Save position-trade mapping
        SecurityPositionTradeMapping mapping = new SecurityPositionTradeMapping(incomingPosition.getId(),
                                                                                incomingTrade.getTradeId());
        tradeMappingRepository.save(mapping);
    }

    void handleExistingTradeAndSamePosition(SecurityPosition existingPosition, int incomingQuantity,
                                            int existingQuantity) {
        int changedQuantity = incomingQuantity - existingQuantity;
        positionRepository.updateQuantity(existingPosition.getId(), changedQuantity);
    }

    void handleExistingTradeAndNotSamePosition(Trade incomingTrade,
                                               SecurityPosition existingPosition,
                                               int incomingQuantity,
                                               int existingQuantity) {
        SecurityPosition incomingPosition = positionRepository.findByAccountAndInstrument(incomingTrade.getAccount(),
                                                                                          incomingTrade.getSecurityIdentifier());
        incomingPosition = handleNewPosition(incomingTrade, incomingQuantity, incomingPosition);

        //Save position-trade mapping
        SecurityPositionTradeMapping mapping = tradeMappingRepository.findBySecurityPositionIdAndTradeId(
                incomingPosition.getId(),
                incomingTrade.getTradeId());
        if (mapping == null) {
            mapping = new SecurityPositionTradeMapping(incomingPosition.getId(),
                                                       incomingTrade.getTradeId());
            tradeMappingRepository.save(mapping);
        }

        //process remove quantity for existing position
        positionRepository.updateQuantity(existingPosition.getId(), -existingQuantity);
    }

    SecurityPosition handleNewPosition(Trade incomingTrade,
                                       int incomingQuantity,
                                       SecurityPosition incomingPosition) {
        if (incomingPosition == null) {
            incomingPosition = new SecurityPosition(incomingTrade.getAccount(),
                                                    incomingTrade.getSecurityIdentifier());
            incomingPosition.setQuantity(incomingQuantity);
            incomingPosition = positionRepository.save(incomingPosition);
        } else {
            positionRepository.updateQuantity(incomingPosition.getId(), incomingQuantity);
        }
        return incomingPosition;
    }

    boolean isIncomingTradeHasTheSamePositionWithExisingTrade(Trade incoming, Trade existing) {
        return incoming.getAccount().equals(existing.getAccount()) &&
               incoming.getSecurityIdentifier().equals(existing.getSecurityIdentifier());
    }

}
