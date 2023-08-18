package MonsterClass;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class Boss extends Monster{
    public Boss(int speed, int textLong, int x, int y){
        super(speed, x, y, textLong, new String[]{"A", "S", "D", "F"});
        ImageView ghost = new ImageView(new Image("MonsterClass/ghost.png"));
        vbox = new VBox(getText(), ghost);
    }
    public VBox getVbox(){
        return vbox;
    }
    private final VBox vbox;
}
