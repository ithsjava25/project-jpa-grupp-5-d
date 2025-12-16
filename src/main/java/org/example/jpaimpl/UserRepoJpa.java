package org.example.jpaimpl;

import org.example.pojo.User;
import org.example.repo.UserRepo;

import java.util.Optional;

public class UserRepoJpa implements UserRepo {
    @Override
    public boolean addUser(String userName, String password) {
        return false;
    }

    @Override
    public boolean uppdatePassword(long userId, String newPassword) {
        return false;
    }

    @Override
    public boolean deleteUser(long userId) {
        return false;
    }

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public Optional<User> getById(int userId) {
        return Optional.empty();
    }

    @Override
    public Optional<User> getUsername(String userName) {
        return Optional.empty();
    }
}
