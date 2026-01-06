
TODO:
## Formatering av rating i dba
## Application managed or container managed entity manager?
## Check tests (all)


IMDB - variant

One - To - Many
Regissör -> Filmer

Many - To - Many
Användare - Favoriter


Movie ----
- id (PK)
- Title
- Release date
- Length
- Ranking (avg User rating)
- Country (FK language)
- Language_id

Movie_genre
- Movie_id
- Genre_id

Movie_actor
- movie_id
- actor_id

Movie_director
- movie_id
- actor_id

User -----
- id (PK)
- firstName
- lastName
- Password

User_favorites
- User_id
- Movie_id

User_rating
- user_id
- movie_id
- rating

Actor -----
- id (PK)
- firstName
- lastName
- Country (FK country)

Director -----
- id (PK)
- firstName
- lastName
- Country (FK country)

Genre (enum???)
- id (PK)
- genreName

Country (enum???)
- country_id
- countryName

Language (enum???)
- language_id
- languageName



Funktionalitet
- User Account
  - Login
  - Rate movies / Favorite list


CLI Logic
-


Domändriven design
-Som användare ska jag kunna se mina favoriter
-användare ska kunna rate;a filmer
- Designa programmet utifrån målet/funktionen/kärn-funktionaliteten i programmet
Aggregates


/*
User:
1. Update password (userId, password)
2. Get favorite movies (UserId)
3. Add favorite movie (userId, movie)
4. Remove favorite movie (userId, movie)

        UserRating:
        1. Rate a movie (user, movie, rating)
        2. Get movies that you rated (user)
        4. Get your rating for a movie (user, movie)

        Movie:
        1. List all movies
        2. List movies by language (language)
        3. List movies by ranking (min rank, max rank)
        4. List movies by length (min length, max length)
        5. List movies by release date (from, to)
        6. List movies by actor (actor)
        7. List movies by director (director)
        8. List all movies by genre (NOT IMPLEMENTED)
        9. Find a movie by title (title)


        Admin usecase:
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


         */
