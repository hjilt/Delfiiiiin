import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Discipliner[] israfilDiscipliner = {Discipliner.CRAWL, Discipliner.BRYSTSVOEMNING};
        Medlem medlem1 = new Medlem( "Tarik Naji", "Mand", LocalDate.of(1996, 1, 8), true);
        KonkurrenceSvoemmer israfil = new KonkurrenceSvoemmer("Israfil", "dreng?", LocalDate.of(2011,1,8), true, israfilDiscipliner);
        israfil.recordBestTime(Discipliner.CRAWL, 134);
        israfil.recordBestTime(Discipliner.BRYSTSVOEMNING, 20000);
        israfil.printBestTimes();
        israfil.addKonkurrenceResultat("Sønder Nærums børneturnering for børn", "Flot flot første plads");
        israfil.printKonkurrenceResultat();

        //nu tester vi push

        /*System.out.println("Medlem 1:");
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
        //test*/
    }
}