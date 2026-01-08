package org.example.seed;

import jakarta.persistence.EntityManager;
import org.example.jpaimpl.UserRepoJpa;
import org.example.entity.User;
import java.util.HashMap;
import java.util.Map;

public class SeedUsers {

    private final EntityManager em;

    public SeedUsers(EntityManager em) {
        this.em = em;
    }

    public Map<String, User> seed() {
        UserRepoJpa userRepo = new UserRepoJpa(em);
        Map<String, User> users = new HashMap<>();

        users.put("Admin", userRepo.addUser("Admin", "Admin"));
        users.put("AliceSmith", userRepo.addUser("AliceSmith", "pass123"));
        users.put("BobJones", userRepo.addUser("BobJones", "qwerty"));
        users.put("CharlieBrown", userRepo.addUser("CharlieBrown", "abc123"));
        users.put("DavidMiller", userRepo.addUser("DavidMiller", "password1"));
        users.put("EvaJohnson", userRepo.addUser("EvaJohnson", "123456"));
        users.put("FrankWilson", userRepo.addUser("FrankWilson", "letmein"));
        users.put("GraceLee", userRepo.addUser("GraceLee", "mypassword"));
        users.put("HannahClark", userRepo.addUser("HannahClark", "passw0rd"));
        users.put("IanWalker", userRepo.addUser("IanWalker", "admin123"));
        users.put("JackHall", userRepo.addUser("JackHall", "welcome1"));

        users.put("KarenYoung", userRepo.addUser("KarenYoung", "hello123"));
        users.put("LeoKing", userRepo.addUser("LeoKing", "sunshine"));
        users.put("MiaScott", userRepo.addUser("MiaScott", "flower99"));
        users.put("NathanAdams", userRepo.addUser("NathanAdams", "moonlight"));
        users.put("OliviaBaker", userRepo.addUser("OliviaBaker", "star123"));
        users.put("PeterWright", userRepo.addUser("PeterWright", "dragon1"));
        users.put("QuinnHarris", userRepo.addUser("QuinnHarris", "blueSky"));
        users.put("RachelGreen", userRepo.addUser("RachelGreen", "green123"));
        users.put("SamRoberts", userRepo.addUser("SamRoberts", "robot2025"));
        users.put("TinaEvans", userRepo.addUser("TinaEvans", "tiger12"));

        users.put("UmaCarter", userRepo.addUser("UmaCarter", "queen99"));
        users.put("VictorMitchell", userRepo.addUser("VictorMitchell", "viking1"));
        users.put("WendyPerez", userRepo.addUser("WendyPerez", "wonder12"));
        users.put("XanderCooper", userRepo.addUser("XanderCooper", "xmen123"));
        users.put("YaraReed", userRepo.addUser("YaraReed", "yellow22"));
        users.put("ZacharyBell", userRepo.addUser("ZacharyBell", "zebra99"));
        users.put("AmberRussell", userRepo.addUser("AmberRussell", "apple12"));
        users.put("BrandonWard", userRepo.addUser("BrandonWard", "banana34"));
        users.put("CaitlynBennett", userRepo.addUser("CaitlynBennett", "catDog1"));
        users.put("DerekCook", userRepo.addUser("DerekCook", "delta99"));

        users.put("EllaFoster", userRepo.addUser("EllaFoster", "echo2025"));
        users.put("FelixGray", userRepo.addUser("FelixGray", "falcon1"));
        users.put("GabriellaHunt", userRepo.addUser("GabriellaHunt", "galaxy9"));
        users.put("HenryJames", userRepo.addUser("HenryJames", "harbor12"));
        users.put("IslaKnight", userRepo.addUser("IslaKnight", "icecream"));
        users.put("JasonLong", userRepo.addUser("JasonLong", "joker99"));
        users.put("KylieMorgan", userRepo.addUser("KylieMorgan", "kangaroo"));
        users.put("LiamNelson", userRepo.addUser("LiamNelson", "lion123"));
        users.put("MeganOwens", userRepo.addUser("MeganOwens", "mountain"));
        users.put("NoahPerry", userRepo.addUser("NoahPerry", "nova99"));

        users.put("OlgaQuinn", userRepo.addUser("OlgaQuinn", "olive123"));
        users.put("PaulRoss", userRepo.addUser("PaulRoss", "panda1"));
        users.put("QuincySims", userRepo.addUser("QuincySims", "queenBee"));
        users.put("RileyTurner", userRepo.addUser("RileyTurner", "river12"));
        users.put("SophiaUnderwood", userRepo.addUser("SophiaUnderwood", "sunset"));
        users.put("ThomasVega", userRepo.addUser("ThomasVega", "tornado"));
        users.put("UlyssesWhite", userRepo.addUser("UlyssesWhite", "unicorn"));
        users.put("VanessaXavier", userRepo.addUser("VanessaXavier", "volcano"));
        users.put("WilliamYoung", userRepo.addUser("WilliamYoung", "whale99"));
        users.put("XimenaZimmer", userRepo.addUser("XimenaZimmer", "xylophone"));

        return users;
    }

    /*
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

     */
}
