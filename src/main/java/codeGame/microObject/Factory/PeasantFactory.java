package codeGame.microObject.Factory;

import codeGame.Main;
import codeGame.microObject.Peasant;
import codeGame.microObject.Team;
public class PeasantFactory implements PeopleFactory
{
    @Override
    public void createPeople(String name, int age, String team, double x, double y)
    {
            if(team.equalsIgnoreCase(Team.RED.toString()))
            {
                Main.armyRed.add(new Peasant(name,age, Team.RED.toString(),x,y));
            }
            else
            {
                Main.armyGreen.add(new Peasant(name,age, Team.GREEN.toString(),x,y));
            }
    }
    public static void createAlreadyPeople() {
        Main.armyGreen.add(new Peasant("San",27,Team.GREEN.toString(),2000, 1100));
        Main.armyRed.add(new Peasant("Dan",44,Team.RED.toString(),700, 1100));
        Main.armyRed.add(new Peasant("Zaur",29,Team.RED.toString(),2500, 500));
        Main.armyGreen.add(new Peasant("Fabrio",26,Team.GREEN.toString(),2000, 700));
        Main.armyRed.add(new Peasant("Erjan",42,Team.RED.toString(),2500, 800));
        Main.armyGreen.add(new Peasant("Oleg",81,Team.GREEN.toString(),2325, 1325));
    }
}
