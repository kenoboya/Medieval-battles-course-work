package codeGame.action;

import codeGame.Main;

public class moveStage
{
    private static final double sizeX = 1400;
    private static final double sizeY = 800;
    private static double userX_LEFT = 0 ,userY_LEFT = 0;
    static double userX_RIGHT = Main.scene.getWidth(),userY_RIGHT = Main.scene.getHeight();
    private static double delta = 100;
    public static void rightStage()
    {
        System.out.println("RIGHT");
        System.out.println(userX_LEFT + delta);
        if(sizeX >= userX_LEFT + delta) {
            Main.group.setLayoutX(Main.group.getLayoutX() - delta);
            userX_LEFT += delta;
            userX_RIGHT += delta;
        }
    }
    public static void leftStage()
    {
        System.out.println("LEFT");
        System.out.println(userX_LEFT - delta);
        if(Main.minX <= userX_LEFT - delta) {
            Main.group.setLayoutX(Main.group.getLayoutX() + delta);
            userX_LEFT -= delta;
            userX_RIGHT -= delta;
        }
    }
    public static void upStage()
    {
        System.out.println("UP");
        System.out.println(userY_LEFT - delta);
        if(Main.minY <= userY_LEFT - delta) {
            Main.group.setLayoutY(Main.group.getLayoutY() + delta);
            userY_LEFT -= delta;
            userY_RIGHT -= delta;
        }
    }

    public static void downStage()
    {
        System.out.println("DOWN");
        System.out.println(userY_LEFT + delta);
        if(sizeY >= userY_LEFT + delta) {
            Main.group.setLayoutY(Main.group.getLayoutY() - delta);
            userY_LEFT += delta;
            userY_RIGHT += delta;
        }
    }
}
