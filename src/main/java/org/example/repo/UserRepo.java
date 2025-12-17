package org.example.repo;

import org.example.pojo.User;

import java.util.Optional;

public interface UserRepo {


    User addUser(String userName, String password);

    boolean uppdatePassword(long userId, String newPassword);

    boolean deleteUser(long userId);

    Optional<User> getById(int userId);

    Optional<User> validateUser (String userName, String password);

}
