package com.example.mp03_drivedash;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Controller extends Application {
    Stage stage;
    Juego juegoComenzar;


    public void clickComenzar(ActionEvent event) throws Exception {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.close();

        stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED); // Establece el estilo de la ventana
        stage.setAlwaysOnTop(true); // Asegura que la ventana esté por delante de todas las demás

        juegoComenzar = new Juego();
        juegoComenzar.start(stage);

        stage.show();
        stage.requestFocus(); // Establece el foco en la ventana
        // Si hay un control que deseas enfocar primero, se puede hacer después de cargar la ventana:
        // juegoComenzar.getPrimerControl().requestFocus();
    }

    @Override
    public void start(Stage stage) throws Exception {

    }
}