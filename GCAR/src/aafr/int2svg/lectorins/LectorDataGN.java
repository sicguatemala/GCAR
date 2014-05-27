package aafr.int2svg.lectorins;

import aafr.int2svg.datos.Dato;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Clase para leer datos de un solo nivel 
 * @author AAFR <alffore@gmail.com>
 */
public class LectorDataGN extends LectorDataGA {

    /**
     *
     * @param snomarch
     * @param aD
     */
    public LectorDataGN(String snomarch, ArrayList<Dato> aD) {
        super(snomarch, aD);
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

        double valor = 0;
        int marca = 0;
        int id = 0;

        int ctok = 0;

        boolean b_nd = false;

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

                if (svalor.equalsIgnoreCase("nd")) {
                    valor = Double.NaN;
                } else {
                    valor = Double.parseDouble(svalor);
                }
            }

            smarca = st.nextToken();

            if (smarca.length() > 0 && smarca.charAt(0) != '#') {
                if (smarca.equalsIgnoreCase("nd")) {
                    marca = -1000;
                    b_nd = true;
                } else {
                    marca = Integer.parseInt(smarca);
                }
            }

            Dato daux = new Dato(id, 0, 0, valor, marca);
            if (b_nd) {
                daux.bvalor_nd = true;
            }
            aD.add(daux);

        } catch (NumberFormatException nfex) {

            System.out.println(spar);

        }

        return ctok;

    }
}
