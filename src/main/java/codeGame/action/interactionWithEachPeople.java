package codeGame.action;

import codeGame.Main;
import codeGame.microObject.Peasant;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.util.HashMap;

public class interactionWithEachPeople
{
    public static void selectedPeopleMoveUP() {
        if(selected != null) {
            TranslateTransition transition = new TranslateTransition();
            transition.setNode(selected.getGroup());
            double newY = selected.getY() - 60;
            if (Main.sizeY >= newY && newY >= Main.minY) {
                transition.setByY(-60);
                selected.setXY(selected.getX(), newY);
            }
            transition.play();
        }
    }
    public static void selectedPeopleMoveLEFT()
    {
        if(selected != null) {
            TranslateTransition transition = new TranslateTransition();
            transition.setNode(selected.getGroup());
            double newX = selected.getY() - 60;
            if (Main.sizeX >= newX && newX >= Main.minX) {
                transition.setByX(-60);
                selected.setXY(newX, selected.getY());
            }
            transition.play();
        }
    }
    public static void selectedPeopleMoveRIGHT()
    {
        if(selected != null) {
            TranslateTransition transition = new TranslateTransition();
            transition.setNode(selected.getGroup());
            double newX = selected.getY() + 60;
            if (Main.sizeX >= newX && newX >= Main.minX) {
                transition.setByX(60);
                selected.setXY(newX, selected.getY());
            }
            transition.play();
        }
    }
    public static void selectedPeopleMoveDOWN()
    {
        if(selected != null) {
            TranslateTransition transition = new TranslateTransition();
            transition.setNode(selected.getGroup());
            double newY = selected.getY() + 60;
            if (Main.sizeY >= newY && newY >= Main.minY) {
                transition.setByY(60);
                selected.setXY(selected.getX(), newY);
            }
            transition.play();
        }
    }
    public static void letGoPeople()
    {
        selected.letGoPeople();
        selected = null;
    }
    private static HashMap<ImageView, Peasant> mapPeople = new HashMap<>();
    private static Peasant selected;
    private static HashMap<ImageView,Peasant> fillMap ()
    {
          for(var el: Main.createEveryThingArmy())
          {
              mapPeople.put(el.getImageView(),el);
          }
          return mapPeople;
    }

    private static EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            fillMap();
            Object obj = mapPeople.get(mouseEvent.getSource());
            if(obj instanceof Peasant people)
            {
                people.takePeople();
                selected = people;
            }
        }
    };
    public static EventHandler<MouseEvent> getHandler()
    {
        return handler;
    }
}
