package codeGame.dialogs;

import codeGame.action.Save;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SaveGameDialog
{
    public static void display() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setResizable(false);
        window.setTitle("Save");
        window.setMinWidth(350);
        window.setMaxHeight(350);

        VBox layout = new VBox();
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(10);

        Text text = new Text("Choose a name to save the file");
        text.setFont(Font.font("Times New Roman", FontWeight.BOLD,32));
        TextField in = new TextField("name file");

        Button save = new Button("Save");
        save.setOnAction((el) -> {
            Save.save(in.getText());
        });
        layout.getChildren().addAll(text,in,save);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
