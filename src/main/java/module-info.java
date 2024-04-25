module edu.esprit.freelancejobs {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires eu.hansolo.fx.countries;
    requires com.almasb.fxgl.all;
    requires java.sql;

    opens edu.esprit.freelancejobs to javafx.fxml;
    opens edu.esprit.freelancejobs.controllers to javafx.fxml;
    opens edu.esprit.freelancejobs.entities to javafx.base;
    exports edu.esprit.freelancejobs;
}