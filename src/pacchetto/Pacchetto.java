package pacchetto;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Pacchetto {
    private int tArr, tEntrata, tServ; // millisecondi

    public Pacchetto(int tArr){
        this.tArr = tArr;
    }

    public void setTEntrata(int tEntrata){
        this.tEntrata = tEntrata;
    }
    public void setTServ(int tServ){
        this.tServ = tServ;
    }

    public int getTArr(){
        return this.tArr;
    }
    public int getTEntrata(){
        return this.tEntrata;
    }
    public int getTServ(){
        return this.tServ;
    }

    public String toString(){
        return "Tempo arrivo: " + this.getTArr() + " Tempo entrata: " + this.getTEntrata() + " Tempo uscita: " + this.getTServ() + "\n";
    }

    public static Map<Float, Integer> getDataValuesChart(List<Pacchetto> pacchetti){
        Map<Float, Integer> dataValues = new HashMap<Float, Integer>();

        if(pacchetti.size() != 0 && pacchetti.get(0).getTArr() != 0)
            dataValues.put((float) 0, 0);

        int nPacchettiAIstanteT;

        for(Pacchetto p : pacchetti){
            nPacchettiAIstanteT = Pacchetto.getNPacchettiAIstanteT(pacchetti, p.tArr);
            dataValues.put(((float) p.tArr)/1000, nPacchettiAIstanteT);

            nPacchettiAIstanteT = Pacchetto.getNPacchettiAIstanteT(pacchetti, p.tServ);
            dataValues.put(((float) p.tServ)/1000, nPacchettiAIstanteT);
        }

        return dataValues;
    }

    private static int getNPacchettiAIstanteT(List<Pacchetto> pacchetti, int t){
        int nPacchetti = 0;

        for(int i = 0; i < pacchetti.size(); i++)
            if(pacchetti.get(i).tArr <= t && pacchetti.get(i).tServ > t)
                nPacchetti++;

        return nPacchetti;
    }
}
