package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    public static Stage currentStage;
    public static Scene window;
    @Override
    public void start(Stage primaryStage) throws IOException {
        currentStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/fxmlFile/Menu.fxml"));
        window = new Scene(root);
        primaryStage.setScene(window);
        primaryStage.setTitle("墓地驚魂夜");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
