package com.jaredscarito.airlineoracle.controller;

import com.jaredscarito.airlineoracle.main.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

        GridPane scrollGrid = new GridPane();
        scrollGrid.setAlignment(Pos.TOP_CENTER);
        scrollGrid.setId("ScrollGrid");
        scrollGrid.setBackground(new Background(new BackgroundImage(new Image(
                      "com/jaredscarito/airlineoracle/view/Delta-Airplane-Layout.png"), BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(main.getScreenWidth() * 2,
                  main.getScreenHeight() * 2, false, false, false, false))));
        scrollGrid.setPrefHeight(main.getToolKit().getScreenSize().getHeight() * 2);
        scrollGrid.setPrefWidth(main.getToolKit().getScreenSize().getWidth() * 2);

        GridPane buttonGrid = new GridPane();
        buttonGrid.setTranslateX(-40);
        buttonGrid.setTranslateY(992);
        buttonGrid.setId("ButtonGrid");
        buttonGrid.setVgap(5);
        String letters = "ABCDEFG";
        int currLetIndex = 0;
        int rowInd = 0;
        int colInd = 0;
        for (int i=0; i < 136; i++) {
            char curLet = letters.charAt(currLetIndex);
            currLetIndex++;
            String currentSeat = i + "" + curLet;
            Button btn = new Button("");
            btn.getStyleClass().add("seatButton");
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    // We need to track which seat row and letter this is, then have it save it somewhere
                    // and it needs to check if they selected the number of seats they wanted, then progress to next
                    // TODO
                }
            });
            buttonGrid.add(btn, colInd, rowInd);
            if (curLet == 'A' || curLet == 'B') {
                btn.setTranslateY(-5);
            }
            if(curLet == 'C' || curLet == 'D' || curLet == 'E') {
                // Spacing to the left via translateX
                btn.setTranslateX(40);
            } else
                if (curLet == 'F' || curLet == 'G') {
                    // Spacing to the left via translateX
                    btn.setTranslateX(80);
                    btn.setTranslateY(-5);
                }
                colInd++;
                if (currLetIndex == letters.length()) {
                currLetIndex = 0;
                rowInd++;
                colInd = 0;
                if (i == 132) {
                    currLetIndex = 2;
                    colInd = 2;
                }
            }
        }

        scrollGrid.add(buttonGrid, 0, 0);


        scrollPane.setContent(scrollGrid);

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
