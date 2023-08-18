package MonsterClass;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import tombController.MonsterManager;

public class Monster {

    public Monster(int speed, int x, int y, int textLong,String[] dictionary){
        this.speed = speed;
        this.x = x;
        this.y = y;
        this.dictionary = dictionary;
        this.textLong = textLong;
        initText();
    }

    private void initText(){
        StringBuilder text = new StringBuilder();
        for(int i = 0; i < textLong; i++){
            text.append(dictionary[(int) (Math.random() * dictionary.length)]);
        }
        System.out.println(text);
        this.text = new Text(text.toString());
        this.text.setFont(new Font(20));
        this.text.setStroke(Color.RED);
        this.text.setStrokeWidth(1);
    }

    public char getFirstText(){
        return text.getText().charAt(0);
    }

    public void attacked(){
        if(text.getText().length() > 1){
            text.setText(text.getText().substring(1));
            System.out.println(text.getText());
        }
        else{
            tombController.MonsterManager.GhostDie((Ghost) this);
            System.out.println("Dead");
        }
    }

    public Path CreatePath(){
        Path path = new Path();
        path.getElements().add(new MoveTo(x, y));
        path.getElements().add(new LineTo(673,575));
        return path;
    }

    public Text getText(){
        return text;
    }

    public String[] dictionary;
    private Text text;
    private final int speed;
    private final int x;
    private final int y;
    private final int textLong;
    public Timeline detect = new Timeline(
            new KeyFrame(Duration.millis(10000), (e)->{
                Role.Role.Damage();
                MonsterManager.GhostDie((Ghost)this);
                System.out.println(Role.Role.getLife());
            }));
}
