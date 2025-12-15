package org.example.repo;

import org.example.entity.Actor;
import org.example.entity.Movie;

import java.util.List;

public interface MovieActorRepo {
    List<Movie> getActor(String actor);
    List<Movie> getMovie (String title);
}
