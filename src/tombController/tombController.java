package tombController;

import Animation.Animation;
import Main.Main;
import javafx.animation.FillTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class tombController implements Initializable, EventHandler<KeyEvent> {
    @FXML
    private Text victory;
    public static Text static_victory;
    @FXML
    private Text lose;
    public static Text static_lose;
    @FXML
    private ImageView black;
    public static ImageView static_black;
    @FXML
    private Button BackButton;
    private static Button static_BackButton;
    @FXML
    private Pane groundPane;
    @FXML
    private ImageView player_start;
    @FXML
    private ImageView player_pose_1;
    @FXML
    private ImageView player_pose_2;
    @FXML
    private ImageView player_pose_3;
    @FXML
    private ImageView player_pose_4;
    @FXML
    private ImageView player_win;
    @FXML
    private ImageView player_lose;
    @FXML
    private ImageView life1;
    @FXML
    private ImageView life2;
    @FXML
    private ImageView life3;
    @FXML
    private ImageView life4;
    @FXML
    private ImageView life5;
    private static ImageView[] all_life;

    private Media media;
    private MediaPlayer player;
    public static Pane ground;
    private long currentTime = 0;
    private long startTime;
    private static Timeline ghostAppear;
    private static int heart = 5;

    @Override
    public void handle(KeyEvent e){
        try {
            MonsterManager.attack(e.getCode().toString());
        } catch (Exception ignored) {
        }
    }

    public static void damage(){
        if(heart > 0){
            all_life[heart-1].setVisible(false);
        }
        heart--;
    }

    public static void gameOver(){
        ghostAppear.stop();
        MonsterManager.ghostStop();
        static_BackButton.setVisible(true);
    }

    @FXML
    public void BackPress() throws IOException {
        player.stop();
        Parent root = FXMLLoader.load(getClass().getResource("/fxmlFile/Menu.fxml"));
        Main.currentStage.setScene(new Scene(root));
    }

    private void init_every_object(){
        Role.Role.initLife();
        MonsterManager.init();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        heart = 5;
        media = new Media(new File("src/tombController/tomb_music.mp3").toURI().toString());
        player = new MediaPlayer(media);
        //player.setAutoPlay(true);
        player.setCycleCount(MediaPlayer.INDEFINITE);
        player.play();
        init_every_object();
        static_victory = victory;
        static_lose = lose;
        static_black = black;
        static_BackButton = BackButton;
        ground = groundPane;
        all_life = new ImageView[]{life1, life2, life3, life4, life5};
        startTime = System.currentTimeMillis();
        ghostAppear = new Timeline(
                new KeyFrame(Duration.millis(500), (e)->{
                    currentTime = System.currentTimeMillis() - startTime;
                    MonsterManager.CreateMonster(currentTime);
                }));
        ghostAppear.setCycleCount(Timeline.INDEFINITE);
        ghostAppear.play();
        Animation.initAnimation(new ImageView[]{player_start, player_pose_1,
                player_pose_3, player_pose_2, player_pose_4, player_win,
                player_lose});
        FillTransition victory_color = new FillTransition(Duration.millis(1000), victory, Color.YELLOW, Color.BLACK);
        victory_color.setDuration(Duration.millis(1000));
        victory_color.setCycleCount(javafx.animation.Animation.INDEFINITE);
        victory_color.setAutoReverse(true);
        victory_color.play();
        FillTransition lose_color = new FillTransition(Duration.millis(1000), lose, Color.RED, Color.BLACK);
        lose_color.setDuration(Duration.millis(1000));
        lose_color.setCycleCount(javafx.animation.Animation.INDEFINITE);
        lose_color.setAutoReverse(true);
        lose_color.play();
    }
}
