package org.example;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.example.enums.Country;
import org.example.enums.Language;
import org.example.jpaimpl.*;
import org.example.pojo.Movie;

import java.time.LocalDate;

public class DatabaseFiller {

    private final EntityManager em;

    public DatabaseFiller(EntityManager em) {
        this.em = em;
    }

    public void seedActors() {
        ActorRepoJpa actorRepo = new ActorRepoJpa(em);

        actorRepo.addActor("Robert Downey Jr.", Country.USA);
        actorRepo.addActor("Scarlett Johansson", Country.USA);
        actorRepo.addActor("Chris Hemsworth", Country.AUSTRALIA);
        actorRepo.addActor("Chris Evans", Country.USA);
        actorRepo.addActor("Mark Ruffalo", Country.USA);
        actorRepo.addActor("Jeremy Renner", Country.USA);
        actorRepo.addActor("Tom Holland", Country.UK);
        actorRepo.addActor("Benedict Cumberbatch", Country.UK);
        actorRepo.addActor("Paul Rudd", Country.USA);
        actorRepo.addActor("Elizabeth Olsen", Country.USA);
        actorRepo.addActor("Brie Larson", Country.USA);
        actorRepo.addActor("Chadwick Boseman", Country.USA);
        actorRepo.addActor("Anthony Mackie", Country.USA);
        actorRepo.addActor("Sebastian Stan", Country.ROMANIA);
        actorRepo.addActor("Don Cheadle", Country.USA);
        actorRepo.addActor("Samuel L. Jackson", Country.USA);
        actorRepo.addActor("Cobie Smulders", Country.CANADA);
        actorRepo.addActor("Tom Hiddleston", Country.UK);
        actorRepo.addActor("Natalie Portman", Country.USA);
        actorRepo.addActor("Christian Bale", Country.UK);
        actorRepo.addActor("Heath Ledger", Country.AUSTRALIA);
        actorRepo.addActor("Joaquin Phoenix", Country.USA);
        actorRepo.addActor("Anne Hathaway", Country.USA);
        actorRepo.addActor("Gary Oldman", Country.UK);
        actorRepo.addActor("Morgan Freeman", Country.USA);
        actorRepo.addActor("Brad Pitt", Country.USA);
        actorRepo.addActor("Leonardo DiCaprio", Country.USA);
        actorRepo.addActor("Matt Damon", Country.USA);
        actorRepo.addActor("Ben Affleck", Country.USA);
        actorRepo.addActor("Angelina Jolie", Country.USA);
        actorRepo.addActor("Jennifer Lawrence", Country.USA);
        actorRepo.addActor("Emma Stone", Country.USA);
        actorRepo.addActor("Ryan Gosling", Country.CANADA);
        actorRepo.addActor("Hugh Jackman", Country.AUSTRALIA);
        actorRepo.addActor("Chris Pratt", Country.USA);
        actorRepo.addActor("Zoe Saldana", Country.USA);
        actorRepo.addActor("Dave Bautista", Country.USA);
        actorRepo.addActor("Vin Diesel", Country.USA);
        actorRepo.addActor("Michelle Rodriguez", Country.USA);
        actorRepo.addActor("Gal Gadot", Country.ISRAEL);
        actorRepo.addActor("Henry Cavill", Country.UK);
        actorRepo.addActor("Ben Kingsley", Country.UK);
        actorRepo.addActor("Judi Dench", Country.UK);
        actorRepo.addActor("Ian McKellen", Country.UK);
        actorRepo.addActor("Patrick Stewart", Country.UK);
        actorRepo.addActor("Michael Fassbender", Country.GERMANY);
        actorRepo.addActor("James McAvoy", Country.UK);
        actorRepo.addActor("Tilda Swinton", Country.UK);
        actorRepo.addActor("Kate Winslet", Country.UK);
        actorRepo.addActor("Rachel Weisz", Country.UK);
        actorRepo.addActor("Naomi Watts", Country.AUSTRALIA);
    }

