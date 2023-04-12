package codeGame.action;
import codeGame.Main;
import codeGame.microObject.Peasant;
import javafx.animation.TranslateTransition;
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
    }
    private static void playAnimation()
    {
        for(var transition : transitions) {transition.play();}
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
    public static void stopWalk()
    {
        try {stopAnimation();}
        catch(Exception ex)
        {
            System.out.println("For programmer: " + ex.getMessage());
            System.out.println("For user: " + "Objects are already standing or animation has not even been started");
        }
    }
    public static void againWalk()
    {
        try{playAnimation();}
        catch (Exception ex)
        {
            walk();
            System.out.println("For programmer: " + ex.getMessage());
            System.out.println("For user: " + "Objects are already moving or the animation hasn't even started");
        }
    }
    public static void clearCorpse()
    {
        for(var el : Main.createEveryThingArmy())
        {
            if(el.getStatusDead())
            {
                if(el.getTeam().equalsIgnoreCase("RED"))
                {
                    Main.armyRed.remove(el);
                }
                else if(el.getTeam().equalsIgnoreCase("GREEN"))
                {
                    Main.armyGreen.remove(el);
                }
                transitionsMap.remove(el);
                Main.group.getChildren().remove(el.getGroup());
                el = null;
            }
        }
    }
}
