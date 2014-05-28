
package aafr.int2svg.jncartem;

import aafr.int2svg.datos.Colorc;

/**
 * Clase que define unas constantes comunes a todo el desarrollo
 * @author AAFR
 */
public class Const {

    public static final boolean BDEP = true;
    
    
    public static final int NIVEL_MULTI12 = 1;
    
    public static final int CONCENTRACION1 = 40;
    public static final int CONCENTRACION2 = 30;
    
    public static final int CAPAS2 = 20; // capas simples
    public static final int CAPAPOLI=10; //capas multipoligonales 
    
    
    //subtipo de mapas
    public static final int MUNICIPAL=3;
    public static final int DISTRITAL=2;
    public static final int REGIONAL=1;
    public static final int ESTATAL=4;
    
/**
 * 
 */
    public static final String DIRBASE="";
    

    
    /**
     * Color indefinido
     */
    public static final Colorc COLINDEFINIDO_SISTEMA = new Colorc(209, 209, 209, "INDEFINIDO", -1);
}
