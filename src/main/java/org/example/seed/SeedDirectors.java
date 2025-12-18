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

        // Befintliga
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

        // Nya riktiga
        directors.put("Jon Watts", directorRepo.addDirector("Jon Watts", Country.USA));
        directors.put("Anthony Russo", directorRepo.addDirector("Anthony Russo", Country.USA));
        directors.put("Joe Russo", directorRepo.addDirector("Joe Russo", Country.USA));
        directors.put("George Lucas", directorRepo.addDirector("George Lucas", Country.USA));
        directors.put("Ryan Coogler", directorRepo.addDirector("Ryan Coogler", Country.USA));
        directors.put("James Gunn", directorRepo.addDirector("James Gunn", Country.USA));
        directors.put("Scott Derrickson", directorRepo.addDirector("Scott Derrickson", Country.USA));
        directors.put("Jon Favreau", directorRepo.addDirector("Jon Favreau", Country.USA));
        directors.put("Peyton Reed", directorRepo.addDirector("Peyton Reed", Country.USA));
        directors.put("David Slade", directorRepo.addDirector("David Slade", Country.UK));

        // Nya fiktiva placeholders
        directors.put("Oliver Hunt", directorRepo.addDirector("Oliver Hunt", Country.UK));
        directors.put("Sophia Crimson", directorRepo.addDirector("Sophia Crimson", Country.USA));
        directors.put("Lucas Emerald", directorRepo.addDirector("Lucas Emerald", Country.CANADA));
        directors.put("Ethan Midnight", directorRepo.addDirector("Ethan Midnight", Country.USA));
        directors.put("Henry Dragon", directorRepo.addDirector("Henry Dragon", Country.UK));
        directors.put("Amelia Star", directorRepo.addDirector("Amelia Star", Country.USA));
        directors.put("Karl Stahl", directorRepo.addDirector("Karl Stahl", Country.GERMANY));
        directors.put("Nova Celeste", directorRepo.addDirector("Nova Celeste", Country.USA));
        directors.put("Pierre Realm", directorRepo.addDirector("Pierre Realm", Country.FRANCE));
        directors.put("Oliver Neon", directorRepo.addDirector("Oliver Neon", Country.UK));
        directors.put("Stella Dawn", directorRepo.addDirector("Stella Dawn", Country.USA));
        directors.put("Evelyn Mystic", directorRepo.addDirector("Evelyn Mystic", Country.CANADA));
        directors.put("Henry Voyage", directorRepo.addDirector("Henry Voyage", Country.USA));
        directors.put("Sophia Frost", directorRepo.addDirector("Sophia Frost", Country.UK));

        return directors;
    }
}
