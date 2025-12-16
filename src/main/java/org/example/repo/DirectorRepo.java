package org.example.repo;

import org.example.pojo.Director;

import java.util.List;
import java.util.Optional;

public interface DirectorRepo {

    //add director
    boolean addDirector (String directorName, String country);
    //update director
    //delete director
    boolean deleteDirector(long id);

    Optional<Director> getById(long id);

    Optional<Director> getName(String name);

    List<Director> getCountry(String country);
}
