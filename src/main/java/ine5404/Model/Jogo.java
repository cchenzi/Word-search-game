/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ine5404.Model;


import ine5404.Exception.EntradaDadosInvalidaException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.List;
import ine5404.Model.Embaralhador.*;
import static ine5404.Model.Jogo.Dificuldade.EmbaralhadorDiagonalPrincipal;
import static ine5404.Model.Jogo.Dificuldade.EmbaralhadorHorizontal;
import static ine5404.Model.Jogo.Dificuldade.EmbaralhadorVertical;
import static ine5404.Model.Jogo.Dificuldade.EmbaralhadorDiagonalSecundaria;
import static java.lang.Math.random;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author francisco.vicenzi
 */
public class Jogo {

  
    
    public enum Dificuldade{
        EmbaralhadorDiagonalPrincipal,
        EmbaralhadorDiagonalSecundaria,
        EmbaralhadorHorizontal,
        EmbaralhadorVertical
    }
      private Embaralhador Embaralhador(Dificuldade selecionarDificuldadeAleatoria) {
        switch(selecionarDificuldadeAleatoria){
            case EmbaralhadorDiagonalPrincipal:
                return new EmbaralhadorDiagonalPrincipal();
            case EmbaralhadorDiagonalSecundaria:
                return new EmbaralhadorDiagonalSecundaria();
            case EmbaralhadorHorizontal:
                return new EmbaralhadorHorizontal();
            case EmbaralhadorVertical:
                return new EmbaralhadorVertical();
        }
        return null;
    }
    
    private List<Dificuldade> dificuldades;
    private Map<Coordenada, Boolean> mapaPalavras;
    private Map<Coordenada, Boolean> mapaLetrasAleatorias;
    private int linha,coluna,numeroPalavras;
    private String[] palavrasSorteadas;
    private char[][] grid;
    
    
    public Jogo(int linha, int coluna, int numeroPalavras, List<Dificuldade> dificuldades){
        
        grid = new char[linha][coluna];
        this.linha=linha;
        this.coluna=coluna;
        this.numeroPalavras=numeroPalavras;
        this.palavrasSorteadas = new String[numeroPalavras];
        this.dificuldades = dificuldades;
        this.mapaPalavras = new LinkedHashMap<>();
        this.mapaLetrasAleatorias = new LinkedHashMap<>();
       
    }

    public int getLinha() {
        return linha;
    }

    public Map<Coordenada, Boolean> getMapaPalavras() {
        return mapaPalavras;
    }

    public void setMapaPalavras(Map<Coordenada, Boolean> mapaPalavras) {
        this.mapaPalavras = mapaPalavras;
    }

   

  

  

    public Map<Coordenada, Boolean> getMapaLetrasAleatorias() {
        return mapaLetrasAleatorias;
    }

    public void setMapaLetrasAleatorias(Map<Coordenada, Boolean> mapaLetrasAleatorias) {
        this.mapaLetrasAleatorias = mapaLetrasAleatorias;
    }
    
    public void setLinha(int linha) {
        this.linha = linha;
    }

    public int getColuna() {
        return coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

    public int getNumeroPalavras() {
        return numeroPalavras;
    }

    public void setNumeroPalavras(int numeroPalavras) {
        this.numeroPalavras = numeroPalavras;
    }

    public void colocarPalavrasNoGrid() throws IOException{
        boolean aux = false;
        int cont = 0;
        this.guardarPalavra();
        
        for(int i =0; i<palavrasSorteadas.length;i++){
            
            Embaralhador embaralhador;
            embaralhador = Embaralhador(selecionarDificuldadeAleatoria());
            aux = embaralhador.distribuirPalavras(linha, coluna, this.palavrasSorteadas[i], this.grid, (LinkedHashMap<Coordenada, Boolean>) mapaPalavras);
            if(!aux && cont<10){
                substituirPalavra(i);
                i--;
                cont++;
            }else if(!aux && cont>=10){
                this.palavrasSorteadas[i] = this.palavrasSorteadas[i] +" - não coube!";
                cont=0;
                
            }
                else{
                cont=0;
                
            }
          
        }
    this.carregarLetrasAleatoriasNoGrid();
    }
    
   

    public Dificuldade selecionarDificuldadeAleatoria(){
         Random gerador = new Random();
      int numeroAleatorio = gerador.nextInt(dificuldades.size());
        return dificuldades.get(numeroAleatorio);
    }

    public String[] getPalavrasSorteadas() {
        return palavrasSorteadas;
    }
    
    
    
    public void substituirPalavra(int i) throws IOException{
        String novaPalavra = null;
        novaPalavra = lerPalavra();
        palavrasSorteadas[i]=novaPalavra;
    }
    
    
  
    public void setPalavrasSorteadas(String[] palavrasSorteadas) {
        this.palavrasSorteadas = palavrasSorteadas;
    }

    public char[][] getGrid() {
        return grid;
    }
    
    
    
    
    
    public String lerPalavra() throws FileNotFoundException, IOException{
      String palavraLida = "";
        Random gerador = new Random();
      int numeroAleatorio = gerador.nextInt(29762);
        BufferedReader leitorArquivo = new BufferedReader(new FileReader("Lista-de-Palavras.txt"));
        for(int i = 0;i<numeroAleatorio;i++){
            palavraLida = leitorArquivo.readLine();
        }
      return palavraLida;        
    }
    
    public void guardarPalavra() throws IOException{
        for(int i=0;i<palavrasSorteadas.length;i++){
            palavrasSorteadas[i] = this.lerPalavra();
        }        
    }
    
    public void carregarLetrasAleatoriasNoGrid(){
        for(int x=0;x<linha;x++){
            for(int y=0;y<coluna;y++){
                if(grid[x][y]==0){
                    grid[x][y] = this.completarLetra();
                     Coordenada c1 = new Coordenada(x,y);
                     mapaLetrasAleatorias.put(c1, Boolean.FALSE); 
                }
            }
        }
    }
    
    public char completarLetra(){
        char letraAleatoria = 0;
         Random gerador = new Random();
         int numeroAleatorio = 65 + gerador.nextInt(26);
        letraAleatoria = (char) numeroAleatorio;
       
        return letraAleatoria;
    }
    
 
      public String fazerPalavraAoContrario(String palavra){
        String palavraAux = "";
        for(int i=palavra.length()-1;i>=0;i--){
            palavraAux = palavraAux + palavra.charAt(i);
       }
       return palavraAux;
    }

    
  
    
    
    
}
