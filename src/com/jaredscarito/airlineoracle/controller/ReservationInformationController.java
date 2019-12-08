package com.jaredscarito.airlineoracle.controller;

import com.jaredscarito.airlineoracle.main.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.sql.ResultSet;

public class ReservationInformationController implements Controller {
    private Main main;
    public ReservationInformationController(Main main) {
        this.main = main;
    }

    @Override
    public void start() {
        GridPane mainPanel = new GridPane();
        mainPanel.setId("resMain");
        GridPane grid = new GridPane();
        grid.setId("resContain");
        grid.setVgap(20);

        Image deltaLogo = new Image("com/jaredscarito/airlineoracle/view/delta-airlines-logo.png");
        ImageView img = new ImageView(deltaLogo);
        img.setId("logo");
        img.setFitHeight(120);
        img.setFitWidth(260);
        grid.add(img, 0, 1, 2, 1);

        grid.setHgap(15);

        Label yourOrder = new Label("Your Order");
        yourOrder.setId("yourOrder");
        grid.add(yourOrder, 0, 2);

        Label totalTicks = new Label("Total Tickets:");
        totalTicks.setId("ticketsLab");
        Label tickets = new Label("NOT CONNECTED TO SQL");
        tickets.setId("ticketCount");
        grid.add(totalTicks, 0, 3);
        grid.add(tickets, 1, 3);

        Label seatsSelected = new Label("Seats Selected:");
        seatsSelected.setId("seatsSelected");

        Label seatsSelectedDef = new Label("NOT CONNECTED TO SQL");
        seatsSelectedDef.setId("seatsSelectedDef");
        grid.add(seatsSelected, 0, 4);
        grid.add(seatsSelectedDef, 1, 4);

        // SQL for ticket count for milesID, for seats selected
        StringBuilder ticketsStr = new StringBuilder();
        try {
            ResultSet res = main.getHelper().runQuery("SELECT COUNT(*) FROM Reservations WHERE milesID = '" + main.getMilesID() + "'");
            res.next();
            seatsSelectedDef.setText(res.getInt(1) + "");
            res = main.getHelper().runQuery("SELECT seat FROM Reservations WHERE milesID = '" + main.getMilesID() + "'");
            while (res.next()) {
                String seat = res.getString(1);
                ticketsStr.append(seat).append(", ");
            }
            String str = ticketsStr.substring(0, ticketsStr.length() - 2);
            tickets.setText(str);
        } catch (Exception ex) {
            ex.printStackTrace(); // SQL Exception probably
        }

        Button returnButton = new Button("Return");
        returnButton.setId("returnButton");
        returnButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                main.getLoadingControl().start(main.getMilesIDController());
            }
        });
        grid.add(returnButton, 0, 5, 2, 1);

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
