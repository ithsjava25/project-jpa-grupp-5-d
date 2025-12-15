package org.example.repo;

import org.example.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepo {


    boolean addUser(long userId, String firstName, String lastName, String password);

    boolean uppdatePassword(long userId, String newPassword);

    boolean deleteUser(long userId);

    User create(User user);

    Optional<User> getUserID(int userId);

    Optional<User> getUsername (String userName);

}
