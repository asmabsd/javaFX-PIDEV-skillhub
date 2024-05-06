module edu.esprit.freelanceproposals {
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

    opens edu.esprit.freelanceproposals to javafx.fxml;
    opens edu.esprit.freelanceproposals.controllers to javafx.fxml;
    opens edu.esprit.freelanceproposals.entities to javafx.base;
    exports edu.esprit.freelanceproposals;
}