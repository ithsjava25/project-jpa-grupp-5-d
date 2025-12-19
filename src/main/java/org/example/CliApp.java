package org.example;

import org.example.enums.Language;
import org.example.jpaimpl.MovieRepoJpa;
import org.example.jpaimpl.UserRatingRepoJpa;
import org.example.jpaimpl.UserRepoJpa;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.example.pojo.Actor;
import org.example.pojo.Movie;
import org.example.pojo.User;
import org.example.pojo.Director;
import org.example.pojo.Genre;

public class CliApp {

    public void runUserMenu(Scanner sc,
                                    UserRepoJpa userRepoJpa,
                                    UserRatingRepoJpa userRatingRepoJpa,
                                    MovieRepoJpa movieRepoJpa,
                                    User user) {
        String choice;
        do {
            showMenuOptions();
            choice = sc.nextLine();

            try {
                int number = Integer.parseInt(choice);

                switch (number) {
                    case 1 -> optionsUser(userRepoJpa, movieRepoJpa, user);
                    case 2 -> optionsUserRating(movieRepoJpa, userRatingRepoJpa, user);
                    case 3 -> optionsMovies(movieRepoJpa, user);
                    default -> {
                        System.out.println("Exiting the menu...");
                        return;
                    }
                }

                System.out.println("*******************************");
                System.out.println("Do you want to continue? (Y/N)");
                choice = sc.nextLine().toUpperCase();

            } catch (NumberFormatException e) {
                System.out.println("Please enter a numeric value");
            }
        } while (choice.equals("Y"));
    }

    public void optionsUser(UserRepoJpa userRepoJpa,
                            MovieRepoJpa movieRepoJpa,
                            User user) {

        printOptionsUser();
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        while (running) {

        int choice = sc.nextInt();
        sc.nextLine();

        switch(choice) {
            case 1 -> {
                System.out.println("Enter User ID: ");
                long userID = sc.nextLong();

                List<Movie> favorites = userRepoJpa.getFavoriteMovies(userID);

                if (favorites.isEmpty()) {
                    System.out.println("No favorite movie found");
                } else {
                    System.out.println("Favorite movies: ");
                    favorites.forEach(movie->
                        System.out.println("- " + movie.getTitle()));
                }

            }
            case 2 -> {
                System.out.println("Enter User ID: ");
                long userID = sc.nextLong();
                System.out.println("Enter movie title: ");
                String title = sc.nextLine();

                Optional<Movie> movieOpt = movieRepoJpa.findByTitle(title);

                if (movieOpt.isPresent()) {
                    userRepoJpa.addFavoriteMovie(userID, movieOpt.get());
                    System.out.println("Movie added to favorites");
                } else {
                    System.out.println("Movie not found");
                }
            }
            case 3 -> {
                System.out.println("Enter User ID: ");
                long userID = sc.nextLong();
                sc.nextLine();
                System.out.println("Enter movie title: ");
                String title = sc.nextLine();

                Optional<Movie> movieOpt = movieRepoJpa.findByTitle(title);

                if (movieOpt.isPresent()) {
                    Movie movie = movieOpt.get();
                    userRepoJpa.removeFavoriteMovie(userID, movie);
                    System.out.println("Movie removed from favorites");
                } else {
                    System.out.println("Movie not found");
                }
            }

            case 4 -> {
                System.out.println("Enter Username: ");
                String userName = sc.nextLine();

                Optional<User> userOpt = userRepoJpa.findByUserName(userName);

                if (userOpt.isPresent()) {
                    System.out.println("User found: ");
                    System.out.println("ID: " + user.getId());
                    System.out.println("Username: " + user.getUserName());
                } else {
                    System.out.println("No user found with that username: " + userName);
                }
            }

            case 0 -> {
                System.out.println("Exiting program...");
                running = false;
            }

            case 8 -> {
                printOptionsUser();
            }

            }
        }
    }

