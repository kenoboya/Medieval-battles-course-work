module com.example.game {
    requires javafx.controls;
    requires javafx.fxml;

    requires javafx.media;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    opens codeGame to javafx.fxml;
    exports codeGame;
    exports codeGame.dialogs;
    opens codeGame.dialogs to javafx.fxml;
    exports codeGame.action;
    opens codeGame.action to javafx.fxml;
}