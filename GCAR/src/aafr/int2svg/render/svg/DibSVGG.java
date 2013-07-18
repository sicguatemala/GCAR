/*
 * DibSVG.java
 *
 * Creado el 22 de marzo de 2005, 07:30 PM
 */
package aafr.int2svg.render.svg;

import aafr.int2svg.datos.DataXML;
import aafr.int2svg.objcarto.PoligonoA;
import aafr.int2svg.objgeom.Poligonal;
import aafr.int2svg.objgeom.Poligono;
import aafr.int2svg.render.ORIndeG2;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Clase que permite la construccion de SVGs, esta clase puede integrar estilos
 *
 * y escripts en el SVG final, todo lo que lee y escribe esta codificado en
 * iso-8859-1
 *
 * @author AAFR, GENEC, S.A. de C.V.
 *
 */
public class DibSVGG extends ORIndeG2 {

    int indu;
    /**
     * Booelano para indicar depuracion
     */
    private File fp;
    public BufferedWriter fbwp;

    /**
     *
     * Crea una nueva instancia de DibSVG
     *
     * @param midxml Instancia a la Data del XML
     * @see DataXML
     */
    public DibSVGG(DataXML midxml, boolean bca) {
        super(midxml);
        indu = 0;
        if (bca) {
            creaArchivo();
        }
    }

    /**
     *
     * @param midxml
     * @param aPA
     * @param bca
     */
    public DibSVGG(DataXML midxml, ArrayList<PoligonoA> aPA, boolean bca) {
        super(midxml, aPA);
        indu = 0;
        if (bca) {
            creaArchivo();
        }
    }

