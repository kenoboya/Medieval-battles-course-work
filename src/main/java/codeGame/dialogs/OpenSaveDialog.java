package codeGame.dialogs;

import codeGame.action.Save;
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

public class OpenSaveDialog
{
    public static void display() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setResizable(false);
        window.setTitle("Open Save");
        window.setMaxWidth(800);
        window.setMaxHeight(500);
        window.setMinWidth(800);
        window.setMinHeight(500);

        VBox layout = new VBox();
        layout.setAlignment(Pos.CENTER);
        layout.setSpacing(10);

        Text text = new Text("Choose the save you want");
        text.setFont(Font.font("Times New Roman", FontWeight.BOLD,40));
        ObservableList<String> arrayList = FXCollections.observableArrayList();
        ListView<String> listView = new ListView<>();

        for(String el : Save.nameSave) {
            arrayList.add(el);
        }
        listView.setItems(arrayList);
        listView.setOnMouseClicked((el) ->
        {
            String selectedItem = listView.getSelectionModel().getSelectedItem();
            if(selectedItem != null) {
                Save.loadSave(selectedItem);
                window.close();
            }
        });

        layout.getChildren().addAll(text,listView);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
