package codeGame.action;
import codeGame.Main;
import codeGame.microObject.Peasant;
import codeGame.microObject.Team;
import javafx.animation.TranslateTransition;
import java.io.IOException;
import java.util.HashMap;

public class Movement
{
    public static HashMap<Peasant,TranslateTransition> transitionsMap = new HashMap<>();
    static boolean stood = false;
    private static TranslateTransition [] transitions;
    public static void walk() {
        transitions = new TranslateTransition[Main.createEveryThingArmy().size()];
        transitionsMap =  new HashMap<>();
        int index = 0;
        for (var el : Main.createEveryThingArmy())
        {
            if(!el.getStatusDead())
            {
                transitions[index] = el.walk();
                transitionsMap.put(el, transitions[index]);
                index++;
            }
        }
        statusCheck();
    }
    private static void stopAnimation()
    {
        for (var transition : transitions) {transition.pause();}
        stood = true;
        try{
            Main.inLog.write("[" + Main.currentTime() + "] " +
                "The movement of micro-objects was stopped \n");}
        catch(IOException exc){exc.getMessage();}
    }
    private static void playAnimation()
    {
        for(var transition : transitions) {transition.play();}
        stood = false;
        try{
            Main.inLog.write("[" + Main.currentTime() + "] " +
                "The movement of micro-objects was started \n");}
        catch(IOException exc){exc.getMessage();}
    }
    private static void statusCheck()
    {
        if(stood) {stopAnimation();}
        else if (!stood) {playAnimation();}
    }
    public static void stopWalk()
    {
        try {stopAnimation();}
        catch(Exception ex)
        {
            try{
                Main.inLog.write("[" + Main.currentTime() + "] " +
                    "Objects are already standing or animation has not even been started \n");}
            catch(IOException exc){exc.getMessage();}
            System.out.println("For programmer: " + ex.getMessage());
        }
    }
    public static void againWalk()
    {
        try{playAnimation();}
        catch (Exception ex)
        {
            walk();
            try{
                Main.inLog.write("[" + Main.currentTime() + "] " +
                    "Objects are already standing or animation has not even been started \n");}
            catch(IOException exc){exc.getMessage();}
            System.out.println("For programmer: " + ex.getMessage());
        }
    }
    public static void clearCorpse()
    {
        for(var el : Main.createEveryThingArmy())
        {
            if(el.getStatusDead())
            {
                if(el.getTeam().equalsIgnoreCase(Team.RED.toString()))
                {
                    Main.armyRed.remove(el);
                }
                else if(el.getTeam().equalsIgnoreCase(Team.GREEN.toString()))
                {
                    Main.armyGreen.remove(el);
                }
                transitionsMap.remove(el);
                Main.group.getChildren().remove(el.getGroup());
                el = null;
            }
        }
        try{
            Main.inLog.write("[" + Main.currentTime() + "] " +
                "The corpses have been removed \n");}
        catch(IOException exc){exc.getMessage();}
    }
}
