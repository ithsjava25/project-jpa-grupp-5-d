package org.example;
import org.example.enums.Country;
import org.example.enums.Language;
import org.example.jpaimpl.*;
import org.example.pojo.Genre;
import org.example.pojo.User;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CliAdminApp {

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
                        System.out.println("Username and password cannot be empty.");
                        break; // stop this case early
                    }
                    JpaRunner.runInTransaction(em -> {
                        UserRepoJpa userRepo = new UserRepoJpa(em);
                        userRepo.addUser(userName, password);
                        System.out.println("New user '" + userName + "' added!");
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
                            System.out.println("No username found with username: " + userName);
                        } else {
                            long userId = userOpt.get().getId();
                            userRepo.deleteUser(userId);
                            System.out.println("User '" + userName + "' successfully deleted!");
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
                        System.out.println("Invalid date format. " + e.getMessage() + ". Press Enter to continue.");
                        continue;
                    }

                    // --- Length ---
                    int length = -1;
                    try {
                        System.out.println("Enter the length in minutes:");
                        length = Integer.parseInt(sc.nextLine().trim());
                        if (length < 0) {
                            System.out.println("Length must be a positive number.");
                            System.exit(0);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid length. Please enter a number.");
                        System.exit(0);
                    }

                    // --- Country ---
                    Country newCountry;
                    try {
                        System.out.println("Enter the country:");
                        newCountry = Country.valueOf(sc.nextLine().trim().toUpperCase());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid country. Please enter one of: " + Arrays.toString(Country.values()));
                        return;
                    }

                    // --- Language ---
                    Language newLanguage;
                    try {
                        System.out.println("Enter the language:");
                        newLanguage = Language.valueOf(sc.nextLine().trim().toUpperCase());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid language. Please enter one of: " + Arrays.toString(Language.values()));
                        return;
                    }

                    // --- Transaction ---
                    int finalNewLength = length;
                    JpaRunner.runInTransaction(em -> {
                        MovieRepoJpa movieRepo = new MovieRepoJpa(em);
                        movieRepo.addMovie(movie, parsedDate, finalNewLength, newCountry, newLanguage);
                        System.out.println("Movie added!");
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
                            System.out.println("No movie found with the title: " + movieTitle);
                        } else {
                            long movieId = movieOpt.get().getId();
                            movieRepo.deleteMovie(movieId);
                            System.out.println("Movie: '" + movieTitle + "' was successfully deleted");
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
                            System.out.println("No actor found with name: " + actorName);
                        } else if (movieOpt.isEmpty()) {
                            System.out.println("No movie found with that title: " + movieTitle);
                        } else {
                            movieRepo.addActors(movieTitle, List.of(actorOpt.get()));
                            System.out.println("Actor added to movie!");
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
                            System.out.println("No actor found with name: " + directorName);
                        } else if (movieOpt.isEmpty()) {
                            System.out.println("No movie found with that title: " + movieTitle);
                        } else {
                            movieRepo.setDirector(movieTitle, directorOpt.get());
                            System.out.println("Director '" + directorName + "' set for movie '" + movieTitle + "'!");
                        }
                    });
                }
                // ADD A NEW GENRE
                case 7 -> {
                    System.out.println("Enter genre name:");
                    String genre = sc.nextLine().trim();

                    if (genre.isEmpty()) {
                        System.out.println("Genre name cannot be empty.");
                        break;
                    }

                    JpaRunner.runInTransaction(em -> {
                        GenreRepoJpa genreRepo = new GenreRepoJpa(em);
                        genreRepo.addGenre(genre);
                        System.out.println("Genre '" + genre + "' added!");
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
                            System.out.println("No genre found with name: " + genreName);
                            return; // exit the transaction block
                        }

                        Genre genre = genreOpt.get();
                        long id = genre.getGenreID();
                        if (genreRepo.deleteGenre(id)) {
                            System.out.println("Genre with ID " + id + " is deleted.");
                        } else {
                            System.out.println("Something went wrong with delete method.");
                        }
                    });
                }
                // ADD A NEW ACTOR
                case 9 -> {
                    System.out.println("Enter actor name:");
                    String actorName = sc.nextLine().trim();

                    // Kolla så att namn inte innehåller ottilåtna tecken.
                    if (!actorName.matches("^[A-Za-z]+( [A-Za-z]+){1,}$")) {
                        System.out.println("Invalid name format. Please enter (at least) first and last name.");
                        break;
                    }

                    System.out.println("Enter country:");
                    String countryInput = sc.nextLine().trim().toUpperCase();

                    Country country;
                    try {
                        country = Country.valueOf(countryInput);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid country. Please enter one of: " + Arrays.toString(Country.values()));
                        break; // stop this case early
                    }

                    JpaRunner.runInTransaction(em -> {
                        ActorRepoJpa actorRepo = new ActorRepoJpa(em);
                        actorRepo.addActor(actorName, country);
                        System.out.println("Actor '" + actorName + "' from " + country + " added!");
                    });
                }
                // DELETE AN ACTOR
                case 10 -> {
                    System.out.print("Enter actor name to delete: ");
                    String actorName = sc.nextLine().trim();

                    if (actorName.isEmpty()) {
                        System.out.println("Actor name cannot be empty.");
                        break; // stop this case early
                    }

                    JpaRunner.runInTransaction(em -> {
                        ActorRepoJpa actorRepo = new ActorRepoJpa(em);
                        boolean deleted = actorRepo.deleteByName(actorName);

                        if (deleted) {
                            System.out.println("Actor '" + actorName + "' successfully deleted!");
                        } else {
                            System.out.println("No actor found with name: " + actorName);
                        }
                    });
                }
                // ADD A NEW DIRECTOR
                case 11 -> {
                    System.out.println("Enter director name:");
                    String directorName = sc.nextLine().trim();

                    // Kolla så att namn inte innehåller ottilåtna tecken.
                    if (!directorName.matches("^[A-Za-z]+( [A-Za-z]+){1,}$")) {
                        System.out.println("Invalid name format. Please enter (at least) first and last name.");
                        break;
                    }

                    System.out.println("Enter country:");
                    String countryInput = sc.nextLine().trim().toUpperCase();

                    Country country;
                    try {
                        country = Country.valueOf(countryInput);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid country. Please enter one of: " + Arrays.toString(Country.values()));
                        break; // stop this case early
                    }

                    JpaRunner.runInTransaction(em -> {
                        DirectorRepoJpa directorRepo = new DirectorRepoJpa(em);
                        directorRepo.addDirector(directorName, country);
                        System.out.println("Director '" + directorName + "' from " + country + " added!");
                    });
                }
                // DELETE A DIRECTOR
                case 12 -> {
                    System.out.print("Enter director name to delete: ");
                    String directorName = sc.nextLine().trim();

                    if (directorName.isEmpty()) {
                        System.out.println("Director name cannot be empty.");
                        break; // stop this case early
                    }

                    JpaRunner.runInTransaction(em -> {
                        DirectorRepoJpa directorRepo = new DirectorRepoJpa(em);
                        boolean deleted = directorRepo.deleteByName(directorName);

                        if (deleted) {
                            System.out.println("Director '" + directorName + "' successfully deleted!");
                        } else {
                            System.out.println("No director found with name: " + directorName);
                        }
                    });
                }
                // FIND USERS BY USERNAME
                case 13 -> {
                    System.out.print("Enter username: ");
                    String userName = sc.nextLine().trim();

                    if (userName.isEmpty()) {
                        System.out.println("Username cannot be empty.");
                        break; // stop this case early
                    }

                    JpaRunner.runInTransaction(em -> {
                        UserRepoJpa userRepo = new UserRepoJpa(em);
                        var userOpt = userRepo.findByUserName(userName);

                        if (userOpt.isEmpty()) {
                            System.out.println("No user found with username: " + userName);
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
