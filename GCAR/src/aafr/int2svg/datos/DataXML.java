/*
 * DataXML.java
 *
 * Created on 4 de febrero de 2005, 12:35 PM
 */
package aafr.int2svg.datos;

import aafr.int2svg.jncartem.Const;
import aafr.int2svg.objcarto.PoligonoA;
import java.util.ArrayList;

/**
 *
 * Clase que mantiene y procesa los datos resultado del proceso del XML, y
 * concentra toda la información necesaria para efectuar el pintado del raster
 *
 * (JPEG o PNG) y/o SVG
 *
 * @author AAFR, GENEC, S.A. de C.V.
 *
 */
public class DataXML {

    /**
     *
     * ArrayList de Colores
     *
     * @see Colorc
     */
    ArrayList<Colorc> alcolor;
    public ArrayList<RRCdato> alrrc;
    /**
     *
     * Descripción del dato de color donde aplica
     *
     */
    public String snomadata;
    /**
     *
     * Directorio de destino del raster o svg o su mapa
     *
     */
    public String sdirdest;
    /**
     * Archivo donde se encuentra la fuente del MGM
     */
    public String sdirMGM;
    public String sdirFuente2;
    /**
     * Archivo donde se encuentra la fuente del MGM de Estados
     */
    public String sdirMGME;
    public String sdirAI;
    /**
     * Dimension horizontal de la imagen
     */
    public int dimx;
    /**
     *
     * Dimension vertical de la imagen
     */
    public int dimy;
    public double factoresc;
    /**
     *
     * Margen de la imagen
     *
     */
    public float margen;
    /**
     *
     * Indicador de alcance especifico (se pinta a nivel de municipios y/o
     * localidades)
     *
     */
    public boolean aesp;
    /**
     *
     * Tipo de alcances
     *
     */
    public int alcance_tipo;
    /**
     * Clave de estado del alcance
     */
    public int alcance_ce;
    /**
     * Clave de estado para centrar
     */
    public int alcance_cce;
    /**
     *
     * Factor de calidad al comprimir el JPEG
     *
     */
    public float calidadJPG;

    public double ancholin;
    /**
     * Especifica el formato deseado
     */
    public String sformato;
    /**
     *
     * Booleano para indicar que los datos ya han sido escalados
     *
     */
    public boolean bescalado;
    public boolean bp_raster;
    /**
     * Booleano para indicar que se van a pintar fronteras estales
     */
    public boolean bp_fest;
    /**
     *
     * booleano para indicar que se va a pintar fronteras municipales
     *
     */
    public boolean bp_fmun;
    /**
     *
     * Booleano para indicar que se va a construir el mapa de tipo cliente para
     * un raster
     *
     */
    public boolean bp_mapahtml;
    /**
     * String de color de fondo por default de poligonos
     */
    public String scfondo;

    /**
     * String de color No Disponible (ND)
     */
    public String scnd;
    /**
     *
     * Nombre del archivo de Municipios (nomenclatura)
     *
     */
    public String sdirNomMun;
    public String sdirLoc;
    public String sLocCoord;
    public String sdirFNME;
    /**
     *
     * booleano para indicar que se va incluir en el mapa la nomenclatura de
     * municipios
     *
     */
    public boolean bp_nom;
    public String archivo_origen;
    public String archivo_destino;
    public String archivo_complemento;
    public ArrayList<Dato> aDatos;

    /**
     * Poligono arbitrario
     */
    public ArrayList<PoligonoA> aPA;
    public ArrayList<PoligonoA> aPA2;
    /**
     * Indice para filtrado
     */
    public int indfiltro1;

    /**
     * Indicador se el filtro es alfanumerico
     */
    public boolean besalfan;

    /**
     * Indice para el filtrado de la segunda capa
     */
    public int indfiltro2;

    /**
     * Factores de tranparencia
     */
    public double[] dtrans;

    //Seccion de SVG
    /**
     *
     * Nombre del archivo para los estilos de SVG
     *
     */
    public String sna_estilo_svg;
    /**
     *
     * Nombre del archivo para la leyenda o Tooltip
     *
     */
    public String sna_leyenda_svg;
    public String sna_script_svg;
    public boolean bsvg_mresaltado;
    /**
     * Booleano para indicar el centrado de la localidad
     */
    public boolean bcenloc;

    //seccion de sripts
    public boolean bscript;

    public String spath_script;

    // booblean para indicar que el SVG va ser embebido en un HTML5
    public boolean bembebido;

    //booleano para indicar que los poligonos van por rotulos
    public boolean binrotulos;

    //booleano para indicar el subtipo de mapa
    public int subtipom;

    /**
     *
     */
    public String arch_rotulos;

