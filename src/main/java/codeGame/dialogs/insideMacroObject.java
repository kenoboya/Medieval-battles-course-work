package codeGame.dialogs;

import codeGame.Main;
import codeGame.action.interactionWithEachPeople;
import codeGame.macroObject.Fortress;
import codeGame.microObject.Peasant;
import codeGame.microObject.Team;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.stream.Stream;

public class insideMacroObject
{
    private static String pathBackground = "D:\\project\\game\\src\\main\\java\\codeGame\\image\\fortressInside.jpg";
    public static Stage window;
    private static double  xRED = 2370, xGREEN = 320, y = 755, row = 0, col = 0;
    public static void display(Fortress fortress)
    {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setResizable(true);
        window.setTitle("Inside MacroObject");
        window.setMaxWidth(1200);
        window.setMaxHeight(600);
        Pane layoutMain = new Pane();
        FlowPane layout  = new FlowPane();
        layout.setVgap(8);
        layout.setHgap(15);
        layout.setAlignment(Pos.TOP_LEFT);
        layout.setOrientation(Orientation.VERTICAL);
        ImageView mapView = new ImageView(new Image(pathBackground));
        layoutMain.getChildren().add(mapView);
        if(fortress.getTeam().equalsIgnoreCase(Team.RED.toString()))
        {
            try {
                Stream<Peasant> streamRed = Main.FORTRESS_RED.insidePeople.stream();
                streamRed.forEach(el ->
                {
                    if (row <= 1050) // 0 150 300 450 600 750 900 1050 - 8 мест на столбец
                    {
                        el.getGroup().setVisible(true);
                        el.getGroup().setTranslateX(row);
                        el.getGroup().setTranslateY(col);
                        layout.getChildren().addAll(el.getGroup());
                        row += 150;
                    } else if (col <= 450) {
                        col += 150;
                        row = 0;
                    }
                });
            }
            catch (Exception ex){}
        }
        else if(fortress.getTeam().equalsIgnoreCase(Team.GREEN.toString()))
        {
            try {
                Stream<Peasant> streamGreen = Main.FORTRESS_GREEN.insidePeople.stream();
                streamGreen.forEach(el ->
                {
                    if (row <= 1050) // 0 150 300 450 600 750 900 1050 - 8 мест на столбец
                    {
                        el.getGroup().setVisible(true);
                        el.getGroup().setTranslateX(row);
                        el.getGroup().setTranslateY(col);
                        el.getGroup().setLayoutX(0);
                        el.getGroup().setLayoutY(0);
                   /*     el.getImageView().setX(row);
                        el.getImageView().setY(col);*/
                        layout.getChildren().addAll(el.getGroup());
                        row += 150;
                    } else if (col <= 450) // 0,150,300,450 - 4 колонки
                    {
                        col += 150;
                        row = 0;
                    }
                });
            }
            catch(Exception ex){}
        }
        layoutMain.getChildren().add(layout);
        layout.setMaxWidth(1200);
        layout.setMaxHeight(600);
        Scene scene = new Scene(layoutMain);
        /*scene.setFill(Color.DARKGRAY);*/
        window.setScene(scene);
        window.showAndWait();
    }
    private static HashMap<ImageView, Fortress> hashMapFortress = new HashMap<>();
    private static HashMap<ImageView,Fortress> fillMap ()
    {
        hashMapFortress.put(Main.FORTRESS_RED.getImageView(),Main.FORTRESS_RED);
        hashMapFortress.put(Main.FORTRESS_GREEN.getImageView(),Main.FORTRESS_GREEN);
        return hashMapFortress;
    }
    private static EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            fillMap();
            Object obj = hashMapFortress.get(mouseEvent.getSource());
            if(obj instanceof Fortress fortress)
            {
                display(fortress);
                for (var el : Main.createEveryThingArmy())
                {
                    if(el.equals(interactionWithEachPeople.elementAfterInsideMacro)) {
                       if(el.getTeam().equals(Team.RED.toString())) {
                           el.setXY(xRED, y);
                           el.getGroup().setTranslateX(xRED);
                       }
                       else if(el.getTeam().equals(Team.GREEN.toString()))
                       {
                           el.setXY(xGREEN, y);
                           el.getGroup().setTranslateX(xGREEN);
                       }
                        el.getGroup().setTranslateY(y);
                        interactionWithEachPeople.elementAfterInsideMacro = null;
                    }
                }
            }
        }
    };
    public static EventHandler<MouseEvent> getHandler()
    {
        return handler;
    }
}
