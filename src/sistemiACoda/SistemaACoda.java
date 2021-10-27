package sistemiACoda;

public abstract class SistemaACoda {
    double lambda, mu;

    public SistemaACoda(double lambda, double mu){
        this.lambda = lambda;
        this.mu = mu;
    }

    public abstract double rho();

    public abstract double P0();

    public abstract double PK(int k);

    public abstract double LS();

    public abstract double WS();

    public abstract double WQ();

    public abstract double LQ();

    public abstract double noCoda();
}
