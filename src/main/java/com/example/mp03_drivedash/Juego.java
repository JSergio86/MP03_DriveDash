package com.example.mp03_drivedash;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javafx.animation.AnimationTimer;

import java.net.URL;
import java.util.*;

public class Juego extends Application implements Initializable {
     Coche jugador;
     List<Obstaculo> obstacles;
     AnimationTimer timer;
     int dificultad = 1500;
     int numCoches=15;
     int obstaculosEsquivados = 0;
     boolean comenzarJuego = false;



    @Override
    public void start(Stage stage) throws InterruptedException {
        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);

        Canvas canvas = new Canvas(550, 900);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        //gc.drawImage(new Image("background1.png"),0,0);

        final double[] y = {0};
        double velocity = -2;
        Image background1 = new Image("background1.png");
        Image background2 = new Image("background2.png");

        AnimationTimer timer3 = new AnimationTimer() {
            @Override
            public void handle(long now) {
                gc.clearRect(0, 0, 550, 900);
                gc.drawImage(background1,0 , y[0]);
                gc.drawImage(background2,0, y[0] + 900);
                y[0] += velocity;
                if (y[0] <= -900) {
                    y[0] = 0;
                }
            }
        };
        timer3.start();
        stage.show();

        jugador = new Coche(150, 600, 20, new Image("coche_amarillo.png"));
        root.getChildren().add(jugador.getImageView());

        obstacles = new ArrayList<>();

        String[] imagenes = {"coche_azul.png", "coche_naranja.png", "coche_poli.png","coche_rosa.png","coche_turquesa.png","coche_gris.png","coche_verde.png","camion_morado.png","camion_rojo.png", "camioneta_blanca.png"};
        Random random = new Random();
        for (int i = 0; i < numCoches; i++) {

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            int randomIndex = random.nextInt(imagenes.length);
                            String randomImageName = imagenes[randomIndex];
                            Image image = new Image(randomImageName);
                            Obstaculo obstaculo = new Obstaculo(image, 1);
                            if (obstaculo.getImageView().getX() < 275) {
                                obstaculo.getImageView().setRotate(180);
                            }
                            root.getChildren().add(obstaculo.getImageView());
                            obstacles.add(obstaculo);
                          /*  if(obstacles.size()==numCoches){
                                comenzarJuego=true;
                            }

                           */
                        }
                    });
                }
            }, i * dificultad);
        }

        //if(comenzarJuego) {
            timer = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    for (int i = 0; i < obstacles.size(); i++) {
                        Obstaculo obstaculo = obstacles.get(i);
                        obstaculo.move();
                        if (jugador.getImageView().getBoundsInParent().intersects(obstaculo.getImageView().getBoundsInParent())) {
                            timer.stop();
                            System.out.println(obstaculo.puntuacion);
                            gameOver(stage);
                            obstaculo.puntuacion = 1500;
                            break;
                        } else {
                            obstaculosEsquivados++;
                        }
                        if (obstaculosEsquivados == numCoches) {
                            System.out.println("Has subido de nivel");
                        }
                    }
                }

            };
            timer.start();
        //}


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
    public void gameOver(Stage stage) {
        try {
            Pane root = FXMLLoader.load(getClass().getResource("menu.fxml"));
            Scene gameOverScene = new Scene(root);
            stage.setScene(gameOverScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}


