/*
 * Poligono.java
 *
 * Created on 2 de febrero de 2005, 05:11 PM
 */
package aafr.int2svg.objgeom;

import aafr.int2svg.datos.Colorc;
import java.awt.Polygon;

/**

 *

 * @author alfonso

 */
public class Poligono extends Polygon {

    public Colorc cfondo;
    public Colorc cperimetro;
    public int mag;

    /**
     * Crea una nueva instancia de Poligono
     * @param x 
     * @param y 
     * @param nump 
     */
    public Poligono(int[] x, int[] y, int nump) {

        super(x, y, nump);
        mag = -1;

    }
}

