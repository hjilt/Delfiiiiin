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

                if (values.length >= 6) {
                    String fuldeNavn = values[0];
                    String koen = values[1];
                    LocalDate foedselsdato = LocalDate.parse(values[2], dateFormatter);
                    LocalDate oprettelsesDato = LocalDate.parse(values[3], dateFormatter);
                    boolean erAktivtMedlem = Boolean.parseBoolean(values[4]);
                    boolean erKonkurrenceSvømmer = Boolean.parseBoolean(values[5]);
                    Medlem medlem;

                    if (erKonkurrenceSvømmer) {
                        KonkurrenceSvoemmer svoemmer;
                        if (values.length > 6 && !values[6].isBlank()) {
                            String[] disciplinerStrings = values[6].split(";");
                            Discipliner[] discipliner = new Discipliner[disciplinerStrings.length];
                            for (int i = 0; i < disciplinerStrings.length; i++) {
                                try {
                                    discipliner[i] = Discipliner.valueOf(disciplinerStrings[i].toUpperCase());
                                } catch (IllegalArgumentException e) {
                                    System.err.println("Invalid disciplin: " + disciplinerStrings[i]);
                                }
                            }
                            svoemmer = new KonkurrenceSvoemmer(fuldeNavn, koen, foedselsdato, oprettelsesDato, erAktivtMedlem, discipliner);
                        } else {
                            svoemmer = new KonkurrenceSvoemmer(fuldeNavn, koen, foedselsdato, oprettelsesDato, erAktivtMedlem);
                        }
                        medlem = svoemmer;
                    } else {
                       medlem = new Medlem(fuldeNavn, koen, foedselsdato, oprettelsesDato, erAktivtMedlem);
                    }
                    klub.tilfoejMedlem(medlem);
                }
            }
    } catch (IOException e) {
            e.printStackTrace();
        }
}

    public static void saveMedlemmerToCSV(String filePath, Klub klub) {
        String csvSeparator = ",";
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filePath)))
        {
            for (Medlem medlem : klub.getMedlemmer()) {
                StringBuilder line = new StringBuilder();
                line.append(medlem.getFuldeNavn()).append(csvSeparator)
                .append(medlem.getKoen()).append(csvSeparator)
                .append(medlem.getFoedselsdato()).append(csvSeparator)
                .append(medlem.getOprettelsesDato()).append(csvSeparator)
                .append(medlem.getErAktivtMedlem()).append(csvSeparator);

                if (medlem instanceof KonkurrenceSvoemmer) {
                    KonkurrenceSvoemmer svoemmer = (KonkurrenceSvoemmer) medlem;
                    line.append("true").append(csvSeparator);
                    if (svoemmer.getDiscipliner() != null && svoemmer.getDiscipliner().length > 0) {
                        for (int i = 0; i < svoemmer.getDiscipliner().length; i++) {
                            Discipliner disciplin = svoemmer.getDiscipliner()[i];
                            line.append(disciplin.name());
                            if (i < svoemmer.getDiscipliner().length - 1) {
                                line.append(";"); // Add semicolon only if not the last element
                            }
                        }
                    }
                } else {
                    line.append("false");  // mark as non-competitive swimmer
                }
                bw.write(line.toString());
                bw.newLine();
            }
        }
        catch(IOException e)
        {
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
                }
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void loadRestancer(String filePath, Klub klub)
    {
        String line;
        String csvSeparator = ",";

        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            while((line = br.readLine()) != null)
            {
                String[] values = line.split(csvSeparator);
                if (values.length == 2) {
                    String fuldeNavn = values[0];
                    double restance = Double.parseDouble(values[1]);

                    Medlem medlem = null;
                    for(Medlem m : klub.getMedlemmer())
                    {
                        if(m.getFuldeNavn().equalsIgnoreCase(fuldeNavn))
                        {
                            medlem = m;
                            break;
                        }
                    }
                    medlem.setRestance(restance);
                }
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
