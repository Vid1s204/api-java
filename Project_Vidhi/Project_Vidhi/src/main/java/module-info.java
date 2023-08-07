module com.example.project_vidhi {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;


    opens com.example.project_vidhi to javafx.fxml;
    exports com.example.project_vidhi;
}