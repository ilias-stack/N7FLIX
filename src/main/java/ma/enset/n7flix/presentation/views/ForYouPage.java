package ma.enset.n7flix.presentation.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import ma.enset.n7flix.Main;

import java.io.IOException;
import java.util.Objects;

public class ForYouPage extends Scene {

    public ForYouPage() throws IOException {
        super(new FXMLLoader(Main.class.getResource("FXML/forYou-view.fxml")).load());
        this.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/ma/enset/n7flix/Styles/global.css")).toExternalForm());
        this.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/ma/enset/n7flix/Styles/home.css")).toExternalForm());
        this.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/ma/enset/n7flix/Styles/list.css")).toExternalForm());

        Label usernameLabel=(Label) this.lookup("#username");
        usernameLabel.setText(Main.currentUser.getUsername());
    }
}
