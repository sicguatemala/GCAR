/*
 * LectorINT.java
 *
 * Created on 8 de febrero de 2005, 06:06 PM
 */
package aafr.int2svg.lectorint;

import java.io.*;

/**
 * Clase generica que carga archivos de formato INT
 * @author AAFR <alffore@gmail.com>
 */
public class LectorINT {

    private final File farch;
    

    /**
     * Creates a new instance of LectorINT
     * @param snomarch 
     */
    public LectorINT(String snomarch) {        
        farch = new File(snomarch);
    }

    /**
     *
     * Metodo que lee datos de la fuente en formato INT
     *
     */
    public void leedatos() {

        StringBuffer sb = new StringBuffer();
        int linea = 1;

        try {

            FileReader fr = new FileReader(farch);

            while (true) {

                int c = fr.read();

                if (c == -1) {
                    break;
                }


                if (c == '\n') {
                    parsea(sb.toString(), linea);
                    linea++;
                    sb = new StringBuffer();

                } else {
                    sb.append((char) c);
                }

            }

        } catch (FileNotFoundException fnfex) {

            System.out.println(fnfex);
           

        } catch (IOException ioex) {

            System.out.println(ioex);
           

        }

    }

    /**
     *
     * Metodo basico para parseo de cadenas
     * @param spar
     * @param linea
     * @return
     */
    public int parsea(String spar, int linea) {

        System.out.println("LINEA :" + linea + ":: " + spar);

        return 0;

    }
}

