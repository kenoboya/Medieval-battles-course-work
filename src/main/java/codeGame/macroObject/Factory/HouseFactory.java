package codeGame.macroObject.Factory;

import codeGame.Main;
import codeGame.macroObject.Construction;
import codeGame.macroObject.House;

public class HouseFactory implements BuldingFactory{
    @Override
    public Construction createBulding(String name, double x, double y) {
        return new House(name,x,y);
    }
    public static void createAlreadyBulding() {
        Main.houseList.add(new House("First house",2350,150));
        Main.houseList.add(new House("Second house",2350,1200));
        Main.houseList.add(new House("Third house",150,150));
        Main.houseList.add(new House("Fourth house",150,1200));
        Main.houseList.add(new House("Fifth house",1300,700));
    }
}
