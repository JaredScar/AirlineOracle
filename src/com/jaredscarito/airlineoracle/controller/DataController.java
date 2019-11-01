package com.jaredscarito.airlineoracle.controller;

import com.jaredscarito.airlineoracle.main.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class DataController {
    public DataController(Main main) {
        // Input the data here
        /**
         * Date booking the flight
         * Amount of seats booking
         * Submit Button
         */
        GridPane grid = new GridPane();

        Label lab = new Label("Your Selection");
        lab.getStyleClass().add("sectionLabel");

        Label dateSel = new Label("Flight Date:");
        dateSel.setId("dateLabel");
        DatePicker datePick = new DatePicker();
        datePick.setId("datePicker");

        Label seatCount = new Label("# of seats:");
        seatCount.setId("seatLabel");
        Button decreaseNum = new Button("<");
        decreaseNum.getStyleClass().add("creaseButton");
        Label numLabel = new Label("1");
        decreaseNum.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (Integer.parseInt(numLabel.getText()) > 1) {
                    numLabel.setText(String.valueOf(Integer.parseInt(numLabel.getText()) - 1));
                }
            }
        });
        numLabel.setId("numberLabel");
        Button increaseNum = new Button(">");
        increaseNum.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (Integer.parseInt(numLabel.getText()) + 1 <= 9) {
                    numLabel.setText(String.valueOf(Integer.parseInt(numLabel.getText()) + 1));
                }
            }
        });
        increaseNum.getStyleClass().add("creaseButton");


        Button submitBtn = new Button("Submit");
        submitBtn.setId("dataSubmit");

        grid.setId("dataGrid");
        grid.setHgap(0.0);
        grid.setVgap(0.0);
        grid.add(lab, 0, 0, 3, 1);
        grid.add(dateSel, 0, 2, 1, 1);
        grid.add(datePick, 1, 2);
        grid.add(seatCount, 0, 3, 1, 1);
        //grid.add(decreaseNum, 1, 3);
        GridPane seats = new GridPane();
        seats.setVgap(0.0);
        seats.setHgap(18.0);
        seats.add(decreaseNum, 0, 0);
        seats.add(numLabel, 1, 0);
        seats.add(increaseNum, 2, 0);
        grid.addRow(3, seats);
        //grid.add(numLabel, 2, 3);
        //grid.add(increaseNum, 3, 3);
        grid.add(submitBtn, 1, 5);
        main.getMainPanel().add(grid, 0, 0);
    }
}
