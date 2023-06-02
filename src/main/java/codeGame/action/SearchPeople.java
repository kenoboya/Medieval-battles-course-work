package codeGame.action;

import codeGame.Initialization;
import codeGame.Main;
import codeGame.microObject.Peasant;
public class SearchPeople
{
    public static void search(Peasant people)
    {
        Initialization.writeToFile("The search for a micro-object was started to: " + people.getName());
        if(people.getX() <= 1400 && people.getY() <= 650)
        {
            Main.group.setLayoutX(0);
            Main.group.setLayoutY(0);
            MoveStage.userX_RIGHT = MoveStage.minX;
            MoveStage.userY_RIGHT = MoveStage.minY;
            MoveStage.userX_LEFT = MoveStage.minX;
            MoveStage.userY_LEFT = MoveStage.minY;
            MoveStage.miniMap.setX(0);
            MoveStage.miniMap.setY(302);
            MoveStage.rectangle.setX(0);
            MoveStage.rectangle.setY(302);
        }
        else if(people.getX() > 1400 && people.getY() <= 650)
        {
            Main.group.setLayoutX(-1400);
            Main.group.setLayoutY(0);
            MoveStage.userX_RIGHT = MoveStage.sizeX;
            MoveStage.userY_RIGHT = MoveStage.minX;
            MoveStage.userX_LEFT = MoveStage.sizeX;
            MoveStage.userY_LEFT = MoveStage.minY;
            MoveStage.miniMap.setX(1400);
            MoveStage.miniMap.setY(302);
            MoveStage.rectangle.setX(1638);
            MoveStage.rectangle.setY(302);
        }
        else if(people.getX() <= 1400 && people.getY() > 650)
        {
            Main.group.setLayoutX(0);
            Main.group.setLayoutY(-800);
            MoveStage.userX_RIGHT = MoveStage.minX;
            MoveStage.userY_RIGHT = MoveStage.sizeY;
            MoveStage.userX_LEFT = MoveStage.minX;
            MoveStage.userY_LEFT = MoveStage.sizeY;
            MoveStage.miniMap.setX(0);
            MoveStage.miniMap.setY(1100);
            MoveStage.rectangle.setX(0);
            MoveStage.rectangle.setY(1358);
        }
        else if(people.getX() > 1400 && people.getY() > 650)
        {

            Main.group.setLayoutX(-1400);
            Main.group.setLayoutY(-800);
            MoveStage.userX_RIGHT = MoveStage.sizeX;
            MoveStage.userY_RIGHT = MoveStage.sizeY;
            MoveStage.userX_LEFT = MoveStage.sizeX;
            MoveStage.userY_LEFT = MoveStage.sizeY;
            MoveStage.miniMap.setX(1400);
            MoveStage.miniMap.setY(1100);
            MoveStage.rectangle.setX(1638);
            MoveStage.rectangle.setY(1358);
        }
        people.takePeople();
        InteractionWithEachPeople.selected.add(people);
    }
}
