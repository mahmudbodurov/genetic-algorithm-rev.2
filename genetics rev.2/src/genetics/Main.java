/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetics;

import Constant.Const;
import Constant.Range;

/**
 *
 * @author Mahmud
 */
public class Main {

    public static void main(String[] args) {
        //single parameter function

        Const.setFunction((Double... x) -> x[0] * x[0] + x[1] * x[1] + x[2] * x[2] + x[3] * x[3] - 2 * x[3] * x[3]
        //21.5+x[0]*Math.sin(4*Math.PI*x[0])+x[1]*Math.sin(20*Math.PI*x[1]) 
        //15*x[0]-x[0]*x[0]
        //Math.pow(x[0] - 2, 2) + 100 * Math.pow(x[1] - 3, 2);
        );
//        //Double Parameter function
//        Const.setFunction((Double x, Double y) -> {
//            return Math.pow(1 - x, 2) + 100 * Math.pow(y - Math.pow(x, 2), 2);
//        });
        Const.setSearch(Const.MIN);
        Const.setRanges(new Range[]{
            new Range(-10, 10),
            new Range(-10, 10),
            new Range(-10, 10),
            new Range(-10000000, 10000)
        });
//        Const.setMutationRange(50, -50);
        Const.setVar_count(4);
        Population p = new Population();
        int generation = 0;
        while (generation < 640) {

            p.computeAllChromosomeValue();
            p.crossOver();
            if (generation % 5 == 0) {
                //     System.out.println("-------------mutation----------------------mutation-------------");
                p.mutate();
            }

            p.createNewGeneration();
            p.computeAllChromosomeValue();

            p.crossOver();
            p.createNewGeneration();

            // p.printValues();
            generation++;
        }
        Double[] d = p.getOptimalChromosome();
        for (Double e : d) {
            System.out.print(e.doubleValue() + "\t");
        }
        System.out.println("\n-------------");
        p.printOptimalChromosome();
    }

}

