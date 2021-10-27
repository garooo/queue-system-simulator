import frame.FrameSimulatore;
import sistemiACoda.MM1;

public class App {
    public static void main(String[] args) throws Exception {
        FrameSimulatore f = new FrameSimulatore();

        f.setHP();

        int lambda = 8;
        int mu = 9;
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
        System.out.println("Tempo medio in coda= " +s1.WQ());
    }
}
