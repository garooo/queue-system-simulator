import frame.FrameSimulatore;
import sistemiACoda.MM1;
import sistemiACoda.MMC;
import pacchetto.Pacchetto;

import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        FrameSimulatore f = new FrameSimulatore();

        //f.setHP();

        //MM1:
        /* int lambda = 8;
        int mu = 2;
        int k = 10;
        MM1 s1 = new MM1(lambda, mu);
        //Assegnato variabili

        System.out.println("rho = " +s1.rho());
        System.out.println("Probabilita che il sistema sia vuoto = " +s1.P0());
        System.out.println("Probabilita 10 pkt in coda = " +s1.PK(k));
        System.out.println("Probabilita no coda = " +s1.noCoda());
        System.out.println("Numero medio di pkt nel sistema= " +s1.LS());
        System.out.println("Numero medio di pkt in coda= " +s1.LQ());
        System.out.println("Tempo medio in attesa nel sistema= " +s1.WS());
        System.out.println("Tempo medio in coda= " +s1.WQ()); */

        //MMC:
        int lambda = 10;
        int mu = 2;
        int k = 10;
        MMC s2 = new MMC(lambda, mu, 4);

        System.out.println(s2.simulazione(10000).toString());

        System.out.println("** M/M/C Description ** \n");
        System.out.println("rho = " +s2.rho());
        System.out.println("Probabilita che il sistema sia vuoto = " +s2.P0());
        System.out.println("Probabilita che ci siano 10 pkt in coda = " +s2.PK(k));
        System.out.println("Numero medio di pkt nel sistema = " +s2.LS());
        System.out.println("Numero medio di pkt in coda = " +s2.LQ());
        System.out.println("Tempo medio in attesa nel sistema = " +s2.WS());
        System.out.println("Tempo medio in coda = " +s2.WQ());
        System.out.println("Numero medio di utenti serviti = " +s2.LX());
        System.out.println("Probabilita che il pkt entra in coda = " +s2.Coda());
    }
}
