package com.example.metodosnumeros;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class GaussSeidel extends Stage {

    public GaussSeidel(){
        Parent root = null;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ventana.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.setTitle("Gauss Seidel Solver");
        this.setScene(new Scene(root, 600, 400));
        this.show();
    }


}
