/*
 * UTM2CCL.java
 *
 * Created on 23 de febrero de 2005, 07:15 PM
 */

package gob.conaculta.ccoord;

/**
 * Clase que transforma coordenanadas UTM a Radianes 
 *
 * @author alfonso
 */
public class UTM2CCL {
    
    double A;
    
    double D;
    
    double C1;
    
    
    double M0;
    double M1;
    
    double T1;
    
    double mu1;
    double nu1;
    double rho1;
    double e1;
    
    double a;
    double e;   //excentricidad
    double e2;  //excentricidad cuadrado
    double eprima2;
    
    double k0;  //factor de escala en el punto de origen
    
    
    double phy1;
    double phy0;    //altitud del punto de referencia UTM (utiliza ecuador)
    double lambda0; //longitud de la zona
    
    
    double FE;    //falso este
    double FN;    //falso norte
    
    // Coordenadas
    public double phy;    //latitud rad 
    public double lambda; //longitud rad 
    
    double N;     //Norte UTM
    double E;     //Este UTM
    
    double M_PI=Math.PI;
    
    
    int ZonaUTM;  //Zona utm en la que se va a realizar los calculos
    

    
    /** 
     * Crea una nueva instancia de UTM2CCL 
     */
    public UTM2CCL() {
        
        
	FE=5.0E+5;
	FN=0;
	
	k0=1;
	
	e=0.08227185;
	e2=Math.pow(e,2);
	eprima2=e2/(1-e2);

	a=6378206.4;

	e1=(1-Math.sqrt(1-e2))/(1+Math.sqrt(1-e2));

	phy0=0;

	if(phy0>0){
		M0=(1-e2/4-3*Math.pow(e2,2)/64-5*Math.pow(e2,3)/256)*phy0;
		M0-=(3*e2/8+3*Math.pow(e2,2)/32+45*Math.pow(e2,3)/1024)*Math.sin(2*phy0);
		M0+=(15*Math.pow(e2,2)/256+45*Math.pow(e2,3)/1024)*Math.sin(4*phy0);
		M0-=(35*Math.pow(e2,3)/3072)*Math.sin(6*phy0);
		M0*=a;
	}else M0=0;	

    }
    
    
    /**
     * Transforma de UTM a CG en Rads
     * @param este 
     * @param norte 
     * @param longitud0 
     * @return regresa true si todo sale bien
     */
    public boolean UTM2CG(double este,double norte,double longitud0){
        
        
        N=norte;
        E=este;
        lambda0=longitud0;
        
        M1=M0+(N-FN)/k0;
        
        mu1=M1/(a*(1-e2/4-3*Math.pow(e2,2)/64-5*Math.pow(e2,3)/256));
        
        phy1=mu1;
        phy1+=(3*e1/2-27*Math.pow(e1,3)/32)*Math.sin(2*mu1);
        phy1+=(21*Math.pow(e1,2)/16-55*Math.pow(e1,4)/32)*Math.sin(4*mu1);
        phy1+=(151*Math.pow(e1,3)/96)*Math.sin(6*mu1);
        phy1+=(1097*Math.pow(e1,4)/512)*Math.sin(8*mu1);
        
        
        double aux=1-e2*Math.pow(Math.sin(phy1),2);
        
        nu1=a/Math.sqrt(aux);
        rho1=a*(1-e2)/Math.pow(aux,3/2);
        
        T1=Math.pow(Math.tan(phy1),2);
        
        C1=eprima2*Math.pow(Math.cos(phy1),2);
        
        D=(E-FE)/(nu1*k0);
        
        //calculo de la latitud
        phy=Math.pow(D,2)/2;
        phy-=(5+3*T1+10*C1-4*Math.pow(C1,2)-9*eprima2)*Math.pow(D,4)/24;
        phy+=(61+90*T1+298*C1+45*Math.pow(T1,2)-252*eprima2-3*Math.pow(C1,2))*Math.pow(D,6)/720;
        phy*=(-1.0*nu1*Math.tan(phy1)/rho1);
        phy+=phy1;
        
        //calculo de longitud
        lambda=D;
        lambda-=(1+2*T1+C1)*Math.pow(D,3)/6;
        lambda+=(5-2*C1+28*T1-3*Math.pow(C1,2)+8*eprima2+24*Math.pow(T1,2))*Math.pow(D,5)/120;
        lambda/=Math.cos(phy1);
        lambda+=lambda0;
        
        return true;
    }
 
    
    /**
     * Sobrecarga del metodo UTM2CG
     * @param este 
     * @param norte 
     * @param zona 
     * @return 
     */
    public boolean UTM2CG(double este,double norte,int zona){
        
        zona2long0(zona);
        return UTM2CG(este,norte,lambda0);
        
    }
    
    
    //metodo que convierte la zona a rad en la long. de referencia
    /**
     * 
     * @param zona 
     */
    private void zona2long0(int zona){
        
        ZonaUTM=zona;
        lambda0=-6.0*ZonaUTM+183.0;
        lambda0*=-1.0*M_PI/180.00;
    }
    

    
}
