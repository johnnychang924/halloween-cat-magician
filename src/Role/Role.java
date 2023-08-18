package Role;

import java.io.IOException;

public abstract class Role {
    private static int life = 5;
    public static void Damage(){
        life--;
        if(life <= 0){
            try{
                Animation.Animation.lose();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        tombController.tombController.damage();
    }
    public static void initLife(){
        life = 5;
    }
    public static int getLife() {
        return life;
    }
}