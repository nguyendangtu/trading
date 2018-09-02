package com.trading.rule;

import com.trading.constants.TradeConstants;
import com.trading.domain.Trade;
import com.trading.enums.OPERATION_ENUM;
import org.junit.Assert;
import org.junit.Test;

public class PositionRuleTest {

    @Test
    public void testQUANTITY_SIGN_BUY_NEW_POSITIVE() {
        Trade trade = new Trade(new Long(1234), 1, "XYZ", 100, TradeConstants.BUY, "ACC-1234", OPERATION_ENUM.NEW);
        int sign = PositionRule.QUANTITY_SIGN(trade);
        Assert.assertEquals(1, sign);
    }

    @Test
    public void testQUANTITY_SIGN_BUY_NEW_NEGATIVE() {
        Trade trade = new Trade(new Long(1234), 1, "XYZ", 100, TradeConstants.BUY, "ACC-1234", OPERATION_ENUM.NEW);
        int sign = PositionRule.QUANTITY_SIGN(trade);
        Assert.assertNotEquals(-1, sign);
    }

    @Test
    public void testQUANTITY_SIGN_BUY_AMEND_POSITIVE() {
        Trade trade = new Trade(new Long(1234), 1, "XYZ", 100, TradeConstants.BUY, "ACC-1234", OPERATION_ENUM.AMEND);
        int sign = PositionRule.QUANTITY_SIGN(trade);
        Assert.assertEquals(1, sign);
    }

    @Test
    public void testQUANTITY_SIGN_BUY_AMEND_NEGATIVE() {
        Trade trade = new Trade(new Long(1234), 1, "XYZ", 100, TradeConstants.BUY, "ACC-1234", OPERATION_ENUM.AMEND);
        int sign = PositionRule.QUANTITY_SIGN(trade);
        Assert.assertNotEquals(-1, sign);
    }

    @Test
    public void testQUANTITY_SIGN_BUY_CANCEL_POSITIVE() {
        Trade trade = new Trade(new Long(1234), 1, "XYZ", 100, TradeConstants.BUY, "ACC-1234", OPERATION_ENUM.CANCEL);
        int sign = PositionRule.QUANTITY_SIGN(trade);
        Assert.assertEquals(-1, sign);
    }

    @Test
    public void testQUANTITY_SIGN_BUY_CANCEL_NEGATIVE() {
        Trade trade = new Trade(new Long(1234), 1, "XYZ", 100, TradeConstants.BUY, "ACC-1234", OPERATION_ENUM.CANCEL);
        int sign = PositionRule.QUANTITY_SIGN(trade);
        Assert.assertNotEquals(1, sign);
    }

    @Test
    public void testQUANTITY_SIGN_SELL_NEW_POSITIVE() {
        Trade trade = new Trade(new Long(1234), 1, "XYZ", 100, TradeConstants.SELL, "ACC-1234", OPERATION_ENUM.NEW);
        int sign = PositionRule.QUANTITY_SIGN(trade);
        Assert.assertEquals(-1, sign);
    }

    @Test
    public void testQUANTITY_SIGN_SELL_NEW_NEGATIVE() {
        Trade trade = new Trade(new Long(1234), 1, "XYZ", 100, TradeConstants.SELL, "ACC-1234", OPERATION_ENUM.NEW);
        int sign = PositionRule.QUANTITY_SIGN(trade);
        Assert.assertNotEquals(1, sign);
    }

    @Test
    public void testQUANTITY_SIGN_SELL_AMEND_POSITIVE() {
        Trade trade = new Trade(new Long(1234), 1, "XYZ", 100, TradeConstants.SELL, "ACC-1234", OPERATION_ENUM.AMEND);
        int sign = PositionRule.QUANTITY_SIGN(trade);
        Assert.assertEquals(-1, sign);
    }

    @Test
    public void testQUANTITY_SIGN_SELL_AMEND_NEGATIVE() {
        Trade trade = new Trade(new Long(1234), 1, "XYZ", 100, TradeConstants.SELL, "ACC-1234", OPERATION_ENUM.AMEND);
        int sign = PositionRule.QUANTITY_SIGN(trade);
        Assert.assertNotEquals(1, sign);
    }

    @Test
    public void testQUANTITY_SIGN_SELL_CANCEL_POSITIVE() {
        Trade trade = new Trade(new Long(1234), 1, "XYZ", 100, TradeConstants.SELL, "ACC-1234", OPERATION_ENUM.CANCEL);
        int sign = PositionRule.QUANTITY_SIGN(trade);
        Assert.assertEquals(1, sign);
    }

    @Test
    public void testQUANTITY_SIGN_SELL_CANCEL_NEGATIVE() {
        Trade trade = new Trade(new Long(1234), 1, "XYZ", 100, TradeConstants.SELL, "ACC-1234", OPERATION_ENUM.CANCEL);
        int sign = PositionRule.QUANTITY_SIGN(trade);
        Assert.assertNotEquals(-1, sign);
    }

    @Test
    public void MAX_VERSION_InComingTrade() {
        Trade inComingTrade = new Trade(new Long(1234), 2, "XYZ", 200, TradeConstants.SELL, "ACC-1234", OPERATION_ENUM.NEW);
        Trade existingTrade = new Trade(new Long(1234), 1, "XYZ", 100, TradeConstants.SELL, "ACC-1234", OPERATION_ENUM.NEW);
        Trade finalTrade = PositionRule.MAX_VERSION(inComingTrade, existingTrade);
        Assert.assertEquals(inComingTrade, finalTrade);
    }

    @Test
    public void MAX_VERSION_ExistingTrade() {
        Trade inComingTrade = new Trade(new Long(1234), 1, "XYZ", 200, TradeConstants.SELL, "ACC-1234", OPERATION_ENUM.NEW);
        Trade existingTrade = new Trade(new Long(1234), 2, "XYZ", 100, TradeConstants.SELL, "ACC-1234", OPERATION_ENUM.NEW);
        Trade finalTrade = PositionRule.MAX_VERSION(inComingTrade, existingTrade);
        Assert.assertEquals(existingTrade, finalTrade);
    }

}