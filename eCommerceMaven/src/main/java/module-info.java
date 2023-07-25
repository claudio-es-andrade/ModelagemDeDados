module com.claudioesandradeecommerce.ecommercemaven {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires mysql.connector.j;
    requires org.apache.pdfbox;

    opens com.claudioesandradeecommerce.ecommercemaven to javafx.fxml;
    exports com.claudioesandradeecommerce.ecommercemaven;

}