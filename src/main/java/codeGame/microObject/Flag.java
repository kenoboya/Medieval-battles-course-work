package codeGame.microObject;

import codeGame.Main;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.Serializable;
import java.util.ArrayList;

public class Flag implements Serializable
{
    public static ArrayList<Flag> flagArrayList = new ArrayList<>();
    private double x,y;
    private String team;
    private ImageView imageView;
    private boolean taken = false;

    private Group group;

    public Flag(String team, double x, double y)
    {
        this.team = team;
        this.x = x;
        this.y = y;
        setImageView();
        Main.group.getChildren().add(setGroup());
    }

    private void setImageView()
    {
        if(team.equalsIgnoreCase(Team.RED.toString()))
        {

            imageView = new ImageView(
                    new Image(Main.class.getResource("flag_red.png").toString()));
            imageView.setX(x); imageView.setY(y);
        }
        else if(team.equalsIgnoreCase(Team.GREEN.toString()))
        {
            imageView = new ImageView(
                    new Image(Main.class.getResource("flag_green.png").toString()));
            imageView.setX(x); imageView.setY(y);
        }
    }
    private Group setGroup() {
        group = new Group(imageView);
        return group;
    }
    public String getTeam() {return team;}
    public Group getGroup() {return group;}
    void removeGroup() {Main.group.getChildren().remove(group);}
    void addGroup() {Main.group.getChildren().add(group);}

    public void setXY(double X, double Y)
    {
        if(!taken) {
            this.x = X;
            this.y = Y;
            removeGroup();
            imageView.setX(x);
            imageView.setY(y);
            addGroup();
        }
    }
    public void takeFlag(boolean taken) {this.taken = taken;}
}
