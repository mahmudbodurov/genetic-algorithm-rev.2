/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Constant;

/**
 *extends java.util.function.Function< T, R>
 * @author Mahmud
 * @param <T>
 * @param <R>
 */
public interface Functions<T, R>  {
    R compute(T ... x);

}







