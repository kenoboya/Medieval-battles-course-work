package codeGame.oldAction;

import codeGame.Main;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class movePeople
{
    static boolean stood = false;
    private static TranslateTransition [] transitions;
        public static void walk() {
           double minX = 0;
           double minY = 0;
           double newX;
           double newY;
           transitions = new TranslateTransition[Main.createEveryThingArmy().size()];
           int index = 0;
           for (var el : Main.createEveryThingArmy()) {
               TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5));
               double randomX = Math.floor(Math.random() * Main.sizeX/2 - (Main.sizeX/2)/2);
               double randomY = Math.floor(Math.random() * Main.sizeY/2 - (Main.sizeY/2)/2);
               newX = el.getX() + randomX;
               newY = el.getY() + randomY;
               transition.setNode(el.getGroup());
               if(minX <= newX && minY <= newY && Main.sizeX >= newX && Main.sizeY >= newY) {
                   transition.setByX(randomX);
                   transition.setByY(randomY);
                   el.setXY(newX,newY);
               }
               else {transition.setByX(0);transition.setByY(0);}
               transition.setOnFinished(e -> {
                   transition.setNode(el.getGroup());
                   double randomX2 = Math.floor(Math.random() * Main.sizeX/2 - (Main.sizeX/2)/2);
                   double randomY2 = Math.floor(Math.random() * Main.sizeY/2 - (Main.sizeY/2)/2);
                   double newX2 = el.getX() + randomX2;
                   double newY2 = el.getY() + randomY2;
                   if(minX <= newX2 && minY <= newY2 && Main.sizeX >= newX2 && Main.sizeY >= newY2) {
                       transition.setByX(randomX2);
                       transition.setByY(randomY2);
                       el.setXY(newX2,newY2);
                   }
                   else {transition.setByX(0); transition.setByY(0);}
                   transition.playFromStart();
               });
               transitions[index] = transition;
               index++;
           }
           statusCheck();
           }
    protected static void stopAnimation()
    {
        for (var transition : transitions) {
            transition.pause();
        }
        stood = true;
    }
    protected static void playAnimation()
    {
           for(var transition : transitions)
           {
               transition.play();
           }
           stood = false;
    }
    private static void statusCheck()
    {
          if(stood)
          {
              stopAnimation();
          }
          else if (!stood)
          {
              playAnimation();
          }
    }
}
