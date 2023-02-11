package com.example.mp03_drivedash;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Controller extends Application {
    Stage stage;
    Juego juego;
    Obstaculo obstaculo = new Obstaculo();
    int puntuacion;

    @FXML
    Text puntuacionText;


    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void clickComenzar() throws InterruptedException {
        stage = new Stage();
        juego = new Juego();
        juego.start(stage);
        puntuacion = obstaculo.puntuacion;

    }

    public void ponerPuntuacion() {
        puntuacionText.setText(String.valueOf(puntuacion));
    }

    @Override
    public void start(Stage stage) throws Exception {

    }
}