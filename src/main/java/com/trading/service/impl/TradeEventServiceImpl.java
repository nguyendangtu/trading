package com.trading.service.impl;

import com.trading.domain.SecurityPosition;
import com.trading.domain.Trade;
import com.trading.repository.SecurityPositionRepository;
import com.trading.repository.SecurityPositionTradingMappingRepository;
import com.trading.repository.TradeRepository;
import com.trading.rule.PositionRule;
import com.trading.service.TradeEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("tradeEventService")
public class TradeEventServiceImpl implements TradeEventService {

    @Autowired
    private SecurityPositionTradingMappingRepository tradingAccountMappingRepository;

    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private SecurityPositionRepository securityPositionRepository;

    @Override
    public SecurityPosition processIncomingEvent(Trade incomingTrade) {
        Long tradeId = incomingTrade.getTradeId();
        String accountNumber = incomingTrade.getAccountNumber();
        String securityIdentifier = incomingTrade.getSecurityIdentifier();
        Trade existingTrade = tradeRepository.findByTradeId(tradeId);
        Trade finalTrade = PositionRule.MAX_VERSION(incomingTrade, existingTrade);
        //tradeRepository.save(finalTrade);//note
        List<String> tradeIds = tradeRepository.getAllTradeIDByAccountNumberAndSecurityIdentifier(accountNumber, securityIdentifier);
        SecurityPosition securityPosition = securityPositionRepository.findByAccountNumber(accountNumber);
        if (tradeIds.contains(tradeId)) {
            Integer quantity = securityPosition.getQuantity() + finalTrade.getQuantity() * PositionRule.QUANTITY_SIGN(finalTrade);
            securityPosition.setQuantity(quantity);

        } else {

        }
      //  SecurityPosition securityPosition = securityPositionRepository.findByAccountNumber(accountNumber);


        //select mapping base on trade id,acc.
        //1.If existing account and trade id, do nothing.
        //2. If existing ACC, add trade ID, if not existing account. remove current
        //mapping, and new mapping with new trade id and new account.
        // find existing account, and update to new account on security position.

        //Convert Trade to SecurityPosition

        //calculate quantity on ACCOUNT
        //update security position
        // table
        //update mapping


        return null;
    }


}
