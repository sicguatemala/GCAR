package gob.conaculta.render.svg;

import gob.conaculta.datos.Colorc;
import gob.conaculta.datos.DataXML;
import gob.conaculta.jncartem.Const;
import gob.conaculta.objcarto.PoligonoA;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author  AAFR
 */
public class AreasPolAM extends DibSVGG {

    /**
     * 
     * @param dxml
     * @param bca 
     */
    public AreasPolAM(DataXML dxml, boolean bca) {
        super(dxml, bca);

        this.obtenLimites(dxml.aPA2);

        this.escalaPoligonos(midxml.aPA);
        this.escalaPoligonos(midxml.aPA2);
    }

    /**
     * Metodo que solo utiliza la escala del poligono base (opcionalmente)
     * @param dxml
     * @param bca
     * @param blimPB 
     */
    public AreasPolAM(DataXML dxml, boolean bca, boolean blimPB) {
        super(dxml, bca);

        if (blimPB == true) {
            this.obtenLimites(dxml.aPA2);
        }

        this.escalaPoligonos(midxml.aPA);
        this.escalaPoligonos(midxml.aPA2);
    }

    /**
     * 
     * @return 
     */
    @Override
    public boolean pintaMapa() {

        super.pintaMapa();

        try {
            pintaPoligonos(midxml.aPA, midxml.dtrans[0]);
            pintaCapa(midxml.aPA2, midxml.dtrans[1], 1);
            super.pon_FinSVG();
        } catch (IOException ex) {
            Logger.getLogger(AreasPolA.class.getName()).log(Level.SEVERE, null, ex);
        }


        return true;
    }

    /**
     * 
     * @param aPA
     * @param dtrans
     * @throws IOException 
     */
    void pintaPoligonos(ArrayList<PoligonoA> aPA, double dtrans) throws IOException {
        int vtam = aPA.size();
        for (int i = 0; i < vtam; i++) {

            pintaPoligono(aPA.get(i), dtrans);
        }
    }

    /**
     * 
     * @param aPA
     * @param dtrans
     * @param icapa
     * @throws IOException 
     */
    void pintaCapa(ArrayList<PoligonoA> aPA, double dtrans, int icapa) throws IOException {
        int vtam = aPA.size();
        for (int i = 0; i < vtam; i++) {

            pintaPoligonoCapa(aPA.get(i), dtrans, icapa);
        }
    }

    /**
     * 
     * @param pola
     * @param dtrans
     * @throws IOException 
     */
    private void pintaPoligono(PoligonoA pola, double dtrans) throws IOException {


        Rectangle2D rec2d = pola.getBounds2D();
        double dims = rec2d.getHeight() * rec2d.getWidth();

        if (dims == 0.0) {
            return;
        }



        String sid = (pola.sdatos.get(midxml.indfiltro)).trim();
        String strans = "fill-opacity=\"" + dtrans + "\" stroke-opacity=\"" + dtrans + "\"";

        Double dnivel = Double.parseDouble((pola.sdatos.get(midxml.indfiltro)).trim());
        //Integer inivel=new Integer(dnivel.intValue());

        int icolor = midxml.obtenRango(dnivel.intValue(), 0);

        Colorc color = midxml.rango2color(icolor);


        fbwp.write("<polygon ");
        fbwp.write("fill=\"rgb(" + color + ")\" fill-rule=\"nonzero\" " + strans);


        Colorc colorc = this.obtenColorContorno();
        if (!colorc.equals(Const.COLINDEFINIDO)) {
            fbwp.write(" stroke=\"rgb(" + colorc + ")\" stroke-linejoin=\"round\" stroke-width=\"" + midxml.ancholin + "\"");
        } else {
            fbwp.write(" stroke=\"rgb(" + color + ")\"  stroke-linejoin=\"round\" stroke-width=\"" + midxml.ancholin + "\"");
        }


        fbwp.write(" id=\"i" + sid + "\"");
        fbwp.write(" points=\"");

        int i = 0;

        int xa = pola.xpoints[0];
        int ya = pola.ypoints[0];

        fbwp.write(xa + "," + ya);


        int xant = xa;
        int yant = ya;


        i++;

        while (true) {



            xa = pola.xpoints[i];
            ya = pola.ypoints[i];


            if (xant != xa || yant != ya) {

                fbwp.write(" " + xa + "," + ya);
                xant = xa;
                yant = ya;
            }

            i++;

            if (i == pola.npoints) {
                break;
            }

        }

        fbwp.write("\"/>");
    }

    /**
     * 
     * @param pola
     * @param dtrans
     * @param icapa
     * @throws IOException 
     */
    private void pintaPoligonoCapa(PoligonoA pola, double dtrans, int icapa) throws IOException {

        Colorc color = null;

        String strans = "fill-opacity=\"" + dtrans + "\" stroke-opacity=\"" + dtrans + "\"";

        if (midxml.indfiltro2 > 0) {

            Double dnivel = Double.parseDouble((pola.sdatos.get(midxml.indfiltro2)).trim());
            int icolor = midxml.obtenRango(dnivel.intValue(), 0);

            color = midxml.rango2color(icolor);

        } else {

            color = midxml.rango2color(icapa);
        }

        fbwp.write("<polygon ");
        fbwp.write("fill=\"rgb(" + color + ")\" fill-rule=\"nonzero\" " + strans);



        fbwp.write(" stroke=\"rgb(" + color.toString() + ")\" stroke-linejoin=\"round\" stroke-width=\"0.25\"");

        fbwp.write(" points=\"");

        int i = 0;

        int xa = pola.xpoints[0];
        int ya = pola.ypoints[0];

        fbwp.write(xa + "," + ya);

        i++;

        while (true) {

            xa = pola.xpoints[i];
            ya = pola.ypoints[i];

            fbwp.write(" " + xa + "," + ya);

            i++;

            if (i == pola.npoints) {
                break;
            }

        }

        fbwp.write("\"/>");
    }
}
