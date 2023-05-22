package codeGame;

import codeGame.action.*;
import codeGame.macroObject.Construction;
import codeGame.macroObject.Factory.*;
import codeGame.microObject.Factory.*;
import codeGame.microObject.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

public class Initialization
{
    public static void initialization()
    {
        String pathBackground = "D:\\project\\game\\src\\main\\java\\codeGame\\image\\mapTest.jpg";
        ImageView mapView = new ImageView(new Image(pathBackground));
        Main.group.getChildren().add(mapView);
        Main.group.getChildren().addAll(textCreated(),pointCreated(),borderRectangle());
        alreadyCreatedObjects();
        Main.scene = new Scene(Main.group,0,0);
        Control.control();
        initializationTimeline();
        Sound.playSoundTrack();
        moveStage.createMiniMap();
    }
    private static Rectangle borderRectangle()
    {
        Rectangle rectangle = new Rectangle();
        rectangle.setFill(Color.TRANSPARENT);
        rectangle.setStroke(Color.BURLYWOOD);
        rectangle.setX(Main.minX);
        rectangle.setY(Main.minY);
        rectangle.setWidth(Main.sizeX);
        rectangle.setHeight(Main.sizeY);
        rectangle.setStrokeType(StrokeType.INSIDE);
        rectangle.setStrokeWidth(3);
        return rectangle;
    }
    private static String currentTime()
    {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return currentTime.format(formatter);
    }
    public static void writeToFile(String message)
    {
        try{
            Main.inLog.write("[" + Initialization.currentTime() + "] " + message + "\n");}
        catch(IOException exc){ throw new RuntimeException();}
    }
    public static PeopleFactory createFactory(String type)
    {
        if(type.equalsIgnoreCase(People.PEASANT.toString())) {return new PeasantFactory();}
        else if(type.equalsIgnoreCase(People.WARRIOR.toString())) {return new WarriorFactory();}
        else if(type.equalsIgnoreCase(People.KNIGHT.toString())) {return new KnightFactory();}
        else {throw new RuntimeException("Object was not selected");}
    }
    public static ArrayList<Peasant> createEveryThingArmy()
    {
        ArrayList<Peasant> everythingArmy = new ArrayList<Peasant>();
        everythingArmy.addAll(Main.armyRed);
        everythingArmy.addAll(Main.armyGreen);
        Collections.sort(everythingArmy);
        return everythingArmy;
    }
    public static ArrayList<Construction> createEveryThingMacro()
    {
        ArrayList<Construction> everyThingMacro = new ArrayList<Construction>();
        everyThingMacro.addAll(Main.houseList);
        everyThingMacro.addAll(Main.mineList);
        everyThingMacro.add(Main.FORTRESS_RED);
        everyThingMacro.add(Main.FORTRESS_GREEN);
        return everyThingMacro;
    }
    private static void alreadyCreatedObjects()
    {
        FortressFactory.createAlreadyBulding();
        HouseFactory.createAlreadyBulding();
        MineFactory.createAlreadyBulding();
        PeasantFactory.createAlreadyPeople();
        WarriorFactory.createAlreadyPeople();
        KnightFactory.createAlreadyPeople();
    }
    private static void initializationTimeline()
    {
        intersection.intersectionWithFortress();
        intersection.intersectionWithHouse();
        intersection.intersectionAttackFlag();
        intersection.intersectionFlagWithMacroObject();
        intersection.intersectionAttackEnemy();
        intersection.CartIntersectionWithMine();
        intersection.belongingToMacro();
        Movement.walk();
    }
    private static void updatedSizeArray(int[] array)
    {
        array[0] = Main.armyRed.size();
        array[1] = Main.armyGreen.size();
        array[2] = interactionWithEachPeople.getSelected().size();
    }
    private static HBox pointCreated()
    {
        HBox textHbox = new HBox();
        textHbox.setAlignment(Pos.TOP_CENTER);
        textHbox.setSpacing(20);
        textHbox.setPrefWidth(Main.sizeX + 175);
        Text[] text = new Text[3];
        int [] method = new int[3];
        Color[] color = new Color[]{Color.RED,Color.WHITE,Color.GREEN};
        for(int i = 0; i < text.length; i++)
        {
            text[i] = new Text();
            if(i == 1){text[i].setText(":");}
            text[i].setFont(Font.font("Roboto", FontWeight.BOLD,72));
            text[i].setStroke(Color.BLACK);
            text[i].setFill(color[i]);
            textHbox.getChildren().add(text[i]);

        }
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(50), actionEvent ->
        {
            text[0].setText("" + Main.FORTRESS_RED.getPOINT());
            text[2].setText("" + Main.FORTRESS_GREEN.getPOINT());
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        return textHbox;
    }
    private static VBox textCreated()
    {
        VBox textVbox = new VBox();
        textVbox.setAlignment(Pos.TOP_LEFT);
        textVbox.setSpacing(5);
        Text[] text = new Text[3];
        String [] count = new String[]{"read team","green team", "active"};
        int [] method = new int[3];
        Color[] color = new Color[]{Color.RED,Color.GREEN,Color.YELLOW};
        updatedSizeArray(method);
        for(int i = 0; i < text.length; i++)
        {
            text[i] = new Text("Number of " + count[i] + "soldiers: " + method[i]);
            text[i].setFont(Font.font("Roboto", FontWeight.BOLD,28));
            text[i].setStroke(Color.BLACK);
            text[i].setFill(color[i]);
            textVbox.getChildren().add(text[i]);

        }
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(50), actionEvent ->
        {
            updatedSizeArray(method);
            for(int i = 0; i < text.length; i++)
            {
                text[i].setText("Number of " + count[i] + "soldiers: " + method[i]);
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        return textVbox;
    }
}
