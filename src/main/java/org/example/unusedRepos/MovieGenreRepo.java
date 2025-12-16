package org.example.unusedRepos;

import org.example.pojo.Genre;
import org.example.pojo.Movie;

import java.util.List;

public interface MovieGenreRepo {
    List<Movie> getGenre (String title);
    List<Genre> getTitle (String genre);
}
