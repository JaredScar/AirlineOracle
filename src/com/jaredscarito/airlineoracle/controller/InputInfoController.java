package com.jaredscarito.airlineoracle.controller;

import com.jaredscarito.airlineoracle.main.Main;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class InputInfoController implements Controller {
    private Main main;
    public InputInfoController(Main main) {
        this.main = main;
    }

    @Override
    public void start() {
        GridPane mainPanel = new GridPane();
        mainPanel.setId("InputMainPanel");

        GridPane grid = new GridPane();
        grid.setId("InputGrid");

        Image deltaLogo = new Image("com/jaredscarito/airlineoracle/view/delta-airlines-logo.png");
        ImageView img = new ImageView(deltaLogo);
        img.setId("InputLogo");
        img.setFitHeight(200);
        img.setFitWidth(600);
        grid.add(img, 0, 0, 3, 1);

        Label merchantName = new Label("Merchant Name:");
        merchantName.getStyleClass().add("inputLabel");
        Label merchantNameDef = new Label("DELTA AIRLINES");
        grid.add(merchantName, 0, 1);
        grid.add(merchantNameDef, 1, 1);

        Label orderInfo = new Label("Total Tickets:");
        orderInfo.getStyleClass().add("inputLabel");
        Label totalTickets = new Label(main.getPassengerCount() + "");
        grid.add(orderInfo, 0, 2);
        grid.add(totalTickets, 1, 2);

        Label lockedLabel = new Label("\uD83D\uDD12 This is not a real input form. Please do not put your actual information.");
        grid.add(lockedLabel, 0, 3, 4, 1);

        Label cardNumber = new Label("Card Number:");
        cardNumber.getStyleClass().add("inputLabel");
        TextField cardNumberDef = new TextField();
        grid.add(cardNumber, 0, 4);
        grid.add(cardNumberDef, 1, 4, 2, 1);

        Label expiryDate = new Label("Expiry Date:");
        expiryDate.getStyleClass().add("inputLabel");
        ComboBox monthSelect = new ComboBox();
        monthSelect.setPromptText("Month");
        ComboBox yearSelect = new ComboBox();
        yearSelect.setPromptText("Year");
        grid.add(expiryDate, 0, 5);
        grid.add(monthSelect, 1, 5);
        grid.add(yearSelect, 2, 5);

        Label csvLab = new Label("CSV:");
        csvLab.getStyleClass().add("inputLabel");
        TextField csvDef = new TextField();
        grid.add(csvLab, 0, 6);
        grid.add(csvDef, 1, 6, 2, 1);

        Label infoLab = new Label("Address Information");
        grid.add(infoLab, 0, 7, 3, 1);

        Label addressLab = new Label("Address:");
        addressLab.getStyleClass().add("inputLabel");
        TextField addressDef = new TextField();
        grid.add(addressLab, 0, 8);
        grid.add(addressDef, 1, 8, 2, 1);

        Label cityTownLab = new Label("City/Town:");
        cityTownLab.getStyleClass().add("inputLabel");
        TextField cityTownDef = new TextField();
        grid.add(cityTownLab, 0, 9);
        grid.add(cityTownDef, 1, 9, 2, 1);

        Label stateLab = new Label("State/Province:");
        stateLab.getStyleClass().add("inputLabel");
        TextField stateDef = new TextField();
        grid.add(stateLab, 0, 10);
        grid.add(stateDef, 1, 10, 2, 1);

        Label zipLab = new Label("Post/Zip Code:");
        zipLab.getStyleClass().add("inputLabel");
        TextField zipDef = new TextField();
        grid.add(zipLab, 0, 11);
        grid.add(zipDef, 1, 11, 2, 1);

        Label countryLab = new Label("Country:");
        countryLab.getStyleClass().add("inputLabel");
        ComboBox countrySel = new ComboBox();
        countrySel.setPromptText("Select Country");
        grid.add(countryLab, 0, 12);
        grid.add(countrySel, 1, 12, 1, 1);

        Button submitBtn = new Button("Confirm Order");
        grid.add(submitBtn, 3, 13);

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
