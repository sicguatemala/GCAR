/*
 * Frontera.java
 *
 * Created on 8 de febrero de 2005, 05:59 PM
 */

package gob.conaculta.objcarto;

import gob.conaculta.objgeom.Poligonal;

/**
 *
 * @author AAFR
 */
public class Frontera extends Poligonal{
    
    public String  stipo;
    
    
    
    /**
     * Crea una nueva instancia de Frontera
     * @param x 
     * @param y 
     * @param npuntos 
     * @param stipo 
     */
    public Frontera(int []x,int y[], int npuntos,String stipo) {
        
        super(x,y,npuntos);
        
        this.stipo=stipo;
        
    }
    
    
    
    
}
