package codeGame.action;
import codeGame.Main;
import codeGame.oldAction.movePeople;
import codeGame.oldAction.stopMovePeople;
import javafx.animation.TranslateTransition;
public class Movement
{
    static boolean stood = false;
    private static TranslateTransition [] transitions;
    public static void walk() {
        transitions = new TranslateTransition[Main.createEveryThingArmy().size()];
        int index = 0;
        for (var el : Main.createEveryThingArmy()) {
            transitions[index] = el.walk();
            index++;
        }
        statusCheck();
    }
    private static void stopAnimation()
    {
        for (var transition : transitions) {
            transition.pause();
        }
        stood = true;
    }
    private static void playAnimation()
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
/*            System.out.println("For programmer: " + ex.getMessage());
            System.out.println("For user: " + "Objects are already moving or the animation hasn't even started");*/
        }
    }
    public static void moveToPoint()
    {
        stopWalk();
        for (var el : Main.armyGreen) {el.returnToTheFortress();}
        for (var el : Main.armyRed) {el.returnToTheFortress();}
        walk();
    }
}
