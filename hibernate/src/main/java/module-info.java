module com.example.hibernate {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.sql;

    opens com.example.hibernate to javafx.fxml;
    exports com.example.hibernate;
}