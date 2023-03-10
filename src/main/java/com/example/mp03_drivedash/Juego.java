package com.example.mp03_drivedash;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.animation.AnimationTimer;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.function.Predicate;

public class Juego extends Application{
     Coche jugador;
     List<Obstaculo> obstacles;
     AnimationTimer timer;
     int dificultad = 1500;
     int numCoches=15;
     int cochesPasando;
     int puntuacion=0;
     int nivelActual=1;
     int velocidadObstaculos= 1;


    @Override
    public void start(Stage stage) {
        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);

        Canvas canvas = new Canvas(550, 900);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();


        Image carretera1 = new Image("background1.png");
        Image carretera2 = new Image("background2.png");
        final double[] posY1 = {0};
        final double[] posY2 = {-900};

        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                gc.clearRect(0, 0, 550, 900);
                posY1[0] += 2.5;
                posY2[0] += 2.5;
                gc.drawImage(carretera1, 0, posY1[0]);
                gc.drawImage(carretera2, 0, posY2[0]);
                if (posY1[0] >= 900) {
                    posY1[0] = -900;
                }
                if (posY2[0] >= 900) {
                    posY2[0] = -900;
                }
            }
        };
        animationTimer.start();
        stage.show();


        jugador = new Coche(250, 700, 30, new Image("coche_amarillo.png"));
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
                                Obstaculo obstaculo = new Obstaculo(image, velocidadObstaculos);
                                if (obstaculo.getImageView().getX() < 275) {
                                    obstaculo.getImageView().setRotate(180);
                                }
                                root.getChildren().add(obstaculo.getImageView());
                                obstacles.add(obstaculo);
                                cochesPasando++;
                            }
                        });
                    }
                }, i * dificultad);
            }


        Label levelLabel = new Label();
        levelLabel.setAlignment(Pos.CENTER);
        levelLabel.setStyle("-fx-background-color: transparent; -fx-text-fill: #ffff00; -fx-padding: 10px; -fx-font-size: 32px; -fx-font-weight: bold;");
        levelLabel.setOpacity(0);
        root.getChildren().add(levelLabel);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(levelLabel.opacityProperty(), 0)),
                new KeyFrame(Duration.seconds(0.5), new KeyValue(levelLabel.opacityProperty(), 1)),
                new KeyFrame(Duration.seconds(2.5), new KeyValue(levelLabel.opacityProperty(), 1)),
                new KeyFrame(Duration.seconds(3), new KeyValue(levelLabel.opacityProperty(), 0))
        );

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                puntuacion+=1;
                for (int i = 0; i < obstacles.size(); i++) {
                    Obstaculo obstaculo = obstacles.get(i);
                    obstaculo.mover();
                    if (jugador.getImageView().getBoundsInParent().intersects(obstaculo.getImageView().getBoundsInParent())) {
                        timer.stop();
                        //System.out.println(puntuacion);
                        stage.close();
                        try {
                            gameOver(puntuacion);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        break;
                    }
                    if(obstaculo.y>1000){
                        root.getChildren().remove(obstaculo.getImageView());
                        obstacles.remove(i);
                    }

                    if(obstacles.size()==0){
                        levelLabel.setText("\n\n\n\n                   Nivel " + (nivelActual+1));
                        timeline.playFromStart();
                        //System.out.println("Has subido de nivel");
                        nivelActual++;
                        numCoches+=5;
                        dificultad-=200;
                        velocidadObstaculos++;
                            for (int j = 0; j < numCoches; j++) {
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
                                                Obstaculo obstaculo = new Obstaculo(image, velocidadObstaculos);
                                                if (obstaculo.getImageView().getX() < 275) {
                                                    obstaculo.getImageView().setRotate(180);
                                                }
                                                root.getChildren().add(obstaculo.getImageView());
                                                obstacles.add(obstaculo);
                                                cochesPasando++;
                                            }
                                        });
                                    }
                                }, j * dificultad);
                            }



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


    public void gameOver(int puntuacion) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gameover.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);

        // Obtener la referencia al Text nPuntos
        Text puntuacionText = (Text) loader.getNamespace().get("puntuacionText");
        // Asignar el valor de nPuntos al Text nPuntos
        puntuacionText.setText("Puntuaci??n: "+puntuacion);

        stage.show();
        stage.toFront();
    }



    public static void main(String[] args) {
        launch(args);
    }

}


