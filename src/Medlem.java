import java.time.LocalDate;
import java.time.Period;

public class Medlem {

    private String fuldeNavn;
    private String koen;
    private LocalDate foedselsdato;
    private LocalDate oprettelsesDato;
    private boolean erAktivtMedlem;
    private double restance; // Nyt felt til at gemme restance

    public Medlem(String fuldeNavn, String koen, LocalDate foedselsdato, LocalDate oprettelsesDato, boolean erAktivtMedlem) {
        this.fuldeNavn = fuldeNavn;
        this.koen = koen;
        this.foedselsdato = foedselsdato;
        this.oprettelsesDato = oprettelsesDato;
        this.erAktivtMedlem = erAktivtMedlem;
        this.restance = beregnKontingent(); // Initial restance er det fulde kontingent
    }

    public void setPassivtMedlem(){
        if (!erAktivtMedlem){
            System.out.println(fuldeNavn + " er allerede et passivt medlem.");
        }
        else {
            this.erAktivtMedlem = false;
            this.restance = 500.0;
            System.out.println(fuldeNavn + " er nu et passivt medlem.");
        }
    }

    public int udregnAlder() {
        return Period.between(foedselsdato, LocalDate.now()).getYears();
    }

    public boolean erSenior() {
        return udregnAlder() >= 18;
    }

    public double alderBetaling() {
        double juniorSats = 1000.0;
        double seniorSats = 1600.0;
        int alder = udregnAlder();
        int månederTilbage = 12 - oprettelsesDato.getMonthValue() + 1;

        double grundSats = (alder < 18) ? juniorSats : seniorSats;
        return (månederTilbage / 12.0) * grundSats;
    }

    public double beregnKontingent() {
        return alderBetaling();
    }

    public double getRestance() {
        return this.restance;
    }

    public void setRestance(double restance) {
        this.restance = restance;
    }

    public String getFuldeNavn() {
        return fuldeNavn;
    }

    public void setFuldeNavn(String fuldeNavn) {
        this.fuldeNavn = fuldeNavn;
    }

    public String getKoen() {
        return koen;
    }

    public void setKoen(String koen) {
        this.koen = koen;
    }

    public LocalDate getFoedselsdato() {
        return foedselsdato;
    }

    public void setFoedselsdato(LocalDate foedselsdato) {
        this.foedselsdato = foedselsdato;
    }

    public LocalDate getOprettelsesDato() {
        return oprettelsesDato;
    }

    public void setOprettelsesDato(LocalDate oprettelsesDato) {
        this.oprettelsesDato = oprettelsesDato;
    }

    public boolean getErAktivtMedlem() {
        return erAktivtMedlem;
    }

    public void setErAktivtMedlem(boolean erAktivtMedlem) {
        this.erAktivtMedlem = erAktivtMedlem;
    }
}