package org.example.repo;

import org.example.pojo.Director;
import org.example.enums.Country;

import java.util.List;
import java.util.Optional;

public interface DirectorRepo {

    //add director
    Director addDirector (String directorName, Country country);
    //update director
    //delete director
    boolean deleteDirector(long id);

    Optional<Director> findById(Long id);

    Optional<Director> findByName(String directorName);

    List<Director> getCountry (Country country);

}
