/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chart;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Random;
import javafx.scene.chart.PieChart;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Mahmud
 */
public class Data2D {

    DoubleArray array1;
    DoubleArray array2;

    public Data2D() {
        array1 = new DoubleArray();
        array2 = new DoubleArray();
    }

    public Data2D(DoubleArray array1,
            DoubleArray array2) {
        this.array1 = array1;
        this.array2 = array2;
    }

    void addData() {
//        double d[] = {1, 2, 3, 4, 45, 6, 7, 5, 67, 7, 7, 7, 7, 6, 5, 4, 45, 2, 2, 3, 4, 45, 6, 7, 5, 67, 7, 7, 7, 7, 6, 5, 4, 45, 2, 2, 3, 4, 45, 6, 7, 5, 67, 7, 7, 7, 7, 6, 5, 4, 45, 2, 2, 3, 4, 45, 6, 7, 5, 67, 7, 7, 7, 7, 6, 5, 4, 45, 2, 2, 3, 4, 45, 6, 7, 5, 67, 7, 7, 7, 7, 6, 5, 4, 45, 2, 2, 3, 4, 45, 6, 7, 5, 67, 7, 7, 7, 7, 6, 5, 4, 45, 2, 2, 3, 4, 45, 6, 7, 5, 67, 7, 7, 7, 7, 6, 5, 4, 45, 2};
//        for (double e : d) {
//            array1.append(e);
//        }
        Random random = new Random();
        for (int i = 0; i < 1600; i++) {
            array1.append(i * random.nextDouble());

        }
    }

    void printDatas() {
        double d[] = array1.getArray();
        for (double e : d) {
            System.out.println(e);
        }
        System.out.println("array size : " + array1.getArray().length);
        System.out.println("array max : " + array1.getMax());
        System.out.println("array min : " + array1.getMin());
    }

    BufferedImage getChart() {

        BufferedImage image = new BufferedImage(960, 720, BufferedImage.BITMASK);
        Graphics2D d = (Graphics2D) image.getGraphics();
        ///System.out.println(d.getRenderingHints());
        d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        d.setColor(Color.WHITE);
        d.fillRect(0, 0, 960, 720);
        /*
        *chart bottom 980px <= y <= 1080px and 100px<x<1920px
        *chart left 0px <= x <= 100px and 100px < y < 1820px
        *chart draw area 100px < x < 1820px and 100px < y < 980px //y=880 x=1720
         */
        double min_value = array1.getMin();
        double max_value = array1.getMax();
        double range = max_value - min_value;
//cicle diameter = 10px;

        int length = array1.getSize();
        d.setColor(Color.lightGray);
//left side
        double bosluq = 860 / length ;
        for (int i = 0; i < 100; i++) {
//            d.drawString(i + "", i, i);
            d.drawLine(0, (int) (i * 7.2), 960, (int) (i * 7.2));
            d.drawLine((int) (i * 9.6), 0, (int) (i * 9.6), 720);
        }

        d.setColor(Color.BLACK);
        d.setFont(new Font("Arial", Font.BOLD, 8));
        double position = max_value;
        int last_x = 50, last_y = 670;
        while (position >= min_value) {
            d.drawString(String.format("%.6f", position), 1, 50 + (int) (620 - 620 * (position - min_value) / range) + 4);
//            System.out.println("test");
            position -= range / 50;
        }
        int olcu = array2.getSize();
        for (int i = 0; i < 10; i++) {
            d.drawLine(50 + i * 62, 0, 50 + i * 62, 720);
            d.drawString("" + array2.getValue(i * olcu/10), 50 + i * 62, 700);
        }
            d.drawLine(670, 0, 670, 720);
            d.drawString("" + array2.getValue(olcu-1), 670, 700);
        System.out.println(length);
        for (int i = 0; i < length; i++) {
//            d.setColor(new Color(i % 100, i % 255, i % 45));

            int x = (int) (50 + i * bosluq);
            int y = 50 + (int) (620 - 620 * (array1.getValue(i) - min_value) / range);

            d.drawLine(last_x, last_y, x, y);
//            d.fillOval(x, y, bosluq * 2, bosluq * 2);
            last_x = x;
            last_y = y;
//            d.drawString("" + array1.getValue(i), 100 + i * bosluq, 100 + (int) (880 - 880 * array1.getValue(i) / range));
        }
        return image;
    }

}

/*
        BufferedImage image = new BufferedImage(2910, 2910, BufferedImage.BITMASK);
        Graphics2D d = (Graphics2D) image.getGraphics();
        ///System.out.println(d.getRenderingHints());
        d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        d.setColor(Color.WHITE);
        d.drawRect(0, 0, 2910, 2910);
        /*
        *chart bottom 2700px <= y <= 2610px and 300px<x<2610px
        *chart left 0px <= x <= 300px and 0px < y < 2310px
        *chart draw area 300px < x < 2610px and 300px < y < 2310px
         /
        double min_value = array1.getMin();
        double max_value = array1.getMax();
        double range = max_value - min_value;
//cicle diameter = 10px;

        int length = array1.getSize();
        d.setColor(Color.BLACK);
//left side
        for (int i = 0; i < 10; i++) {
//            d.drawString(i + "", i, i);
            d.drawLine(0, i * 291, 2910, i * 291);
            d.drawLine(i * 291, 0, i * 291, 2910);
        }
        //if max value y equals 300px
        int bosluq = 2310 / length;
        d.setFont(new Font("Arial", Font.BOLD, 48));
        for (int i = 0; i < length; i++) {
            d.fillOval(300 + i * bosluq,300+ (int) (2310 - 2310 * array1.getValue(i) / range), 10, 10);
            d.drawString("" + array1.getValue(i), 300 + i * bosluq, (int) (2610 - 2610 * array1.getValue(i) / range));
        }
 */









