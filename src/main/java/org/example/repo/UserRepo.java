package org.example.repo;

import org.example.pojo.User;

import java.util.Optional;

public interface UserRepo {


    boolean addUser(String userName, String password);

    boolean uppdatePassword(long userId, String newPassword);

    boolean deleteUser(long userId);

    User create(User user);

    Optional<User> getById(int userId);

    Optional<User> getUsername (String userName);

}
