    // Movie repræsenterer en film og arver titel, år og rating fra Media
    public class Movie extends Media {

        // Konstruktør — sender titel, år og rating videre til Media
        public Movie(String title, int releaseYear, double rating) {
            super(title, releaseYear, rating);
        }

        // Afspiller filmen og viser titlen
        @Override
        public void play() {
            System.out.println(getTitle() + " is now playing...");
        }

        // Returnerer titel, år og rating — arvet fra Media
        @Override
        public String toString() {
            return super.toString();
        }
    }