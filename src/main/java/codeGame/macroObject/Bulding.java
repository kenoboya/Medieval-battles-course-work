package codeGame.macroObject;

public enum Bulding
{
    FORTRESS("Fortress"),HOUSE("House"),MINE("Mine");
    private String type;
    Bulding(String type) {this.type = type;}
    public String getType() {return type;}
    @Override
    public String toString(){return type;}
}
