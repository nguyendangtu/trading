package com.trading.service;

import com.trading.domain.SecurityPosition;
import com.trading.domain.Trade;

public interface TradeEventService {
    SecurityPosition processIncomingEvent(Trade trade);
}
