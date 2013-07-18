package aafr.int2svg.jncartem;

import aafr.int2svg.objcarto.PoligonoA;
import java.awt.geom.Rectangle2D;
import java.util.Comparator;

/**
 *
 * @author alfonso
 */
public class COrdenaPolA implements Comparator<PoligonoA> {

    /**
     *
     * @param m1
     * @param m2
     * @return
     */
    @Override
    public int compare(PoligonoA m2, PoligonoA m1) {

        Rectangle2D rec1 = m1.getBounds2D();

        Rectangle2D rec2 = m2.getBounds2D();


        double a2 = rec2.getHeight() * rec2.getWidth();
        double a1 = rec1.getHeight() * rec1.getWidth();

        if (Double.isNaN(a1) || Double.isNaN(a2) || Double.isInfinite(a1) || Double.isInfinite(a2)) {
            return 0;
        }


        if (a1 < a2) {
            return -1;

        }

        if (a1 == a2) {
            return 0;
        }

        return 1;

    }
}
