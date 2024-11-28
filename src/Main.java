import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Medlem medlem1 = new Medlem( "Tarik Naji", "Mand", LocalDate.of(1996, 1, 8), true);

        System.out.println("Medlem 1:");
        System.out.println("Fulde navn: " + medlem1.getFuldeNavn());
        System.out.println("Køn: " + medlem1.getKoen());
        System.out.println("Fødselsdato: " + medlem1.getFoedselsdato());
        System.out.println("Alder: " + medlem1.udregnAlder());
        System.out.println("Er senior: " + medlem1.erSenior());
        System.out.println("kontigentstatus: " + (medlem1.getKontigentStatus() ? "Aktiv" : "Ikke aktiv"));

        System.out.println(("\nOpdatering af medlem 1..."));
        medlem1.setFuldeNavn("Sikorsky S-67 Blackhawk");
        medlem1.setKoen("Non-Binær angrebshelikopter");
        medlem1.setKontigentStatus(false);

        System.out.println("Opdateret Medlem 1:");
        System.out.println("Fulde navn: " + medlem1.getFuldeNavn());
        System.out.println("Køn: " + medlem1.getKoen());
        System.out.println("Fødselsdato: " + medlem1.getFoedselsdato());
        System.out.println("Alder: " + medlem1.udregnAlder());
        System.out.println("Er senior: " + medlem1.erSenior());
        System.out.println("kontigentstatus: " + (medlem1.getKontigentStatus() ? "Aktiv" : "Ikke aktiv"));
    }
}