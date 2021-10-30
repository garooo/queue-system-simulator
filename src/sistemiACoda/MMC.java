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
}
