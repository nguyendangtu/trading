package com.trading.service.impl;

import com.trading.BaseTest;
import com.trading.domain.SecurityPosition;
import com.trading.domain.SecurityPositionTradeMapping;
import com.trading.domain.Trade;
import com.trading.enums.OPERATION_ENUM;
import com.trading.repository.SecurityPositionRepository;
import com.trading.repository.SecurityPositionTradeMappingRepository;
import com.trading.repository.TradeRepository;
import com.trading.service.TradeEventService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TradeEventServiceImplTest extends BaseTest {

    @Autowired
    private TradeEventService tradeEventService;

    @Autowired
    private SecurityPositionTradeMappingRepository tradeMappingRepository;

    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private SecurityPositionRepository positionRepository;

    @Test
    public void processIncomingEvent_firstTrade_positive() {
        Trade trade = trades.get(0);
        tradeEventService.processIncomingEvent(trade);

        Trade dbTrade = tradeRepository.findByTradeId(1234L);
        Assert.assertNotNull(dbTrade);
        Assert.assertEquals("XYZ", dbTrade.getSecurityIdentifier());

        SecurityPosition dbPosition = positionRepository.findByAccountAndInstrument("ACC-1234", "XYZ");
        Assert.assertNotNull(dbPosition);
        Assert.assertEquals("XYZ", dbPosition.getInstrument());
        Assert.assertEquals("ACC-1234", dbPosition.getAccount());

        SecurityPositionTradeMapping mapping = tradeMappingRepository.findBySecurityPositionIdAndTradeId(dbPosition.getId(),
                                                                                                         dbTrade.getTradeId());
        Assert.assertNotNull(mapping);
    }
}