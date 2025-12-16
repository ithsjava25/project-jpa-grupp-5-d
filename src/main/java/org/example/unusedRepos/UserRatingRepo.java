package org.example.unusedRepos;

import org.example.entity.User;

import java.util.List;

public interface UserRatingRepo {
    List<User> getRating(int rating);
}
