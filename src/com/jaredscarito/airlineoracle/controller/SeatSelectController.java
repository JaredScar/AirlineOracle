package com.jaredscarito.airlineoracle.controller;

import com.jaredscarito.airlineoracle.main.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

public class SeatSelectController implements Controller {
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
                "com/jaredscarito/airlineoracle/view/Delta-Airplane-Layout-EDITED.jpg"), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(main.getScreenWidth() * 2.5,
                main.getScreenHeight() * 2.5, false, false, false, false))));
        scrollGrid.setPrefHeight(main.getToolKit().getScreenSize().getHeight() * 2.5);
        scrollGrid.setPrefWidth(main.getToolKit().getScreenSize().getWidth() * 2.5);

        GridPane buttonGrid = new GridPane();
        buttonGrid.setTranslateX(-25);
        String osName = System.getProperty("os.name").toLowerCase();
        boolean isMacOs = osName.startsWith("mac os x");
        int buttonHeight, buttonWidth;
        if (!isMacOs) {
            buttonGrid.setTranslateY(1260);
            buttonWidth = 20;
            buttonHeight = 21;
        } else {
            buttonGrid.setTranslateY(1130);
            buttonWidth = 25;
            buttonHeight = 21;
            scrollGrid.setBackground(new Background(new BackgroundImage(new Image(
                    "com/jaredscarito/airlineoracle/view/Delta-Airplane-Layout-EDITED.jpg"), BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(main.getScreenWidth() * 2.7,
                    main.getScreenHeight() * 2.7, false, false, false, false))));
            scrollGrid.setPrefHeight(main.getToolKit().getScreenSize().getHeight() * 2.7);
            scrollGrid.setPrefWidth(main.getToolKit().getScreenSize().getWidth() * 2.7);
        }
        buttonGrid.setId("ButtonGrid");
        buttonGrid.setVgap(0);
        String letters = "ABCDEFG";
        int currLetIndex = 0;
        int rowInd = 0;
        int colInd = 0;
        for (int i=0; i < 143; i++) {
            char curLet = letters.charAt(currLetIndex);
            currLetIndex++;
            Button btn = new Button("");
            // TODO Need to check if seat is taken
            // TODO SQL FOR ABOVE ^^^
            // NOT TAKEN: Width:20 Height:21
            btn.setGraphic(new ImageView(new Image("com/jaredscarito/airlineoracle/view/empty-main-seat.png", buttonWidth, buttonHeight, true, true)));
            // TAKEN:
            //btn.setGraphic(new ImageView(new Image("com/jaredscarito/airlineoracle/view/taken-main-seat.png", 20, 21, true, true)));
            // END TODO
            btn.getStyleClass().add("seatButton");
            Tooltip tip = new Tooltip("Seat " + (rowInd + 22) + "" + curLet);
            btn.setTooltip(tip);
            String seat = (rowInd + 22) + "" + curLet;
            btn.setOnAction(new EventHandler<ActionEvent>() {
                String[] selectedSeats = new String[9];
                @Override
                public void handle(ActionEvent event) {
                    // We need to track which seat row and letter this is, then have it save it somewhere
                    // and it needs to check if they selected the number of seats they wanted, then progress to next
                    boolean wasSelected = false;
                    for (int i=0; i < selectedSeats.length; i++) {
                        if (selectedSeats[i] !=null) {
                            // It was selected already
                            wasSelected = true;
                        }
                    }
                    if (!wasSelected) {
                        // Wasn't selected, set it selected
                        System.out.println("Added to selected count");
                        System.out.println("The select count is: " + main.getSeatsSelCount());
                        main.setSeatsSelCount(main.getSeatsSelCount() + 1);
                        for (int i=0; i < selectedSeats.length; i++) {
                            if (selectedSeats[i] == null) {
                                selectedSeats[i] = seat;
                                break;
                            }
                        }
                        btn.setGraphic(new ImageView(new Image("com/jaredscarito/airlineoracle/view/taken-main-seat.png", 20, 21, true, true)));
                    } else {
                        // Unselect it
                        System.out.println("Removed from selected count");
                        main.setSeatsSelCount(main.getSeatsSelCount() - 1);
                        for (int i=0; i < selectedSeats.length; i++) {
                            if (selectedSeats[i] != null && selectedSeats[i].equals(seat)) {
                                selectedSeats[i] = null;
                                break;
                            }
                        }
                        btn.setGraphic(new ImageView(new Image("com/jaredscarito/airlineoracle/view/empty-main-seat.png", 20, 21, true, true)));
                    }
                    //System.out.println("The length of selected is: " + selected);
                    if (main.getPassengerCount() == main.getSeatsSelCount()) {
                        // They have selected the right count, show the button to go to next page
                        // TODO Change this to a button
                        main.getLoadingControl().start(main.getInputInfoController());
                    }
                    System.out.println("You are clicking " + "Seat " + seat); //TODO  DEBUG - Get rid of
                }
            });
            buttonGrid.add(btn, colInd, rowInd);
            if (curLet == 'A' || curLet == 'B') {
                btn.setTranslateY(-5);
            }
            if(curLet == 'C' || curLet == 'D' || curLet == 'E') {
                // Spacing to the left via translateX
                btn.setTranslateX(25);
            } else
            if (curLet == 'F' || curLet == 'G') {
                // Spacing to the left via translateX
                btn.setTranslateX(48);
                btn.setTranslateY(-5);
            }
            colInd++;
            if (currLetIndex == letters.length()) {
                currLetIndex = 0;
                rowInd++;
                colInd = 0;
                if (i == 139) {
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