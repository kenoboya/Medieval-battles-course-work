package codeGame.macroObject.Factory;

import codeGame.Main;
import codeGame.macroObject.Construction;
import codeGame.macroObject.Mine;

public class MineFactory implements BuldingFactory{
    @Override
    public Construction createBulding(String name, double x, double y) {
        return new Mine(name,x,y);
    }
    public static void createAlreadyBulding() {
        Main.mineList.add(new Mine("First mine",1250,250));
        Main.mineList.add(new Mine("Second mine",1800,1100));
        Main.mineList.add(new Mine("Third mine",700,1100));
    }
}
