package codeGame.action;

import codeGame.Initialization;
import codeGame.Main;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.util.Duration;


public class MoveStage {
    static final double sizeX = 1400, sizeY = 800, minX = 0, minY = 0;
    static double userX_LEFT = 0, userY_LEFT = 0;
    static double userX_RIGHT = Main.scene.getWidth(), userY_RIGHT = Main.scene.getHeight();
    private static double delta = 100;

    public static void rightStage() {
        Initialization.writeToFile("The screen has been moved to the right ");
        if (sizeX >= userX_LEFT + delta) {
            Main.group.setLayoutX(Main.group.getLayoutX() - delta);
            miniMap.setX(miniMap.getX() + delta);
            rectangle.setX(rectangle.getX() + delta * 1.17);
            userX_LEFT += delta;
            userX_RIGHT += delta;
        }
    }

    public static void leftStage() {
        Initialization.writeToFile("The screen has been moved to the left ");
        if (minX <= userX_LEFT - delta) {
            Main.group.setLayoutX(Main.group.getLayoutX() + delta);
            miniMap.setX(miniMap.getX() - delta);
            rectangle.setX(rectangle.getX() - delta * 1.17);
            userX_LEFT -= delta;
            userX_RIGHT -= delta;
        }
    }

    public static void upStage() {
        Initialization.writeToFile("The screen has been moved to the up ");
        if (minY <= userY_LEFT - delta) {
            Main.group.setLayoutY(Main.group.getLayoutY() + delta);
            miniMap.setY(miniMap.getY() - delta);
            rectangle.setY(rectangle.getY() - delta * 1.32);
            userY_LEFT -= delta;
            userY_RIGHT -= delta;
        }
    }

    public static void downStage() {
        Initialization.writeToFile("The screen has been moved to the down ");
        if (sizeY >= userY_LEFT + delta) {
            Main.group.setLayoutY(Main.group.getLayoutY() - delta);
            miniMap.setY(miniMap.getY() + delta);
            rectangle.setY(rectangle.getY() + delta * 1.32);
            userY_LEFT += delta;
            userY_RIGHT += delta;
        }
    }

    static ImageView miniMap = new ImageView();
    private static boolean stop = true;
    private static Timeline timeline;
    static Rectangle rectangle;

    public static boolean isStop() {
        return stop;
    }

    public static void createMiniMap() {
        SnapshotParameters snapshot = new SnapshotParameters();
        snapshot.setFill(Color.TRANSPARENT);
        miniMap = new ImageView();
        miniMap.setFitWidth(500);
        miniMap.setFitHeight(500);
        miniMap.setY(sizeY - miniMap.getFitHeight() + 2);
        rectangle = new Rectangle();
        rectangle.setX(miniMap.getX());
        rectangle.setY(miniMap.getY());
        rectangle.setWidth(265);
        rectangle.setHeight(240);
        rectangle.setFill(Color.TRANSPARENT);
        rectangle.setStroke(Color.PURPLE);
        rectangle.setStrokeType(StrokeType.INSIDE);
        rectangle.setStrokeWidth(3);
        Main.group.getChildren().addAll(miniMap,rectangle);
        timeline = new Timeline(new KeyFrame(Duration.millis(200), actionEvent -> {
            miniMap.setVisible(false);
            rectangle.setVisible(false);
            Image screen = Main.scene.getRoot().snapshot(snapshot, null);
            miniMap.setVisible(true);
            rectangle.setVisible(true);
            miniMap.setImage(screen);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        statusMiniMap();
    }

    public static void statusMiniMap() {
        if (!stop) {
            stop = true;
            timeline.stop();
            miniMap.setVisible(false);
            rectangle.setVisible(false);
        } else if (stop) {
            miniMap.toFront();
            timeline.play();
            stop = false;
            miniMap.setVisible(true);
            rectangle.setVisible(true);
        }
    }
}
