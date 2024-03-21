package com.example.metodosnumeros.vistas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GaussSeidel extends Stage {


    public GaussSeidel() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.setTitle("Gauss Seidel Solver");
        this.setScene(new Scene(root, 600, 400));
        this.show();
    }


}
