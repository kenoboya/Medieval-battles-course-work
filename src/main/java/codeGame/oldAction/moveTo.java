package codeGame.oldAction;

import codeGame.Main;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class moveTo
{
    protected boolean active = true;
    public static void moveToPoint()
    {
            stopMovePeople.stopWalk();
            for (var el : Main.armyGreen) {
                TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5));
                transition.setNode(el.getGroup());
                transition.setByX(Math.floor(595 - el.getX()));
                transition.setByY(Math.floor(140 - el.getY()));
                el.setXY(595, 140);
                transition.play();
            }
            for (var el : Main.armyRed) {
                TranslateTransition transition1 = new TranslateTransition(Duration.seconds(0.5));
                transition1.setNode(el.getGroup());
                transition1.setByX(Math.floor(35 - el.getX()));
                transition1.setByY(Math.floor(145 - el.getY()));
                el.setXY(35, 145);
                transition1.play();
            }
        movePeople.walk();
    }
}
