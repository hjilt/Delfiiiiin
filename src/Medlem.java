import java.time.LocalDate;
import java.time.Period;

public class Medlem {

    private String fuldeNavn;
    private String koen;
    private LocalDate foedselsdato;
    private boolean erSenior;
    private boolean kontigentStatus;
    private boolean erKonkurrenceSvoemmer;

    public Medlem (String fuldeNavn, String koen, LocalDate foedselsdato, boolean kontigentStatus) {
        this.fuldeNavn = fuldeNavn;
        this.koen = koen;
        this.foedselsdato = foedselsdato;
        this.kontigentStatus = kontigentStatus;
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

    public boolean getKontigentStatus(){
        return kontigentStatus;
    }

    public void setKontigentStatus(boolean kontigentsStatus){
        this.kontigentStatus = kontigentsStatus;
    }

    public int udregnAlder() {
        LocalDate aktuelleDato = LocalDate.now();
        Period period = Period.between(foedselsdato, aktuelleDato);
        return period.getYears();
    }

    public boolean erSenior() {
        return udregnAlder() > 60;
    }
}

