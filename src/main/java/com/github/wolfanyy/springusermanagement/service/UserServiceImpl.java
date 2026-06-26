package com.github.wolfanyy.springusermanagement.service;

import com.github.wolfanyy.springusermanagement.entity.User;
import com.github.wolfanyy.springusermanagement.exception.DuplicateEmailException;
import com.github.wolfanyy.springusermanagement.exception.UserNotFoundException;
import com.github.wolfanyy.springusermanagement.repository.UserRepository;
import com.github.wolfanyy.springusermanagement.validation.UserValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserValidator userValidator;

    public UserServiceImpl(UserRepository userRepository,
                           UserValidator userValidator) {
        this.userRepository = userRepository;
        this.userValidator = userValidator;
    }

    @Override
    public User create(User user) {

        userValidator.normalize(user);

        validateEmailUniqueness(user);

        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Long id) {

        userValidator.validateId(id);

        return getUserOrThrow(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User update(User user) {

        userValidator.validateId(user.getId());

        userValidator.normalize(user);

        getUserOrThrow(user.getId());

        validateEmailUniqueness(user);

        return userRepository.update(user);
    }

    @Override
    public void deleteById(Long id) {

        userValidator.validateId(id);

        getUserOrThrow(id);

        userRepository.deleteById(id);
    }

    private void validateEmailUniqueness(User user) {

        userRepository.findByEmail(user.getEmail())
                .ifPresent(existingUser -> {

                    boolean anotherUserWithSameEmail =
                            user.getId() == null
                                    || !existingUser.getId()
                                    .equals(user.getId());

                    if (anotherUserWithSameEmail) {
                        throw new DuplicateEmailException(
                                user.getEmail()
                        );
                    }
                });
    }

    private User getUserOrThrow(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException(id));
    }
}