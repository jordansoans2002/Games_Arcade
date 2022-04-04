module com.example.miniproject_1b {
    requires javafx.controls;
    requires javafx.fxml;
    //requires org.controlsfx.controls;
    requires java.desktop;
    requires javafx.media;

    opens com.example.miniproject_1b to javafx.fxml;
    exports com.example.miniproject_1b;
}