package codeGame.action;

import codeGame.Main;

import java.io.IOException;
public class moveStage
{
    static final double sizeX = 1400;
    static final double sizeY = 800;
    static double userX_LEFT = 0 ,userY_LEFT = 0;
    static double userX_RIGHT = Main.scene.getWidth(),userY_RIGHT = Main.scene.getHeight();
    private static double delta = 100;
    public static void rightStage()
    {
        try{
            Main.inLog.write("[" + Main.currentTime() + "] " +
                "The screen has been moved to the right \n");}
        catch(IOException ex){ex.getMessage();}
        if(sizeX >= userX_LEFT + delta) {
            Main.group.setLayoutX(Main.group.getLayoutX() - delta);
            userX_LEFT += delta;
            userX_RIGHT += delta;
        }
    }
    public static void leftStage()
    {
        try{
            Main.inLog.write("[" + Main.currentTime() + "] " +
                "The screen has been moved to the left \n");}
        catch(IOException ex){ex.getMessage();}
        if(Main.minX <= userX_LEFT - delta) {
            Main.group.setLayoutX(Main.group.getLayoutX() + delta);
            userX_LEFT -= delta;
            userX_RIGHT -= delta;
        }
    }
    public static void upStage()
    {
        try{
            Main.inLog.write("[" + Main.currentTime() + "] " +
                "The screen has been moved to the up \n");}
        catch(IOException ex){ex.getMessage();}
        if(Main.minY <= userY_LEFT - delta) {
            Main.group.setLayoutY(Main.group.getLayoutY() + delta);
            userY_LEFT -= delta;
            userY_RIGHT -= delta;
        }
    }

    public static void downStage()
    {
        try{
            Main.inLog.write("[" + Main.currentTime() + "] " +
                "The screen has been moved to the down \n");}
        catch(IOException ex){ex.getMessage();}
        if(sizeY >= userY_LEFT + delta) {
            Main.group.setLayoutY(Main.group.getLayoutY() - delta);
            userY_LEFT += delta;
            userY_RIGHT += delta;
        }
    }
}
