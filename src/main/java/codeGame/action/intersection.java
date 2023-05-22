package codeGame.action;

import codeGame.Initialization;
import codeGame.Main;
import codeGame.Sound;
import codeGame.macroObject.Building;
import codeGame.macroObject.Fortress;
import codeGame.microObject.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class intersection
{
    private static boolean stop = false;
    private static Timeline[] timeline = new Timeline[7];
    public static void intersectionWithFortress()
    {
        timeline[0] = new Timeline(new KeyFrame(Duration.millis(500), actionEvent ->
        {
            for(var el : Initialization.createEveryThingArmy())
            {
                if(Main.FORTRESS_RED.getImageView().getBoundsInParent()
                        .intersects(el.getGroup().getBoundsInParent()))
                {
                    if(el.getTeam().equalsIgnoreCase(Team.GREEN.toString()))
                    {
                        if(!el.getStatusDead()) {
                            Sound.playSound("D:\\project\\game\\src\\main\\java\\codeGame\\sound\\attackFortress.mp3");
                        }
                        el.attackUnit(Fortress.getDamage());
                    }
                }
                else if(Main.FORTRESS_GREEN.getImageView().getBoundsInParent()
                        .intersects(el.getGroup().getBoundsInParent()))
                {
                    if(el.getTeam().equalsIgnoreCase(Team.RED.toString()))
                    {
                        if(!el.getStatusDead()) {
                            Sound.playSound("D:\\project\\game\\src\\main\\java\\codeGame\\sound\\attackFortress.mp3");
                        }
                        el.attackUnit(Fortress.getDamage());
                    }
                }
            }
        }));
       timeline[0].setCycleCount(Timeline.INDEFINITE);
       statusCheck();
    }
    private static void statusCheck()
    {
        if(stop) {stopIntersactionMacro();}
        else if(!stop){startIntersactionMacro();}
    }
    public static void stopIntersactionMacro()
    {
        try {
            stop = true;
            timeline[0].stop();
        }
        catch (Exception ex) {intersectionWithFortress();}
    }
    public static void startIntersactionMacro()
    {
        try {
            timeline[0].play();
            stop = false;
        }
        catch (Exception ex) {intersectionWithFortress();}
    }
    public static void intersectionAttackFlag()
    {
        timeline[1] = new Timeline(new KeyFrame(Duration.millis(500), actionEvent ->
        {
            Flag flagDelete = null;
            for(var el : Initialization.createEveryThingArmy())
            {
               for(var flag : Flag.flagArrayList)
               {
                   if(el.getGroup().getBoundsInParent()
                           .intersects(flag.getGroup().getBoundsInParent()))
                   {
                       if(!el.getTeam().equalsIgnoreCase(flag.getTeam()))
                       {
                           Main.group.getChildren().remove(flag.getGroup());
                           flagDelete = flag;
                       }
                       else
                       {
                           if(el.getType() != People.PEASANT)
                           {
                               Warrior aboveSecondLevel = (Warrior)el;
                               if(!aboveSecondLevel.getListFlag().contains(flag)
                                       && !aboveSecondLevel.isTakenFlag()) {
                                   flag.setXY(aboveSecondLevel.getImageView().getX() + 125,
                                           aboveSecondLevel.getImageView().getY() + 50);
                                   aboveSecondLevel.takeFlag(flag);
                               }
                           }
                       }
                   }
               }
               try{Flag.flagArrayList.remove(flagDelete);}
               catch(Exception ex){}
            }
        }));
        timeline[1].setCycleCount(Timeline.INDEFINITE);
        timeline[1].play();
    }
    public static void intersectionFlagWithMacroObject()
    {
        timeline[2] = new Timeline(new KeyFrame(Duration.millis(1000), actionEvent ->
        {
            for(var mine : Main.mineList)
            {
                for(var flag : Flag.flagArrayList)
                {
                    if(flag.getGroup().getBoundsInParent()
                            .intersects(mine.getImageView().getBoundsInParent()))
                    {
                        mine.seizeMine(flag.getTeam());
                    }
                }
            }
        }));
        timeline[2].setCycleCount(Timeline.INDEFINITE);
        timeline[2].play();
    }
    public static void intersectionAttackEnemy()
    {
        timeline[3] = new Timeline(new KeyFrame(Duration.millis(1000), actionEvent ->
        {
            for(var first : Initialization.createEveryThingArmy())
            {
                for(var second : Initialization.createEveryThingArmy())
                {
                    if (!(first.getTeam().equalsIgnoreCase(second.getTeam()))) {
                        if (first.getX() <= second.getX() && first.getY() <= second.getY()) {
                            if (first.getX() + 125 > second.getX() && first.getY() + 175 > second.getY()) {
                                if (!((first.getStatusDead() || second.getStatusDead())
                                        || (first.getStatusDead() && second.getStatusDead()))) {
                                    Sound.playSound("D:\\project\\game\\src\\main\\java\\codeGame\\sound\\attackPeople.mp3");
                                    first.attackUnit(second.getDamage());
                                    second.attackUnit(first.getDamage());
                                }
                            }
                        } else if (first.getX() >= second.getX() && first.getY() >= second.getY()) {
                            if (first.getX() - 125 < second.getX() && first.getY() - 175 < second.getY()) {
                                if (!((first.getStatusDead() || second.getStatusDead())
                                        || (first.getStatusDead() && second.getStatusDead()))) {
                                    Sound.playSound("D:\\project\\game\\src\\main\\java\\codeGame\\sound\\attackPeople.mp3");
                                    first.attackUnit(first.getDamage());
                                    second.attackUnit(second.getDamage());
                                }
                            }
                        }
                    }
                }
            }
        }));
        timeline[3].setCycleCount(Timeline.INDEFINITE);
        timeline[3].play();
    }
    public static void intersectionWithHouse() {
        timeline[4] = new Timeline(new KeyFrame(Duration.millis(50), actionEvent ->
        {
            for (var el : Initialization.createEveryThingArmy()) {
                for (var house : Main.houseList) {
                    if (el.getType() != People.PEASANT) {
                        if (el.getGroup().getBoundsInParent().intersects(house.getImageView().getBoundsInParent())) {
                            house.makingFlag((Warrior) el);
                        }
                        else if(house.getFinishedList().contains(el))
                        {
                            house.leftHome((Warrior) el);
                        }

                    }
                }
            }
        }));
        timeline[4].setCycleCount(Timeline.INDEFINITE);
        timeline[4].play();
    }
    public static void CartIntersectionWithMine() {
        timeline[5] = new Timeline(new KeyFrame(Duration.millis(50), actionEvent ->
        {
            for(var mine : Main.mineList)
            {
                for(var cart : Cart.cartList) {
                    if (mine.getTeam().equalsIgnoreCase(cart.getTeam())) {
                        if (mine.getImageView().getBoundsInParent().intersects(cart.getGroup().getBoundsInParent())) {
                            if (!cart.getCollects() && !(cart.getCountCoal() >= cart.getLimitCoal()))
                            {
                                cart.startCollect();
                            }
                        }
                        else if (cart.getCountCoal() == 0) {cart.moveTo(mine);}
                    }
                    if (cart.getTeam().equalsIgnoreCase(Team.RED.toString()))
                    {
                        if (!Main.FORTRESS_RED.getImageView().getBoundsInParent()
                                .intersects(cart.getGroup().getBoundsInParent()))
                        {
                            if (cart.getCountCoal() >= cart.getLimitCoal()) {
                                cart.moveTo(Main.FORTRESS_RED);
                            }
                        }
                        else
                        {
                            cart.ShipmentAtTheCastle(Main.FORTRESS_RED);
                        }
                    }
                    else if (cart.getTeam().equalsIgnoreCase(Team.GREEN.toString()))
                    {
                        if (!Main.FORTRESS_GREEN.getImageView().getBoundsInParent()
                                .intersects(cart.getGroup().getBoundsInParent()))
                        {
                            if (cart.getCountCoal() >= cart.getLimitCoal())
                            {
                                cart.moveTo(Main.FORTRESS_GREEN);
                            }
                        }
                        else
                        {
                            cart.ShipmentAtTheCastle(Main.FORTRESS_GREEN);
                        }
                    }
                }
            }
        }));
        timeline[5].setCycleCount(Timeline.INDEFINITE);
        timeline[5].play();
    }
    public static void belongingToMacro()
    {
        timeline[6] = new Timeline(new KeyFrame(Duration.millis(50), actionEvent ->
        {
            for(var macro : Initialization.createEveryThingMacro())
            {
                for(var el : Initialization.createEveryThingArmy()) {
                    if (macro.getType() != Building.FORTRESS &&
                            macro.getImageView().getBoundsInParent()
                            .intersects(el.getGroup().getBoundsInParent()))
                    {if(!macro.insidePeople.contains(el)) {macro.insidePeople.add(el);}}
                    else {macro.insidePeople.remove(el);}
                }
            }
        }));
        timeline[6].setCycleCount(Timeline.INDEFINITE);
        timeline[6].play();
    }
}
