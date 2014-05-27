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
 * @author AAFR <alffore@gmail.com>
 */
public class LectorPC {

    private String snomarch;
    File farch = null;
    ArrayList<Dato> aD = null;

    /**
     *
     * @param snomarch
     * @param adato
     */
    public LectorPC(String snomarch, ArrayList<Dato> adato) {

        this.snomarch = snomarch;

        this.aD = adato;

        abreArchivo();
    }

    /**
     *
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

                    parsea(sb.toString());

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
     * Metodo que parsea una entrada de coordenadas geograficar con marca valor
     * Modelo:
     *
     * longitud,latitud,valor,marca
     *
     * @return La cantidad de tokens procesados
     */
    public int parsea(String spar) {

        double longitud = 0.0;
        double latitud = 0.0;

        float valor = 0;
        int marca = 0;

        int ctok = 0;

        String slon;
        String slat;
        String svalor;
        String smarca;

        try {

            StringTokenizer st = new StringTokenizer(spar, ",|");

            ctok = st.countTokens();

            slon = st.nextToken();

            if (slon.length() > 0 && slon.charAt(0) != '#') {
                longitud = Double.parseDouble(slon);
            }

            slat = st.nextToken();

            if (slat.length() > 0 && slat.charAt(0) != '#') {
                latitud = Double.parseDouble(slat);
            }

            svalor = st.nextToken();

            if (svalor.length() > 0 && svalor.charAt(0) != '#') {
                valor = Float.parseFloat(svalor);
            }

            smarca = st.nextToken();

            if (smarca.length() > 0 && smarca.charAt(0) != '#') {
                marca = Integer.parseInt(smarca);
            }

            insertaDato(longitud, latitud, valor, marca);

        } catch (NumberFormatException nfex) {

            System.out.println(spar);

        }

        return ctok;

    }

    /**
     *
     * @param longitud
     * @param latitud
     * @param valor
     * @param marca
     */
    protected void insertaDato(double longitud, double latitud, float valor, int marca) {
        Dato daux = new Dato(longitud, latitud, valor, marca);
        daux.radio = valor;

        aD.add(daux);
    }
}
