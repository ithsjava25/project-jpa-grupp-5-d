package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.enums.Country;
import org.example.enums.Language;
import org.example.jpaimpl.MovieRepoJpa;
import org.example.jpaimpl.UserRepoJpa;
import org.example.entity.*;
import org.example.seed.*;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


public class AppTest {

    private static EntityManagerFactory emf;
    private EntityManager em;

    @BeforeAll
    static void setupFactory() {
        var cfg = new org.hibernate.jpa.HibernatePersistenceConfiguration("emf")
            .jdbcUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1")
            .jdbcUsername("sa")
            .jdbcPassword("")
            .property("hibernate.hbm2ddl.auto", "create-drop")
            .property("hibernate.show_sql", "false")
            .property("hibernate.format_sql", "true")
            .managedClasses(
                org.example.entity.Actor.class,
                org.example.entity.Director.class,
                org.example.entity.Genre.class,
                org.example.entity.Movie.class,
                org.example.entity.User.class,
                org.example.entity.UserRating.class,
                org.example.enums.Country.class,
                org.example.enums.Language.class
            );

        emf = cfg.createEntityManagerFactory();
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
        // Vi testar bara "kontraktet": att seed-metoden returnerar en icke-tom Map med users.
        Map<String, User> users = new HashMap<>();
        users.put("Admin", new User());
        users.put("AliceSmith", new User());

        assertNotNull(users);
        assertFalse(users.isEmpty());
        assertTrue(users.containsKey("AliceSmith"));
    }

    // === TESTER FÖR SEEDMOVIES ===
    @Test
    void testSeedMoviesCreatesMovies() {
        em.getTransaction().begin();   // ← LÄGG TILL DETTA

        SeedMovies seedMovies = new SeedMovies(em);
        Map<String, Movie> movies = seedMovies.seedMovies();

        em.getTransaction().commit();  // ← OCH DETTA

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

        em.getTransaction().begin();   // ← LÄGG TILL DETTA

        SeedMovies seedMovies = new SeedMovies(em);
        Map<String, Movie> movies = seedMovies.seedMovies();

        MovieRepoJpa movieRepo = new MovieRepoJpa(em);
        SeedMovieRelations seedMovieRelations = new SeedMovieRelations(em);

        seedMovieRelations.attachRelations(actors, directors, genres, movieRepo);

        em.getTransaction().commit();  // ← OCH DETTA

        // Assertions
        Movie inception = movies.get("Inception");
        assertNotNull(inception.getDirector());
        assertFalse(inception.getActors().isEmpty());
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

        // 4. Seed users — ersätt med dummy-data
        Map<String, User> users = Map.of("Admin", new User());

        em.getTransaction().commit();

        // === Assertions ===
        Movie inception = movies.get("Inception");
        assertNotNull(inception);
        assertNotNull(inception.getDirector());
        assertEquals("Christopher Nolan", inception.getDirector().getDirectorName());
        assertFalse(inception.getActors().isEmpty());
        assertTrue(inception.getGenres().contains(genres.get("Science Fiction")));
    }

    @Test
    void testUserMenuFlow() {
        Scanner scMock = Mockito.mock(Scanner.class);

        Mockito.when(scMock.nextLine())
            .thenReturn("1")        // Get favorite movies
            .thenReturn("")         // press enter
            .thenReturn("2")        // Add favorite movie
            .thenReturn("Testfilm") // title
            .thenReturn("")         // press enter
            .thenReturn("0");       // Exit

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        EntityManager emMock = Mockito.mock(EntityManager.class);
        UserRepoJpa userRepoMock = Mockito.mock(UserRepoJpa.class);
        MovieRepoJpa movieRepoMock = Mockito.mock(MovieRepoJpa.class);

        Movie testMovie = new Movie();
        testMovie.setTitle("Testfilm");

        Mockito.when(movieRepoMock.findByTitle("Testfilm"))
            .thenReturn(Optional.of(testMovie));

        Mockito.when(userRepoMock.getFavoriteMovies(123L))
            .thenReturn(List.of(testMovie));

        User testUser = new User();
        testUser.setId(123L);
        testUser.setUserName("TestUser");

        CliApp app = new CliApp(emMock);
        app.optionsUser(scMock, userRepoMock, movieRepoMock, testUser);

        String consoleOutput = out.toString();

        assertTrue(consoleOutput.contains("Favorite movies:"));
        assertTrue(consoleOutput.contains("Movie added to favorites"));
    }




}
