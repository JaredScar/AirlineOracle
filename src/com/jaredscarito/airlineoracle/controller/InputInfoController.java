package com.jaredscarito.airlineoracle.controller;

import com.jaredscarito.airlineoracle.main.Main;
import com.jaredscarito.airlineoracle.model.ApplicableRules;
import com.jaredscarito.airlineoracle.model.MathModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Random;

import static java.time.temporal.ChronoUnit.DAYS;

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
        img.setFitHeight(150);
        img.setFitWidth(450);
        grid.setVgap(15);
        grid.add(img, 0, 0, 4, 1);
        grid.setGridLinesVisible(false);

        Label merchantName = new Label("Merchant Name:");
        merchantName.getStyleClass().add("inputLabel");
        Label merchantNameDef = new Label("DELTA AIRLINES");
        merchantNameDef.setStyle("-fx-padding: 0px 0px 0px 50px;");
        grid.add(merchantName, 0, 1);
        grid.add(merchantNameDef, 1, 1, 2, 1);

        Label orderInfo = new Label("Total Tickets:");
        orderInfo.getStyleClass().add("inputLabel");
        Label totalTickets = new Label(main.getPassengerCount() + "");
        totalTickets.setStyle("-fx-padding: 0px 0px 0px 50px;");
        grid.add(orderInfo, 0, 2);
        grid.add(totalTickets, 1, 2, 2, 1);

        Label priceLabel = new Label("Price:");
        priceLabel.getStyleClass().add("inputLabel");
        double price = 100;
        try {
            MathModel.getCoefficients(main);
            // a + b1 (availableSeats) + b2 (days_to_flight)
            int availableSeats = 0;
            double priceFromRegress = 100;
            LocalDate localDate = LocalDate.now();
            long days_to_flight = DAYS.between(localDate, main.getDateSelected());
            try {
                ResultSet res = main.getHelper().runQuery("SELECT COUNT(*) FROM Reservations");
                res.next();
                availableSeats = res.getInt(1);
                priceFromRegress = MathModel.a + MathModel.b1 * availableSeats + MathModel.b2 * days_to_flight;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            price = priceFromRegress * main.getPassengerCount();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        price = ApplicableRules.applyHolidaySurgeCharge(price, main.getDateSelected());
        price = ApplicableRules.applySummerSurge(price, main.getDateSelected());
        price = ApplicableRules.applyWeekendSurge(price, main.getDateSelected());
        DecimalFormat decFormat = new DecimalFormat("00.00");
        Label prices = new Label("$" + decFormat.format(price));
        main.setPrice(price);
        prices.setStyle("-fx-padding: 0px 0px 0px 50px;");
        grid.add(priceLabel, 0, 3);
        grid.add(prices, 1, 3, 2, 1);

        Label lockedLabel = new Label("\uD83D\uDD12 This is not a real input form. Please do not put your actual information.");
        grid.add(lockedLabel, 0, 4, 4, 1);

        Label cardNumber = new Label("Card Number:");
        cardNumber.getStyleClass().add("inputLabel");
        TextField cardNumberDef = new TextField();
        grid.add(cardNumber, 0, 5);
        grid.add(cardNumberDef, 1, 5, 2, 1);

        Label expiryDate = new Label("Expiry Date:");
        expiryDate.getStyleClass().add("inputLabel");
        ComboBox monthSelect = new ComboBox();
        monthSelect.getItems().addAll("January", "February", "March", "April", "May", "June", "July",
                "August", "September", "October", "November", "December");
        monthSelect.setPromptText("Month");
        monthSelect.setPrefWidth(120);
        ComboBox yearSelect = new ComboBox();
        yearSelect.getItems().addAll("2020", "2021", "2022", "2023", "2024", "2025", "2026");
        yearSelect.setPromptText("Year");
        grid.add(expiryDate, 0, 6);
        grid.add(monthSelect, 1, 6);
        grid.add(yearSelect, 2, 6);

        Label csvLab = new Label("CSV:");
        csvLab.getStyleClass().add("inputLabel");
        TextField csvDef = new TextField();
        csvDef.setPrefWidth(120);
        grid.add(csvLab, 0, 7);
        grid.add(csvDef, 1, 7, 1, 1);

        Label infoLab = new Label("Address Information");
        infoLab.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        grid.add(infoLab, 0, 8, 3, 1);

        Label addressLab = new Label("Address:");
        addressLab.getStyleClass().add("inputLabel");
        TextField addressDef = new TextField();
        grid.add(addressLab, 0, 9);
        grid.add(addressDef, 1, 9, 2, 1);

        Label cityTownLab = new Label("City/Town:");
        cityTownLab.getStyleClass().add("inputLabel");
        TextField cityTownDef = new TextField();
        grid.add(cityTownLab, 0, 10);
        grid.add(cityTownDef, 1, 10, 2, 1);

        Label stateLab = new Label("State/Province:");
        stateLab.getStyleClass().add("inputLabel");
        TextField stateDef = new TextField();
        grid.add(stateLab, 0, 11);
        grid.add(stateDef, 1, 11, 2, 1);

        Label zipLab = new Label("Post/Zip Code:");
        zipLab.getStyleClass().add("inputLabel");
        TextField zipDef = new TextField();
        grid.add(zipLab, 0, 12);
        grid.add(zipDef, 1, 12, 2, 1);

        Label countryLab = new Label("Country:");
        countryLab.getStyleClass().add("inputLabel");
        ComboBox countrySel = new ComboBox();
        String[] countries = getCountries().split("\n");
        countrySel.getItems().addAll(countries);
        countrySel.setPrefWidth(120);
        grid.add(countryLab, 0, 13);
        grid.add(countrySel, 1, 13, 1, 1);

        Button submitBtn = new Button("Confirm Order");
        submitBtn.setPrefWidth(140);
        submitBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // It was clicked, we need to calculate the price of tickets, and everything else
                if (cardNumberDef.getText().length() > 0 && expiryDate.getText().length() > 0 &&
                !monthSelect.getSelectionModel().isEmpty() && !yearSelect.getSelectionModel().isEmpty() &&
                csvDef.getText().length() > 0 && addressDef.getText().length() > 0 && cityTownDef.getText().length() > 0 &&
                stateDef.getText().length() > 0 && zipDef.getText().length() > 0 && !countrySel.getSelectionModel().isEmpty()) {
                    // INSERT DATA, MOVE TO NEXT PAGE
                    try {
                        String code = getMilesID();
                        boolean alreadyHasCode = false;
                        while (!alreadyHasCode) {
                            code = getMilesID();
                            ResultSet res = main.getHelper().runQuery("SELECT COUNT(*) FROM Reservations WHERE milesID " +
                                    "= '" + code + "'");
                            res.next();
                            if (res.getInt(1) == 0) {
                                alreadyHasCode = true;
                                main.setMilesID(code);
                            }
                        }
                        for (String seat : main.getSeatsSelected()) {
                            System.out.println("Trying to insert seat: " + seat);
                            if (seat !=null) {
                                if (main.getHelper().runStatement("INSERT INTO Reservations VALUES ('" + seat + "', '" + code + "')")) {
                                    // It ran
                                    main.getHelper().runStatement("COMMIT");
                                    System.out.println("Inserted the Data");
                                } else {
                                    Alert alert;
                                    alert = new Alert(Alert.AlertType.ERROR);
                                    alert.setTitle("ERROR");
                                    alert.setHeaderText("PROBLEM ON SQL INSERTION");
                                    alert.setContentText("Could not insert seat " + seat + " into Reservations Table...");
                                    alert.showAndWait();
                                }
                            }
                        }
                        main.setSeatsSelected(new String[9]);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        Alert alert;
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("ERROR");
                        alert.setHeaderText("PROBLEM ON SQL CONNECTION");
                        alert.setContentText("We are not connected to the SQL database :(");
                        alert.showAndWait();
                    }
                    // Go to next page
                    main.getLoadingControl().start(main.getResInformationController());
                } else {
                    Alert alert;
                    alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("NOTICE");
                    alert.setHeaderText("ALL FIELDS MUST BE FILLED OUT");
                    alert.setContentText(null);
                    alert.showAndWait();
                }
            }
        });
        grid.add(submitBtn, 3, 14);

        mainPanel.add(grid, 0, 1);

        mainPanel.setAlignment(Pos.CENTER);

        double width, height;
        width = main.getScreenWidth();
        height = main.getScreenHeight();
        main.getPrimaryStage().setScene(new Scene(mainPanel, width, height));
        main.getPrimaryStage().getScene().getStylesheets().add("com/jaredscarito/airlineoracle/view/style.css");
        main.getPrimaryStage().show();
    }

    public String getMilesID() {
        String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rand = new Random();
        String code = "";
        for (int i=0; i < 15; i++) {
            if (i % 2 == 0) {
                // Alphabet letter
                code += alpha.charAt( rand.nextInt((alpha.length() - 1)) );
            } else {
                // Number letter
                code += rand.nextInt(9);
            }
        }
        return code;
    }

    public String getCountries() {
        return "Afghanistan\n" +
                "Albania\n" +
                "Algeria\n" +
                "Andorra\n" +
                "Angola\n" +
                "Anguilla\n" +
                "Antigua & Barbuda\n" +
                "Argentina\n" +
                "Armenia\n" +
                "Australia\n" +
                "Austria\n" +
                "Azerbaijan\n" +
                "Bahamas\n" +
                "Bahrain\n" +
                "Bangladesh\n" +
                "Barbados\n" +
                "Belarus\n" +
                "Belgium\n" +
                "Belize\n" +
                "Benin\n" +
                "Bermuda\n" +
                "Bhutan\n" +
                "Bolivia\n" +
                "Bosnia & Herzegovina\n" +
                "Botswana\n" +
                "Brazil\n" +
                "Brunei Darussalam\n" +
                "Bulgaria\n" +
                "Burkina Faso\n" +
                "Burundi\n" +
                "Cambodia\n" +
                "Cameroon\n" +
                "Canada\n" +
                "Cape Verde\n" +
                "Cayman Islands\n" +
                "Central African Republic\n" +
                "Chad\n" +
                "Chile\n" +
                "China\n" +
                "China - Hong Kong / Macau\n" +
                "Colombia\n" +
                "Comoros\n" +
                "Congo\n" +
                "Congo, Democratic Republic of (DRC)\n" +
                "Costa Rica\n" +
                "Croatia\n" +
                "Cuba\n" +
                "Cyprus\n" +
                "Czech Republic\n" +
                "Denmark\n" +
                "Djibouti\n" +
                "Dominica\n" +
                "Dominican Republic\n" +
                "Ecuador\n" +
                "Egypt\n" +
                "El Salvador\n" +
                "Equatorial Guinea\n" +
                "Eritrea\n" +
                "Estonia\n" +
                "Eswatini\n" +
                "Ethiopia\n" +
                "Fiji\n" +
                "Finland\n" +
                "France\n" +
                "French Guiana\n" +
                "Gabon\n" +
                "Gambia, Republic of The\n" +
                "Georgia\n" +
                "Germany\n" +
                "Ghana\n" +
                "Great Britain\n" +
                "Greece\n" +
                "Grenada\n" +
                "Guadeloupe\n" +
                "Guatemala\n" +
                "Guinea\n" +
                "Guinea-Bissau\n" +
                "Guyana\n" +
                "Haiti\n" +
                "Honduras\n" +
                "Hungary\n" +
                "Iceland\n" +
                "India\n" +
                "Indonesia\n" +
                "Iran\n" +
                "Iraq\n" +
                "Israel and the Occupied Territories\n" +
                "Italy\n" +
                "Ivory Coast (Cote d'Ivoire)\n" +
                "Jamaica\n" +
                "Japan\n" +
                "Jordan\n" +
                "Kazakhstan\n" +
                "Kenya\n" +
                "Korea, Democratic Republic of (North Korea)\n" +
                "Korea, Republic of (South Korea)\n" +
                "Kosovo\n" +
                "Kuwait\n" +
                "Kyrgyz Republic (Kyrgyzstan)\n" +
                "Laos\n" +
                "Latvia\n" +
                "Lebanon\n" +
                "Lesotho\n" +
                "Liberia\n" +
                "Libya\n" +
                "Liechtenstein\n" +
                "Lithuania\n" +
                "Luxembourg\n" +
                "Madagascar\n" +
                "Malawi\n" +
                "Malaysia\n" +
                "Maldives\n" +
                "Mali\n" +
                "Malta\n" +
                "Martinique\n" +
                "Mauritania\n" +
                "Mauritius\n" +
                "Mayotte\n" +
                "Mexico\n" +
                "Moldova, Republic of\n" +
                "Monaco\n" +
                "Mongolia\n" +
                "Montenegro\n" +
                "Montserrat\n" +
                "Morocco\n" +
                "Mozambique\n" +
                "Myanmar/Burma\n" +
                "Namibia\n" +
                "Nepal\n" +
                "New Zealand\n" +
                "Nicaragua\n" +
                "Niger\n" +
                "Nigeria\n" +
                "North Macedonia, Republic of\n" +
                "Norway\n" +
                "Oman\n" +
                "Pacific Islands\n" +
                "Pakistan\n" +
                "Panama\n" +
                "Papua New Guinea\n" +
                "Paraguay\n" +
                "Peru\n" +
                "Philippines\n" +
                "Poland\n" +
                "Portugal\n" +
                "Puerto Rico\n" +
                "Qatar\n" +
                "Reunion\n" +
                "Romania\n" +
                "Russian Federation\n" +
                "Rwanda\n" +
                "Saint Kitts and Nevis\n" +
                "Saint Lucia\n" +
                "Saint Vincent and the Grenadines\n" +
                "Samoa\n" +
                "Sao Tome and Principe\n" +
                "Saudi Arabia\n" +
                "Senegal\n" +
                "Serbia\n" +
                "Seychelles\n" +
                "Sierra Leone\n" +
                "Singapore\n" +
                "Slovak Republic (Slovakia)\n" +
                "Slovenia\n" +
                "Solomon Islands\n" +
                "Somalia\n" +
                "South Africa\n" +
                "South Sudan\n" +
                "Spain\n" +
                "Sri Lanka\n" +
                "Sudan\n" +
                "Suriname\n" +
                "Sweden\n" +
                "Switzerland\n" +
                "Syria\n" +
                "Tajikistan\n" +
                "Tanzania\n" +
                "Thailand\n" +
                "Netherlands\n" +
                "Timor Leste\n" +
                "Togo\n" +
                "Trinidad & Tobago\n" +
                "Tunisia\n" +
                "Turkey\n" +
                "Turkmenistan\n" +
                "Turks & Caicos Islands\n" +
                "Uganda\n" +
                "Ukraine\n" +
                "United Arab Emirates\n" +
                "United States of America (USA)\n" +
                "Uruguay\n" +
                "Uzbekistan\n" +
                "Venezuela\n" +
                "Vietnam\n" +
                "Virgin Islands (UK)\n" +
                "Virgin Islands (US)\n" +
                "Yemen\n" +
                "Zambia\n" +
                "Zimbabwe";
    }
}
