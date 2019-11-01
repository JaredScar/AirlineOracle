package com.jaredscarito.airlineoracle.controller;

import com.jaredscarito.airlineoracle.main.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class SeatController {
    private List<Button> seatTracker = new ArrayList<>();
    public SeatController(Main main) {
        // The seat selection area
        String alphabet = "ABCDEFG";
        // Seats start at 22
        Button seat;
        for (int i=22; i < 43; i++) {
            // Has all of alphabet
            for (int j=0; j < alphabet.length(); j++) {
                seat = new Button();
                seat.setText("" + i + alphabet.charAt(j));
                if (!seatAvailable("" + i + alphabet.charAt(j))) {
                    // The seat is not available, make it disabled
                    seat.setDisable(true);
                    seat.getStyleClass().add("SeatButton");
                    seat.getStyleClass().add("SeatButton-Disabled");
                } else {
                    seat.getStyleClass().add("SeatButton");
                }
                seat.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        // This will select the seat clicked
                        // TODO
                    }
                });
                if (i != 42) {
                    seatTracker.add(seat);
                }
                if ((i == 42 && j >= 2 && j <= 4)) {
                    seatTracker.add(seat);
                }
            }
        }
        GridPane gridLeft = new GridPane();
        int leftCt = 0;
        GridPane gridMid = new GridPane();
        int midCt = 0;
        GridPane gridRight = new GridPane();
        int rightCt = 0;
        int row = 0;
        // Loop through seats, add them to grid
        for (Button st : seatTracker) {
            if (st.getText().contains("A") || st.getText().contains("B")) {
                gridLeft.add(st, leftCt, row);
                leftCt++;
                if (leftCt == 2) {
                    leftCt = 0;
                }
            } else
                if (st.getText().contains("C") || st.getText().contains("D") || st.getText().contains("E")) {
                    gridMid.add(st, midCt, row);
                    midCt++;
                    if (midCt == 3) {
                        midCt = 0;
                    }
                } else
                    if (st.getText().contains("F") || st.getText().contains("G")) {
                        gridRight.add(st, rightCt, row);
                        rightCt++;
                        if (rightCt == 2) {
                            rightCt = 0;
                        }
                    }
                    if (st.getText().contains("G")) {
                        row++;
                    }
        }
        gridLeft.setId("planeLeft");
        gridMid.setId("planeMid");
        gridRight.setId("planeRight");
        main.getMainPanel().add(gridLeft, 1, 1);
        main.getMainPanel().add(gridMid, 2, 1);
        main.getMainPanel().add(gridRight, 3, 1);
    }

    public boolean seatAvailable(String seat) {
        // TODO SQL to check if seat is available or not
        return true;
    }
}
