package codeGame.action;
import codeGame.Initialization;
import codeGame.Main;
import codeGame.microObject.Peasant;
import codeGame.microObject.Team;
import javafx.animation.TranslateTransition;
import java.util.HashMap;

public class Movement
{
    public static HashMap<Peasant,TranslateTransition> transitionsMap = new HashMap<>();
    static boolean stood = false;
    private static TranslateTransition [] transitions;
    public static void walk() {
        transitions = new TranslateTransition[Initialization.createEveryThingArmy().size()];
        transitionsMap =  new HashMap<>();
        int index = 0;
        for (var el : Initialization.createEveryThingArmy())
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
        Initialization.writeToFile("The movement of micro-objects was stopped ");
    }
    private static void playAnimation()
    {
        for(var el : Initialization.createEveryThingArmy()) {
            if (!Main.FORTRESS_RED.insidePeople.contains(el)
                    && !Main.FORTRESS_GREEN.insidePeople.contains(el))
            {
                transitionsMap.get(el).playFromStart();
                stood = false;
                Initialization.writeToFile("The movement of micro-objects was started ");
            }
        }
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
            Initialization.writeToFile("Objects are already standing or animation has not even been started ");
            System.out.println("For programmer: " + ex.getMessage());
        }
    }
    public static void againWalk()
    {
        try{playAnimation();}
        catch (Exception ex)
        {
            walk();
            Initialization.writeToFile("Objects are already standing or animation has not even been started ");
            System.out.println("For programmer: " + ex.getMessage());
        }
    }
    public static void clearCorpse()
    {
        for(var el : Initialization.createEveryThingArmy())
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
        Initialization.writeToFile("The corpses have been removed ");
    }
}
