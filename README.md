# SP3-Streaming-Tjeneste

Entiteter:
- Media (Superklasse)
- Movie
- Series
- Season
- Episode
- User
- Admin (valgfri)

Enum:
Categori(crime,war,drama,family,romance,sci-fi) måske ENUM? Fordi det bare er en værdi

Attributter:
Media:
- title
- releaseYear
- rating
- category

User:
- username
- password
- watchedMovies (liste)
- savedMovies (liste)

Series:
- seasons (liste af Season)

Season:
- episodes (liste af Episode)

Episode:
- title
- episodeNumber (for at kunne identificere episoder i en sæson) (står ikke i opgaven)
- duration (for at vise længden på en episode) (står ikke i opgaven)

Systemklasser:
- StreamingService (logik)
- TextIU (Brugerinterface)
- Util.FileIO (Datahåndtering)
