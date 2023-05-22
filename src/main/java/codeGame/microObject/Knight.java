package codeGame.microObject;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
public class Knight extends Warrior
{
    private Cart cart;
    private int HP = 500;
    private final double DAMAGE = 100;
    protected People type = People.KNIGHT;

    public Knight(String name, int age, String team, double _x, double _y)
    {
        super(name,age,team,_x,_y);
        super.setHP(this.HP);
        super.setDAMAGE(this.DAMAGE);
    }
    @Override
    public People getType(){return this.type;}
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
        for(int i = 0,dX = 22; i < 5; i++,dX+=15) {
            super.healthView = new ImageView(new Image(pathToHP));
            super.healthView.setX(x + dX);
            super.healthView.setY(y - 5);
            super.groupHP.getChildren().addAll(super.healthView);
        }
    }
    public void createCart()
    {
        if(cart == null)
        {
            Cart.cartList.add(Cart.builder().addTeam(super.team)
                    .setXY(getX(),getY()).build());
            cart = Cart.cartList.get(Cart.cartList.size() - 1);
        }
    }
}
