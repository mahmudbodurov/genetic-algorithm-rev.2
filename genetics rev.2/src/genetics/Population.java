/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetics;

import Constant.Const;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author Mahmud
 */
public class Population {

    static final int N = 64;
    Chromosome[] chromosomes = new Chromosome[N];
    Chromosome[] childs = new Chromosome[N];
    private final Random rand = new Random();

    public int getMinChromosome() {
        int min = 0;
        for (int i = 1; i < N; i++) {
            if (chromosomes[min].getOverAll() > chromosomes[i].getOverAll()) {
                min = i;
            }
        }
        return min;
    }

    public int getMinChromosome(Set set) {
        int min = 0;
        for (int i = 1; i < N; i++) {
            if (!set.contains(i) && chromosomes[min].getOverAll() > chromosomes[i].getOverAll()) {
                min = i;
            }
        }
        return min;
    }

    public int getMaxChromosome() {
        int max = N - 1;
        for (int i = 0; i < N; i++) {
            if (chromosomes[max].getOverAll() < chromosomes[i].getOverAll()) {
                max = i;
            }
        }
        return max;
    }

    public int getMaxChromosome(Set set) {
        int max = N - 1;
        for (int i = 0; i < N; i++) {
            if (!set.contains(i) && chromosomes[max].getOverAll() <= chromosomes[i].getOverAll()) {
                max = i;
            }
        }
        return max;
    }

    public void crossOver() {
        if (Const.getSearch() == Const.MAX) {
            crossOverMax();
        } else if (Const.getSearch() == Const.MIN) {
            crossOverMin();
        }
    }

    public void crossOverMin() {

        Set set = new HashSet();
        for (int i = 0; i < N / 2; i++) {
            int min1 = getMinChromosome(set);
            set.add(min1);
            int min2 = getMinChromosome(set);
            set.add(min2);
            double[] arr1 = new double[Chromosome.N * Const.var_count];
            double[] arr2 = new double[Chromosome.N * Const.var_count];
            int pos = rand.nextInt(Chromosome.N * Const.var_count);
            //child1

            double[] p1 = chromosomes[min1].getElements();
            double[] p2 = chromosomes[min2].getElements();

            System.arraycopy(p2, 0, arr2, 0, pos);
            System.arraycopy(p1, 0, arr1, 0, pos);

            System.arraycopy(p1, pos, arr2, pos, Chromosome.N * Const.var_count - pos);
            System.arraycopy(p2, pos, arr1, pos, Chromosome.N * Const.var_count - pos);
//        System.arraycopy(chromosomes[min1].getElements(0, pos), 0, arr1, 0, pos);
//        System.arraycopy(chromosomes[min2].getElements(pos, Chromosome.N), pos, arr1, 0, Chromosome.N - pos);
//        //child 2
//        System.arraycopy(chromosomes[min1].getElements(pos, Chromosome.N), pos, arr2, 0, pos);
//        System.arraycopy(chromosomes[min2].getElements(0, pos), 0, arr1, 0, pos);
            ///set childs
            childs[i * 2].setElements(arr1);//auto compute result
            childs[i * 2 + 1].setElements(arr2);
        }

    }

    public void crossOverMax() {

        Set set = new HashSet();
        for (int i = 0; i < N / 2; i++) {

            int max1 = getMaxChromosome(set);
            set.add(max1);
            int max2 = getMaxChromosome(set);
            set.add(max2);
            double[] arr1 = new double[Chromosome.N * Const.var_count];
            double[] arr2 = new double[Chromosome.N * Const.var_count];
            int pos = rand.nextInt(Chromosome.N * Const.var_count);
            //child1

            double[] p1 = chromosomes[max1].getElements();
            double[] p2 = chromosomes[max2].getElements();

            System.arraycopy(p2, 0, arr2, 0, pos);
            System.arraycopy(p1, 0, arr1, 0, pos);

            System.arraycopy(p1, pos, arr2, pos, Chromosome.N * Const.var_count - pos);
            System.arraycopy(p2, pos, arr1, pos, Chromosome.N * Const.var_count - pos);
//        System.arraycopy(chromosomes[min1].getElements(0, pos), 0, arr1, 0, pos);
//        System.arraycopy(chromosomes[min2].getElements(pos, Chromosome.N), pos, arr1, 0, Chromosome.N - pos);
//        //child 2
//        System.arraycopy(chromosomes[min1].getElements(pos, Chromosome.N), pos, arr2, 0, pos);
//        System.arraycopy(chromosomes[min2].getElements(0, pos), 0, arr1, 0, pos);
            ///set childs
            childs[i * 2].setElements(arr1);//auto compute result
            childs[i * 2 + 1].setElements(arr2);
        }

    }

    public void mutate() {
        chromosomes[rand.nextInt(N)].mutation();
    }

    public void computeAllChromosomeValue() {
        for (Chromosome chromosome : chromosomes) {
            chromosome.computeResults();
        }
    }

