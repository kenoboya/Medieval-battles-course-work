package codeGame.action;

import codeGame.Main;

public class deadAllPeople
{
    public static void dead()
    {
        for(var el : Main.createEveryThingArmy())
        {
            el.dead();
        }
    }
}
