package codeGame.microObject;

import codeGame.Main;
import codeGame.macroObject.Construction;
import codeGame.macroObject.Fortress;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.Serializable;
import java.util.ArrayList;

public class Cart implements Serializable
{
    private double x,y;
    private Text coal;
    private boolean collects = false, move = false;
    private String team;
    private int countCoal, limitCoal = 100;
    public static ArrayList<Cart> cartList = new ArrayList<>();
    private ImageView imageView =
            new ImageView(new Image(Main.class.getResource("cart.png").toString()));
    private Group group = new Group();
    private Cart(String team, int countCoal,double x, double y)
    {
        this.team = team;
        this.countCoal = countCoal;
        this.x = x;
        this.y = y;
        imageView.setX(x);
        imageView.setY(y);
        setText();
        group.getChildren().addAll(imageView,coal);
        Main.group.getChildren().add(group);
    }
    public String getTeam(){return team;}
    public ImageView getImageView(){return imageView;}
    public int getCountCoal(){return countCoal;}
    public int getLimitCoal(){return limitCoal;}
    public void setMove(boolean move) {this.move = move;}
    public boolean getMove(){return move;}
    public boolean getCollects(){return collects;}
    public Group getGroup(){return group;}
    public void setCollects(boolean status){collects = status;}
    public void startCollect()
    {
        final Timeline[] timeline = {null};
        if (!collects) {
            setCollects(true);
            timeline[0] = new Timeline(new KeyFrame(Duration.millis(1000), actionEvent -> {
                if (countCoal >= limitCoal) {
                    timeline[0].stop();
                    setCollects(false);
                }
                else
                {
                    countCoal += 10;
                    updateText();
                }
            }));
            timeline[0].setCycleCount(10);
            timeline[0].setOnFinished(el -> collects = false);
            timeline[0].play();
        }
    }
    public synchronized void ShipmentAtTheCastle(Fortress fortress)
    {
        final Timeline[] timeline = {null};
        if (!collects) {
            setCollects(true);
            timeline[0] = new Timeline(new KeyFrame(Duration.millis(1000), actionEvent -> {
                if (countCoal <= 0) {
                    timeline[0].stop();
                    setCollects(false);
                }
                else
                {
                    countCoal -= 10;
                    fortress.setPOINT(fortress.getPOINT() + 10);
                    updateText();
                }
            }));
            timeline[0].setOnFinished(e -> collects = false);
            timeline[0].setCycleCount(10);
            timeline[0].play();
        }
    }
    public void moveTo(Construction construction)
    {
        if(!move) {
            move = true;
            TranslateTransition transition = new TranslateTransition(Duration.seconds(7));
            transition.setNode(this.group);
            transition.setByX(construction.getImageView().getX() - this.imageView.getX() - group.getTranslateX());
            transition.setByY(construction.getImageView().getY() - this.imageView.getY() - group.getTranslateY());
            transition.play();
            transition.setOnFinished(e -> move = false);
        }
    }
    void removeGroup() {Main.group.getChildren().remove(group);}
    void addGroup() {Main.group.getChildren().add(group);}
    public static CartBuilder builder() {return new CartBuilder();}
    private void updateText()
    {
        coal.setText("Coal :" + countCoal);
    }
    private void setText()
    {
        coal = new Text();
        coal.setText("Coal :" + countCoal);
        coal.setLayoutX(imageView.getX() + 80);
        coal.setLayoutY(imageView.getY() + 10);
        coal.setStroke(Color.WHITE);
        if(team.equalsIgnoreCase(Team.RED.toString())) {coal.setFill(Color.RED);}
        else if(team.equalsIgnoreCase(Team.GREEN.toString())) {coal.setFill(Color.GREEN);}
        coal.setFont(Font.font("Verdana", FontWeight.BOLD,20));
    }
    public static class CartBuilder {
        private double x = 0,y = 0;
        private String team = "None";
        private int countCoal = 0;
        public CartBuilder setXY(double x, double y)
        {
            this.x = x;
            this.y = y;
            return this;
        }
        public CartBuilder addTeam(String team)
        {
            this.team = team;
            return this;
        }
        public CartBuilder countCoal(int coal)
        {
            this.countCoal = coal;
            return this;
        }
        public Cart build() {return new Cart(team,countCoal,x,y);}
    }
}
