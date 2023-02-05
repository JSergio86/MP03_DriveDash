package com.example.mp03_drivedash;

import javafx.application.Application;
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

public class Game extends Application {
    private Vehicle jugador;
    private List<Obstacle> obstacles;
    private Pane root;
    private AnimationTimer timer;

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        Canvas canvas = new Canvas(300, 600);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(new Image("carretera2.png"),0,0);

        jugador = new Vehicle(150, 150, 5, new Image("car2.png"));
        root.getChildren().add(jugador.getImageView());

        obstacles = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
           Obstacle obstacle = new Obstacle(new Image("car2.png"),1);
           root.getChildren().add(obstacle.getImageView());
           obstacles.add(obstacle);
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
                    jugador.moveUp();
                    break;
                case DOWN:
                    jugador.moveDown();
                    break;
                case LEFT:
                    jugador.moveLeft();
                    break;
                case RIGHT:
                    jugador.moveRight();
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


