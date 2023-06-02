package codeGame.action;
import codeGame.Initialization;
import codeGame.Main;
import codeGame.microObject.*;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.HashMap;

public class InteractionWithEachPeople
{
    private static HashMap<ImageView, Peasant> mapPeople = new HashMap<>();
    static ArrayList <Peasant> selected = new ArrayList<>();
    public static Peasant elementAfterInsideMacro;
    public static void selectedPeopleMoveUP() {
        for(var el : selected) {
            if (selected != null) {
                TranslateTransition transition = new TranslateTransition(Duration.millis(1));
                transition.setNode(el.getGroup());
                double newY = el.getY() - 10;
                if (Main.sizeY >= newY && newY >= Main.minY) {
                    transition.setByY(-10);
                    el.setXY(el.getX(), newY);
                }
                transition.play();
            }
        }
        Initialization.writeToFile("Selected micro-objects have been moved to the up ");
    }
    public static void selectedPeopleMoveLEFT()
    {
        for(var el : selected) {
            if (el != null) {
                TranslateTransition transition = new TranslateTransition(Duration.millis(1));
                transition.setNode(el.getGroup());
                double newX = el.getX() - 10;
                if (Main.sizeX >= newX && newX >= Main.minX - 200)
                {
                    transition.setByX(-10);
                    el.setXY(newX, el.getY());
                }
                transition.play();
            }
        }
        Initialization.writeToFile("Selected micro-objects have been moved to the left ");
    }
    public static void selectedPeopleMoveRIGHT()
    {
        for(var el : selected) {
            if (el != null) {
                TranslateTransition transition = new TranslateTransition(Duration.millis(1));
                transition.setNode(el.getGroup());
                double newX = el.getX() + 10;
                if (Main.sizeX >= newX && newX >= Main.minX - 200) {
                    transition.setByX(10);
                    el.setXY(newX, el.getY());
                }
                transition.play();
            }
        }
        Initialization.writeToFile("Selected micro-objects have been moved to the right ");
    }
    public static void selectedPeopleMoveDOWN()
    {
        for(var el : selected) {
            if (el != null) {
                TranslateTransition transition = new TranslateTransition(Duration.millis(1));
                transition.setNode(el.getGroup());
                double newY = el.getY() + 10;
                if (Main.sizeY >= newY && newY >= Main.minY) {
                    transition.setByY(10);
                    el.setXY(el.getX(), newY);
                }
                transition.play();
            }
        }
        Initialization.writeToFile("Selected micro-objects have been moved to the down ");
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
        Initialization.writeToFile("Selected micro-objects were released ");
    }
    private static HashMap<ImageView,Peasant> fillMap ()
    {
          for(var el: Initialization.createEveryThingArmy())
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
                if(Main.FORTRESS_RED.insidePeople.contains(people))
                {
                    Main.FORTRESS_RED.insidePeople.remove(people);
                    elementAfterInsideMacro = people;
                    InsideMacroObject.window.close();
                }
                else if(Main.FORTRESS_GREEN.insidePeople.contains(people))
                {
                    Main.FORTRESS_GREEN.insidePeople.remove(people);
                    elementAfterInsideMacro = people;
                    InsideMacroObject.window.close();
                }
                people.takePeople();
                selected.add(people);
                Initialization.writeToFile("The micro-object was chosen to: " + people.getName());
            }
        }
    };
    public static void clear()
    {
        for(var el : selected)
        {
            el.letGoPeople();
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
        Initialization.writeToFile("All selected micro-objects have been removed ");
    }
    public static void clonePeople()
    {
        for(var el : selected)
        {
            try {el.clone();}
            catch (CloneNotSupportedException e) {throw new RuntimeException(e);}
        }
    }
    public static EventHandler<MouseEvent> getHandler()
    {
        return handler;
    }
    public static ArrayList<Peasant> getSelected(){return selected;}
    public static void putFlag()
    {
        for(var el : selected)
        {
            if(!el.getType().equals(People.PEASANT))
            {
                Warrior aboveSecondLevel = (Warrior)el;
                aboveSecondLevel.putUpFlag();
            }
        }
    }
    public static void createCart()
    {
        for(var el : selected)
        {
            if(el.getType().equals(People.KNIGHT))
            {
                Knight maxLevel = (Knight) el;
                maxLevel.createCart();
            }
        }
    }
}
