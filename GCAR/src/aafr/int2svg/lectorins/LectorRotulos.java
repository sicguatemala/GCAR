
package aafr.int2svg.lectorins;

import aafr.int2svg.datos.Dato;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author alfonso
 */
public class LectorRotulos {

    /**
     * Nombre del archivo
     */
    private String snomarch;
    /**
     * Objeto tipo File del archiv que se esta recuperando
     */
    private File farch;
    private ArrayList<Dato> aD;

    
    /**
     * 
     * @param snomarch
     * @param aD 
     */
    public LectorRotulos(String snomarch, ArrayList<Dato> aD) {
        this.snomarch = snomarch;
        this.aD = aD;

        if (snomarch != null && aD != null) {
            abreArchivo();
        }

    }

    private void abreArchivo() {
        farch = new File(snomarch);

    }

    public void leeDatos() {


        StringBuffer sb = new StringBuffer();

        try {
            if (farch == null) {
                return;
            }

            //FileReader fr = new FileReader(farch);
   
            InputStreamReader fr= new InputStreamReader(new FileInputStream(snomarch),"UTF-8");

            while (true) {

                int c = fr.read();

                if (c == -1) {
                    break;
                }
                if (c == '\n') {


                    parsea10(sb.toString());


                    sb = new StringBuffer();

                } else {
                    sb.append((char) c);
                }

            }

        } catch (FileNotFoundException fnfex) {

            System.out.println(fnfex);

        } catch (IOException ioex) {

            System.out.println(ioex);


        }


    }

    public int parsea10(String spar) {

        int ce = 0;
        int cr = 0;
        int cd = 0;
        int cm = 0;
        int cl = 0;


        int ctok = 0;


        String sce;
        String scr;
        String scd;
        String scm;
        String scl;
        String sne;
        String snr;
        String snd;
        String snm;
        String snl;


        try {

            StringTokenizer st = new StringTokenizer(spar, "|");

            ctok = st.countTokens();


            sce = st.nextToken();

            if (sce.length() > 0 && sce.charAt(0) != '#') {
                ce = Integer.parseInt(sce);
            }

            scr = st.nextToken();

            if (scr.length() > 0 && scr.charAt(0) != '#') {
                cr = Integer.parseInt(scr);
            }

            scd = st.nextToken();

            if (scd.length() > 0 && scd.charAt(0) != '#') {
                cd = Integer.parseInt(scd);
            }

            scm = st.nextToken();

            if (scm.length() > 0 && scm.charAt(0) != '#') {
                cm = Integer.parseInt(scm);
            }

            scl = st.nextToken();

            if (scl.length() > 0 && scl.charAt(0) != '#') {
                cl = Integer.parseInt(scl);
            }

            sne = st.nextToken();
            snr = st.nextToken();
            snd = st.nextToken();
            snm = st.nextToken();
            snl = st.nextToken();
//System.out.println(snm);
            this.insertaData(ce, cr, cd, cm, cl, sne, snr, snd, snm, snl);


        } catch (NumberFormatException nfex) {

            System.out.println(spar);

        }

        return ctok;

    }

    /**
     * 
     * @param ce
     * @param cr
     * @param cd
     * @param cm
     * @param cl
     * @param ne
     * @param nr
     * @param nd
     * @param nm
     * @param nl
     * @return 
     */
    private int insertaData(int ce, int cr, int cd, int cm, int cl, String ne, String nr, String nd, String nm, String nl) {

        int tam = aD.size();

        for (int i = 0; i < tam; i++) {

            Dato dato = aD.get(i);

            if (dato.nivel2_id == cm && dato.nivel1_id == ce && dato.nivel3_id == cl) {
                dato.sne = ne;
                dato.snr = nr;
                dato.snd = nd;
                dato.snm = nm;
                dato.snl = nl;
                
                return 1;
            }

        }

        return 0;

    }
}
