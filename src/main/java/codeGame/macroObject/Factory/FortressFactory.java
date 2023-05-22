package codeGame.macroObject.Factory;

import codeGame.Main;
import codeGame.macroObject.Construction;
import codeGame.macroObject.Fortress;
import codeGame.microObject.Team;

public class FortressFactory implements BuldingFactory {
    @Override
    public Construction createBulding(String name, double x, double y) {return new Fortress(name,x,y);}
    public static void createAlreadyBulding() {
        Main.FORTRESS_RED = new Fortress(Team.RED.toString(),2250, 655); // X120 Y110
        Main.FORTRESS_GREEN = new Fortress(Team.GREEN.toString(),200, 655); // X120 Y110

    }
}
