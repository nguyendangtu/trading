package com.trading.input;

import com.trading.constants.TradeConstants;
import com.trading.domain.Trade;
import com.trading.enums.OPERATION_ENUM;

import java.util.ArrayList;
import java.util.List;

public class TradeInput {
    public static final List<Trade> getListInputTrade() {
        List<Trade> trades = new ArrayList<>();
        Trade trade0 = new Trade(1234L, 1, "XYZ", 100, TradeConstants.BUY, "ACC-1234", OPERATION_ENUM.NEW);
        Trade trade1 = new Trade(1234L, 2, "XYZ", 150, TradeConstants.BUY, "ACC-1234", OPERATION_ENUM.AMEND);
        Trade trade2 = new Trade(5678L, 1, "QED", 200, TradeConstants.BUY, "ACC-2345", OPERATION_ENUM.NEW);
        Trade trade3 = new Trade(5678L, 2, "QED", 0, TradeConstants.BUY, "ACC-2345", OPERATION_ENUM.CANCEL);
        Trade trade4 = new Trade(2233L, 1, "RET", 100, TradeConstants.SELL, "ACC-3456", OPERATION_ENUM.NEW);
        Trade trade5 = new Trade(2233L, 2, "RET", 400, TradeConstants.SELL, "ACC-3456", OPERATION_ENUM.AMEND);
        Trade trade6 = new Trade(2233L, 3, "RET", 0, TradeConstants.SELL, "ACC-3456", OPERATION_ENUM.CANCEL);
        Trade trade7 = new Trade(8896L, 1, "YUI", 300, TradeConstants.BUY, "ACC-4567", OPERATION_ENUM.NEW);
        Trade trade8 = new Trade(6638L, 1, "YUI", 100, TradeConstants.SELL, "ACC-4567", OPERATION_ENUM.NEW);
        Trade trade9 = new Trade(6363L, 1, "HJK", 200, TradeConstants.BUY, "ACC-5678", OPERATION_ENUM.NEW);
        Trade trade10 = new Trade(7666L, 1, "HJK", 200, TradeConstants.BUY, "ACC-5678", OPERATION_ENUM.NEW);
        Trade trade11 = new Trade(6363L, 2, "HJK", 100, TradeConstants.BUY, "ACC-5678", OPERATION_ENUM.AMEND);
        Trade trade12 = new Trade(7666L, 2, "HJK", 50, TradeConstants.SELL, "ACC-5678", OPERATION_ENUM.AMEND);
        Trade trade13 = new Trade(8686L, 1, "FVB", 100, TradeConstants.BUY, "ACC-6789", OPERATION_ENUM.NEW);
        Trade trade14 = new Trade(8686L, 2, "GBN", 100, TradeConstants.BUY, "ACC-6789", OPERATION_ENUM.AMEND);
        Trade trade15 = new Trade(9654L, 1, "FVB", 200, TradeConstants.BUY, "ACC-6789", OPERATION_ENUM.NEW);
        Trade trade16 = new Trade(1025L, 1, "JKL", 100, TradeConstants.BUY, "ACC-7789", OPERATION_ENUM.NEW);
        Trade trade17 = new Trade(1036L, 1, "JKL", 100, TradeConstants.BUY, "ACC-7789", OPERATION_ENUM.NEW);
        Trade trade18 = new Trade(1025L, 2, "JKL", 100, TradeConstants.SELL, "ACC-8877", OPERATION_ENUM.AMEND);
        Trade trade19 = new Trade(1122L, 1, "KLO", 100, TradeConstants.BUY, "ACC-9045", OPERATION_ENUM.NEW);
        Trade trade20 = new Trade(1122L, 2, "HJK", 100, TradeConstants.SELL, "ACC-9045", OPERATION_ENUM.AMEND);
        Trade trade21 = new Trade(1122L, 3, "KLO", 100, TradeConstants.SELL, "ACC-9045", OPERATION_ENUM.AMEND);
        Trade trade22 = new Trade(1144L, 1, "KLO", 300, TradeConstants.BUY, "ACC-9045", OPERATION_ENUM.NEW);
        Trade trade23 = new Trade(1144L, 2, "KLO", 400, TradeConstants.BUY, "ACC-9045", OPERATION_ENUM.AMEND);
        Trade trade24 = new Trade(1155L, 1, "KLO", 600, TradeConstants.SELL, "ACC-9045", OPERATION_ENUM.NEW);
        Trade trade25 = new Trade(1155L, 2, "KLO", 0, TradeConstants.BUY, "ACC-9045", OPERATION_ENUM.CANCEL);

        return trades;
    }
}
