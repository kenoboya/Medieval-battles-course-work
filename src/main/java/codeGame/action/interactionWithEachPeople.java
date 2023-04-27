package codeGame.action;
import codeGame.Main;
import codeGame.dialogs.insideMacroObject;
import codeGame.microObject.Peasant;
import codeGame.microObject.Team;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.HashMap;

public class interactionWithEachPeople
{
    public static void selectedPeopleMoveUP() {
        for(var el : selected) {
            if (selected != null) {
                TranslateTransition transition = new TranslateTransition();
                transition.setNode(el.getGroup());
                double newY = el.getY() - 60;
                if (Main.sizeY >= newY && newY >= Main.minY) {
                    transition.setByY(-60);
                    el.setXY(el.getX(), newY);
                }
                transition.play();
            }
        }
    }
    public static void selectedPeopleMoveLEFT()
    {
        for(var el : selected) {
            if (el != null) {
                TranslateTransition transition = new TranslateTransition();
                transition.setNode(el.getGroup());
                double newX = el.getX() - 60;
                if (Main.sizeX >= newX && newX >= Main.minX) {
                    transition.setByX(-60);
                    el.setXY(newX, el.getY());
                }
                transition.play();
            }
        }
    }
    public static void selectedPeopleMoveRIGHT()
    {
        for(var el : selected) {
            if (el != null) {
                TranslateTransition transition = new TranslateTransition();
                transition.setNode(el.getGroup());
                double newX = el.getX() + 60;
                if (Main.sizeX >= newX && newX >= Main.minX) {
                    transition.setByX(60);
                    el.setXY(newX, el.getY());
                }
                transition.play();
            }
        }
    }
    public static void selectedPeopleMoveDOWN()
    {
        for(var el : selected) {
            if (el != null) {
                TranslateTransition transition = new TranslateTransition();
                transition.setNode(el.getGroup());
                double newY = el.getY() + 60;
                if (Main.sizeY >= newY && newY >= Main.minY) {
                    transition.setByY(60);
                    el.setXY(el.getX(), newY);
                }
                transition.play();
            }
        }
    }
    public static void letGoPeople()
    {
        ArrayList<Peasant> dontActive = new ArrayList<>();
        for(var el : selected)
        {
            el.letGoPeople();
            dontActive.add(el);
        }
        selected.removeAll(dontActive);
    }
    private static HashMap<ImageView, Peasant> mapPeople = new HashMap<>();
    private static ArrayList <Peasant> selected = new ArrayList<>();
    private static HashMap<ImageView,Peasant> fillMap ()
    {
          for(var el: Main.createEveryThingArmy())
          {
              mapPeople.put(el.getImageView(),el);
          }
          return mapPeople;
    }
    public static Peasant elementAfterInsideMacro;
    private static EventHandler<MouseEvent> handler = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            fillMap();
            Object obj = mapPeople.get(mouseEvent.getSource());
            if(obj instanceof Peasant people)
            {
                if(Main.FORTRESS_RED.insidePeople.contains(people))
                {
                    Main.FORTRESS_RED.insidePeople.remove(people);
                    elementAfterInsideMacro = people;
                    insideMacroObject.window.close();
                }
                else if(Main.FORTRESS_GREEN.insidePeople.contains(people))
                {
                    Main.FORTRESS_GREEN.insidePeople.remove(people);
                    elementAfterInsideMacro = people;
                    insideMacroObject.window.close();
                }
                people.takePeople();
                selected.add(people);
            }
        }
    };
    public static EventHandler<MouseEvent> getHandler()
    {
        return handler;
    }
    public static void clear()
    {
        for(var el : selected)
        {
                if(el.getTeam().equalsIgnoreCase(Team.RED.toString()))
                {
                    Main.armyRed.remove(el);
                }
                else if(el.getTeam().equalsIgnoreCase(Team.GREEN.toString()))
                {
                    Main.armyGreen.remove(el);
                }
                Movement.transitionsMap.remove(el);
                Main.group.getChildren().remove(el.getGroup());
                el = null;
        }
        selected.removeAll(selected);
    }
    public static void clonePeople()
    {
        for(var el : selected)
        {
            try {el.clone();}
            catch (CloneNotSupportedException e) {throw new RuntimeException(e);}
        }
    }
}
