/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ine5404.Exception;

/**
 *
 * @author francisco.vicenzi
 */
public class EntradaDadosInvalidaException extends Exception {

    /**
     * Creates a new instance of <code>PalavraNaoCoubeExcepetion</code> without
     * detail message.
     */
    public EntradaDadosInvalidaException() {
    }

    /**
     * Constructs an instance of <code>PalavraNaoCoubeExcepetion</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public EntradaDadosInvalidaException(String msg) {
        super(msg);
    }
}
