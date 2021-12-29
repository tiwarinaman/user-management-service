package com.techienaman.usermanagementservice.service;

import com.techienaman.usermanagementservice.exception.DuplicateMobileNumberException;
import com.techienaman.usermanagementservice.exception.UserNotFoundException;
import com.techienaman.usermanagementservice.model.User;
import com.techienaman.usermanagementservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
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
            throw new DuplicateMobileNumberException("Mobile Number already taken");
        }

        return userRepository.save(user);
    }

    @Transactional
    public User updateUserDetails(User user, Long id) {
        User userDetails = userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("User not found with this id"));

        if (user.getFirstname() != null
                && user.getFirstname().length() > 0
                && !Objects.equals(user.getFirstname(), userDetails.getFirstname())) {
            userDetails.setFirstname(user.getFirstname());
        } else if (user.getLastname() != null
                && user.getLastname().length() > 0
                && !Objects.equals(user.getLastname(), userDetails.getLastname())) {
            userDetails.setLastname(user.getLastname());
        } else if (user.getMobileNumber() != null
                && user.getLastname().length() > 0
                && !Objects.equals(user.getMobileNumber(), userDetails.getMobileNumber())) {
            Optional<User> userOptional =
                    userRepository.findUserByMobileNumber(user.getMobileNumber());

            if (userOptional.isPresent()) {
                throw new DuplicateMobileNumberException("Mobile already taken");
            }
            userDetails.setMobileNumber(user.getMobileNumber());
        }
        userRepository.save(userDetails);
        return userDetails;
    }

    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User Not found with this id");
        }
        userRepository.deleteById(id);
    }

}
