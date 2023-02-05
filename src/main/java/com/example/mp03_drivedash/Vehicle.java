package com.example.mp03_drivedash;


import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Vehicle {
    private double xPos;
    private double yPos;
    private double velocidad;
    private ImageView imageView;

    public Vehicle(double x, double y, double velocidad, Image image) {
        xPos = x;
        yPos = y;
        this.velocidad = velocidad;
        imageView = new ImageView(image);
        imageView.setX(xPos);
        imageView.setY(yPos);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public double getX() {
        return xPos;
    }

    public double getY() {
        return yPos;
    }

    public void setX(double x) {
        xPos = x;
        imageView.setX(xPos);
    }

    public void setY(double y) {
        yPos = y;
        imageView.setY(yPos);
    }

    public void moveUp() {
        yPos -= velocidad;
        imageView.setY(yPos);
    }

    public void moveDown() {
        yPos += velocidad;
        imageView.setY(yPos);
    }

    public void moveLeft() {
        xPos -= velocidad;
        imageView.setX(xPos);
    }

    public void moveRight() {
        xPos += velocidad;
        imageView.setX(xPos);
    }
}
