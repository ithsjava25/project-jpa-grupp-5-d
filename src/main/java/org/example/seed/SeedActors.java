package org.example.seed;

import jakarta.persistence.EntityManager;
import org.example.enums.Country;
import org.example.jpaimpl.ActorRepoJpa;
import org.example.entity.Actor;

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

        // Exempel på befintliga (lägg till de du redan har)
        actors.put("Leonardo DiCaprio", actorRepo.addActor("Leonardo DiCaprio", Country.USA));
        actors.put("Kate Winslet", actorRepo.addActor("Kate Winslet", Country.UK));
        actors.put("Christian Bale", actorRepo.addActor("Christian Bale", Country.UK));
        actors.put("Heath Ledger", actorRepo.addActor("Heath Ledger", Country.AUSTRALIA));
        actors.put("Gary Oldman", actorRepo.addActor("Gary Oldman", Country.UK));
        actors.put("Sam Neill", actorRepo.addActor("Sam Neill", Country.NEW_ZEALAND));
        actors.put("Laura Dern", actorRepo.addActor("Laura Dern", Country.USA));
        actors.put("Jeff Goldblum", actorRepo.addActor("Jeff Goldblum", Country.USA));
        actors.put("Keanu Reeves", actorRepo.addActor("Keanu Reeves", Country.CANADA));
        actors.put("Laurence Fishburne", actorRepo.addActor("Laurence Fishburne", Country.USA));
        actors.put("Carrie-Anne Moss", actorRepo.addActor("Carrie-Anne Moss", Country.CANADA));
        actors.put("Matthew McConaughey", actorRepo.addActor("Matthew McConaughey", Country.USA));
        actors.put("Anne Hathaway", actorRepo.addActor("Anne Hathaway", Country.USA));
        actors.put("Jessica Chastain", actorRepo.addActor("Jessica Chastain", Country.USA));
        actors.put("Russell Crowe", actorRepo.addActor("Russell Crowe", Country.NEW_ZEALAND));
        actors.put("Joaquin Phoenix", actorRepo.addActor("Joaquin Phoenix", Country.PUERTO_RICO));

        // Nya riktiga
        actors.put("Sam Worthington", actorRepo.addActor("Sam Worthington", Country.AUSTRALIA));
        actors.put("Zoe Saldana", actorRepo.addActor("Zoe Saldana", Country.USA));
        actors.put("Sigourney Weaver", actorRepo.addActor("Sigourney Weaver", Country.USA));
        actors.put("Joseph Gordon-Levitt", actorRepo.addActor("Joseph Gordon-Levitt", Country.USA));
        actors.put("Ellen Page", actorRepo.addActor("Elliot Page", Country.CANADA)); // uppdaterat namn
        actors.put("Robert Downey Jr.", actorRepo.addActor("Robert Downey Jr.", Country.USA));
        actors.put("Chris Evans", actorRepo.addActor("Chris Evans", Country.USA));
        actors.put("Scarlett Johansson", actorRepo.addActor("Scarlett Johansson", Country.USA));
        actors.put("Mark Ruffalo", actorRepo.addActor("Mark Ruffalo", Country.USA));
        actors.put("Chris Hemsworth", actorRepo.addActor("Chris Hemsworth", Country.AUSTRALIA));
        actors.put("Mark Hamill", actorRepo.addActor("Mark Hamill", Country.USA));
        actors.put("Harrison Ford", actorRepo.addActor("Harrison Ford", Country.USA));
        actors.put("Carrie Fisher", actorRepo.addActor("Carrie Fisher", Country.USA));
        actors.put("Chadwick Boseman", actorRepo.addActor("Chadwick Boseman", Country.USA));
        actors.put("Michael B. Jordan", actorRepo.addActor("Michael B. Jordan", Country.USA));
        actors.put("Lupita Nyong'o", actorRepo.addActor("Lupita Nyong'o", Country.KENYA));
        actors.put("Chris Pratt", actorRepo.addActor("Chris Pratt", Country.USA));
        actors.put("Dave Bautista", actorRepo.addActor("Dave Bautista", Country.USA));
        actors.put("Bradley Cooper", actorRepo.addActor("Bradley Cooper", Country.USA));
        actors.put("Vin Diesel", actorRepo.addActor("Vin Diesel", Country.USA));
        actors.put("Benedict Cumberbatch", actorRepo.addActor("Benedict Cumberbatch", Country.UK));
        actors.put("Chiwetel Ejiofor", actorRepo.addActor("Chiwetel Ejiofor", Country.UK));
        actors.put("Rachel McAdams", actorRepo.addActor("Rachel McAdams", Country.CANADA));
        actors.put("Tilda Swinton", actorRepo.addActor("Tilda Swinton", Country.UK));
        actors.put("Tom Hiddleston", actorRepo.addActor("Tom Hiddleston", Country.UK));
        actors.put("Cate Blanchett", actorRepo.addActor("Cate Blanchett", Country.AUSTRALIA));
        actors.put("Gwyneth Paltrow", actorRepo.addActor("Gwyneth Paltrow", Country.USA));
        actors.put("Jeff Bridges", actorRepo.addActor("Jeff Bridges", Country.USA));
        actors.put("Paul Rudd", actorRepo.addActor("Paul Rudd", Country.USA));
        actors.put("Evangeline Lilly", actorRepo.addActor("Evangeline Lilly", Country.CANADA));
        actors.put("Michael Douglas", actorRepo.addActor("Michael Douglas", Country.USA));
        actors.put("Tom Holland", actorRepo.addActor("Tom Holland", Country.UK));
        actors.put("Michael Keaton", actorRepo.addActor("Michael Keaton", Country.USA));
        actors.put("Zendaya", actorRepo.addActor("Zendaya", Country.USA));
        actors.put("Sebastian Stan", actorRepo.addActor("Sebastian Stan", Country.ROMANIA));
        actors.put("Kristen Stewart", actorRepo.addActor("Kristen Stewart", Country.USA));
        actors.put("Robert Pattinson", actorRepo.addActor("Robert Pattinson", Country.UK));
        actors.put("Taylor Lautner", actorRepo.addActor("Taylor Lautner", Country.USA));

        // Nya fiktiva placeholders
        actors.put("Emma Example", actorRepo.addActor("Emma Example", Country.UK));
        actors.put("Jack Example", actorRepo.addActor("Jack Example", Country.UK));
        actors.put("Liam Example", actorRepo.addActor("Liam Example", Country.USA));
        actors.put("Mia Example", actorRepo.addActor("Mia Example", Country.USA));
        actors.put("Noah Example", actorRepo.addActor("Noah Example", Country.CANADA));
        actors.put("Olivia Example", actorRepo.addActor("Olivia Example", Country.CANADA));
        actors.put("Ava Example", actorRepo.addActor("Ava Example", Country.USA));
        actors.put("James Example", actorRepo.addActor("James Example", Country.USA));
        actors.put("Sophia Example", actorRepo.addActor("Sophia Example", Country.UK));
        actors.put("Daniel Example", actorRepo.addActor("Daniel Example", Country.UK));
        actors.put("Leo Example", actorRepo.addActor("Leo Example", Country.USA));
        actors.put("Hans Example", actorRepo.addActor("Hans Example", Country.GERMANY));
        actors.put("Greta Example", actorRepo.addActor("Greta Example", Country.GERMANY));
        actors.put("Orion Example", actorRepo.addActor("Orion Example", Country.USA));
        actors.put("Lyra Example", actorRepo.addActor("Lyra Example", Country.USA));
        actors.put("Claire Example", actorRepo.addActor("Claire Example", Country.FRANCE));
        actors.put("Julien Example", actorRepo.addActor("Julien Example", Country.FRANCE));
        actors.put("Maya Example", actorRepo.addActor("Maya Example", Country.UK));
        actors.put("Ethan Example", actorRepo.addActor("Ethan Example", Country.UK));
        actors.put("Nova Example", actorRepo.addActor("Nova Example", Country.USA));
        actors.put("Arthur Example", actorRepo.addActor("Arthur Example", Country.CANADA));
        actors.put("Isabella Example", actorRepo.addActor("Isabella Example", Country.CANADA));
        actors.put("Lucas Example", actorRepo.addActor("Lucas Example", Country.USA));
        actors.put("Anna Example", actorRepo.addActor("Anna Example", Country.UK));
        actors.put("Eric Example", actorRepo.addActor("Eric Example", Country.UK));

        return actors;
    }
}
