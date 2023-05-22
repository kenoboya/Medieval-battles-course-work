package codeGame.macroObject;

public enum Building
{
    FORTRESS("Fortress"),HOUSE("House"),MINE("Mine");
    private String type;
    Building(String type) {this.type = type;}
    public String getType() {return type;}
    @Override
    public String toString(){return type;}
}
