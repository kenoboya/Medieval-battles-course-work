package codeGame.dialogs;

import codeGame.Initialization;
import codeGame.Main;
import codeGame.macroObject.Building;
import codeGame.microObject.Peasant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.ArrayList;

public class getPeopleBelongingToMacroObject
{
   public static void display(boolean belonging)
   {
       Stage window = new Stage();
       window.initModality(Modality.APPLICATION_MODAL);
       window.setResizable(false);
       window.setMaxWidth(800);
       window.setMaxHeight(500);
       window.setMinWidth(800);
       window.setMinHeight(500);
       ObservableList<Peasant> arrayList = FXCollections.observableArrayList();
       ListView<Peasant> listView = new ListView<>();
       Text text = new Text();
       text.setFont(Font.font("Times New Roman", FontWeight.BOLD,40));
       VBox layout = new VBox();
       layout.setAlignment(Pos.CENTER);
       if(belonging) {
           text.setText("Objects that belong to MacroObjects");
           for(var macro : Initialization.createEveryThingMacro()) {
               arrayList.addAll(macro.insidePeople);
           }
           listView.setItems(arrayList);
           Initialization.writeToFile("A list of objects that belong to macro objects was received");
       }
       else
       {
           text.setText("Objects that do not belong to MacroObjects");
           ArrayList<Peasant> notBelonging = Initialization.createEveryThingArmy();
           for(var macro : Initialization.createEveryThingMacro()) {
               notBelonging.removeAll(macro.insidePeople);
           }
           arrayList.addAll(notBelonging);
           listView.setItems(arrayList);
           Initialization.writeToFile("A list of objects was received that does not belong to macro objects");
       }
       layout.getChildren().addAll(text,listView);

       Scene scene = new Scene(layout);
       window.setScene(scene);
       window.showAndWait();
   }
}
