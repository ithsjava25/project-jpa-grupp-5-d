package org.example.repo;

import org.example.pojo.User;

import java.util.Optional;

public interface UserRepo {


    User addUser(String userName, String password);

    boolean updatePassword(long userId, String newPassword);

    boolean deleteUser(long userId);

    Optional<User> getById(long userId);

    Optional<User> validateUser (String userName, String password);

}
