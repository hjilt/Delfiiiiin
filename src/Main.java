import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TODO Nice up Switch Case
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Klub klub = new Klub();
        List<KonkurrenceSvoemmer> konku = new ArrayList<KonkurrenceSvoemmer>();

        Persistens persistens = new Persistens();
        persistens.loadMedlemmerFromCSV("medlemmer.txt", klub);
        persistens.loadBedsteTider("resultater.txt", klub);
        persistens.saveMedlemmerToCSV("medlemmer.txt", klub);

        for (Medlem medlem : klub.getMedlemmer()) {
            if (medlem instanceof KonkurrenceSvoemmer) {
                konku.add((KonkurrenceSvoemmer) medlem);
            }
        }

        KonkurrenceSvoemmer.udskrivTop5Svoemmer(konku);

        for(Medlem medlem : klub.getMedlemmer()) {
            if(medlem instanceof KonkurrenceSvoemmer)
                System.out.println(medlem.getFuldeNavn() + ": " + ((KonkurrenceSvoemmer) medlem).getBestTimes());
        }
        /*medlemmer.add(new KonkurrenceSvoemmer("Sarah Pedersen", "Kvinde", LocalDate.of(2003, 3, 15), true));
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
            System.out.println("3: Træner og skema");
            System.out.println("4: Afslut");

            int valg = scanner.nextInt();
            scanner.nextLine();
            switch (valg) {
                case 1:
                    medlemsMenu(scanner, klub);
                    break;
                case 2:
                    kontingentMenu(scanner, klub);
                    break;
                case 3:
                    traenerOgSkemaMenu(scanner, klub);
                    break;
                case 4:
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
            System.out.println("6: Registrer nyt medlem");
            System.out.println("7: Gør et aktivt medlem passivt");
            System.out.println("8: Tilbage til hovedmenuen");

            int medlemValg = scanner.nextInt();
            switch (medlemValg) {
                case 1:
                    for (Medlem medlem : klub.getMedlemmer()) {
                        printMedlemmer(medlem);
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
                    registrerNytMedlem(scanner, klub);
                    break;
                case 7:
                    gørMedlemPassivt(scanner, klub);
                    break;
                case 8:
                    fortsaetMedlemsMenu = false;
                    break;
                default:
                    System.out.println("Ugyldigt valg, prøv igen.");
                    break;
            }
            System.out.println();
        }
    }

    private static void gørMedlemPassivt(Scanner scanner, Klub klub) {
        ArrayList<Medlem> aktiveMedlemmer = new ArrayList<>();
        int index = 1;

        // Vis aktive medlemmer
        System.out.println("\nAktive medlemmer:");
        for (Medlem medlem : klub.getMedlemmer()) {
            if (medlem.getErAktivtMedlem()) {
                System.out.println(index + ": " + medlem.getFuldeNavn());
                aktiveMedlemmer.add(medlem);
                index++;
            }
        }

        if (aktiveMedlemmer.isEmpty()) {
            System.out.println("Ingen aktive medlemmer fundet.");
            return;
        }

        System.out.println("\nIndtast nummeret på det medlem, du vil gøre passivt:");
        int valgtIndex = scanner.nextInt();
        scanner.nextLine();

        if (valgtIndex > 0 && valgtIndex <= aktiveMedlemmer.size()) {
            Medlem valgtMedlem = aktiveMedlemmer.get(valgtIndex - 1);
            valgtMedlem.setPassivtMedlem();
        } else {
            System.out.println("Ugyldigt valg. Ingen ændringer foretaget.");
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

    private static void traenerOgSkemaMenu(Scanner scanner, Klub klub) {
        boolean fortsaetTraener = true;
        while (fortsaetTraener) {
            System.out.println("\nTræner og skema:");
            System.out.println("1: Vis alle trænere");
            System.out.println("2. Se skema");
            System.out.println("3. Tilbage til hovedmenuen");

            int traenerValg = scanner.nextInt();
            switch (traenerValg) {
                case 1:
                    Traener.printTraenere();
                    break;

                // case 2:

            }
        }
    }

    private static void printMedlemmer(Medlem medlem)
    {
        char konkurrenceSvømmer = medlem instanceof KonkurrenceSvoemmer ? 'X' : ' ';
        System.out.println("\nNavn: " + medlem.getFuldeNavn() + "\nAlder: " +  + medlem.udregnAlder() + "\nRestance: " + medlem.getRestance() + "\nKonkurrence-svømmer: " + "[" + konkurrenceSvømmer + "]");
        if(medlem instanceof KonkurrenceSvoemmer) {
            System.out.println("Discipliner: ");
            for (Discipliner discipliner : ((KonkurrenceSvoemmer) medlem).getDiscipliner())
                System.out.println(discipliner.toString());
        }
    }

    private static void registrerNytMedlem(Scanner scanner, Klub klub) {
        scanner.nextLine();
        Persistens persistens = new Persistens();
        System.out.println("Indtast fulde navn:");
        String fuldeNavn = scanner.nextLine();

        System.out.println("Indtast køn");
        String koen = scanner.nextLine();

        System.out.println("Indtast fødselsdato (yyyy-mm-dd)1");
        LocalDate foedselsdato = LocalDate.parse(scanner.nextLine());

        System.out.println("Er medlemmet aktivt? (true/false)");
        boolean erAktivtMedlem = Boolean.parseBoolean(scanner.nextLine());

        System.out.println("Er medlemmet en konkurrencesvømmer? (True/False)");
        boolean erKonkurrenceSvoemmer = Boolean.parseBoolean(scanner.nextLine());

        LocalDate oprettelsesDato = LocalDate.now();

        Medlem nytMedlem;
        if (erKonkurrenceSvoemmer) {
            System.out.println("Indtast discipliner" +
                    "\n    (BUTTERFLY,\n" +
                    "    CRAWL,\n" +
                    "    RYGCRAWL,\n" +
                    "    BRYSTSVOEMNING) \n(separeret med semikolon)");
            String disciplinerInput = scanner.nextLine();
            String[] disciplinerStrings = disciplinerInput.split(";");
            Discipliner[] discipliner = new Discipliner[disciplinerStrings.length];
            for (int i = 0; i < discipliner.length; i++) {
                discipliner[i] = Discipliner.valueOf(disciplinerStrings[i].toUpperCase());
            }
            nytMedlem = new KonkurrenceSvoemmer(fuldeNavn, koen, foedselsdato, oprettelsesDato, erAktivtMedlem, discipliner);
        } else {
            nytMedlem = new Medlem(fuldeNavn, koen, foedselsdato, oprettelsesDato, erAktivtMedlem);
        }

        klub.tilfoejMedlem(nytMedlem);
        persistens.saveMedlemmerToCSV("medlemmer.txt", klub);
        System.out.println("Nyt medlem registreret: " + nytMedlem.getFuldeNavn());
    }
}