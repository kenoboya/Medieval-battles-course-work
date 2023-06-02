package codeGame.dialogs;

import codeGame.Initialization;
import codeGame.Main;
import codeGame.microObject.Peasant;
import codeGame.microObject.People;
import codeGame.microObject.Team;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.Collections;

public class CreatePeopleDialog
{
    public static void display()
    {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setResizable(false);
        window.setTitle("Creating soldier");
        window.setMinWidth(350);
        window.setMinHeight(350);

        VBox layout = new VBox();
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(10);

        ComboBox comboBoxClass = new ComboBox<>();
        comboBoxClass.getItems().addAll(People.getAllEnum());

        RadioButton radioButtonRedTeam = new RadioButton(Team.RED.toString());
        RadioButton radioButtonGreenTeam = new RadioButton(Team.GREEN.toString());
        ToggleGroup group =  new ToggleGroup();
        radioButtonRedTeam.setToggleGroup(group);
        radioButtonGreenTeam.setToggleGroup(group);

        Label nameLabel = new Label();
        nameLabel.setText("Name: ");
        TextField nameText = new TextField();
        nameText.setPromptText("Arthur");

        Label ageLabel = new Label();
        ageLabel.setText("Age: ");
        TextField ageText = new TextField();
        ageText.setPromptText("17");

        Label teamLabel = new Label();
        teamLabel.setText("Team: ");

        Label xLabel = new Label();
        xLabel.setText("X: ");
        TextField xText = new TextField();
        xText.setPromptText("0 to 2750");

        Label yLabel = new Label();
        yLabel.setText("Y: ");
        TextField yText = new TextField();
        yText.setPromptText("0 to 1375");

        Label classLabel = new Label();
        classLabel.setText("Type: ");

        Button createButton = new Button("Create");
        createButton.setOnAction((element) ->
        {
            try {
                String name = nameText.getText();
                String age = ageText.getText();
                String team = "null";
                String Class = "null";
                String X = xText.getText();
                String Y = yText.getText();

                if (radioButtonRedTeam.isSelected()) {
                    team = radioButtonRedTeam.getText();
                } else if (radioButtonGreenTeam.isSelected()) {
                    team = radioButtonGreenTeam.getText();
                }

                if (comboBoxClass.getValue().equals(People.PEASANT.toString())) {
                    Class = People.PEASANT.toString();
                } else if (comboBoxClass.getValue().equals(People.WARRIOR.toString())) {
                    Class = People.WARRIOR.toString();
                } else if (comboBoxClass.getValue().equals(People.KNIGHT.toString())) {
                    Class = People.KNIGHT.toString();
                }
                if (!Class.equals("null") && !team.equals("null")) {
                    Initialization.createFactory(Class).createPeople(name,Integer.parseInt(age),
                            Team.GREEN.toString(), Double.parseDouble(X),Double.parseDouble(Y));
                }
                Collections.sort(Main.armyRed,new Peasant.PeopleDamageComparator());
                Collections.sort(Main.armyGreen,new Peasant.PeopleDamageComparator());
            }
            catch(Exception ex)
            {
                Initialization.writeToFile("The user dont enter information about People ");
                System.out.println("For programmer: " + ex.getMessage());
            }
        });
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction((element) -> window.close());
        layout.getChildren()
                .addAll(nameLabel,nameText,ageLabel,ageText,teamLabel,radioButtonRedTeam,radioButtonGreenTeam,
                        xLabel,xText,yLabel,yText,classLabel,comboBoxClass, createButton,cancelButton);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
