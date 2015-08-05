package com.webs.integration;

import com.webs.IntegrationTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import user.Application;

/**
 * Created by chris on 8/5/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@Category(IntegrationTest.class)
public class ComplexTest {
    @Test
    public void MoreComplexTest() {
        assert 2 * 3 == 6;
    }
}
