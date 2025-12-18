package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.enums.Country;
import org.example.enums.Language;
import org.example.jpaimpl.MovieRepoJpa;
import org.example.pojo.*;
import org.example.seed.*;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    private static EntityManagerFactory emf;
    private EntityManager em;

    @BeforeAll
    static void setupFactory() {
        emf = new org.hibernate.jpa.HibernatePersistenceProvider()
            .createEntityManagerFactory("emf", Map.of(
                "jakarta.persistence.jdbc.url", "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1",
                "jakarta.persistence.jdbc.user", "sa",
                "jakarta.persistence.jdbc.password", "",
                "hibernate.hbm2ddl.auto", "create-drop",
                "hibernate.show_sql", "true"
            ));
    }

    @BeforeEach
    void setup() {
        em = emf.createEntityManager();
    }

    @AfterEach
    void tearDown() {
        em.close();
    }

    @AfterAll
    static void closeFactory() {
        emf.close();
    }

    // === TESTER FÖR SEEDGENRES ===
    @Test
    void testSeedGenresCreatesAllGenres() {
        SeedGenres seedGenres = new SeedGenres(em);
        Map<String, Genre> genres = seedGenres.seed();

        assertNotNull(genres);
        assertTrue(genres.containsKey("Action"));
        assertTrue(genres.containsKey("Science Fiction"));
        assertEquals(20, genres.size()); // du har lagt in 20 genrer
    }

    // === TESTER FÖR SEEDACTORS ===
    @Test
    void testSeedActorsCreatesActors() {
        SeedActors seedActors = new SeedActors(em);
        Map<String, Actor> actors = seedActors.seed();

        assertNotNull(actors);
        assertTrue(actors.containsKey("Leonardo DiCaprio"));
        assertTrue(actors.containsKey("Robert Downey Jr."));
        assertTrue(actors.containsKey("Emma Example")); // placeholder
    }

    // === TESTER FÖR SEEDDIRECTORS ===
    @Test
    void testSeedDirectorsCreatesDirectors() {
        SeedDirectors seedDirectors = new SeedDirectors(em);
        Map<String, Director> directors = seedDirectors.seed();

        assertNotNull(directors);
        assertTrue(directors.containsKey("Steven Spielberg"));
        assertTrue(directors.containsKey("Christopher Nolan"));
        assertTrue(directors.containsKey("Oliver Hunt")); // placeholder
    }

    // === TESTER FÖR SEEDUSERS ===
    @Test
    void testSeedUsersCreatesUsers() {
        SeedUsers seedUsers = new SeedUsers(em);
        Map<String, User> users = seedUsers.seed();

        assertNotNull(users);
        assertFalse(users.isEmpty());
        // exempel: kontrollera att en testuser finns
        assertTrue(users.containsKey("TestUser1"));
    }

    // === TESTER FÖR SEEDMOVIES ===
    @Test
    void testSeedMoviesCreatesMovies() {
        SeedMovies seedMovies = new SeedMovies(em);
        Map<String, Movie> movies = seedMovies.seedMovies();

        assertNotNull(movies);
        assertTrue(movies.containsKey("Inception"));
        assertTrue(movies.containsKey("Avatar"));
        assertTrue(movies.containsKey("Shadow Hunters")); // fiktiv
    }

    // === TESTER FÖR MOVIEREPOJPA ===
    @Test
    void testAddMovie() {
        MovieRepoJpa movieRepo = new MovieRepoJpa(em);

        em.getTransaction().begin();
        Movie movie = movieRepo.addMovie(
            "Test Movie",
            LocalDate.now(),
            120,
            Country.USA,
            Language.ENGLISH
        );
        em.getTransaction().commit();

        assertNotNull(movie.getId());
        assertEquals("Test Movie", movie.getTitle());
        assertEquals(Country.USA, movie.getCountry());
        assertEquals(Language.ENGLISH, movie.getLanguage());
    }

    // === TESTER FÖR RELATIONER ===
    @Test
    void testAttachRelationsSetsDirectorAndActors() {
        SeedGenres seedGenres = new SeedGenres(em);
        Map<String, Genre> genres = seedGenres.seed();

        SeedActors seedActors = new SeedActors(em);
        Map<String, Actor> actors = seedActors.seed();

        SeedDirectors seedDirectors = new SeedDirectors(em);
        Map<String, Director> directors = seedDirectors.seed();

        SeedMovies seedMovies = new SeedMovies(em);
        Map<String, Movie> movies = seedMovies.seedMovies();

        MovieRepoJpa movieRepo = new MovieRepoJpa(em);
        SeedMovieRelations seedMovieRelations = new SeedMovieRelations(em);

        em.getTransaction().begin();
        seedMovieRelations.attachRelations(actors, directors, genres, movieRepo);
        em.getTransaction().commit();

        // Kontrollera att en film har fått en director
        Movie inception = movies.get("Inception");
        assertNotNull(inception.getDirector());

        // Kontrollera att en film har fått actors
        assertFalse(inception.getActors().isEmpty());

        // Kontrollera att en film har fått genres
        assertFalse(inception.getGenres().isEmpty());
    }
    @Test
    void testFullSeedIntegration() {
        em.getTransaction().begin();

        // 1. Seed grunddata
        SeedGenres seedGenres = new SeedGenres(em);
        Map<String, Genre> genres = seedGenres.seed();

        SeedActors seedActors = new SeedActors(em);
        Map<String, Actor> actors = seedActors.seed();

        SeedDirectors seedDirectors = new SeedDirectors(em);
        Map<String, Director> directors = seedDirectors.seed();

        // 2. Seed filmer
        SeedMovies seedMovies = new SeedMovies(em);
        Map<String, Movie> movies = seedMovies.seedMovies();

        // 3. Seed relationer
        MovieRepoJpa movieRepo = new MovieRepoJpa(em);
        SeedMovieRelations seedMovieRelations = new SeedMovieRelations(em);
        seedMovieRelations.attachRelations(actors, directors, genres, movieRepo);

        // 4. Seed users
        SeedUsers seedUsers = new SeedUsers(em);
        Map<String, User> users = seedUsers.seed();

        em.getTransaction().commit();

        // === Assertions ===
        // Kontrollera att en film finns
        Movie inception = movies.get("Inception");
        assertNotNull(inception);

        // Kontrollera att Inception har en director
        assertNotNull(inception.getDirector());
        assertEquals("Christopher Nolan", inception.getDirector().getDirectorName());

        // Kontrollera att Inception har actors
        assertFalse(inception.getActors().isEmpty());

        // Kontrollera att Inception har genres
        assertTrue(inception.getGenres().contains(genres.get("Science Fiction")));

        // Kontrollera att minst en user finns
        assertFalse(users.isEmpty());
    }
}
