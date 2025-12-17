package org.example.seed;

import jakarta.persistence.EntityManager;
import org.example.jpaimpl.MovieRepoJpa;
import org.example.pojo.Actor;
import org.example.pojo.Director;
import org.example.pojo.Genre;

import java.util.List;
import java.util.Map;

public class SeedMovieRelations {

    private final EntityManager em;

    public SeedMovieRelations(EntityManager em) {
        this.em = em;
    }

    public void attachRelations(Map<String, Actor> actors,
                                Map<String, Director> directors,
                                Map<String, Genre> genres,
                                MovieRepoJpa movieRepo) {



        // Titanic
        movieRepo.setDirector("Titanic", directors.get("James Cameron"));
        movieRepo.addActors("Titanic", List.of(
            actors.get("Leonardo DiCaprio"),
            actors.get("Kate Winslet")
        ));
        movieRepo.addGenres("Titanic", List.of(
            genres.get("Romance"),
            genres.get("Drama")
        ));

        // The Dark Knight
        movieRepo.setDirector("The Dark Knight", directors.get("Christopher Nolan"));
        movieRepo.addActors("The Dark Knight", List.of(
            actors.get("Christian Bale"),
            actors.get("Heath Ledger"),
            actors.get("Gary Oldman")
        ));
        movieRepo.addGenres("The Dark Knight", List.of(
            genres.get("Action"),
            genres.get("Thriller"),
            genres.get("Crime")
        ));

        // Jurassic Park
        movieRepo.setDirector("Jurassic Park", directors.get("Steven Spielberg"));
        movieRepo.addActors("Jurassic Park", List.of(
            actors.get("Sam Neill"),
            actors.get("Laura Dern"),
            actors.get("Jeff Goldblum")
        ));
        movieRepo.addGenres("Jurassic Park", List.of(
            genres.get("Science Fiction"),
            genres.get("Adventure")
        ));

        // The Matrix
        movieRepo.setDirector("The Matrix", directors.get("Lana Wachowski")); // lägg till i SeedDirectors
        movieRepo.addActors("The Matrix", List.of(
            actors.get("Keanu Reeves"),
            actors.get("Laurence Fishburne"),
            actors.get("Carrie-Anne Moss")
        ));
        movieRepo.addGenres("The Matrix", List.of(
            genres.get("Science Fiction"),
            genres.get("Action")
        ));

        // Interstellar
        movieRepo.setDirector("Interstellar", directors.get("Christopher Nolan"));
        movieRepo.addActors("Interstellar", List.of(
            actors.get("Matthew McConaughey"),
            actors.get("Anne Hathaway"),
            actors.get("Jessica Chastain")
        ));
        movieRepo.addGenres("Interstellar", List.of(
            genres.get("Science Fiction"),
            genres.get("Drama")
        ));

        // Gladiator
        movieRepo.setDirector("Gladiator", directors.get("Ridley Scott"));
        movieRepo.addActors("Gladiator", List.of(
            actors.get("Russell Crowe"),
            actors.get("Joaquin Phoenix")
        ));
        movieRepo.addGenres("Gladiator", List.of(
            genres.get("Action"),
            genres.get("Drama"),
            genres.get("History")
        ));

        em.flush();
        em.clear();
    }
}
