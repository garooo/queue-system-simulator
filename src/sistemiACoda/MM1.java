package sistemiACoda;
import java.lang.Math;


public class MM1 extends SistemaACoda {
    public MM1(double lambda, double mu){
        super(lambda, mu);
    }

    public double rho(){
        return this.lambda/this.mu;
    }

    public double P0(){
        return 1 - this.rho();
        //prob di stato iniziale
    }

    public double PK(int k){
        return ((1-this.rho()) * Math.pow(this.rho(),k));
        //prob di stato basato su un fattore k
    }

    public double LS(){
        return this.rho()/(1-this.rho());
        //numero medio di pkt nel sistema
    }

    public double WS(){
        return (1/this.mu)/(1-this.rho());
        //tempo medio attesa nel sistema
    }

    public double WQ(){
        return 1/this.mu *(this.rho()/(1-this.rho()));
        //tempo medio in coda;
    }

    public double LQ(){
        return (Math.pow(this.rho(),2)) / (1 - this.rho());
    }

    public double noCoda(){
        return 1 - Math.pow(this.rho(),2);
    }
}