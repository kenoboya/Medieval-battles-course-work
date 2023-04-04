package codeGame;

import codeGame.microObject.*;
import codeGame.dialogs.*;
import codeGame.action.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
public class Main extends Application {
    static final String RED = "RED";
    static final String GREEN = "GREEN";
    public static Pane group = new Pane();
    public static final String PEASANT = "Peasant";
    public static final String WARRIOR = "Warrior";
    public static final String KNIGHT = "Knight";

    public static final double minX = 0;
    public static final double minY = 0;
    public static final double sizeX = 1345; // 1345 + 200
    public static final double sizeY = 580; // 580 + 300

    public static void createSoldier(String name, String age, String team,String Class,String x,String y)
    {
        if(Class.equalsIgnoreCase(PEASANT))
        {
            if(team.equalsIgnoreCase(RED))
            {
                armyRed.add(new Peasant(name,Integer.parseInt(age),
                        RED,Double.parseDouble(x),Double.parseDouble(y)));
            }
            else
            {
                armyGreen.add(new Peasant(name,Integer.parseInt(age),
                        GREEN,Double.parseDouble(x),Double.parseDouble(y)));
            }
        }
        else if(Class.equalsIgnoreCase(WARRIOR))
        {
            if(team.equalsIgnoreCase(RED))
            {
                armyRed.add(new Warrior(name,Integer.parseInt(age),
                        RED,Double.parseDouble(x),Double.parseDouble(y)));
            }
            else
            {
                armyGreen.add(new Warrior(name,Integer.parseInt(age),
                        GREEN,Double.parseDouble(x),Double.parseDouble(y)));
            }
        }
        else if(Class.equalsIgnoreCase(KNIGHT))
        {
            if(team.equalsIgnoreCase(RED))
            {
                armyRed.add(new Knight(name,Integer.parseInt(age),
                        RED,Double.parseDouble(x),Double.parseDouble(y)));
            }
            else
            {
                armyGreen.add(new Knight(name,Integer.parseInt(age),
                        GREEN,Double.parseDouble(x),Double.parseDouble(y)));
            }
        }
    }
    public static ArrayList<Peasant> createEveryThingArmy()
    {
       ArrayList<Peasant> everythingArmy = new ArrayList<Peasant>();
        everythingArmy.addAll(Main.armyRed);
        everythingArmy.addAll(Main.armyGreen);
        return everythingArmy;
    }
    public static ArrayList<Peasant> armyGreen = new ArrayList<Peasant>();
    public static ArrayList<Peasant> armyRed = new ArrayList<Peasant>();
    @Override
    public void start(Stage stage) throws IOException {
        /*FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");*/
        String pathBackground = "D:\\project\\game\\src\\main\\java\\codeGame\\image\\map.jpg";
        ImageView mapView = new ImageView(new Image(pathBackground));
        group.getChildren().add(mapView);
        Scene scene = new Scene(group,sizeX+200,sizeY+300); // 1280 800
        scene.setOnKeyPressed((key) ->
        {
            switch (key.getCode())
            {
                case F1 -> createPeopleDialog.display(500,500);
                case F2 -> deletePeopleDialog.display(500,500);
                case F3 -> changePeopleParameterDialog.display(500,500);
                case F4 -> infoAboutPeopleDialog.display(500,500);
                case NUMPAD0 -> Movement.walk();
                case E -> Peasant.stand();
                case Q -> Peasant.walkAgain();
                case F -> Movement.moveToPoint();
                case J -> deadAllPeople.dead();
                case F10 -> stage.close();

                // Устарело
/*                case NUMPAD0 -> movePeople.walk();
                case E -> stopMovePeople.stopWalk();
                case Q -> stopMovePeople.againWalk();
                case F -> moveTo.moveToPoint();
                case J -> deadAllPeople.dead();*/
            }
        });
        stage.setMaxHeight(1980);
        stage.setMaxHeight(1080);
        stage.setScene(scene);
        stage.show();

    }
    public static void main(String[] args) {
        launch();
    }
}