    @Transactional
    public void seedDirectors() {
        DirectorRepoJpa directorRepo = new DirectorRepoJpa(em);

        directorRepo.addDirector("Steven Spielberg", Country.USA);
        directorRepo.addDirector("Christopher Nolan", Country.UK);
        directorRepo.addDirector("Quentin Tarantino", Country.USA);
        directorRepo.addDirector("Martin Scorsese", Country.USA);
        directorRepo.addDirector("James Cameron", Country.CANADA);
        directorRepo.addDirector("Ridley Scott", Country.UK);
        directorRepo.addDirector("Peter Jackson", Country.NEW_ZEALAND);
        directorRepo.addDirector("Alfonso Cuarón", Country.MEXICO);
        directorRepo.addDirector("Guillermo del Toro", Country.MEXICO);
        directorRepo.addDirector("Clint Eastwood", Country.USA);
        directorRepo.addDirector("Tim Burton", Country.USA);
        directorRepo.addDirector("Wes Anderson", Country.USA);
        directorRepo.addDirector("David Fincher", Country.USA);
        directorRepo.addDirector("Spike Lee", Country.USA);
        directorRepo.addDirector("George Miller", Country.AUSTRALIA);
        directorRepo.addDirector("Taika Waititi", Country.NEW_ZEALAND);
        directorRepo.addDirector("Ang Lee", Country.TAIWAN);
        directorRepo.addDirector("Paul Thomas Anderson", Country.USA);
        directorRepo.addDirector("Sofia Coppola", Country.USA);
        directorRepo.addDirector("Greta Gerwig", Country.USA);
    }

