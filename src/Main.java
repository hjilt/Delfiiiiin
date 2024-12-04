import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Klub klub = new Klub();

        Persistens persistens = new Persistens();
        persistens.loadMedlemmerFromCSV("medlemmer.txt", klub);
        persistens.loadBedsteTider("resultater.txt", klub);

        for(Medlem medlem : klub.getMedlemmer()) {
            if(medlem instanceof KonkurrenceSvoemmer)
                System.out.println(medlem.getFuldeNavn() + ": " + ((KonkurrenceSvoemmer) medlem).getBestTimes());
        }

        /*medlemmer.add(new Medlem("Mikkel Hansen", "Mand", LocalDate.of(1995, 5, 20), true));
        medlemmer.add(new KonkurrenceSvoemmer("Sarah Pedersen", "Kvinde", LocalDate.of(2003, 3, 15), true));
        medlemmer.add(new Medlem("Lone Jensen", "Kvinde", LocalDate.of(1950, 10, 5), true));*/

        Traener Ole = new Traener("Ole Dolriis", "Senior Træner", "Mandag 6:00-8:00, Onsdag kl. 6:00-8:00, Fredag kl. 06:00-8:00");
        Traener Oskar = new Traener("Oskar Tuska", "Junior Træner", "Tirsdag 6:00-8:00, Torsdag kl. 6:00-8:00, Lørdag kl. 06:00-8:00" );
        Traener.traenere.add(Oskar);
        Traener.traenere.add(Ole);

        boolean programKoerer = true;

        while (programKoerer) {
            System.out.println("\nVælg en mulighed:");
            System.out.println("1: Håndter medlemmer");
            System.out.println("2: Håndter kontingent");
            System.out.println("3: Afslut");

            int valg = scanner.nextInt();
            switch (valg) {
                case 1:
                    medlemsMenu(scanner, klub);
                    break;
                case 2:
                    kontingentMenu(scanner, klub);
                    break;
                case 3:
                    programKoerer = false;
                    break;
                default:
                    System.out.println("Ugyldigt valg, prøv igen.");
                    break;
            }
        }

        scanner.close();
    }

    private static void medlemsMenu(Scanner scanner, Klub klub) {
        boolean fortsaetMedlemsMenu = true;
        while (fortsaetMedlemsMenu) {
            System.out.println("\nMedlemshåndtering:");
            System.out.println("1: Vis alle medlemmer");
            System.out.println("2: Medlemmer under 18");
            System.out.println("3: Medlemmer 18 og over, men under 60");
            System.out.println("4: Medlemmer 60 og over");
            System.out.println("5: Konkurrencesvømmere");
            System.out.println("6: Tilbage til hovedmenuen");

            int medlemValg = scanner.nextInt();
            switch (medlemValg) {
                case 1:
                    for (Medlem medlem : klub.getMedlemmer()) {
                        printMedlemmer(medlem);
                        //System.out.println(medlem.getFuldeNavn());
                    }
                    break;
                case 2:
                    for (Medlem medlem : klub.getMedlemmer()) {
                        if (medlem.udregnAlder() < 18) {
                            printMedlemmer(medlem);
                        }
                    }
                    break;
                case 3:
                    for (Medlem medlem : klub.getMedlemmer()) {
                        int alder = medlem.udregnAlder();
                        if (alder >= 18 && alder < 60) {
                            printMedlemmer(medlem);
                        }
                    }
                    break;
                case 4:
                    for (Medlem medlem : klub.getMedlemmer()) {
                        if (medlem.udregnAlder() >= 60) {
                            printMedlemmer(medlem);
                        }
                    }
                    break;
                case 5:
                    for (Medlem medlem : klub.getMedlemmer()) {
                        if (medlem instanceof KonkurrenceSvoemmer) {
                            printMedlemmer(medlem);
                        }
                    }
                    break;
                case 6:
                    fortsaetMedlemsMenu = false;
                    break;
                default:
                    System.out.println("Ugyldigt valg, prøv igen.");
                    break;
            }
            System.out.println();
        }
    }

    private static void kontingentMenu(Scanner scanner, Klub klub) {
        boolean fortsaetKontingentMenu = true;
        while (fortsaetKontingentMenu) {
            System.out.println("\nKontingenthåndtering:");
            System.out.println("1: Vis kontingent for hvert medlem");
            System.out.println("2: Vis samlet kontingentindkomst");
            System.out.println("3: Tilbage til hovedmenuen");

            int kontingentValg = scanner.nextInt();
            switch (kontingentValg) {
                case 1:
                    for (Medlem medlem : klub.getMedlemmer()) {
                        System.out.println(medlem.getFuldeNavn() + " betaler " + medlem.beregnKontingent() + " i kontingent");
                    }
                    break;
                case 2:
                    double totalIndkomst = 0;
                    for (Medlem medlem : klub.getMedlemmer()) {
                        totalIndkomst += medlem.beregnKontingent();
                    }
                    System.out.println("Total indkomst: " + totalIndkomst);
                    break;
                case 3:
                    fortsaetKontingentMenu = false;
                    break;
                default:
                    System.out.println("Ugyldigt valg, prøv igen.");
                    break;
            }
            System.out.println();
        }
    }

    private static void printMedlemmer(Medlem medlem)
    {
        char konkurrenceSvømmer = medlem instanceof KonkurrenceSvoemmer ? 'X' : ' ';
        System.out.println("\nNavn: " + medlem.getFuldeNavn() + "\nAlder: " +  + medlem.udregnAlder() + "\nKonkurrence-svømmer: " + "[" + konkurrenceSvømmer + "]");
        if(medlem instanceof KonkurrenceSvoemmer) {
            System.out.println("Discipliner: ");
            for (Discipliner discipliner : ((KonkurrenceSvoemmer) medlem).getDiscipliner())
                System.out.println(discipliner.toString());
        }
    }
}