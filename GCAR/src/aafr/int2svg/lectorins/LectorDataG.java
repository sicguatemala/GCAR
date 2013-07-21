/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aafr.int2svg.lectorins;


import aafr.int2svg.datos.Dato;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author alfonso
 */
public class LectorDataG {

    /**
     * Nombre del archivo
     */
    private String snomarch;
    /**
     * Objeto tipo File del archiv que se esta recuperando
     */
    private File farch;
    /**
     * Vector de Datos
     */
    private ArrayList<Dato> aD;

    /**
     * Crea una instancia de Lectorins
     *
     * @param snomarch Nombre del archivo a recuperar
     * @param aD Datos
     * @param nivel1_id
     * @param alcance_tipo
     *
     */
    public LectorDataG(String snomarch, ArrayList<Dato> aD) {

        this.snomarch = snomarch;

        this.aD = aD;


        if (snomarch != null) {
            abreArchivo();
        }

    }

    /**
     * Metodo que abre archivos
     */
    private void abreArchivo() {
        farch = new File(snomarch);

    }

    /**
     * Metodo que lee un archivo linea por linea
     */
    public void leeDatos() {


        StringBuffer sb = new StringBuffer();

        try {
            if (farch == null) {
                return;
            }

            FileReader fr = new FileReader(farch);

            while (true) {

                int c = fr.read();

                if (c == -1) {
                    break;
                }
                if (c == '\n') {


                    parsea3(sb.toString());


                    sb = new StringBuffer();

                } else {
                    sb.append((char) c);
                }

            }

        } catch (FileNotFoundException fnfex) {
        } catch (IOException ioex) {
        }


    }

    /**
     *
     * Metodo que parsea un archivo de 5 componentes
     *
     * @param spar Cadena a parsear
     * @return Regresa la cantidad de tokens parseados
     *
     */
    public int parsea3(String spar) {


        float valor = 0;
        int marca = 0;

        int ctok = 0;


        String sid;
        String svalor;
        String smarca;



        try {

            StringTokenizer st = new StringTokenizer(spar, ",|");

            ctok = st.countTokens();


            sid = st.nextToken();


            svalor = st.nextToken();

            if (svalor.length() > 0 && svalor.charAt(0) != '#') {
                valor = Float.parseFloat(svalor);
            }

            smarca = st.nextToken();

            if (smarca.length() > 0 && smarca.charAt(0) != '#') {
                marca = Integer.parseInt(smarca);
            }



            aD.add(new Dato(sid, valor, marca));



        } catch (NumberFormatException nfex) {

            System.out.println(spar);

        }

        return ctok;

    }
}
