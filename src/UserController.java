import Util.TextUI;

    // UserController håndterer al logik omkring oprettelse og login af brugere
    // Den kommunikerer med StreamingService for at gemme og hente brugerdata (UC2 og UC3)

    public class UserController {

        private StreamingService service;
        private Menu menu;
        private TextUI ui;

        // ── Konstruktør ───────────────────────────────────
        public UserController(StreamingService service) {
            this.service = service;
            this.ui = service.getUi();
        }

        // Sætter Menu-referencen efter den er oprettet
        public void setMenu(Menu menu) {
            this.menu = menu;
        }

        // ══════════════ UC2: RegisterUser ══════════════════════════════════
        public void registerUser() {
            int attempts = 0;
            while (attempts < 3) {
                // Spørg om brugernavn
                String username = ui.promptText("Enter your username: ");

                // Tjek om brugernavnet allerede er taget
                boolean taken = false;
                for (User u : service.getUserList()) {
                    if (u.getUsername().equals(username)) {
                        ui.displayMsg("Username already exists, try again");
                        taken = true;
                        attempts++;
                        break;
                    }
                }

                // Hvis brugernavnet er taget → prøv igen
                if (taken) continue;

                // Spørg om adgangskode
                String password = ui.promptText("Enter your password: ");

                // Generer næste ledige ID
                int nextId = 1;
                for (User u : service.getUserList()) {
                    int uid = Integer.parseInt(u.getId());
                    if (uid >= nextId) nextId = uid + 1;
                }

                // Opret ny bruger og tilføj til listen
                User newUser = new User(String.valueOf(nextId), username, password);
                service.addUser(newUser);
                service.saveUsers();
                ui.displayMsg("User created!");

                // Send videre til login (UC3)
                loginUser();
                return;
            }

            // 3 mislykkede forsøg → tilbage til startmenuen
            ui.displayMsg("Too many attempts, returning to start menu");
            menu.showStartMenu();
        }

        // ═══════════════ UC3: LoginUser ═══════════════════════════════════
        public void loginUser() {
            int attempts = 0;

            while (attempts < 3) {
                String username = ui.promptText("\nEnter your username: ");
                String password = ui.promptText("\nEnter your password: ");

                // Tjek om brugernavn og adgangskode matcher
                for (User u : service.getUserList()) {
                    if (u.getUsername().equals(username) && u.checkLogin(password)) {
                        ui.displayMsg("Login successful");
                        // Login lykkedes → start hovedmenuen i Menu
                        menu.run(u);
                        return;
                    }
                }

                // Forkert login → tæl forsøg op
                attempts++;
                ui.displayMsg("Wrong username or password. Attempts left: " + (3 - attempts));
            }

            // 3 mislykkede forsøg → tilbage til startmenuen
            ui.displayMsg("Too many attempts, returning to start menu");
            menu.showStartMenu();
        }
    }
