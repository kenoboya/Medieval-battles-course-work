package codeGame.microObject;

import javafx.animation.TranslateTransition;

public interface Action
{
    public void dead();
    public boolean attackUnit(double damage);
    public TranslateTransition walk();
    public void seizePoint();
    public void takePeople();
    public void letGoPeople();
}
