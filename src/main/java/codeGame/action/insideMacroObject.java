package codeGame.action;

import codeGame.Main;
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
    private static String pathBackground =
            "D:\\project\\game\\src\\main\\java\\codeGame\\image\\fortressInside.jpg";
    public static Stage window;
    private static double  xRED = 2370, xGREEN = 320, y = 755;
    public static void display(Fortress fortress)
    {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setResizable(true);
        window.setTitle("Inside MacroObject");
        window.setMaxWidth(1200);
        window.setMaxHeight(600);
        Pane layoutMain = new Pane();

        layoutMain.setMaxWidth(800);
        layoutMain.setMaxHeight(550);

        FlowPane layout  = new FlowPane();
        layout.setVgap(8);
        layout.setHgap(15);
        layout.setAlignment(Pos.TOP_LEFT);
        layout.setOrientation(Orientation.VERTICAL);
        layout.setPrefWrapLength(800);
        ImageView mapView = new ImageView(new Image(pathBackground));
        layoutMain.getChildren().add(mapView);
        if(fortress.getTeam().equalsIgnoreCase(Team.RED.toString()))
        {
            try {
                Stream<Peasant> streamRed = Main.FORTRESS_RED.insidePeople.stream();
                streamRed.forEach(el -> setTranslateZero(el,layout));
            }
            catch (Exception ex){}
        }
        else if(fortress.getTeam().equalsIgnoreCase(Team.GREEN.toString()))
        {
            try {
                Stream<Peasant> streamGreen = Main.FORTRESS_GREEN.insidePeople.stream();
                streamGreen.forEach(el -> setTranslateZero(el,layout));
            }
            catch(Exception ex){}
        }
        layoutMain.getChildren().add(layout);
        layout.setMaxWidth(1200);
        layout.setMaxHeight(600);
        Scene scene = new Scene(layoutMain);
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
                           el.setXY(xRED - 200, y);
                           el.getGroup().setTranslateX(xRED - 200);
                       }
                       else if(el.getTeam().equals(Team.GREEN.toString()))
                       {
                           el.setXY(xGREEN - 200, y);
                           el.getGroup().setTranslateX(xGREEN - 200);
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
    private static void setTranslateZero(Peasant el,FlowPane layout)
    {
        el.getGroup().setVisible(true);
        el.getGroup().setTranslateX(0);
        el.getGroup().setTranslateY(0);
        layout.getChildren().addAll(el.getGroup());
    }
}
