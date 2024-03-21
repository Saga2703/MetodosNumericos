package com.example.metodosnumeros.components;
import java.util.Arrays;

public class GaussSeidelSolver {

    public static double[] solve(double[][] coefficients, double[] constants, double[] initialGuess, int maxIterations, double errorPercentage) {
        int n = constants.length;
        double[] x = Arrays.copyOf(initialGuess, n);
        double[] xNew = new double[n];
        int iterations = 0;

        while (iterations < maxIterations) {
            for (int i = 0; i < n; i++) {
                double sum = 0.0;
                for (int j = 0; j < n; j++) {
                    if (j != i) {
                        sum += coefficients[i][j] * x[j];
                    }
                }
                xNew[i] = (constants[i] - sum) / coefficients[i][i];
            }

            if (converged(x, xNew, errorPercentage)) { // Comprueba si la solución ha convergido utilizando el error porcentual
                break;
            }

            System.arraycopy(xNew, 0, x, 0, n);
            iterations++;
        }

        return x;
    }

    private static boolean converged(double[] x, double[] xNew, double errorPercentage) {
        for (int i = 0; i < x.length; i++) {
            double error = Math.abs((xNew[i] - x[i]) / xNew[i]); // Calcula el error porcentual para cada componente
            if (error > errorPercentage) {
                return false;
            }
        }
        return true;
    }
}