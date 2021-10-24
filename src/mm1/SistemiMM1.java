package mm1;
import java.lang.Math;


public class SistemiMM1 {
    private double lambda;
    private double mu;
    private double rho;
    private double P0;
    private double PK;
    private double LS;
    private double WS;
    private double WQ;
    private double LQ;
    private double noCoda;

    public SistemiMM1(double lambda, double mu){
        this.lambda = lambda;
        this.mu = mu;
        //Variabili assegnate
    }

    public double rho(){
        this.rho = this.lambda/this.mu;
        return this.rho;
    }

    public double P0(){
        this.P0 = 1 - this.rho;
        return this.P0;
        //prob di stato iniziale
    }

    public double PK(double k){
        this.PK = ((1-this.rho) * Math.pow(this.rho,k));
        return this.PK;
        //prob di stato basato su un fattore k
    }

    public double LS(){
        this.LS = this.rho/(1-this.rho);
        return this.LS;
        //numero medio di pkt nel sistema
    }

    public double WS(){
        this.WS = (1/this.mu)/(1-this.rho);
        return this.WS;
        //tempo medio attesa nel sistema
    }

    public double WQ(){
        this.WQ = 1/this.mu *(this.rho/(1-this.rho));
        return this.WQ;
        //tempo medio in coda;
    }

    public double LQ(){
        this.LQ = (Math.pow(this.rho,2)) / (1 - this.rho);
        return this.LQ;
    }

    public double noCoda(){
        this.noCoda = 1 - Math.pow(this.rho,2);
        return this.noCoda;
    }




}