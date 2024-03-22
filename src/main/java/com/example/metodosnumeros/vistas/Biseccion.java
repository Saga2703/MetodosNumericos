package com.example.metodosnumeros.vistas;

import com.example.metodosnumeros.components.BiseccionData;

import com.example.metodosnumeros.components.FunctionGraph;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Biseccion extends Stage {
    private TextField functionField;
    private TextField intervalAField;
    private TextField intervalBField;
    private TextField errorField;

    public Biseccion() {
        // Crear las etiquetas y campos de entrada
        Label functionLabel = new Label("Función (En términos de 'x'): ");
        functionField = new TextField();
        functionField.setPromptText("Ejemplo: x^2 + 2");

        Label intervalALabel = new Label("Intervalo A: ");
        intervalAField = new TextField();
        intervalAField.setPromptText("Número");

        Label intervalBLabel = new Label("Intervalo B: ");
        intervalBField = new TextField();
        intervalBField.setPromptText("Número");

        Label errorLabel = new Label("Error: ");
        errorField = new TextField();
        errorField.setPromptText("Número");
        Label instrucciones = new Label("Insertar la ecuacion de la manera 2x^2+1,\n" +
                "ademas para graficar se debe de insertar" +
                "los datos ecepto el de error \n" +
                "y si al resolver no se ve nada es por que dentro del intervalo no pasa por cero");

        // Configurar los campos de intervalos y error para aceptar números con punto decimal y negativos
        intervalAField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("-?\\d*\\.?\\d*")) {
                intervalAField.setText(newValue.replaceAll("[^\\d.-]", ""));
            }
        });
        intervalBField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("-?\\d*\\.?\\d*")) {
                intervalBField.setText(newValue.replaceAll("[^\\d.-]", ""));
            }
        });
        errorField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*\\.?\\d*")) {
                errorField.setText(newValue.replaceAll("[^\\d.]", ""));
            }
        });

        // Botón para resolver
        Button solveButton = new Button("Resolver");
        solveButton.setOnAction(event -> {
            // Obtener los datos ingresados
            String functionString = functionField.getText();
            double intervalA = Double.parseDouble(intervalAField.getText());
            double intervalB = Double.parseDouble(intervalBField.getText());
            double error = Double.parseDouble(errorField.getText());

            // Crear objeto BisectionData y mostrar ventana
            BiseccionData data = new BiseccionData(functionString, intervalA, intervalB, error);
            data.show();
        });

        // Botón para graficar
        Button graphButton = new Button("Graficar");
        graphButton.setOnAction(event -> {
            String functionString = functionField.getText();
            double intervalA = Double.parseDouble(intervalAField.getText());
            double intervalB = Double.parseDouble(intervalBField.getText());
            FunctionGraph graph = new FunctionGraph(functionString, intervalA, intervalB);
            graph.show(); // Mostrar la ventana de la gráfica
        });

        // Diseño de la interfaz
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.getChildren().addAll(functionLabel, functionField, intervalALabel, intervalAField, intervalBLabel, intervalBField, errorLabel, errorField,instrucciones, solveButton, graphButton);

        // Configurar la escena
        Scene scene = new Scene(root, 600, 500);
        // Configurar el escenario
        setTitle("Método de Bisección");
        setScene(scene);
        show();
    }
}