package org.example.repo;

import org.example.pojo.User;

import java.util.List;

public interface UserRatingRepo {
    List<User> getRating(int rating);
}
