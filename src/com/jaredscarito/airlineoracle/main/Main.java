package com.jaredscarito.airlineoracle.main;

import com.jaredscarito.airlineoracle.controller.LoadingController;
import com.jaredscarito.airlineoracle.controller.SeatSelectController;
import com.jaredscarito.airlineoracle.controller.SelectionController;
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
    private SQLHelper helper;

    // Need to keep track of this data:
    private int passengerCount = 0;
    private LocalDate dateSelected = null;

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
        selectControl.start();
        LoadingController loadingControl = new LoadingController(this);
        SeatSelectController seatControl = new SeatSelectController(this);
        this.seatSelectController = seatControl;
        this.loadingControl = loadingControl;
        // Controllers END
        primaryStage.getScene().getStylesheets().add("com/jaredscarito/airlineoracle/view/style.css");
        primaryStage.show();

        primaryStage.getIcons().add(new Image("com/jaredscarito/airlineoracle/view/delta-logo-favicon.png"));
        try {
            this.helper = new SQLHelper("ip", 3306, "servicename", "username", "port");
        } catch (SQLException e) {
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
        return tk.getScreenSize().getHeight() - 80;
    }

    public double getScreenWidth() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        return tk.getScreenSize().getWidth() - 80;
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

    public static void main(String[] args) {
        launch(args);
    }
}
