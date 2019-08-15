/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetics;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author Mahmud
 */
public class Genetics {

    private static final long serialVersionUID = 0L;

    /**
     * @param args the command line arguments
     */
    Node[] node = new Node[32];
    Node[] nextNode = new Node[32];
    final int N = 32;

    public Genetics() {
        for (int i = 0; i < 32; i++) {
            node[i] = new Node(N);
            nextNode[i] = new Node(N);
        }
    }

    public void crossover() {
        Set set = new HashSet();
        Random rand = new Random();
        int lastMin = 0;
        for (int i = 0; i < 4; i++) {
            int min = minNodeValue(set);
            set.add(min);
            int n = rand.nextInt(N - 3) + 1;
            createElements(node[lastMin], node[min], n, i);
            lastMin = min;
        }
//        for (int i = 0; i < 4; i++) {
//            int n = rand.nextInt(N - 3) + 1;
//            //System.out.println("n = " + n);
//            createElements(node[rand.nextInt(N)], node[rand.nextInt(N)], n, i);
//        }
    }

    public void createElements(Node node1, Node node2, int n, int a) {
        double[] arr = new double[N];
        double[] arr1 = node1.getElements(0, n);
        double[] arr2 = node2.getElements(n, N);
        System.arraycopy(arr1, 0, arr, 0, n);
        System.arraycopy(arr2, 0, arr, n, arr2.length);
        nextNode[a * 2].setElements(arr);
        arr = new double[N];
        arr1 = node2.getElements(0, n);
        arr2 = node1.getElements(n, N);
        System.arraycopy(arr1, 0, arr, 0, n);
        System.arraycopy(arr2, 0, arr, n, arr2.length);
        nextNode[a * 2 + 1].setElements(arr);
    }

//    public void setElement(Node node1, Node node2) {
//        double[] ch1 = new double[N];
//        double[] ch2 = new double[N];
//        double[] arr1 = node1.getElements();
//        double[] arr2 = node2.getElements();
//        Random rand = new Random();
//        int n = rand.nextInt(N - 2) + 2;
//        System.out.println("n=" + n);
//        System.arraycopy(arr1, 0, ch1, 0, n);
//
//        for (int i = 0; i < n; i++) {
//            ch2[i] = arr1[N - i - 1];
//        }
//        for (int i = n; i < N; i++) {
//            ch1[i] = arr2[i];
//            ch2[i] = arr2[i - n];
//        }
//        nextNode[0].setElements(ch1);
//        nextNode[1].setElements(ch2);
//    }
    double function(double x) {
        return Math.pow(x, 2) - 1 + Math.sqrt(Math.abs(x) + 1);//Math.log(Math.cos(x))/Math.log(Math.sin(x));//Math.abs(1-x);//Math.pow(x, 2) - 1 + Math.sqrt(Math.abs(x) + 1);
    }

    public int minNodeValue(Set set) {
        int min = 0;
        for (int i = 0; i < node.length; i++) {
            if (!set.contains(i)) {
                Node node1 = node[i];
                if (node[min].getOverAll() > node1.getOverAll()) {
                    min = i;
                }
            }
        }
        return min;
    }

    double overAll(Node node) {
        double sum = 0;
        double arr[] = node.getElements();
        for (int i = 0; i < N; i++) {
            double d = function(arr[i]);
            sum += d;
            node.setResult(d, i);
        }
        return sum / N;
    }

    public void compute() {
        double[] parents = new double[node.length];
        double[] childs = new double[nextNode.length];
        double p1, p2, ch1, ch2;
        int count = 1;
        Random rand = new Random();
        while (count < 4001) {
            if (count % 5 == 0) {
                System.out.println("-------mutation-------");
                node[rand.nextInt(node.length)].mutation();
            }
            System.out.println("\nGeneration " + count);

            System.out.println("-------crossover-------");
            crossover();

            for (int i = 0; i < parents.length; i++) {
                parents[i] = overAll(node[i]);
            }
            for (int i = 0; i < childs.length; i++) {
                childs[i] = overAll(nextNode[i]);
            }
//            for (double child : childs) {
//                System.out.printf("Child : %.8f\n", child);
//            }
//            for (double parent : parents) {
//                System.out.printf("Parent : %.8f\n", parent);
//            }
            Set set = new HashSet();
            //for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 32; i++) {
                int min = minNodeValue(set);
                set.add(min);
                if ((childs[i]) < (node[min].getOverAll())) {
                    node[min].setElements(nextNode[i].getElements());
//                        break;
                }
            }
            //}
//            System.out.println("new Nodes : ");
//            for (int i = 0; i < 32; i++) {
//                node[i].printElements();
//            }
            System.out.println("-------crossover-------");
            crossover();
            for (int i = 0; i < 32; i++) {
                childs[i] = overAll(nextNode[i]);
            }
            for (double child : childs) {
                System.out.printf("Child : %.8f\n", child);
            }
            count++;
        }
        for (int i = 0; i < node.length; i++) {
            node[i].printElements();
            node[i].printResults();
            System.out.println("----------------");
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
        Genetics g = new Genetics();
        Random rand = new Random();
        double p1 = g.overAll(g.node[0]), p2 = g.overAll(g.node[1]),
                c1 = g.overAll(g.nextNode[0]), c2 = g.overAll(g.nextNode[1]);
        int count = 0;
        while (count < 4000 ) {
            System.out.println("-----------------------");
            if (count % 4 == 0) {
                System.out.println("Mutation");
                g.node[rand.nextInt(g.node.length)].mutation();
            }
            p1 = g.overAll(g.node[0]);
            p2 = g.overAll(g.node[1]);
            g.crossover();
            c1 = g.overAll(g.nextNode[0]);
            c2 = g.overAll(g.nextNode[1]);
            if ((c1) < (p1)) {
                g.node[0].setElements(g.nextNode[0].getElements());
            }
            if ((c2) < (p2)) {
                g.node[1].setElements(g.nextNode[1].getElements());
            }
            g.crossover();
            c1 = g.overAll(g.nextNode[0]);
            c2 = g.overAll(g.nextNode[1]);
            System.out.println(String.format("%.16f", c1));
            System.out.println(String.format("%.16f", c2));
            count++;
        }
        g.compute();
    }

}



