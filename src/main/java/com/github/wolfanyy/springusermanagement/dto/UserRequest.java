package com.github.wolfanyy.springusermanagement.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserRequest {

    private Long id;

    @NotBlank(message = "Name cannot be empty")
    @Size(
            min = 3,
            max = 30,
            message = "Name must be between 3 and 30 characters"
    )
    @Pattern(
            regexp = "^[A-Za-zА-Яа-яЁё\\s-]+$",
            message = "Name contains invalid characters"
    )
    private String name;

    @NotBlank(message = "Last name cannot be empty")
    @Size(
            min = 3,
            max = 30,
            message = "Last name must be between 3 and 30 characters"
    )
    @Pattern(
            regexp = "^[A-Za-zА-Яа-яЁё\\s-]+$",
            message = "Last name contains invalid characters"
    )
    private String lastName;

    @NotBlank(message = "Email cannot be empty")
    @Size(
            max = 50,
            message = "Email must not exceed 50 characters"
    )
    @Email(message = "Email is invalid")
    private String email;

    @NotNull(message = "Age cannot be null")
    @Min(
            value = 1,
            message = "Age must be between 1 and 100"
    )
    @Max(
            value = 100,
            message = "Age must be between 1 and 100"
    )
    private Integer age;

}