package com.jaredscarito.airlineoracle.controller;

import com.jaredscarito.airlineoracle.main.Main;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.GridPane;

public class SelectionController {
    private Main main;

    public SelectionController(Main main) {
        this.main = main;
        GridPane grid = new GridPane();
        grid.setId("SelectionGrid");

        DatePicker picker = new DatePicker();
        picker.setId("DatePick");
        grid.add(picker, 0, 0);

        ComboBox passengers = new ComboBox();
        passengers.setId("PassengerSelect");
        passengers.getItems().add("1 Passenger");
        passengers.getItems().add("2 Passenger");
        passengers.getItems().add("3 Passenger");
        passengers.getItems().add("4 Passenger");
        passengers.getItems().add("5 Passenger");
        passengers.getItems().add("6 Passenger");
        passengers.getItems().add("7 Passenger");
        passengers.getItems().add("8 Passenger");
        passengers.getItems().add("9 Passenger");
        grid.add(passengers, 0, 1);

        Button submit = new Button("Submit");
        submit.setId("SubmitButton");
        grid.add(submit, 0, 2);

        grid.setVgap(40);

        main.getMainPanel().add(grid, 0, 0);
        main.getMainPanel().setAlignment(Pos.CENTER);
    }
}
