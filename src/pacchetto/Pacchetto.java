package pacchetto;

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
}
