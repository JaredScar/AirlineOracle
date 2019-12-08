package com.jaredscarito.airlineoracle.controller;

import com.jaredscarito.airlineoracle.main.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.time.LocalDate;

public class SelectionController implements Controller {
    private Main main;

    public SelectionController(Main main) {
        this.main = main;
    }

    @Override
    public void start() {
        main.setPassengerCount(0);
        main.setDateSelected(null);
        main.setSeatsSelected(new String[] {});
        main.setSeatsSelCount(0);
        GridPane mainPanel = new GridPane();
        mainPanel.setId("main");

        GridPane grid = new GridPane();
        grid.setId("SelectionGrid");

        Image deltaLogo = new Image("com/jaredscarito/airlineoracle/view/delta-airlines-logo.png");
        ImageView img = new ImageView(deltaLogo);
        img.setId("logo");
        img.setFitHeight(100);
        img.setFitWidth(240);
        grid.add(img, 0, 1);

        Label flightInfoFrom = new Label("FROM: JFK");
        Label flightInfoTo = new Label("TO: LAX");
        flightInfoFrom.getStyleClass().add("infoLabel");
        flightInfoTo.getStyleClass().add("infoLabel");
        grid.add(flightInfoFrom, 0, 2);
        grid.add(flightInfoTo, 0, 3);

        DatePicker picker = new DatePicker();
        picker.setId("DatePick");
        picker.setDayCellFactory(dp -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();

                setDisable(empty || date.compareTo(today) < 0 );
            }
        });
        picker.getEditor().setDisable(true);
        picker.getEditor().setStyle("-fx-opacity: 1.0;");
        picker.getEditor().setText("Select Departure Date");
        grid.add(picker, 0, 4);

        ComboBox passengers = new ComboBox();
        passengers.setPromptText("Select Passenger Amount");
        passengers.setId("PassengerSelect");
        passengers.getItems().add("1 Passenger");
        passengers.getItems().add("2 Passengers");
        passengers.getItems().add("3 Passengers");
        passengers.getItems().add("4 Passengers");
        passengers.getItems().add("5 Passengers");
        passengers.getItems().add("6 Passengers");
        passengers.getItems().add("7 Passengers");
        passengers.getItems().add("8 Passengers");
        passengers.getItems().add("9 Passengers");
        grid.add(passengers, 0, 5);

        Button submit = new Button("Submit");
        submit.setId("SubmitButton");
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LocalDate val = picker.getValue();
                String passengerVal = "";
                if (passengers.getValue() != null) {
                    passengerVal = passengers.getValue().toString().replace("Passengers", "")
                            .replace("Passenger", "").replace(" ", "");
                }
                submit.setDisable(true);
                Alert alert;
                if (val == null) {
                    // They didn't put a date, bring an error
                    alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("NOTICE");
                    alert.setHeaderText("You need to enter a departure date");
                    alert.setContentText("The process can not be continued with until a departure date is specified...");
                    alert.showAndWait();
                } else
                    if (passengerVal.length() == 0) {
                        // They didn't pick number of passengers, bring an error
                        alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("NOTICE");
                        alert.setHeaderText("You need to select the amount of passengers you will be flying with");
                        alert.setContentText("The process can not be continued until the passenger count is selected...");
                        alert.showAndWait();
                    }
                if (val !=null && passengers.getValue() != null) {
                    // Move onto next screen
                    main.setDateSelected(val);
                    main.setPassengerCount(Integer.parseInt(passengerVal));
                    main.getLoadingControl().start(main.getSeatSelectController());
                }
                submit.setDisable(false);
            }
        });
        grid.add(submit, 0, 6);

        grid.setVgap(40);

        mainPanel.add(grid, 0, 0);
        mainPanel.setAlignment(Pos.CENTER);
        double width, height;
        width = main.getScreenWidth();
        height = main.getScreenHeight();
        main.getPrimaryStage().setScene(new Scene(mainPanel, width, height));
        main.getPrimaryStage().getScene().getStylesheets().add("com/jaredscarito/airlineoracle/view/style.css");
        main.getPrimaryStage().show();
    }
}
