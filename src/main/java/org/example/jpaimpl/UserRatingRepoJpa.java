package org.example.jpaimpl;

import org.example.pojo.User;
import org.example.repo.UserRatingRepo;

import java.util.List;

public class UserRatingRepoJpa implements UserRatingRepo {
    @Override
    public List<User> getRating(int rating) {
        return List.of();
    }
}
