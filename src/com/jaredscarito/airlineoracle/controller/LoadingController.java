package com.jaredscarito.airlineoracle.controller;

import com.jaredscarito.airlineoracle.main.Main;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class LoadingController {
    private Main main;
    public LoadingController(Main main) {
        this.main = main;
    }

    public void start() {
        GridPane mainPanel = new GridPane();
        mainPanel.setBackground(new Background(new BackgroundImage(new Image("com/jaredscarito/airlineoracle/view/plane-loading-gif.gif"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, new BackgroundSize(main.getScreenWidth(), main.getScreenHeight(), false, false, false, false))));
        double width, height;
        width = main.getScreenWidth();
        height = main.getScreenHeight();
        main.getPrimaryStage().setScene(new Scene(mainPanel, width, height));
        main.getPrimaryStage().getScene().getStylesheets().add("com/jaredscarito/airlineoracle/view/style.css");
        main.getPrimaryStage().show();

        Random rand = new Random();
        int randNum = rand.nextInt((7 - 3) + 3) + 3;
        Timeline fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(randNum), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                main.getSeatSelectController().start();
            }
        }));
        fiveSecondsWonder.setCycleCount(1);
        fiveSecondsWonder.play();
    }
}
