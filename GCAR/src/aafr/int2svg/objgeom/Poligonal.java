/*
 * Poligonal.java
 *
 * Created on 2 de febrero de 2005, 07:08 PM
 */

package aafr.int2svg.objgeom;

/**
 *
 * @author alfonso
 */
public class Poligonal {
    public int xpoints[];
    public int ypoints[];
    public int npoints;
    
    /**
     * Crea una nueva instancia de Poligonal
     * @param x 
     * @param y 
     * @param npuntos 
     */
    public Poligonal(int []x,int y[], int npuntos) {
        
        xpoints=x;
        ypoints=y;
        
        npoints=npuntos;
        
    }
    
}
