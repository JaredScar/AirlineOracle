package com.jaredscarito.airlineoracle.controller;

import com.jaredscarito.airlineoracle.main.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MilesIDController implements Controller {
    private Main main;
    public MilesIDController(Main main) {
        this.main = main;
    }

    @Override
    public void start() {
        GridPane mainPanel = new GridPane();
        mainPanel.setId("milesMain");
        GridPane grid = new GridPane();
        grid.setId("milesContain");
        grid.setVgap(20);


        Label loginToDelta = new Label("Check Reservations");
        loginToDelta.setAlignment(Pos.CENTER);
        Label allFieldsReq = new Label("All fields are required");
        TextField loginField = new TextField("Reservation ID");
        loginField.setAlignment(Pos.CENTER);
        loginToDelta.setId("loginToDelta");
        allFieldsReq.setId("allFieldsReq");
        loginField.setId("loginField");
        grid.add(loginToDelta, 0, 1);
        grid.add(allFieldsReq, 0, 2);
        grid.add(loginField, 0, 3);

        Button loginButton = new Button("Check Reservation");
        loginButton.setId("loginButton");
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                /**/ // TODO Uncomment this for final program
                Alert alert;
                try {
                    ResultSet res = main.getHelper().runQuery("SELECT COUNT(*) FROM Reservations WHERE MILESID = '" +
                            loginField.getText().replace(" ", "") + "'");
                    System.out.println("SELECT COUNT(*) FROM Reservations WHERE MILESID = '" +
                            loginField.getText().replace(" ", "") + "'");
                    System.out.println("Checking Reservation ID with ID " + loginField.getText());
                    try {
                        res.next();
                        System.out.print("ResultSet returns " + res.getInt(1));
                        if (res.getInt(1) >= 1) {
                            // It has a reservation, move onto ReservationInfo
                            main.setMilesID(loginField.getText().replace(" ", ""));
                            main.getLoadingControl().start(main.getResInformationController());
                        } else {
                            // Doesn't have a reservation
                            alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("NOTICE");
                            alert.setHeaderText("Issue with Reservation ID");
                            alert.setContentText("We could not find the reservation with the specified Reservation ID :(");
                            alert.showAndWait();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("ERROR");
                        alert.setHeaderText("Encountered an Error");
                        alert.setContentText("Sadly, we encountered an error on our end with the SQL :(");
                        alert.showAndWait();
                    }
                } catch (NullPointerException ex) {
                    ex.printStackTrace();
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText("Encountered an Error");
                    alert.setContentText("Sadly, we encountered an error on our end with the SQL :(");
                    alert.showAndWait();
                }
                /**/
            }
        });
        grid.add(loginButton, 0, 4);

        Button skipButton = new Button("Skip");
        skipButton.setId("skipButton");
        skipButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                main.getLoadingControl().start(main.getSelectionController());
            }
        });
        grid.add(skipButton, 0, 5);

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
