package com.github.wolfanyy.springusermanagement.util;

import com.github.wolfanyy.springusermanagement.entity.User;
import com.github.wolfanyy.springusermanagement.exception.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class UserNormalizer {

    public void normalize(User user) {

        if (user == null) {
            throw new ValidationException("User cannot be null");
        }

        user.setName(user.getName() != null ? user.getName().trim() : null);
        user.setLastName(user.getLastName() != null ? user.getLastName().trim() : null);
        user.setEmail(user.getEmail() != null ? user.getEmail().trim().toLowerCase() : null);
    }
}
