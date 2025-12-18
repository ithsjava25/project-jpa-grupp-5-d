package org.example.seed;

import jakarta.persistence.EntityManager;
import org.example.jpaimpl.MovieRepoJpa;
import org.example.pojo.Actor;
import org.example.pojo.Director;
import org.example.pojo.Genre;

import java.util.List;
import java.util.Map;

public class SeedMovieRelations {

    private final EntityManager em;

    public SeedMovieRelations(EntityManager em) {
        this.em = em;
    }

    public void attachRelations(Map<String, Actor> actors,
                                Map<String, Director> directors,
                                Map<String, Genre> genres,
                                MovieRepoJpa movieRepo) {



        // Titanic
        movieRepo.setDirector("Titanic", directors.get("James Cameron"));
        movieRepo.addActors("Titanic", List.of(
            actors.get("Leonardo DiCaprio"),
            actors.get("Kate Winslet")
        ));
        movieRepo.addGenres("Titanic", List.of(
            genres.get("Romance"),
            genres.get("Drama")
        ));

        // The Dark Knight
        movieRepo.setDirector("The Dark Knight", directors.get("Christopher Nolan"));
        movieRepo.addActors("The Dark Knight", List.of(
            actors.get("Christian Bale"),
            actors.get("Heath Ledger"),
            actors.get("Gary Oldman")
        ));
        movieRepo.addGenres("The Dark Knight", List.of(
            genres.get("Action"),
            genres.get("Thriller"),
            genres.get("Crime")
        ));

        // Jurassic Park
        movieRepo.setDirector("Jurassic Park", directors.get("Steven Spielberg"));
        movieRepo.addActors("Jurassic Park", List.of(
            actors.get("Sam Neill"),
            actors.get("Laura Dern"),
            actors.get("Jeff Goldblum")
        ));
        movieRepo.addGenres("Jurassic Park", List.of(
            genres.get("Science Fiction"),
            genres.get("Adventure")
        ));

        // The Matrix
        movieRepo.setDirector("The Matrix", directors.get("Lana Wachowski")); // lägg till i SeedDirectors
        movieRepo.addActors("The Matrix", List.of(
            actors.get("Keanu Reeves"),
            actors.get("Laurence Fishburne"),
            actors.get("Carrie-Anne Moss")
        ));
        movieRepo.addGenres("The Matrix", List.of(
            genres.get("Science Fiction"),
            genres.get("Action")
        ));

        // Interstellar
        movieRepo.setDirector("Interstellar", directors.get("Christopher Nolan"));
        movieRepo.addActors("Interstellar", List.of(
            actors.get("Matthew McConaughey"),
            actors.get("Anne Hathaway"),
            actors.get("Jessica Chastain")
        ));
        movieRepo.addGenres("Interstellar", List.of(
            genres.get("Science Fiction"),
            genres.get("Drama")
        ));

        // Gladiator
        movieRepo.setDirector("Gladiator", directors.get("Ridley Scott"));
        movieRepo.addActors("Gladiator", List.of(
            actors.get("Russell Crowe"),
            actors.get("Joaquin Phoenix")
        ));
        movieRepo.addGenres("Gladiator", List.of(
            genres.get("Action"),
            genres.get("Drama"),
            genres.get("History")
        ));
        // Avatar
        movieRepo.setDirector("Avatar", directors.get("James Cameron"));
        movieRepo.addActors("Avatar", List.of(
            actors.get("Sam Worthington"),
            actors.get("Zoe Saldana"),
            actors.get("Sigourney Weaver")
        ));
        movieRepo.addGenres("Avatar", List.of(
            genres.get("Science Fiction"),
            genres.get("Adventure")
        ));

// Inception
        movieRepo.setDirector("Inception", directors.get("Christopher Nolan"));
        movieRepo.addActors("Inception", List.of(
            actors.get("Leonardo DiCaprio"),
            actors.get("Joseph Gordon-Levitt"),
            actors.get("Ellen Page")
        ));
        movieRepo.addGenres("Inception", List.of(
            genres.get("Science Fiction"),
            genres.get("Thriller")
        ));

// Avengers: Endgame
        movieRepo.setDirector("Avengers: Endgame", directors.get("Anthony Russo")); // + Joe Russo om du vill
        movieRepo.addActors("Avengers: Endgame", List.of(
            actors.get("Robert Downey Jr."),
            actors.get("Chris Evans"),
            actors.get("Scarlett Johansson"),
            actors.get("Mark Ruffalo"),
            actors.get("Chris Hemsworth")
        ));
        movieRepo.addGenres("Avengers: Endgame", List.of(
            genres.get("Action"),
            genres.get("Science Fiction"),
            genres.get("Adventure")
        ));

// Star Wars: A New Hope
        movieRepo.setDirector("Star Wars: A New Hope", directors.get("George Lucas"));
        movieRepo.addActors("Star Wars: A New Hope", List.of(
            actors.get("Mark Hamill"),
            actors.get("Harrison Ford"),
            actors.get("Carrie Fisher")
        ));
        movieRepo.addGenres("Star Wars: A New Hope", List.of(
            genres.get("Science Fiction"),
            genres.get("Adventure")
        ));

// Black Panther
        movieRepo.setDirector("Black Panther", directors.get("Ryan Coogler"));
        movieRepo.addActors("Black Panther", List.of(
            actors.get("Chadwick Boseman"),
            actors.get("Michael B. Jordan"),
            actors.get("Lupita Nyong'o")
        ));
        movieRepo.addGenres("Black Panther", List.of(
            genres.get("Action"),
            genres.get("Adventure"),
            genres.get("Science Fiction")
        ));
// Guardians of the Galaxy
        movieRepo.setDirector("Guardians of the Galaxy", directors.get("James Gunn"));
        movieRepo.addActors("Guardians of the Galaxy", List.of(
            actors.get("Chris Pratt"),
            actors.get("Zoe Saldana"),
            actors.get("Dave Bautista"),
            actors.get("Bradley Cooper"),   // röst för Rocket
            actors.get("Vin Diesel")        // röst för Groot
        ));
        movieRepo.addGenres("Guardians of the Galaxy", List.of(
            genres.get("Science Fiction"),
            genres.get("Action"),
            genres.get("Adventure")
        ));

// Doctor Strange
        movieRepo.setDirector("Doctor Strange", directors.get("Scott Derrickson"));
        movieRepo.addActors("Doctor Strange", List.of(
            actors.get("Benedict Cumberbatch"),
            actors.get("Chiwetel Ejiofor"),
            actors.get("Rachel McAdams"),
            actors.get("Tilda Swinton")
        ));
        movieRepo.addGenres("Doctor Strange", List.of(
            genres.get("Fantasy"),
            genres.get("Action"),
            genres.get("Science Fiction")
        ));

// Thor: Ragnarok
        movieRepo.setDirector("Thor: Ragnarok", directors.get("Taika Waititi"));
        movieRepo.addActors("Thor: Ragnarok", List.of(
            actors.get("Chris Hemsworth"),
            actors.get("Tom Hiddleston"),
            actors.get("Cate Blanchett"),
            actors.get("Mark Ruffalo")
        ));
        movieRepo.addGenres("Thor: Ragnarok", List.of(
            genres.get("Action"),
            genres.get("Fantasy"),
            genres.get("Science Fiction")
        ));

// Iron Man
        movieRepo.setDirector("Iron Man", directors.get("Jon Favreau"));
        movieRepo.addActors("Iron Man", List.of(
            actors.get("Robert Downey Jr."),
            actors.get("Gwyneth Paltrow"),
            actors.get("Jeff Bridges")
        ));
        movieRepo.addGenres("Iron Man", List.of(
            genres.get("Action"),
            genres.get("Science Fiction")
        ));

// Ant-Man
        movieRepo.setDirector("Ant-Man", directors.get("Peyton Reed"));
        movieRepo.addActors("Ant-Man", List.of(
            actors.get("Paul Rudd"),
            actors.get("Evangeline Lilly"),
            actors.get("Michael Douglas")
        ));
        movieRepo.addGenres("Ant-Man", List.of(
            genres.get("Action"),
            genres.get("Science Fiction"),
            genres.get("Comedy")
        ));
// Spider-Man: Homecoming
        movieRepo.setDirector("Spider-Man: Homecoming", directors.get("Jon Watts"));
        movieRepo.addActors("Spider-Man: Homecoming", List.of(
            actors.get("Tom Holland"),
            actors.get("Michael Keaton"),
            actors.get("Zendaya"),
            actors.get("Robert Downey Jr.")
        ));
        movieRepo.addGenres("Spider-Man: Homecoming", List.of(
            genres.get("Action"),
            genres.get("Adventure"),
            genres.get("Science Fiction")
        ));

// Captain America: Civil War
        movieRepo.setDirector("Captain America: Civil War", directors.get("Anthony Russo")); // + Joe Russo
        movieRepo.addActors("Captain America: Civil War", List.of(
            actors.get("Chris Evans"),
            actors.get("Robert Downey Jr."),
            actors.get("Scarlett Johansson"),
            actors.get("Sebastian Stan")
        ));
        movieRepo.addGenres("Captain America: Civil War", List.of(
            genres.get("Action"),
            genres.get("Adventure"),
            genres.get("Science Fiction")
        ));

// Avengers: Infinity War
        movieRepo.setDirector("Avengers: Infinity War", directors.get("Anthony Russo")); // + Joe Russo
        movieRepo.addActors("Avengers: Infinity War", List.of(
            actors.get("Robert Downey Jr."),
            actors.get("Chris Hemsworth"),
            actors.get("Chris Evans"),
            actors.get("Mark Ruffalo"),
            actors.get("Scarlett Johansson"),
            actors.get("Tom Holland")
        ));
        movieRepo.addGenres("Avengers: Infinity War", List.of(
            genres.get("Action"),
            genres.get("Adventure"),
            genres.get("Science Fiction")
        ));
        // Shadow Hunters
        movieRepo.setDirector("Shadow Hunters", directors.get("Oliver Hunt")); // lägg till i SeedDirectors
        movieRepo.addActors("Shadow Hunters", List.of(
            actors.get("Emma Example"),
            actors.get("Jack Example")
        ));
        movieRepo.addGenres("Shadow Hunters", List.of(
            genres.get("Fantasy"),
            genres.get("Action")
        ));

// Crimson Dawn
        movieRepo.setDirector("Crimson Dawn", directors.get("Sophia Crimson")); // lägg till i SeedDirectors
        movieRepo.addActors("Crimson Dawn", List.of(
            actors.get("Liam Example"),
            actors.get("Mia Example")
        ));
        movieRepo.addGenres("Crimson Dawn", List.of(
            genres.get("Action"),
            genres.get("Thriller")
        ));

// Emerald Skies
        movieRepo.setDirector("Emerald Skies", directors.get("Lucas Emerald")); // lägg till i SeedDirectors
        movieRepo.addActors("Emerald Skies", List.of(
            actors.get("Noah Example"),
            actors.get("Olivia Example")
        ));
        movieRepo.addGenres("Emerald Skies", List.of(
            genres.get("Drama"),
            genres.get("Fantasy")
        ));

// Midnight Chronicles
        movieRepo.setDirector("Midnight Chronicles", directors.get("Ethan Midnight")); // lägg till i SeedDirectors
        movieRepo.addActors("Midnight Chronicles", List.of(
            actors.get("Ava Example"),
            actors.get("James Example")
        ));
        movieRepo.addGenres("Midnight Chronicles", List.of(
            genres.get("Thriller"),
            genres.get("Mystery")
        ));

// Dragon Reign
        movieRepo.setDirector("Dragon Reign", directors.get("Henry Dragon")); // lägg till i SeedDirectors
        movieRepo.addActors("Dragon Reign", List.of(
            actors.get("Sophia Example"),
            actors.get("Daniel Example")
        ));
        movieRepo.addGenres("Dragon Reign", List.of(
            genres.get("Fantasy"),
            genres.get("Adventure")
        ));
        // Starlight Odyssey
        movieRepo.setDirector("Starlight Odyssey", directors.get("Amelia Star")); // lägg till i SeedDirectors
        movieRepo.addActors("Starlight Odyssey", List.of(
            actors.get("Leo Example"),
            actors.get("Sophia Example")
        ));
        movieRepo.addGenres("Starlight Odyssey", List.of(
            genres.get("Science Fiction"),
            genres.get("Adventure")
        ));

// Iron Horizon
        movieRepo.setDirector("Iron Horizon", directors.get("Karl Stahl")); // lägg till i SeedDirectors
        movieRepo.addActors("Iron Horizon", List.of(
            actors.get("Hans Example"),
            actors.get("Greta Example")
        ));
        movieRepo.addGenres("Iron Horizon", List.of(
            genres.get("Science Fiction"),
            genres.get("Drama")
        ));

// Celestial Wars
        movieRepo.setDirector("Celestial Wars", directors.get("Nova Celeste")); // lägg till i SeedDirectors
        movieRepo.addActors("Celestial Wars", List.of(
            actors.get("Orion Example"),
            actors.get("Lyra Example")
        ));
        movieRepo.addGenres("Celestial Wars", List.of(
            genres.get("Science Fiction"),
            genres.get("Action")
        ));

// The Final Realm
        movieRepo.setDirector("The Final Realm", directors.get("Pierre Realm")); // lägg till i SeedDirectors
        movieRepo.addActors("The Final Realm", List.of(
            actors.get("Claire Example"),
            actors.get("Julien Example")
        ));
        movieRepo.addGenres("The Final Realm", List.of(
            genres.get("Fantasy"),
            genres.get("Drama")
        ));

// Neon Shadows
        movieRepo.setDirector("Neon Shadows", directors.get("Oliver Neon")); // lägg till i SeedDirectors
        movieRepo.addActors("Neon Shadows", List.of(
            actors.get("Maya Example"),
            actors.get("Ethan Example")
        ));
        movieRepo.addGenres("Neon Shadows", List.of(
            genres.get("Thriller"),
            genres.get("Science Fiction")
        ));
        // Galactic Dawn
        movieRepo.setDirector("Galactic Dawn", directors.get("Stella Dawn")); // lägg till i SeedDirectors
        movieRepo.addActors("Galactic Dawn", List.of(
            actors.get("Orion Example"),
            actors.get("Nova Example")
        ));
        movieRepo.addGenres("Galactic Dawn", List.of(
            genres.get("Science Fiction"),
            genres.get("Adventure")
        ));

// Mystic Wars
        movieRepo.setDirector("Mystic Wars", directors.get("Evelyn Mystic")); // lägg till i SeedDirectors
        movieRepo.addActors("Mystic Wars", List.of(
            actors.get("Arthur Example"),
            actors.get("Isabella Example")
        ));
        movieRepo.addGenres("Mystic Wars", List.of(
            genres.get("Fantasy"),
            genres.get("Drama")
        ));

// Twilight Saga: Eclipse
        movieRepo.setDirector("Twilight Saga: Eclipse", directors.get("David Slade"));
        movieRepo.addActors("Twilight Saga: Eclipse", List.of(
            actors.get("Kristen Stewart"),
            actors.get("Robert Pattinson"),
            actors.get("Taylor Lautner")
        ));
        movieRepo.addGenres("Twilight Saga: Eclipse", List.of(
            genres.get("Fantasy"),
            genres.get("Romance"),
            genres.get("Drama")
        ));

// The Last Voyage
        movieRepo.setDirector("The Last Voyage", directors.get("Henry Voyage")); // lägg till i SeedDirectors
        movieRepo.addActors("The Last Voyage", List.of(
            actors.get("Lucas Example"),
            actors.get("Emma Example")
        ));
        movieRepo.addGenres("The Last Voyage", List.of(
            genres.get("Adventure"),
            genres.get("Drama")
        ));

// Frozen Dawn
        movieRepo.setDirector("Frozen Dawn", directors.get("Sophia Frost")); // lägg till i SeedDirectors
        movieRepo.addActors("Frozen Dawn", List.of(
            actors.get("Anna Example"),
            actors.get("Eric Example")
        ));
        movieRepo.addGenres("Frozen Dawn", List.of(
            genres.get("Fantasy"),
            genres.get("Adventure")
        ));








        em.flush();
        em.clear();
    }
}
