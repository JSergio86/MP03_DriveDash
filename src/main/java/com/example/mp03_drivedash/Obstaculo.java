package com.example.mp03_drivedash;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Obstaculo {
    int x;
    int y;
    double velocidad;
    ImageView imageView;
    int puntuacion=0;

    public Obstaculo(Image image,double velocidad) {
        this.x = (int) (Math.random() * 315 + 80);
        this.y = -400;
        this.velocidad = velocidad;
        imageView = new ImageView(image);
        imageView.setX(x);
        imageView.setY(y);

    }

    public ImageView getImageView() {
        return imageView;
    }

    public int getX() {
        return x;
    }

    public void move() {
        y += velocidad;
        imageView.setY(y);
        puntuacion+=20;

    }
}
