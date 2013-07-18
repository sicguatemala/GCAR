package aafr.int2svg.render.svg;

import aafr.int2svg.datos.Colorc;
import aafr.int2svg.datos.DataXML;
import aafr.int2svg.objcarto.PoligonoA;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author alfonso
 */
public class AreasPolig extends AreasPolAM {

    /**
     * 
     * @param dxml
     * @param bca 
     */
    public AreasPolig(DataXML dxml, boolean bca) {
        super(dxml, bca,false);
    }

    /**
     * 
     * @param aPA
     * @param dtrans
     * @param icapa
     * @throws IOException 
     */
    @Override
    void pintaCapa(ArrayList<PoligonoA> aPA, double dtrans, int icapa) throws IOException {
        int vtam = aPA.size();
        for (int i = 0; i < vtam; i++) {

            pintaPoligonal2(aPA.get(i), dtrans, icapa);
        }
    }

    /**
     * 
     * @param pola
     * @param dtrans
     * @param icapa
     * @throws IOException 
     */
    private void pintaPoligonal(PoligonoA pola, double dtrans, int icapa) throws IOException {


        String sid = "";
        String strans = "";

        Double dnivel = Double.parseDouble((pola.sdatos.get(midxml.indfiltro)).trim());

        Colorc color = this.obtenColorRango(midxml.obtenRango(dnivel.intValue(), 0));

        fbwp.write("<path ");
        fbwp.write("stroke=\"rgb(" + color + ")\"  stroke-width=\"0.25\" fill=\"none\" " + strans);

        fbwp.write(" id=\"i" + sid + "\"");

        fbwp.write(" d=\"");

        int i = 0;

        int xa = pola.xpoints[0];
        int ya = pola.ypoints[0];

        fbwp.write("M " + xa + "," + ya);

        i++;

        while (true) {
            if (i == pola.npoints) {
                break;
            }
            
            xa = pola.xpoints[i];
            ya = pola.ypoints[i];

            fbwp.write(" L" + xa + "," + ya);

            i++;



        }

        fbwp.write(" \"/>");

    }
    
    
       /**
     * 
     * @param pola
     * @param dtrans
     * @param icapa
     * @throws IOException 
     */
    private void pintaPoligonal2(PoligonoA pola, double dtrans, int icapa) throws IOException {


        String sid = "";
        String strans = "stroke-opacity=\"" + dtrans + "\"";
        int i = 0;



        Colorc color = midxml.rango2color(0);
        
        fbwp.write("<polyline ");
        fbwp.write("stroke=\"rgb(" + color + ")\" stroke-width=\"1\" stroke-linejoin=\"round\" fill=\"none\" " + strans);

        fbwp.write(" id=\"i" + i + "\"");

        fbwp.write(" points=\"");

        

        int xa = pola.xpoints[0];
        int ya = pola.ypoints[0];

        fbwp.write( xa + "," + ya);

        i++;

        while (true) {
            if (i == pola.npoints) {
                break;
            }
            
            xa = pola.xpoints[i];
            ya = pola.ypoints[i];

            fbwp.write(" " + xa + "," + ya);

            i++;



        }

        fbwp.write("\" />");

    }
}
