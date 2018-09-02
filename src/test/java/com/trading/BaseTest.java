package com.trading;

import com.trading.config.H2JPAConfig;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        Application.class,
        H2JPAConfig.class})
@ActiveProfiles("test")
public abstract class BaseTest {


}
