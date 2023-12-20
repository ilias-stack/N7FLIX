package ma.enset.n7flix.presentation.views;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ma.enset.n7flix.Main;

import java.io.IOException;
import java.util.Objects;

public class AlertBox extends Stage {

    public AlertBox(String title, String error) throws IOException {
        Image icon = new Image(Objects.requireNonNull(getClass().getResource("/ma/enset/n7flix/Assets/Icon.png")).toExternalForm());
        this.getIcons().add(icon);
        this.initStyle(StageStyle.UNDECORATED);

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("FXML/alert-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/ma/enset/n7flix/Styles/global.css")).toExternalForm());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/ma/enset/n7flix/Styles/login.css")).toExternalForm());


        Button closeBtn = (Button) scene.lookup("#closeBtn");
        Label errorText = (Label) scene.lookup("#errorText");
        Label titleText = (Label) scene.lookup("#titleLabel");
        errorText.setText(error);
        titleText.setText(title);

        closeBtn.setOnAction(e->this.close());

        this.setTitle(title);
        this.setScene(scene);
        this.setAlwaysOnTop(true);
        this.show();
    }


}
