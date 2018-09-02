package com.trading;

import com.trading.config.H2JPAConfig;
import com.trading.domain.Trade;
import com.trading.input.TradeInput;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        Application.class,
        H2JPAConfig.class})
@ActiveProfiles("test")
public abstract class BaseTest {
    protected List<Trade> trades = null;

    @Before
    public void setUp() {
        this.trades = TradeInput.getListInputTrade();
    }

}
