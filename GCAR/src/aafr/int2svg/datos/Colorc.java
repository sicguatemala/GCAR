/*
 * Colorc.java
 *
 * Created on 4 de febrero de 2005, 02:27 PM
 */

package aafr.int2svg.datos;

import java.awt.Color;
/**
 * Clase que define un objeto de color que pertenece a cierto rango y tiene una descripción
 * @author AAFR
 */
public class Colorc extends Color{
    /**
     * Descripcion o nombre del color
     */
    public String snomd;
    
    /**
     * Rango al que pertenece este color
     */
    public int rango;
    /**
     * Crea una nueva instancia de Colorc
     * @param rango Rango al que pertenece
     * @param r Componente <I>R</I> del Color
     * @param g Componente <I>G</I> del Color
     * @param b Componente <I>B</I> del Color
     * @param snomd Nombre del color para indicar su uso o comprender su descripcion
     */
    public Colorc(int r,int g,int b,String snomd,int rango) {
        
        super(r,g,b);
        this.snomd=snomd;
        this.rango=rango;
    }
    
    @Override
    public String toString(){
        return new String(getRed()+","+getGreen()+","+getBlue());
    }
}
