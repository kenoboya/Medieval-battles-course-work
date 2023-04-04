package codeGame.oldAction;

public class stopMovePeople
{
    public static void stopWalk()
    {
        try {movePeople.stopAnimation();}
        catch(Exception ex)
        {
            System.out.println("For programmer: " + ex.getMessage());
            System.out.println("For user: " + "Objects are already standing or animation has not even been started");
        }
    }
    public static void againWalk()
    {
        try{movePeople.playAnimation();}
        catch (Exception ex)
        {
            movePeople.walk();
/*            System.out.println("For programmer: " + ex.getMessage());
            System.out.println("For user: " + "Objects are already moving or the animation hasn't even started");*/
        }
    }
}
