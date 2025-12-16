package org.example.unusedRepos;

import org.example.entity.Movie;

import java.util.List;

public interface MovieDirectorRepo {
    List<Movie> getDirector (String name);
    List<Movie> getMovie (String title);
}
