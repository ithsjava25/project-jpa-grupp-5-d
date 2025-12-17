package org.example;

import jakarta.persistence.EntityManager;
import org.example.jpaimpl.UserRepoJpa;

public class SeedUsers {

    private final EntityManager em;

    public SeedUsers(EntityManager em) {
        this.em = em;
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
