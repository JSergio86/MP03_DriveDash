package com.example.mp03_drivedash;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Juego extends Application {
     Coche jugador;
     List<Obstaculo> obstacles;
     AnimationTimer timer;

    @Override
    public void start(Stage stage) throws InterruptedException {
        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);

        Canvas canvas = new Canvas(550, 900);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(new Image("fondo2.png"),0,0);

        Button button = new Button();
        button.setText("Hola");


        jugador = new Coche(150, 600, 7, new Image("car2.png"));
        root.getChildren().add(jugador.getImageView());

        obstacles = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            Obstaculo obstaculo = new Obstaculo(new Image("car2.png"),1);
                            root.getChildren().add(obstaculo.getImageView());
                            obstacles.add(obstaculo);
                        }
                    });
                }
            }, i * 1000);
        }


        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                for (Obstaculo obstaculo : obstacles) {
                    obstaculo.move();
                    if (jugador.getImageView().getBoundsInParent().intersects(obstaculo.getImageView().getBoundsInParent())) {
                        timer.stop();
                        System.out.println("Se ha chocado");
                    }
                }
            }
        };
        timer.start();




        scene.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case UP:
                    jugador.moverArriba();
                    break;
                case DOWN:
                    jugador.moverAbajo();
                    break;
                case LEFT:
                    jugador.moverIzquierda();
                    break;
                case RIGHT:
                    jugador.moverDerecha();
                    break;
            }
        });

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


