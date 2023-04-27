package codeGame.microObject;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
public class Warrior extends Peasant
{
    private int HP = 250;
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
    @Override
    public void setHP() {
        String pathToHP = "D:\\project\\game\\src\\main\\java\\codeGame\\image\\health.png";
        super.groupHP =  new Group();
        for(int i = 0,dX = 67; i < 3; i++,dX+=20) {
            super.healthView = new ImageView(new Image(pathToHP));
            super.healthView.setX(x + dX);
            super.healthView.setY(y - 5);
            super.groupHP.getChildren().addAll(super.healthView);
        }
    }
    public void putUpFlag() // Поставить флаг(Отличие от обычного человека)
    {

    }
}
