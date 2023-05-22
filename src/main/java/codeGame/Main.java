package codeGame;
import codeGame.macroObject.*;
import codeGame.microObject.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {

    public static Pane group = new Pane();
    public static Scene scene;
    public static FileWriter inLog;
    static {
        try {inLog = new FileWriter("log.txt");}
        catch (IOException e) {throw new RuntimeException(e);}
    }
    public static Fortress FORTRESS_RED,FORTRESS_GREEN;
    public static ArrayList<House> houseList = new ArrayList<>();
    public static ArrayList<Mine> mineList = new ArrayList<>();
    public static ArrayList<Peasant> armyGreen = new ArrayList<Peasant>();
    public static ArrayList<Peasant> armyRed = new ArrayList<Peasant>();
    public static final double minX = 200 , minY = 125, sizeX = 2550, sizeY = 1425; // 0 0 2750 1375
    @Override
    public void start(Stage stage) throws IOException {
        /*FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("BelongingToTheMacroObject.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");*/
        Initialization.initialization();
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}