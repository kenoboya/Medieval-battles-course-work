package codeGame.microObject;

import javafx.animation.TranslateTransition;

public interface Action
{
    public void dead();
    public void attackUnit();
    public TranslateTransition walk();
    public void returnToTheFortress();
    public void seizePoint();
    public void takePeople();
    public void letGoPeople();
}
