/*
 * LocalidadesP.java
 *
 * Created on 2 de febrero de 2005, 07:18 PM
 */

package gob.conaculta.objcarto;

/**
 *
 * @author alfonso
 */
public class LocalidadP {
    
    public int cedo;
    public int cmun;
    public int cloc;
    
    public double norte;
    public double este;
    
    /**
     * Creates a new instance of LocalidadesP
     * @param cedo 
     * @param cmun 
     * @param cloc 
     * @param este 
     * @param norte 
     */
    public LocalidadP(int cedo,int cmun,int cloc,double este,double norte) {
        
        this.cedo=cedo;
        this.cmun=cmun;
        this.cloc=cloc;
        this.norte=norte;
        this.este=este;
        
        
    }
    
}
