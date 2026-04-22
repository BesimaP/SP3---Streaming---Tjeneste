    import java.util.ArrayList;

    // User repræsenterer en bruger i systemet
    // Den gemmer brugerens login-oplysninger samt sete og gemte medier
    public class User {

        private String id;
        private String username;
        private String password;
        private ArrayList<Media> watched;
        private ArrayList<Media> saved;

        // Konstruktør — opretter tomme lister til watched og saved
        public User(String id, String username, String password) {
            this.id = id;
            this.username = username;
            this.password = password;
            this.watched = new ArrayList<>();
            this.saved = new ArrayList<>();
        }

        // Tjekker om det indtastede password matcher brugerens password
        public boolean checkLogin(String password) {
            return this.password.equals(password);
        }

        // Tilføjer et medie til watched-listen — undgår dubletter
        public void addToWatched(Media m) {
            if (!watched.contains(m)) {
                watched.add(m);
            }
        }

        // Tilføjer et medie til saved-listen — undgår dubletter
        public void addToSaved(Media m) {
            if (!saved.contains(m)) {
                saved.add(m);
            }
        }

        // Fjerner et medie fra saved-listen hvis det findes
        public void removeFromSaved(Media m) {
            if (saved.contains(m)) {
                saved.remove(m);
            }
        }

        // Returnerer brugerens ID
        public String getId() {
            return id;
        }

        // Returnerer brugernavnet
        public String getUsername() {
            return username;
        }

        // Returnerer adgangskoden
        public String getPassword() {
            return password;
        }

        // Opdaterer adgangskoden
        public void setPassword(String password) {
            this.password = password;
        }

        // Returnerer listen af sete medier
        public ArrayList<Media> getWatched() {
            return watched;
        }

        // Returnerer listen af gemte medier
        public ArrayList<Media> getSaved() {
            return saved;
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }