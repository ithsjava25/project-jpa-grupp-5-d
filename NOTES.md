IMDB - variant

One - To - Many
Regissör -> Filmer

Many - To - Many
Användare - Favoriter


Movie
- id (PK)
- Title
- Director_ID (FK) *****
- Actor_ID (FK) ******
- Release date
- Length
- Ranking (avg User rating)
- Genre (FK genre)
- Country (FK country)
- Language (FK country)

Movie_genre
- Movie_id
- Genre_id

Movie_actor
- movie_id
- director_id
- actor_id

User
- id (PK)
- firstName
- lastName
- Password
- Favorites
- Rating

Actor
- id (PK)
- firstName
- lastName
- Country (FK country)
- Language (FK country)

Actor_language
- actor_id
- language_id

Director
- id (PK)
- firstName
- lastName
- Country (FK country)
- Language (FK country)

Director_language
- Director_id
- Language_id

Genre
- id (PK)
- Genre

Country
- country_id
- Country
- language_id
- Language



Funktionalitet
- User Account
  - Login
  - Rate movies / Favorite list


CLI Logic
-


