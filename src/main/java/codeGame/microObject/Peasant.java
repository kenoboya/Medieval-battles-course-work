package codeGame.microObject;
import codeGame.Main;
import codeGame.action.Movement;
import codeGame.action.interactionWithEachPeople;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

public class Peasant extends AbstractPeople implements Action
{
    private int uniqueID = 1;
    private int HP = 100;
    private final double DAMAGE = 35;
    // LAB2
/*    public Peasant() {System.out.println("Called basic constructor");}
    static{System.out.println("A static block was called");}
    {System.out.println("A dynamic block was called");}*/
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
    public boolean getStatusDead(){return super.dead;}
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
        super.viewObject.setOnMouseClicked(interactionWithEachPeople.getHandler());
    }
    private void setGroup()
    {
        super.group = new Group();
        super.group.getChildren().addAll(super.label,super.viewObject,super.groupHP);
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
        try {Movement.transitionsMap.get(this).stop();/*Movement.transitionsMap.remove(this);*/}
        catch(Exception ex){};
        Main.group.getChildren().remove(super.group);
        super.pathRED = super.pathGREEN = "D:\\project\\game\\src\\main\\java\\codeGame\\image\\coffin.png";
        setTeam(super.team);
        setName(super.name);
        setImageView();
        super.group = new Group();
        super.group.getChildren().addAll(super.label,super.viewObject);
        Main.group.getChildren().addAll(super.group);
        super.dead = true;
    }

    @Override
    public boolean attackUnit(double damage)
    {
        String pathToHP = "D:\\project\\game\\src\\main\\java\\codeGame\\image\\health.png";
         super.HP -= damage;
         if(super.HP >= 301)
         {
             Main.group.getChildren().remove(super.group);
             super.group.getChildren().remove(super.groupHP);
             super.groupHP = new Group();
             for(int i = 0,dX = 35; i < 4; i++,dX+=20) {
                 super.healthView = new ImageView(new Image(pathToHP));
                 super.healthView.setX(this.viewObject.getX() + dX);
                 super.healthView.setY(this.viewObject.getY() - 25);
                 super.groupHP.getChildren().addAll(super.healthView);
             }
             super.group.getChildren().add(super.groupHP);
             Main.group.getChildren().add(super.group);
         }
         else if(super.HP >= 201)
         {
             super.group.getChildren().remove(super.groupHP);
             Main.group.getChildren().remove(super.group);
             super.groupHP = new Group();
             for(int i = 0,dX = 35; i < 3; i++,dX+=20) {
                 super.healthView = new ImageView(new Image(pathToHP));
                 super.healthView.setX(this.viewObject.getX() + dX);
                 super.healthView.setY(this.viewObject.getY() - 25);
                 super.groupHP.getChildren().addAll(super.healthView);
             }
             super.group.getChildren().add(super.groupHP);
             Main.group.getChildren().add(super.group);
         }
         else if(super.HP >= 101)
         {
             super.group.getChildren().remove(super.groupHP);
             Main.group.getChildren().remove(super.group);
             super.groupHP = new Group();
             for(int i = 0,dX = 35; i < 2; i++,dX+=20) {
                 super.healthView = new ImageView(new Image(pathToHP));
                 super.healthView.setX(this.viewObject.getX() + dX);
                 super.healthView.setY(this.viewObject.getY() - 25);
                 super.groupHP.getChildren().addAll(super.healthView);
             }
             super.group.getChildren().add(super.groupHP);
             Main.group.getChildren().add(super.group);
         }
         else if(super.HP > 0)
         {
             super.group.getChildren().remove(super.groupHP);
             Main.group.getChildren().remove(super.group);
             super.groupHP = new Group();
             for(int i = 0,dX = 35; i < 1; i++,dX+=20) {
                 super.healthView = new ImageView(new Image(pathToHP));
                 super.healthView.setX(this.viewObject.getX() + dX);
                 super.healthView.setY(this.viewObject.getY() - 25);
                 super.groupHP.getChildren().addAll(super.healthView);
             }
             super.group.getChildren().add(super.groupHP);
             Main.group.getChildren().add(super.group);
         }
         else if(super.HP <= 0) {dead(); return false;}
         return true;
    }
    @Override
    public TranslateTransition walk()
    {
        TranslateTransition transition = new TranslateTransition(Duration.millis(250));
        double randomDiraction =  Math.floor(1 + Math.random() * 4);
        double newX = getX(),newY = getY(),moveX = 0,moveY = 0;
        boolean collision = false;
        transition.setNode(getGroup());
        switch((int)randomDiraction)
        {
            // RIGHT
            case 1: newX = getX() + 25;
                moveX = 25;
                break;
            // LEFT
            case 2: newX = getX() - 25;
                moveX = - 25;
                break;
            // UP
            case 3: newY = getY() - 25;
                moveY = - 25;
                break;
            // DOWN
            case 4: newY = getY() + 25;
                moveY = 25;
                break;
        }
        if(Main.minX <= newX && Main.minY <= newY && Main.sizeX >= newX && Main.sizeY >= newY) {
            for(var el : Main.createEveryThingArmy())
            {
                if(!this.equals(el)) {
                    if (el.getX() <= newX && el.getY() <= newY) {
                        if (el.getX() + 125 > newX && el.getY() + 175 > newY) {
                            collision = true;
                        }
                    }
                    else if (el.getX() >= newX && el.getY() >= newY) {
                        if (el.getX() - 125 < newX && el.getY() - 175 < newY) {
                            collision = true;
                        }
                    }
                }
            }
            if(!collision) {
                transition.setByX(moveX);
                transition.setByY(moveY);
                setXY(newX,newY);
            }
            else {transition.setByX(0); transition.setByY(0); System.out.println("COLLISION");}
        }
        else { transition.setByX(0); transition.setByY(0); System.out.println("BORDER");}

        transition.setOnFinished(e -> {
            transition.setNode(getGroup());

            // Почему все переменные лямбда?
            // Я бы с большим удовольствием сделал бы переменные статическими
            // Но по условию курсовой работы, методы должны быть в классе.
            // А создать в классе эти переменные не сильно хотелось.
            // Было принято решение немного написать плохой код.

            double randomDiraction_LAMBDA = Math.floor(1 + Math.random() * 4);
            double newX_LAMBDA = getX(),newY_LAMBDA = getY(),moveX_LAMBDA = 0,moveY_LAMBDA = 0;
            boolean collision_LAMBDA = false;
            switch((int)randomDiraction_LAMBDA)
            {
                // RIGHT
                case 1: newX_LAMBDA = getX() + 25;
                    moveX_LAMBDA = 25;
                    break;
                // LEFT
                case 2: newX_LAMBDA = getX() - 25;
                    moveX_LAMBDA = - 25;
                    break;
                // UP
                case 3: newY_LAMBDA = getY() - 25;
                    moveY_LAMBDA = - 25;
                    break;
                // DOWN
                case 4: newY_LAMBDA = getY() + 25;
                    moveY_LAMBDA = 25;
                    break;
            }
            if(Main.minX <= newX_LAMBDA && Main.minY <= newY_LAMBDA && Main.sizeX >= newX_LAMBDA && Main.sizeY >= newY_LAMBDA) {
                for(var el : Main.createEveryThingArmy())
                {
                    if(!this.equals(el)) {
                        if (el.getX() <= newX_LAMBDA && el.getY() <= newY_LAMBDA) {
                            if (el.getX() + 125 > newX_LAMBDA && el.getY() + 175 > newY_LAMBDA) {
                                collision_LAMBDA = true;
                            }
                        }
                        else if (el.getX() >= newX_LAMBDA && el.getY() >= newY_LAMBDA) {
                            if (el.getX() - 125 < newX_LAMBDA && el.getY() - 175 < newY_LAMBDA) {
                                collision_LAMBDA = true;
                            }
                        }
                    }
                }
                if(!collision_LAMBDA) {
                    transition.setByX(moveX_LAMBDA);
                    transition.setByY(moveY_LAMBDA);
                    setXY(newX_LAMBDA,newY_LAMBDA);

                    for(var el : Main.createEveryThingArmy())
                    {

                            if (!(el.getTeam().equalsIgnoreCase(this.team)))
                            {
                                if (el.getX() <= this.getX() && el.getY() <= this.getY()) {
                                    if (el.getX() + 150 > this.getX() && el.getY() + 200 > this.getY()) {
                                        if (!((el.dead || this.dead) || (el.dead && this.dead))) {
                                            el.attackUnit(this.DAMAGE);
                                            attackUnit(el.DAMAGE);
                                        }
                                    }
                                }
                                else if (el.getX() >= this.getX() && el.getY() >= this.getY()) {
                                    if (el.getX() - 150 < this.getX() && el.getY() - 200 < this.getY()) {
                                        if (!((el.dead || this.dead) || (el.dead && this.dead))) {
                                            el.attackUnit(this.DAMAGE);
                                            attackUnit(el.DAMAGE);
                                        }
                                    }
                                }
                            }
                    }
                }
                else {transition.setByX(0.0); transition.setByY(0.0);System.out.println("COLLISION");}
            }
            else {transition.setByX(0.0); transition.setByY(0.0); System.out.println("BORDER");}
           if(!this.getStatusDead()) {transition.playFromStart();}
        });
        return transition;

    }
    public static void stand() {Movement.stopWalk();}

    public static void walkAgain() {Movement.againWalk();}

    public static void returnToTheFortress()
    {
        try {
            for (var el : Main.armyRed) {
                TranslateTransition transition = new TranslateTransition(Duration.seconds(1));
                transition.setNode(el.getGroup());
                transition.setByX(Math.floor(1245 - el.getX()));
                transition.setByY(Math.floor(255 - el.getY()));
                el.setXY(1245, 255);
                transition.play();
            }
            for (var el : Main.armyGreen) {
                TranslateTransition transition1 = new TranslateTransition(Duration.seconds(1));
                transition1.setNode(el.getGroup());
                transition1.setByX(Math.floor(165 - el.getX()));
                transition1.setByY(Math.floor(330 - el.getY()));
                el.setXY(165, 330);
                transition1.play();
            }
        }
        catch(Exception ex){System.out.println("For programmer: " + ex.getMessage());}
    }
    @Override
    public void seizePoint() {}
    @Override
    public void takePeople()
    {
        super.lineRectangle.setX(viewObject.getX());
        super.lineRectangle.setY(viewObject.getY() -25);
        super.lineRectangle.setStroke(Color.YELLOW);
        super.lineRectangle.setStrokeType(StrokeType.INSIDE);
        super.lineRectangle.setFill(Color.TRANSPARENT);
        super.lineRectangle.setHeight(230);
        super.lineRectangle.setWidth(165);
        try {
            Main.group.getChildren().remove(super.group);
            super.group.getChildren().add(super.lineRectangle);
            Main.group.getChildren().add(super.group);
        }
        catch(Exception ex) {System.out.println("For programmer: " + ex.getMessage());}
    }
    @Override
    public void letGoPeople()
    {
        Main.group.getChildren().remove(super.group);
        super.group.getChildren().remove(super.lineRectangle);
        Main.group.getChildren().add(super.group);
    }
}


