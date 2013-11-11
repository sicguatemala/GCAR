/*
 * LectorDXML.java
 *
 * Created on 2 de febrero de 2005, 07:57 PM
 */
package aafr.int2svg.lectorins;

import aafr.int2svg.datos.DataXML;
import aafr.int2svg.datos.RRCdato;
import aafr.int2svg.jncartem.Const;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.StringTokenizer;
import org.kxml.Attribute;
import org.kxml.Xml;
import org.kxml.parser.ParseEvent;
import org.kxml.parser.XmlParser;

/**
 * Clase que lee un XML con todas las instrucciones de pintado necesarias
 *
 * @author AAFR
 */
public class LectorDXML {

    File faxml;
    DataXML dxml;

    /**
     * Crea una nueva instancia LectorDXML
     *
     * @param snomarch
     * @param dxml
     */
    public LectorDXML(String snomarch, DataXML dxml) {

        faxml = new File(snomarch);

        this.dxml = dxml;

        try {

            Reader doc = new InputStreamReader(new FileInputStream(faxml));
            XmlParser parser = new XmlParser(doc);

            boolean sigueParsing = true;

            while (sigueParsing) {

                ParseEvent event = parser.read();

                switch (event.getType()) {

                    case Xml.START_TAG:
                        procesa_ElemInicio(event);
                        break;

                    case Xml.END_TAG:
                        procesa_ElemFin(event);
                        break;

                    case Xml.TEXT:
                        procesa_ElemTexto(event);
                        break;

                    case Xml.WHITESPACE:
                        break;

                    case Xml.COMMENT:
                        break;

                    case Xml.PROCESSING_INSTRUCTION:
                        break;

                    case Xml.DOCTYPE:
                        break;

                    case Xml.END_DOCUMENT:
                        sigueParsing = false;
                        break;
                }
            }

        } catch (FileNotFoundException fnfex) {
        } catch (IOException ioex) {
        }

        //completa informacion en caso de radio circulos proporcionados
        int tam = dxml.alrrc.size();
        if (tam > 0) {
            for (int i = 0; i < tam; i++) {
                RRCdato rrcd = dxml.alrrc.get(i);
                rrcd.scolor = "rgb(" + dxml.rango2color(rrcd.rango).toString() + ")";
            }
        }

    }

