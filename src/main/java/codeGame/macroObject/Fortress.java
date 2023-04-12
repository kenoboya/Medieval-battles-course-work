package codeGame.macroObject;

import codeGame.Main;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;

public  final class Fortress
{
    private static final double DAMAGE = 30;
    private Group group;
    private ImageView imageView;
    private String team;
    private double x,y;
    private Circle circle;
    public Fortress(double x, double y, String team)
    {
        this.x = x;
        this.y = y;
        this.team = team;
        setImageView();
        setCircle();
        Main.group.getChildren().addAll(setGroup());

    }

    private void setImageView()
    {
        if(team.equalsIgnoreCase("RED"))
        {
            imageView = new ImageView(
                    new Image("D:\\project\\game\\src\\main\\java\\codeGame\\image\\fortress_red.png"));
            imageView.setX(x); imageView.setY(y);
        }
        else if(team.equalsIgnoreCase("GREEN"))
        {
            imageView = new ImageView(
                    new Image("D:\\project\\game\\src\\main\\java\\codeGame\\image\\fortress_green.png"));
            imageView.setX(x); imageView.setY(y);
        }
    }
    private  Group setGroup()
    {
        group = new Group(imageView,circle);
        return group;
    }
    public Group getGroup(){return group;}
    private  void setCircle()
    {
        circle = new Circle(x + 200.0,y + 175.0,200);
        circle.setFill(Color.TRANSPARENT);
        circle.setStrokeWidth(8);
        circle.setStrokeType(StrokeType.INSIDE);
        if(team.equalsIgnoreCase("RED")) {circle.setStroke(Color.RED);}
        else if(team.equalsIgnoreCase("GREEN")) {circle.setStroke(Color.GREEN);}

    }

}
