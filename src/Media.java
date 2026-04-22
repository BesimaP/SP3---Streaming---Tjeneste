import java.util.ArrayList;

    // Media er en abstrakt klasse der repræsenterer et medie (film eller serie)
    // Den kan ikke bruges direkte — man skal bruge Movie eller Series i stedet
    public abstract class Media {

        // Felter der er fælles for alle medier
        protected String title;
        protected int releaseYear;
        protected double rating;
        protected ArrayList<Category> categories;

        // Konstruktør — sætter de fælles felter og opretter en tom kategoriliste
        public Media(String title, int releaseYear, double rating) {
            this.title = title;
            this.releaseYear = releaseYear;
            this.rating = rating;
            this.categories = new ArrayList<>();
        }

        // Returnerer mediets titel
        public String getTitle() {
            return title;
        }

        // Returnerer udgivelsesåret
        public int getReleaseYear() {
            return releaseYear;
        }

        // Returnerer mediets rating
        public double getRating() {
            return rating;
        }

        // Returnerer listen af kategorier
        public ArrayList<Category> getCategories() {
            return categories;
        }

        // Tilføjer en kategori til mediet
        public void addCategory(Category c) {
            categories.add(c);
        }

        // Abstrakt metode — hver underklasse skal selv implementere hvordan mediet afspilles
        public abstract void play();

        // Viser titel, år og rating — eks: "Inception (2010) - Rating: 8.8"
        @Override
        public String toString() {
            return title + " (" + releaseYear + ") - Rating: " + rating;
        }
    }