package codeGame.dialogs;

import codeGame.Main;
import codeGame.action.searchPeople;
import codeGame.microObject.People;
import codeGame.microObject.Team;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
public class searchPeopleDialog
{
    public static void display()
    {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Search people");
        window.setMinWidth(350);
        window.setMinHeight(350);

        VBox layout = new VBox();
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(10);

        ComboBox comboBoxClass = new ComboBox<>();
        comboBoxClass.getItems().addAll(People.PEASANT.toString(),People.WARRIOR.toString(),
                People.KNIGHT.toString());

        RadioButton radioButtonRedTeam = new RadioButton(Team.RED.toString());
        RadioButton radioButtonGreenTeam = new RadioButton(Team.GREEN.toString());
        ToggleGroup group =  new ToggleGroup();
        radioButtonRedTeam.setToggleGroup(group);
        radioButtonGreenTeam.setToggleGroup(group);

        Label nameLabel = new Label();
        nameLabel.setText("Name: ");
        TextField nameText = new TextField();
        nameText.setPromptText("Maximiliano");

        Label ageLabel = new Label();
        ageLabel.setText("Age: ");
        TextField ageText = new TextField();
        ageText.setPromptText("24");

        Label teamLabel = new Label();
        teamLabel.setText("Team: ");

        Label classLabel = new Label();
        classLabel.setText("Type: ");

        Button searchButton = new Button("Search");
        searchButton.setOnAction((element) ->
        {
            try {
                String name = nameText.getText();
                String age = ageText.getText();
                String team = "null";
                String Class = "null";

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
                for(var el : Main.createEveryThingArmy())
                {
                    if(el.getName().equalsIgnoreCase(name) && el.getAge() == Integer.parseInt(age)
                    && el.getTeam().equalsIgnoreCase(team) && el.getType().toString().equalsIgnoreCase(Class))
                    {searchPeople.search(el);}
                }
            }
            catch(Exception ex)
            {
                try{
                    Main.inLog.write("[" + Main.currentTime() + "] " +
                        "The user dont enter information about People \n");}
                catch(IOException exc){exc.getMessage();}
                System.out.println("For programmer: " + ex.getMessage());
            }
        });
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction((element) -> window.close());
        layout.getChildren()
                .addAll(nameLabel,nameText,ageLabel,ageText,teamLabel,radioButtonRedTeam,radioButtonGreenTeam,
                        classLabel,comboBoxClass, searchButton,cancelButton);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
