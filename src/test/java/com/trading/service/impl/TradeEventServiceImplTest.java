package com.trading.service.impl;

import com.trading.BaseTest;
import com.trading.domain.SecurityPosition;
import com.trading.domain.SecurityPositionTradeMapping;
import com.trading.domain.Trade;
import com.trading.input.TradeInput;
import com.trading.repository.SecurityPositionRepository;
import com.trading.repository.SecurityPositionTradeMappingRepository;
import com.trading.repository.TradeRepository;
import com.trading.rule.PositionRule;
import com.trading.service.TradeEventService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TradeEventServiceImplTest extends BaseTest {

    @Autowired
    private TradeEventService tradeEventService;

    @Autowired
    private SecurityPositionTradeMappingRepository tradeMappingRepository;

    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private SecurityPositionRepository positionRepository;

    private List<Trade> trades = null;

    @Before
    public void setUp() {
        tradeMappingRepository.deleteAll();
        tradeRepository.deleteAll();
        positionRepository.deleteAll();
        this.trades = TradeInput.getTradesByNormalOrder();
    }

    @Test
    public void testProcessIncomingEvent_NotExistingTrade() {
        Trade trade = trades.get(0);
        tradeEventService.processIncomingEvent(trade);

        Trade dbTrade = tradeRepository.findByTradeId(trade.getTradeId());
        Assert.assertNotNull(dbTrade);
        Assert.assertEquals(trade.getSecurityIdentifier(), dbTrade.getSecurityIdentifier());

        SecurityPosition dbPosition = positionRepository.findByAccountAndInstrument(trade.getAccount(), trade.getSecurityIdentifier());
        Assert.assertNotNull(dbPosition);
        Assert.assertEquals(trade.getSecurityIdentifier(), dbPosition.getInstrument());
        Assert.assertEquals(trade.getAccount(), dbPosition.getAccount());
        Assert.assertEquals(trade.getQuantity(), dbPosition.getQuantity());

        SecurityPositionTradeMapping mapping = tradeMappingRepository.findBySecurityPositionIdAndTradeId(dbPosition.getId(),
                dbTrade.getTradeId());
        Assert.assertNotNull(mapping);
        Assert.assertEquals(dbPosition.getId(), mapping.getSecurityPositionId());
        Assert.assertEquals(trade.getTradeId(), mapping.getTradeId());
    }

    @Test
    public void testProcessIncomingEvent_handleIncomingTradeVersionLowerThanExistingOne() {
        Trade trade0 = trades.get(1);
        Trade trade1 = trades.get(0);

        Assert.assertEquals(trade0.getTradeId(), trade1.getTradeId());
        Assert.assertTrue(trade0.getVersion() > trade1.getVersion());

        tradeEventService.processIncomingEvent(trade0);
        tradeEventService.processIncomingEvent(trade1);

        Trade dbTrade = tradeRepository.findByTradeId(trade1.getTradeId());
        Assert.assertNotNull(dbTrade);
        Assert.assertEquals(trade0.getSecurityIdentifier(), dbTrade.getSecurityIdentifier());
        Assert.assertEquals(trade0.getVersion(), dbTrade.getVersion());
        Assert.assertEquals(trade0.getAccount(), dbTrade.getAccount());

        SecurityPosition dbPosition = positionRepository.findByAccountAndInstrument(trade1.getAccount(), trade1.getSecurityIdentifier());
        Assert.assertNotNull(dbPosition);
        Assert.assertEquals(trade0.getSecurityIdentifier(), dbPosition.getInstrument());
        Assert.assertEquals(trade0.getAccount(), dbPosition.getAccount());
        Assert.assertEquals(PositionRule.CALCULATE_QUANTITY(trade0), dbPosition.getQuantity());

        SecurityPositionTradeMapping mapping = tradeMappingRepository.findBySecurityPositionIdAndTradeId(dbPosition.getId(),
                dbTrade.getTradeId());
        Assert.assertNotNull(mapping);
        Assert.assertEquals(dbPosition.getId(), mapping.getSecurityPositionId());
        Assert.assertEquals(trade0.getTradeId(), mapping.getTradeId());
    }

    @Test
    public void testProcessIncomingEvent_HandleExistingTradeAndSamePosition_biggerVersion() {
        Trade trade0 = trades.get(0);
        Trade trade1 = trades.get(1);

        Assert.assertEquals(trade0.getTradeId(), trade1.getTradeId());
        Assert.assertTrue(trade0.getVersion() < trade1.getVersion());

        tradeEventService.processIncomingEvent(trade0);
        tradeEventService.processIncomingEvent(trade1);

        Trade dbTrade = tradeRepository.findByTradeId(trade1.getTradeId());
        Assert.assertNotNull(dbTrade);
        Assert.assertEquals(trade1.getSecurityIdentifier(), dbTrade.getSecurityIdentifier());
        Assert.assertEquals(trade1.getVersion(), dbTrade.getVersion());
        Assert.assertEquals(trade1.getAccount(), dbTrade.getAccount());

        SecurityPosition dbPosition = positionRepository.findByAccountAndInstrument(trade1.getAccount(), trade1.getSecurityIdentifier());
        Assert.assertNotNull(dbPosition);
        Assert.assertEquals(trade1.getSecurityIdentifier(), dbPosition.getInstrument());
        Assert.assertEquals(trade1.getAccount(), dbPosition.getAccount());
        Assert.assertEquals(PositionRule.CALCULATE_QUANTITY(trade1), dbPosition.getQuantity());

        SecurityPositionTradeMapping mapping = tradeMappingRepository.findBySecurityPositionIdAndTradeId(dbPosition.getId(),
                dbTrade.getTradeId());
        Assert.assertNotNull(mapping);
        Assert.assertEquals(dbPosition.getId(), mapping.getSecurityPositionId());
        Assert.assertEquals(trade1.getTradeId(), mapping.getTradeId());
    }

    @Test
    public void testProcessIncomingEvent_handleExistingTradeAndNotSamePosition() {
        Trade trade0 = trades.get(16);
        Trade trade1 = trades.get(18);

        tradeEventService.processIncomingEvent(trade0);
        tradeEventService.processIncomingEvent(trade1);

        Trade dbTrade = tradeRepository.findByTradeId(trade1.getTradeId());
        Assert.assertNotNull(dbTrade);
        Assert.assertEquals(trade1.getSecurityIdentifier(), dbTrade.getSecurityIdentifier());
        Assert.assertEquals(trade1.getVersion(), dbTrade.getVersion());
        Assert.assertEquals(trade1.getAccount(), dbTrade.getAccount());

        SecurityPosition dbPosition0 = positionRepository.findByAccountAndInstrument(trade0.getAccount(), trade0.getSecurityIdentifier());
        Assert.assertNotNull(dbPosition0);
        Assert.assertEquals(trade0.getSecurityIdentifier(), dbPosition0.getInstrument());
        Assert.assertEquals(trade0.getAccount(), dbPosition0.getAccount());
        Assert.assertEquals(0, dbPosition0.getQuantity());

        SecurityPosition dbPosition1 = positionRepository.findByAccountAndInstrument(trade1.getAccount(), trade1.getSecurityIdentifier());
        Assert.assertNotNull(dbPosition1);
        Assert.assertEquals(trade1.getSecurityIdentifier(), dbPosition1.getInstrument());
        Assert.assertEquals(trade1.getAccount(), dbPosition1.getAccount());
        Assert.assertEquals(PositionRule.CALCULATE_QUANTITY(trade1), dbPosition1.getQuantity());

        SecurityPositionTradeMapping mapping0 = tradeMappingRepository.findBySecurityPositionIdAndTradeId(dbPosition0.getId(),
                dbTrade.getTradeId());
        Assert.assertNotNull(mapping0);
        Assert.assertEquals(dbPosition0.getId(), mapping0.getSecurityPositionId());
        Assert.assertEquals(trade1.getTradeId(), mapping0.getTradeId());

        SecurityPositionTradeMapping mapping1 = tradeMappingRepository.findBySecurityPositionIdAndTradeId(dbPosition1.getId(),
                dbTrade.getTradeId());
        Assert.assertNotNull(mapping1);
        Assert.assertEquals(dbPosition1.getId(), mapping1.getSecurityPositionId());
        Assert.assertEquals(trade1.getTradeId(), mapping1.getTradeId());
    }
}