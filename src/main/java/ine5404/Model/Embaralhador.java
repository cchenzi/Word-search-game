/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ine5404.Model;

import java.util.LinkedHashMap;

/**
 *
 * @author francisco.vicenzi
 */
public interface Embaralhador {
    
    public boolean distribuirPalavras(int linha, int coluna, String palavra, char[][] grid, LinkedHashMap<Coordenada, Boolean> mapaPalavras);
    public boolean validarCoordenada(int linha, int coluna, String palavra, char[][] grid);
    
}
