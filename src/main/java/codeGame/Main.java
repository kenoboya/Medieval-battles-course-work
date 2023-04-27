package codeGame;

import codeGame.macroObject.Fortress;
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
import java.util.Collections;

public class Main extends Application {
    public static Pane group = new Pane();
    public static Scene scene;
    public static Fortress FORTRESS_RED;
    public static Fortress FORTRESS_GREEN;
    public static final double minX = 0;
    public static final double minY = 0;
    public static final double sizeX = 2750;
    public static final double sizeY = 1375;

    public static void createSoldier(String name, String age, String team,String Class,String x,String y)
    {
        if(Class.equalsIgnoreCase(People.PEASANT.toString()))
        {
            if(team.equalsIgnoreCase(Team.RED.toString()))
            {
                armyRed.add(new Peasant(name,Integer.parseInt(age),
                        Team.RED.toString(), Double.parseDouble(x),Double.parseDouble(y)));
            }
            else
            {
                armyGreen.add(new Peasant(name,Integer.parseInt(age),
                        Team.GREEN.toString(), Double.parseDouble(x),Double.parseDouble(y)));
            }
        }
        else if(Class.equalsIgnoreCase(People.WARRIOR.toString()))
        {
            if(team.equalsIgnoreCase(Team.RED.toString()))
            {
                armyRed.add(new Warrior(name,Integer.parseInt(age),
                        Team.RED.toString(), Double.parseDouble(x),Double.parseDouble(y)));
            }
            else
            {
                armyGreen.add(new Warrior(name,Integer.parseInt(age),
                        Team.GREEN.toString(), Double.parseDouble(x),Double.parseDouble(y)));
            }
        }
        else if(Class.equalsIgnoreCase(People.KNIGHT.toString()))
        {
            if(team.equalsIgnoreCase(Team.RED.toString()))
            {
                armyRed.add(new Knight(name,Integer.parseInt(age),
                        Team.RED.toString(), Double.parseDouble(x),Double.parseDouble(y)));
            }
            else
            {
                armyGreen.add(new Knight(name,Integer.parseInt(age),
                        Team.GREEN.toString(), Double.parseDouble(x),Double.parseDouble(y)));
            }
        }
        Collections.sort(Main.armyRed,new Peasant.PeopleNameComparator());
        Collections.sort(Main.armyGreen,new Peasant.PeopleNameComparator());
    }
    public static ArrayList<Peasant> createEveryThingArmy()
    {
        ArrayList<Peasant> everythingArmy = new ArrayList<Peasant>();
        everythingArmy.addAll(Main.armyRed);
        everythingArmy.addAll(Main.armyGreen);
        Collections.sort(everythingArmy);
        return everythingArmy;
    }
    public static ArrayList<Peasant> armyGreen = new ArrayList<Peasant>();
    public static ArrayList<Peasant> armyRed = new ArrayList<Peasant>();
    @Override
    public void start(Stage stage) throws IOException {
        /*FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");*/
        String pathBackground = "D:\\project\\game\\src\\main\\java\\codeGame\\image\\mapTest.jpg";
        ImageView mapView = new ImageView(new Image(pathBackground));
        group.getChildren().add(mapView);
        FORTRESS_RED = new Fortress(2250, 655, Team.RED.toString()); // X120 Y110
        FORTRESS_GREEN = new Fortress(200, 655, Team.GREEN.toString()); // X120 Y110
        scene = new Scene(group,0,0);
        scene.setOnKeyPressed((key) ->
        {
            switch (key.getCode())
            {
                case F1 -> createPeopleDialog.display();
                case F2 -> deletePeopleDialog.display();
                case F3 -> changePeopleParameterDialog.display();
                case F4 -> infoAboutPeopleDialog.display();

                case NUMPAD0 -> Movement.walk();
                case E -> Peasant.stand();
                case Q -> Peasant.walkAgain();
                case F -> Peasant.returnToTheFortress();
                case J -> Movement.clearCorpse();

                case NUMPAD6 -> interactionWithEachPeople.selectedPeopleMoveRIGHT();
                case NUMPAD4 -> interactionWithEachPeople.selectedPeopleMoveLEFT();
                case NUMPAD8 -> interactionWithEachPeople.selectedPeopleMoveUP();
                case NUMPAD2 -> interactionWithEachPeople.selectedPeopleMoveDOWN();
                case NUMPAD5 -> interactionWithEachPeople.letGoPeople();
                case C -> interactionWithEachPeople.clonePeople();

                case DELETE -> interactionWithEachPeople.clear();
                // Для 3-й лабы (просто другая комбинация клавиш)
                case INSERT -> createPeopleDialog.display();
                case ESCAPE -> interactionWithEachPeople.letGoPeople();
                case F10 -> stage.close();

                case UP ->  moveStage.upStage();
                case DOWN -> moveStage.downStage();
                case RIGHT -> moveStage.rightStage();
                case LEFT -> moveStage.leftStage();

            }
        });
/*        stage.setMaxHeight(1980);
        stage.setMaxHeight(1080);
        stage.setMinWidth(1536);
        stage.setMinHeight(800);*/
        stage.setMaximized(true);
        /*stage.setFullScreen(true);*/
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}