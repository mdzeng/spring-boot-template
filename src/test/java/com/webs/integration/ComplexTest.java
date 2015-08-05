package com.webs.integration;

import com.webs.IntegrationTest;
import com.webs.user.model.User;
import com.webs.user.repository.UserRepository;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.webs.user.Application;

/**
 * Created by chris on 8/5/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@Category(IntegrationTest.class)
public class ComplexTest {
    @Autowired
    public UserRepository userRepository;

    @Test
    public void moreComplexTest() {
        assert 2 * 3 == 6;
    }

    @Test
    public void userRepoInsertTest() {
        userRepository.save(new User(0L, "tobias"));
    }
}
