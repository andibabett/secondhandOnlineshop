package org.fasttrackit.secondhandOnlineshop.steps;

import org.fasttrackit.secondhandOnlineshop.domain.User;
import org.fasttrackit.secondhandOnlineshop.service.UserService;
import org.fasttrackit.secondhandOnlineshop.transfer.user.SaveUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@Component
public class UserSteps {

    @Autowired
    private UserService userService;

    public User createUser() {
        SaveUserRequest request = new SaveUserRequest();
        request.setEmail("ionel_pop@yahoo.com" + System.currentTimeMillis());
        request.setPassword("******");

        User createdUser = userService.createUser(request);

        assertThat(createdUser, notNullValue());
        assertThat(createdUser.getEmail(), notNullValue());
        assertThat(createdUser.getPassword(), notNullValue());

        return createdUser;

    }
}
