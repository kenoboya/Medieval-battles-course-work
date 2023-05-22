package codeGame.macroObject;

import codeGame.Initialization;
import codeGame.Main;
import codeGame.microObject.Warrior;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class House extends Construction
{
    private List<Warrior> finishedList = new ArrayList<>();
    private List<Warrior> flagMakingList = new ArrayList<>();

    protected Building type = Building.HOUSE;
    private Text name = new Text();
    private Text finished = new Text();
    private Text flagMaking = new Text();
    public House(String name, double x, double y)
    {
        this.x = x;
        this.y = y;
        this.name.setText(name);
        setImageView();
        setName();
        setInformationAboutProcess();
        Main.group.getChildren().addAll(setGroup());
        Initialization.writeToFile("A macro object has been created to: " + name);
    }
    public double getX() {return x;}
    public double getY() {return y;}
    public String getName() {return name.getText();}
    private void setImageView()
    {
        imageView = new ImageView(
                new Image("D:\\project\\game\\src\\main\\java\\codeGame\\image\\house.png"));
        imageView.setX(x); imageView.setY(y);
    }
    private void InformationAboutProcess()
    {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), actionEvent ->
        {
            if(finishedList.isEmpty()) {finished.setVisible(false);}
            else {
                finished.setVisible(true);
                finished.setText("Finished making flags : " + finishedList.size());
            }
            if(flagMakingList.isEmpty()) {flagMaking.setVisible(false);}
            else{
                flagMaking.setVisible(true);
                flagMaking.setText("Make flags : " + flagMakingList.size());
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    private void setOptionsText(Text text)
    {
        text.setFont(Font.font("Verdana", FontWeight.BOLD,24));
        text.setStroke(Color.WHITE);
    }
    private void setName()
    {
        setOptionsText(name);
        name.setLayoutY(y + 330);
        name.setLayoutX(x + 125);
    }
    private void setInformationAboutProcess()
    {
        flagMaking.setText("Make flags : " + flagMakingList.size());
        finished.setText("Finished making flags : " + finishedList.size());
        setOptionsText(flagMaking);
        flagMaking.setLayoutX(x + 100);
        flagMaking.setLayoutY(y + 50);
        setOptionsText(finished);
        finished.setLayoutX(x + 75);
        finished.setLayoutY(y);
        InformationAboutProcess();
    }
    private Group setGroup()
    {
        group = new Group(imageView,name,flagMaking,finished);
        return group;
    }
    @Override
    public Building getType(){return this.type;}

    public List<Warrior> getFinishedList(){return finishedList;}
    public void leftHome(Warrior aboveSecondLevel){finishedList.remove(aboveSecondLevel);}
    public void makingFlag(Warrior aboveSecondLevel)
    {
        if(!finishedList.contains(aboveSecondLevel)
                && !flagMakingList.contains(aboveSecondLevel) && aboveSecondLevel.getListFlag().size() == 5)
        {
            flagMakingList.add(aboveSecondLevel);
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(10), actionEvent ->
            {
                flagMakingList.remove(aboveSecondLevel);
                aboveSecondLevel.getListFlag().removeAll(aboveSecondLevel.getListFlag());
                aboveSecondLevel.resetLimit();
                finishedList.add(aboveSecondLevel);
            }));
            timeline.play();
        }
    }
}
