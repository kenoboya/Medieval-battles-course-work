package codeGame.macroObject;

import codeGame.Initialization;
import codeGame.Main;
import codeGame.action.InsideMacroObject;
import codeGame.microObject.Team;
import javafx.scene.Group;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.Serializable;

public class Fortress extends Construction implements Serializable
{
    protected Building type = Building.FORTRESS;
    private int POINT = 0;
    private static final double DAMAGE = 30;
    private String team;
    protected Text textTeam;
    private Circle circle;
    public Fortress(String team,double x, double y)
    {
        this.x = x;
        this.y = y;
        this.team = team;
        setImageView();
        setCircle();
        setTextTeam();
        Main.group.getChildren().addAll(setGroup());
        Initialization.writeToFile("A macro object has been created to: " + team + " FORTRESS");
    }
    public static double getDamage(){return DAMAGE;}
    public double getX() {return x;}
    public double getY() {return y;}
    public String getTeam(){return team;}
    public Circle getCircle(){return circle;}
    private void setTextTeam()
    {
        Glow glow = new Glow();
        glow.setLevel(5);
        if(team.equalsIgnoreCase(Team.RED.toString()))
        {
            textTeam = new Text("RED FORTRESS");
            textTeam.setFont(Font.font("Arial Black", FontWeight.BLACK,18));
            textTeam.setLayoutX(x + 125);
            textTeam.setLayoutY(y + 330);
            textTeam.setStroke(Color.DARKRED);
            textTeam.setEffect(glow);
        }
        else if(team.equalsIgnoreCase(Team.GREEN.toString()))
        {
            textTeam = new Text("GREEN FORTRESS");
            textTeam.setFont(Font.font("Arial Black", FontWeight.BLACK,18));
            textTeam.setLayoutX(x + 125);
            textTeam.setLayoutY(y + 330);
            textTeam.setStroke(Color.DARKGREEN);
            textTeam.setEffect(glow);
        }
    }
    private void setImageView()
    {
        if(team.equalsIgnoreCase(Team.RED.toString()))
        {
            imageView = new ImageView(
                    new Image(Main.class.getResource("fortress_red.png").toString()));
            imageView.setX(x); imageView.setY(y);
        }
        else if(team.equalsIgnoreCase(Team.GREEN.toString()))
        {
            imageView = new ImageView(
                    new Image(Main.class.getResource("fortress_green.png").toString()));
            imageView.setX(x); imageView.setY(y);
        }
        imageView.setOnMouseClicked(InsideMacroObject.getHandler());
    }
    private Group setGroup()
    {
        group = new Group(circle,imageView,textTeam);
        return group;
    }
    private void setCircle()
    {
        circle = new Circle(x + 200.0,y + 175.0,200);
        circle.setFill(Color.TRANSPARENT);
        circle.setStrokeWidth(4);
        circle.setStrokeType(StrokeType.INSIDE);
        if(team.equalsIgnoreCase(Team.RED.toString())) {circle.setStroke(Color.RED);}
        else if(team.equalsIgnoreCase(Team.GREEN.toString())) {circle.setStroke(Color.GREEN);}

    }
    public void setInside()
    {
        if(insidePeople.isEmpty()) {inside = false;}
        else{inside = true;}
    }
    public boolean getInside()
    {
        return inside;
    }
    @Override
    public Building getType(){return this.type;}
    public int getPOINT(){return POINT;}
    public void setPOINT(int POINT){this.POINT = POINT;}
}
