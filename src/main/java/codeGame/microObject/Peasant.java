package codeGame.microObject;
import codeGame.Main;
import codeGame.action.Movement;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

public class Peasant extends AbstractPeople implements Action
{

    private int uniqueID = 1;
    private int HP = 100;
    private final double DAMAGE = 10.5;
    // LAB2
    public Peasant() {System.out.println("Called basic constructor");}
    static{System.out.println("A static block was called");}
    {System.out.println("A dynamic block was called");}
    // LAB2
    public Peasant(String name, int age, String team, double _x, double _y)
    {
        setXY(_x,_y);
        setAge(age);
        setName(name);
        setPath();
        setTeam(team);
        uniqueID++;
        setDAMAGE(this.DAMAGE);
        setHP(this.HP);
        setHP();
        setImageView();
        setGroup();
        Main.group.getChildren().add(getGroup());
    }
    public ImageView getImageView(){return super.viewObject;}
    public Group getGroup()
    {
        return super.group;
    }
    public int getHP()
    {
        return super.HP;
    }
    public double getDamage()
    {
        return super.DAMAGE;
    }
    public String getTeam()
    {
        return super.team;
    }
    public String getName()
    {
        return super.name;
    }
    public int getAge()
    {
        return super.age;
    }
    public double getX()
    {
        return super.x;
    }
    public double getY()
    {
        return super.y;
    }

    public void setHP()
    {
        String pathToHP = "D:\\project\\game\\src\\main\\java\\codeGame\\image\\health.png";
        super.groupHP = new Group();
        super.healthView = new ImageView(new Image(pathToHP));
        super.healthView.setX(x+73);
        super.healthView.setY(y-5);
        super.groupHP.getChildren().add(healthView);
    }
    public void setHP(int HP) {super.HP = HP;}
    public void setName(String name)
    {
        super.name = name;
        super.label = new Label(name);
        super.label.setLayoutX(x+68);
        super.label.setLayoutY(y+15);
        super.label.setTextFill(Color.FLORALWHITE);
        super.label.setFont(Font.font("Verdana", FontWeight.BOLD,15));
    }
    public void setTeam(String team) {
        super.team = team;
        if(team.equalsIgnoreCase("Red"))
        {
            imageObject = new Image(super.pathRED);
        }
        else if(team.equalsIgnoreCase("Green"))
        {
            imageObject = new Image(super.pathGREEN);
        }
    }
    private void setImageView()
    {
        super.viewObject = new ImageView(imageObject);
        super.viewObject.setX(x);
        super.viewObject.setY(y+15+5); // +15+5
    }
    private void setGroup()
    {
        super.group = new Group();
        super.group.getChildren().addAll(super.label,super.viewObject,super.groupHP); // life (Убрал полоску)
    }
    public void setAge(int age) {
        super.age = age;
    }
    void setDAMAGE(double damage) {super.DAMAGE = damage;}
    void setPath(String pathGREEN,String pathRED)
    {
        super.pathGREEN = pathGREEN;
        super.pathRED = pathRED;
    }
    void setPath()
    {
        super.pathGREEN = "D:\\project\\game\\src\\main\\java\\codeGame\\image\\peasant_green.png";
        super.pathRED = "D:\\project\\game\\src\\main\\java\\codeGame\\image\\peasant_red.png";
        setPath(pathGREEN,pathRED);
    }

    public void setXY(double _x, double _y)
    {
        super.x = _x;
        super.y = _y;
    }
    public void setX(double _x) {super.x = _x;}
    public void setY(double _y) {super.y = _y;}
    @Override
    public String toString()
    {
        return "@" +getClass().getName() + " Name: " + name + " Age: " + age + " Team: " + team;
    }
    @Override
    public boolean equals(Object object)
    {
            if(object instanceof Peasant prototype)
            {
                return this.name.equals(prototype.name) &&
                        this.age == prototype.age &&
                        this.team.equals(prototype.team);
            }
            return false;
    }
    @Override
    public int hashCode(){return uniqueID;}
    @Override
    public void dead()
    {
        Main.group.getChildren().remove(super.group);
        super.pathRED = super.pathGREEN = "D:\\project\\game\\src\\main\\java\\codeGame\\image\\coffin.png";
        setTeam(super.team);
        setName(super.name);
        setImageView();
        Main.group.getChildren().addAll(super.label,super.viewObject);
    }
    @Override
    public void attackUnit() {}
    @Override
    public TranslateTransition walk()
    {
            TranslateTransition transition = new TranslateTransition(Duration.seconds(1));
            double randomX = Math.floor(Math.random() * Main.sizeX/2 - (Main.sizeX/2)/2);
            double randomY = Math.floor(Math.random() * Main.sizeY/2 - (Main.sizeY/2)/2);
            double newX = getX() + randomX;
            double newY = getY() + randomY;
            transition.setNode(getGroup());
            if(Main.minX <= newX && Main.minY <= newY && Main.sizeX >= newX && Main.sizeY >= newY) {
                transition.setByX(randomX);
                transition.setByY(randomY);
                setXY(newX,newY);
            }
            else {transition.setByX(0);transition.setByY(0);}
            transition.setOnFinished(e -> {
                transition.setNode(getGroup());
                double randomX2 = Math.floor(Math.random() * Main.sizeX/2 - (Main.sizeX/2)/2);
                double randomY2 = Math.floor(Math.random() * Main.sizeY/2 - (Main.sizeY/2)/2);
                double newX2 = getX() + randomX2;
                double newY2 = getY() + randomY2;
                if(Main.minX <= newX2 && Main.minY <= newY2 && Main.sizeX >= newX2 && Main.sizeY >= newY2) {
                    transition.setByX(randomX2);
                    transition.setByY(randomY2);
                    setXY(newX2,newY2);
                }
                else {transition.setByX(0); transition.setByY(0);}
                transition.playFromStart();
            });
            return transition;
    }
    public static void stand() {Movement.stopWalk();}

    public static void walkAgain() {Movement.againWalk();}
    @Override
    public void returnToTheFortress()
    {
        if(super.team.equalsIgnoreCase("Red"))
        {
            TranslateTransition transition = new TranslateTransition(Duration.seconds(1));
            transition.setNode(getGroup());
            transition.setByX(Math.floor(1245 - getX()));
            transition.setByY(Math.floor(255 - getY()));
            setXY(1245, 255);
            transition.play();
        }
        else if(super.team.equalsIgnoreCase("Green"))
        {
            TranslateTransition transition1 = new TranslateTransition(Duration.seconds(1));
            transition1.setNode(getGroup());
            transition1.setByX(Math.floor(165 - getX()));
            transition1.setByY(Math.floor(330 - getY()));
            setXY(165, 330);
            transition1.play();
        }
    }
    @Override
    public void seizePoint() {}
}
