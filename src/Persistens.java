import java.util.*;
import java.io.*;
import java.time.*;
import java.time.format.*;


public class Persistens {
//load medlemmer
public static List<Medlem> loadMedlemmerFromCSV(String filePath) {
    List<Medlem> medlemmer = new ArrayList<>();
    String line;
    String csvSeparator = ",";
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
        br.readLine();

        while ((line = br.readLine()) != null) {
            String[] values = line.split(csvSeparator);

            if (values.length >= 5) {
                String fuldeNavn = values[0];
                String koen = values[1];
                LocalDate foedselsdato = LocalDate.parse(values[2], dateFormatter);
                boolean erAktivtMedlem = Boolean.parseBoolean(values[3]);
                boolean erKonkurrenceSvømmer = Boolean.parseBoolean(values[4]);

                if (erKonkurrenceSvømmer) {
                    KonkurrenceSvoemmer svoemmer = new KonkurrenceSvoemmer(fuldeNavn, koen, foedselsdato, erAktivtMedlem);
                    if (values.length > 5) {
                        String[] discipliner = values[5].split(";");
                        for (String disciplin : discipliner) {
                            try {
                                svoemmer.recordBestTime(Discipliner.valueOf(disciplin), Double.MAX_VALUE); // Placeholder time
                            } catch (IllegalArgumentException e) {
                                System.err.println("Invalid disciplin: " + disciplin);
                            }
                        }
                    }
                    medlemmer.add(svoemmer);
                } else {
                    medlemmer.add(new Medlem(fuldeNavn, koen, foedselsdato, erAktivtMedlem));
                }
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }

    return medlemmer;
}

    public static void main(String[] args) {
        String filePath = "medlemmer.txt"; // Path to your CSV file
        List<Medlem> medlemmer = loadMedlemmerFromCSV(filePath);

        for (Medlem medlem : medlemmer) {
            System.out.println(medlem.getFuldeNavn());
            if (medlem instanceof KonkurrenceSvoemmer) {
                ((KonkurrenceSvoemmer) medlem).printBestTimes();
            }
        }
    }
}
