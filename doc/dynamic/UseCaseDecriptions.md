## UC1 – RegistrerUser

**Aktør:** Bruger

**Forudsætning:** Brugeren har ikke en konto

**Hovedforløb:**
1. Bruger vælger "Opret bruger" i startmenuen
2. System beder brugeren om brugernavn og adgangskode
3. Bruger indtaster oplysninger og bekræfter
4. System gemer brugeren i en fil
5. System viser startmenuen igen

**Alternativt forløb:**

1. Brugernavn er allerede i filen
2. System viser fejlbesked og brugeren kan prøve igen

## UC2 – Login

**Aktør:** Bruger

**Forudsætning:** Brugeren har en konto

**Hovedforløb:**
1. Bruger vælger "Log ind" i startmenuen
2. System beder brugeren om brugernavn og adgangskode
3. Bruger indtaster oplysningerne
4. System tjekker brugers data i filen
5. Bruger sendes videre til hovedmenuen

**Alternativt forløb:**

1. Forkert brugernavn eller adgangskode
2. System viser fejlbesked og brugeren kan prøve igen

## UC3 – Homepage

**Aktør:** Bruger

**Forudsætning:** Brugeren er logget ind

**Hovedforløb:**
1. Bruger får vist menu med valgene: Søg efter film, Afspil film, Se Gemte film
2. Bruger indtaster ønsket valg
4. System sender bruger videre til ønsket valg

**Alternativt forløb:**
 ?

## UC4 – SearchMedia

**Aktør:** Bruger

**Forudsætning:** Brugeren er logget ind

**Hovedforløb:**
1. Bruger vælger "søg efter medie" i hovedmenuen
2. Systemet beder om søgetekst (title)
3. Bruger indtaster søgetekst
4. System viser matchende film
5. Bruger kan vælge et medie fra listen
6. System viser filmmenu (afspil / gem)

**Alternativt forløb:**

1. Intet medie matcher søgning
2. System viser besked om ingen resultater

## UC5 – SearchCategories

**Aktør:** Bruger

**Forudsætning:** Brugeren er logget ind

**Hovedforløb:**
1. Bruger vælger "søg efter kategori" i hovedmenuen
2. Systemet viser liste over kategorier
3. Bruger vælger en kategori
4. System viser liste over film i kategorien
5. Bruger kan vælge en film fra listen
6. System viser filmmenu (afspil / gem)

**Alternativt forløb:**
?

## UC6 – WatchedMedia

**Aktør:** Bruger

**Forudsætning:** Brugeren er logget ind og har set et medie

**Hovedforløb:**
1. Bruger vælger "Seete film" i hovedmenuen
2. Systemet viser liste over seete medier
3. Bruger kan vælge en medie fra listen
4. System viser filmmenu (afspil / gem)

**Alternativt forløb:**

1. Brugeren har ikke set nogen film endnu
2. System viser ikke seete film på hovedmenuen

## UC7 – SavedMedia

**Aktør:** Bruger

**Forudsætning:** Brugeren har valgt et medie

**Hovedforløb:**
1. Bruger vælger "Gemt til senere" i filmmenuen
2. Systemet tilføjer mediet til brugerens gemte liste
3. System bekræfter med en besked
4. System vender tilbage til filmmenuen

**Alternativt forløb:**

1. Medie er allerede på listen. 
2. System viser besked, der gemmes ikke en kopi

## UC8 – PlayMedia

**Aktør:** Bruger

**Forudsætning:** Brugeren har valgt et medie

**Hovedforløb:**
1. Bruger vælger "Afspil" i filmmenuen
2. Systemet viser "Title afspilles nu..."
3. Systemet tilføjer filmen/serien til brugerens liste over seete film
4. System vender tilbage til hovedmenuen

**Alternativt forløb:**
?



