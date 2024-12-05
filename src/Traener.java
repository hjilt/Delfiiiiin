import java.util.ArrayList;

public class Traener {
    String navn;
    String hold;
    String skema;

    public static ArrayList<Traener> traenere = new ArrayList<>();


    public Traener(String navn, String hold, String skema) {
        this.navn = navn;
        this.hold = hold;
        this.skema = skema;
        traenere.add(this);
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getHold() {
        return hold;
    }

    public void setHold(String hold) {
        this.hold = hold;
    }

    public String getSkema() {
        return skema;
    }

    public void setSkema(String skema) {
        this.skema = skema;
    }


    public String toString() {
        return "Navn: " + navn + "\nHold: " + hold + "\nSkema: " + skema;
    }

    //metode til at få listen over alle trænere
    public static ArrayList<Traener> getTraenere() {
        return traenere;
    }

    public static void printTraenere(){
        for (Traener traener : traenere) {
            System.out.println(traener);
        }
    }

    //metode til at tilføje en ny træner
    public static void addTraener(String navn, String hold, String skema) {
        new Traener(navn, hold, skema); // Tilføjer automatisk til listen
    }

}
