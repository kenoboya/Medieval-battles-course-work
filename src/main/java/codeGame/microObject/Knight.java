package codeGame.microObject;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
public class Knight extends Warrior
{
    private int chance; // Рандомное число.

    private int HP = 500;
    private final double DAMAGE = 100;

    public Knight(String name, int age, String team, double _x, double _y)
    {
        super(name,age,team,_x,_y);
        super.setHP(this.HP);
        super.setDAMAGE(this.DAMAGE);
    }
    @Override
    void setPath()
    {
        super.pathGREEN = "D:\\project\\game\\src\\main\\java\\codeGame\\image\\knight_green.png";
        super.pathRED = "D:\\project\\game\\src\\main\\java\\codeGame\\image\\knight_red.png";
        setPath(pathGREEN,pathRED);
    }
    @Override
    public void setHP() {
        String pathToHP = "D:\\project\\game\\src\\main\\java\\codeGame\\image\\health.png";
        super.groupHP =  new Group();
        for(int i = 0,dX = 35; i < 5; i++,dX+=20) {
            super.healthView = new ImageView(new Image(pathToHP));
            super.healthView.setX(x + dX);
            super.healthView.setY(y - 5);
            super.groupHP.getChildren().addAll(super.healthView);
        }
    }
    public void defend() // Защищаться щитом (отличие от война)
    {
        // Выпадает рандомное число, и если оно положительное к примеру. Рыцарь отражает атаку.
    }
}