    /**
     *
     * @param pe
     */
    private void procesa_ElemInicio(ParseEvent pe) {

//recupera el nombre de la etiqueta de inicio
        String qName = pe.getName();

//recupera los atributos
        Attribute[] attrs = null;

        int numatri = pe.getAttributeCount();

        if (numatri > 0) {

            attrs = new Attribute[numatri];

            for (int i = 0; i < numatri; i++) {
                attrs[i] = pe.getAttribute(i);
            }

        }

        if (qName.equals("dir_fuente")) {

            dxml.sdirMGM = checaDirsH(attrs[0].getValue());

        } else if (qName.equals("dir_fuente2")) {

            dxml.sdirFuente2 = checaDirsH(attrs[0].getValue());

        } else if (qName.equals("dir_fuente2")) {

            dxml.sdirMGME = checaDirsH(attrs[0].getValue());

        } else if (qName.equals("dir_nommun")) {

            dxml.sdirNomMun = checaDirsH(attrs[0].getValue());

        } else if (qName.equals("arch_rotulos")) {

            dxml.arch_rotulos = checaDirsH(attrs[0].getValue());

        } else if (qName.equals("arch_script")) {

            dxml.spath_script = checaDirsH(attrs[0].getValue());

            if (dxml.spath_script.length() > 0) {
                dxml.bscript = true;
            }

        } else if (qName.equals("besalfan")) {

            if (attrs[0].getValue().equals("t")) {
                dxml.besalfan = true;
            }

        } else if (qName.equals("dir_loc")) {

            dxml.sdirLoc = checaDirsH(attrs[0].getValue());

        } else if (qName.equals("color_fondo")) {

            dxml.scfondo = "rgb(" + attrs[0].getValue() + ")";

            if (attrs[0].getValue().length() > 0) {
                Color col = parseaSColor(attrs[0].getValue());
                dxml.ponColor(col.getRed(), col.getGreen(), col.getBlue(), "fondop", -1);
            }

        } else if (qName.equals("color_nd")) {

            dxml.scnd = "rgb(" + attrs[0].getValue() + ")";

            if (attrs[0].getValue().length() > 0) {
                Color col = parseaSColor(attrs[0].getValue());
                dxml.ponColor(col.getRed(), col.getGreen(), col.getBlue(), "nd", -1000);
            }

        }else if (qName.equals("tipo_corrd_loc")) {

            dxml.sLocCoord = attrs[0].getValue();

        } else if (qName.equals("anchol")) {
            double aux = Double.parseDouble(attrs[0].getValue());
            dxml.ancholin = (aux > 0) ? aux : 0.25;

        } else if (qName.equals("color")) {

            int rango = Integer.parseInt(attrs[0].getValue());

            Color col = parseaSColor(attrs[1].getValue());

            dxml.ponColor(col.getRed(), col.getGreen(), col.getBlue(), "rango" + rango, rango);

        } else if (qName.equals("radio")) {

            int rango = Integer.parseInt(attrs[0].getValue());
            double radio = Double.parseDouble(attrs[1].getValue());

            RRCdato rrc = new RRCdato(rango, radio, "");

            dxml.alrrc.add(rrc);

        } else if (qName.equals("color_contorno")) {
            Color col = null;
            if (attrs[0].getValue().length() > 0) {
                col = parseaSColor(attrs[0].getValue());
            } else {
                col = Const.COLINDEFINIDO_SISTEMA;
            }

            dxml.ponColor(col.getRed(), col.getGreen(), col.getBlue(), "contorno", -2);

        } else if (qName.equals("color_resaltado")) {

            Color col = parseaSColor(attrs[0].getValue());
            dxml.ponColor(col.getRed(), col.getGreen(), col.getBlue(), "resaltado", -3);

        } else if (qName.equals("dir_pola")) {

            dxml.sdirAI = checaDirsH(attrs[0].getValue());

        } else if (qName.equals("indfiltro") && attrs[0].getValue().length() > 0) {

            dxml.indfiltro = Integer.parseInt(attrs[0].getValue());

        } else if (qName.equals("indfiltro2") && attrs[0].getValue().length() > 0) {

            dxml.indfiltro2 = Integer.parseInt(attrs[0].getValue());

        } else if (qName.equals("alcance_tipo") && attrs[0].getValue().length() > 0) {

            dxml.alcance_tipo = Integer.parseInt(attrs[0].getValue());

        } else if (qName.equals("alcance_ce") && attrs[0].getValue().length() > 0) {

            dxml.alcance_ce = Integer.parseInt(attrs[0].getValue());

        } else if (qName.equals("alcance_cce") && attrs[0].getValue().length() > 0) {

            dxml.alcance_cce = Integer.parseInt(attrs[0].getValue());

        } else if (qName.equals("alcance_cxloc")) {

            dxml.bcenloc = (attrs[0].getValue().equals("t")) ? true : false;

        } else if (qName.equals("dimx") && attrs[0].getValue().length() > 0) {

            dxml.dimx = Integer.parseInt(attrs[0].getValue());

        } else if (qName.equals("dimy") && attrs[0].getValue().length() > 0) {

            dxml.dimy = Integer.parseInt(attrs[0].getValue());

        } else if (qName.equals("margen") && attrs[0].getValue().length() > 0) {

            dxml.margen = Float.parseFloat(attrs[0].getValue()) / 100.0f;

        } else if (qName.equals("factoresc") && attrs[0].getValue().length() > 0) {

            dxml.factoresc = 0 + Double.parseDouble(attrs[0].getValue());
            if (dxml.factoresc == 0) {
                dxml.factoresc = 1.00d;
            }

        } else if (qName.equals("trans") && attrs[0].getValue().length() > 0) {

            dxml.dtrans = parseArrayD(attrs[0].getValue());

        } else if (qName.equals("formato_tipo")) {

            dxml.sformato = attrs[0].getValue();

            if (dxml.sformato.equalsIgnoreCase("SVG")) {
                dxml.bp_raster = false;
            }

        } else if (qName.equals("estiloSVG")) { //elementos para SVG

            dxml.sna_estilo_svg = attrs[0].getValue();

        } else if (qName.equals("leyendaSVG")) {

            dxml.sna_leyenda_svg = attrs[0].getValue();

        } else if (qName.equals("scriptSVG")) {

            dxml.sna_script_svg = attrs[0].getValue();

        } else if (qName.equals("arch_origen")) {

            dxml.archivo_origen = checaDirsH(attrs[0].getValue());

        } else if (qName.equals("arch_destino")) {

            dxml.archivo_destino = checaDirsH(attrs[0].getValue());

        } else if (qName.equals("archivo_complemento")) {

            dxml.archivo_complemento = checaDirsH(attrs[0].getValue());

        } else if (qName.equals("bemb")) {

            dxml.bembebido = (attrs[0].getValue().equalsIgnoreCase("t")) ? true : false;

        } else if (qName.equals("brotulos")) {

            dxml.binrotulos = (attrs[0].getValue().equalsIgnoreCase("t")) ? true : false;

        } else if (qName.equals("subtipom")) {

            if (attrs[0].getValue().equalsIgnoreCase("mun")) {
                dxml.subtipom = Const.MUNICIPAL;
            } else if (attrs[0].getValue().equalsIgnoreCase("dis")) {
                dxml.subtipom = Const.DISTRITAL;
            } else if (attrs[0].getValue().equalsIgnoreCase("reg")) {
                dxml.subtipom = Const.REGIONAL;
            }

        }
    }

    /**
     *
     * @param pe
     */
    private void procesa_ElemTexto(ParseEvent pe) {
    }

    /**
     *
     * @param pe
     */
    private void procesa_ElemFin(ParseEvent pe) {
    }

    /**
     *
     * @param scolor
     * @return
     */
    private Color parseaSColor(String scolor) {

        StringTokenizer stok = new StringTokenizer(scolor, ",. ");

        int rgb[] = new int[3];

        int i = 0;

        while (stok.hasMoreTokens()) {
            rgb[i] = Integer.parseInt(stok.nextToken());
            i++;
        }

        return new Color(rgb[0], rgb[1], rgb[2]);
    }

    /**
     *
     * @param sdata
     * @return
     */
    private double[] parseArrayD(String sdata) {
        StringTokenizer stok = new StringTokenizer(sdata, ";");

        double[] daux = new double[stok.countTokens()];

        int i = 0;

        while (stok.hasMoreTokens()) {
            daux[i] = Double.parseDouble(stok.nextToken());
            i++;
        }

        return daux;

    }

    /**
     *
     * @param sdir
     * @return
     */
    private String checaDirsH(String sdir) {

        if (sdir.length() > 0 && sdir.charAt(0) != '/') {
            sdir = Const.DIRBASE + sdir;
        }

        return sdir;
    }
}
