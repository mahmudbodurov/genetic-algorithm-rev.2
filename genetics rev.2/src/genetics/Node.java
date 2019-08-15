/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetics;

import java.util.Random;

/**
 *
 * @author Mahmud
 */
public class Node {

    private int N;
    double[] elements;
    double[] results;

    public Node(int length) {
        this.N = length;
        elements = new double[length];
        results = new double[length];
        generateElements();
    }

    public void setResults(double[] results) {
        this.results = results;
    }

    public void setResult(double value, int position) {
        if (position < N) {
            this.results[position] = value;
        }
    }

    public double[] getResults() {
        return results;
    }

    public double getOverAll() {
        double sum = 0;
        for (int i = 0; i < results.length; i++) {
            double result = results[i];
            sum += result;
        }
        return sum / N;
    }

    private void generateElements() {
        Random rand = new Random();
        for (int i = 0; i < N; i++) {
            elements[i] = ((double) Math.round(rand.nextDouble() * 10d * 100d)) / 100d - 5d;
        }
    }

    public double[] getElements(int start, int end) {
        if (end > start) {
            double[] d = new double[end - start];
            for (int i = start; i < end; i++) {
                d[i - start] = elements[i];
            }
            return d;
        }
        return null;
    }

    public void mutation() {
        Random rand = new Random();
        elements[rand.nextInt(N)] = ((double) Math.round(rand.nextDouble() * 10d * 10000d)) / 10000d - 5d;
    }

    public double[] getElements() {
        return elements;
    }

    public void setElements(double[] arr) {
        if (arr.length == N) {
            elements = arr;
        }
    }

    public void printElements() {
        for (double element : elements) {
            System.out.printf("%.4f\t", element);
        }
        System.out.println("");
    }

    public void printResults() {
        for (double result : results) {
            System.out.printf("%.4f\t", result);
        }
        System.out.println("");
    }

}

