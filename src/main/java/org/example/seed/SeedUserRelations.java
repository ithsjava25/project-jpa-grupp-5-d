package org.example.seed;

import jakarta.persistence.EntityManager;
import org.example.jpaimpl.UserRatingRepoJpa;
import org.example.jpaimpl.UserRepoJpa;
import org.example.pojo.Movie;
import org.example.pojo.User;

import java.util.*;

public class SeedUserRelations {

    private final EntityManager em;

    public SeedUserRelations(EntityManager em) {
        this.em = em;
    }

    public void seedFavoritesAndRatings(Map<String, User> users, Map<String, Movie> moviesMap) {
        UserRepoJpa userRepo = new UserRepoJpa(em);
        UserRatingRepoJpa ratingRepo = new UserRatingRepoJpa(em);
        Random random = new Random();

        for (User user : users.values()) {
            // Assign favorite movies
            List<Movie> movieList = new ArrayList<>(moviesMap.values());
            Collections.shuffle(movieList);
            int favoritesCount = 2 + random.nextInt(4);
            for (int i = 0; i < favoritesCount; i++){
                userRepo.addFavoriteMovie(user.getId(), movieList.get(i));
            }

            // Assign ratings on movies
            Collections.shuffle(movieList);
            int ratingsCount = 1 + random.nextInt(5);
            for (int i = 0; i < ratingsCount; i++){
                float rating = 1.0f + random.nextFloat() * 4.0f;
                ratingRepo.rateMovie(user, movieList.get(i), rating);
            }
        }
        em.flush();
        em.clear();
    }


}
