package codeGame.dialogs;

import codeGame.Main;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class createPeopleDialog
{
    public static void display(double x,double y)
    {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Creating soldier");
        window.setMinWidth(350);
        window.setMinHeight(350);

        VBox layout = new VBox();
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(10);

        ComboBox comboBoxClass = new ComboBox<>();
        comboBoxClass.getItems().addAll(Main.PEASANT,Main.WARRIOR,Main.KNIGHT);

        RadioButton radioButtonRedTeam = new RadioButton("RED");
        RadioButton radioButtonGreenTeam = new RadioButton("GREEN");
        ToggleGroup group =  new ToggleGroup();
        radioButtonRedTeam.setToggleGroup(group);
        radioButtonGreenTeam.setToggleGroup(group);

        Label nameLabel = new Label();
        nameLabel.setText("Name: ");
        TextField nameText = new TextField();

        Label ageLabel = new Label();
        ageLabel.setText("Age: ");
        TextField ageText = new TextField();

        Label teamLabel = new Label();
        teamLabel.setText("Team: ");

        Label xLabel = new Label();
        xLabel.setText("X: ");
        TextField xText = new TextField();

        Label yLabel = new Label();
        yLabel.setText("Y: ");
        TextField yText = new TextField();

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

                if (comboBoxClass.getValue().equals(Main.PEASANT)) {
                    Class = Main.PEASANT;
                } else if (comboBoxClass.getValue().equals(Main.WARRIOR)) {
                    Class = Main.WARRIOR;
                } else if (comboBoxClass.getValue().equals(Main.KNIGHT)) {
                    Class = Main.KNIGHT;
                }
                if (!Class.equals("null") && !team.equals("null")) {
                    Main.createSoldier(name, age, team, Class, X, Y);
                }
            }
            catch(Exception ex)
            {
                System.out.println("For programmer: " + ex.getMessage());
                System.out.println("For user: " + "The user dont enter information about People");
            }
        });
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction((element) ->
        {
            window.close();
        });
        layout.getChildren()
                .addAll(nameLabel,nameText,ageLabel,ageText,teamLabel,radioButtonRedTeam,radioButtonGreenTeam,
                        xLabel,xText,yLabel,yText,classLabel,comboBoxClass, createButton,cancelButton);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
