package org.example.unusedRepos;

import org.example.entity.Movie;

import java.util.List;

public interface MovieActorRepo {
    List<Movie> getActor(String actor);
    List<Movie> getMovie (String title);
}
