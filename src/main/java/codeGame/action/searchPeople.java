package codeGame.action;

import codeGame.Initialization;
import codeGame.Main;
import codeGame.microObject.Peasant;
public class searchPeople
{
    public static void search(Peasant people)
    {
        Initialization.writeToFile("The search for a micro-object was started to: " + people.getName());
        if(people.getX() <= 1400 && people.getY() <= 650)
        {
            Main.group.setLayoutX(0);
            Main.group.setLayoutY(0);
            moveStage.userX_RIGHT = Main.minX;
            moveStage.userY_RIGHT = Main.minY;
            moveStage.userX_LEFT = Main.minX;
            moveStage.userY_LEFT = Main.minY;
        }
        else if(people.getX() > 1400 && people.getY() <= 650)
        {
            Main.group.setLayoutX(-1400);
            Main.group.setLayoutY(0);
            moveStage.userX_RIGHT = moveStage.sizeX;
            moveStage.userY_RIGHT = Main.minY;
            moveStage.userX_LEFT = moveStage.sizeX;
            moveStage.userY_LEFT = Main.minY;
        }
        else if(people.getX() <= 1400 && people.getY() > 650)
        {
            Main.group.setLayoutX(0);
            Main.group.setLayoutY(-800);
            moveStage.userX_RIGHT = Main.minX;
            moveStage.userY_RIGHT = moveStage.sizeY;
            moveStage.userX_LEFT = Main.minX;
            moveStage.userY_LEFT = moveStage.sizeY;
        }
        else if(people.getX() > 1400 && people.getY() > 650)
        {
            Main.group.setLayoutX(-1400);
            Main.group.setLayoutY(-800);
            moveStage.userX_RIGHT = moveStage.sizeX;
            moveStage.userY_RIGHT = moveStage.sizeY;
            moveStage.userX_LEFT = moveStage.sizeX;
            moveStage.userY_LEFT = moveStage.sizeY;
        }
        people.takePeople();
        interactionWithEachPeople.selected.add(people);
    }
}
