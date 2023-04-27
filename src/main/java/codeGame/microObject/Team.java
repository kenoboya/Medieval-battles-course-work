package codeGame.microObject;

public enum Team
{
    RED("RED"),GREEN("GREEN");
    String team;
    Team(String team) {this.team = team;}
    public String getType() {return team;}
    @Override
    public String toString(){return team;}
}
