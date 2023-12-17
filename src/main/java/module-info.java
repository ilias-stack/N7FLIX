module ma.enset.n7flix {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens ma.enset.n7flix to javafx.fxml;
    exports ma.enset.n7flix;
}