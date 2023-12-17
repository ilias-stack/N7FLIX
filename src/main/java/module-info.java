module ma.enset.n7flix {
    requires javafx.controls;
    requires javafx.fxml;


    opens ma.enset.n7flix to javafx.fxml;
    exports ma.enset.n7flix;
}