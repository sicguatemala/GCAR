/*
 * PoligonoR.java
 *
 * Created on 16 de abril de 2005, 10:53 AM
 */

package gob.conaculta.objgeom;

import java.awt.Polygon;

/**
 *
 * @author alfonso
 */
public class PoligonoR extends Polygon{
    
    
    public boolean []bpu;
    
    /** 
     * Crea una nueva instancia de PoligonoR 
     */
    public PoligonoR(int []x,int []y,int n) {
        super(x,y,n);
        
        bpu = new boolean[n];
        for(int i=0;i<n;i++)bpu[i]=false;
    }
    
}
