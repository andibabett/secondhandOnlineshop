package org.fasttrackit.secondhandOnlineshop.service;


import org.fasttrackit.secondhandOnlineshop.domain.User;
import org.fasttrackit.secondhandOnlineshop.exception.ResourceNotFoundException;
import org.fasttrackit.secondhandOnlineshop.persistance.UserRepository;
import org.fasttrackit.secondhandOnlineshop.transfer.user.SaveUserRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.Optional;

@Service
public class UserService {

    private final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(SaveUserRequest request) throws ValidationException {
        if (userAlreadyExist(request.getEmail())) {
            LOGGER.info("Email is already registered");
            throw new ValidationException("Email is already registered");

        } else {
            LOGGER.info("Creating user: {}", request);
            User user = new User();
            user.setUsername(request.getUsername());
            user.setPassword(request.getPassword());
            user.setEmail(request.getEmail());

            return userRepository.save(user);
        }
    }

    public User getUser(Long id) {
        LOGGER.info("Retrieving user: {}", id);
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByEmail(String email) {
        LOGGER.info("Retrieving user: {},", email);
        return userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User doesn't exist"));
    }

    private User getUserByEmailAndPassword(String email, String password){
        return getUserByEmailAndPassword(email,password);

    }

    private boolean userAlreadyExist(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent();
    }
}

