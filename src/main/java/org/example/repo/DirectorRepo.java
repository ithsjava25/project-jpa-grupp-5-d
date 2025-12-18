package org.example.repo;

import org.example.pojo.Director;
import org.example.enums.Country;

import java.util.List;
import java.util.Optional;

public interface DirectorRepo {

    Director addDirector (String directorName, Country country);
    boolean deleteDirector(long id);
    List<Director> getCountry (Country country);

    Optional<Director> findById(Long id);
    Optional<Director> findByName(String directorName);
}
