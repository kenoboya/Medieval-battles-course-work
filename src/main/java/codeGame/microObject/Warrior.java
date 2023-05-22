package codeGame.microObject;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Warrior extends Peasant
{
    private List<Flag> flags = new ArrayList<>();
    private int HP = 250, limitFlag = 5, countFlag = 0;
    private boolean takenFlag = false;
    private final double DAMAGE = 60;
    protected People type = People.WARRIOR;
    public Warrior(String name, int age, String team, double _x, double _y)
    {

        super(name,age,team,_x,_y);
        super.setHP(this.HP);
        super.setDAMAGE(this.DAMAGE);
    }
    @Override
    void setPath()
    {
        super.pathGREEN = "D:\\project\\game\\src\\main\\java\\codeGame\\image\\warrior_green.png";
        super.pathRED = "D:\\project\\game\\src\\main\\java\\codeGame\\image\\warrior_red.png";
        setPath(pathGREEN,pathRED);
    }
    @Override
    public People getType(){return this.type;}
    public boolean isTakenFlag(){return takenFlag;}
    @Override
    public void setHP() {
        String pathToHP = "D:\\project\\game\\src\\main\\java\\codeGame\\image\\health.png";
        super.groupHP =  new Group();
        for(int i = 0,dX = 50; i < 3; i++,dX+=15) {
            super.healthView = new ImageView(new Image(pathToHP));
            super.healthView.setX(x + dX);
            super.healthView.setY(y - 5);
            super.groupHP.getChildren().addAll(super.healthView);
        }
    }
    public void putUpFlag() // Поставить флаг(Отличие от обычного человека)
    {
        if(countFlag < limitFlag) {
            Flag.flagArrayList.add(new Flag(team, x + 50, y + 100));
            flags.add(Flag.flagArrayList.get(Flag.flagArrayList.size() - 1));
            countFlag++;
        }
    }
    public void takeFlag(Flag flag)
    {
        if(!takenFlag) {
            removeGroup();
            getGroup().getChildren().add(flag.getGroup());
            addGroup();
            takenFlag = true;
            flag.takeFlag(true);
        }
    }
    public void resetLimit(){countFlag = 0;}
    public List<Flag> getListFlag() {return flags;}
}
