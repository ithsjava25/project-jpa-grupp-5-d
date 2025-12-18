package org.example;

import org.example.jpaimpl.MovieRepoJpa;
import org.example.jpaimpl.UserRatingRepoJpa;
import org.example.jpaimpl.UserRepoJpa;
import org.example.pojo.*;
import org.example.seed.*;

import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

import static org.example.DatabaseFiller.isDatabaseEmpty;

public class App {
    static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        JpaRunner.runInTranscation(em -> {
            if(isDatabaseEmpty(em)){
                DatabaseFiller filler = new DatabaseFiller(em);
                filler.seedAll();
            } else {
                System.out.println("Database already contain Movies. If other data is missing disable function and run filler.seedAll()");
            }
        });


        JpaRunner.runInTranscation(em -> {
            UserRepoJpa userRepo = new UserRepoJpa(em);
            UserRatingRepoJpa userRatingRepoJpa = new UserRatingRepoJpa(em);
            MovieRepoJpa movieRepoJpa = new MovieRepoJpa(em);

            String answer;

            do {
                System.out.println("**** Welcome to IMDB CLI Application ****");
                System.out.print("Please enter your username: ");
                String userName = sc.nextLine();
                System.out.print("Please enter your password: ");
                String password = sc.nextLine();

                Optional<User> optionalUser = userRepo.validateUser(userName, password);
                //Add check for Admin login
                if (optionalUser.isPresent()) {
                    System.out.println("**** You logged in succesfully ****");
                    User user = optionalUser.get();
                    System.out.println("Your userID is: " + user.getId());

                    if (user.getUserName().equals("Admin")){
                        CliAdminApp cliAdminApp = new CliAdminApp(em);
                        cliAdminApp.printOptions();
                    } else {
                        CliApp cliApp = new CliApp();
                        showMenueOptions();

                        String choice = sc.nextLine();
                        int number = Integer.parseInt(choice);

                        if (number == 1){
                            cliApp.optionsUser(userRepo, movieRepoJpa, user);
                        } else if (number == 2) {
                            cliApp.optionsUserRating(movieRepoJpa, userRatingRepoJpa, user);
                        } else if (number == 3) {
                            cliApp.optionsMovies(movieRepoJpa, user);
                        } else {
                            answer = "N";
                            continue;
                        }
                    }
                } else {
                    System.out.println("Either your username or password was incorrect.");
                }

                System.out.println("*********************************");
                System.out.println("Do you want to try again? (Y/N)");
                answer = sc.nextLine().toUpperCase();

                } while (answer.equals("Y"));
            });
    }

    public static void showMenueOptions(){
        System.out.println("""
                            ======== WHAT WOULD YOU LIKE TO DO? ========
                            1. Show User Menu
                            2. Show User Rating Menu
                            3. Show Movie Menu
                            Press any key to exit
                            """);
    }
}

