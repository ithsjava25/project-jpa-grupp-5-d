package org.example.seed;

import jakarta.persistence.EntityManager;
import org.example.enums.Country;
import org.example.jpaimpl.DirectorRepoJpa;
import org.example.pojo.Director;

import java.util.HashMap;
import java.util.Map;

public class SeedDirectors {

    private final EntityManager em;

    public SeedDirectors(EntityManager em) {
        this.em = em;
    }

    public Map<String, Director> seed() {
        DirectorRepoJpa directorRepo = new DirectorRepoJpa(em);
        Map<String, Director> directors = new HashMap<>();

        directors.put("Steven Spielberg", directorRepo.addDirector("Steven Spielberg", Country.USA));
        directors.put("Christopher Nolan", directorRepo.addDirector("Christopher Nolan", Country.UK));
        directors.put("Quentin Tarantino", directorRepo.addDirector("Quentin Tarantino", Country.USA));
        directors.put("Martin Scorsese", directorRepo.addDirector("Martin Scorsese", Country.USA));
        directors.put("James Cameron", directorRepo.addDirector("James Cameron", Country.CANADA));
        directors.put("Ridley Scott", directorRepo.addDirector("Ridley Scott", Country.UK));
        directors.put("Peter Jackson", directorRepo.addDirector("Peter Jackson", Country.NEW_ZEALAND));
        directors.put("Alfonso Cuarón", directorRepo.addDirector("Alfonso Cuarón", Country.MEXICO));
        directors.put("Guillermo del Toro", directorRepo.addDirector("Guillermo del Toro", Country.MEXICO));
        directors.put("Clint Eastwood", directorRepo.addDirector("Clint Eastwood", Country.USA));
        directors.put("Tim Burton", directorRepo.addDirector("Tim Burton", Country.USA));
        directors.put("Wes Anderson", directorRepo.addDirector("Wes Anderson", Country.USA));
        directors.put("David Fincher", directorRepo.addDirector("David Fincher", Country.USA));
        directors.put("Spike Lee", directorRepo.addDirector("Spike Lee", Country.USA));
        directors.put("George Miller", directorRepo.addDirector("George Miller", Country.AUSTRALIA));
        directors.put("Taika Waititi", directorRepo.addDirector("Taika Waititi", Country.NEW_ZEALAND));
        directors.put("Ang Lee", directorRepo.addDirector("Ang Lee", Country.TAIWAN));
        directors.put("Paul Thomas Anderson", directorRepo.addDirector("Paul Thomas Anderson", Country.USA));
        directors.put("Sofia Coppola", directorRepo.addDirector("Sofia Coppola", Country.USA));
        directors.put("Greta Gerwig", directorRepo.addDirector("Greta Gerwig", Country.USA));

        return directors;
    }
}
