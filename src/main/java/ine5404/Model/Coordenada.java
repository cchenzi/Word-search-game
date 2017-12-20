/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ine5404.Model;

/**
 *
 * @author francisco.vicenzi
 */
public class Coordenada {
    
    private int x, y;

    public Coordenada(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
     @Override
    public boolean equals(Object o){
        if(o == null)
            return false;
        
        if(o instanceof Coordenada){
            Coordenada outra = (Coordenada)o;
            
            return this.x == outra.getX() && this.y == outra.getY();
        }
        else{
            return false;
        }        
    }
    
    @Override
    public int hashCode(){
        
        String s = this.x + "" +   this.y;
        
        return 666;
    }
    
   
    
}
