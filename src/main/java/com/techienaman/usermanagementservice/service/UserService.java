package com.techienaman.usermanagementservice.service;

import com.techienaman.usermanagementservice.exception.DuplicateMobileNumber;
import com.techienaman.usermanagementservice.model.User;
import com.techienaman.usermanagementservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        Optional<User> userOptional =
                userRepository.findUserByMobileNumber(user.getMobileNumber());

        if (userOptional.isPresent()) {
            throw new DuplicateMobileNumber("Mobile Number already taken");
        }

        return userRepository.save(user);
    }

}
