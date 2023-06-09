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
import java.io.IOException;
import java.util.Comparator;
import java.util.Objects;

public class Peasant extends AbstractPeople implements Action, Cloneable,
        Comparable<Peasant>/*,Comparator<Peasant>*/
{
    private int HP = 100;
    private final double DAMAGE = 35;
    protected People type = People.PEASANT;
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
        setDAMAGE(this.DAMAGE);
        setHP(this.HP);
        setHP();
        setImageView();
        setGroup();
        addGroup();
    }
    public People getType(){return this.type;}
    public boolean getStatusDead(){return super.dead;}
    public ImageView getImageView(){return super.viewObject;}
    public Group getGroup() {return super.group;}
    public int getHP() {return super.HP;}
    public double getDamage() {return super.DAMAGE;}
    public String getTeam() {return super.team;}
    public String getName() {return super.name;}
    public int getAge() {return super.age;}
    public double getX() {return super.x;}
    public double getY() {return super.y;}
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
        if(team.equalsIgnoreCase(Team.RED.toString()))
        {
            imageObject = new Image(super.pathRED);
        }
        else if(team.equalsIgnoreCase(Team.GREEN.toString()))
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
    public int hashCode() {return Objects.hash(name,age,HP, DAMAGE, type);}
    @Override
    public Peasant clone() throws CloneNotSupportedException
    {
        Peasant clone = (Peasant)super.clone();
        String copyName = clone.getName() + " COPY ";
        Main.createSoldier(copyName,String.valueOf(clone.getAge()),clone.getTeam(),
                clone.getType().toString(),String.valueOf(clone.getX()),String.valueOf(clone.getY()));
          return clone;
    }

    @Override
    public int compareTo(Peasant p) {return this.age - p.age;} // По возросту
