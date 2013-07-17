package gob.conaculta.render;

import gob.conaculta.datos.Colorc;
import gob.conaculta.datos.DataXML;
import gob.conaculta.datos.Dato;
import gob.conaculta.jncartem.Const;
import gob.conaculta.objcarto.PoligonoA;
import gob.conaculta.objgeom.Poligono;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author AAFR
 */
public class ORIndeG2 {

    /**
     * Instancia a la Data del XML
     */
    public DataXML midxml;
    /**
     *
     * Punto vertice superior
     *
     */
    public Point pvsup;
    /**
     *
     * Punto vertice inferior
     *
     */
    public Point pvinf;
    /**
     *
     * Dimension <I>x</I> de la imagen
     *
     */
    public int DIMX;
    /**
     * Dimension <I>y</I> de la imagen
     */
    public int DIMY;
    /**
     *
     * Dimension minima
     *
     */
    public int dimpmin;
    /**
     *
     * Dimension maxima
     *
     */
    public int dimpmax;
    /**
     *
     * factor de escala
     *
     */
    public double escala;
    /**
     *
     * Correccion en el eje <I>x</I> para centrado
     *
     */
    public int corx;
    /**
     *
     * Correcci&oacute;n en el eje <I>y</I> para centrado
     *
     */
    public int cory;
    
    
    public int xming, yming;
    public int xmaxg, ymaxg;
    
    
    boolean bpvg;

    /**
     * 
     * @param midxml 
     */
    public ORIndeG2(DataXML midxml) {

        this.inicializador(midxml);
        
        this.addPoligonos(midxml.aPA);

    }

    /**
     * 
     * @param midxml
     * @param aPA 
     */
    public ORIndeG2(DataXML midxml, ArrayList<PoligonoA> aPA) {

        this.inicializador(midxml);

        
        this.addPoligonos(aPA);
    }
    
    /**
     * 
     * @param midxml
     * @param aPA 
     */
    public void addPoligonos(ArrayList<PoligonoA> aPA){
        if (aPA != null) {
            this.obtenLimites(aPA);
        }
    }
    
    /**
     *  Metodo que inicializa el escalador
     * @param midxml 
     */
    private void inicializador(DataXML midxml) {
        this.midxml = midxml;

        //se fija la dimension del mapa
        DIMX = midxml.dimx;
        DIMY = midxml.dimy;

        dimpmin = Math.min(DIMX, DIMY);
        dimpmax = Math.max(DIMX, DIMY);

        xming = 0;
        xmaxg = 0;
        yming = 0;
        ymaxg = 0;

        bpvg = false;

        //se fija la escala
        escala = 1.0d;

    }

    /**
     *  Metodo que permite obtener los limites de todos los poligonos involucrados
     * @param aPA
     * @return 
     */
    public final int obtenLimites(ArrayList<PoligonoA> aPA) {

        int vtam = aPA.size();

        int xmin = 0;
        int ymin = 0;
        int xmax = 0;
        int ymax = 0;

        boolean bpv = false;

        double escala_aux = 0.0f;

        System.out.println("obtenlimites Generales: inicio: " + vtam);


        for (int i = 0; i < vtam; i++) {

            
            Rectangle rec = aPA.get(i).getBounds();

            if (!bpv) {

                xmin = rec.x;
                ymin = rec.y;

                xmax = rec.x + rec.width;
                ymax = rec.y + rec.height;

                bpv = true;
            } else {

                if (xmin > rec.x) {
                    xmin = rec.x;
                }
                if (ymin > rec.y) {
                    ymin = rec.y;
                }
                if (xmax < rec.x + rec.width) {
                    xmax = rec.x + rec.width;
                }
                if (ymax < rec.y + rec.height) {
                    ymax = rec.y + rec.height;
                }
            }
        }


        if (bpvg) {
            xmin = Math.min(xmin, xming);
            ymin = Math.min(ymin, yming);

            xmax = Math.max(xmax, xmaxg);
            ymax = Math.max(ymax, ymaxg);
        }

        pvinf = new Point(xmin, ymin);
        pvsup = new Point(xmax, ymax);


        xming = xmin;
        yming = ymin;
        xmaxg = xmax;
        ymaxg = ymax;

        bpvg = true;


        //se calculan los paramentros de la transformacion
        int dimmx = xmax - xmin;
        int dimmy = ymax - ymin;

        int dimmmax = Math.max(dimmy, dimmx);


        if (Const.BDEP) {
            System.out.println("DEPURACION dimmx: " + dimmx + " dimmy: " + dimmy + " DIMX: " + DIMX + " DIMY: " + DIMY);
        }
        if (dimmmax == dimmx) {

            escala_aux = (double) DIMX / (double) dimmmax;

            if ((int) (escala_aux * dimmy) <= DIMY) {
                escala = escala_aux;
            } else {
                escala = (double) DIMY / (double) dimmy;
            }
        } else {

            escala_aux = (double) DIMY / (double) dimmmax;

            if ((int) (escala_aux * dimmx) <= DIMX) {
                escala = escala_aux;
            } else {
                escala = (double) DIMX / (double) dimmx;
            }
        }


        corx = (int) (DIMX / 2 - escala * ((xmax + xmin) / 2 - xmin));
        cory = (int) (DIMY / 2 + escala * ((ymax + ymin) / 2 - ymin));

        if (Const.BDEP) {
            System.out.println("DEPURACION escala: " + escala + " corx: " + corx + " cory: " + cory);
        }

        System.out.println("obtenlimites Generales: fin");

        return vtam;
    }

