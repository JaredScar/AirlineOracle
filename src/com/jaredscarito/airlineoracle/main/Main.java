package com.jaredscarito.airlineoracle.main;

import com.jaredscarito.airlineoracle.controller.DataController;
import com.jaredscarito.airlineoracle.controller.SeatController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {

    private static GridPane mainPanel;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("../view/Main.fxml"));
        primaryStage.setTitle("Delta Airlines Manager");
        Toolkit tk = Toolkit.getDefaultToolkit();
        mainPanel = new GridPane();
        new DataController(this);
        new SeatController(this);
        primaryStage.setScene(new Scene(mainPanel, tk.getScreenSize().getWidth() - 100, (tk.getScreenSize().getHeight()) - 150));
        primaryStage.getScene().getStylesheets().add("com/jaredscarito/airlineoracle/view/style.css");
        primaryStage.show();
    }

    public GridPane getMainPanel() {
        return mainPanel;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
