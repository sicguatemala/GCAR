

package aafr.int2svg.objcarto;

import aafr.int2svg.objgeom.Poligono;
import java.util.ArrayList;

/**
 * Esta clase representa un poligono arbitrario
 * @author AAFR <alffore@gmail.com>
 */
public class PoligonoA extends Poligono{

    public ArrayList<String> sdatos=null;

    /**
     * 
     * @param xp
     * @param yp
     * @param numpuntos
     * @param sdatos 
     */
    public PoligonoA(int[]xp,int[]yp,int numpuntos,ArrayList<String> sdatos){
        super(xp,yp,numpuntos);
        this.sdatos=sdatos;
    }
}
