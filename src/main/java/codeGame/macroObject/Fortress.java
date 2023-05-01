package codeGame.macroObject;

import codeGame.Main;
import codeGame.action.insideMacroObject;
import codeGame.microObject.Peasant;
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

import java.util.ArrayList;

public final class Fortress
{
    protected Bulding type = Bulding.FORTRESS;
    public boolean inside = false;
    public ArrayList<Peasant> insidePeople = new ArrayList<>();
    private static final double DAMAGE = 30;
    private Group group;
    private ImageView imageView;
    private String team;
    private double x,y;

    protected Text textTeam;
    private Circle circle;
    public Fortress(double x, double y, String team)
    {
        this.x = x;
        this.y = y;
        this.team = team;
        setImageView();
        setCircle();
        setTextTeam();
        Main.group.getChildren().addAll(setGroup());

    }
    public double getX() {return x;}
    public double getY() {return y;}
    public String getTeam(){return team;}
    public ImageView getImageView(){return this.imageView;}
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
                    new Image("D:\\project\\game\\src\\main\\java\\codeGame\\image\\fortress_red.png"));
            imageView.setX(x); imageView.setY(y);
        }
        else if(team.equalsIgnoreCase(Team.GREEN.toString()))
        {
            imageView = new ImageView(
                    new Image("D:\\project\\game\\src\\main\\java\\codeGame\\image\\fortress_green.png"));
            imageView.setX(x); imageView.setY(y);
        }
        imageView.setOnMouseClicked(insideMacroObject.getHandler());
    }
    private Group setGroup()
    {
        group = new Group(circle,imageView,textTeam);
        return group;
    }
    public Group getGroup(){return group;}
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
    public Bulding getType(){return this.type;}
}
