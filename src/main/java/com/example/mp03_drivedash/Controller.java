package com.example.mp03_drivedash;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Controller extends Application {
    Stage stage;
    Juego juego;
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void clickComenzar() throws InterruptedException {
        stage = new Stage();
        juego = new Juego();
        juego.start(stage);
    }

    @Override
    public void start(Stage stage) throws Exception {

    }
}