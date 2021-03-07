package sample.animations;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Shake {
    private TranslateTransition tt;

    public Shake(Node node){
        tt = new TranslateTransition(Duration.millis(80), node);
        tt.setFromX(0f);
        tt.setByX(7f);
        tt.setFromY(0f);
        tt.setByY(3f);
        tt.setCycleCount(3);
        tt.setAutoReverse(true);
    }

    public void playAnimation(){
        tt.playFromStart();
    }

}
