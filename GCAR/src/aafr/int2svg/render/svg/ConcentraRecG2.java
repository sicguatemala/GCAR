
package aafr.int2svg.render.svg;

import aafr.int2svg.datos.DataXML;
import aafr.int2svg.datos.Dato;
import java.awt.Point;
import java.io.IOException;

/**
 *
 * @author AAFR
 */
public class ConcentraRecG2 extends ConcentraRecG{
    
    
    
    public ConcentraRecG2(DataXML midxml){
        super(midxml);
    }
    
    /**
     * 
     * @param dat
     * @throws IOException 
     */
    @Override
    void pintaCirculo(Dato dat) throws IOException {

        dat = escalaPunto(dat);

        Point pl = new Point((int) dat.este, (int) dat.norte);
                
        String scolor= "rgb(" + midxml.rango2color(dat.rango).toString() + ")";
                     
        fbwp.write("<circle opacity=\".7\" fill=\"" + scolor + "\"  cx=\"" + pl.x + "\" cy=\"" + pl.y + "\" r=\"" + dat.radio + "\"/>");

    }
}
