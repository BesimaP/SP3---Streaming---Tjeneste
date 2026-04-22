import Util.TextUI;
import java.util.ArrayList;

    // MediaController håndterer al logik omkring søgning og afspilning af medier
    // samt visning og styring af brugerens lister (UC4 og UC5)
    public class MediaController {
        private StreamingService service;
        private TextUI ui;

        // ── Konstruktør ───────────────────────────────────
        // Henter UI-objektet fra StreamingService så vi kan vise beskeder
        public MediaController(StreamingService service) {
            this.service = service;
            this.ui = service.getUi();
        }

        // ═══════════════ UC4: BrowseMedia ══════════════════════════════════

        // Søger efter film/serier der indeholder søgeordet i titlen
        public void searchTitle(User user) {
            String title = ui.promptText("Search title: ");

            // Saml alle resultater der matcher søgeordet
            ArrayList<Media> results = new ArrayList<>();
            for (Media m : service.getMediaList()) {
                if (m.getTitle().toLowerCase().contains(title.toLowerCase())) {
                    results.add(m);
                }
            }

            // Ingen resultater fundet
            if (results.isEmpty()) {
                ui.displayMsg("No results found for: " + title);
                return;
            }

            // Vis nummererede resultater
            ui.displayMsg("\nResults:");
            for (int i = 0; i < results.size(); i++) {
                Media m = results.get(i);
                String type = (m instanceof Series) ? "[Series]" : "[Movie]";
                ui.displayMsg((i + 1) + ". " + type + " " + m.getTitle() + " (" + m.getReleaseYear() + ") - Rating: " + m.getRating());
            }

            // Lad brugeren vælge et resultat
            int mediaChoice = ui.promptNumeric("Choose a title (number): ");
            if (mediaChoice < 1 || mediaChoice > results.size()) {
                ui.displayMsg("Invalid choice");
                return;
            }

            // Lad brugeren afspille eller gemme det valgte medie
            playOrSave(results.get(mediaChoice - 1), user);
        }

        // Søger efter film/serier inden for en valgt kategori
        public void searchCategory(User user) {
            // Vis alle kategorier
            ui.displayMsg("\nCategories:");
            ui.displayMsg("1. Crime");
            ui.displayMsg("2. War");
            ui.displayMsg("3. Drama");
            ui.displayMsg("4. Family");
            ui.displayMsg("5. Romance");
            ui.displayMsg("6. Sci-Fi");
            ui.displayMsg("7. Mystery");

            int categoryChoice = ui.promptNumeric("Choose a category (number): ");

            // Oversæt valget til et Category-objekt
            Category chosen;
            switch (categoryChoice) {
                case 1: chosen = Category.Crime; break;
                case 2: chosen = Category.War; break;
                case 3: chosen = Category.Drama; break;
                case 4: chosen = Category.Family; break;
                case 5: chosen = Category.Romance; break;
                case 6: chosen = Category.Sciencefiction; break;
                case 7: chosen = Category.Mystery; break;
                default:
                    ui.displayMsg("Invalid choice");
                    return;
            }

            // Saml alle resultater med den valgte kategori
            ArrayList<Media> results = new ArrayList<>();
            for (Media m : service.getMediaList()) {
                if (m.getCategories().contains(chosen)) {
                    results.add(m);
                }
            }

            // Ingen resultater fundet
            if (results.isEmpty()) {
                ui.displayMsg("No results found for that category");
                return;
            }

            // Vis nummererede resultater
            ui.displayMsg("\nResults:");
            for (int i = 0; i < results.size(); i++) {
                Media m = results.get(i);
                String type = (m instanceof Series) ? "[Series]" : "[Movie]";
                ui.displayMsg((i + 1) + ". " + type + " " + m.getTitle() + " (" + m.getReleaseYear() + ") - Rating: " + m.getRating());
            }

            // Lad brugeren vælge et resultat
            int mediaChoice = ui.promptNumeric("Choose a title (number): ");
            if (mediaChoice < 1 || mediaChoice > results.size()) {
                ui.displayMsg("Invalid choice");
                return;
            }

            // Lad brugeren afspille eller gemme det valgte medie
            playOrSave(results.get(mediaChoice - 1), user);
        }

        // Afspiller en film eller serie
        // Hvis det er en serie → lad brugeren vælge sæson og episode først
        private void playMedia(Media chosen, User user) {
            if (chosen instanceof Series) {
                Series series = (Series) chosen;
                ArrayList<Season> seasons = series.getSeasons();

                // Vis sæsoner
                ui.displayMsg("\nSeasons in " + series.getTitle() + ":");
                for (int i = 0; i < seasons.size(); i++) {
                    Season s = seasons.get(i);
                    ui.displayMsg((i + 1) + ". Season " + s.getSeasonNumber() + " (" + s.getEpisodes().size() + " episodes)");
                }

                int seasonChoice = ui.promptNumeric("Choose a season: ");
                if (seasonChoice < 1 || seasonChoice > seasons.size()) {
                    ui.displayMsg("Invalid choice");
                    return;
                }

                Season chosenSeason = seasons.get(seasonChoice - 1);
                ArrayList<Episode> episodes = chosenSeason.getEpisodes();

                // Vis episoder
                ui.displayMsg("\nEpisodes in Season " + chosenSeason.getSeasonNumber() + ":");
                for (int i = 0; i < episodes.size(); i++) {
                    ui.displayMsg((i + 1) + ". " + episodes.get(i).getTitle());
                }

                int episodeChoice = ui.promptNumeric("Choose an episode: ");
                if (episodeChoice < 1 || episodeChoice > episodes.size()) {
                    ui.displayMsg("Invalid choice");
                    return;
                }

                // Afspil den valgte episode
                episodes.get(episodeChoice - 1).play();

            } else {
                // Film → afspil direkte
                chosen.play();
            }

            // Tilføj til watched-listen og gem til CSV
            user.addToWatched(chosen);
            service.saveMedia(user, user.getWatched(), "data/watchedMedia.csv");
        }

        // ═══════════════ UC5: ManageLists ══════════════════════════════════

        // Viser brugerens sete film/serier
        public void displayWatchedList(User user) {
            // Tom liste → fejlbesked og returner
            if (user.getWatched().isEmpty()) {
                ui.displayMsg("Watched list is empty");
                return;
            }
            ui.displayMsg("\n── Watched list ──");
            for (Media m : user.getWatched()) {
                ui.displayMsg(m.toString());
            }
        }

        // Viser brugerens gemte film/serier
        public void displaySavedList(User user) {
            // Tom liste → fejlbesked og returner
            if (user.getSaved().isEmpty()) {
                ui.displayMsg("Saved list is empty");
                return;
            }
            ui.displayMsg("\n── Saved list ──");
            for (Media m : user.getSaved()) {
                ui.displayMsg(m.toString());
            }
        }

        // Lader brugeren afspille eller fjerne et medie fra en liste
        // fileName bruges til at gemme den opdaterede liste i den rigtige CSV-fil
        public void manageMedia(User user, ArrayList<Media> list, String fileName) {
            String titleChoice = ui.promptText("Enter title to play or remove: ");

            boolean found = false;
            for (Media m : list) {
                if (m.getTitle().equals(titleChoice)) {
                    found = true;

                    // Medie fundet → vis valg
                    ui.displayMsg("You have chosen: " + titleChoice);
                    int choice = ui.promptNumeric("1. Play  2. Remove");

                    if (choice == 1) {
                        // Afspil mediet — addToWatched og gem håndteres i playMedia()
                        playMedia(m, user);
                    } else if (choice == 2) {
                        // Fjern fra listen og gem den opdaterede liste til CSV
                        list.remove(m);
                        service.saveMedia(user, list, fileName);
                        ui.displayMsg(titleChoice + " has been removed");
                    }
                    return;
                }
            }

            // Titlen blev ikke fundet → prøv igen
            if (!found) {
                ui.displayMsg("Title not found, try again");
                manageMedia(user, list, fileName);
            }
        }

        // Lader brugeren vælge om de vil gemme eller afspille et medie
        public void playOrSave(Media chosen, User user) {
            int choice = ui.promptNumeric("\nPress 1 to save \nPress 2 to play");

            if (choice == 1) {
                // Tilføj til saved-listen og gem til CSV
                user.addToSaved(chosen);
                service.saveMedia(user, user.getSaved(), "data/savedMedia.csv");
                ui.displayMsg(chosen.getTitle() + " has been saved");
            } else if (choice == 2) {
                // Afspil mediet
                playMedia(chosen, user);
            } else {
                // Ugyldigt valg → prøv igen
                ui.displayMsg("Invalid choice, try again");
                playOrSave(chosen, user);
            }
        }

        // Returnerer den samlede medieliste fra StreamingService
        public ArrayList<Media> getMediaList() {
            return service.getMediaList();
        }
    }