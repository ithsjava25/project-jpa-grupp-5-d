package org.example;
import org.example.enums.Country;
import org.example.enums.Language;
import org.example.jpaimpl.*;
import org.example.entity.Genre;
import org.example.entity.User;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CliAdminApp {

    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String RESET = "\u001B[0m";

    public void adminOptions(Scanner sc,
                             User user) {
        boolean running = true;

        System.out.println("**** Welcome to the Admin Menu ****");

        while (running) {

            printOptions();

            String input = sc.nextLine();
            int choice;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a numeric value");
                continue;
            }

            switch (choice) {

                // ADD A NEW USER
                case 1 -> {
                    System.out.println("Enter a Username:");
                    String userName = sc.nextLine().trim();

                    System.out.println("Enter a password for the user:");
                    String password = sc.nextLine().trim();

                    if (userName.isEmpty() || password.isEmpty()) {
                        System.out.println(RED + "Username and password cannot be empty." + RESET);
                        break; // stop this case early
                    }
                    JpaRunner.runInTransaction(em -> {
                        UserRepoJpa userRepo = new UserRepoJpa(em);
                        userRepo.addUser(userName, password);
                        System.out.println(GREEN + "New user '" + userName + "' added!" + RESET);
                    });
                }
                // DELETE A USER
                case 2 -> {
                    System.out.print("Enter user (username) to delete: ");
                    String userName = sc.nextLine();

                    JpaRunner.runInTransaction(em -> {
                        UserRepoJpa userRepo = new UserRepoJpa(em);
                        var userOpt = userRepo.findByUserName(userName);
                        if (userOpt.isEmpty()){
                            System.out.println(RED + "No username found with username: " + userName + RESET);
                        } else {
                            long userId = userOpt.get().getId();
                            userRepo.deleteUser(userId);
                            System.out.println(GREEN + "User '" + userName + "' successfully deleted!" + RESET);
                        }
                    });
                }
                // ADD A NEW MOVIE
                case 3 -> {
                    System.out.println("Enter the title of the movie:");
                    String movie = sc.nextLine();

                    // --- Date ---
                    System.out.println("Enter release Date (YYYY-MM-DD or YYYYMMDD): ");
                    String date = sc.nextLine().trim();
                    LocalDate parsedDate;

                    try {
                        if (date.matches("\\d{8}")) {
                            date = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6, 8);
                        }
                        parsedDate = LocalDate.parse(date);
                    } catch (DateTimeParseException e) {
                        System.out.println(RED + "Invalid date format. " + e.getMessage() + ". Press Enter to continue." + RESET);
                        continue;
                    }

                    // --- Length ---
                    int length = -1;
                    try {
                        System.out.println("Enter the length in minutes:");
                        length = Integer.parseInt(sc.nextLine().trim());
                        if (length < 0) {
                            System.out.println(RED + "Length must be a positive number." + RESET);
                            System.exit(0);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(RED + "Invalid length. Please enter a number." + RESET);
                        System.exit(0);
                    }

                    // --- Country ---
                    Country newCountry;
                    try {
                        System.out.println("Enter the country:");
                        newCountry = Country.valueOf(sc.nextLine().trim().toUpperCase());
                    } catch (IllegalArgumentException e) {
                        System.out.println(RED + "Invalid country. Please enter one of: " + Arrays.toString(Country.values()) + RESET);
                        return;
                    }

                    // --- Language ---
                    Language newLanguage;
                    try {
                        System.out.println("Enter the language:");
                        newLanguage = Language.valueOf(sc.nextLine().trim().toUpperCase());
                    } catch (IllegalArgumentException e) {
                        System.out.println(RED + "Invalid language. Please enter one of: " + Arrays.toString(Language.values()) + RESET);
                        return;
                    }

                    // --- Transaction ---
                    int finalNewLength = length;
                    JpaRunner.runInTransaction(em -> {
                        MovieRepoJpa movieRepo = new MovieRepoJpa(em);
                        movieRepo.addMovie(movie, parsedDate, finalNewLength, newCountry, newLanguage);
                        System.out.println(GREEN + "Movie added!" + RESET);
                    });
                }
                // DELETE A MOVIE
                case 4 -> {
                    System.out.print("Enter the title of a movie that you would like to delete: ");
                    String movieTitle = sc.nextLine();

                    JpaRunner.runInTransaction(em -> {
                        MovieRepoJpa movieRepo = new MovieRepoJpa(em);
                        var movieOpt = movieRepo.findByTitle(movieTitle);

                        if (movieOpt.isEmpty()) {
                            System.out.println(RED + "No movie found with the title: " + movieTitle + RESET);
                        } else {
                            long movieId = movieOpt.get().getId();
                            movieRepo.deleteMovie(movieId);
                            System.out.println(GREEN + "Movie: '" + movieTitle + "' was successfully deleted" + RESET);
                        }
                    });

                }
                // ADD AN ACTOR TO A MOVIE
                case 5 -> {
                    System.out.println("Enter movie title:");
                    String movieTitle = sc.nextLine();

                    System.out.println("Enter actor name:");
                    String actorName = sc.nextLine();

                    JpaRunner.runInTransaction(em -> {
                        MovieRepoJpa movieRepo = new MovieRepoJpa(em);
                        ActorRepoJpa actorRepo = new ActorRepoJpa(em);

                        var actorOpt = actorRepo.findByName(actorName);
                        var movieOpt = movieRepo.findByTitle(movieTitle);
                        if (actorOpt.isEmpty()) {
                            System.out.println(RED + "No actor found with name: " + actorName + RESET);
                        } else if (movieOpt.isEmpty()) {
                            System.out.println(RED + "No movie found with that title: " + movieTitle + RESET);
                        } else {
                            movieRepo.addActors(movieTitle, List.of(actorOpt.get()));
                            System.out.println(GREEN + "Actor added to movie!" + RESET);
                        }
                    });
                }
                // ADD A DIRECTOR TO A MOVIE
                case 6 -> {
                    System.out.println("Enter movie title:");
                    String movieTitle = sc.nextLine();

                    System.out.println("Enter director name:");
                    String directorName = sc.nextLine();

                    JpaRunner.runInTransaction(em -> {
                        MovieRepoJpa movieRepo = new MovieRepoJpa(em);
                        DirectorRepoJpa directorRepo = new DirectorRepoJpa(em);

                        var directorOpt = directorRepo.findByName(directorName);
                        var movieOpt = movieRepo.findByTitle(movieTitle);
                        if (directorOpt.isEmpty()) {
                            System.out.println(RED + "No actor found with name: " + directorName + RESET);
                        } else if (movieOpt.isEmpty()) {
                            System.out.println(RED + "No movie found with that title: " + movieTitle + RESET);
                        } else {
                            movieRepo.setDirector(movieTitle, directorOpt.get());
                            System.out.println(GREEN + "Director '" + directorName + "' set for movie '" + movieTitle + "'!" + RESET);
                        }
                    });
                }
                // ADD A NEW GENRE
                case 7 -> {
                    System.out.println("Enter genre name:");
                    String genre = sc.nextLine().trim();

                    if (genre.isEmpty()) {
                        System.out.println(RED + "Genre name cannot be empty." + RESET);
                        break;
                    }

                    JpaRunner.runInTransaction(em -> {
                        GenreRepoJpa genreRepo = new GenreRepoJpa(em);
                        if (genreRepo.addGenre(genre)) {
                            System.out.println(GREEN + "Genre '" + genre + "' added!" + RESET);
                        } else {
                            System.out.println(RED + "The genre: '" + genre + "' already exist." + RESET);
                        }
                    });
                }
                // DELETE A GENRE
                case 8 -> {
                    System.out.print("Enter genre name to delete: ");
                    String genreName = sc.nextLine();

                    JpaRunner.runInTransaction(em -> {
                        GenreRepoJpa genreRepo = new GenreRepoJpa(em);
                        Optional<Genre> genreOpt = genreRepo.findByName(genreName);

                        if (genreOpt.isEmpty()) {
                            System.out.println(RED + "No genre found with name: " + genreName + RESET);
                            return; // exit the transaction block
                        }

                        Genre genre = genreOpt.get();
                        long id = genre.getGenreID();
                        if (genreRepo.deleteGenre(id)) {
                            System.out.println(GREEN + "Genre with ID " + id + " is deleted." + RESET);
                        } else {
                            System.out.println(RED + "Something went wrong with delete method." + RESET);
                        }
                    });
                }
                // ADD A NEW ACTOR
                case 9 -> {
                    System.out.println("Enter actor name:");
                    String actorName = sc.nextLine().trim();

                    // Kolla så att namn inte innehåller ottilåtna tecken.
                    if (!actorName.matches("^[A-Za-z]+( [A-Za-z]+){1,}$")) {
                        System.out.println(RED + "Invalid name format. Please enter (at least) first and last name." + RESET);
                        break;
                    }

                    System.out.println("Enter country:");
                    String countryInput = sc.nextLine().trim().toUpperCase();

                    Country country;
                    try {
                        country = Country.valueOf(countryInput);
                    } catch (IllegalArgumentException e) {
                        System.out.println(RED + "Invalid country. Please enter one of: " + Arrays.toString(Country.values()) + RESET);
                        break; // stop this case early
                    }

                    JpaRunner.runInTransaction(em -> {
                        ActorRepoJpa actorRepo = new ActorRepoJpa(em);

                        var existingActor = actorRepo.findByName(actorName);

                        if (existingActor.isPresent()) {
                            System.out.println("Actor with name: " + actorName + " already exists.");
                            System.out.println("Do you really want to add this actor anyway? (Y/N)");
                            String answer = sc.nextLine().trim().toLowerCase();

                            if (!answer.equals("y")) {
                                System.out.println(RED + "The actor was not added, returning to menu." + RESET);
                                return;
                            }
                        }

                        actorRepo.addActor(actorName, country);
                        System.out.println(GREEN + "Actor '" + actorName + "' from " + country + " added!" + RESET);
                    });
                }
                // DELETE AN ACTOR
                case 10 -> {
                    System.out.print("Enter actor name to delete: ");
                    String actorName = sc.nextLine().trim();

                    if (actorName.isEmpty()) {
                        System.out.println(RED + "Actor name cannot be empty." + RESET);
                        break; // stop this case early
                    }

                    JpaRunner.runInTransaction(em -> {
                        ActorRepoJpa actorRepo = new ActorRepoJpa(em);
                        boolean deleted = actorRepo.deleteByName(actorName);

                        if (deleted) {
                            System.out.println(GREEN + "Actor '" + actorName + "' successfully deleted!" + RESET);
                        } else {
                            System.out.println(RED + "No actor found with name: " + actorName + RESET);
                        }
                    });
                }
                // ADD A NEW DIRECTOR
                case 11 -> {
                    System.out.println("Enter director name:");
                    String directorName = sc.nextLine().trim();

                    // Kolla så att namn inte innehåller ottilåtna tecken.
                    if (!directorName.matches("^[A-Za-z]+( [A-Za-z]+){1,}$")) {
                        System.out.println(RED + "Invalid name format. Please enter (at least) first and last name." + RESET);
                        break;
                    }

                    System.out.println("Enter country:");
                    String countryInput = sc.nextLine().trim().toUpperCase();

                    Country country;
                    try {
                        country = Country.valueOf(countryInput);
                    } catch (IllegalArgumentException e) {
                        System.out.println(RED + "Invalid country. Please enter one of: " + Arrays.toString(Country.values()) + RESET);
                        break; // stop this case early
                    }

                    JpaRunner.runInTransaction(em -> {
                        DirectorRepoJpa directorRepo = new DirectorRepoJpa(em);

                        var existingDirector = directorRepo.findByName(directorName);

                        if (existingDirector.isPresent()) {
                            System.out.println("Director with name : " + directorName + " already exists!");
                            System.out.println("Do you want to add this director anyways? (Y/N)");
                            String answer = sc.nextLine().trim().toLowerCase();

                            if (!answer.equals("y")) {
                                System.out.println(RED + "The director was not added, now returning to menu." + RESET);
                                return;
                            }
                        }

                        directorRepo.addDirector(directorName, country);
                        System.out.println(GREEN + "Director '" + directorName + "' from " + country + " added!" + RESET);
                    });
                }
                // DELETE A DIRECTOR
                case 12 -> {
                    System.out.print("Enter director name to delete: ");
                    String directorName = sc.nextLine().trim();

                    if (directorName.isEmpty()) {
                        System.out.println(RED + "Director name cannot be empty." + RESET);
                        break; // stop this case early
                    }

                    JpaRunner.runInTransaction(em -> {
                        DirectorRepoJpa directorRepo = new DirectorRepoJpa(em);
                        boolean deleted = directorRepo.deleteByName(directorName);

                        if (deleted) {
                            System.out.println(GREEN + "Director '" + directorName + "' successfully deleted!" + RESET);
                        } else {
                            System.out.println(RED + "No director found with name: " + directorName + RESET);
                        }
                    });
                }
                // FIND USERS BY USERNAME
                case 13 -> {
                    System.out.print("Enter username: ");
                    String userName = sc.nextLine().trim();

                    if (userName.isEmpty()) {
                        System.out.println(RED + "Username cannot be empty." + RESET);
                        break; // stop this case early
                    }

                    JpaRunner.runInTransaction(em -> {
                        UserRepoJpa userRepo = new UserRepoJpa(em);
                        var userOpt = userRepo.findByUserName(userName);

                        if (userOpt.isEmpty()) {
                            System.out.println(RED + "No user found with username: " + userName + RESET);
                        } else {
                            System.out.println("Found user: " + userOpt.get().getUserName());
                        }
                    });
                }
                // EXIT
                case 0 -> {
                    System.out.println("Exiting admin menu...");
                    running = false;
                }
                default -> System.out.println("Invalid option");
            }
        }
    }

    public void printOptions(){
        System.out.println("""
                =============ADMIN MENU=============
                1. Add new user (userName, password)
                2. Delete user (userName)
                3. Add a new movie (title, date YYYY-MM-DD, length in minutes, country, language)
                4. Delete movie (title)
                5. Add an actor to a movie (actor name & movie title)
                6. Add a director to a movie (movieId, directorId)
                7. Add a new Genre (genreName)
                8. Delete a genre (genreId)
                9. Add a new actor (actorname, country)
                10. Delete an actor (actor name)
                11. Add a new Director (directorName, country)
                12. Delete a director (director name)
                13. Find users by username (userName)
                0. Exit
                """);
    }
}