    /**
     * Crea una nueva instancia de DataXML
     *
     */
    public DataXML() {

        alcolor = new ArrayList<Colorc>();

        alrrc = new ArrayList<RRCdato>();

        //fija el alcance especifico a false calculo por estados
        aesp = false;

        bcenloc = false;

        dimx = 0;
        dimy = 0;
        margen = 0.00f;
        factoresc = 1.00d;

        //alcance_ce=-1;
        alcance_cce = -1;

        sformato = "jpg";

        bp_raster = true;

        bp_mapahtml = false;

        archivo_destino = "indefinido";

        archivo_complemento = "";

        arch_rotulos = "";

        calidadJPG = 0.85f;

        bp_fest = false;

        bp_fmun = false;

        bp_nom = false;

        bescalado = false;

        //crea los vectores para acumular la informacion
        aDatos = new ArrayList<Dato>();

        aPA = new ArrayList<PoligonoA>();
        aPA2 = new ArrayList<PoligonoA>();

        sna_estilo_svg = new String();

        scfondo = "rgb(240,240,240)";

        this.indfiltro1 = -1;
        this.indfiltro2 = -1;

        this.besalfan = false;

        this.ancholin = 0.25;

        this.bscript = false;
        this.spath_script = new String();

        this.bembebido = true;
    }

    /**
     * Metodo que inserta un color en la paleta
     *
     * @param rango Rango del color
     * @param r Componente <B>R</B> del color
     * @param g Componente <B>G</B> del color
     * @param b Componente <B>B</B> del color
     * @param sdes Descripción del color
     */
    public void ponColor(int r, int g, int b, String sdes, int rango) {

        alcolor.add(new Colorc(r, g, b, sdes, rango));

    }

    /**
     *
     * Método que obtiene el color según el rango proporcionado
     *
     * @param rango
     * @return
     */
    public Colorc rango2color(int rango) {

        int tamc = alcolor.size();

        for (int i = 0; i < tamc; i++) {

            Colorc colr = alcolor.get(i);

            if (colr.rango == rango) {
                return colr;
            }

        }

        return Const.COLINDEFINIDO_SISTEMA;

    }

    /**
     *
     * @param rango
     * @return
     */
    public String rango2colorr(int rango) {
        int tamc = alrrc.size();

        for (int i = 0; i < tamc; i++) {

            RRCdato aux = alrrc.get(i);
            if (aux.rango == rango) {
                return aux.scolor;
            }
        }

        return Const.COLINDEFINIDO_SISTEMA.toString();
    }

    /**
     * Método que regresa el radio de acuerdo al rango  
     * @param rango
     * @return 
     */
    public double rango2radio(int rango) {

        int tam = alrrc.size();

        for (int i = 0; i < tam; i++) {

            RRCdato aux = alrrc.get(i);
            if (aux.rango == rango) {
                return aux.radio;
            }
        }

        return 0;

    }

    /**
     *
     * Metodo que regresa un color si se le pasa como argumento su descripción
     *
     * @param sdesc Descripción del color que se desea
     * @return Regresa un Objeto Colorc
     * @see Colorc
     *
     */
    public Colorc desc2color(String sdesc) {

        int tamc = alcolor.size();

        for (int i = 0; i < tamc; i++) {

            Colorc colr = (Colorc) alcolor.get(i);

            if (colr.snomd.equals(sdesc)) {
                return colr;
            }

        }

        return Const.COLINDEFINIDO_SISTEMA;
    }

    /**
     *
     * Método que obtiene el rango al que pertenece un municipio
     *
     * @param ce clave de estado
     * @param cm clave de municipio
     *
     * @return Regresa un entero con el valor del rango al que pertenece
     *
     */
    public int obtenRango(int ce, int cm) {

        int vtam = aDatos.size();

        for (int i = 0; i < vtam; i++) {

            Dato dat = aDatos.get(i);

//            if (aesp) {
//
//                if (dat.nivel1_id == ce && dat.nivel2_id == cm) {
//                    return dat.rango;
//                }
//
//            } else {
//
//                if (dat.nivel1_id == ce) {
//                    return dat.rango;
//                }
//
//            }
            if (cm > 0) {
                if (dat.nivel1_id == ce && dat.nivel2_id == cm) {
                    return dat.rango;
                }
            } else {
                if (dat.nivel1_id == ce) {
                    return dat.rango;
                }
            }

        }

        return -1;

    }

    /**
     *
     * @param sid
     * @return
     */
    public int obtenRango(String sid) {
        int vtam = aDatos.size();
        for (int i = 0; i < vtam; i++) {

            Dato dat = aDatos.get(i);

            if (dat.sid.equals(sid)) {
                return dat.rango;
            }

        }

        return -1;
    }

    /**
     *
     * @param ce
     * @param cm
     * @return
     */
    public double obtenValor(int ce, int cm) {

        int vtam = aDatos.size();

        for (int i = 0; i < vtam; i++) {

            Dato dat = aDatos.get(i);

            if (aesp) {

                if (dat.nivel1_id == ce && dat.nivel2_id == cm) {
                    return dat.valor;
                }

            } else {

                if (dat.nivel1_id == ce) {
                    return dat.valor;
                }

            }

        }

        return -1;

    }

    /**
     * Metodo para obtener el nombre del municipio adecuado
     *
     *
     * @param ce
     * @param cm
     * @return
     */
    public String obtenNombre(int ce, int cm) {

        int vtam = aDatos.size();

        for (int i = 0; i < vtam; i++) {

            Dato dat = aDatos.get(i);

            if (dat.nivel1_id == ce && dat.nivel2_id == cm) {
                switch (this.subtipom) {
                    case Const.MUNICIPAL:
                        return dat.snm;
                    case Const.DISTRITAL:
                        return dat.snd;
                    case Const.REGIONAL:
                        return dat.snr;
                }
            }

        }

        return null;
    }

}
