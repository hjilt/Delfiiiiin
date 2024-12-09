import java.time.LocalDate;
import java.util.*;

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
            System.out.println("1: Vis medlemmer");
            System.out.println("2: Registrer nyt medlem");
            System.out.println("3: Gør et aktivt medlem passivt");
            System.out.println("4: Konkurrencesvømmere");
            System.out.println("5: Tilbage til hovedmenuen");

            int medlemValg = scanner.nextInt();
            switch (medlemValg) {
                case 1:
                    visMedlemmer(klub);
                    break;
                case 2:
                    registrerNytMedlem(scanner, klub);
                    break;
                case 3:
                    gørMedlemPassivt(scanner, klub);
                    break;
                case 4:
                    haandterKonku(klub);
                    break;
                case 5:
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

    private static void visMedlemmer(Klub klub)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1: Vis alle medlemmer");
        System.out.println("2: Vis medlemmer under 18");
        System.out.println("3: Vis medlemmer over 18");
        System.out.println("4: Vis medlemmer over 60");
        int medlemsValg = scanner.nextInt();
        switch (medlemsValg) {
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
            default:
                System.out.println("Invalid input");
        }
    }

    private static void haandterKonku(Klub klub)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1: Vis alle konkurrence-svømmere");
        System.out.println("2: Vis junior konkurrence-svømmere");
        System.out.println("3: Vis senior konkurrence-svømmere");
        System.out.println("4: Registrer ny rekord");
        System.out.println("5: Registrer nyt stævne");
        System.out.println("6: Rapport over top 5 indenfor discipliner");
        System.out.println("7: Stævne-resultater");

        int medlemsValg = scanner.nextInt();
        switch (medlemsValg)
        {
            case 1:
                for (Medlem medlem : klub.getMedlemmer()) {
                    if (medlem instanceof KonkurrenceSvoemmer) {
                        printMedlemmer(medlem);
                    }
                }
                break;
            case 2:
                for (Medlem medlem : klub.getMedlemmer()) {
                    if (medlem instanceof KonkurrenceSvoemmer && !medlem.erSenior()) {
                        printMedlemmer(medlem);
                    }
                }
                break;
            case 3:
                for (Medlem medlem : klub.getMedlemmer()) {
                    if (medlem instanceof KonkurrenceSvoemmer && medlem.erSenior()) {
                        printMedlemmer(medlem);
                    }
                }
            case 4:
                registrerNyRekord(scanner, klub);
                break;
            case 5:
                registrerStaevneResultat(klub, scanner);
                break;
            case 6:
                udskrivTop5(klub);
                break;
            case 7:
                printKonkurrenceResultater(klub);
            default:
                System.out.println("Invalid input");
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

    private static void udskrivTop5(Klub klub)
    {
        System.out.println("1: Junior-hold");
        System.out.println("2: Senior-hold");
        Scanner scanner = new Scanner(System.in);
        int medlemValg = scanner.nextInt();
        KonkurrenceSvoemmer konku = new KonkurrenceSvoemmer();
        switch(medlemValg){
            case 1:
                konku.udskrivTop5Svoemmer(klub.getJuniorHold());
                break;
            case 2:
                konku.udskrivTop5Svoemmer(klub.getSeniorHold());
                break;
            default:
                System.out.println("Der er ikke valgt et af de to mulige hold.");
        }
    }

    private static void registrerNyRekord(Scanner scanner, Klub klub) {
        List<KonkurrenceSvoemmer> konkurrenceSvoemmere = new ArrayList<>();
        for (Medlem medlem : klub.getMedlemmer()) {
            if (medlem instanceof KonkurrenceSvoemmer) {
                konkurrenceSvoemmere.add((KonkurrenceSvoemmer) medlem);
            }
        }

        if (konkurrenceSvoemmere.isEmpty()) {
            System.out.println("Der er ingen registrerede konkurrencesvømmere.");
            return;
        }

        System.out.println("Vælg en konkurrencesvømmer for at registrere ny rekord:");
        for (int i = 0; i < konkurrenceSvoemmere.size(); i++) {
            System.out.println((i + 1) + ": " + konkurrenceSvoemmere.get(i).getFuldeNavn());
        }

        int valgtSvømmerIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        if (valgtSvømmerIndex < 0 || valgtSvømmerIndex >= konkurrenceSvoemmere.size()) {
            System.out.println("Ugyldigt valg.");
            return;
        }

        KonkurrenceSvoemmer valgtSvømmer = konkurrenceSvoemmere.get(valgtSvømmerIndex);

        System.out.println("Vælg disciplin for ny rekord:");
        for (Discipliner disciplin : Discipliner.values()) {
            System.out.println(disciplin.ordinal() + 1 + ": " + disciplin);
        }

        int disciplinValg = scanner.nextInt() - 1;
        scanner.nextLine();

        if (disciplinValg < 0 || disciplinValg >= Discipliner.values().length) {
            System.out.println("Ugyldigt valg af disciplin.");
            return;
        }

        Discipliner valgtDisciplin = Discipliner.values()[disciplinValg];

        System.out.println("Indtast ny tid for disciplinen (sekunder, fx 50.23):");
        double nyTid = scanner.nextDouble();

        valgtSvømmer.recordBestTime(valgtDisciplin, nyTid);
        System.out.println("Ny rekord registreret for " + valgtSvømmer.getFuldeNavn() + " i disciplinen " + valgtDisciplin + ": " + nyTid + " sekunder.");
    }

    private static void registrerStaevneResultat(Klub klub, Scanner scanner) {
        List<KonkurrenceSvoemmer> konkurrenceSvoemmere = new ArrayList<>();
        for (Medlem medlem : klub.getMedlemmer()) {
            if (medlem instanceof KonkurrenceSvoemmer) {
                konkurrenceSvoemmere.add((KonkurrenceSvoemmer) medlem);
            }
        }

        if (konkurrenceSvoemmere.isEmpty()) {
            System.out.println("Der er ingen registrerede konkurrencesvømmere.");
            return;
        }

        System.out.println("Vælg en konkurrencesvømmer for at tilføje stævneresultat:");
        for (int i = 0; i < konkurrenceSvoemmere.size(); i++) {
            System.out.println((i + 1) + ": " + konkurrenceSvoemmere.get(i).getFuldeNavn());
        }

        int valgtSvømmerIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        if (valgtSvømmerIndex < 0 || valgtSvømmerIndex >= konkurrenceSvoemmere.size()) {
            System.out.println("Ugyldigt valg.");
            return;
        }

        KonkurrenceSvoemmer valgtSvømmer = konkurrenceSvoemmere.get(valgtSvømmerIndex);

        System.out.println("Indtast navn på stævne:");
        String staevneNavn = scanner.nextLine();

        System.out.println("Indtast resultat (placering):");
        String resultat = scanner.nextLine();

        valgtSvømmer.addKonkurrenceResultat(staevneNavn, resultat);
        System.out.println("Stævneresultat tilføjet for " + valgtSvømmer.getFuldeNavn() + ": " +
                staevneNavn + ", resultat: " + resultat + ".");
    }

    private static void printKonkurrenceResultater(Klub klub) {
        // Find konkurrencesvømmere i klubben
        List<KonkurrenceSvoemmer> konkurrenceSvoemmere = new ArrayList<>();
        for (Medlem medlem : klub.getMedlemmer()) {
            if (medlem instanceof KonkurrenceSvoemmer) {
                konkurrenceSvoemmere.add((KonkurrenceSvoemmer) medlem);
            }
        }

        // Hvis der ikke er nogen konkurrencesvømmere
        if (konkurrenceSvoemmere.isEmpty()) {
            System.out.println("Der er ingen registrerede konkurrencesvømmere.");
            return;
        }

        // Print resultater for hver konkurrencesvømmer
        System.out.println("Konkurrence-resultater:");
        for (KonkurrenceSvoemmer svoemmer : konkurrenceSvoemmere) {
            System.out.println("Navn: " + svoemmer.getFuldeNavn());
            Map<String, String> resultater = svoemmer.getKonkurrenceResultater();

            if (resultater.isEmpty()) {
                System.out.println("  Ingen registrerede resultater.");
            } else {
                for (Map.Entry<String, String> entry : resultater.entrySet()) {
                    System.out.println("  Stævne: " + entry.getKey() + " - Resultat: " + entry.getValue());
                }
            }
            System.out.println();
        }
    }
}