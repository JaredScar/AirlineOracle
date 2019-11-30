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
        monthSelect.getItems().addAll("January", "February", "March", "April", "May", "June", "July",
                "August", "September", "October", "November", "December");
        monthSelect.setPromptText("Month");
        ComboBox yearSelect = new ComboBox();
        yearSelect.getItems().addAll("2020", "2021", "2022", "2023", "2024", "2025", "2026");
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
        infoLab.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
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
        String[] countries = getCountries().split("\n");
        countrySel.getItems().addAll(countries);
        countrySel.setPrefWidth(120);
        grid.add(countryLab, 0, 12);
        grid.add(countrySel, 1, 12, 1, 1);

        Button submitBtn = new Button("Confirm Order");
        submitBtn.setPrefWidth(140);
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
