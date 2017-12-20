/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ine5404.Model;

import java.util.LinkedHashMap;
import java.util.Random;

/**
 *
 * @author chiqu
 */
public class EmbaralhadorDiagonalSecundaria implements Embaralhador {

    public boolean distribuirPalavras(int linha, int coluna, String palavra, char[][] grid, LinkedHashMap<Coordenada, Boolean> mapaPalavras) {
          int linhaAleatoria = 0;
         int colunaAleatoria = 0;
         Random gerador = new Random();
         int cont = 0;
        
         if(gerador.nextInt(2)==0){
             palavra = new StringBuilder(palavra).reverse().toString();
         }
       
    do{  
    
      linhaAleatoria = gerador.nextInt(linha);
      colunaAleatoria = gerador.nextInt(coluna);
      cont++;
    }
    while(!validarCoordenada(linhaAleatoria, colunaAleatoria, palavra, grid) && cont<linha);
    cont = 0;
     if(validarCoordenada(linhaAleatoria, colunaAleatoria, palavra, grid)){
          for(int i=0;i<palavra.length();i++){
              grid[linhaAleatoria+i][colunaAleatoria-i]=palavra.charAt(i);
              Coordenada c1 = new Coordenada(linhaAleatoria+i,colunaAleatoria-i);
              mapaPalavras.put(c1, Boolean.FALSE); 
          }
         return true; 
      }else{
         return false;
     }
        
    }

    public boolean validarCoordenada(int linha, int coluna, String palavra, char[][] grid) {
          int aux = 0;
         if(coluna <  palavra.length() -1 || linha >grid.length - palavra.length()){
             return false;
         }
         
        for(int i=0; i<palavra.length();i++){
             if((grid[linha+i][coluna-i] != 0) && (grid[linha+i][coluna-i] != palavra.charAt(i))){
                 aux = 1;
                 break;
             }
        }
       if(aux == 0){
          return true;    
        }else{
            return false;
        }
    }
    
     
    
}
