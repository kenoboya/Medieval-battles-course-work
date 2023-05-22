package codeGame;

import codeGame.action.Movement;
import codeGame.action.interactionWithEachPeople;
import codeGame.action.intersection;
import codeGame.action.moveStage;
import codeGame.dialogs.*;
import codeGame.microObject.Cart;
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
                interactionWithEachPeople.clonePeople();
            }
            else {
                switch (key.getCode()) {
                    case F1 -> createPeopleDialog.display();
                    case F2 -> deletePeopleDialog.display();
                    case F3 -> changePeopleParameterDialog.display();
                    case F4 -> infoAboutPeopleDialog.display();
                    case F5 -> getPeopleBelongingToMacroObject.display(true);
                    case F6 -> getPeopleBelongingToMacroObject.display(false);
                    case F7 -> searchPeopleDialog.display();

                    case NUMPAD0 -> Movement.walk();
                    case E -> Peasant.stand();
                    case Q -> Peasant.walkAgain();
                    case F -> Peasant.returnToTheFortress();
                    case J -> Movement.clearCorpse();

                    case NUMPAD6 -> interactionWithEachPeople.selectedPeopleMoveRIGHT();
                    case NUMPAD4 -> interactionWithEachPeople.selectedPeopleMoveLEFT();
                    case NUMPAD8 -> interactionWithEachPeople.selectedPeopleMoveUP();
                    case NUMPAD2 -> interactionWithEachPeople.selectedPeopleMoveDOWN();
                    case NUMPAD5 -> interactionWithEachPeople.letGoPeople();

                    case G -> interactionWithEachPeople.putFlag();
                    case M -> interactionWithEachPeople.createCart();

                    case DELETE -> interactionWithEachPeople.clear();
                    // Для 3-й лабы (просто другая комбинация клавиш)
                    case INSERT -> createPeopleDialog.display();
                    case ESCAPE -> interactionWithEachPeople.letGoPeople();
                    case F10 -> System.exit(0);

                    case UP -> moveStage.upStage();
                    case DOWN -> moveStage.downStage();
                    case RIGHT -> moveStage.rightStage();
                    case LEFT -> moveStage.leftStage();

                    case Y -> intersection.startIntersactionMacro();
                    case U -> intersection.stopIntersactionMacro();

                    case K -> moveStage.timeline();

                    case ADD -> Sound.playAgainSoundTrack();
                    case SUBTRACT -> Sound.stopSoundTrack();
                }
            }
        });
    }
}
