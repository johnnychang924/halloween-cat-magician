package Animation;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import tombController.tombController;

import java.io.IOException;

public class Animation {
    private static ImageView[] player_pose;
    private static final Timeline simplePose = new Timeline(
            new KeyFrame(Duration.millis(300), (e)->{
                playerHide();
                player_pose[0].setVisible(true);
            }));

    public static void initAnimation(ImageView[] player_pose){
        Animation.player_pose = player_pose;
        simplePose.setCycleCount(1);
    }

    public static void playerHide(){
        for(var pose: player_pose){
            pose.setVisible(false);
        }
    }

    public static void attackLeft(){
        playerHide();
        player_pose[1 + (int)(Math.random()*2)].setVisible(true);
        simplePose.play();
    }

    public static void attackRight(){
        playerHide();
        player_pose[3].setVisible(true);
        simplePose.play();
    }

    public static void attackBothSide(){
        playerHide();
        player_pose[4].setVisible(true);
        simplePose.play();
    }

    public static void win() throws IOException {
        simplePose.stop();
        playerHide();
        player_pose[5].setVisible(true);
        tombController.gameOver();
        tombController.static_black.setVisible(true);
        tombController.static_victory.setVisible(true);
    }

    public static void lose() throws IOException {
        playerHide();
        player_pose[6].setVisible(true);
        tombController.gameOver();
        tombController.static_black.setVisible(true);
        tombController.static_lose.setVisible(true);
    }
}
