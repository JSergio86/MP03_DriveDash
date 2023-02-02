package com.example.mp03_drivedash;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Coche extends Application{

    @Override
    public void start(Stage theStage) {
        theStage.setTitle("Coche en la Carretera");

        Group root = new Group();
        Scene theScene = new Scene(root);
        theStage.setScene(theScene);
        theStage.setResizable(false);

        Canvas canvas = new Canvas(300, 500);
        root.getChildren().add(canvas);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        Image car = new Image("car2.png");
        ImageView carView = new ImageView(car);
        System.out.println(car.getHeight()+" "+ car.getWidth());
        Image carretera = new Image("carretera2.png");


        final int[] x = {(int) (canvas.getWidth() / 2)};
        final int[] y = {(int) (canvas.getHeight() - carView.getFitHeight())};

        theScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.LEFT) {
                    if (x[0] > 7) x[0]-=10;
                } else if (event.getCode() == KeyCode.RIGHT) {
                    if (x[0] > -(canvas.getWidth())) x[0]+=10;
                } else if (event.getCode() == KeyCode.UP) {
                    if (y[0] > 0) y[0]-=10;
                } else if (event.getCode() == KeyCode.DOWN) {
                    if (y[0] < canvas.getHeight() - carView.getFitHeight()) y[0]+=10;
                }
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                gc.drawImage(carretera, 0, 0);
                gc.drawImage(carView.getImage(), x[0], y[0]);
            }
        });

        gc.drawImage(carretera, 0, 0);
        gc.drawImage(carView.getImage(), x[0], y[0]);
        System.out.println(carView.getFitHeight()+" "+ carView.getFitWidth());

        theStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
