package com.trading.input;

import com.trading.constants.TradeConstants;
import com.trading.domain.SecurityPosition;
import com.trading.domain.SecurityPositionTradeMapping;
import com.trading.domain.Trade;
import com.trading.enums.OPERATION_ENUM;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TradeInput {

    private static final List<Trade> trades;

    private static final List<SecurityPosition> expectedPositions;

    static {
        trades = new ArrayList<>();
        trades.add(new Trade(1234L, 1, "XYZ", 100, TradeConstants.BUY, "ACC-1234", OPERATION_ENUM.NEW));
        trades.add(new Trade(1234L, 2, "XYZ", 150, TradeConstants.BUY, "ACC-1234", OPERATION_ENUM.AMEND));
        trades.add(new Trade(5678L, 1, "QED", 200, TradeConstants.BUY, "ACC-2345", OPERATION_ENUM.NEW));
        trades.add(new Trade(5678L, 2, "QED", 0, TradeConstants.BUY, "ACC-2345", OPERATION_ENUM.CANCEL));
        trades.add(new Trade(2233L, 1, "RET", 100, TradeConstants.SELL, "ACC-3456", OPERATION_ENUM.NEW));
        trades.add(new Trade(2233L, 2, "RET", 400, TradeConstants.SELL, "ACC-3456", OPERATION_ENUM.AMEND));
        trades.add(new Trade(2233L, 3, "RET", 0, TradeConstants.SELL, "ACC-3456", OPERATION_ENUM.CANCEL));
        trades.add(new Trade(8896L, 1, "YUI", 300, TradeConstants.BUY, "ACC-4567", OPERATION_ENUM.NEW));
        trades.add(new Trade(6638L, 1, "YUI", 100, TradeConstants.SELL, "ACC-4567", OPERATION_ENUM.NEW));
        trades.add(new Trade(6363L, 1, "HJK", 200, TradeConstants.BUY, "ACC-5678", OPERATION_ENUM.NEW));
        trades.add(new Trade(7666L, 1, "HJK", 200, TradeConstants.BUY, "ACC-5678", OPERATION_ENUM.NEW));
        trades.add(new Trade(6363L, 2, "HJK", 100, TradeConstants.BUY, "ACC-5678", OPERATION_ENUM.AMEND));
        trades.add(new Trade(7666L, 2, "HJK", 50, TradeConstants.SELL, "ACC-5678", OPERATION_ENUM.AMEND));
        trades.add(new Trade(8686L, 1, "FVB", 100, TradeConstants.BUY, "ACC-6789", OPERATION_ENUM.NEW));
        trades.add(new Trade(8686L, 2, "GBN", 100, TradeConstants.BUY, "ACC-6789", OPERATION_ENUM.AMEND));
        trades.add(new Trade(9654L, 1, "FVB", 200, TradeConstants.BUY, "ACC-6789", OPERATION_ENUM.NEW));
        trades.add(new Trade(1025L, 1, "JKL", 100, TradeConstants.BUY, "ACC-7789", OPERATION_ENUM.NEW));
        trades.add(new Trade(1036L, 1, "JKL", 100, TradeConstants.BUY, "ACC-7789", OPERATION_ENUM.NEW));
        trades.add(new Trade(1025L, 2, "JKL", 100, TradeConstants.SELL, "ACC-8877", OPERATION_ENUM.AMEND));
        trades.add(new Trade(1122L, 1, "KLO", 100, TradeConstants.BUY, "ACC-9045", OPERATION_ENUM.NEW));
        trades.add(new Trade(1122L, 2, "HJK", 100, TradeConstants.SELL, "ACC-9045", OPERATION_ENUM.AMEND));
        trades.add(new Trade(1122L, 3, "KLO", 100, TradeConstants.SELL, "ACC-9045", OPERATION_ENUM.AMEND));
        trades.add(new Trade(1144L, 1, "KLO", 300, TradeConstants.BUY, "ACC-9045", OPERATION_ENUM.NEW));
        trades.add(new Trade(1144L, 2, "KLO", 400, TradeConstants.BUY, "ACC-9045", OPERATION_ENUM.AMEND));
        trades.add(new Trade(1155L, 1, "KLO", 600, TradeConstants.SELL, "ACC-9045", OPERATION_ENUM.NEW));
        trades.add(new Trade(1155L, 2, "KLO", 0, TradeConstants.BUY, "ACC-9045", OPERATION_ENUM.CANCEL));

        expectedPositions = new ArrayList<>();
        expectedPositions.add(new SecurityPosition(6,
                                                   "ACC-1234",
                                                   "XYZ",
                                                   150,
                                                   Arrays.asList(new SecurityPositionTradeMapping(3, 1234)),
                                                   null));
        expectedPositions.add(new SecurityPosition(20,
                                                   "ACC-2345",
                                                   "QED",
                                                   0,
                                                   Arrays.asList(new SecurityPositionTradeMapping(9, 5678)),
                                                   null));
        expectedPositions.add(new SecurityPosition(11,
                                                   "ACC-3456",
                                                   "RET",
                                                   0,
                                                   Arrays.asList(new SecurityPositionTradeMapping(23, 2233)),
                                                   null));
        expectedPositions.add(new SecurityPosition(9,
                                                   "ACC-4567",
                                                   "YUI",
                                                   200,
                                                   Arrays.asList(new SecurityPositionTradeMapping(13, 6638),
                                                                new SecurityPositionTradeMapping(13, 8896)),
                                                   null));
        expectedPositions.add(new SecurityPosition(3,
                                                   "ACC-5678",
                                                   "HJK",
                                                   50,
                                                   Arrays.asList(new SecurityPositionTradeMapping(11, 7666),
                                                                new SecurityPositionTradeMapping(11, 6363)),
                                                   null));
        expectedPositions.add(new SecurityPosition(18,
                                                   "ACC-6789",
                                                   "GBN",
                                                   100,
                                                   Arrays.asList(new SecurityPositionTradeMapping(5, 8686)),
                                                   null));
        expectedPositions.add(new SecurityPosition(15,
                                                   "ACC-6789",
                                                   "FVB",
                                                   200,
                                                   Arrays.asList(new SecurityPositionTradeMapping(18, 9654),
                                                                new SecurityPositionTradeMapping(18, 8686)),
                                                   null));
        expectedPositions.add(new SecurityPosition(22,
                                                   "ACC-7789",
                                                   "JKL",
                                                   100,
                                                   Arrays.asList(new SecurityPositionTradeMapping(21, 1036),
                                                                new SecurityPositionTradeMapping(21, 1025)),
                                                   null));
        expectedPositions.add(new SecurityPosition(24,
                                                   "ACC-8877",
                                                   "JKL",
                                                   -100,
                                                   Arrays.asList(new SecurityPositionTradeMapping(7, 1025)),
                                                   null));
        expectedPositions.add(new SecurityPosition(1,
                                                   "ACC-9045",
                                                   "KLO",
                                                   300,
                                                   Arrays.asList(new SecurityPositionTradeMapping(1, 1155),
                                                                new SecurityPositionTradeMapping(1, 1122),
                                                                new SecurityPositionTradeMapping(1, 1144)),
                                                   null));
        expectedPositions.add(new SecurityPosition(13,
                                                   "ACC-9045",
                                                   "HJK",
                                                   0,
                                                   Arrays.asList(new SecurityPositionTradeMapping(26, 1122)),
                                                   null));
    }

    public static List<Trade> getTradesByNormalOrder() {
        return trades;
    }

    public static List<Trade> getTradesByRandomOrder() {
        List<Trade> newTrades = new ArrayList<>(trades);
        Collections.shuffle(newTrades);
        return newTrades;
    }

    public static List<SecurityPosition> getExpectedPositions() {
        return expectedPositions;
    }
}
