package org.example.repo;

import org.example.pojo.Movie;
import org.example.pojo.User;

import java.util.List;
import java.util.Optional;

public interface UserRepo {


    User addUser(String userName, String password);
    List<Movie> getFavoriteMovies(long userId);
    void addFavoriteMovie(long userId, Movie movie);

    boolean updatePassword(long userId, String newPassword);
    boolean deleteUser(long userId);
    boolean removeFavoriteMovie(long userId, Movie movie);

    Optional<User> getById(long userId);
    Optional<User> validateUser (String userName, String password);
    Optional<User> findByUserName(String userName);
}
