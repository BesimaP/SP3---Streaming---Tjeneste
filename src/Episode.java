    // Episode repræsenterer én enkelt episode i en serie
    // Den arver titel, år og rating fra Media
    public class Episode extends Media {

        private String title;
        private int episodeNumber;

        // Konstruktør — sender titel, år og rating videre til Media
        public Episode(String title, int episodeNumber, int releaseYear, double rating) {
            super(title, releaseYear, rating);
            this.title = title;
            this.episodeNumber = episodeNumber;
        }

        // Returnerer episodens titel
        public String getTitle() {
            return title;
        }

        // Afspiller episoden og viser titel og episodenummer
        @Override
        public void play() {
            System.out.println("Now playing: " + title + " (Episode " + episodeNumber + ")");
        }

        // Viser episodenummer og titel — eks: "Episode 3: The One Where..."
        @Override
        public String toString() {
            return "Episode " + episodeNumber + ": " + title;
        }
    }
