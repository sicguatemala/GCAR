package aafr.int2svg.render.svg;

import aafr.int2svg.datos.Colorc;
import aafr.int2svg.datos.DataXML;
import aafr.int2svg.jncartem.Const;
import aafr.int2svg.objcarto.PoligonoA;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Esta clase esta construida para pintar un conjunto arbitrario de poligonos
 *
 * @author alfonso
 */
public class AreasPolA extends DibSVGG {

    DataXML dxml;
    int suidpol;

    /**
     *
     * @param dxml
     * @param bca
     */
    public AreasPolA(DataXML dxml, boolean bca) {
        super(dxml, bca);

        this.dxml = dxml;

        suidpol = 1;

        this.escalaPoligonos(midxml.aPA);
    }

    /**
     *
     * @return
     */
    @Override
    public boolean pintaMapa() {

        super.pintaMapa();

        try {
            pintaPoligonos();
            super.pon_FinSVG();
        } catch (IOException ex) {
            Logger.getLogger(AreasPolA.class.getName()).log(Level.SEVERE, null, ex);
        }


        return true;
    }

    /**
     *
     * @throws IOException
     */
    private void pintaPoligonos() throws IOException {
        int vtam = midxml.aPA.size();
        for (int i = 0; i < vtam; i++) {

            pintaPoligono(midxml.aPA.get(i));
        }
    }

    /**
     *
     * @param pola
     * @throws IOException
     */
    private void pintaPoligono(PoligonoA pola) throws IOException {


        Rectangle2D rec2d = pola.getBounds2D();
        double dims = rec2d.getHeight() * rec2d.getWidth();

        if (dims == 0.0) {
            return;
        }

        //String suidpol = (pola.sdatos.get(midxml.indfiltro)).trim();
        String strans = "";


        Colorc color = null;
        Double dnivel=new Double(0.0);
        if (midxml.besalfan) {
            int icolor=midxml.obtenRango((pola.sdatos.get(midxml.indfiltro)).trim());
            color = midxml.rango2color(icolor);
          
        } else {
            dnivel = Double.parseDouble((pola.sdatos.get(midxml.indfiltro)).trim());
            int icolor = midxml.obtenRango(dxml.alcance_ce, dnivel.intValue());
            color = midxml.rango2color(icolor);
        }


        String satteve = "";
        if (midxml.bscript) {
            satteve = this.genAttEve(pola, dnivel, suidpol);
        }

        fbwp.write("<polygon  " + satteve);
        fbwp.write(" fill=\"rgb(" + color + ")\" fill-rule=\"nonzero\" " + strans);

        Colorc colorc = this.obtenColorContorno();
        if (colorc.equals(Const.COLINDEFINIDO)) {
            fbwp.write(" stroke=\"rgb(" + color + ")\" stroke-linejoin=\"round\" stroke-width=\"" + midxml.ancholin + "\"");
        } else {
            fbwp.write(" stroke=\"rgb(" + colorc + ")\"  stroke-linejoin=\"round\" stroke-width=\"" + midxml.ancholin + "\"");
        }

        fbwp.write(" id=\"i" + suidpol + "\"");
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

        this.suidpol++;
    }

    /**
     *
     * @param pola
     * @throws IOException
     */
    private void pintaPoligono3(PoligonoA pola) throws IOException {


        String sid = "";
        String strans = "";

        Double dnivel = Double.parseDouble((pola.sdatos.get(midxml.indfiltro)).trim());

        Colorc color = this.obtenColorRango(midxml.obtenRango(dnivel.intValue(), 0));

        fbwp.write("<path ");
        fbwp.write("fill=\"rgb(" + color + ")\"  width=\"0.25\" fill-rule=\"nonzero\" " + strans);

        fbwp.write(" id=\"i" + sid + "\"");

        fbwp.write(" d=\"");

        int i = 0;

        int xa = pola.xpoints[0];
        int ya = pola.ypoints[0];

        fbwp.write("M " + xa + "," + ya);

        i++;

        while (true) {

            xa = pola.xpoints[i];
            ya = pola.ypoints[i];

            fbwp.write(" L" + xa + "," + ya);

            i++;

            if (i == pola.npoints) {
                break;
            }

        }

        fbwp.write(" z \"/>");

    }

    /**
     * M&eacute;todo que genera las etiquetas para desplegar sobre los poligonos
     *
     * @param pola
     * @return String que contiene la bandera
     */
    private String genAttEve(PoligonoA pola, Double dnivel, int sid) {
        String sbuff = "";

        String bandera = "";

        float valor = midxml.obtenValor(dxml.alcance_ce, dnivel.intValue());

        if (valor > 0 || valor < 0) {
            bandera = midxml.obtenNombre(dxml.alcance_ce, dnivel.intValue()) + " (" + this.formateaValor(valor) + ")";
        } else {
            bandera = midxml.obtenNombre(dxml.alcance_ce, dnivel.intValue());
        }

        sbuff += " onmouseover=\"muestraToolTip(evt,'" + bandera + "','i" + sid + "')\" onmouseout=\"ocultaToolTip(evt)\"";

        if (valor > 0) {
            switch (midxml.subtipom) {
                case Const.MUNICIPAL:
                    sbuff += " onclick=\"pol_onclickM(" + midxml.alcance_ce + "," + dnivel.intValue() + ")\"";
                    break;
                case Const.DISTRITAL:
                    sbuff += " onclick=\"pol_onclickD(" + midxml.alcance_ce + "," + dnivel.intValue() + ")\"";
                    break;
                case Const.REGIONAL:
                    sbuff += " onclick=\"pol_onclickR(" + midxml.alcance_ce + "," + dnivel.intValue() + ")\"";
                    break;
            }
        }



        return sbuff;
    }

    /**
     * @todo Poner el formateo decimal para las catidades enteras
     * @param valor
     * @return
     */
    private String formateaValor(float valor) {
        int valor_e = Math.round(valor);

        if (valor_e == (int) valor) {
            return (new Integer(valor_e)).toString();
        }

        DecimalFormat df2 = new DecimalFormat("###,##0.00");

        return (new Double(df2.format(valor))).toString();
    }
}
