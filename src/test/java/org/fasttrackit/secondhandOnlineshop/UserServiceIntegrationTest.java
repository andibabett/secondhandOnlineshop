package org.fasttrackit.secondhandOnlineshop;


import org.fasttrackit.secondhandOnlineshop.domain.User;
import org.fasttrackit.secondhandOnlineshop.service.UserService;
import org.fasttrackit.secondhandOnlineshop.steps.UserSteps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserSteps userSteps;


    @Test
    public void testCreateUser_whenValidRequest_thenUserIsSaved() {
        userSteps.createUser();
    }

    @Test
    public void testGetUser_whenExistingUser_thenReturnUser() {
        User createUser = userSteps.createUser();
        User user = userService.getUser(createUser.getId());


        assertThat(user, notNullValue());
        assertThat(user.getId(), is(createUser.getId()));
        assertThat(user.getEmail(), is(createUser.getEmail()));
        assertThat(user.getPassword(), is(createUser.getPassword()));
    }
}
