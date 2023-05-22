package codeGame.microObject;

import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public abstract class AbstractPeople
{
    int HP,age;
    double DAMAGE;
    volatile double  x,y;
    boolean dead;
    ImageView viewObject,healthView;
    Image imageObject;
    Group group, groupHP;
    String team,name;
    String pathRED,pathGREEN;
    Label label;
    Rectangle lineRectangle = new Rectangle();
    abstract public void dead();
    abstract public boolean attackUnit(double damage);
    abstract public TranslateTransition walk();
    abstract public void takePeople();
    abstract public void letGoPeople();
}
