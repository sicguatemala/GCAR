/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aafr.int2svg.lectorins;

import aafr.int2svg.datos.Dato;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author alfonso
 */
public class LectorDataGN extends LectorDataGA {

    
    
    public LectorDataGN(String snomarch, ArrayList<Dato> aD){
        super(snomarch,aD);
    }
    /**
     *
     * Método que parsea un archivo de 3 componentes uno de ellos es un
     * identificador de caracteres
     *
     * @param spar Cadena a parsear
     * @return Regresa la cantidad de tokens parseados
     *
     */
    @Override
    public int parsea3(String spar) {


        float valor = 0;
        int marca = 0;
        int id = 0;

        int ctok = 0;


        String sid;
        String svalor;
        String smarca;



        try {

            StringTokenizer st = new StringTokenizer(spar, ",|");

            ctok = st.countTokens();


            sid = st.nextToken();

            if (sid.length() > 0 && sid.charAt(0) != '#') {
                id = Integer.parseInt(sid);
            }

            svalor = st.nextToken();

            if (svalor.length() > 0 && svalor.charAt(0) != '#') {
                valor = Float.parseFloat(svalor);
            }

            smarca = st.nextToken();

            if (smarca.length() > 0 && smarca.charAt(0) != '#') {
                marca = Integer.parseInt(smarca);
            }




            aD.add(new Dato(id, 0, 0, valor, marca));



        } catch (NumberFormatException nfex) {

            System.out.println(spar);

        }

        return ctok;

    }
}
