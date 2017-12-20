/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ine5404.View;

import ine5404.Control.Controller;
import ine5404.Model.Coordenada;
import ine5404.Model.Jogo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

/**
 *
 * @author chiqu
 */
public class TelaJogo {
    
    JFrame frame = new JFrame("GridLayout");
    private  Controller controller;
    JPanel painelBotoes = new JPanel();
    JPanel painelLabels = new JPanel();
    
    JToggleButton[][] botoesGrid;
    JLabel[] labelPalavrasSorteadas;
    JCheckBox[] checkBoxPalavras;
    
    
    public TelaJogo(int linha, int coluna, char[][] grid, Controller controller){
        this.controller = controller;
        }    
    
    
     public void CriarBotoes(Jogo jogo){
     int linha = jogo.getLinha();
     int coluna = jogo.getColuna();
     painelBotoes.setLayout(new GridLayout(linha,coluna));
     botoesGrid=new JToggleButton[linha][coluna]; 
 
        for(int x=0; x<linha; x++){
            for(int y=0; y<coluna; y++){
                botoesGrid[x][y]=new JToggleButton("");
           //     botoesGrid[x][y].setPreferredSize(new Dimension(50,35));
                Coordenada cord = new Coordenada(x,y);
                botoesGrid[x][y].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        controller.mudarEstadoCoordenada(jogo, cord);
                          if(controller.checarJogoAcabou(jogo, cord)){
                          for(int i =0;i<jogo.getNumeroPalavras();i++){
                             labelPalavrasSorteadas[i].setForeground(Color.green);
                          }
                          for(int z=0;z<linha;z++){
                              for(int g=0;g<coluna;g++){
                                  botoesGrid[z][g].setEnabled(false);
                              }
                          }
                          JOptionPane.showMessageDialog(null, "Ganhou!");
                         
                      }
                           
                      
                    }
                });
                painelBotoes.add(botoesGrid[x][y]); 
                }
     }
     
     }
     
    public void CriarLabels(Jogo jogo){
        labelPalavrasSorteadas = new JLabel[jogo.getNumeroPalavras()];
        checkBoxPalavras = new JCheckBox[jogo.getNumeroPalavras()];
        painelLabels.setLayout(new GridLayout(jogo.getNumeroPalavras(),2));
        
        for(int i =0;i<jogo.getNumeroPalavras();i++){
           labelPalavrasSorteadas[i] = new JLabel(jogo.getPalavrasSorteadas()[i]);
           // checkBoxPalavras[i] = new JCheckBox(jogo.getPalavrasSorteadas()[i]);
           // painelLabels.add(checkBoxPalavras[i]);
            painelLabels.add(labelPalavrasSorteadas[i]);
        }
        
     
          
        
        JButton botaoDesistir = new JButton();
        botaoDesistir.setText("Desistir");
        int linha = jogo.getLinha();
        int coluna = jogo.getColuna();
        botaoDesistir.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                         for(int i =0;i<jogo.getNumeroPalavras();i++){
                             labelPalavrasSorteadas[i].setForeground(Color.green);
                          }
                          for(int z=0;z<linha;z++){
                              for(int g=0;g<coluna;g++){
                                  Coordenada cord = new Coordenada(z,g);
                                  if(jogo.getMapaPalavras().containsKey(cord)){
                                        botoesGrid[z][g].setBackground(Color.red);
                                  }
                                 botoesGrid[z][g].setEnabled(false);
                                 
                              }
                          }
                            JOptionPane.showMessageDialog(null, "Perdeu!");
            }
            
            
        });
        painelLabels.add(botaoDesistir);
      
        
    }
 
     
      public void CarregarPalavras(Jogo jogo){
          int linha = jogo.getLinha();
          int coluna = jogo.getColuna();  
          char[][] grid = jogo.getGrid();
         for(int x=0; x<linha; x++){
            for(int y=0; y<coluna; y++){
                if( "".equals(botoesGrid[x][y].getText())){
                    botoesGrid[x][y].setText(grid[x][y]+"");
                    
                }
            }
        }
     frame.setLayout(new FlowLayout(FlowLayout.CENTER));
     frame.add(painelBotoes,BorderLayout.WEST);
     frame.add(painelLabels, BorderLayout.EAST);
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.pack(); 
     frame.setVisible(true); 
     }
      
      
}
