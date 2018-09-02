package com.trading.service;

import com.trading.domain.Trade;

public interface TradeEventService {
    void processIncomingEvent(Trade trade);
}
