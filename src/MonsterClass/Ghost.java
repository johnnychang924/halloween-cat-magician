package MonsterClass;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.NodeOrientation;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class Ghost extends Monster{
    public boolean isRight;
    public Ghost(int speed, int textLong, int x, int y){
        super(speed, x, y, textLong, new String[]{"A", "S", "D", "F", "B", "C", "E", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"});
        ghost_attacked.setRotate(45);
        isRight = x > 666;
        detect.play();
        if(!isRight){
            ghost.nodeOrientationProperty().setValue(NodeOrientation.RIGHT_TO_LEFT);
            ghost_attacked.nodeOrientationProperty().setValue(NodeOrientation.RIGHT_TO_LEFT);
        }
        vbox = new VBox(getText(), ghost);
        simpleGhost = new Timeline(
                new KeyFrame(Duration.millis(300), (e)->{
                    if(vbox.getChildren().contains(ghost_attacked)){
                        vbox.getChildren().remove(ghost_attacked);
                        vbox.getChildren().add(ghost);
                    }
                }));
        simpleGhost.setCycleCount(1);
    }
    public VBox getVbox(){
        return vbox;
    }
    public void ghostAttackedAnimation(){
        if(vbox.getChildren().contains(ghost)){
            vbox.getChildren().remove(ghost);
            vbox.getChildren().add(ghost_attacked);
        }
        simpleGhost.play();
    }
    private final Timeline simpleGhost;
    private final VBox vbox;
    private final ImageView ghost = new ImageView(new Image("MonsterClass/ghost.png"));
    private final ImageView ghost_attacked = new ImageView(new Image("MonsterClass/ghost_attacked.png"));
}