    /**
     *
     * Metodo que crea el archivo svg donde se va a escribir
     *
     */
    private void creaArchivo() {

        try {

            fp = new File(midxml.archivo_destino);
            fbwp = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fp), "UTF-8"));
           // fbwp = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fp)));

        } catch (Exception ex) {
        }

    }

    /**
     *
     * Metodo que inicia las acciones para el pintado del mapa
     *
     * @return Regresa verdadero si todo salio bien
     *
     */
    public boolean pintaMapa() {
        try {
            pon_EncabezadoSVG();
        } catch (IOException ex) {
            Logger.getLogger(DibSVGG.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;

    }

    /**
     *
     * Metodo que imprime el encabezado SVG
     *
     * @throws java.io.IOException En caso de que no pueda escribir en el SA
     *
     */
    void pon_EncabezadoSVG() throws IOException {

        Point pmin = new Point(0, 0);
        Point pmax = new Point(DIMX, DIMY);

        if (!midxml.bembebido) {
            fbwp.write("<?xml version=\"1.0\"?>\n");

            fbwp.write("<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\"\n\"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">\n");

            fbwp.write("<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" ");
        } else {
            fbwp.write("<svg xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\"  id=\"mapa\" ");
        }
        fbwp.write("width=\"" + DIMX + "\" height=\"" + DIMY + "\" viewBox=\"" + pmin.x + " " + pmin.y + " " + pmax.x + " " + pmax.y + "\">\n");

        if (midxml.bscript) {
            fbwp.write("<script type=\"text/javascript\"><![CDATA[ \n");

            this.incluye_archivo(midxml.spath_script);

            fbwp.write("]]></script>\n");
        }

    }

    /**
     *
     * Metodo que imprime el estilo
     *
     * @throws java.io.IOException En caso de que no pueda escribir en el SA
     *
     */
    void pon_EstiloSVG() throws IOException {


        incluye_archivo(midxml.sna_estilo_svg);

    }

    /**
     *
     * Metodo que pone los scripts de animacion y comportamiento
     *
     * @throws java.io.IOException En caso de que no pueda escribir en el SA
     *
     */
    void pon_ScriptSVG() throws IOException {

        incluye_archivo(midxml.sna_script_svg);

    }

    /**
     *
     * Metodo que imprime las leyendas o globitos
     *
     * @throws java.io.IOException En caso de que no pueda escribir en el SA
     *
     */
    void pon_LeyendaSVG() throws IOException {
        incluye_archivo(midxml.sna_leyenda_svg);
    }

    /**
     * Metodo que pone el fin del SVG y vacia el stream al archivo
     *
     * @throws java.io.IOException En caso de que no pueda escribir en el SA
     */
    void pon_FinSVG() throws IOException {


        if (midxml.bscript) {


            fbwp.write("<rect id=\"TooltipRect\" x=\"0\" y=\"0\" width=\"100\" height=\"15\" rx=\"5\" ry=\"5\" "
                    + "style=\"visibility:hidden;fill:rgb(255,255,0);stroke-width:1; stroke:rgb(0,0,128);opacity:1;pointer-events:none\"></rect><text id =\"Tooltip\" "
                    + "style=\"fill:rgb(0,0,0);visibility:hidden;font-weight:normal; font-family:'Arial';font-size:12;text-anchor:middle;pointer-events:none\" x=\"0\" y=\"0\">!</text>");


        }


        fbwp.write("</svg>");
        fbwp.flush();

    }

    /**
     *
     * @throws java.io.IOException
     */
    void pintaFondo() throws IOException {
        fbwp.write("<rect x=\"-" + DIMX + "\" y=\"-" + DIMY + "\" width=\"" + 3 * DIMX + "\" height=\"" + 3 * DIMY + "\"  fill=\"rgb(" + midxml.desc2color("fondo") + ")\" stroke=\"none\"/>\n");
    }

    /**
     *
     * @param sclase
     * @param pol
     * @throws java.io.IOException En caso de que no pueda escribir en el SA
     */
    void pintaPoligonal(String sclase, Poligonal pol) throws IOException {
        fbwp.write("<polyline class=\"" + sclase + "\"");
        fbwp.write(" points=\"");

        int i = 0;

        int xa = pol.xpoints[0];

        int ya = pol.ypoints[0];

        fbwp.write(xa + "," + ya);

        i++;

        while (true) {

            if (xa != pol.xpoints[i] || ya != pol.ypoints[i]) {

                xa = pol.xpoints[i];
                ya = pol.ypoints[i];
                fbwp.write(" " + xa + "," + ya);

            }

            i++;

            if (i == pol.npoints) {
                break;
            }

        }

        fbwp.write("\"/>\n");

    }

    /**
     * Metodo que pinta un poligono
     *
     * @param sclase Clase de estilo a la que pertenece este poligono
     * @param pol Intancia al Poligono
     * @throws java.io.IOException En caso de que no pueda escribir en el SA
     */
    void pintaPoligono(String sclase, Poligono pol) throws IOException {

        fbwp.write("<polygon class=\"" + sclase + "\"");
        fbwp.write(" points=\"");

        int i = 0;

        int xa = pol.xpoints[0];
        int ya = pol.ypoints[0];

        fbwp.write(xa + "," + ya);

        i++;

        while (true) {

            if (xa != pol.xpoints[i] || ya != pol.ypoints[i]) {

                xa = pol.xpoints[i];
                ya = pol.ypoints[i];
                fbwp.write(" " + xa + "," + ya);

            }

            i++;

            if (i == pol.npoints) {
                break;
            }

        }
        fbwp.write("\"/>\n");
    }

    /**
     * Metodo que pinta circulos en el punto especificado, todo ya en
     * coordenadas de buffer
     *
     * @param sclase Clase de estilo a la que pertenece este circulo
     * @param cx Coordenada <I>x</I> del centro del circulo
     * @param cy Coordenada <I>y</I> del centro del circulo
     * @param radio Radio del circulo
     * @throws java.io.IOException En caso de que no pueda escribir en el SA
     */
    void pintaCirculo(String sclase, int cx, int cy, int radio) throws IOException {
        fbwp.write("<circle class=\"" + sclase + "\" cx=\"" + cx + "\" cy=\"" + cy + "\" r=\"" + radio + "\"/>\n");
    }

    /**
     * Metodo para incluir un archivo en el SVG
     *
     * @param snomarch Nombre del archivo svg o parte a incluir
     */
    void incluye_archivo(String snomarch) {

        try {

            File farch = new File(snomarch);

            BufferedReader bfr = new BufferedReader(new InputStreamReader(new FileInputStream(farch), "UTF-8"));

            while (true) {

                int c = bfr.read();
                if (c == -1) {
                    break;
                }
                fbwp.write(c);

            }

            bfr.close();

        } catch (FileNotFoundException fnfex) {
        } catch (UnsupportedEncodingException ueex) {
        } catch (IOException ioex) {
        }

    }
}
