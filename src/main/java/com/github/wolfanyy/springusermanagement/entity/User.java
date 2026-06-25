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
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "last_name", nullable = false, length = 30)
    private String lastName;

    @Column(
            name = "email",
            nullable = false,
            unique = true,
            length = 50
    )
    private String email;

    @Column(name = "age", nullable = false)
    private Integer age;

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