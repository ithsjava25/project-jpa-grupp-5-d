package org.example.unusedRepos;

import org.example.pojo.User;

import java.util.List;

public interface UserFavoritesRepo {
    List<User> getFavorite(String favorite);
}
