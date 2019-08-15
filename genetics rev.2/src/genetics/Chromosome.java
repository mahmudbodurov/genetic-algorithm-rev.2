/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetics;

import Constant.Const;
import Constant.Range;
import java.util.Random;

/**
 *
 * @author Mahmud
 */
public class Chromosome {

    private double[] elements = new double[N * Const.var_count];
    private final double[] results = new double[N];

    private double OverAll;
    static final int N = 1;
    private final Random rand = new Random();

    public void mutation() {
        int m = rand.nextInt(Const.var_count);
        Range range = Const.ranges[m];
        int n = rand.nextInt(N) + m * N;                                       //100d                                                      //50d      
        elements[n] = ((double) Math.round(rand.nextDouble() * (range.getMaximum() - range.getMinimum()) * 100000000d) / 100000000d) + range.getMinimum();//rand.nextDouble() * (Constants.Max - Constants.Min) - Constants.Min;
        //   results[n] = function(elements[n]);
        computeResults();
    }

    public double[] getElements() {
        return elements;
    }

    public double[] getElements(int start, int end) {
        if (end > start && start >= 0 && end <= N * Const.var_count) {
            double[] arr = new double[end - start];
            /*
             * @param      src      the source array.
             * @param      srcPos   starting position in the source array.
             * @param      dest     the destination array.
             * @param      destPos  starting position in the destination data.
             * @param      length   the number of array elements to be copied.
             * @exception  IndexOutOfBoundsException  if copying would cause
             */
            System.arraycopy(elements, start, arr, 0, end - start);
            //  System.arraycopy(elements, start, arr, 0, end);
            return arr;
        }
        return null;
    }

    public void setElements(double[] elements) {
        this.elements = elements;
        computeResults();
    }

    private void generateElements() {
        for (int i = 0; i < Const.var_count; i++) {
            Range range = Const.ranges[i];
            for (int j = 0; j < N; j++) {
                elements[j + i * N] = ((double) Math.round(rand.nextDouble() * (range.getMaximum() - range.getMinimum()) * 1000000000000000000000d) / 1000000000000000000000d) + range.getMinimum();
            }
        }
//        for (int i = 0; i < N * Const.var_count; i++) {
//            
//            elements[i] = ((double) Math.round(rand.nextDouble() * (Const.MAXIMUM - Const.MINIMUM) * 100000000d) / 100000000d) + Const.MINIMUM;//rand.nextDouble() * (Constants.Max - Constants.Min) - Constants.Min;
//        }
    }

    public Chromosome() {
        generateElements();
    }
    //declare const functions where set Const.setfunction(Function<Double,Double>)

    private double function(Double... x) {
        return Const.compute(x);//Math.pow(1 - x, 4);// - 1 + Math.sqrt(Math.abs(x) );
    }
/////////////

    public void computeResults() {
        for (int i = 0; i < N; i++) {
            Double[] element = new Double[Const.var_count];
            for (int j = 0; j < Const.var_count; j++) {
                element[j] = elements[j * N + i];
            }
            results[i] = function(element);
        }
        calculateOverAll();
    }

    private void calculateOverAll() {
        double sum = 0;
        for (int i = 0; i < results.length; i++) {
            sum += results[i];
        }
        OverAll = sum / N;
    }

    public double getOverAll() {
        return (OverAll);
    }

    public void printElements() {
        for (int i = 0; i < elements.length; i++) {
            System.out.printf("%.4f \t", elements[i]);
        }
        System.out.println("");
    }

    void printAverageElements() {
        double[] element = new double[Const.var_count];
        for (int j = 0; j < Const.var_count; j++) {
            for (int i = 0; i < N; i++) {
                element[j] += elements[j * N + i];
            }
            element[j] /= N;
            System.out.printf("%.8f\t", element[j]);
        }
        System.out.println("");
    }

    public void printMinElements() {
        for (int i = 0; i < Const.var_count; i++) {
            System.out.printf("%.4f \t", elements[i * N + getMinElement()]);
        }
        System.out.printf("\t v = %.16f \n", results[getMinElement()]);
    }

    public void printMaxElements() {
        for (int i = 0; i < Const.var_count; i++) {
            System.out.printf("%.4f \t", elements[i * N + getMaxElement()]);
        }
        System.out.printf("\t v = %.16f \n", results[getMaxElement()]);
    }

    public int getMinElement() {
        int min = 0;
        for (int i = 1; i < N; i++) {
            if (results[min] > results[i]) {
                min = i;
            }
        }
        return min;
    }

    public int getMaxElement() {
        int min = 0;
        for (int i = 1; i < N; i++) {
            if (results[min] < results[i]) {
                min = i;
            }
        }
        return min;
    }

    public Double[] getMinElements() {
        Double[] d = new Double[Const.var_count];
        for (int i = 0; i < Const.var_count; i++) {
            d[i] = elements[i * N + getMinElement()];
        }
        return d;
    }

    public Double[] getMaxElements() {
        Double[] d = new Double[Const.var_count];
        for (int i = 0; i < Const.var_count; i++) {
            d[i] = elements[i * N + getMaxElement()];
        }
        return d;
    }

}














