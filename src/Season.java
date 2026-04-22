    import java.util.ArrayList;

    // Season repræsenterer én sæson i en serie
    // Den indeholder et sæsonnummer og en liste af episoder
    public class Season {
        private int seasonNumber;
        private ArrayList<Episode> episodes;

        // Konstruktør — opretter en tom episodeliste
        public Season(int seasonNumber) {
            this.seasonNumber = seasonNumber;
            this.episodes = new ArrayList<>();
        }

        // Returnerer sæsonnummeret
        public int getSeasonNumber() {
            return seasonNumber;
        }

        // Returnerer listen af episoder i sæsonen
        public ArrayList<Episode> getEpisodes() {
            return episodes;
        }

        // Tilføjer en episode til sæsonen
        public void addEpisode(Episode e) {
            episodes.add(e);
        }

        // Viser sæsonnummer og antal episoder — eks: "Season 1 - Episodes: 8"
        @Override
        public String toString() {
            return "Season " + seasonNumber + " - Episodes: " + episodes.size();
        }
    }