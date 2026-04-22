    import java.util.ArrayList;

    // Series repræsenterer en serie og arver titel, år og rating fra Media
    // Den indeholder en liste af sæsoner
    public class Series extends Media {
        private ArrayList<Season> seasons;

        // Konstruktør — sender titel, år og rating videre til Media
        // og opretter en tom sæsonliste
        public Series(String title, int releaseYear, double rating) {
            super(title, releaseYear, rating);
            this.seasons = new ArrayList<>();
        }

        // Returnerer listen af sæsoner
        public ArrayList<Season> getSeasons() {
            return seasons;
        }

        // Tilføjer en sæson til serien
        public void addSeason(Season s) {
            seasons.add(s);
        }

        // Returnerer antal sæsoner
        public int getNumberOfSeasons() {
            return seasons.size();
        }

        // Afspiller serien og viser titlen
        @Override
        public void play() {
            System.out.println(getTitle() + " is now playing...");
        }

        // Viser titel, år, rating og antal sæsoner
        @Override
        public String toString() {
            return super.toString() + " - Seasons: " + getNumberOfSeasons();
        }
    }