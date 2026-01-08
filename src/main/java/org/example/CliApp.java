package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.example.enums.Language;
import org.example.jpaimpl.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.example.entity.*;

public class CliApp {


    private final EntityManager em;
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String RESET = "\u001B[0m";

    public CliApp(EntityManager em) {
        this.em = em;
    }


    public void runUserMenu(Scanner sc,
                            UserRepoJpa userRepoJpa,
                            UserRatingRepoJpa userRatingRepoJpa,
                            MovieRepoJpa movieRepoJpa,
                            ActorRepoJpa actorRepoJpa,
                            GenreRepoJpa genreRepoJpa,
                            DirectorRepoJpa directorRepoJpa,
                            User user) {
        boolean keepRunning = true;
        do {
            printMenuOptions();
            String choice = sc.nextLine();

            try {
                int number = Integer.parseInt(choice);
                switch (number) {
                    case 1 ->
                        optionsUser(sc, userRepoJpa, movieRepoJpa, user);
                    case 2 ->
                        optionsUserRating(sc, movieRepoJpa, userRatingRepoJpa, user);
                    case 3 ->
                        optionsMovies(sc, actorRepoJpa, directorRepoJpa, genreRepoJpa, movieRepoJpa, userRepoJpa, user);
                    default -> keepRunning = false;
                }
            } catch (NumberFormatException e) {
                System.out.println(RED + "Please enter a numeric value" + RESET);
            }


            if (keepRunning) {
                System.out.println("*******************************");
                System.out.println("Do you want to continue? (Y/N)");
                keepRunning = sc.nextLine().equalsIgnoreCase("Y");
            }
        } while (keepRunning);

    }

