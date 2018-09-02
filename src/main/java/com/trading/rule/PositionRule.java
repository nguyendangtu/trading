package com.trading.rule;

import com.trading.constants.TradeConstants;
import com.trading.domain.Trade;

public class PositionRule {
    /**
     * @param trade
     * @return
     */
    public static final int QUANTITY_SIGN(final Trade trade) {
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
        return flag;
    }

    /**
     *
     * @param incomingTrade
     * @param existingTrade
     * @return
     */
    public static final Trade MAX_VERSION(final Trade incomingTrade, final Trade existingTrade) {
        if (null == incomingTrade) return existingTrade;
        if (null == existingTrade) return incomingTrade;
        return incomingTrade.getTradeVersion() > existingTrade.getTradeVersion() ? incomingTrade : existingTrade;
    }


}
