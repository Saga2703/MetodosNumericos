package com.example.metodosnumeros.vistas;

import java.util.Scanner;
import java.util.function.Function;

public class Biseccion {
    public static void biseccion(Function<Double, Double> f, double a, double b, double errorMaximo) {
        System.out.println("Iteración\tXi-1\tXi\tF(Xi-1)\t\tF(Xi)\t\tXi+1\t\tError");
        int iteraciones = 0;
        double error = Math.abs(b - a);
        double x0 = a + (b - a) / 2;
        while (error > errorMaximo) {
            iteraciones++;

            double fx0 = f.apply(x0);
            if (f.apply(a) * fx0 <= 0) {
                b = x0;
            } else {
                a = x0;
            }
            error = Math.abs(b - a);
            System.out.printf("%d\t\t%.6f\t%.6f\t%.6f\t%.6f\t%.6f\t%.6f\n", iteraciones, a, x0, f.apply(a), fx0, b, error);
        }
        System.out.println("\nResultado encontrado:");
        System.out.printf("x = %.8f, error = %.8f\n", x0, error);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Introducir el valor máximo del error
        System.out.println("Introduzca el valor máximo del error:");
        double errorMaximo = scanner.nextDouble();

        // Definir la función a resolver
        Function<Double, Double> f = x -> Math.pow(x, 3) - 2 * x - 3;

        // Seleccionar el intervalo [a, b]System.out.println("Introduzca el intervalo [a, b]:");
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();

        // Ejecutar el método de la bisección
        biseccion(f, a, b, errorMaximo);
    }
}