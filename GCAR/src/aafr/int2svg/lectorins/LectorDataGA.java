package aafr.int2svg.lectorins;

import aafr.int2svg.datos.Dato;
import java.io.*;
import java.util.*;

/**
 *
 * @author alfonso
 */
public class LectorDataGA {

    /**
     * Nombre del archivo
     */
    private String snomarch;
    /**
     * Objeto tipo File del archivo que se esta recuperando
     */
    private File farch;
    /**
     * Vector de Datos
     */
    protected ArrayList<Dato> aD;

    /**
     * Crea una instancia de LectorDataGA
     *
     * @param snomarch Nombre del archivo a recuperar
     * @param aD Datos
     *
     */
    public LectorDataGA(String snomarch, ArrayList<Dato> aD) {

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
     * Método que parsea un archivo de 3 componentes uno de ellos es un
     * identificador de caracteres
     *
     * @param spar Cadena a parsear
     * @return Regresa la cantidad de tokens parseados
     *
     */
    public int parsea3(String spar) {

        double valor = 0;
        int marca = 0;

        int ctok = 0;

        boolean b_nd = false;

        String sid;
        String svalor;
        String smarca;

        try {

            StringTokenizer st = new StringTokenizer(spar, ",|");

            ctok = st.countTokens();

            sid = st.nextToken();

            svalor = st.nextToken();

            if (svalor.length() >= 0 && svalor.charAt(0) != '#') {

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

            Dato daux = new Dato(sid, valor, marca);
            if (b_nd) {
                daux.bvalor_nd = true;
            }
            
            System.out.println(daux);
            aD.add(daux);

        } catch (NumberFormatException nfex) {

            System.out.println(spar);

        }

        return ctok;

    }
}