/*    @Override
    public int compare(Peasant first, Peasant second) // По возросту
    {
        if(first.getAge() > second.getAge()) {return 1;}
        else if(first.getAge() < second.getAge()) {return -1;}
        else {return 0;}
    }*/
    public static class PeopleNameComparator implements Comparator<Peasant>
    {
        @Override
        public int compare(Peasant first, Peasant second){return first.getName().compareTo(second.getName());}
    }
    public static class PeopleAgeComparator implements Comparator<Peasant>
    {
        @Override
        public int compare(Peasant first, Peasant second)
        { if(first.getAge()> second.getAge()) return 1;
        else if(first.getAge()< second.getAge()) return -1;
        else return 0;}
    }
    public static class PeopleHPComparator implements Comparator<Peasant>
    {
        @Override
        public int compare(Peasant first, Peasant second)
        { if(first.getHP()> second.getHP()) return 1;
        else if(first.getHP()< second.getHP()) return -1;
        else return 0;}
    }
    public static class PeopleDamageComparator implements Comparator<Peasant>
    {
        @Override
        public int compare(Peasant first, Peasant second)
        { if(first.getDamage()> second.getDamage()) return 1;
        else if(first.getDamage()< second.getDamage()) return -1;
        else return 0;}
    }
    private void removeGroup() {Main.group.getChildren().remove(super.group);}
    private void addGroup() {Main.group.getChildren().add(super.group);}
    @Override
    public void dead()
    {
        try {Movement.transitionsMap.get(this).stop();/*Movement.transitionsMap.remove(this);*/}
        catch(Exception ex){};
        removeGroup();
        super.pathRED = super.pathGREEN = "D:\\project\\game\\src\\main\\java\\codeGame\\image\\coffin.png";
        setTeam(super.team);
        setName(super.name);
        setImageView();
        super.group = new Group();
        super.group.getChildren().addAll(super.label,super.viewObject);
        addGroup();
        super.dead = true;
    }

    @Override
    public boolean attackUnit(double damage)
    {
        String pathToHP = "D:\\project\\game\\src\\main\\java\\codeGame\\image\\health.png";
        super.HP -= damage;
        int i = 0;
        if(super.HP >= 301) {i = 4;}
        else if(super.HP >= 201) {i = 3;}
        else if(super.HP >= 101) {i = 2;}
        else if(super.HP > 0) {i = 1;}
        else if(super.HP <= 0) {dead(); return false;}

        removeGroup();
        super.group.getChildren().remove(super.groupHP);
        super.groupHP = new Group();
        for(int dX = 35; i > 0; i--,dX+=20) {
            super.healthView = new ImageView(new Image(pathToHP));
            super.healthView.setX(this.viewObject.getX() + dX);
            super.healthView.setY(this.viewObject.getY() - 25);
            super.groupHP.getChildren().addAll(super.healthView);
        }
        super.group.getChildren().add(super.groupHP);
        addGroup();
        return true;
    }
    private void onceWalk(TranslateTransition transition)
    {
        double randomDiraction = Math.floor(1 + Math.random() * 4);
        double newX = getX(), newY = getY(), moveX = 0, moveY = 0;
        boolean collision = false;
        switch ((int) randomDiraction) {
            // RIGHT
            case 1:
                newX = getX() + 25;
                moveX = 25;
                break;
            // LEFT
            case 2:
                newX = getX() - 25;
                moveX = -25;
                break;
            // UP
            case 3:
                newY = getY() - 25;
                moveY = -25;
                break;
            // DOWN
            case 4:
                newY = getY() + 25;
                moveY = 25;
                break;
        }
        if (Main.minX <= newX && Main.minY <= newY && Main.sizeX >= newX && Main.sizeY >= newY) {
            for (var el : Main.createEveryThingArmy()) {
                if (!this.equals(el)) {
                    if (el.getX() <= newX && el.getY() <= newY) {
                        if (el.getX() + 125 > newX && el.getY() + 175 > newY) {
                            collision = true;
                        }
                    } else if (el.getX() >= newX && el.getY() >= newY) {
                        if (el.getX() - 125 < newX && el.getY() - 175 < newY) {
                            collision = true;
                        }
                    }
                }
            }
            if (!collision) {
                transition.setByX(moveX);
                transition.setByY(moveY);
                setXY(newX, newY);

                for (var el : Main.createEveryThingArmy()) {

                    if (!(el.getTeam().equalsIgnoreCase(this.team))) {
                        if (el.getX() <= this.getX() && el.getY() <= this.getY()) {
                            if (el.getX() + 150 > this.getX() && el.getY() + 200 > this.getY()) {
                                if (!((el.dead || this.dead) || (el.dead && this.dead))) {
                                    el.attackUnit(this.DAMAGE);
                                    attackUnit(el.DAMAGE);
                                }
                            }
                        } else if (el.getX() >= this.getX() && el.getY() >= this.getY()) {
                            if (el.getX() - 150 < this.getX() && el.getY() - 200 < this.getY()) {
                                if (!((el.dead || this.dead) || (el.dead && this.dead))) {
                                    el.attackUnit(this.DAMAGE);
                                    attackUnit(el.DAMAGE);
                                }
                            }
                        }
                    }
                }
            } else {
                transition.setByX(0.0);
                transition.setByY(0.0);
                System.out.println("COLLISION");
            }
        } else {
            transition.setByX(0.0);
            transition.setByY(0.0);
            System.out.println("BORDER");
        }
        if (!this.getStatusDead()) {
            transition.playFromStart();
        }
    }
    @Override
    public TranslateTransition walk()
    {
        if(!Main.FORTRESS_RED.insidePeople.contains(this)
                || !Main.FORTRESS_GREEN.insidePeople.contains(this)) {
            TranslateTransition transition = new TranslateTransition(Duration.millis(250));
            onceWalk(transition);
            transition.setOnFinished(e -> {
                transition.setNode(getGroup());
                onceWalk(transition);
            });
            return transition;
        }
        return null;
    }
    public static void stand() {Movement.stopWalk();}

    public static void walkAgain() {Movement.againWalk();}
    public static void returnToTheFortress()
    {
        Main.FORTRESS_RED.setInside();
        Main.FORTRESS_GREEN.setInside();
        if(!Main.FORTRESS_GREEN.getInside() && !Main.FORTRESS_RED.getInside()) {
            interactionWithEachPeople.letGoPeople();
            try {
                for (var el : Main.armyRed) {
                    TranslateTransition transition = new TranslateTransition(Duration.seconds(1));
                    transition.setNode(el.getGroup());
                    transition.setByX(Math.floor(2370 - el.getX()));
                    transition.setByY(Math.floor(755 - el.getY()));
                    transition.setOnFinished(ev -> {
                        el.getGroup().setVisible(false);
                        Main.FORTRESS_RED.insidePeople.add(el);
                    });
                    el.setXY(2370, 765);
                    transition.play();
                }
                for (var el : Main.armyGreen) {
                    TranslateTransition transition1 = new TranslateTransition(Duration.seconds(1));
                    transition1.setNode(el.getGroup());
                    transition1.setByX(Math.floor(320 - el.getX()));
                    transition1.setByY(Math.floor(755 - el.getY()));
                    transition1.setOnFinished(ev -> {
                        el.getGroup().setVisible(false);
                        Main.FORTRESS_GREEN.insidePeople.add(el);
                    });
                    el.setXY(320, 755);
                    transition1.play();
                }
                try{
                    Main.inLog.write("[" + Main.currentTime() + "] " +
                        "All micro-objects were sent to their castles \n");}
                catch(IOException exc){exc.getMessage();}
            } catch (Exception ex) {
                System.out.println("For programmer: " + ex.getMessage());
            }
        }
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
        super.lineRectangle.setStrokeWidth(3);
        super.lineRectangle.setFill(Color.TRANSPARENT);
        super.lineRectangle.setHeight(230);
        super.lineRectangle.setWidth(165);
        try {
            removeGroup();
            super.group.getChildren().add(super.lineRectangle);
            addGroup();
        }
        catch(Exception ex) {System.out.println("For programmer: " + ex.getMessage());}
    }
    @Override
    public void letGoPeople()
    {
        removeGroup();
        super.group.getChildren().remove(super.lineRectangle);
        addGroup();
    }
}
