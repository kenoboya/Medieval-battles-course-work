package codeGame.action;

import codeGame.Initialization;
import codeGame.Main;
import codeGame.microObject.Peasant;
import codeGame.microObject.Team;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Save {
    public static List<String> nameSave = new ArrayList<>();

    public static HashMap<String, ArrayList<Peasant>> saveMap = new HashMap<>();

    public static void save(String name) {
        name += ".txt";
        try (ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(name))) {
            saveMap.put(name, Initialization.createEveryThingArmy());
            nameSave.add(name);
            for (var el : Initialization.createEveryThingArmy()) {
                el.save(saveMap);
                writer.writeObject(el);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не был найден:");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void loadSave(String name) {
        try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(name))) {
            ArrayList<Peasant> allPeopleOld = saveMap.get(name);
            for (var el : allPeopleOld) {
                Main.group.getChildren().remove(el.getGroup());
                if (Main.armyGreen.contains(el)) {
                    int index = Main.armyGreen.indexOf(el);
                    el.setOldCordinate(Main.armyGreen.get(index).getOldCordinates(saveMap));
                    Main.armyGreen.set(index, el);
                    Main.group.getChildren().add(el.getGroup());
                } else if (el.getTeam().equals(Team.GREEN.toString())) {
                    Main.armyGreen.add(el);
                    Main.group.getChildren().add(el.getGroup());
                } else if (Main.armyRed.contains(el)) {
                    int index = Main.armyRed.indexOf(el);
                    el.setOldCordinate(Main.armyRed.get(index).getOldCordinates(saveMap));
                    Main.armyRed.set(index, el);
                    Main.group.getChildren().add(el.getGroup());
                } else if (el.getTeam().equals(Team.RED.toString())) {
                    Main.armyRed.add(el);
                    Main.group.getChildren().add(el.getGroup());
                }
            }
            /*int i = 1;
            while(true) {
                try {
                    Peasant peasant = (Peasant) reader.readObject();
                    System.out.println(i + " people: " + peasant);
                    i++;
                }
                catch(Exception ex) {
                    break;
                }
            }*/
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
