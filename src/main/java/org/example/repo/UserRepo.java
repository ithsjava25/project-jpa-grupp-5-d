package org.example.repo;

import org.example.pojo.Movie;
import org.example.pojo.User;

import java.util.List;
import java.util.Optional;

public interface UserRepo {


    User addUser(String userName, String password);

    boolean updatePassword(long userId, String newPassword);

    boolean deleteUser(long userId);

    Optional<User> getById(long userId);

    Optional<User> validateUser (String userName, String password);

    public boolean addFavoriteMovie(long userId, Movie movie);

    public boolean removeFavoriteMovie(long userId, Movie movie);

    public List<Movie> getFavoriteMovies(long userId);

    public Optional<User> findByUserName(String userName);
}
