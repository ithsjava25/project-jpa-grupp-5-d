package org.example.seed;

import jakarta.persistence.EntityManager;
import org.example.enums.Country;
import org.example.enums.Language;
import org.example.jpaimpl.MovieRepoJpa;
import org.example.pojo.Movie;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class SeedMovies {

    private final EntityManager em;

    public SeedMovies(EntityManager em) {
        this.em = em;
    }

    public Map<String, Movie> seedMovies() {
        MovieRepoJpa movieRepo = new MovieRepoJpa(em);

        Map<String, Movie> moviesMap = new HashMap<>();

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
            Movie movie = movieRepo.addMovie(
                (String) m[0],
                (LocalDate) m[1],
                (int) m[2],
                (Country) m[3],
                (Language) m[4]
            );
            moviesMap.put((String) m[0], movie);

            i++;
            if (i % 20 == 0) {
                em.flush();
                em.clear();
            }

        }

        return moviesMap;

    }
}
