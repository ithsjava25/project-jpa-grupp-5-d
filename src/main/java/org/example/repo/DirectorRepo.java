package org.example.repo;

import org.example.entity.Director;

import java.util.List;
import java.util.Optional;

public interface DirectorRepo {

    //add director
    boolean addDirector (long id, String firstName, String lastName, String country);
    //update director
    //delete director
    boolean deleteDirector(long id);

    Optional<Director> getId(long id);

    Optional<Director> getName(String name);

    List<Director> getCountry(String country);
}
