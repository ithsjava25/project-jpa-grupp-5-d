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
