package com.example.mp03_drivedash;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Obstaculo {
    int x;
    int y;
    double velocidad;
    ImageView imageView;

    public Obstaculo(Image image,double velocidad) {
        this.x = (int) (Math.random() * 300 + 25);
        this.y = -400;
        this.velocidad = velocidad;
        imageView = new ImageView(image);
        imageView.setX(x);
        imageView.setY(y);

    }

    public ImageView getImageView() {
        return imageView;
    }

    public void move() {
        y += velocidad;
        imageView.setY(y);

    }
}
