import Util.TextUI;
import java.util.ArrayList;

    public class Menu {

        private UserController userController;
        private MediaController mediaController;
        private TextUI ui;

        // ── Konstruktør ───────────────────────────────────
        public Menu(UserController userController, MediaController mediaController, TextUI ui) {
            this.userController = userController;
            this.mediaController = mediaController;
            this.ui = ui;
        }

        // ── Startmenu ─────────────────────────────────────
        // Vises når systemet starter og der allerede er brugere
        public void showStartMenu() {
            ui.displayMsg("\n1. Login\n2. Create User");
            int choice = ui.promptNumeric("Choose:");
            if (choice == 1) userController.loginUser();
            else if (choice == 2) userController.registerUser();
        }

        // ── Hovedmenu ─────────────────────────────────────
        // Viser valgmuligheder i hovedmenuen
        private void showMainMenu() {
            ui.displayMsg("\n Menu: \n1. Search by title\n2. Search by category\n3. Watched list\n4. Saved list\n5. Vis katalog\n0. Exit");
        }

        // ── Run ───────────────────────────────────────────
        // Hovedløkke der kører mens brugeren er logget ind
        public void run(User user) {
            String choice = "";

            // Kør indtil brugeren vælger 0 (Exit)
            while (!choice.equals("0")) {
                showMainMenu();
                choice = ui.promptText("Choose:").trim();

                switch (choice) {
                    case "1":
                        mediaController.searchTitle();
                        break;
                    case "2":
                        mediaController.searchCategory(user);
                        break;
                    case "3":
                        mediaController.displayWatchedList(user);
                        if (!user.getWatched().isEmpty()) {
                            mediaController.manageMedia(user, user.getWatched());
                        }
                        break;
                    case "4":
                        mediaController.displaySavedList(user);
                        if (!user.getSaved().isEmpty()) {
                            mediaController.manageMedia(user, user.getSaved());
                        }
                        break;
                    case "5":
                        CatalogView.open(mediaController.getMediaList());
                        break;
                    case "0":
                        ui.displayMsg("Goodbye!");
                        break;
                    default:
                        ui.displayMsg("Invalid choice, try again.");
                }
            }
        }
    }
