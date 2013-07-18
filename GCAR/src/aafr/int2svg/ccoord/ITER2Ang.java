/*
 * ITER2Ang.java
 *
 * Created on 9 de agosto de 2005, 12:31 AM
 *
 
 */

package aafr.int2svg.ccoord;

/**
 * Clase que convierte coordenadas ITER (INEGI) a DEG
 * @author alfonso
 */
public class ITER2Ang {
    
    public double lon;
    public double lat;
    
    /** 
     * Crea una nueva instancia de ITER2Ang 
     * @param slongitud
     * @param slatitud 
     */
    public ITER2Ang(String slongitud,String slatitud) {
        
        lon=Double.parseDouble(slongitud.substring(0,3));
        lon+=Double.parseDouble(slongitud.substring(3,5))/60.00;
        lon+=Double.parseDouble(slongitud.substring(5,7))/3600.00;
        
        lon *=-1.00;
        
        lat=Double.parseDouble(slatitud.substring(0,2));
        lat+=Double.parseDouble(slatitud.substring(2,4))/60.00;
        lat+=Double.parseDouble(slatitud.substring(4,6))/3600.00;
        
    }
    
}
