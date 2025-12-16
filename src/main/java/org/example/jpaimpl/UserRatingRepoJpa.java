package org.example.jpaimpl;

import jakarta.persistence.EntityManager;
import org.example.pojo.Movie;
import org.example.repo.UserRatingRepo;

import java.util.List;

public class UserRatingRepoJpa implements UserRatingRepo {

    private final EntityManager em;

    public UserRatingRepoJpa(EntityManager em){
        this.em = em;
    }

    @Override
    public List<Movie> getMoviesByRating(float minRating, float maxRating) {
        return em.createQuery(
            "select ur.movie from UserRating ur where ur.rating between :min and :max", Movie.class)
            .setParameter("min", minRating)
            .setParameter("max", maxRating)
            .getResultList();

    }

    public List<Object[]> getAverageRatings() {
        return em.createQuery(
                "SELECT ur.movie, AVG(ur.rating) FROM UserRating ur GROUP BY ur.movie",
                Object[].class)
            .getResultList();
    }

}
