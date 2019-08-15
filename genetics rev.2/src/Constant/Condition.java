/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Constant;

/**
 * extends java.util.function.Function< T, R>
 *
 * @author Mahmud
 * @param <T> Double types
 */
public interface Condition<T> {

    Boolean compute(T... x);

}


