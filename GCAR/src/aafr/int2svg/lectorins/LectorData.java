/*
 * lectorins.java
 *
 * Created on 2 de febrero de 2005, 02:22 PM
 */
package aafr.int2svg.lectorins;

import aafr.int2svg.ccoord.Ang2CCL;
import aafr.int2svg.ccoord.ITER2Ang;
import java.io.*;
import java.util.*;
import aafr.int2svg.datos.Dato;
import aafr.int2svg.jncartem.Const;

/**
 *
 * Clase que lee un archivo de data en formato CSV, para 1 a 3 niveles
 *
 * @author alfonso
 *
 */
public class LectorData {

    /**
     * Nombre del archivo
     */
    private String snomarch;
    /**
     * Objeto tipo File del archivo que se esta recuperando
     */
    private File farch;
    private final int cedo;
    private  int alcance_tipo;
    /**
     * Vector de Datos
     */
    private ArrayList<Dato> aD;
    private Ang2CCL a2ccl;

    /**
     * Crea una instancia de LectorData
     *
     * @param snomarch Nombre del archivo a recuperar
     * @param aD Datos
     * @param cedo
     * @param alcance_tipo
     *
     */
    public LectorData(String snomarch, ArrayList<Dato> aD, int cedo, int alcance_tipo) {

        this.snomarch = snomarch;

        this.aD = aD;

        this.cedo = cedo;
        this.alcance_tipo = alcance_tipo;

        a2ccl = new Ang2CCL();
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

                    parsea5(sb.toString());

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
    public int parsea5(String spar) {

        int ce = 0;
        int cm = 0;
        int cl = 0;
        float valor = 0;
        int marca = 0;

        int ctok = 0;

        String sce;
        String scm;
        String scl;
        String svalor;
        String smarca;

        try {

            StringTokenizer st = new StringTokenizer(spar, ",|");

            ctok = st.countTokens();

            sce = st.nextToken();

            if (sce.length() > 0 && sce.charAt(0) != '#') {
                ce = Integer.parseInt(sce);
            }

            scm = st.nextToken();

            if (scm.length() > 0 && scm.charAt(0) != '#') {
                cm = Integer.parseInt(scm);
            }

            scl = st.nextToken();

            if (scl.length() > 0 && scl.charAt(0) != '#') {
                cl = Integer.parseInt(scl);
            }

            svalor = st.nextToken();

            if (svalor.length() > 0 && svalor.charAt(0) != '#') {
                valor = Float.parseFloat(svalor);
            }

            smarca = st.nextToken();

            if (smarca.length() > 0 && smarca.charAt(0) != '#') {
                marca = Integer.parseInt(smarca);
            }

            if (Const.BDEP) {
                System.out.println(ce + ", " + cm + ", " + cl + ", " + valor + ", " + marca);
            }

            if (ce == cedo || cedo == 0) {

                aD.add(new Dato(ce, cm, cl, valor, marca));

            }

        } catch (NumberFormatException nfex) {

            System.out.println(spar);

        }

        return ctok;

    }

    /**
     * Metodo que parsea un archivo de 6 componentes
     *
     * @param spar Cadena a parsear
     * @return Regresa la cantidad de tokens parseados
     */
    public int parsea6(String spar) {

        int ce = 0;
        int cm = 0;
        int cl = 0;
        float valor = 0;
        int marca = 0;
        int ctok = 0;

        String sce;
        String scm;
        String scl;
        String svalor;
        String smarca;

//        String seste;
//
//        String snorte;
        try {

            StringTokenizer st = new StringTokenizer(spar, ",|");

            ctok = st.countTokens();

            sce = st.nextToken();

            if (sce.length() > 0 && sce.charAt(0) != '#') {
                ce = Integer.parseInt(sce);
            }

            scm = st.nextToken();

            if (scm.length() > 0 && scm.charAt(0) != '#') {
                cm = Integer.parseInt(scm);
            }

            scl = st.nextToken();

            if (scl.length() > 0 && scl.charAt(0) != '#') {
                cl = Integer.parseInt(scl);
            }

            svalor = st.nextToken();

            if (svalor.length() > 0 && svalor.charAt(0) != '#') {
                valor = Float.parseFloat(svalor);
            }

            smarca = st.nextToken();

            if (smarca.length() > 0 && smarca.charAt(0) != '#') {
                marca = Integer.parseInt(smarca);
            }

            if (ce == cedo || cedo == 0) {

                Dato daux = new Dato(ce, cm, cl, valor, marca);

                aD.add(daux);

            }

        } catch (NumberFormatException nfex) {

            System.out.println(spar);

        }

        return ctok;

    }

    /**
     * Metodo que parsea un archivo de 6 componentes
     *
     * @param spar Cadena a parsear
     * @return Regresa la cantidad de tokens parseados
     */
    public int parsea7(String spar) {

        int ce = 0;
        int cm = 0;
        int cl = 0;
        float valor = 0;
        int marca = 0;
        int ctok = 0;

        String sce;
        String scm;
        String scl;
        String svalor;
        String smarca;
        String seste;
        String snorte;

        try {

            StringTokenizer st = new StringTokenizer(spar, ",|");

            ctok = st.countTokens();

            sce = st.nextToken();

            if (sce.length() > 0 && sce.charAt(0) != '#') {
                ce = Integer.parseInt(sce);
            }

            scm = st.nextToken();

            if (scm.length() > 0 && scm.charAt(0) != '#') {
                cm = Integer.parseInt(scm);
            }

            scl = st.nextToken();

            if (scl.length() > 0 && scl.charAt(0) != '#') {
                cl = Integer.parseInt(scl);
            }

            svalor = st.nextToken();

            if (svalor.length() > 0 && svalor.charAt(0) != '#') {
                valor = Float.parseFloat(svalor);
            }

            smarca = st.nextToken();

            if (smarca.length() > 0 && smarca.charAt(0) != '#') {
                marca = Integer.parseInt(smarca);
            }

            seste = st.nextToken();

            snorte = st.nextToken();

            ITER2Ang i2a = new ITER2Ang(seste, snorte);

            a2ccl.Grados2PCCL(i2a.lon, i2a.lat);

            if (ce == cedo || cedo == 0) {

                Dato daux = new Dato(ce, cm, cl, valor, marca);

                daux.este = a2ccl.obten_E();

                daux.norte = a2ccl.obten_N();

                aD.add(daux);

            }

        } catch (NumberFormatException nfex) {

        }

        return ctok;

    }
}
