module com.example._2122_3ahitn_scrum_mekina_hager_miklautsch_medzikovic {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;
    requires com.google.gson;
    requires java.desktop;
    requires soundPlay;


    exports com.example.Controller;
    exports Model;
    opens Model to com.google.gson, javafx.fxml;
    opens com.example.Controller to com.google.gson, javafx.fxml;
}