module com.example._2122_3ahitn_scrum_mekina_hager_miklautsch_medzikovic {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.web;
    requires org.json;
    requires com.google.gson;


    exports com.example.Controller;
    opens com.example.Controller to javafx.fxml;
    exports Model;
    opens Model to com.google.gson;
}