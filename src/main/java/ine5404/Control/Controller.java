package ine5404.Control;

import ine5404.Model.Coordenada;
import ine5404.Model.Jogo;
import java.io.IOException;
import java.util.Random;
import ine5404.View.TelaInicial;
import java.util.Map;
import javax.swing.JOptionPane;

public class Controller 
{
    
 public Controller(){
     TelaInicial telaInicial = new TelaInicial(this);
     telaInicial.setVisible(true);
 }
    
 
 public void mudarEstadoCoordenada(Jogo jogo, Coordenada cord){
                if(jogo.getMapaLetrasAleatorias().containsKey(cord)){
                           jogo.getMapaLetrasAleatorias().put(cord, !jogo.getMapaLetrasAleatorias().get(cord));
                       }
                           
                       if(jogo.getMapaPalavras().containsKey(cord)){
                           jogo.getMapaPalavras().put(cord, !jogo.getMapaPalavras().get(cord));
                       }
     
 }
 
    public boolean checarJogoAcabou(Jogo jogo, Coordenada cord){
        boolean checaPalavras = true,checaLetras =true; 
                      for(Map.Entry<Coordenada, Boolean> entry: jogo.getMapaPalavras().entrySet()){
                          checaPalavras = checaPalavras && entry.getValue();
                      }
                       for(Map.Entry<Coordenada, Boolean> entry: jogo.getMapaLetrasAleatorias().entrySet()){
                          checaLetras = checaLetras && !entry.getValue();
                      }
        return checaLetras && checaPalavras;               
                       
        
    }
        
   
}
