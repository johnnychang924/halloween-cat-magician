package MenuController;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import Main.Main;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MenuController {
    private Media media;
    private MediaPlayer player;
    @FXML
    public void onStartPressed() throws IOException{
        player.stop();
        Parent root = FXMLLoader.load(getClass().getResource("/fxmlFile/tomb.fxml"));
        Scene tombScene = new Scene(root);
        tombScene.getRoot().requestFocus();
        Main.currentStage.setScene(tombScene);
    }

    @FXML
    public void onExitPressed() {
        Main.currentStage.close();
    }

    @FXML
    private void initialize(){
        media = new Media(new File("src/MenuController/music.mp3").toURI().toString());
        player = new MediaPlayer(media);
        player.setCycleCount(MediaPlayer.INDEFINITE);
        player.play();
    }
}
