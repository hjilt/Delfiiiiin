import java.util.*;
import java.io.*;
import java.time.*;
import java.time.format.*;


public class Persistens {
//load medlemmer
    public static void loadMedlemmerFromCSV(String filePath, Klub klub) {
        List<Medlem> medlemmer = new ArrayList<>();
        String line;
        String csvSeparator = ",";
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            while ((line = br.readLine()) != null) {
                String[] values = line.split(csvSeparator);

                if (values.length >= 5) {
                    String fuldeNavn = values[0];
                    String koen = values[1];
                    LocalDate foedselsdato = LocalDate.parse(values[2], dateFormatter);
                    boolean erAktivtMedlem = Boolean.parseBoolean(values[3]);
                    boolean erKonkurrenceSvømmer = Boolean.parseBoolean(values[4]);
                    Medlem medlem;

                    if (erKonkurrenceSvømmer) {
                        KonkurrenceSvoemmer svoemmer;
                        if (values.length > 5 && !values[5].isBlank()) {
                            String[] disciplinerStrings = values[5].split(";");
                            Discipliner[] discipliner = new Discipliner[disciplinerStrings.length];
                            for (int i = 0; i < disciplinerStrings.length; i++) {
                                try {
                                    discipliner[i] = Discipliner.valueOf(disciplinerStrings[i].toUpperCase());
                                } catch (IllegalArgumentException e) {
                                    System.err.println("Invalid disciplin: " + disciplinerStrings[i]);
                                }
                            }
                            svoemmer = new KonkurrenceSvoemmer(fuldeNavn, koen, foedselsdato, erAktivtMedlem, discipliner);
                        } else {
                            svoemmer = new KonkurrenceSvoemmer(fuldeNavn, koen, foedselsdato, erAktivtMedlem);
                        }
                        medlem = svoemmer;
                    } else {
                       medlem = new Medlem(fuldeNavn, koen, foedselsdato, erAktivtMedlem);
                    }
                    klub.tilfoejMedlem(medlem);
                }
            }
    } catch (IOException e) {
            e.printStackTrace();
        }
}
    public static void loadBedsteTider(String filePath, Klub klub)
    {
        String line;
        String csvSeparator = ",";

        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            while((line = br.readLine()) != null)
            {
                String[] values = line.split(csvSeparator);
                if (values.length == 3) {
                    String fuldeNavn = values[0];
                    String disciplinString = values[1];
                    double time = Double.parseDouble(values[2]);

                    Discipliner disciplin = Discipliner.valueOf(disciplinString);
                    Medlem medlem = null;
                    for(Medlem m : klub.getMedlemmer())
                    {
                        if(m.getFuldeNavn().equalsIgnoreCase(fuldeNavn))
                        {
                            medlem = m;
                            break;
                        }
                    }
                    if (medlem instanceof KonkurrenceSvoemmer) {
                        ((KonkurrenceSvoemmer) medlem).recordBestTime(disciplin, time);
                        System.out.println("TEST VIRKER YEEHAWW");
                    }
                    else {
                        System.out.println("Svømmer ikke fundet ");
                    }
                }
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
