package org.example.unusedRepos;

import org.example.entity.Genre;
import org.example.entity.Movie;

import java.util.List;

public interface MovieGenreRepo {
    List<Movie> getGenre (String title);
    List<Genre> getTitle (String genre);
}
