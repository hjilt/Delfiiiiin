import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Medlem> medlemmer = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        medlemmer.add(new Medlem("Mikkel Hansen", "Mand", LocalDate.of(1995, 5, 20), true));
        medlemmer.add(new KonkurrenceSvoemmer("Sarah Pedersen", "Kvinde", LocalDate.of(2003, 3, 15), true));
        medlemmer.add(new Medlem("Lone Jensen", "Kvinde", LocalDate.of(1950, 10, 5), true));

        boolean programKoerer = true;

        while (programKoerer) {
            System.out.println("\nVælg en mulighed:");
            System.out.println("1: Håndter medlemmer");
            System.out.println("2: Håndter kontingent");
            System.out.println("3: Afslut");

            int valg = scanner.nextInt();
            switch (valg) {
                case 1:
                    medlemsMenu(scanner, medlemmer);
                    break;
                case 2:
                    kontingentMenu(scanner, medlemmer);
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

    private static void medlemsMenu(Scanner scanner, ArrayList<Medlem> medlemmer) {
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
                    for (Medlem medlem : medlemmer) {
                        System.out.println(medlem.getFuldeNavn());
                    }
                    break;
                case 2:
                    for (Medlem medlem : medlemmer) {
                        if (medlem.udregnAlder() < 18) {
                            System.out.println(medlem.getFuldeNavn());
                        }
                    }
                    break;
                case 3:
                    for (Medlem medlem : medlemmer) {
                        int alder = medlem.udregnAlder();
                        if (alder >= 18 && alder < 60) {
                            System.out.println(medlem.getFuldeNavn());
                        }
                    }
                    break;
                case 4:
                    for (Medlem medlem : medlemmer) {
                        if (medlem.udregnAlder() >= 60) {
                            System.out.println(medlem.getFuldeNavn());
                        }
                    }
                    break;
                case 5:
                    for (Medlem medlem : medlemmer) {
                        if (medlem instanceof KonkurrenceSvoemmer) {
                            System.out.println(medlem.getFuldeNavn());
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

    private static void kontingentMenu(Scanner scanner, ArrayList<Medlem> medlemmer) {
        boolean fortsaetKontingentMenu = true;
        while (fortsaetKontingentMenu) {
            System.out.println("\nKontingenthåndtering:");
            System.out.println("1: Vis kontingent for hvert medlem");
            System.out.println("2: Vis samlet kontingentindkomst");
            System.out.println("3: Tilbage til hovedmenuen");

            int kontingentValg = scanner.nextInt();
            switch (kontingentValg) {
                case 1:
                    for (Medlem medlem : medlemmer) {
                        System.out.println(medlem.getFuldeNavn() + " betaler " + medlem.beregnKontingent() + " i kontingent");
                    }
                    break;
                case 2:
                    double totalIndkomst = 0;
                    for (Medlem medlem : medlemmer) {
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
}