import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

// CatalogView er vores grafiske vindue (GUI)
// Den arver fra Application, som er JavaFX's måde at lave vinduer på
public class CatalogView extends Application {

    // Vi gemmer medielisten som static, fordi JavaFX selv opretter objektet
    // og vi derfor ikke kan sende listen direkte med i konstruktøren
    private static ArrayList<Media> mediaList;

    // Denne metode kaldes fra Menu.java når brugeren vælger "Vis katalog"
    // Vi starter JavaFX i en separat tråd (Thread) så terminalen
    // ikke fryser mens vinduet er åbent
    public static void open(ArrayList<Media> list) {
        mediaList = list;
        new Thread(() -> Application.launch(CatalogView.class)).start();
    }

    // start() er JavaFX's svarende til main() - den køres automatisk
    // når vinduet åbnes. Stage er selve vinduet (som et teaterscene)
    @Override
    public void start(Stage stage) {

        // TilePane er en layout-container der placerer elementer
        // i et gitter (ligesom fliser på et gulv)
        TilePane tilePane = new TilePane();

        // Insets(15) betyder 15 pixels luft rundt om indholdet
        tilePane.setPadding(new Insets(15));

        // Afstand mellem kortene vandret og lodret
        tilePane.setHgap(15);
        tilePane.setVgap(15);

        // Hvor mange kolonner gitteret skal have
        tilePane.setPrefColumns(5);

        // Løb igennem alle medier og lav et kort for hvert
        for (Media media : mediaList) {
            VBox card = createCard(media);
            tilePane.getChildren().add(card); // tilføj kortet til gitteret
        }

        // ScrollPane giver en scrollbar hvis indholdet er større end vinduet
        // setFitToWidth sikrer at indholdet fylder hele bredden
        ScrollPane scrollPane = new ScrollPane(tilePane);
        scrollPane.setFitToWidth(true);

        // Scene er det indhold der vises inde i vinduet (900 bred, 600 høj)
        Scene scene = new Scene(scrollPane, 900, 600);

        // Sæt titlen øverst i vinduet og vis det
        stage.setTitle("BLLKstream - Katalog");
        stage.setScene(scene);
        stage.show();
    }

    // Denne metode laver ét kort (billede + titel + rating) for ét medie
    private VBox createCard(Media media) {

        // VBox er en container der stakker elementer lodret (verticalt)
        // tallet 8 er afstanden mellem elementerne inde i kortet
        VBox card = new VBox(8);
        card.setPadding(new Insets(8));

        // CSS styling - mørk baggrund og afrundede hjørner
        card.setStyle("-fx-background-color: #1e1e1e; -fx-background-radius: 8;");

        // Tjek om mediet er en Film eller Serie og vælg den rigtige billedmappe
        String folder = (media instanceof Movie) ? "filmplakater" : "serieforsider";

        // Byg stien til billedet - titlen skal matche filnavnet præcist
        String fileName = media.getTitle()
                .replace("'", "_")
                .replace("ä", "ä")
                .replace("&", "_");

        String path = "data/" + folder + "/" + fileName + ".jpg";

        File imgFile = new File(path);

        // ImageView er den JavaFX-komponent der viser et billede
        ImageView imageView = new ImageView();
        imageView.setFitWidth(140);   // maks bredde i pixels
        imageView.setFitHeight(200);  // maks højde i pixels
        imageView.setPreserveRatio(true); // bevar billedets originale proportioner

        // Indlæs kun billedet hvis filen rent faktisk findes på disk
        // toURI().toString() konverterer filstien til et format JavaFX forstår
        if (imgFile.exists()) {
            imageView.setImage(new Image(imgFile.toURI().toString()));
        }

        // Label er en tekstboks - her viser vi titlen med fed hvid skrift
        Label titleLabel = new Label(media.getTitle());
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 11));
        titleLabel.setStyle("-fx-text-fill: white;");
        titleLabel.setWrapText(true);   // fold teksten hvis den er for lang
        titleLabel.setMaxWidth(140);    // maks samme bredde som billedet

        // Label til rating med gul farve og stjerne-emoji
        Label ratingLabel = new Label("⭐ " + media.getRating());
        ratingLabel.setStyle("-fx-text-fill: #f0c040;");

        // Tilføj billede, titel og rating til kortet i den rækkefølge
        card.getChildren().addAll(imageView, titleLabel, ratingLabel);
        return card;
    }
}