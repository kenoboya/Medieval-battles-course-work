package codeGame.macroObject;

import codeGame.Main;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class House
{
    protected Bulding type = Bulding.HOUSE;
    private Text name = new Text();
    private Group group;
    private ImageView imageView;
    private double x,y;

    public House(String name, double x, double y)
    {
        this.x = x;
        this.y = y;
        this.name.setText(name);
        setImageView();
        setName();
        Main.group.getChildren().addAll(setGroup());
    }
    public double getX() {return x;}
    public double getY() {return y;}
    public String getName() {return name.getText();}
    private void setImageView()
    {
        imageView = new ImageView(
                new Image("D:\\project\\game\\src\\main\\java\\codeGame\\image\\house.png"));
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
        group = new Group(imageView,name);
        return group;
    }
}
