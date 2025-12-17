package org.example;

import jakarta.persistence.EntityManager;
import org.example.enums.Country;
import org.example.jpaimpl.ActorRepoJpa;
import org.example.pojo.Actor;

import java.util.HashMap;
import java.util.Map;

public class SeedActors {
    private final EntityManager em;

    public SeedActors(EntityManager em) {
        this.em = em;
    }

    public Map<String, Actor> seed() {
        ActorRepoJpa actorRepo = new ActorRepoJpa(em);
        Map<String, Actor> actors = new HashMap<>();

        actors.put("Robert Downey Jr.", actorRepo.addActor("Robert Downey Jr.", Country.USA));
        actors.put("Scarlett Johansson", actorRepo.addActor("Scarlett Johansson", Country.USA));
        actors.put("Chris Hemsworth", actorRepo.addActor("Chris Hemsworth", Country.AUSTRALIA));
        actors.put("Chris Evans", actorRepo.addActor("Chris Evans", Country.USA));
        actors.put("Mark Ruffalo", actorRepo.addActor("Mark Ruffalo", Country.USA));
        actors.put("Jeremy Renner", actorRepo.addActor("Jeremy Renner", Country.USA));
        actors.put("Tom Holland", actorRepo.addActor("Tom Holland", Country.UK));
        actors.put("Benedict Cumberbatch", actorRepo.addActor("Benedict Cumberbatch", Country.UK));
        actors.put("Paul Rudd", actorRepo.addActor("Paul Rudd", Country.USA));
        actors.put("Elizabeth Olsen", actorRepo.addActor("Elizabeth Olsen", Country.USA));
        actors.put("Brie Larson", actorRepo.addActor("Brie Larson", Country.USA));
        actors.put("Chadwick Boseman", actorRepo.addActor("Chadwick Boseman", Country.USA));
        actors.put("Anthony Mackie", actorRepo.addActor("Anthony Mackie", Country.USA));
        actors.put("Sebastian Stan", actorRepo.addActor("Sebastian Stan", Country.ROMANIA));
        actors.put("Don Cheadle", actorRepo.addActor("Don Cheadle", Country.USA));
        actors.put("Samuel L. Jackson", actorRepo.addActor("Samuel L. Jackson", Country.USA));
        actors.put("Cobie Smulders", actorRepo.addActor("Cobie Smulders", Country.CANADA));
        actors.put("Tom Hiddleston", actorRepo.addActor("Tom Hiddleston", Country.UK));
        actors.put("Natalie Portman", actorRepo.addActor("Natalie Portman", Country.USA));
        actors.put("Christian Bale", actorRepo.addActor("Christian Bale", Country.UK));
        actors.put("Heath Ledger", actorRepo.addActor("Heath Ledger", Country.AUSTRALIA));
        actors.put("Joaquin Phoenix", actorRepo.addActor("Joaquin Phoenix", Country.USA));
        actors.put("Anne Hathaway", actorRepo.addActor("Anne Hathaway", Country.USA));
        actors.put("Gary Oldman", actorRepo.addActor("Gary Oldman", Country.UK));
        actors.put("Morgan Freeman", actorRepo.addActor("Morgan Freeman", Country.USA));
        actors.put("Brad Pitt", actorRepo.addActor("Brad Pitt", Country.USA));
        actors.put("Leonardo DiCaprio", actorRepo.addActor("Leonardo DiCaprio", Country.USA));
        actors.put("Matt Damon", actorRepo.addActor("Matt Damon", Country.USA));
        actors.put("Ben Affleck", actorRepo.addActor("Ben Affleck", Country.USA));
        actors.put("Angelina Jolie", actorRepo.addActor("Angelina Jolie", Country.USA));
        actors.put("Jennifer Lawrence", actorRepo.addActor("Jennifer Lawrence", Country.USA));
        actors.put("Emma Stone", actorRepo.addActor("Emma Stone", Country.USA));
        actors.put("Ryan Gosling", actorRepo.addActor("Ryan Gosling", Country.CANADA));
        actors.put("Hugh Jackman", actorRepo.addActor("Hugh Jackman", Country.AUSTRALIA));
        actors.put("Chris Pratt", actorRepo.addActor("Chris Pratt", Country.USA));
        actors.put("Zoe Saldana", actorRepo.addActor("Zoe Saldana", Country.USA));
        actors.put("Dave Bautista", actorRepo.addActor("Dave Bautista", Country.USA));
        actors.put("Vin Diesel", actorRepo.addActor("Vin Diesel", Country.USA));
        actors.put("Michelle Rodriguez", actorRepo.addActor("Michelle Rodriguez", Country.USA));
        actors.put("Gal Gadot", actorRepo.addActor("Gal Gadot", Country.ISRAEL));
        actors.put("Henry Cavill", actorRepo.addActor("Henry Cavill", Country.UK));
        actors.put("Ben Kingsley", actorRepo.addActor("Ben Kingsley", Country.UK));
        actors.put("Judi Dench", actorRepo.addActor("Judi Dench", Country.UK));
        actors.put("Ian McKellen", actorRepo.addActor("Ian McKellen", Country.UK));
        actors.put("Patrick Stewart", actorRepo.addActor("Patrick Stewart", Country.UK));
        actors.put("Michael Fassbender", actorRepo.addActor("Michael Fassbender", Country.GERMANY));
        actors.put("James McAvoy", actorRepo.addActor("James McAvoy", Country.UK));
        actors.put("Tilda Swinton", actorRepo.addActor("Tilda Swinton", Country.UK));
        actors.put("Kate Winslet", actorRepo.addActor("Kate Winslet", Country.UK));
        actors.put("Rachel Weisz", actorRepo.addActor("Rachel Weisz", Country.UK));
        actors.put("Naomi Watts", actorRepo.addActor("Naomi Watts", Country.AUSTRALIA));
        actors.put("Tom Hanks", actorRepo.addActor("Tom Hanks", Country.USA));
        actors.put("Ingrid Bergman", actorRepo.addActor("Ingrid Bergman", Country.SWEDEN));

        return actors;
    }
}
