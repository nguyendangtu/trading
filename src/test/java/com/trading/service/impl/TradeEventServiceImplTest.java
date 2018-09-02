package com.trading.service.impl;

import com.trading.BaseTest;
import com.trading.domain.Trade;
import com.trading.enums.OPERATION_ENUM;
import com.trading.repository.TradeRepository;
import com.trading.service.TradeEventService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TradeEventServiceImplTest extends BaseTest {

    @Autowired
    private TradeEventService tradeEventService;

    @Autowired
    private TradeRepository tradeRepository;

    @Test
    public void processIncomingEvent_firstTrade_positive() {
        Trade trade = new Trade(new Long(1234), 1, "XYZ", 100, 'B', "ACC-1234", OPERATION_ENUM.NEW);
        tradeEventService.processIncomingEvent(trade);
    }
}