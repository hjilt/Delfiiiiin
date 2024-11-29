import java.time.LocalDate;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //TEST AF MEDLEM OG OPDATER MEDLEM
        /*
        Medlem medlem1 = new Medlem( "Tarik Naji", "Mand", LocalDate.of(1996, 1, 8), true, true);

        System.out.println("Medlem 1:");
        System.out.println("Fulde navn: " + medlem1.getFuldeNavn());
        System.out.println("Køn: " + medlem1.getKoen());
        System.out.println("Fødselsdato: " + medlem1.getFoedselsdato());
        System.out.println("Alder: " + medlem1.udregnAlder());
        System.out.println("Er senior: " + medlem1.erSenior());
        System.out.println("kontigentstatus: " + (medlem1.getKontigentStatus() ? "Aktivt" : "Passivt"));

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
        System.out.println("kontigentstatus: " + (medlem1.getKontigentStatus() ? "Aktivt" : "Passivt"));
        */

        //TEST AF KONTINGENT OG INDKOMST
        /*
        Medlem medlem1 = new Medlem("Anna", "Kvinde", LocalDate.of(2005, 5, 20), true); // Aktiv ungdom
        Medlem medlem2 = new Medlem("Ole", "Mand", LocalDate.of(1965, 3, 15), true);  // Aktiv senior
        Medlem medlem3 = new Medlem("Jens", "Mand", LocalDate.of(1940, 8, 10), true); // Aktiv over 60
        Medlem medlem4 = new Medlem("Marie", "Kvinde", LocalDate.of(1990, 1, 5), false); // Passiv

        System.out.println("Kontingent for Anna: " + medlem1.beregnKontingent());
        System.out.println("Kontingent for Ole: " + medlem2.beregnKontingent());
        System.out.println("Kontingent for Jens: " + medlem3.beregnKontingent());
        System.out.println("Kontingent for Marie: " + medlem4.beregnKontingent());

        ArrayList<Medlem> medlemmer = new ArrayList<>();

        medlemmer.add(new Medlem("Anna", "Kvinde", LocalDate.of(2005, 5, 20), true)); // Aktiv ungdom
        medlemmer.add(new Medlem("Ole", "Mand", LocalDate.of(1965, 3, 15), true));  // Aktiv senior
        medlemmer.add(new Medlem("Jensn", "Mand", LocalDate.of(1940, 8, 10), true)); // Aktiv over 60
        medlemmer.add(new Medlem("Marie", "Kvinde", LocalDate.of(1990, 1, 5), false)); // Passiv

        double totalIndkomst = Medlem.beregnSamletIndkomst(medlemmer);

        System.out.println("Den samlede årlige indkomst fra kontingenter er: " + totalIndkomst + " kr.");
        */



        // Ved ikke om det er lavet rigtigt eller om det overhovdet skal være sådan, men i kan lige se til engang
        Traener traener1 = new Traener("Ole", "oevet hold", "Mandag og Onsdag");
        System.out.println(traener1);

        // Ændring af hold og skema
        traener1.setHold("Oevet hold");
        traener1.setSkema("Tirsdag og Torsdag");
        System.out.println("\nOpdateret information:");
        System.out.println(traener1);

        Traener traener2 = new Traener("Oskar", "Begynder hold", "Fredag");
        System.out.println("\nNy træner:");
        System.out.println(traener2);

        // Tilføj træner
        Traener.addTraener("Bord", "Oevet hold", "Mandag og Onsdag");
        Traener.addTraener("Mike Wasawski", "Begynder hold", "Fredag");

        // Vis alle trænere
        System.out.println("Alle trænere:");
        for (Traener traener : Traener.getAllTraenere()) {
            System.out.println(traener);
            System.out.println();
        }
    }

}


