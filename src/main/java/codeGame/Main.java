package codeGame;
import codeGame.macroObject.*;
import codeGame.microObject.*;
import codeGame.dialogs.*;
import codeGame.action.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

public class Main extends Application {

    public static Pane group = new Pane();
    public static Scene scene;

    public static FileWriter inLog;
    static {
        try {inLog = new FileWriter("log.txt");}
        catch (IOException e) {throw new RuntimeException(e);}
    }

    public static Fortress FORTRESS_RED,FORTRESS_GREEN;
    public static House FIRSTHOUSE,SECONDHOUSE,THIRDHOUSE,FOURTHHOUSE,FIFTHHOUSE;
    public static Mine  FIRSTMINE,SECONDMINE,THIRDMINE;
    public static final double minX = 0 , minY = 0, sizeX = 2750, sizeY = 1375;

    public static String currentTime()
    {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return currentTime.format(formatter);
    }
    private static void updatedSizeArray(int[] array)
    {
            array[0] = Main.armyRed.size();
            array[1] = Main.armyGreen.size();
            array[2] = interactionWithEachPeople.getSelected().size();
    }
    public static VBox textCreated()
    {
        VBox textVbox = new VBox();
        textVbox.setAlignment(Pos.TOP_LEFT);
        textVbox.setSpacing(5);
        Text [] text = new Text[3];
        String [] count = new String[]{"read team","green team", "active"};
        int [] method = new int[3];/*{Main.armyRed.size(),Main.armyGreen.size(),
                interactionWithEachPeople.getSelected().size()};*/
        Color [] color = new Color[]{Color.RED,Color.GREEN,Color.YELLOW};
        updatedSizeArray(method);
        for(int i = 0; i < text.length; i++)
        {
            text[i] = new Text("Number of " + count[i] + "soldiers: " + method[i]);
            text[i].setFont(Font.font("Roboto", FontWeight.BOLD,28));
            text[i].setStroke(Color.BLACK);
            text[i].setFill(color[i]);
            textVbox.getChildren().add(text[i]);

        }
        new Thread(() ->
        {
            while(true)
            {
                updatedSizeArray(method);
                for(int i = 0; i < text.length; i++)
                {
                    text[i].setText("Number of " + count[i] + "soldiers: " + method[i]);
                }
            }
        }).start();
        return textVbox;
    }
    public static void alreadyCreatedObjects() throws IOException
    {
        FORTRESS_RED = new Fortress(2250, 655, Team.RED.toString()); // X120 Y110
        FORTRESS_GREEN = new Fortress(200, 655, Team.GREEN.toString()); // X120 Y110
        FIRSTHOUSE = new House("First house",2500,200);
        SECONDHOUSE = new House("Second house",2300,1100);
        THIRDHOUSE = new House("Third house",800,0);
        FOURTHHOUSE = new House("Fourth house",250,1200);
        FIFTHHOUSE = new House("Fifth house",1600,900);
        FIRSTMINE = new Mine("First mine",0,250);
        SECONDMINE = new Mine("Second mine",1500,300);
        THIRDMINE = new Mine("Third mine",900,900);
        String nameObject[] = new String[]{Team.RED.toString() + " FORTRESS",Team.GREEN.toString() + " FORTRESS",
                FIRSTHOUSE.getName(),SECONDHOUSE.getName(), THIRDHOUSE.getName(), FOURTHHOUSE.getName(),
                FIFTHHOUSE.getName(), FIRSTMINE.getName(), SECONDMINE.getName(), THIRDMINE.getName()};
        try
        {
            for(int i = 0; i < 10; i++)
            {
                inLog.write("[" + currentTime() +   "] " +
                        "A macro object has been created to: " + nameObject[i] + "\n");
            }
        }
        catch(IOException ex){ex.getMessage();}
        createSoldier("Max","23",Team.GREEN.toString(), People.KNIGHT.toString(), "1500", "550");
        createSoldier("Danila","18",Team.RED.toString(), People.WARRIOR.toString(), "0", "0");
        createSoldier("San","27",Team.GREEN.toString(), People.PEASANT.toString(), "2700", "1000");
        createSoldier("Dan","44",Team.RED.toString(), People.PEASANT.toString(), "0", "1300");
        createSoldier("Arthur","54",Team.GREEN.toString(), People.WARRIOR.toString(), "400", "300");
        createSoldier("Zaur","29",Team.RED.toString(), People.PEASANT.toString(), "2500", "500");
        createSoldier("Ragnar","43",Team.RED.toString(), People.KNIGHT.toString(), "1400", "1000");
        createSoldier("Rollo","59",Team.GREEN.toString(), People.WARRIOR.toString(), "2750", "1375");
        createSoldier("Diego","44",Team.GREEN.toString(), People.KNIGHT.toString(), "200", "400");
        createSoldier("Alberto","16",Team.RED.toString(), People.WARRIOR.toString(), "150", "1300");
        createSoldier("Fabrio","26",Team.GREEN.toString(), People.PEASANT.toString(), "2700", "0");
        createSoldier("Hans","33",Team.RED.toString(), People.KNIGHT.toString(), "500", "1300");
        createSoldier("Bogdan","65",Team.GREEN.toString(), People.WARRIOR.toString(), "700", "600");
        createSoldier("Erjan","42",Team.RED.toString(), People.PEASANT.toString(), "2500", "800");
        createSoldier("Bjorn","59",Team.RED.toString(), People.KNIGHT.toString(), "1600", "1000");
        createSoldier("Oleg","81",Team.GREEN.toString(), People.PEASANT.toString(), "2350", "1375");
    }
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
        Collections.sort(Main.armyRed,new Peasant.PeopleAgeComparator());
        Collections.sort(Main.armyGreen,new Peasant.PeopleHPComparator());
        try{
            inLog.write("[" + currentTime() + "] " +
                "A micro object has been created to: " + name + "\n");}
        catch(IOException ex){ex.getMessage();}
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
        /*FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("BelongingToTheMacroObject.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");*/
        String pathBackground = "D:\\project\\game\\src\\main\\java\\codeGame\\image\\mapTest.jpg";
        ImageView mapView = new ImageView(new Image(pathBackground));
        group.getChildren().add(mapView);
        alreadyCreatedObjects();
        group.getChildren().add(textCreated());
        scene = new Scene(group,0,0);
        scene.setOnKeyPressed((key) ->
        {
            KeyCodeCombination combination = new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN);
            if(combination.match(key)) {
                interactionWithEachPeople.clonePeople();
            }
            else {
                switch (key.getCode()) {
                    case F1 -> createPeopleDialog.display();
                    case F2 -> deletePeopleDialog.display();
                    case F3 -> changePeopleParameterDialog.display();
                    case F4 -> infoAboutPeopleDialog.display();
                    case F5 -> getPeopleBelongingToMacroObject.display(true);
                    case F6 -> getPeopleBelongingToMacroObject.display(false);
                    case F7 -> searchPeopleDialog.display();

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

                    case DELETE -> interactionWithEachPeople.clear();
                    // Для 3-й лабы (просто другая комбинация клавиш)
                    case INSERT -> createPeopleDialog.display();
                    case ESCAPE -> interactionWithEachPeople.letGoPeople();
                    case F10 -> System.exit(0);

                    case UP -> moveStage.upStage();
                    case DOWN -> moveStage.downStage();
                    case RIGHT -> moveStage.rightStage();
                    case LEFT -> moveStage.leftStage();
                }
            }
        });
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}