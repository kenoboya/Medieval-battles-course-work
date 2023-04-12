package codeGame.microObject;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public abstract class AbstractPeople
{
    ImageView healthView;
    int HP;
    Group group;
    Group groupHP;
    Image imageObject;
    ImageView viewObject;
    double DAMAGE;
    String team;
    Label label;
    String name;
    int age;
    volatile double  x,y;
    String pathRED;
    String pathGREEN;
    Rectangle lineRectangle = new Rectangle();
    boolean dead;
}
