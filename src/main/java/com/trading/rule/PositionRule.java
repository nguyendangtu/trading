package com.trading.rule;

import com.trading.constants.TradeConstants;
import com.trading.domain.Trade;

public class PositionRule {
    /**
     * 1. The position quantity will be incremented when a trade is processed with the following attributes:
     * a. Direction = BUY, Operation = NEW or AMEND
     * b. Direction = SELL, Operation = CANCEL
     * 2. The position quantity will be decremented when a trade is processed with the following attributes:
     * a. Direction = SELL, Operation = NEW or AMEND
     * b. Direction = BUY, Operation = CANCEL
     *
     * @param trade
     * @return quantity
     */
    public static final int CALCULATE_QUANTITY(final Trade trade) {
        int flag = -1;
        switch (trade.getOperation()) {
            case NEW:
            case AMEND:
                flag = 1;
                break;
            case CANCEL:
                flag = -1;
                break;
        }
        flag = TradeConstants.BUY == trade.getDirection().charValue() ? flag : (-1) * flag;
        return flag * trade.getQuantity();
    }
}
