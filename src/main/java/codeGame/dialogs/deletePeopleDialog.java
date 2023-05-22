package codeGame.dialogs;

import codeGame.Initialization;
import codeGame.Main;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class deletePeopleDialog
{
    public static void display()
    {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setResizable(false);
        window.setTitle("Delete people");
        window.setMinWidth(350);
        window.setMinHeight(350);

        VBox layout = new VBox();
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(10);

        Label redLabel = new Label("Red team");
        redLabel.setAlignment(Pos.CENTER_LEFT);
        Label greenLabel = new Label("Green team");
        greenLabel.setAlignment(Pos.CENTER_RIGHT);

        ComboBox comboBoxFirst = new ComboBox();
        comboBoxFirst.getItems().addAll(Main.armyRed);
        ComboBox comboBoxSecond = new ComboBox();
        comboBoxSecond.getItems().addAll(Main.armyGreen);

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction((element) ->
        {
            int index;
            if(comboBoxFirst.getValue() != null) {
                index = Main.armyRed.indexOf(comboBoxFirst.getValue());
                Initialization.writeToFile("The macro object has been removed to: " + Main.armyRed.get(index).getName());
                Main.group.getChildren().remove(Main.armyRed.get(index).getGroup());
                Main.armyRed.remove(comboBoxFirst.getValue());
            }
            else if(comboBoxSecond.getValue() != null)
            {
                index = Main.armyGreen.indexOf(comboBoxSecond.getValue());
                Initialization.writeToFile("The macro object has been removed to: " + Main.armyGreen.get(index).getName());
                Main.group.getChildren().remove(Main.armyGreen.get(index).getGroup());
                Main.armyGreen.remove(comboBoxSecond.getValue());
            }
            window.close();
        });
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction((element) -> window.close());
        layout.getChildren().addAll(redLabel,comboBoxFirst,greenLabel,comboBoxSecond,deleteButton,cancelButton);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
