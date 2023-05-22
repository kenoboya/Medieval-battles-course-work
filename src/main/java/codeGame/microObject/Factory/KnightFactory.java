package codeGame.microObject.Factory;

import codeGame.Main;
import codeGame.microObject.Knight;
import codeGame.microObject.Team;
public class KnightFactory implements PeopleFactory {
    @Override
    public void createPeople(String name, int age, String team, double x, double y) {
            if(team.equalsIgnoreCase(Team.RED.toString()))
            {
                Main.armyRed.add(new Knight(name,age,Team.RED.toString(),x,y));
            }
            else
            {
                Main.armyGreen.add(new Knight(name,age,Team.GREEN.toString(),x,y));
            }
    }
    public static void createAlreadyPeople() {
        Main.armyGreen.add(new Knight("Max",23,Team.GREEN.toString(), 1500, 550));
        Main.armyRed.add(new Knight("Ragnar",43,Team.RED.toString(), 1400, 1000));
        Main.armyGreen.add(new Knight("Diego",44,Team.GREEN.toString(), 200, 400));
        Main.armyRed.add(new Knight("Hans",33,Team.RED.toString(), 500, 1300));
        Main.armyRed.add(new Knight("Bjorn",59,Team.RED.toString(), 1600, 1000));
    }
}