    public void optionsMovies(MovieRepoJpa movieRepoJpa,
                              User user) {
        printOptionsMovie();
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        while (running) {

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                // ====== LIST ALL MOVIES ======
                case 1 -> {
                    System.out.println("***** You have selected to list all movies *****");
                    List<Movie> allMovies = movieRepoJpa.getAllMovies();
                    allMovies.forEach(m-> System.out.println("- " + m.getTitle()));
                }

                // ====== LIST MOVIES BY LANGUAGE ======
                case 2 -> {
                    System.out.println("***** You have selected to list movies by language *****");
                    System.out.println("Enter Language: ");
                    String langInput = sc.nextLine().trim().toUpperCase();
                    Language lang = Language.valueOf(langInput);
                    movieRepoJpa.getMovieByLanguage(lang)
                        .forEach(m-> System.out.println("- " + m.getTitle()));
                }

                // ====== LIST MOVIES BY RANKING ======
                case 3 -> {
                    System.out.println("***** You have selected to list movies by ranking *****");
                    System.out.println("Enter minimum Ranking: ");
                    int minRank = sc.nextInt();
                    System.out.println("Enter maximum Ranking: ");
                    int maxRank = sc.nextInt();
                    sc.nextLine();

                    movieRepoJpa.getMovieByRanking(minRank, maxRank)
                        .forEach(m-> System.out.println(m.getTitle()
                        + " rank: " + m.getRanking()));
                }

                // ====== LIST MOVIES BY LENGTH ======
                case 4 -> {
                    System.out.println("***** You have selected to list movies by length *****");
                    System.out.println("Enter minimum Length in minutes: ");
                    int minLen = sc.nextInt();
                    System.out.println("Enter maximum length in minutes: ");
                    int maxLen = sc.nextInt();
                    sc.nextLine();

                    movieRepoJpa.getMovieByLength(minLen, maxLen)
                        .forEach(m-> System.out.println(m.getTitle()
                        + " length: " + m.getLength() + " min"));
                }

                // ====== LIST MOVIES BY DATE ======
                case 5 -> {
                    System.out.println("***** You have selected to list movies by date *****");
                    System.out.println("Enter release Date from: ");
                    String from = sc.nextLine();
                    System.out.println("Enter release Date to: ");
                    String to = sc.nextLine();

                    movieRepoJpa.getMovieByReleaseDate(from, to)
                        .forEach(m-> System.out.println(m.getTitle()
                        + " date: " + m.getReleaseDate()));
                }

                // ====== LIST MOVIES BY ACTOR ======
                case 6 -> {
                    System.out.println("***** You have selected find movies by actor *****");
                    System.out.println("Enter Actor name: ");
                    String actorName = sc.nextLine();

                    Actor actor = new Actor();
                    actor.setActorName(actorName);
                    List<Movie> moviesByActor = movieRepoJpa.getByActor(actor);

                    if (moviesByActor.isEmpty()) {
                        System.out.println("No movies found for actor." + actor.getActorName());
                    } else {
                        moviesByActor.forEach(m-> System.out.println("- " + m.getTitle()));
                    }
                }

                // ====== LIST MOVIES BY DIRECTOR ======
                case 7 -> {
                    System.out.println("***** You have selected find a movie by a director *****");
                    System.out.println("Enter Director name: ");
                    String directorName = sc.nextLine();

                    Director director = new Director();
                    director.setDirectorName(directorName);
                    List<Movie> moviesByDirector = movieRepoJpa.getByDirector(director);

                    if (moviesByDirector.isEmpty()) {
                        System.out.println("No movies found for director." + director.getDirectorName());
                    } else {
                        moviesByDirector.forEach(m-> System.out.println("- " + m.getTitle()));
                    }
                }

                // ====== FIND MOVIES BY GENRE ======
                case 8 -> {
                    System.out.println("***** You have selected find a movie by genre *****");
                    System.out.println("Enter Genre: ");
                    String genreName = sc.nextLine();

                    Genre genre = new Genre();
                    genre.setName(genreName);

                    List<Movie> moviesByGenre = movieRepoJpa.getMovieByGenre(genreName);

                    if (moviesByGenre.isEmpty()) {
                        System.out.println("No movies found in genre: " + genre.getName());
                    } else {
                        moviesByGenre.forEach(m-> System.out.println("- " + m.getTitle()));
                    }
                }

                // ====== FIND MOVIES BY TITLE ======
                case 9 -> {
                    System.out.println("***** You have selected find a movie by title *****");
                    System.out.println("Enter movie title: ");
                    String title = sc.nextLine();

                    Movie movie = new Movie();
                    movie.setTitle(title);

                    Optional<Movie> movieOpt = movieRepoJpa.findByTitle(movie.getTitle());

                    if (movieOpt.isPresent()) {
                        Movie found = movieOpt.get();
                        System.out.println("===== MOVIE FOUND =====");

                        // --TITLE
                        System.out.println("Title: " + found.getTitle());

                        // -- RELEASE DATE
                        System.out.println("Release date: " + movie.getReleaseDate());

                        // -- DIRECTOR
                        if (movie.getDirector() != null) {
                            System.out.println("Director: " + movie.getDirector().getDirectorName());
                        } else {
                            System.out.println("No directors found");
                        }

                        // -- ACTOR
                        if (movie.getActors() != null && !movie.getActors().isEmpty()) {
                            System.out.println("Actors: ");
                            movie.getActors().forEach(a -> System.out.println(a.getActorName() + ", "));
                            System.out.println();
                        } else {
                            System.out.println("No actors found");

                            // -- GENRES
                            if (movie.getGenres() != null && !movie.getGenres().isEmpty()) {
                                System.out.println("Genre: ");
                                movie.getGenres().forEach(g -> System.out.println(g.getName() + ", "));
                                System.out.println();
                            } else {
                                System.out.println("No genres found");
                            }
                            System.out.println("========================\n");
                        }
                        } else {
                            System.out.println("Movie not found: " + movie.getTitle());
                        }
                    }

                case 0 -> {
                    System.out.println("Exiting program...");
                running = false;
                }

                case 10 -> {
                    printOptionsMovie();
                }

            }
        }
    }

    public void optionsUserRating(MovieRepoJpa movieRepoJpa,
                                  UserRatingRepoJpa userRatingRepoJpa,
                                  User user){
        printOptionsUserRating();
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        while (running) {

            String option = sc.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(option);

                switch (choice) {
                    // Rate a movie
                    case 1 -> {
                        System.out.println("***** You have selected to rate a movie *****");
                        System.out.print("What movie would you like to rate? (Title): ");
                        String title = sc.nextLine();
                        Optional<Movie> movieOpt = movieRepoJpa.findByTitle(title);
                        if (movieOpt.isEmpty()) {
                            System.out.println("Movie not found. Please try again.");
                            continue;
                        }

                        System.out.print("What would you like to rate the movie? (1-5): ");
                        String rateString = sc.nextLine();

                        try {
                            float rating = Float.parseFloat(rateString);

                            if (rating >= 1.0f && rating <= 5.0f) {
                                movieOpt.ifPresent(movie -> userRatingRepoJpa.rateMovie(user, movie, rating));
                                System.out.println("You succefully rated a movie!!");
                            } else {
                                System.out.println("Please enter a valid rating");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. You must enter a numeric value.");
                        }
                    }
                    // Movies that you rated
                    case 2 -> {
                        System.out.println("***** You want to get all the movies that you rated *****");

                        List<Movie> ratedMovies = userRatingRepoJpa.getMoviesRatedByUser(user);

                        if (ratedMovies.isEmpty()) {
                            System.out.println("You haven't rated any movies yet.");
                        } else {
                            System.out.println("Here are the movies you have rated: ");
                            for (Movie m : ratedMovies) {
                                System.out.println("- " + m.getTitle());
                            }
                        }
                    }
                    // Get rating for a movie
                    case 3 -> {
                        System.out.println("**** You want to get rating for a specific movie ****");
                        System.out.print("What movie title would you like to get your ratings from? ");
                        String title = sc.nextLine();

                        Optional<Movie> movieOpt = movieRepoJpa.findByTitle(title);
                        if (movieOpt.isEmpty()) {
                            System.out.println("Movie not found. Please try again.");
                            continue;
                        }
                        movieOpt.ifPresent(movie -> userRatingRepoJpa.getRatingForMovieByUser(user, movie));

                    }
                    // Print options again
                    case 4 -> printOptionsUserRating();
                    // Exit
                    case 0 -> {
                        System.out.println("Exiting program...");
                        running = false;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. You must enter a numeric value.");
            }
        }
    }

    public void printOptionsUserRating(){
        System.out.println("""
                            ======== USER RATING MENU ========
                            1. Rate a movie
                            2. Get movies that you rated
                            3. Get your rating for a movie
                            4. Show options again
                            0. Exit
                            """);
    }
    public void printOptionsMovie(){
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
                            0. Exit
                            00. Show Options again
                            """);
    }
    public void printOptionsUser(){
        System.out.println("""
                            ======== USER MENU ========
                            1. Get favorite movies
                            2. Add favorite movie
                            3. Remove favorite movie
                            4. Find users by username
                            0. Exit
                            00. Show Options again
                            """);
    }
    public void showMenuOptions(){
        System.out.println("""
                            ======== WHAT WOULD YOU LIKE TO DO? ========
                            1. Show User Menu
                            2. Show User Rating Menu
                            3. Show Movie Menu
                            Press any key to exit
                            """);
    }
}
