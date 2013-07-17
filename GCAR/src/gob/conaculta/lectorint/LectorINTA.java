package gob.conaculta.lectorint;

import gob.conaculta.datos.DataXML;
import gob.conaculta.jncartem.Const;
import gob.conaculta.objcarto.PoligonoA;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Clase que lee poligonos arbitrarios
 *
 * @author alfonso
 */
public class LectorINTA extends LectorINT {

    ArrayList<PoligonoA> aPA;
    DataXML midxml;

    /**
     *
     * @param midxml
     */
    public LectorINTA(DataXML midxml) {

        super(midxml.sdirMGM);
        this.midxml = midxml;

        if (Const.BDEP) {
            System.out.println("DEPURACION: LectorINTA entrada: " + midxml.sdirMGM);
        }


        this.aPA = midxml.aPA;

        leedatos();

    }

    /**
     *
     * @param midxml
     * @param sarchivo
     */
    public LectorINTA(DataXML midxml, String sarchivo) {

        super(sarchivo);
        this.midxml = midxml;

        if (Const.BDEP) {
            System.out.println("DEPURACION: LectorINTA con archivo entrada: " + sarchivo);
        }


        this.aPA = midxml.aPA;



        leedatos();


    }

    /**
     *
     * @param midxml
     * @param sarchivo
     * @param aPa
     */
    public LectorINTA(DataXML midxml, String sarchivo, ArrayList<PoligonoA> aPA) {

        super(sarchivo);
        this.midxml = midxml;

        if (Const.BDEP) {
            System.out.println("DEPURACION: LectorINTA con archivo entrada: " + sarchivo);
        }


        this.aPA = aPA;



        leedatos();


    }

    /**
     *
     * @param spar
     * @param linea
     * @return
     */
    @Override
    public int parsea(String spar, int linea) {

        StringTokenizer st = new StringTokenizer(spar, "|");

        ArrayList<String> vpres = new ArrayList<String>();

        while (st.hasMoreTokens()) {
            vpres.add(st.nextToken());
        }

        int vtvp = vpres.size();

        if (this.midxml.alcance_ce == 0 || midxml.alcance_ce == Integer.parseInt(vpres.get(midxml.indfiltro2))) {

            if (vpres.get(0).equals("SHPT_POLYGON") || vpres.get(0).equals("SHPT_ARC")) {


                st = new StringTokenizer(vpres.get(2), ",");

                int npuntos = st.countTokens() / 2;

                int[] x = new int[npuntos];

                int[] y = new int[npuntos];

                int j = 0;

                while (st.hasMoreTokens()) {

                    Double fx = Double.parseDouble(st.nextToken());
                    Double fy = Double.parseDouble(st.nextToken());


                    x[j] = Math.round((new Float(fx.doubleValue() * midxml.factoresc)).floatValue());
                    y[j] = Math.round((new Float(fy.doubleValue() * midxml.factoresc)).floatValue());


                    j++;

                }

                PoligonoA paux = new PoligonoA(x, y, npuntos, vpres);
                paux.invalidate();
                aPA.add(paux);


            }
        }

        return vtvp;

    }
}
