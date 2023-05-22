package codeGame.microObject;

import java.util.ArrayList;
import java.util.Arrays;

public enum People
{
    PEASANT("Peasant"),WARRIOR("Warrior"),KNIGHT("Knight");
    private String type;
    People(String type) {this.type = type;}
    public String getType() {return type;}
    @Override
    public String toString(){return type;}
    public static ArrayList<String> getAllEnum() {
        return new ArrayList<>(Arrays.asList(PEASANT.toString(),WARRIOR.toString(),KNIGHT.toString()));
    }
}
