package aafr.int2svg.render.svg;

import aafr.int2svg.datos.Colorc;
import aafr.int2svg.datos.DataXML;
import aafr.int2svg.datos.Dato;
import aafr.int2svg.jncartem.Const;
import aafr.int2svg.objcarto.PoligonoA;
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
            System.out.println("DEPURACION: ConcentraRecG");
        }

    }
/**
 * 
 * @return 
 */
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

    /**
     * 
     * @throws IOException 
     */
    void pintaPoligonoA() throws IOException {
        int atam = midxml.aPA.size();

        if (!midxml.bpolpath) {
            for (int i = 0; i < atam; i++) {
                
                pintaPath(midxml.aPA.get(i));
            }
        } else {
            for (int i = 0; i < atam; i++) {
                
                this.pintaPoligono2(midxml.aPA.get(i));
            }
        }
    }

    /**
     * 
     * @throws IOException 
     */
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

    /**
     * 
     * @param pola
     * @throws IOException 
     */
    private void pintaPoligono2(PoligonoA pola) throws IOException {


        String sid = (pola.sdatos.get(midxml.indfiltro1)).trim();
        String strans = "";




        fbwp.write("<polygon ");
        fbwp.write("fill=\"" + midxml.scfondo + "\"  stroke-linejoin=\"round\" stroke-width=\""+midxml.ancholin+"\" fill-rule=\"nonzero\" " + strans);


        Colorc colorc = this.obtenColorContorno();
        if (!colorc.equals(Const.COLINDEFINIDO_SISTEMA)) {
            fbwp.write(" stroke=\"rgb(" + colorc + ")\" ");
        } else {
            fbwp.write(" stroke=\"" + midxml.scfondo + "\" ");
        }


        //fbwp.write(" id=\"i" + sid + "\"");
        fbwp.write(" points=\"");

        int i = 0;

        int xa = pola.xpoints[0];
        int ya = pola.ypoints[0];

        int xant=xa;
        int yant=ya;
        
        fbwp.write(xa + "," + ya);

        i++;

        while (true) {

            xa = pola.xpoints[i];
            ya = pola.ypoints[i];

            if(xant!=xa || yant!=ya){
            fbwp.write(" " + xa + "," + ya);

                xant=xa;
                yant=ya;
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
     * @throws IOException
     */
    private void pintaPath(PoligonoA pola) throws IOException {

        String sid = (pola.sdatos.get(midxml.indfiltro1)).trim();
        String strans = "";

        Double dnivel = Double.parseDouble((pola.sdatos.get(midxml.indfiltro1)).trim());

        Colorc color = this.obtenColorRango(midxml.obtenRango(dnivel.intValue(), 0));

        fbwp.write("<path ");
        fbwp.write("fill=\"rgb(" + color + ")\"  width=\"0.25\" fill-rule=\"nonzero\" " + strans);

        fbwp.write(" id=\"i" + sid + "\"");

        fbwp.write(" d=\"");

        int i = 0;

        int xa = pola.xpoints[0];
        int ya = pola.ypoints[0];
        
        
        int xant=xa;
        int yant=ya;

        fbwp.write("M " + xa + "," + ya);

        i++;

        while (true) {

            xa = pola.xpoints[i];
            ya = pola.ypoints[i];

            if(xa!=xant || ya!=yant){
                fbwp.write(" L" + xa + "," + ya);
                xant=xa;
                yant=ya;
            }
            
            

            i++;
            
            if (i == pola.npoints) {
                break;
            }

        }

        fbwp.write(" z \"/>\n");

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
