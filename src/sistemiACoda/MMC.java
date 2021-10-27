package sistemiACoda;

import java.lang.Math;
import utility.Utility;

public class MMC extends SistemaACoda implements SistemaStreamable{
    private int c;

    public MMC(double lambda, double mu, int c){
        super(lambda, mu);
        this.c = c;
    }

    public double rho(){
        return 0;
    }

    public double P0(){
        return 0;
    }

    public double PK(int k){
        return 0;
    }

    public double LS(){
        return 0;
    }

    public double WS(){
        return 0;
    }

    public double WQ(){
        return 0;
    }

    public double LQ(){
        return 0;
    }

    public double noCoda(){
        return 0;
    }

    private double probNascita(int k){

      return (Math.pow(this.lambda, k)/Utility.factorial(k)) * Math.pow(Math.E, -this.lambda);
      //Poisson function which simulates the arrivals of the pkgs
        
    }
    
    private boolean nascita(double probNascita){
        return Math.random() < probNascita;
    }
}
