package com.jaredscarito.airlineoracle.main;

import com.jaredscarito.airlineoracle.controller.*;
import com.jaredscarito.airlineoracle.model.MathModel;
import com.jaredscarito.airlineoracle.model.SQLHelper;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDate;

public class Main extends Application {

    private Stage primary;
    private LoadingController loadingControl;
    private SeatSelectController seatSelectController;
    private InputInfoController inputInfoController;
    private MilesIDController milesIDController;
    private SelectionController selectionController;
    private ReservationInformationController resInformationController;
    private SQLHelper helper;

    private double price = 0.0;

    private String milesID = "";

    public String getMilesID() {
        return milesID;
    }
    public void setMilesID(String milesID) {
        this.milesID = milesID;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    // Need to keep track of this data:
    private int passengerCount = 0;
    private LocalDate dateSelected = null;
    private String[] seatsSelected = new String[9];
    private int seatsSelCount = 0;

    public void setPassengerCount(int count) {
        this.passengerCount = count;
    }
    public int getPassengerCount() {
        return this.passengerCount;
    }
    public void setDateSelected(LocalDate date) {
        this.dateSelected = date;
    }
    public LocalDate getDateSelected() {
        return this.dateSelected;
    }
    public void setSeatsSelected(String[] seats) {
        this.seatsSelected = seats;
    }
    public String[] getSeatsSelected() {
        return this.seatsSelected;
    }
    public void setSeatsSelCount(int count) {
        this.seatsSelCount = count;
    }
    public int getSeatsSelCount() {
        return this.seatsSelCount;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("../view/Main.fxml"));
        this.primary = primaryStage;
        primaryStage.setTitle("Delta Airlines Manager");
        Toolkit tk = Toolkit.getDefaultToolkit();
        primaryStage.setScene(new Scene(new GridPane(),
                tk.getScreenSize().getWidth() - 80,
                (tk.getScreenSize().getHeight()) - 80
        ));
        // Controllers START
        SelectionController selectControl = new SelectionController(this);
        LoadingController loadingControl = new LoadingController(this);
        SeatSelectController seatControl = new SeatSelectController(this);
        InputInfoController inputInfoController = new InputInfoController(this);
        MilesIDController milesIDController = new MilesIDController(this);
        ReservationInformationController resInformationController = new ReservationInformationController(this);
        this.seatSelectController = seatControl;
        this.inputInfoController = inputInfoController;
        this.loadingControl = loadingControl;
        this.milesIDController = milesIDController;
        this.resInformationController = resInformationController;
        this.selectionController = selectControl;
        milesIDController.start();
        // Controllers END
        primaryStage.getScene().getStylesheets().add("com/jaredscarito/airlineoracle/view/style.css");
        primaryStage.show();

        primaryStage.getIcons().add(new Image("com/jaredscarito/airlineoracle/view/delta-logo-favicon.png"));
        primaryStage.setResizable(false);
        try {
            this.helper = new SQLHelper("10.0.20.23",
                    1521, "ORCL12.campus.sjcny.edu",
                    "jscarito", "0341922");
            System.out.println("IT CONNECTED!");
        } catch (SQLException e) {
            helper = new SQLHelper();
            e.printStackTrace();
        }
        // TESTING:
        //MathModel.getCoefficients(this);
    }

    public Toolkit getToolKit() {
        return Toolkit.getDefaultToolkit();
    }

    public double getScreenHeight() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        return 800;
    }

    public double getScreenWidth() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        return 1400;
    }

    public SQLHelper getHelper() {
        return helper;
    }

    public Stage getPrimaryStage() {
        return this.primary;
    }

    public LoadingController getLoadingControl() {
        return this.loadingControl;
    }

    public SeatSelectController getSeatSelectController() {
        return this.seatSelectController;
    }

    public InputInfoController getInputInfoController() {
        return this.inputInfoController;
    }

    public SelectionController getSelectionController() {
        return this.selectionController;
    }

    public MilesIDController getMilesIDController() {
        return this.milesIDController;
    }

    public ReservationInformationController getResInformationController() {
        return this.resInformationController;
    }

    public static void main(String[] args) {
        launch(args);
    }
}