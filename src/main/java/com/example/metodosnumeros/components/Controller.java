package com.example.metodosnumeros.components;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private TextArea iterationsArea;

    @FXML
    private TextArea equationsInput;

    @FXML
    private TextField errorInput;

    @FXML
    private void solveEquations() {
        String equations = equationsInput.getText();
        String[] lines = equations.split("\n");
        int n = lines.length;
        double[][] coefficients = new double[n][n];
        double[] constants = new double[n];

        // Parse equations
        for (int i = 0; i < n; i++) {
            String line = lines[i].trim();
            if (!line.isEmpty()) {
                String[] parts = line.split(" ");
                for (int j = 0; j < n; j++) {
                    String coefficient = parts[j].replaceAll("[^\\d.-]", "");
                    if (!coefficient.isEmpty()) {
                        coefficients[i][j] = Double.parseDouble(coefficient);
                    }
                }
                String constant = parts[n].replaceAll("[^\\d.-]", "");
                if (!constant.isEmpty()) {
                    constants[i] = Double.parseDouble(constant);
                }
            }
        }

        double[] initialGuess = new double[n];

        int maxIterations = 1000;
        double errorPercentage = Double.parseDouble(errorInput.getText()) / 100.0;

        // Llamar al método solveEquations con el errorPercentage
        double[] solution = solveEquations(coefficients, constants, initialGuess, maxIterations, errorPercentage);

        // Display solution
        StringBuilder sb = new StringBuilder();
        sb.append("Solution:\n");
        for (int i = 0; i < n; i++) {
            sb.append("x").append(i).append(": ").append(solution[i]).append("\n");
        }
        iterationsArea.setText(sb.toString());
    }

    // Método para resolver el sistema de ecuaciones lineales mediante el método de Gauss-Seidel
    private double[] solveEquations(double[][] coefficients, double[] constants, double[] initialGuess, int maxIterations, double errorPercentage) {
        int n = constants.length;
        double[] solution = new double[n];
        double[] previousSolution = new double[n];
        int iteration = 0;
        boolean converged = false;

        while (!converged && iteration < maxIterations) {
            // Hacer una copia de la solución anterior
            System.arraycopy(solution, 0, previousSolution, 0, n);

            // Actualizar cada variable de la solución
            for (int i = 0; i < n; i++) {
                double sum = 0.0;
                for (int j = 0; j < n; j++) {
                    if (j != i) {
                        sum += coefficients[i][j] * solution[j];
                    }
                }
                solution[i] = (constants[i] - sum) / coefficients[i][i];
            }
            // Mostrar la iteración actual
            StringBuilder iterationInfo = new StringBuilder();
            iterationInfo.append("Iteration ").append(iteration + 1).append(":\n");
            for (int i = 0; i < n; i++) {
                iterationInfo.append("x").append(i).append(": ").append(solution[i]).append("\n");
            }
            iterationInfo.append("\n");

            iterationsArea.appendText(iterationInfo.toString());

            // Verificar si la solución ha convergido
            double maxError = 0.0;
            for (int i = 0; i < n; i++) {
                double error = Math.abs((solution[i] - previousSolution[i]) / solution[i]);
                if (error > maxError) {
                    maxError = error;
                }
            }
            if (maxError < errorPercentage) {
                converged = true;
            }

            iteration++;
        }

        if (!converged) {
            System.err.println("El método de Gauss-Seidel no converge dentro del límite de iteraciones.");
        }

        return solution;
    }
}
