package sistemiACoda;

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
}
