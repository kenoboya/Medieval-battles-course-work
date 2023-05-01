package codeGame.action;
import codeGame.Main;
import codeGame.microObject.Peasant;
import codeGame.microObject.Team;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class interactionWithEachPeople
{
    private static HashMap<ImageView, Peasant> mapPeople = new HashMap<>();
    static ArrayList <Peasant> selected = new ArrayList<>();
    public static Peasant elementAfterInsideMacro;
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
        try{
            Main.inLog.write("[" + Main.currentTime() + "] " +
                "Selected micro-objects have been moved to the up \n");}
        catch(IOException exc){exc.getMessage();}
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
        try{
            Main.inLog.write("[" + Main.currentTime() + "] " +
                "Selected micro-objects have been moved to the left \n");}
        catch(IOException exc){exc.getMessage();}
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
        try{
            Main.inLog.write("[" + Main.currentTime() + "] " +
                "Selected micro-objects have been moved to the right \n");}
        catch(IOException exc){exc.getMessage();}
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
        try{
            Main.inLog.write("[" + Main.currentTime() + "] " +
                "Selected micro-objects have been moved to the down \n");}
        catch(IOException exc){exc.getMessage();}
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
        try{
            Main.inLog.write("[" + Main.currentTime() + "] " +
                "Selected micro-objects were released \n");}
        catch(IOException exc){exc.getMessage();}
    }
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
                try{
                    Main.inLog.write("[" + Main.currentTime() + "] " +
                        "The micro-object was chosen to: " + people.getName() + "\n");}
                catch(IOException exc){exc.getMessage();}
            }
        }
    };
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
        try{
            Main.inLog.write("[" + Main.currentTime() + "] " +
                "All selected micro-objects have been removed \n");}
        catch(IOException exc){exc.getMessage();}
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
}
