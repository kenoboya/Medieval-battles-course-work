package codeGame.action;

import codeGame.Initialization;
import codeGame.Main;
import codeGame.microObject.Cart;
import codeGame.microObject.Team;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class moveStage
{
    static SnapshotParameters snapshot;
    static ImageView miniMap;
    static final double sizeX = 1400, sizeY = 800, minX = 0, minY = 0;
    static double userX_LEFT = 0 ,userY_LEFT = 0;
    static double userX_RIGHT = Main.scene.getWidth(),userY_RIGHT = Main.scene.getHeight();
    private static double delta = 100;
    public static void rightStage()
    {
        Initialization.writeToFile("The screen has been moved to the right ");
        if(sizeX >= userX_LEFT + delta) {
            Main.group.setLayoutX(Main.group.getLayoutX() - delta);
            userX_LEFT += delta;
            userX_RIGHT += delta;
        }
    }
    public static void leftStage()
    {
        Initialization.writeToFile("The screen has been moved to the left ");
        if(minX <= userX_LEFT - delta) {
            Main.group.setLayoutX(Main.group.getLayoutX() + delta);
            userX_LEFT -= delta;
            userX_RIGHT -= delta;
        }
    }
    public static void upStage()
    {
        Initialization.writeToFile("The screen has been moved to the up ");
        if(minY <= userY_LEFT - delta) {
            Main.group.setLayoutY(Main.group.getLayoutY() + delta);
            userY_LEFT -= delta;
            userY_RIGHT -= delta;
        }
    }
    public static void downStage()
    {
        Initialization.writeToFile("The screen has been moved to the down ");
        if(sizeY >= userY_LEFT + delta) {
            Main.group.setLayoutY(Main.group.getLayoutY() - delta);
            userY_LEFT += delta;
            userY_RIGHT += delta;
        }
    }
    /*public static void createMiniMap()
    {
        snapshot = new SnapshotParameters();
        snapshot.setFill(Color.TRANSPARENT);
        Image screen = Main.scene.getRoot().snapshot(snapshot, null);
        miniMap = new ImageView(screen);
        miniMap.setFitWidth(500);
        miniMap.setFitHeight(500);
        miniMap.setY(sizeY - miniMap.getFitHeight());
        Main.group.getChildren().add(miniMap);
    }

    public static void updateMiniMap()
    {
        Image screen = Main.scene.getRoot().snapshot(snapshot, null);
        miniMap.setImage(screen);
    }

    public static void timeline()
    {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(3000), actionEvent ->
        {
            updateMiniMap();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }*/
    private static PixelWriter pixelWriter;
    public static void createMiniMap() {
        miniMap = new ImageView();
        miniMap.setFitWidth(500);
        miniMap.setFitHeight(500);
        miniMap.setY(sizeY - miniMap.getFitHeight());
        Main.group.getChildren().add(miniMap);
        WritableImage image = new WritableImage((int) miniMap.getFitWidth(), (int) miniMap.getFitHeight());
        pixelWriter = image.getPixelWriter();
        miniMap.setImage(image);
    }

    public static void updateMiniMap() {
        Platform.runLater(() -> {
            int width = (int) miniMap.getFitWidth();
            int height = (int) miniMap.getFitHeight();
            PixelReader reader = Main.scene.getRoot().snapshot(null, null).getPixelReader();
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    pixelWriter.setArgb(x, y, reader.getArgb(x, y));
                }
            }
        });
    }

    public static void timeline() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateMiniMap();
            }
        };
        timer.start();
    }

}
