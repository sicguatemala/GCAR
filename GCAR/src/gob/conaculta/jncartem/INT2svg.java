/**
 * INT2svg.java
 *
 * Created on 2 de febrero de 2005, 02:08 PM
 */
package gob.conaculta.jncartem;

import gob.conaculta.datos.DataXML;
import gob.conaculta.lectorins.*;
import gob.conaculta.lectorint.*;
import gob.conaculta.objcarto.PoligonoA;
import gob.conaculta.render.svg.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Clase principal que construye un mapa en algun formato
 *
 * @author alfonso
 */
public class INT2svg {

    /**
     * Instancia a la configuracion del XML para este pintado
     */
    public DataXML midxml;

    /**
     * Crea una instancia nueva de INT2raster
     *
     * @param scmd Paramaetros comas o por pipes
     */
    public INT2svg(String[] scmd) {




        //lee documento XML de instrucciones
        midxml = new DataXML();
        LectorDXML lgi = new LectorDXML(scmd[0], midxml);



        if (Const.BDEP) {
            System.out.println(midxml.archivo_origen + " " + midxml.archivo_destino + " " + midxml.alcance_ce);
        }

        //lee datos en tablas
        if (!midxml.besalfan && (midxml.alcance_tipo == Const.CONCENTRACION1
                || midxml.alcance_tipo == Const.CONCENTRACION2)) {

            LectorPC miLecPC = new LectorPC(midxml.archivo_origen, midxml.aDatos);
            miLecPC.leeDatos();

        } else if (!midxml.besalfan && (midxml.alcance_tipo == Const.NIVEL_UNICO
                || midxml.alcance_tipo == Const.CAPAS2 || midxml.alcance_tipo == Const.CAPAPOLI)) {

            LectorData miLecD = new LectorData(midxml.archivo_origen, midxml.aDatos, midxml.alcance_ce, midxml.alcance_tipo);
            miLecD.leeDatos();
        } else if (midxml.alcance_tipo == Const.NIVEL_UNICO && midxml.besalfan) {

            LectorDataG miLecDG = new LectorDataG(midxml.archivo_origen, midxml.aDatos);
            miLecDG.leeDatos();
        }


        //lee rotulos 
        if (midxml.binrotulos) {
            LectorRotulos miLR = new LectorRotulos(midxml.arch_rotulos, midxml.aDatos);
            miLR.leeDatos();
        }

        if (Const.BDEP) {
            System.out.println("DEPURACION: alcance_tipo: " + midxml.alcance_tipo);
        }


        // Lector de Poligonos arbitrarios
        LectorINTA linta = new LectorINTA(midxml);





        OrdenaPoligonosA(midxml.aPA);




        midxml.aesp = true;

        if (midxml.alcance_tipo == Const.CAPAS2 || midxml.alcance_tipo == Const.CAPAPOLI) {
            LectorINTA linta2 = new LectorINTA(midxml, midxml.sdirFuente2, midxml.aPA2);
            OrdenaPoligonosA(midxml.aPA2);

        }


        if (midxml.alcance_tipo == Const.CONCENTRACION1) {

            // pinta mapas de concentracion
            ConcentraRecG cr = new ConcentraRecG(midxml);
            cr.pintaMapa();

        } else if (midxml.alcance_tipo == Const.CONCENTRACION2) {

            // pinta mapas de concentracion
            ConcentraRecG2 cr = new ConcentraRecG2(midxml);
            cr.pintaMapa();

        } else if (midxml.alcance_tipo == Const.CAPAS2) {

            AreasPolAM polam = new AreasPolAM(midxml, true);
            polam.pintaMapa();

        } else if (midxml.alcance_tipo == Const.CAPAPOLI) {

            AreasPolig poli = new AreasPolig(midxml, true);
            poli.pintaMapa();

        } else {

            //renderea y pinta los poligonos        
            AreasPolA pola = new AreasPolA(midxml, true);
            pola.pintaMapa();

        }


    }

    /**
     * Metodo principal que arranca la aplicacion
     *
     * @param sargs Array de strings con los argumentos para el trabajo de
     * pintado
     */
    public static void main(String[] sargs) {

        //el xml con instrucciones
        INT2svg i2s = new INT2svg(sargs);

    }

    /**
     *
     * @param aPA
     */
    private void OrdenaPoligonosA(ArrayList<PoligonoA> aPA) {
        if (Const.BDEP) {
            System.out.println("Ordena poligonos Arbitrarios: " + aPA.size());
        }

        Collections.sort(aPA, new COrdenaPolA());
    }
}
