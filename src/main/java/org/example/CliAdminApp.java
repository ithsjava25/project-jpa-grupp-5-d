package org.example;
import jakarta.persistence.EntityManager;
import org.example.enums.Country;
import org.example.enums.Language;
import org.example.jpaimpl.*;
import org.example.pojo.Actor;
import org.example.pojo.Director;
import org.example.pojo.Genre;
import org.example.pojo.Movie;
import org.example.seed.SeedUsers;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class CliAdminApp {

    private final EntityManager em;

    public CliAdminApp(EntityManager em) {
        this.em = em;
    }

    public void printOptions() {
        Scanner scan = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("""
                    =============ADMIN MENU=============
                    1. Add new user (userName, password)
                    2. Delete user (userId)
                    3. Add a new movie (title, date YYYY-MM-DD, length in minutes, country, language)
                    4. Delete movie (id)
                    5. Add an actor to a movie (movieId, actorId) OR (movie, actor)
                    6. Add a director to a movie (movieId, directorId)
                    7. Add a new Genre (genreName) ***** ta bort? enum?
                    8. Delete a genre (genreId)
                    9. Add a new actor (actorname, country)
                    10. Delete an actor (id)
                    11. Add a new Director (directorName, country)
                    12. Delete a director (id)
                    13. Find users by username (userName)
                    0. Exit
                """);

            int choice = scan.nextInt();
            scan.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.println("Enter a Username:");
                    String userName = scan.nextLine();

                    System.out.println("Enter a password for the user:");
                    String password = scan.nextLine();

                    UserRepoJpa userRepo = new UserRepoJpa(em);

                    em.getTransaction().begin();
                    userRepo.addUser(userName, password);
                    em.getTransaction().commit();

                    System.out.println("New user added! It will be available on next startup of the program.");
                }
                case 2 -> {
                    System.out.println("Enter userId to delete:");
                    long userId = scan.nextLong();

                    UserRepoJpa userRepo = new UserRepoJpa(em);

                    em.getTransaction().begin();
                    userRepo.deleteUser(userId);
                    em.getTransaction().commit();

                    System.out.println("User successfully deleted!");
                }
                case 3 -> {
                    //Add a new movie (title, date YYYY-MM-DD, length in minutes, country, language)
                    System.out.println("Enter the title of the movie:");
                    String movie = scan.nextLine();

                    System.out.println("Enter the date the movie was released in format YYYY-MM-DD:");
                    String dateStr = scan.nextLine();
                    LocalDate date = LocalDate.parse(dateStr);

                    System.out.println("Enter the length of the movie in minutes:");
                    int length = scan.nextInt();

                    System.out.println("Enter the country the movie is from:");
                    Country country = Country.valueOf(scan.nextLine().trim().toUpperCase());

                    System.out.println("Enter the language the movie is in:");
                    Language language = Language.valueOf(scan.nextLine().trim().toUpperCase());

                    MovieRepoJpa movieRepo = new MovieRepoJpa(em);
                    em.getTransaction().begin();
                    movieRepo.addMovie(movie, date, length, country, language);
                    em.getTransaction().commit();
                    System.out.println("Movie added!");
                }
                case 4 -> {
                    System.out.println("Enter movieId to delete:");
                    long movieId = scan.nextLong();
                    MovieRepoJpa movieRepo = new MovieRepoJpa(em);
                    em.getTransaction().begin();
                    movieRepo.deleteMovie(movieId);
                    em.getTransaction().commit();
                    System.out.println("Movie successfully deleted!");
                }
                case 5 -> {
                    System.out.println("Enter movie title:");
                    String movieTitle = scan.nextLine();
                    System.out.println("Enter actor name:");
                    String actorName = scan.nextLine();
                    MovieRepoJpa movieRepo = new MovieRepoJpa(em);
                    ActorRepoJpa actorRepo = new ActorRepoJpa(em);
                    em.getTransaction().begin();
                    Actor actor = actorRepo.findByName(actorName)
                        .orElseThrow(() -> new RuntimeException("Actor not found: " + actorName));
                    movieRepo.addActors(movieTitle, List.of(actor));
                    em.getTransaction().commit();
                    System.out.println("Actor " + actorName + " added to movie " + movieTitle + "!");
                }

                case 6 -> {
                    System.out.println("Enter movie title:");
                    String movieTitle = scan.nextLine();
                    System.out.println("Enter director name:");
                    String directorName = scan.nextLine();
                    MovieRepoJpa movieRepo = new MovieRepoJpa(em);
                    DirectorRepoJpa directorRepo = new DirectorRepoJpa(em);
                    em.getTransaction().begin();
                    Director director = directorRepo.findByName(directorName)
                        .orElseThrow(() -> new RuntimeException("Director not found: " + directorName));
                    movieRepo.setDirector(movieTitle, director);
                    em.getTransaction().commit();
                    System.out.println("Director " + directorName + " set for movie " + movieTitle + "!");
                }
                case 7 -> {
                    //7. Add a new Genre(genreName) ***** ta bort? enum?
                    System.out.println("Enter genre name to add:");
                    String genre = scan.nextLine();

                    GenreRepoJpa genreRepo = new GenreRepoJpa(em);

                    em.getTransaction().begin();
                    genreRepo.addGenre(genre);
                    em.getTransaction().commit();

                    System.out.println("Genre added!");

                }
                case 8 -> {
                    System.out.println("Enter genreId to delete:");
                    long genreId = scan.nextLong();
                    scan.nextLine();
                    GenreRepoJpa genreRepo = new GenreRepoJpa(em);
                    em.getTransaction().begin();
                    boolean deleted = genreRepo.deleteGenre(genreId);
                    em.getTransaction().commit();
                    if (deleted) {
                        System.out.println("Genre with id " + genreId + " deleted successfully!");
                    } else {
                        System.out.println("No genre found with id " + genreId);
                    }
                }

                case 9 -> {
                    System.out.println("Enter actor name:");
                    String actorName = scan.nextLine();
                    System.out.println("Enter country:");
                    Country country = Country.valueOf(scan.nextLine().toUpperCase());
                    ActorRepoJpa actorRepo = new ActorRepoJpa(em);
                    em.getTransaction().begin();
                    actorRepo.addActor(actorName, country);
                    em.getTransaction().commit();

                    System.out.println("Actor '" + actorName + "' added successfully!");
                }
                case 10 -> {
                    System.out.println("Enter actor name to delete:");
                    String actorName = scan.nextLine();

                    ActorRepoJpa actorRepo = new ActorRepoJpa(em);
                    em.getTransaction().begin();
                    actorRepo.deleteByName(actorName);
                    em.getTransaction().commit();
                    System.out.println("Actor '" + actorName + "' deleted successfully!");
                }
                case 11 -> {
                    //11. Add a new Director (directorName, country)
                    System.out.println("Enter director name to add:");
                    String director = scan.nextLine();

                    System.out.println("Enter the country the director is from:");
                    Country country = Country.valueOf(scan.nextLine());

                    DirectorRepoJpa directorRepo = new DirectorRepoJpa(em);

                    em.getTransaction().begin();
                    directorRepo.addDirector(director, country);
                    em.getTransaction().commit();

                    System.out.println("Director added!");
                }
                case 12 -> {
                    //12. Delete a director (id)
                    System.out.println("Enter director name to delete:");
                    String directorName = scan.nextLine();

                    DirectorRepoJpa directorRepo = new DirectorRepoJpa(em);

                    em.getTransaction().begin();
                    directorRepo.deleteByName(directorName);
                    em.getTransaction().commit();

                    System.out.println("Director removed!");
                }
                case 13 -> {
                    System.out.println("Enter username to search:");
                    String userName = scan.nextLine();
                    UserRepoJpa userRepo = new UserRepoJpa(em);
                    em.getTransaction().begin();
                    var users = userRepo.findByUserName(userName);
                    em.getTransaction().commit();

                    if (users.isEmpty()) {
                        System.out.println("No users found with username '" + userName + "'");
                    } else {
                        users.ifPresent(u -> System.out.println("Found user: " + u.getUserName()));
                    }
                }
                case 0 -> {
                    System.out.println("Exiting...");
                    return;
                }

            }
        }
    }

}
