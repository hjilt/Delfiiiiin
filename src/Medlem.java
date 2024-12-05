import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Medlem {

    protected String fuldeNavn;
    private String koen;
    private LocalDate foedselsdato;
    private boolean erSenior;
    private boolean kontigentStatus;
    private boolean erAktivtMedlem;
    private double restance;
    private LocalDate oprettelsesDato;

    public Medlem (String fuldeNavn, String koen, LocalDate foedselsdato, LocalDate oprettelsesDato, boolean erAktivtMedlem) {
        this.fuldeNavn = fuldeNavn;
        this.koen = koen;
        this.foedselsdato = foedselsdato;
        this.erAktivtMedlem = erAktivtMedlem;
        this.oprettelsesDato = oprettelsesDato;
        restance = beregnKontingent();
    }
//TODO Funktion til at registrere indbetalinger.
//TODO Gør så den tager udgangspunkt i registreringsdato ift det der skal betales.
    public void setRestance(double restance) {
        this.restance = restance;
    }

    public LocalDate getOprettelsesDato() {
        return oprettelsesDato;
    }

    public double getRestance(){
        return restance;
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
    double juniorSats = 1000;
    double seniorSats = 1600;

    public static double alderBetaling(LocalDate foedselsdato, LocalDate medlemsDato, int aar, double juniorSats, double seniorSats) {
        //Årets interval
        LocalDate startAfAaret = LocalDate.of(aar, Month.JANUARY, 1);
        LocalDate slutAfAaret = LocalDate.of(aar, Month.DECEMBER, 31);

        int alderStartAfAaret = Period.between(foedselsdato, startAfAaret).getYears();

        int alderVedMedlemskab = Period.between(foedselsdato, medlemsDato).getYears();

        double kontingent = 0.0;

        if (alderVedMedlemskab >= 18) {
            LocalDate fylder18 = foedselsdato.plusYears(18);
            if (fylder18.isAfter(medlemsDato) && fylder18.isBefore(slutAfAaret)) {

                double maanedUnder18 = fylder18.getMonthValue() - medlemsDato.getMonthValue();
                double maanedover18 = 12 - maanedUnder18;

                kontingent = (maanedUnder18 / 12) * juniorSats + (maanedover18 / 12.0) * seniorSats;
            }

            // else if (alderVedMedlemskab >=


        } else {
            //hele året under 18
            kontingent = juniorSats;
        }
    }

    public static double beregnSamletIndkomst(ArrayList<Medlem> medlemmer){
        double total = 0.0;

        for (Medlem medlem : medlemmer) {
            total += medlem.beregnKontingent();
        }
        return total;
    }


    
    public void betalKontigent(double beloeb) {
        if (beloeb <= 0) {
            System.out.println("Beløbet skal være større end 0");
            return;
        }

        if (beloeb >= restance) {
            System.out.println("Betaling modtaget: " + beloeb + " kr. Restance på " + restance + " er nu betalt.");
            restance = 0;
        } else {
            restance -= beloeb;
            System.out.println("Betaling modtaget: " + beloeb + " kr. Ny restance: " + restance + " kr.");
        }
    }

}