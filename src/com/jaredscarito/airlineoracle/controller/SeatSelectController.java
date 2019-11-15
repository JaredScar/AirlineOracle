package com.jaredscarito.airlineoracle.controller;

import com.jaredscarito.airlineoracle.main.Main;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class SeatSelectController {
    private Main main;
    public SeatSelectController(Main main) {
        this.main = main;
    }

    public void start() {
        GridPane mainPanel = new GridPane();
        mainPanel.setId("SeatMainPanel");

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setId("SeatScrollPane");
        //mainPanel.setBackground(new Background(new BackgroundImage(new Image(
          //      "com/jaredscarito/airlineoracle/view/Delta-Airplane-Layout.png"), BackgroundRepeat.NO_REPEAT,
            //    BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(main.getScreenWidth(),
              //  main.getScreenHeight(), false, false, false, true))));
        ImageView plane = new ImageView(new Image("com/jaredscarito/airlineoracle/view/Delta-Airplane-Layout.png"));
        plane.setFitWidth(main.getToolKit().getScreenSize().getWidth() * 5);
        plane.setFitHeight(main.getToolKit().getScreenSize().getHeight() * 5);
        scrollPane.setContent(plane);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHvalue( (scrollPane.getHmax() - scrollPane.getHmin()) / 2);
        mainPanel.add(scrollPane, 0, 0);

        double width, height;
        width = main.getScreenWidth();
        height = main.getScreenHeight();
        main.getPrimaryStage().setScene(new Scene(mainPanel, width, height));
        main.getPrimaryStage().getScene().getStylesheets().add("com/jaredscarito/airlineoracle/view/style.css");
        main.getPrimaryStage().show();
    }
}
