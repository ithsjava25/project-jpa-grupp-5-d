package org.example.pojo;

import jakarta.persistence.*;

@Entity
@Table(name = "user_rating")
public class UserRating {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "user_id")
        private User user;

        @ManyToOne
        @JoinColumn(name = "movie_id")
        private Movie movie;

        private float rating;

}
