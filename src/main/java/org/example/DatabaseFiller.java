package org.example;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.example.enums.Country;
import org.example.enums.Language;
import org.example.jpaimpl.*;
import org.example.pojo.Actor;
import org.example.pojo.Movie;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class DatabaseFiller {

    private final EntityManager em;

    public DatabaseFiller(EntityManager em) {
        this.em = em;
    }




}
