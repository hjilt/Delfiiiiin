import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class Medlem {

    private String fuldeNavn;
    private String koen;
    private LocalDate foedselsdato;
    private boolean erSenior;
    private boolean kontigentStatus;
    private boolean erAktivtMedlem;

    public Medlem (String fuldeNavn, String koen, LocalDate foedselsdato, boolean erAktivtMedlem) {
        this.fuldeNavn = fuldeNavn;
        this.koen = koen;
        this.foedselsdato = foedselsdato;
        this.erAktivtMedlem = erAktivtMedlem;
    }

    public boolean getErAktivtMedlem() {
        return erAktivtMedlem;
    }

    public void setErAktivtMedlem(boolean erAktivtMedlem){
        this.erAktivtMedlem = erAktivtMedlem;
    }

    public String getFuldeNavn(){
        return fuldeNavn;
    }

    public void setFuldeNavn(String fuldeNavn){
        this.fuldeNavn = fuldeNavn;
    }

    public String getKoen(){
        return koen;
    }

    public void setKoen(String koen){
        this.koen = koen;
    }

    public LocalDate getFoedselsdato(){
        return foedselsdato;
    }

    public int udregnAlder() {
        LocalDate aktuelleDato = LocalDate.now();
        Period period = Period.between(foedselsdato, aktuelleDato);
        return period.getYears();
    }

    public boolean erSenior() {
        return udregnAlder() > 18;
    }



    public double beregnKontingent(){
        if (!erAktivtMedlem) {
            return 500.0;
        }

        int alder = udregnAlder();
        if (alder < 18) {
            return 1000.0;
        } else if (alder >= 18 && alder <=60){
            return 1600.0;
        } else if (alder > 60) {
            return 1600.0 * 0.75;
        }
        return 0.0;
    }
    public static double beregnSamletIndkomst(ArrayList<Medlem> medlemmer){
        double total = 0.0;

        for (Medlem medlem : medlemmer) {
            total += medlem.beregnKontingent();
        }
        return total;
    }
}