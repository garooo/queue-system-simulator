package sistemiACoda;
import java.lang.Math;
import java.util.List;
import java.util.ArrayList;

import pacchetto.Pacchetto;

public class MMC extends SistemaACoda{
    private int c;

    public MMC(double lambda, double mu, int c){
        super(lambda, mu);
        this.c = c;
    }

    static int factorial(int n)
    {
        if (n == 0)
            return 1;
 
        return n * factorial(n - 1);
    }

    public double rho(){
        return this.lambda/(this.c * this.mu);
    }

    public double P0(){
        int i = 0;
        double sum = 0;
        double p0= 0;
        for(i=0; i<=this.c - 1; i++){
            sum = ((Math.pow(this.c*this.rho(), i)/factorial(i)) + (Math.pow(this.c*this.rho(),this.c)/factorial(c)) * (1/(1-this.rho())));
            p0 += sum;
        }

        p0 = Math.pow(p0,-1);

        return p0;
    }

    public double PK(int k){
        if(k <= this.c){
            return this.P0()*Math.pow(this.lambda/this.mu,k) * (1/factorial(k));
        } 
        else{
            return this.P0()*Math.pow(this.lambda/this.mu,this.c) * (1/(factorial(this.c) * Math.pow(this.c, k - this.c )));
        }
    }


    public double CErlang(int c, double A){ 
        double numeratore = 0;
        double sum = 0;
        double denominatore = 0;
        double pK = 0;
        int i = 0;

        numeratore = ((Math.pow(A, this.c)/factorial(c)) * (1/(1-(A/this.c))));

        for(i=0;i <=this.c - 1; i++){
            sum = ((Math.pow(A, i) / factorial(i)) + (Math.pow(A,this.c)/factorial(this.c)) * (1/(1-(A/this.c))));
            denominatore += sum;
        }

        return pK = numeratore/ denominatore;
    }

    //Prob di essere che il pkt entra in coda
    public double Coda(){
        return CErlang(this.c, this.c *this.rho());
    }

    public double LS(){
        return ((this.c*CErlang(this.c,this.c*this.rho())/(1-this.rho()))+ this.c)*this.rho();
    }

    //Numero medio di utenti serviti
    public double LX(){
        return this.c * this.rho();
    }

    public double WS(){
        return ((this.c*CErlang(this.c,this.c*this.rho()) + this.c*(1-this.rho()))/this.c*this.mu*(1-this.rho()));
    }

    public double WQ(){
        return ((this.c*CErlang(this.c, this.c*this.rho()))/(this.c*this.mu*(1-this.rho())));
    }

    public double LQ(){
        return this.c*CErlang(this.c,this.c*this.rho())*(this.rho()/(1-this.rho()));
    }

    /*public double noCoda(){
        return this.P0() + this.PK(1);
    }/*
    //--> Need to discuss with Garo


    /* private double probNascita(int k){

      return (Math.pow(this.lambda, k)/Utility.factorial(k)) * Math.pow(Math.E, -this.lambda);
      //Poisson function which simulates the arrivals of the pkgs
        
    } */
    
    /* private boolean nascita(double probNascita){
        return Math.random() < probNascita;
    } */

    // ritorna il t interarrivo del pacchetto in millisecondi
    private int getTInterarrivo(){
        return (int) (-Math.log(Math.random())/(this.lambda/1000));
    }

    // ritorna il t servizio del pacchetto in millisecondi
    private int getTServizio(){
        return (int) (-Math.log(Math.random())/(this.mu/1000));
    }

    // durata in millisecondi
    public List<Pacchetto> simulazione(int durata){
        int istanteCorrente = 0, tInterarrivo;
        List<Pacchetto> lPacchetti = new ArrayList<Pacchetto>();

        while(istanteCorrente < durata){
            tInterarrivo = this.getTInterarrivo() + istanteCorrente;

            if(tInterarrivo < durata)
                lPacchetti.add(new Pacchetto(tInterarrivo));

            istanteCorrente = tInterarrivo;
        }

        int nPacchetti = lPacchetti.size();
        int[] server = new int[this.c];

        for(int i = 0; i < server.length; i++){
            // true significa servitore libero
            server[i] = 0;
        }

        int iServitoreLiberoPrima;
        Pacchetto pacchettoCorrente;

        for(int i = 0; i < nPacchetti; i++){
            iServitoreLiberoPrima = MMC.cercaServitoreLiberoPrima(server);
            pacchettoCorrente = lPacchetti.get(i);

            if(pacchettoCorrente.getTArr() < server[iServitoreLiberoPrima])
                pacchettoCorrente.setTEntrata(server[iServitoreLiberoPrima]);
            else
                pacchettoCorrente.setTEntrata(pacchettoCorrente.getTArr());

            pacchettoCorrente.setTServ(pacchettoCorrente.getTEntrata() + this.getTServizio());

            server[iServitoreLiberoPrima] = pacchettoCorrente.getTServ();
        }

        System.out.println(lPacchetti);

        return lPacchetti;
    }

    // cerca un servitore libero e ne ritorna l'indice, se non lo trova ritorna -1
    private static int cercaServitoreLiberoPrima(int[] server){
        int iServitoreLiberoPrima = 0;

        for(int i = 0; i < server.length; i++)
            if(server[i] < server[iServitoreLiberoPrima])
                iServitoreLiberoPrima = i;

        return iServitoreLiberoPrima;
    }

    @Override
    public double noCoda() {
        // TODO Auto-generated method stub
        return 0;
    }
}
