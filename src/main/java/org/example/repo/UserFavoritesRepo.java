package org.example.repo;

import org.example.entity.User;

import java.util.List;

public interface UserFavoritesRepo {
    List<User> getFavorite(String favorite);
}