    @Transactional
    public void seedMovies() {
        MovieRepoJpa movieRepo = new MovieRepoJpa(em);

            Object[][] movies = {
            {"Avengers: Endgame", LocalDate.parse("2019-04-26"), 181, Country.USA, Language.ENGLISH},
            {"Inception", LocalDate.parse("2010-07-16"), 148, Country.USA, Language.ENGLISH},
            {"The Dark Knight", LocalDate.parse("2008-07-18"), 152, Country.USA, Language.ENGLISH},
            {"Titanic", LocalDate.parse("1997-12-19"), 195, Country.USA, Language.ENGLISH},
            {"Jurassic Park", LocalDate.parse("1993-06-11"), 127, Country.USA, Language.ENGLISH},
            {"The Matrix", LocalDate.parse("1999-03-31"), 136, Country.USA, Language.ENGLISH},
            {"Interstellar", LocalDate.parse("2014-11-07"), 169, Country.USA, Language.ENGLISH},
            {"Gladiator", LocalDate.parse("2000-05-05"), 155, Country.USA, Language.ENGLISH},
            {"Avatar", LocalDate.parse("2009-12-18"), 162, Country.USA, Language.ENGLISH},
            {"Star Wars: A New Hope", LocalDate.parse("1977-05-25"), 121, Country.USA, Language.ENGLISH},
            {"The Avengers", LocalDate.parse("2012-05-04"), 143, Country.USA, Language.ENGLISH},
            {"Black Panther", LocalDate.parse("2018-02-16"), 134, Country.USA, Language.ENGLISH},
            {"Guardians of the Galaxy", LocalDate.parse("2014-08-01"), 121, Country.USA, Language.ENGLISH},
            {"Doctor Strange", LocalDate.parse("2016-11-04"), 115, Country.USA, Language.ENGLISH},
            {"Thor: Ragnarok", LocalDate.parse("2017-11-03"), 130, Country.USA, Language.ENGLISH},
            {"Iron Man", LocalDate.parse("2008-05-02"), 126, Country.USA, Language.ENGLISH},
            {"Ant-Man", LocalDate.parse("2015-07-17"), 117, Country.USA, Language.ENGLISH},
            {"Spider-Man: Homecoming", LocalDate.parse("2017-07-07"), 133, Country.USA, Language.ENGLISH},
            {"Captain America: Civil War", LocalDate.parse("2016-05-06"), 147, Country.USA, Language.ENGLISH},
            {"Avengers: Infinity War", LocalDate.parse("2018-04-27"), 149, Country.USA, Language.ENGLISH},
            {"The Shadow Realm", LocalDate.parse("2021-10-10"), 142, Country.UK, Language.ENGLISH},
            {"Mystic Horizon", LocalDate.parse("2020-03-22"), 128, Country.CANADA, Language.ENGLISH},
            {"Cyberpunk Legacy", LocalDate.parse("2019-11-05"), 135, Country.USA, Language.ENGLISH},
            {"Galactic Wars: Rebirth", LocalDate.parse("2018-07-12"), 148, Country.USA, Language.ENGLISH},

            {"Rise of the Titans", LocalDate.parse("2021-08-18"), 140, Country.USA, Language.ENGLISH},
            {"Quantum Rift", LocalDate.parse("2020-05-05"), 130, Country.GERMANY, Language.GERMAN},
            {"The Last Oracle", LocalDate.parse("2019-09-10"), 138, Country.FRANCE, Language.FRENCH},
            {"Shadow Hunters", LocalDate.parse("2020-10-01"), 125, Country.UK, Language.ENGLISH},
            {"Crimson Dawn", LocalDate.parse("2021-03-12"), 137, Country.USA, Language.ENGLISH},
            {"Emerald Skies", LocalDate.parse("2018-11-23"), 140, Country.CANADA, Language.ENGLISH},
            {"Midnight Chronicles", LocalDate.parse("2019-07-17"), 128, Country.USA, Language.ENGLISH},

            {"Dragon Reign", LocalDate.parse("2019-08-14"), 145, Country.UK, Language.ENGLISH},
            {"Starlight Odyssey", LocalDate.parse("2020-06-21"), 138, Country.USA, Language.ENGLISH},
            {"Iron Horizon", LocalDate.parse("2021-05-11"), 132, Country.GERMANY, Language.GERMAN},
            {"Celestial Wars", LocalDate.parse("2022-01-18"), 140, Country.USA, Language.ENGLISH},
            {"The Final Realm", LocalDate.parse("2019-09-29"), 136, Country.FRANCE, Language.FRENCH},
            {"Neon Shadows", LocalDate.parse("2021-04-02"), 128, Country.UK, Language.ENGLISH},
            {"Galactic Dawn", LocalDate.parse("2022-03-20"), 142, Country.USA, Language.ENGLISH},
            {"Mystic Wars", LocalDate.parse("2019-11-08"), 135, Country.CANADA, Language.ENGLISH},

            {"Twilight Saga: Eclipse", LocalDate.parse("2010-06-30"), 124, Country.USA, Language.ENGLISH},
            {"The Last Voyage", LocalDate.parse("2020-08-15"), 130, Country.USA, Language.ENGLISH},
            {"Frozen Dawn", LocalDate.parse("2020-12-12"), 128, Country.UK, Language.ENGLISH},
            {"Dragonheart Reborn", LocalDate.parse("2019-10-18"), 145, Country.USA, Language.ENGLISH},
            {"Starlight Crusade", LocalDate.parse("2021-03-03"), 138, Country.GERMANY, Language.GERMAN},
            {"Iron Legacy", LocalDate.parse("2022-05-05"), 132, Country.USA, Language.ENGLISH},
            {"Celestial Horizon", LocalDate.parse("2020-09-20"), 140, Country.FRANCE, Language.FRENCH},
            {"Neon Odyssey", LocalDate.parse("2019-06-10"), 128, Country.UK, Language.ENGLISH},
            {"Galactic Reign", LocalDate.parse("2020-11-25"), 142, Country.USA, Language.ENGLISH},
            {"Mystic Eclipse", LocalDate.parse("2021-07-07"), 135, Country.CANADA, Language.ENGLISH}
        };

        int i = 0;
        for (Object[] m : movies) {
            movieRepo.addMovie(
                (String) m[0],
                (LocalDate) m[1],
                (int) m[2],
                (Country) m[3],
                (Language) m[4]
            );

            i++;
            if (i % 20 == 0) {
                em.flush();
                em.clear();
            }

        }

    }