    public void createNewGeneration() {

        if (Const.getSearch() == Const.MAX) {
            createNewGenerationMax();
        } else if (Const.getSearch() == Const.MIN) {
            createNewGenerationMin();
        }
        //////////////////////////////////////////////////// operation 
    }
// compute for min chromosomes

    public void createNewGeneration_1() {
        ArrayList<Chromosome> list = new ArrayList<>();
        Set set = new HashSet();
        for (int i = 0; i < N; i++) {
            list.add(chromosomes[i]);
            list.add(childs[i]);
        }
        Chromosome[] Parents = new Chromosome[N];
        for (int i = 0; i < N; i++) {
            Parents[i] = new Chromosome();
            int min = 0;
            for (int j = 0; j < list.size(); j++) {
                if (list.get(min).getOverAll() > list.get(j).getOverAll()) {
                    min = j;
                }
            }
            Parents[i].setElements(list.get(min).getElements());
            list.remove(min);
        }
        // printValues();
//        list.sort(new Comparator<Chromosome>() {
//            
//            @Override
//            public int compare(Chromosome o1, Chromosome o2) {
//                return (int) (o1.getOverAll() < o2.getOverAll() ? o1.getOverAll() * 1000 : o2.getOverAll() * 1000);
//            }
//        });
//        for (int i = 0; i < N; i++) {
//            chromosomes[i].setElements(list.get(0).getElements());
//            list.remove(0);
//        }
        chromosomes = Parents;
        //printValues();
        //////////////////////////////////////////////////// operation 
    }

    public Population() {
        for (int i = 0; i < N; i++) {
            chromosomes[i] = new Chromosome();
        }
        for (int i = 0; i < N; i++) {
            childs[i] = new Chromosome();
        }
    }

    public void printValues() {
        for (Chromosome chromosome : chromosomes) {
            System.out.printf("Value : %.8f \t", chromosome.getOverAll());
        }
        // System.out.printf("Value : %.8f \t", chromosomes[getMinChromosome()].getOverAll());
        System.out.println("");
    }

    public void printOptimalChromosome() {
        switch (Const.getSearch()) {
            case Const.MAX:
                printMaxChromosome();
                break;
            case Const.MIN:
                printMinChromosome();
                break;
            default:
                throw new RuntimeException("not Set Function search Priority");
        }

    }

    public Double[] getOptimalChromosome() {
        switch (Const.getSearch()) {
            case Const.MAX:
                return chromosomes[getMaxChromosome()].getMaxElements();
            case Const.MIN:
                return chromosomes[getMaxChromosome()].getMinElements();
            default:
                throw new RuntimeException("not Set Function search Priority");
        }
    }

//    public Double[] getChromosomes(int i) {
//        switch (Const.getSearch()) {
//            case Const.MAX:
//                return chromosomes[i].getMaxElements();
//            case Const.MIN:
//                return chromosomes[i].getMinElements();
//            default:
//                throw new RuntimeException("not Set Function search Priority");
//        }
//    }

    //custom funct test
    public void createNewGenerationMin() {
        ArrayList<Chromosome> list = new ArrayList<>();
        Set set = new HashSet();
        for (int i = 0; i < N; i++) {
            list.add(chromosomes[i]);
            list.add(childs[i]);
        }
        Chromosome[] Parents = new Chromosome[N];
        for (int i = 0; i < N; i++) {
            Parents[i] = new Chromosome();
            int min = 0;
            for (int j = 0; j < list.size(); j++) {
                if (list.get(min).getOverAll() > list.get(j).getOverAll()) {
                    min = j;
                }
            }
            Parents[i].setElements(list.get(min).getElements());
            list.remove(min);
        }

        chromosomes = Parents;
        //////////////////////////////////////////////////// operation 
    }

    public void createNewGenerationMax() {
        ArrayList<Chromosome> list = new ArrayList<>();
        Set set = new HashSet();
        for (int i = 0; i < N; i++) {
            list.add(chromosomes[i]);
            list.add(childs[i]);
        }
        Chromosome[] Parents = new Chromosome[N];
        for (int i = 0; i < N; i++) {
            Parents[i] = new Chromosome();
            int max = 0;
            for (int j = 0; j < list.size(); j++) {
                if (list.get(max).getOverAll() < list.get(j).getOverAll()) {
                    max = j;
                }
            }
            Parents[i].setElements(list.get(max).getElements());
            list.remove(max);
        }

        chromosomes = Parents;
        //////////////////////////////////////////////////// operation 
    }

    public void printMaxChromosome() {
        chromosomes[getMaxChromosome()].printMaxElements();
        chromosomes[getMaxChromosome()].printElements();
        chromosomes[getMaxChromosome()].printAverageElements();
    }

    public void printMinChromosome() {
        chromosomes[getMinChromosome()].printMinElements();
        chromosomes[getMinChromosome()].printElements();
        chromosomes[getMaxChromosome()].printAverageElements();

    }
}