    /**
     * 
     * @param aPA
     * @return 
     */
    public int escalaPoligonos(ArrayList<PoligonoA> aPA) {

        System.out.println("escala poligonos arbitrarios: inicio ");

        int vtam = aPA.size();

        for (int i = 0; i < vtam; i++) {
            escalaPoligono(aPA.get(i));
        }


        System.out.println("escala poligonos arbitrarios: fin");

        return vtam;
    }

    /**
     *
     * Metodo que escala un poligonos para ajustarlo al grafico
     *
     * @return Regresa el numero de vertices escalados
     * @see Poligono
     *
     */
    int escalaPoligono(PoligonoA pol) {

        int numpuntos = pol.npoints;

        for (int j = 0; j < numpuntos; j++) {

            int xn = (int) (escala * (pol.xpoints[j] - pvinf.x) + corx);
            int yn = (int) (-escala * (pol.ypoints[j] - pvinf.y) + cory);

            pol.xpoints[j] = xn;
            pol.ypoints[j] = yn;

        }

        pol.invalidate();

        return numpuntos;

    }

    /**
     * Metodo que escala un punto de Coordenadas geograficas a coordenadas del mapa.
     * Misma proyeccion, mismo esquema de coordenadas.
     * 
     * @param daux
     * @return 
     */
    public Dato escalaPunto(Dato daux) {

        daux.este = (escala * (daux.este * midxml.factoresc - pvinf.x) + corx);
        daux.norte = (-escala * (daux.norte * midxml.factoresc - pvinf.y) + cory);

        return daux;
    }

    /**
     *
     * Metodo que obtiene el color de fondo del municipio
     *
     * @param ce clave de estado
     * @param cm clave de municipio
     * @return Regresa un objeto de Colorc
     * @see Colorc
     *
     */
    public Colorc obtenColorFondo(int ce, int cm) {

        Colorc cres = midxml.rango2color(0);

        int rangores = midxml.obtenRango(ce, cm);

        if (rangores >= 0) {
            cres = midxml.rango2color(rangores);
        }

        return cres;

    }

    /**
     * Metodo que obtiene el color al proporcionar un rango
     * @param rango
     * @return
     */
    public Colorc obtenColorRango(int rango) {
        return midxml.rango2color(rango);
    }

    /**
     *
     * Metodo que determina el color del Perimetro
     *
     * @param ce
     * @param cm
     *
     * @return
     */
    public Colorc obtenColorPerimetro(int ce, int cm) {

        Colorc cres = midxml.rango2color(0);

        int rangores = midxml.obtenRango(ce, cm);
        if (rangores >= 0) {
            cres = midxml.rango2color(rangores);
        }

        return cres;

    }

    /**
     * 
     * @return 
     */
    public Colorc obtenColorContorno() {

        return midxml.rango2color(-2);
    }
}
