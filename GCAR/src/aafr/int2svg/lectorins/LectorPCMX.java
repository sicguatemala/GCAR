

package aafr.int2svg.lectorins;


import aafr.int2svg.ccoord.AITRF2CCL;
import aafr.int2svg.datos.Dato;
import java.util.ArrayList;

/**
 *
 * @author alfonso
 */
public class LectorPCMX extends LectorPC{

    private final AITRF2CCL ac;
 
    /**
     *
     * @param snomarch
     * @param adato
     */
    public LectorPCMX(String snomarch, ArrayList<Dato> adato) {
        super(snomarch, adato);
       
        ac=new AITRF2CCL();
    }
    
    
    /**
     * 
     * @param longitud
     * @param latitud
     * @param valor
     * @param marca 
     */
    @Override
    protected void insertaDato(double longitud, double latitud, float valor, int marca) {
        
        ac.Grados2PCCL(longitud, latitud);
        
        Dato daux = new Dato(ac.obten_E(), ac.obten_N(), valor, marca);
        daux.radio = valor;

        aD.add(daux);
    }
}
