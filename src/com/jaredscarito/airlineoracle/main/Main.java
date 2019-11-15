package com.jaredscarito.airlineoracle.main;

import com.jaredscarito.airlineoracle.controller.LoadingController;
import com.jaredscarito.airlineoracle.controller.SeatSelectController;
import com.jaredscarito.airlineoracle.controller.SelectionController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {

    private Stage primary;
    private LoadingController loadingControl;
    private SeatSelectController seatSelectController;

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
