/*
 * Deg2CCL.java
 *
 * Created on 8 de febrero de 2005, 04:26 PM
 */
package aafr.int2svg.ccoord;

/**
 * Clase que convierte coordenadas Angulares DEG o RAD  a CCL
 * @author alfonso
 */
public class Ang2CCL {

    double a; //eje semi-mayor del elipsoide
    double f; //factor de aplanado del elipsoide 1/f=a/(a-b)
    double e; //excentricidad del elipsoide
    double n; //
    double m1;
    double m2;
    double t;
    double t1;
    double t2;
    double tf;
    double F;
    double r;
    double rf;
    double theta;
    double phy1; //primer paralelo estandar
    double phy2; //segundo paralelo estandar
    double phyf; //latitud falsa de origen
    double lambdaf; //longitud falsa de origen
    double EF;  //falso este
    double NF;  //falso norte
    //Parametros para calcular la transformada de PCCL a GMS
    double thetaprima;
    double tprima;
    double rprima;
    double M_PI = Math.PI;
    // Coordenadas Geograficas
    private double phy;       //latitud
    private double lambda;    //longitud
    // Coordenadas en la proyeccion de Lambert
    private double E;
    private double N;

    /** Crea una nueva instancia de Deg2CCL */
    public Ang2CCL() {

        phy1 = 0.30543262d; //17Ã¯Â¿Â½30' en rad
        phy2 = 0.51487213d; //29Ã¯Â¿Â½30' en rad

        phyf = 0.20943951d; //12Ã¯Â¿Â½ en rad
        lambdaf = -1.7802358d; //102Ã¯Â¿Â½ en rad

        EF = 2.5E6;
        NF = 0;

        a = 6378206.4;
        f = 1.0 / 294.97870;
        e = 0.08227185;

        //calculo de m1
        m1 = Math.sin(phy1);
        m1 *= e;
        m1 = Math.pow(m1, 2);
        m1 = 1 - m1;
        m1 = Math.sqrt(m1);
        m1 = 1 / m1;
        m1 *= Math.cos(phy1);

        //calculo de m2
        m2 = 1 - Math.pow(e * Math.sin(phy2), 2);
        m2 = 1 / m2;
        m2 = Math.sqrt(m2);
        m2 *= Math.cos(phy2);

        //calculo de t1
        t1 = Math.pow((1 - e * Math.sin(phy1)) / (1 + e * Math.sin(phy1)), e / 2);
        t1 = 1.0 / t1;
        t1 *= Math.tan((M_PI / 4) - (phy1 / 2));

        //calculo de t2
        t2 = Math.pow((1 - e * Math.sin(phy2)) / (1 + e * Math.sin(phy2)), e / 2);
        t2 = 1.0 / t2;
        t2 *= Math.tan((M_PI / 4) - (phy2 / 2));

        //calculo de tf
        tf = Math.pow((1 - e * Math.sin(phyf)) / (1 + e * Math.sin(phyf)), e / 2);
        tf = 1.0 / tf;
        tf *= Math.tan((M_PI / 4) - (phyf / 2));

        //calculo de n
        n = (Math.log(m1) - Math.log(m2)) / (Math.log(t1) - Math.log(t2));

        //calculo de F
        F = m1 / (n * Math.pow(t1, n));

        //calculo de rf
        rf = a * F * Math.pow(tf, n);

    }

    /**
     * Metodo para obtener coordenadas Lambert desde GMS
     * @param longitud 
     * @param latitud 
     */
    public void Grados2PCCL(double longitud, double latitud) {


        Rad2PCCL(longitud * (M_PI / 180.00), latitud * (M_PI / 180.00));

    }

    /**
     * Metodo que calcula Radianes a coordenadas de Lambert
     * @param longitud 
     * @param latitud 
     */
    public void Rad2PCCL(double longitud, double latitud) {


        lambda = longitud;
        phy = latitud;

        //calculo de t
        t = Math.pow((1 - e * Math.sin(phy)) / (1 + e * Math.sin(phy)), e / 2);
        t = 1.0 / t;
        t *= Math.tan((M_PI / 4) - (phy / 2));

        //calculo de r
        r = a * F * Math.pow(t, n);

        //calculo de theta
        theta = n * (lambda - lambdaf);

        //Calculo de las coordenadas de Lambert
        E = EF + r * Math.sin(theta);
        N = NF + rf - r * Math.cos(theta);

    }

    /**
     * Metodo que regresa la medida este
     * @return 
     */
    public double obten_E() {
        return E;
    }

    /**
     * Metodo que regresa la medida norte
     * @return 
     */
    public double obten_N() {
        return N;
    }

    /**
     * metodos para obtener coordenadas GMS desde Lambert
     * @param este 
     * @param norte 
     */
    public void PCCL2Grados(double este, double norte) {

        E = este;
        N = norte;

        thetaprima = Math.atan((E - EF) / (rf - (N - NF)));


        rprima = Math.sqrt(Math.pow(E - EF, 2) + Math.pow(rf - (N - NF), 2));
        if (n < 0) {
            rprima *= -1;
        }

        tprima = Math.pow(rprima / (a * F), 1 / n);

        //calculo de la longitud
        lambda = thetaprima / n;
        lambda += lambdaf;

    }

    /**
     *
     * @return Regresa la longitud de la coordenada trasformada
     */
    public double obten_longitud() {
        return lambda;
    }

    /**
     *
     * @return Regresa la latitud de la coordenada transformada
     */
    public double obten_latitud() {
        return phy;
    }
}
