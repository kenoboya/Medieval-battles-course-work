package codeGame;

import codeGame.action.Movement;
import codeGame.action.InteractionWithEachPeople;
import codeGame.action.Intersection;
import codeGame.action.MoveStage;
import codeGame.dialogs.*;
import codeGame.microObject.Peasant;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

public class Control
{
    public static void control()
    {
        Main.scene.setOnKeyPressed((key) ->
        {
            KeyCodeCombination combination = new KeyCodeCombination(KeyCode.V, KeyCombination.CONTROL_DOWN);
            if(combination.match(key)) {
                InteractionWithEachPeople.clonePeople();
            }
            else {
                switch (key.getCode()) {
                    case F1 -> CreatePeopleDialog.display();
                    case F2 -> DeletePeopleDialog.display();
                    case F3 -> ChangePeopleParameterDialog.display();
                    case F4 -> InfoAboutPeopleDialog.display();
                    case F5 -> GetPeopleBelongingToMacroObject.display(true);
                    case F6 -> GetPeopleBelongingToMacroObject.display(false);
                    case F7 -> SearchPeopleDialog.display();
                    case F8 -> SaveGameDialog.display();
                    case F9 -> OpenSaveDialog.display();

                    case NUMPAD0 -> Movement.walk();
                    case E -> Peasant.stand();
                    case Q -> Peasant.walkAgain();
                    case F -> Peasant.returnToTheFortress();
                    case J -> Movement.clearCorpse();

                    case NUMPAD6 -> InteractionWithEachPeople.selectedPeopleMoveRIGHT();
                    case NUMPAD4 -> InteractionWithEachPeople.selectedPeopleMoveLEFT();
                    case NUMPAD8 -> InteractionWithEachPeople.selectedPeopleMoveUP();
                    case NUMPAD2 -> InteractionWithEachPeople.selectedPeopleMoveDOWN();
                    case NUMPAD5 -> InteractionWithEachPeople.letGoPeople();

                    case G -> InteractionWithEachPeople.putFlag();
                    case H -> InteractionWithEachPeople.createCart();

                    case DELETE -> InteractionWithEachPeople.clear();
                    // Для 3-й лабы (просто другая комбинация клавиш)
                    case INSERT -> CreatePeopleDialog.display();
                    case ESCAPE -> InteractionWithEachPeople.letGoPeople();
                    case F10 -> System.exit(0);

                    case UP -> MoveStage.upStage();
                    case DOWN -> MoveStage.downStage();
                    case RIGHT -> MoveStage.rightStage();
                    case LEFT -> MoveStage.leftStage();

                    case Y -> Intersection.startIntersactionMacro();
                    case U -> Intersection.stopIntersactionMacro();

                    case M -> MoveStage.statusMiniMap();

                    case ADD -> Sound.playAgainSoundTrack();
                    case SUBTRACT -> Sound.stopSoundTrack();

                }
            }
        });
    }
}
