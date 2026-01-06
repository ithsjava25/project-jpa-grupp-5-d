
TODO:
## Formatering av rating i dba
## Application managed or container managed entity manager?
## Check tests (all)

## CLI app - Add note (Press enter to show menue again), on all options, hantering för retards??
## Rate a movie - No response after, only message "Movie not found. Please try again"
## Get movies that you rated - also get rating for the movie not only the title??
## Rate a movie - Endast kunna skriva in heltal

## Select movie by language - Throws error if not correct input
## Select movie by language - Takes user input on new row -> print() not println()
## Select movie by ranking - Add user note (ranking 1 -> 5, 0 does not exist)
## Select movie by ranking - error message on invalid input??
## Select movie by releaseDate - formatting on date does not work? Handle both YYYYMMDD and YYYY-MM-DD
## Select movie by genre - Breaks down if incorrect input. Typecast error to runtime?
## Find movie by title - Format the output. Actors is displayed on new rows. Use (Actor 1, Actor 2, etc...) instead. Same with genre
## Menu option 3 -> Shows 2 menues when using option 10 (show menu again)

## ADMIN MENU
## Add new user - Prints menue 2 times after new user is added
## delete user (userId) - searches for username, change menu option name
## Add new movie - exit the program if invalid input
## Add a new actor - if actor name already exist doublecheck with user if you really want to add the actor
        ## Says new actor is added, but its not. Some doublecheck?
## Add a new director - if actor name already exist doublecheck with user if you really want to add the actor
        ## same as above?
## Delete a movie by ID - actually wants title, change name of menu option
## Add an actor to a movie (movie title and actor name) - inconsistent menu name
## Delete a genre - Goes by ID while all other delete-methods search by name/title
## Add new actor - Possible to add actor with name "123", only letters should be allowed
## Delete an actor (id) - delets by name, change menu name
## Add / Delete director - same as actor functions
## Add a new genre - Add alrdy existing does not work, but it consume the auto-increment ID.

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
