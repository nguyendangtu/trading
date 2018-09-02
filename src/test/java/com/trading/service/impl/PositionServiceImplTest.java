package com.trading.service.impl;

import com.trading.BaseTest;
import com.trading.domain.SecurityPosition;
import com.trading.domain.Trade;
import com.trading.input.TradeInput;
import com.trading.repository.SecurityPositionRepository;
import com.trading.repository.SecurityPositionTradeMappingRepository;
import com.trading.repository.TradeRepository;
import com.trading.service.PositionService;
import com.trading.service.TradeEventService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

public class PositionServiceImplTest extends BaseTest {

    @Autowired
    private TradeEventService tradeEventService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private SecurityPositionTradeMappingRepository tradeMappingRepository;

    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private SecurityPositionRepository positionRepository;

    @Before
    public void init(){
        tradeMappingRepository.deleteAll();
        tradeRepository.deleteAll();
        positionRepository.deleteAll();
    }

    @Test
    public void getAllCurrentPositionsWithRandomNormalTrades() {
        List<Trade> trades = TradeInput.getTradesByNormalOrder();

        trades.forEach(trade -> tradeEventService.processIncomingEvent(trade));

        List<SecurityPosition> positions = positionService.getAllCurrentPositions();

        Assert.assertEquals(TradeInput.getExpectedPositions(), positions);
    }

    @Test
    public void getAllCurrentPositionsWithRandomInputTrades() {
        List<Trade> trades = TradeInput.getTradesByRandomOrder();

        trades.forEach(trade -> tradeEventService.processIncomingEvent(trade));

        List<SecurityPosition> positions = positionService.getAllCurrentPositions();

        Assert.assertEquals(TradeInput.getExpectedPositions(), positions);
    }
}