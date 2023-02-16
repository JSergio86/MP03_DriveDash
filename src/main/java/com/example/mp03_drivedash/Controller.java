package com.example.mp03_drivedash;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Controller extends Application {
    Stage stage;
    Juego juegoComenzar;


    public void clickComenzar(ActionEvent event) throws Exception {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();
        stage = new Stage();
        juegoComenzar = new Juego();
        juegoComenzar.start(stage);

    }

    @Override
    public void start(Stage stage) throws Exception {

    }
}