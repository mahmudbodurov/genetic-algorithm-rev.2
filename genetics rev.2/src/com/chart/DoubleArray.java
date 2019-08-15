/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chart;

/**
 *
 * @author Mahmud
 */
public class DoubleArray {

    private double array[];
    private double max_value = Double.NaN;
    private double min_value = Double.NaN;
    private final int max_size = 350000;

    public DoubleArray(int size) {
        this.array = new double[size];
    }

    public DoubleArray() {
        array = new double[0];
    }

    public DoubleArray(double[] array) {
        this.array = array;
    }

    public void append(double value) {
        if (Double.isNaN(max_value) || max_value < value) {
            max_value = value;
        }
        if (Double.isNaN(min_value) || min_value > value) {
            min_value = value;
        }
        double array2[] = new double[array.length + 1];
        System.arraycopy(array, 0, array2, 0, array.length);
        array2[array2.length - 1] = value;
        array = array2;
    }

    public double[] getArray() {
        return array;
    }

    public double getValue(int position) {
        if (position >= array.length) {
            throw new ArrayIndexOutOfBoundsException("ArrayIndexOutOfBoundsException-Massivin olcusunden uzun verildi");
        }
        return array[position];
    }

    public double getMax() {
        return max_value;
    }

    public double getMin() {
        return min_value;
    }
    public int getSize(){
        return array.length;
    }
}



