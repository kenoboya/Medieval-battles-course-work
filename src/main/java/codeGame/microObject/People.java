package codeGame.microObject;

public enum People
{
    PEASANT("Peasant"),WARRIOR("Warrior"),KNIGHT("Knight");
    private String type;
    People(String type) {this.type = type;}
    public String getType() {return type;}
    @Override
    public String toString(){return type;}
}
