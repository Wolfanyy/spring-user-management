package com.github.wolfanyy.springusermanagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "name", nullable = false, length = 30)
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
    @Column(name = "last_name", nullable = false, length = 30)
    private String lastName;

    @NotBlank(message = "Email cannot be empty")
    @Size(
            max = 50,
            message = "Email must not exceed 50 characters"
    )
    @Email(message = "Email is invalid")
    @Column(
            name = "email",
            nullable = false,
            unique = true,
            length = 50
    )
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
    @Column(name = "age", nullable = false)
    private Integer age;

    public User(
            String name,
            String lastName,
            String email,
            Integer age
    ) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }

    @PrePersist
    @PreUpdate
    private void normalize() {
        if (name != null) {
            name = name.trim();
        }

        if (lastName != null) {
            lastName = lastName.trim();
        }

        if (email != null) {
            email = email.trim().toLowerCase();
        }
    }
}