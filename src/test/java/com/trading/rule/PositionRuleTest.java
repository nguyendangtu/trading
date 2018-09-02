package com.trading.rule;

import com.trading.constants.TradeConstants;
import com.trading.domain.Trade;
import com.trading.enums.OPERATION_ENUM;
import org.junit.Assert;
import org.junit.Test;

public class PositionRuleTest {

    @Test
    public void testQUANTITY_SIGN_BUY_NEW() {
        Trade trade = new Trade(1234L, 1, "XYZ", 100, TradeConstants.BUY, "ACC-1234", OPERATION_ENUM.NEW);
        int sign = PositionRule.CALCULATE_QUANTITY(trade);
        Assert.assertEquals(100, sign);
    }

    @Test
    public void testQUANTITY_SIGN_BUY_AMEND() {
        Trade trade = new Trade(1234L, 1, "XYZ", 100, TradeConstants.BUY, "ACC-1234", OPERATION_ENUM.AMEND);
        int sign = PositionRule.CALCULATE_QUANTITY(trade);
        Assert.assertEquals(100, sign);
    }

    @Test
    public void testQUANTITY_SIGN_BUY_CANCEL() {
        Trade trade = new Trade(1234L, 1, "XYZ", 100, TradeConstants.BUY, "ACC-1234", OPERATION_ENUM.CANCEL);
        int sign = PositionRule.CALCULATE_QUANTITY(trade);
        Assert.assertEquals(-100, sign);
    }

    @Test
    public void testQUANTITY_SIGN_SELL_NEW() {
        Trade trade = new Trade(1234L, 1, "XYZ", 100, TradeConstants.SELL, "ACC-1234", OPERATION_ENUM.NEW);
        int sign = PositionRule.CALCULATE_QUANTITY(trade);
        Assert.assertEquals(-100, sign);
    }

    @Test
    public void testQUANTITY_SIGN_SELL_AMEND() {
        Trade trade = new Trade(1234L, 1, "XYZ", 100, TradeConstants.SELL, "ACC-1234", OPERATION_ENUM.AMEND);
        int sign = PositionRule.CALCULATE_QUANTITY(trade);
        Assert.assertEquals(-100, sign);
    }

    @Test
    public void testQUANTITY_SIGN_SELL_CANCEL() {
        Trade trade = new Trade(1234L, 1, "XYZ", 100, TradeConstants.SELL, "ACC-1234", OPERATION_ENUM.CANCEL);
        int sign = PositionRule.CALCULATE_QUANTITY(trade);
        Assert.assertEquals(100, sign);
    }

}