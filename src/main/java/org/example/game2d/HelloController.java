package org.example.game2d;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView bg1, bg2, player, enemy;

    private final int BG_WIDTH = 782;

    private ParallelTransition parallelTransition;
    public static boolean jump = false;
    public static boolean right = false;
    public static boolean left = false;
    private int playerSpeed = 3, jumpDownSpeed = 5;

    AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            if(jump && player.getLayoutY() > 230f)
                player.setLayoutY(player.getLayoutY() - playerSpeed);
            else if(player.getLayoutY() <= 310f){
                jump = false;
                player.setLayoutY(player.getLayoutY() + jumpDownSpeed);
            }

            if(right && player.getLayoutX() < 200f)
                player.setLayoutX(player.getLayoutX() + playerSpeed);
            if(left && player.getLayoutX() > 28f)
                player.setLayoutX(player.getLayoutX() - playerSpeed);
        }
    };

    @FXML
    void initialize() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Are you ready to start game?", ButtonType.OK);
        alert.showAndWait();

        TranslateTransition bgOneTransition = new TranslateTransition(Duration.millis(5000), bg1);
        bgOneTransition.setFromX(0);
        bgOneTransition.setToX(BG_WIDTH * -1);
        bgOneTransition.setInterpolator(Interpolator.LINEAR);

        TranslateTransition bgTwoTransition = new TranslateTransition(Duration.millis(5000), bg2);
        bgTwoTransition.setFromX(0);
        bgTwoTransition.setToX(BG_WIDTH * -1);
        bgTwoTransition.setInterpolator(Interpolator.LINEAR);

        TranslateTransition enemyTransition = new TranslateTransition(Duration.millis(4000), enemy);
        enemyTransition.setFromX(0);
        enemyTransition.setToX(BG_WIDTH * -1 - 300);
        enemyTransition.setInterpolator(Interpolator.LINEAR);
        enemyTransition.setCycleCount(Animation.INDEFINITE);
        enemyTransition.play();


        parallelTransition = new ParallelTransition(bgOneTransition, bgTwoTransition);
        parallelTransition.setCycleCount(Animation.INDEFINITE);
        parallelTransition.play();

        timer.start();

//        assert bg1 != null : "fx:id=\"bg1\" was not injected: check your FXML file 'hello-view.fxml'.";
//        assert bg2 != null : "fx:id=\"bg2\" was not injected: check your FXML file 'hello-view.fxml'.";
//        assert player != null : "fx:id=\"player\" was not injected: check your FXML file 'hello-view.fxml'.";
    }
}
