package tombController;

import Animation.Animation;
import MonsterClass.Ghost;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.io.IOException;
import java.util.LinkedList;

public class MonsterManager {

    private final static int ghostNum = 10;
    private static int[] appearTime = {1, 5, 10, 11, 13, 14, 16, 19, 21, 25};
    private static int[] speed = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    private static int[] textLong = {10, 10, 10, 10, 10, 10, 10, 10, 10, 20};
    private static int[] appearX = {0, 1250, 625, 1250, 0, 0, 625, 0, 1250, 0};
    private static int[] appearY = {630, 630, 315, 315, 360, 630, 315, 630, 630, 630};
    private static int i = 0;

    private static LinkedList<Ghost> ghostList = new LinkedList<>();

    public static void init(){
        ghostList.clear();
        i = 0;
    }

    public static void CreateMonster(long currentTime){
        while(true) {
            if (i < ghostNum) {
                if (currentTime > appearTime[i] * 1000) {
                    createGhost(speed[i], textLong[i], appearX[i], appearY[i]);
                    i++;
                }
                else{
                    break;
                }
            }
            else{
                if(ghostList.isEmpty()){
                    try {
                        Animation.win();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            }
        }
    }

    public static void createGhost(int speed, int textLong, int x, int y){
        Ghost NewGhost = new MonsterClass.Ghost(speed, textLong, x, y);
        ghostList.push(NewGhost);
        PathTransition pathTransition = new PathTransition();
        pathTransition.setPath(NewGhost.CreatePath());
        pathTransition.setDuration(Duration.millis(10000));
        pathTransition.setNode(NewGhost.getVbox());
        pathTransition.play();
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000), NewGhost.getVbox());
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
        new Timeline(
                new KeyFrame(Duration.millis(10), (e)-> tombController.ground.getChildren().add(NewGhost.getVbox()))).play();
    }

    public static void attack(String KeyIn){
        boolean left = false, right = false;
        for(var g: ghostList){
                if (KeyIn.equals(String.valueOf(g.getFirstText()))) {
                    if (g.isRight) {
                        right = true;
                    } else {
                        left = true;
                    }
                    g.ghostAttackedAnimation();
                    g.attacked();
                }
        }
        if(left && right){
            Animation.attackBothSide();
        }
        else if(left){
            Animation.attackLeft();
        }
        else if(right){
            Animation.attackRight();
        }
    }

    public static void GhostDie(MonsterClass.Ghost ghost){
        tombController.ground.getChildren().remove(ghost.getVbox());
        ghost.detect.stop();
        ghostList.remove(ghost);
    }

    public static void ghostStop(){
        for(var g: ghostList){
            g.getVbox().setVisible(false);
            g.detect.stop();
        }
    }
}
