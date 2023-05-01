package codeGame.dialogs;

import codeGame.Main;
import codeGame.microObject.Peasant;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
public class infoAboutPeopleDialog
{
    public static void display()
    {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setResizable(false);
        window.setTitle("Information about soldier");
        window.setMinWidth(350);
        window.setMaxHeight(350);

        VBox layout = new VBox();
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(10);

        ComboBox comboBox = new ComboBox();
        comboBox.getItems().addAll(Main.createEveryThingArmy());

        Label informationLabel = new Label("Select the person you want to get information from");

        Button informationButton = new Button("Get information");
        informationButton.setOnAction((el)->
        {
            Stage window1 = new Stage();
            window1.initModality(Modality.WINDOW_MODAL);
            window1.setMinWidth(350);
            window1.setMaxHeight(350);

            VBox layout1 = new VBox();
            layout1.setAlignment(Pos.CENTER);

            try {
                int index;
                index = Main.createEveryThingArmy().indexOf(comboBox.getValue());
                Peasant object = Main.createEveryThingArmy().get(index);
                Label nameLabel = new Label("Name: " + String.valueOf(object.getName()));
                Label ageLabel = new Label("Age: " + String.valueOf(object.getAge()));
                Label teamLabel = new Label("Team: " + String.valueOf(object.getTeam()));
                Label HPLabel = new Label("HP: " + String.valueOf(object.getHP()));
                Label damageLabel = new Label("Damage: " + String.valueOf(object.getDamage()));
                Label BelongingToTheMacroObjectLabel = new Label();
                if(Main.FORTRESS_RED.insidePeople.contains(object))
                {
                    BelongingToTheMacroObjectLabel.setText("Belonging to the macroObject: RED FORTRESS");
                }
                else if(Main.FORTRESS_GREEN.insidePeople.contains(object))
                {
                    BelongingToTheMacroObjectLabel.setText("Belonging to the macroObject: GREEN FORTRESS");
                }
                else {BelongingToTheMacroObjectLabel.setText("Belonging to the macroObject: NONE");}
                Button okButton = new Button("OK");
                {
                    okButton.setOnAction((element) -> {
                        window1.close();
                    });
                }
                layout1.getChildren().addAll(nameLabel, ageLabel, teamLabel, HPLabel, damageLabel,
                        BelongingToTheMacroObjectLabel);

                Scene scene = new Scene(layout1);
                window1.setScene(scene);
                window1.showAndWait();
            }
            catch(Exception ex)
            {
                try{Main.inLog.write("[" + Main.currentTime() + "] " +
                        "The user has not selected a person \n");}
                catch(IOException exc){exc.getMessage();}
                System.out.println("For programmer: " + ex.getMessage());
            }
        });
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction((el) -> window.close());
        layout.getChildren().addAll(informationLabel,comboBox,informationButton,cancelButton);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
