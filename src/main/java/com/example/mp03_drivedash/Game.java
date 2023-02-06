package com.example.mp03_drivedash;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Game extends Application {
     Vehicle jugador;
     List<Obstacle> obstacles;
     AnimationTimer timer;

    @Override
    public void start(Stage primaryStage) throws InterruptedException {
        Group root = new Group();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        Canvas canvas = new Canvas(580, 1000);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(new Image("fondo2.png"),0,0);

        jugador = new Vehicle(150, 150, 5, new Image("car2.png"));
        root.getChildren().add(jugador.getImageView());

        obstacles = new ArrayList<>();

        for (int i = 0; i < 999; i++) {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            Obstacle obstacle = new Obstacle(new Image("car2.png"),1);
                            root.getChildren().add(obstacle.getImageView());
                            obstacles.add(obstacle);
                        }
                    });
                }
            }, i * 1000);
        }


        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                for (Obstacle obstacle : obstacles) {
                    obstacle.move();
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

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


