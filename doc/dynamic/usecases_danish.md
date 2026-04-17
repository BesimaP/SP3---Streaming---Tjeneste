# Usecases dansk udgave

## UC1: StartSystem
- Systemet starter og indlæser brugerdata fra fil 
- Systemet viser startmenuen med valg om at logge ind eller oprette bruger 
- Hvis ingen brugerdata findes, sendes brugeren til UC2: OpretBruger

OBS: Filen kan ikke læses → systemet viser en fejlbesked og fortsætter uden gemt data

## UC12: OpretBruger
- Programmet beder brugeren om at indtaste et brugernavn 
- Programmet beder brugeren om at indtaste et password 
- Programmet opretter en ny bruger og gemmer brugeren i filen

OBS: Brugernavnet findes allerede → systemet viser en fejlbesked og beder brugeren om at indtaste et andet brugernavn

## UC3: LogIndBruger
- Programmet beder brugeren om at indtaste brugernavn og password
- Programmet tjekker loginoplysningerne
- Hvis login er korrekt vises hovedmenuen

OBS: Forkert brugernavn eller password → systemet viser en fejlbesked og giver brugeren mulighed for at prøve igen — maks 3 forsøg

## UC4: SøgMedier
- Systemet viser hovedmenuen med valg om at søge via titel eller kategori
- Brugeren vælger en søgemulighed og systemet viser matchende film/serier
- Brugeren vælger en film/serie og får tre muligheder: afspil, gem eller tilbage
- Afspil: systemet viser "[titel] afspilles nu..." og tilføjer film/serie til sete medier
- Gem: film/serie tilføjes til gemtelisten og gemmes i filen

OBS: Ingen film/serie matcher søgningen → systemet viser en fejlbesked og returnerer til hovedmenuen

## UC5: AdministrerLister
- Systemet viser valg om at se sete eller gemte film/serier
- Brugeren vælger en liste og systemet viser film/serier 
- Brugeren vælger en film/serie og får mulighed for at afspille eller fjerne den 
- Afspil: systemet viser "[titel] afspilles nu..." og tilføjer film/serie til sete medier 
- Fjern: film/serie fjernes fra gemtelisten og filen opdateres

OBS: Listen er tom → systemet viser en fejlbesked og returnerer til hovedmenuen