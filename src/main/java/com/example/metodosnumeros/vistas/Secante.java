package com.example.metodosnumeros.vistas;

import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.function.Function;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.function.Function;


public class Secante extends Stage {
private Button btnSalir;
    public Secante(){
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        Label functionLabel = new Label("Función (e.g., x^4 - 2*x^3 - 12*x^2 + 16*x - 40):");
        TextField functionField = new TextField();
        grid.addRow(3, functionLabel, functionField);

        Label errorLabel = new Label("Valor máximo del error porcentual:");
        TextField errorField = new TextField();
        grid.addRow(0, errorLabel, errorField);

        Label x0Label = new Label("Primera aproximación inicial (xi-1):");
        TextField x0Field = new TextField();
        grid.addRow(1, x0Label, x0Field);

        Label x1Label = new Label("Segunda aproximación inicial (xi):");
        TextField x1Field = new TextField();
        grid.addRow(2, x1Label, x1Field);

        Label resultLabel = new Label();

        grid.add(resultLabel, 0, 5, 2, 1);

        this.setScene(new Scene(grid, 600, 600));
        this.setTitle("Método de la Secante");
        this.show();

        Function<Double, Double> f = x -> Math.pow(x, 4) - 2 * Math.pow(x, 3) - 12 * Math.pow(x, 2) + 16 * x - 40;

        grid.setOnKeyReleased(event -> {
            try {
                double errorMaximoPorcentual = Double.parseDouble(errorField.getText());
                double x0 = Double.parseDouble(x0Field.getText());
                double x1 = Double.parseDouble(x1Field.getText());

                secante(f, x0, x1, errorMaximoPorcentual, resultLabel);
            } catch (NumberFormatException e) {
                resultLabel.setText("Por favor, introduzca valores numéricos válidos.");
            }
        });
    }

    private Function<Double, Double> evalFunction(String functionString) throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        String script = "function f(x) { return " + functionString + "; }";
        engine.eval(script);
        return (Function<Double, Double>) engine.eval("f");
    }


    public static void secante(Function<Double, Double> f, double x0, double x1, double errorMaximoPorcentual, Label resultLabel) {
        resultLabel.setText("Iteración\tXi-1\tXi\tF(Xi-1)\t\tF(Xi)\t\tXi+1\t\tError\n");

        int iteraciones = 0;
        double errorPorcentual = 100;
        while (errorPorcentual > errorMaximoPorcentual) {
            iteraciones++;
            double fx0 = f.apply(x0);
            double fx1 = f.apply(x1);
            double xNext = x1 - (fx1 * (x0 - x1)) / (fx0 - fx1);
            errorPorcentual = Math.abs((xNext - x1) / xNext) * 100;
            resultLabel.setText(resultLabel.getText() +
                    String.format("%d\t\t%.6f\t%.6f\t%.6f\t%.6f\t%.6f\t%.6f%%\n", iteraciones, x0, x1, fx0, fx1, xNext, errorPorcentual));
            x0 = x1;
            x1 = xNext;
        }

        resultLabel.setText(resultLabel.getText() + "\nResultado encontrado:\n" +
                String.format("x = %.8f, error porcentual = %.8f%%\n", x1, errorPorcentual));
    }


}
