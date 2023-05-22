package codeGame.microObject.Factory;
import codeGame.Main;
import codeGame.microObject.Team;
import codeGame.microObject.Warrior;
public class WarriorFactory implements PeopleFactory {
    @Override
    public void createPeople(String name, int age, String team, double x, double y) {
        if(team.equalsIgnoreCase(Team.RED.toString()))
        {
            Main.armyRed.add(new Warrior(name,age, Team.RED.toString(),x,y));
        }
        else
        {
            Main.armyGreen.add(new Warrior(name,age, Team.GREEN.toString(),x,y));
        }
    }
    public static void createAlreadyPeople() {
        Main.armyRed.add(new Warrior("Danila",18,Team.RED.toString(),800, 250));
        Main.armyGreen.add(new Warrior("Arthur",54,Team.GREEN.toString(),400, 300));
        Main.armyGreen.add(new Warrior("Rollo",59,Team.GREEN.toString(),2050, 300));
        Main.armyRed.add(new Warrior("Alberto",16,Team.RED.toString(),1000, 800));
        Main.armyGreen.add(new Warrior("Bogdan",65,Team.GREEN.toString(),700, 600));
    }
}