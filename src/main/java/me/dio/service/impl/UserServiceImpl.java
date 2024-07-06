package me.dio.service.impl;

import me.dio.domain.model.User;
import me.dio.domain.repository.UserRepository;
import me.dio.service.UserService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl (UserRepository userRepository){
        this.userRepository = userRepository;
    }

    /**
     * Retrieve a user by its ID.
     *
     * @param id The ID of the user to be retrieved.
     * @return The user found by the given ID.
     * @throws NoSuchElementException if no user is found by the given ID.
     */

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    /**
     * Create a new user.
     *
     * @param userToCreate The user to be created.
     * @return The created user.
     * @throws IllegalArgumentException if the account number already exists.
     */

    @Override
    public User create(User userToCreate) {
        if (userRepository.existsByAccountNumber(userToCreate.getAccount().getNumber())){
            throw new IllegalArgumentException("This Account number already exists");
        }
        return userRepository.save(userToCreate);
    }
}
