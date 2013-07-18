/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aafr.int2svg.render.svg;

import aafr.int2svg.datos.DataXML;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

/**
 *
 * @author alfonso
 */
public class PintaLeyenda {

    DataXML midxml;

    public PintaLeyenda(DataXML midxml) {

        this.midxml = midxml;

        this.pintaLeyenda();
    }

    void pintaLeyenda() {

        int dimlx = 26;

        int dimly = 26;

        File fp = null;

        BufferedWriter fbwp = null;



        try {

            fp = new File(midxml.archivo_destino + "leyenda_1.svg");

            fbwp = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fp), "ISO-8859-1"));

            fbwp.write("<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>\n");
            fbwp.write("<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.0//EN\"\n\"http://www.w3.org/TR/2001/REC-SVG-20010904/DTD/svg10.dtd\">\n");
            fbwp.write("<svg xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" ");
            fbwp.write("width=\"" + dimlx + "\" height=\"" + dimly + "\" viewBox=\"0 0 " + dimlx + " " + dimly + "\">");



            fbwp.write("<circle opacity=\".5\" fill=\"rgb(255,0,0)\" stroke=\"rgb(100,100,100)\" stroke-width=\".3\" cx=\"" + dimlx / 2 + "\" cy=\"" + dimly / 2 + "\" r=\"2\"/>");
            fbwp.write("</svg>");

            fbwp.flush();





            fp = new File(midxml.archivo_destino + "leyenda_2.svg");

            fbwp = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fp), "ISO-8859-1"));



            fbwp.write("<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>\n");
            fbwp.write("<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.0//EN\"\n\"http://www.w3.org/TR/2001/REC-SVG-20010904/DTD/svg10.dtd\">\n");
            fbwp.write("<svg xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" ");
            fbwp.write("width=\"" + dimlx + "\" height=\"" + dimly + "\" viewBox=\"0 0 " + dimlx + " " + dimly + "\">");


            fbwp.write("<circle opacity=\".5\" fill=\"rgb(255,172,0)\" stroke=\"rgb(100,100,100)\" stroke-width=\".3\" cx=\"" + dimlx / 2 + "\" cy=\"" + dimly / 2 + "\" r=\"3\"/>");



            fbwp.write("</svg>");

            fbwp.flush();



            fp = new File(midxml.archivo_destino + "leyenda_3.svg");

            fbwp = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fp), "ISO-8859-1"));



            fbwp.write("<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>\n");
            fbwp.write("<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.0//EN\"\n\"http://www.w3.org/TR/2001/REC-SVG-20010904/DTD/svg10.dtd\">\n");
            fbwp.write("<svg xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" ");
            fbwp.write("width=\"" + dimlx + "\" height=\"" + dimly + "\" viewBox=\"0 0 " + dimlx + " " + dimly + "\">");



            fbwp.write("<circle opacity=\".5\" fill=\"rgb(248,255,0)\" stroke=\"rgb(100,100,100)\" stroke-width=\".3\" cx=\"" + dimlx / 2 + "\" cy=\"" + dimly / 2 + "\" r=\"6\"/>");



            fbwp.write("</svg>");

            fbwp.flush();



            fp = new File(midxml.archivo_destino + "leyenda_4.svg");

            fbwp = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fp), "ISO-8859-1"));



            fbwp.write("<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>\n");

            fbwp.write("<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.0//EN\"\n\"http://www.w3.org/TR/2001/REC-SVG-20010904/DTD/svg10.dtd\">\n");
            fbwp.write("<svg xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" ");
            fbwp.write("width=\"" + dimlx + "\" height=\"" + dimly + "\" viewBox=\"0 0 " + dimlx + " " + dimly + "\">");



            fbwp.write("<circle opacity=\".5\" fill=\"rgb(95,255,0)\" stroke=\"rgb(100,100,100)\" stroke-width=\".3\" cx=\"" + dimlx / 2 + "\" cy=\"" + dimly / 2 + "\" r=\"12\"/>");

            fbwp.write("</svg>");

            fbwp.flush();



            fp = new File(midxml.archivo_destino + "leyenda_5.svg");

            fbwp = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fp), "ISO-8859-1"));



            fbwp.write("<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>\n");

            fbwp.write("<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.0//EN\"\n\"http://www.w3.org/TR/2001/REC-SVG-20010904/DTD/svg10.dtd\">\n");

            fbwp.write("<svg xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" ");

            fbwp.write("width=\"" + dimlx + "\" height=\"" + dimly + "\" viewBox=\"0 0 " + dimlx + " " + dimly + "\">");



            fbwp.write("<circle opacity=\".5\" fill=\"rgb(62,164,0)\" stroke=\"rgb(100,100,100)\" stroke-width=\".3\" cx=\"" + dimlx / 2 + "\" cy=\"" + dimly / 2 + "\" r=\"18\"/>");



            fbwp.write("</svg>");
            fbwp.flush();



        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
