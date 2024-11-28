public class Traener {
    String navn;
    String hold;
    String skema;

    public Traener(String navn, String hold, String skema){
        this.navn = navn;
        this.hold = hold;
        this.skema = skema;

    }
    public String getNavn(){
        return navn;
    }

    public void setNavn(String navn){
        this.navn = navn;
    }

    public String getHold(){
        return hold;
    }

    public void setHold(String hold){
        this.hold = hold;
    }

    public String getSkema() {
        return skema;
    }

    public void setSkema(String skema) {
        this.skema = skema;
    }



}


