package org.example.seed;

import jakarta.persistence.EntityManager;
import org.example.jpaimpl.GenreRepoJpa;
import org.example.pojo.Genre;

import java.util.HashMap;
import java.util.Map;

public class SeedGenres {

    private final EntityManager em;

    public SeedGenres(EntityManager em) {
        this.em = em;
    }

    public Map<String, Genre> seed() {
        GenreRepoJpa genreRepo = new GenreRepoJpa(em);
        Map<String, Genre> genres = new HashMap<>();

        genres.put("Action", genreRepo.addGenre("Action"));
        genres.put("Adventure", genreRepo.addGenre("Adventure"));
        genres.put("Comedy", genreRepo.addGenre("Comedy"));
        genres.put("Drama", genreRepo.addGenre("Drama"));
        genres.put("Fantasy", genreRepo.addGenre("Fantasy"));
        genres.put("Horror", genreRepo.addGenre("Horror"));
        genres.put("Mystery", genreRepo.addGenre("Mystery"));
        genres.put("Romance", genreRepo.addGenre("Romance"));
        genres.put("Science Fiction", genreRepo.addGenre("Science Fiction"));
        genres.put("Thriller", genreRepo.addGenre("Thriller"));
        genres.put("Western", genreRepo.addGenre("Western"));
        genres.put("Animation", genreRepo.addGenre("Animation"));
        genres.put("Musical", genreRepo.addGenre("Musical"));
        genres.put("Documentary", genreRepo.addGenre("Documentary"));
        genres.put("Crime", genreRepo.addGenre("Crime"));
        genres.put("War", genreRepo.addGenre("War"));
        genres.put("Biography", genreRepo.addGenre("Biography"));
        genres.put("Sport", genreRepo.addGenre("Sport"));
        genres.put("Family", genreRepo.addGenre("Family"));
        genres.put("History", genreRepo.addGenre("History"));

        return genres;
    }
}
