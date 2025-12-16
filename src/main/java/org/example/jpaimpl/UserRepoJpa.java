package org.example.jpaimpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.example.pojo.User;
import org.example.repo.UserRepo;

import java.util.Optional;

public class UserRepoJpa implements UserRepo {

    @PersistenceContext
    private EntityManager em;

    public UserRepoJpa(EntityManager em) {
        this.em = em;
    }


    @Override
    @Transactional
    public User addUser(String userName, String password) {
        Optional<User> existing = validateUser(userName, password);

        if (existing.isPresent()) {
            User user = existing.get();
            user.setPassword(password); // uppdatera password om det ändrats
            em.merge(user);
            return user;
        } else {
            User user = new User();
            user.setUserName(userName);
            user.setPassword(password);
            em.persist(user);
            return user;
        }
    }


    @Override
    public boolean uppdatePassword(long userId, String newPassword) {
        if (newPassword == null) return false;

        User user = em.find(User.class, userId);

        if (user == null) return false;

        user.setPassword(newPassword);

        return true;
    }

    @Override
    public boolean deleteUser(long userId) {
        if (userId <= 0) return false;

        User user = em.find(User.class, userId);

        if (user == null) return false;

        em.remove(user);
        return true;
    }

    @Override
    public Optional<User> getById(int userId) {
        if (userId <= 0) return Optional.empty();

        return Optional.ofNullable(em.find(User.class, userId));
    }

    @Override
    public Optional<User> validateUser(String userName, String password) {
        if (userName == null || password == null){
            return Optional.empty();
        }

        try {
            User user = em.createQuery(
                    "SELECT u FROM User u WHERE u.userName = :userName AND u.password = :password",
                    User.class)
                .setParameter("userName", userName)
                .setParameter("password", password)
                .getSingleResult();

            return Optional.of(user);
        } catch (NoResultException e) {
            return Optional.empty();
        }

    }
}