    public void seedGenres() {
        GenreRepoJpa genreRepo = new GenreRepoJpa(em);

        genreRepo.addGenre("Action");
        genreRepo.addGenre("Adventure");
        genreRepo.addGenre("Comedy");
        genreRepo.addGenre("Drama");
        genreRepo.addGenre("Fantasy");
        genreRepo.addGenre("Horror");
        genreRepo.addGenre("Mystery");
        genreRepo.addGenre("Romance");
        genreRepo.addGenre("Science Fiction");
        genreRepo.addGenre("Thriller");
        genreRepo.addGenre("Western");
        genreRepo.addGenre("Animation");
        genreRepo.addGenre("Musical");
        genreRepo.addGenre("Documentary");
        genreRepo.addGenre("Crime");
        genreRepo.addGenre("War");
        genreRepo.addGenre("Biography");
        genreRepo.addGenre("Sport");
        genreRepo.addGenre("Family");
        genreRepo.addGenre("History");
    }

    public void seedUsers() {
        UserRepoJpa userRepo = new UserRepoJpa(em);

        String[][] users = {
            {"AliceSmith", "pass123"},
            {"BobJones", "qwerty"},
            {"CharlieBrown", "abc123"},
            {"DavidMiller", "password1"},
            {"EvaJohnson", "123456"},
            {"FrankWilson", "letmein"},
            {"GraceLee", "mypassword"},
            {"HannahClark", "passw0rd"},
            {"IanWalker", "admin123"},
            {"JackHall", "welcome1"},

            {"KarenYoung", "hello123"},
            {"LeoKing", "sunshine"},
            {"MiaScott", "flower99"},
            {"NathanAdams", "moonlight"},
            {"OliviaBaker", "star123"},
            {"PeterWright", "dragon1"},
            {"QuinnHarris", "blueSky"},
            {"RachelGreen", "green123"},
            {"SamRoberts", "robot2025"},
            {"TinaEvans", "tiger12"},

            {"UmaCarter", "queen99"},
            {"VictorMitchell", "viking1"},
            {"WendyPerez", "wonder12"},
            {"XanderCooper", "xmen123"},
            {"YaraReed", "yellow22"},
            {"ZacharyBell", "zebra99"},
            {"AmberRussell", "apple12"},
            {"BrandonWard", "banana34"},
            {"CaitlynBennett", "catDog1"},
            {"DerekCook", "delta99"},

            {"EllaFoster", "echo2025"},
            {"FelixGray", "falcon1"},
            {"GabriellaHunt", "galaxy9"},
            {"HenryJames", "harbor12"},
            {"IslaKnight", "icecream"},
            {"JasonLong", "joker99"},
            {"KylieMorgan", "kangaroo"},
            {"LiamNelson", "lion123"},
            {"MeganOwens", "mountain"},
            {"NoahPerry", "nova99"},

            {"OlgaQuinn", "olive123"},
            {"PaulRoss", "panda1"},
            {"QuincySims", "queenBee"},
            {"RileyTurner", "river12"},
            {"SophiaUnderwood", "sunset"},
            {"ThomasVega", "tornado"},
            {"UlyssesWhite", "unicorn"},
            {"VanessaXavier", "volcano"},
            {"WilliamYoung", "whale99"},
            {"XimenaZimmer", "xylophone"}
        };

        int i = 0;
        for (String[] u : users) {
            userRepo.addUser(u[0], u[1]);
            i++;
            if (i % 20 == 0) {
                em.flush();
                em.clear();
            }
        }
    }
}
