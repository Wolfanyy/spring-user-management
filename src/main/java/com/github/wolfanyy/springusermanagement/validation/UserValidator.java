package com.github.wolfanyy.springusermanagement.validation;

import com.github.wolfanyy.springusermanagement.entity.User;
import com.github.wolfanyy.springusermanagement.exception.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {

    public void normalize(User user) {

        System.out.println(user);

        if (user == null) {
            throw new ValidationException("User cannot be null");
        }

        user.setName(user.getName() != null ? user.getName().trim() : null);
        user.setLastName(user.getLastName() != null ? user.getLastName().trim() : null);
        user.setEmail(user.getEmail() != null ? user.getEmail().trim().toLowerCase() : null);
    }

    public void validateId(Long id) {

        if (id == null) {
            throw new ValidationException(
                    "User id cannot be null"
            );
        }

        if (id <= 0) {
            throw new ValidationException(
                    "User id must be positive"
            );
        }
    }

}
