# Use cases on English

## UC1: StartSystem
The system starts and loads user data from file. 
The system then displays the start menu with options to login or create a new user. 
If no user data is found, the user is sent to UC2: RegisterUser.

Rainy day:
- File cannot be read: The system displays an error message and continues without saved data.

## UC2: RegisterUser
The system prompts the user to enter a username. 
The user types an input, followed by enter. 
The system prompts the user to enter a password. 
The user types an input, followed by enter. The system creates a new user and saves it to file.

Rainy day:
- Username already exists: The system displays an error message and prompts the user to enter a different username.

## UC3: LoginUser
The system prompts the user to enter a username and password. 
The user types an input, followed by enter. 
The system validates the login information. 
If login is correct the main menu is displayed.

Rainy day:
- Wrong username or password: The system displays an error message and allows the user to try again — max 3 attempts.

## UC4: BrowseMedia
The system displays the main menu with options to search by title or category. 
The user selects a search option and types an input, followed by enter. 
The system displays matching movies/series. 
The user selects a movie/series and is given three options: play, save or back.

If the user plays: the system displays "[title] is now playing..." and adds the movie/series to watched.
If the user saves: the movie/series is added to the saved list and saved to file.

Rainy day:
- No movie/series matches the search: The system displays an error message and returns to the main menu.

## UC5: ManageLists
The system displays options to view watched or saved movies/series. 
The user selects a list and the system displays the movies/series. 
The user selects a movie/series and is given two options: play or remove.

If the user plays: the system displays "[title] is now playing..." and adds the movie/series to watched.
If the user removes: the movie/series is removed from the saved list and the file is updated.

Rainy day:
- List is empty: The system displays an error message and returns to the main menu.