    public void optionsUser(Scanner sc,
                            UserRepoJpa userRepoJpa,
                            MovieRepoJpa movieRepoJpa,
                            User user) {

        printOptionsUser();
        boolean running = true;

        while (running) {

            String input = sc.nextLine();   // ✅ alltid nextLine()
            int choice;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println(RED + "Please enter a numeric value" + RESET);
                printOptionsUser();
                continue;
            }

            switch (choice) {
                // Get favorite movies
                case 1 -> {
                    long userID = user.getId();

                    List<Movie> favorites = userRepoJpa.getFavoriteMovies(userID);

                    if (favorites.isEmpty()) {
                        System.out.println(RED + "No favorite movie found" + RESET);
                    } else {
                        System.out.println(GREEN + "Favorite movies: " + RESET);
                        favorites.forEach(movie ->
                            System.out.println(GREEN + "- " + movie.getTitle() + RESET));
                    }
                    System.out.println("Press enter to continue back to menu.");
                    sc.nextLine();
                }
                // Add favorite movies
                case 2 -> {
                    long userID = user.getId();
                    System.out.println("Enter movie title: ");
                    String title = sc.nextLine();

                    Optional<Movie> movieOpt = movieRepoJpa.findByTitle(title);

                    if (movieOpt.isPresent()) {
                        userRepoJpa.addFavoriteMovie(userID, movieOpt.get());
                        System.out.println(GREEN + "Movie added to favorites" + RESET);
                    } else {
                        System.out.println(RED + "Movie not found" + RESET);
                    }
                    System.out.println("Press enter to continue back to menu.");
                    sc.nextLine();
                }
                // Remove favorite movie
                case 3 -> {
                    long userID = user.getId();
                    System.out.println("Enter movie title: ");
                    String title = sc.nextLine();

                    Optional<Movie> movieOpt = movieRepoJpa.findByTitle(title);

                    if (movieOpt.isPresent()) {
                        Movie movie = movieOpt.get();
                        userRepoJpa.removeFavoriteMovie(userID, movie);
                        System.out.println(GREEN + "Movie removed from favorites" + RESET);
                    } else {
                        System.out.println(RED + "Movie not found" + RESET);
                    }
                    System.out.println("Press enter to continue back to menu.");
                    sc.nextLine();
                }
                // Find users by username
                case 4 -> {
                    System.out.print("What user do you want to find? Enter username: ");
                    String userName = sc.nextLine();

                    Optional<User> userOpt = userRepoJpa.findByUserName(userName);

                    if (userOpt.isPresent()) {
                        User foundUser = userOpt.get();
                        System.out.println(GREEN + "User found: ");
                        System.out.println(GREEN + "ID: " + foundUser.getId());
                        System.out.println(GREEN + "Username: " + foundUser.getUserName() + RESET);
                    } else {
                        System.out.println(RED + "No user found with that username: " + userName + RESET);
                    }
                    System.out.println("Press enter to continue back to menu.");
                    sc.nextLine();
                }
                // Show options again
                case 5 -> {
                    printOptionsUser();
                }
                case 0 -> {
                    System.out.println("Returning to main menu...");
                    running = false;
                }
                default -> System.out.println(RED + "Invalid option" + RESET);
            }

            // ✅ Visa menyn igen om vi fortfarande är i loopen
            if (running) {
                System.out.println();
                printOptionsUser();

            }
        }
    }

    public void optionsMovies(Scanner sc,
                              ActorRepoJpa actorRepoJpa,
                              DirectorRepoJpa directorRepoJpa,
                              GenreRepoJpa genreRepoJpa,
                              MovieRepoJpa movieRepoJpa,
                              UserRepoJpa userRepoJpa,
                              User user) {
        printOptionsMovie();
        boolean running = true;
        while (running) {

            String input = sc.nextLine();
            int choice;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a numeric value");
                printOptionsMovie();
                continue;
            }

            switch (choice) {
                // ====== LIST ALL MOVIES ======
                case 1 -> {
                    System.out.println("***** You have selected to list all movies *****");
                    List<Movie> allMovies = movieRepoJpa.getAllMovies();
                    allMovies.forEach(m -> System.out.println(GREEN + "- " + m.getTitle() + RESET));

                    System.out.println("Press enter to continue back to menu.");
                    sc.nextLine();
                }
                // ====== LIST MOVIES BY LANGUAGE ======
                case 2 -> {
                    System.out.println("***** You have selected to list movies by language *****");
                    System.out.print("Enter Language: ");
                    String langInput = sc.nextLine().trim().toUpperCase();

                    try {
                        Language lang = Language.valueOf(langInput);

                        List<Movie> movies = movieRepoJpa.getMovieByLanguage(lang);
                        if (movies.isEmpty()) {
                            System.out.println(RED + "No movies found for language: " + lang + RESET);
                        } else {
                            movies.forEach(m -> System.out.println(GREEN + "- " + m.getTitle() + RESET));
                        }

                    } catch (IllegalArgumentException e) {
                        System.out.println(RED + "Invalid language input." + RESET);
                        System.out.println("Available languages: ");
                        for (Language l : Language.values()) {
                            System.out.println("- " + l);
                        }
                    }
                    System.out.println("Press enter to continue back to menu.");
                    sc.nextLine();
                }

                // ====== LIST MOVIES BY RANKING ======
                case 3 -> {
                    System.out.println("***** You have selected to list movies by ranking *****");
                    System.out.println("The ranking system is from 1-5 toasts");
                    System.out.println("Enter minimum Ranking (min 1) : ");
                    String minRank = sc.nextLine();
                    System.out.println("Enter maximum Ranking (max 5): ");
                    String maxRank = sc.nextLine();

                    try {
                        int minRankInt = Integer.parseInt(minRank);
                        if (minRankInt < 1 || minRankInt > 5){
                            System.out.println(RED + "Invalid minimum ranking. Please enter a value between 1 and 5" + RESET);
                        }
                        int maxRankInt = Integer.parseInt(maxRank);
                        if (maxRankInt < 1 || maxRankInt > 5){
                            System.out.println(RED + "Invalid maximum ranking. Please enter a value between 1 and 5" + RESET);
                        }

                        if (minRankInt > maxRankInt) {
                            System.out.println(RED + "Minimum ranking cannot be higher than maxmimum ranking" + RESET);
                        }

                        movieRepoJpa.getMovieByRanking(minRankInt, maxRankInt)
                            .forEach(m -> System.out.println(GREEN + m.getTitle()
                                + " rank: " + m.getRanking() + RESET));

                    } catch (NumberFormatException e) {
                        System.out.println(RED + "Invalid Input. Please enter numeric values only" + RESET);
                    }
                    System.out.println("Press enter to continue back to menu.");
                    sc.nextLine();
                }
                // ====== LIST MOVIES BY LENGTH ======
                case 4 -> {
                    System.out.println("***** You have selected to list movies by length *****");
                    System.out.println("Enter minimum Length in minutes: ");
                    String minLen = sc.nextLine();
                    System.out.println("Enter maximum length in minutes: ");
                    String maxLen = sc.nextLine();

                    try {
                        int minLenInt = Integer.parseInt(minLen);
                        int maxLenInt = Integer.parseInt(maxLen);

                        movieRepoJpa.getMovieByLength(minLenInt, maxLenInt)
                            .forEach(m -> System.out.println(GREEN + m.getTitle()
                                + " length: " + m.getLength() + " min" + RESET));

                    } catch (NumberFormatException e) {
                        System.out.println(RED + "Please enter a numeric value" + RESET);
                    }
                    System.out.println("Press enter to continue back to menu.");
                    sc.nextLine();
                }
                // ====== LIST MOVIES BY DATE ======
                case 5 -> {
                    System.out.println("***** You have selected to list movies by date *****");

                    LocalDate fromDate = null;
                    LocalDate toDate = null;

                    System.out.println("Enter release Date from (YYYY-MM-DD or YYYYMMDD): ");
                    String from = sc.nextLine().trim();
                    System.out.println("Enter release Date to (YYYY-MM-DD or YYYYMMDD): ");
                    String to = sc.nextLine().trim();

                    try {
                        if (from.matches("\\d{8}")) {
                            from = from.substring(0, 4) + "-" + from.substring(4, 6) + "-" + from.substring(6, 8);
                        }
                        if (to.matches("\\d{8}")) {
                            to = to.substring(0, 4) + "-" + to.substring(4, 6) + "-" + to.substring(6, 8);
                        }

                        fromDate = LocalDate.parse(from);
                        toDate = LocalDate.parse(to);
                    } catch (DateTimeParseException e) {
                        System.out.println(RED + "Invalid date format. " + e.getMessage() + ". Press Enter to continue." + RESET);
                        continue;
                    }

                    movieRepoJpa.getMovieByReleaseDate(fromDate, toDate)
                        .forEach(m -> System.out.println(GREEN + m.getTitle() + " date: " + m.getReleaseDate() + RESET));

                    System.out.println("Press enter to continue back to menu.");
                    sc.nextLine();
                }
                // ====== LIST MOVIES BY ACTOR ======
                case 6 -> {
                    System.out.println("***** You have selected find movies by actor *****");
                    System.out.println("Enter Actor name: ");
                    String actorName = sc.nextLine();

                    actorRepoJpa.findByName(actorName).ifPresentOrElse(
                        actor -> {
                            List<Movie> moviesByActor = movieRepoJpa.getByActor(actor);
                            if (moviesByActor.isEmpty()) {
                                System.out.println(RED + "No movies found for actor: " + actor.getActorName() + RESET);
                            } else {
                                moviesByActor.forEach(m -> System.out.println(GREEN + "- " + m.getTitle() + RESET));
                            }
                        },
                        () -> System.out.println(RED + "No actor found with name: " + actorName + RESET)
                    );
                    System.out.println("Press enter to continue back to menu.");
                    sc.nextLine();
                }
                // ====== LIST MOVIES BY DIRECTOR ======
                case 7 -> {
                    System.out.println("***** You have selected find a movie by a director *****");
                    System.out.println("Enter Director name: ");

                    String directorName = sc.nextLine();

                    Optional<Director> directorOpt = directorRepoJpa.findByName(directorName);

                    if (directorOpt.isEmpty()) {
                        System.out.println(RED + "No director found with name: " + directorName + RESET);
                        break;
                    }

                    Director director = directorOpt.get();
                    List<Movie> moviesByDirector = movieRepoJpa.getByDirector(director);

                    if (moviesByDirector.isEmpty()) {
                        System.out.println(RED + "No movies found for director: " + director.getDirectorName() + RESET);
                    } else {
                        moviesByDirector.forEach(m -> System.out.println(GREEN + "- " + m.getTitle() + RESET));
                    }
                    System.out.println("Press enter to continue back to menu.");
                    sc.nextLine();
                }
                // ====== FIND MOVIES BY GENRE ======
                case 8 -> {
                    System.out.println("***** You have selected find a movie by genre *****");
                    System.out.println("Enter Genre: ");
                    String genreName = sc.nextLine();

                    Optional<Genre> genreOpt = genreRepoJpa.findByName(genreName);

                    if (genreOpt.isEmpty()) {
                        System.out.println(RED + "No genre found with name: " + genreName + ". Press Enter to continue." + RESET);
                        continue;
                    }

                    Genre genre = genreOpt.get();
                    List<Movie> moviesByGenre = movieRepoJpa.getMovieByGenre(genre.getName());

                    if (moviesByGenre.isEmpty()) {
                        System.out.println( RED + "No movies found in genre: " + genre.getName() + RESET);
                    } else {
                        moviesByGenre.forEach(m -> System.out.println(GREEN + "- " + m.getTitle() + RESET));
                    }
                    System.out.println("Press enter to continue back to menu.");
                    sc.nextLine();
                }
                // ====== FIND MOVIES BY TITLE ======
                case 9 -> {
                    System.out.println("***** You have selected find a movie by title *****");
                    System.out.println("Enter movie title: ");
                    String title = sc.nextLine();

                    Optional<Movie> movieOpt = movieRepoJpa.findByTitle(title);

                    if (movieOpt.isPresent()) {
                        Movie found = movieOpt.get();
                        System.out.println(GREEN + "===== MOVIE FOUND =====" + RESET);

                        // -- TITLE
                        System.out.println(GREEN + "Title: " + found.getTitle() + RESET);

                        // -- RELEASE DATE
                        System.out.println(GREEN + "Release date: " + found.getReleaseDate() + RESET);

                        // -- DIRECTOR
                        if (found.getDirector() != null) {
                            System.out.println(GREEN + "Director: " + found.getDirector().getDirectorName() + RESET);
                        } else {
                            System.out.println(RED + "No directors found" + RESET);
                        }

                        // -- ACTORS
                        if (found.getActors() != null && !found.getActors().isEmpty()) {
                            System.out.print(GREEN + "Actors: ");

                            String actorsFormatted = found.getActors().stream()
                                    .map(Actor::getActorName).collect(Collectors.joining(", "));
                            System.out.println(GREEN + "(" + actorsFormatted + ")" + RESET);

                            //found.getActors().forEach(a -> System.out.println(a.getActorName()));
                        } else {
                            System.out.println(RED + "No actors found" + RESET);
                        }

                        // -- GENRES
                        if (found.getGenres() != null && !found.getGenres().isEmpty()) {
                            System.out.println(GREEN + "Genres: ");

                            String genresFormatted = found.getGenres().stream()
                                .map(Genre::getGenreName).collect(Collectors.joining(", "));
                            System.out.println(GREEN + "(" + genresFormatted + ")" + RESET);

                        } else {
                            System.out.println(RED + "No genres found" + RESET);
                        }

                        System.out.println("========================\n");
                    } else {
                        System.out.println(RED + "Movie not found: " + title + RESET);
                    }
                    System.out.println("Press enter to continue back to menu.");
                    sc.nextLine();
                }
                // ====== Show Options again ======
                case 10 -> {
                    continue;
                }
                case 0 -> {
                    System.out.println("Returning to main menu...");
                    running = false;
                }
                default -> System.out.println(RED + "Invalid option" + RESET);
            }
            if (running) {
                System.out.println();
                printOptionsMovie();

            }

        }


    }

    public void optionsUserRating(Scanner sc,
                                  MovieRepoJpa movieRepoJpa,
                                  UserRatingRepoJpa userRatingRepoJpa,
                                  User user) {
        printOptionsUserRating();
        boolean running = true;
        while (running) {

            String input = sc.nextLine();
            int choice;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println(RED + "Please enter a numeric value" + RESET);
                printOptionsUserRating();
                continue;
            }

            switch (choice) {
                // Rate a movie
                case 1 -> {
                    System.out.println("***** You have selected to rate a movie *****");
                    System.out.print("What movie would you like to rate? (Title): ");
                    String title = sc.nextLine();
                    Optional<Movie> movieOpt = movieRepoJpa.findByTitle(title);
                    if (movieOpt.isEmpty()) {
                        System.out.println(RED + "Movie not found. Press enter to continue to menu." + RESET);
                        continue;
                    }

                    double rating = -1.0f;
                    boolean validRating = false;

                    // 🔁 Keep asking until valid rating is entered
                    while (!validRating) {
                        System.out.print("Enter rating (1-5): ");
                        String rateString = sc.nextLine();

                        // Accept ONLY 1, 2, 3, 4, or 5
                        if (rateString.matches("[1-5]")) {
                            rating = Double.parseDouble(rateString);  // or float/int depending on your method
                            validRating = true;
                        } else {
                            System.out.println(RED + "Invalid input. Please enter an integer between 1 and 5." + RESET);
                        }
                    }

                    // ✅ Save rating once valid
                    double finalRating = rating;

                    movieOpt.ifPresent(movie -> {
                        EntityTransaction tx = em.getTransaction();
                        try {
                            tx.begin();
                            userRatingRepoJpa.rateMovie(user, movie, finalRating);
                            tx.commit();   // 🔥 commit immediately, flush happens automatically
                            System.out.println(GREEN + "You successfully rated the movie." + RESET);
                        } catch (Exception e) {
                            if (tx.isActive()) tx.rollback();
                            System.out.println(RED + "Failed to rate movie: " + e.getMessage() + RESET);
                        }
                        System.out.println("Press enter to continue back to menu.");
                        sc.nextLine();
                    });


                }
                // Movies that you rated
                case 2 -> {
                    System.out.println("***** You want to get all the movies that you rated *****");

                    List<Movie> ratedMovies = userRatingRepoJpa.getMoviesRatedByUser(user);

                    if (ratedMovies.isEmpty()) {
                        System.out.println(RED + "You haven't rated any movies yet." + RESET);
                    } else {
                        System.out.println(GREEN + "Here are the movies you have rated: " + RESET);
                        for (Movie m : ratedMovies) {
                            System.out.println("- " + m.getTitle() + " Rating: " + m.getRatings());
                        }
                    }
                    System.out.println("Press enter to continue back to menu.");
                    sc.nextLine();
                }
                // Get rating for a movie
                case 3 -> {
                    System.out.println("**** You want to get rating for a specific movie ****");
                    System.out.print("What movie title would you like to get your ratings from? ");
                    String title = sc.nextLine();

                    Optional<Movie> movieOpt = movieRepoJpa.findByTitle(title);
                    if (movieOpt.isEmpty()) {
                        System.out.println(RED + "Movie not found. Press enter to continue to menu.." + RESET);
                        continue;
                    }
                    movieOpt.ifPresent(movie -> {
                        Optional<Double> rating = userRatingRepoJpa.getRatingForMovieByUser(user, movie);
                        if (rating.isPresent()) {
                            System.out.println(GREEN + "Your rating for " + movie.getTitle() + " is: " + rating.get() + RESET);
                        } else {
                            System.out.println(RED + "You have not rated this movie yet." + RESET);
                        }
                        System.out.println("Press enter to continue back to menu.");
                        sc.nextLine();
                    });
                }
                // Print options again
                case 4 -> printOptionsUserRating();
                // Exit
                case 0 -> {
                    System.out.println("Returning to main menu...");
                    running = false;
                }
                default -> System.out.println("Invalid option");
            }
            if (running) {
                System.out.println();
                printOptionsUserRating();

            }
        }
    }


    public void printOptionsUserRating() {
        System.out.println("""
            ======== USER RATING MENU ========
            1. Rate a movie
            2. Get movies that you rated
            3. Get your rating for a movie
            4. Show options again
            0. Exit
            """);
    }
    public void printOptionsMovie() {
        System.out.println("""
            ======== MOVIE MENU ========
            1. List all movies
            2. List movies by language
            3. List movies by ranking
            4. List movies by length
            5. List movies by date
            6. List movies made by an actor
            7. List movies made by a director
            8. Find movies by genre
            9. Find a movie by title
            10. Show Options again
            0. Exit
            """);
    }
    public void printOptionsUser() {
        System.out.println("""
            ======== USER MENU ========
            1. Get favorite movies
            2. Add favorite movie
            3. Remove favorite movie
            4. Find users by username
            5. Show Options again
            0. Exit
            """);
    }
    public void printMenuOptions() {
        System.out.println("""
            ======== WHAT WOULD YOU LIKE TO DO? ========
            1. Show User Menu
            2. Show User Rating Menu
            3. Show Movie Menu
            Press any key to exit
            """);
    }
}
