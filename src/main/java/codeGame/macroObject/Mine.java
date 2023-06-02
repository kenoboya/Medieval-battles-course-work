package codeGame.macroObject;

import codeGame.Initialization;
import codeGame.Main;
import codeGame.microObject.Team;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.Serializable;

public class Mine extends Construction implements Serializable
{
    private volatile boolean cart = false;
    protected Building type = Building.MINE;
    private Text name = new Text();
    protected Circle circle;
    private String team = "WHITE";
    public Mine(String name, double x, double y)
    {
        this.x = x;
        this.y = y;
        this.name.setText(name);
        setImageView();
        setName();
        setCircle();
        Main.group.getChildren().addAll(setGroup());
        Initialization.writeToFile("A macro object has been created to: " + name);
    }
    public double getX() {return x;}
    public double getY() {return y;}
    public String getName() {return name.getText();}

    public boolean isCart() {
        return cart;
    }
    public void setCart(boolean status) {
        cart = status;
    }
    private void setImageView()
    {
        imageView = new ImageView(
                new Image(Main.class.getResource("mine.png").toString(),0,0,false,false));
        imageView.setX(x); imageView.setY(y);
    }
    private void setName()
    {
        name.setFont(Font.font("Verdana", FontWeight.BOLD,24));
        name.setLayoutX(x + 125);
        name.setLayoutY(y + 330);
        name.setStroke(Color.WHITE);
    }
    private Group setGroup()
    {
        group = new Group(circle,imageView,name);
        return group;
    }
    private void setCircle()
    {
        circle = new Circle(x + 200.0,y + 175.0,225);
        circle.setFill(Color.TRANSPARENT);
        circle.setStrokeWidth(4);
        circle.setStrokeType(StrokeType.INSIDE);
        circle.setStroke(Color.WHITE);

    }
    public void seizeMine(String team)
    {
        if(team.equalsIgnoreCase(Team.RED.toString())) {
            circle.setStroke(Color.RED);
            this.team = Team.RED.toString();
        }
        else if(team.equalsIgnoreCase(Team.GREEN.toString())) {
            circle.setStroke(Color.GREEN);
            this.team = Team.GREEN.toString();
        }
    }
    @Override
    public Building getType(){return this.type;}
    public Circle getCircle(){return this.circle;}
    public String getTeam(){return team;}
}
