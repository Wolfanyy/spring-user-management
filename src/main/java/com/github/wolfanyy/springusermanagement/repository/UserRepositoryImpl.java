package com.github.wolfanyy.springusermanagement.repository;

import com.github.wolfanyy.springusermanagement.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private static final String FIND_BY_EMAIL = """
            SELECT u
            FROM User u
            WHERE u.email = :email
            """;

    private static final String FIND_ALL = """
            SELECT u 
            FROM User u
            ORDER BY u.id
            """;


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User save(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(
                entityManager.find(User.class, id));
    }

    @Override
    public Optional<User> findByEmail(String email) {

        List<User> users = entityManager.createQuery(
                        FIND_BY_EMAIL, User.class)
                .setParameter("email", email)
                .getResultList();

        return users.stream().findFirst();
    }

    @Override
    public List<User> findAll() {

        return entityManager.createQuery(
                        FIND_ALL, User.class)
                .getResultList();
    }

    @Override
    public User update(User user) {
        return entityManager.merge(user);
    }

    @Override
    public void deleteById(Long id) {

        User user = entityManager.find(User.class, id);

        if (user != null) {
            entityManager.remove(user);
        }
    }
}
