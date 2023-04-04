package codeGame.dialogs;

import codeGame.Main;
import codeGame.microObject.Peasant;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
public class changePeopleParameterDialog
{
    public static void display(double x,double y)
    {
          Stage window =  new Stage();
          window.initModality(Modality.APPLICATION_MODAL);
          window.setTitle("Change parameters");
          window.setMinWidth(350);
          window.setMinHeight(350);

          VBox layout  = new VBox();
          layout.setAlignment(Pos.CENTER);
          layout.setSpacing(10);

          ComboBox comboBox = new ComboBox();
          comboBox.getItems().addAll(Main.createEveryThingArmy());
          ComboBox comboBoxClass = new ComboBox();
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
          TextField teamText = new TextField();

          Label classLabel = new Label();
          classLabel.setText("Type: ");
          TextField classText = new TextField();

          Label changeLabel = new Label("Select the person you want to change");

          Button changeButton = new Button("Change");
          changeButton.setOnAction((el)->
          {
                try {
                      double OX = 1, OY = 1;
                      int index;
                      index = Main.createEveryThingArmy().indexOf(comboBox.getValue());
                      Peasant object = Main.createEveryThingArmy().get(index);
                      if (comboBox.getValue() != null) {
                            if (object.getTeam().equalsIgnoreCase("RED")) {
                                  index = Main.armyRed.indexOf(comboBox.getValue());
                                  OX = Main.armyRed.get(index).getX();
                                  OY = Main.armyRed.get(index).getY();
                                  Main.group.getChildren().remove(Main.armyRed.get(index).getGroup());
                                  Main.armyRed.remove(comboBox.getValue());
                            } else if (object.getTeam().equalsIgnoreCase("GREEN")) {
                                  index = Main.armyGreen.indexOf(comboBox.getValue());
                                  OX = Main.armyGreen.get(index).getX();
                                  OY = Main.armyGreen.get(index).getY();
                                  Main.group.getChildren().remove(Main.armyGreen.get(index).getGroup());
                                  Main.armyGreen.remove(comboBox.getValue());
                            }
                            String name = nameText.getText();
                            String age = ageText.getText();
                            String team = "null";
                            String Class = "null";
                            String X = String.valueOf(OX);
                            String Y = String.valueOf(OY);

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
                }
                catch(Exception ex)
                {
                      System.out.println("For programmer: " + ex.getMessage());
                      System.out.println("For user: " + "The user dont enter information about People");
                }
                finally{window.close();}
          });
          Button cancelButton = new Button("Cancel");
          cancelButton.setOnAction((element) -> {window.close();});
          layout.getChildren()
                  .addAll(nameLabel,nameText,ageLabel,ageText,teamLabel,
                          radioButtonRedTeam,radioButtonGreenTeam,classLabel,comboBoxClass,changeLabel,comboBox,changeButton,cancelButton);
          Scene scene = new Scene(layout);
          window.setScene(scene);
          window.showAndWait();

    }
}