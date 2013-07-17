package gob.conaculta.render.svg;

import gob.conaculta.datos.Colorc;
import gob.conaculta.datos.DataXML;
import gob.conaculta.datos.Dato;
import gob.conaculta.jncartem.Const;
import gob.conaculta.objcarto.PoligonoA;
import java.awt.Point;
import java.io.IOException;

/**
 *
 * @author AAFR
 */
public class ConcentraRecG extends DibSVGG {

    /**
     *
     * @param midxml
     */
    public ConcentraRecG(DataXML midxml) {

        super(midxml, true);
        
        this.escalaPoligonos(midxml.aPA);

        if (Const.BDEP) {
            System.out.println("DEPURACION: ConcentraRecG2");
        }

    }

    @Override
    public boolean pintaMapa() {

        super.pintaMapa();


        try {


            pintaPoligonoA();

            pintaCL();


            super.pon_FinSVG();


        } catch (IOException ioex) {
        }



        return true;
    }

    void pintaPoligonoA() throws IOException {
        int atam = midxml.aPA.size();

        for (int i = 0; i < atam; i++) {
            this.pintaPoligono2(midxml.aPA.get(i));

        }
    }

    void pintaCL() throws IOException {


        if (Const.BDEP) {
            System.out.println("DEPURACION: ConcentraRec.pintaCL");
        }

        int vtam = midxml.aDatos.size();

        for (int i = 0; i < vtam; i++) {
            Dato dat = midxml.aDatos.get(i);
            pintaCirculo(dat);
        }
    }

    private void pintaPoligono2(PoligonoA pola) throws IOException {


        String sid = (pola.sdatos.get(midxml.indfiltro)).trim();
        String strans = "";




        fbwp.write("<polygon ");
        fbwp.write("fill=\"" + midxml.scfondo + "\"  stroke-linejoin=\"round\" stroke-width=\""+midxml.ancholin+"\" fill-rule=\"nonzero\" " + strans);


        Colorc colorc = this.obtenColorContorno();
        if (!colorc.equals(Const.COLINDEFINIDO)) {
            fbwp.write(" stroke=\"rgb(" + colorc + ")\" ");
        } else {
            fbwp.write(" stroke=\"" + midxml.scfondo + "\" ");
        }


        //fbwp.write(" id=\"i" + sid + "\"");
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

    /**
     * 
     * @param dat
     * @throws IOException 
     */
    void pintaCirculo(Dato dat) throws IOException {



        dat = escalaPunto(dat);

        Point pl = new Point((int) dat.este, (int) dat.norte);
        
        
        String scolor= "rgb(" + midxml.rango2color(dat.rango).toString() + ")";
        
        if(midxml.alrrc.size()>0){
            dat.radio=midxml.rango2radio(dat.rango);
            scolor=midxml.rango2colorr(dat.rango);
        }
        
        

        fbwp.write("<circle opacity=\".7\" fill=\"" + scolor + "\"  cx=\"" + pl.x + "\" cy=\"" + pl.y + "\" r=\"" + dat.radio + "\"/>");

    }
    
    
    
}
