import java.time.*;
import java.util.*;

public class KonkurrenceSvoemmer extends Medlem{
    private Map<Discipliner, Double> bestResults;
    private Map<String, String> konkurrenceResultater;
    public Discipliner[] discipliner;

    public KonkurrenceSvoemmer(String navn, String koen, LocalDate foedselsDato, LocalDate oprettelsesDato, boolean erAktivtMedlem, Discipliner ... disciplin)
    {
        super(navn, koen, foedselsDato, oprettelsesDato, erAktivtMedlem);
        bestResults = new HashMap<>();
        konkurrenceResultater = new HashMap<>();
        this.discipliner = disciplin.length > 0 ? disciplin : new Discipliner[0];
    }

    public KonkurrenceSvoemmer()
    {

    }

    public Discipliner[] getDiscipliner()
    {
        return discipliner;
    }

    public void recordBestTime(Discipliner disciplin, double time)
    {
        if(!bestResults.containsKey(disciplin) || time < bestResults.get(disciplin))
            bestResults.put(disciplin, time);
    }

    public Map<Discipliner, Double> getBestTimes()
    {
        return bestResults;
    }

    public void addKonkurrenceResultat(String konkurrence, String resultat)
    {
        konkurrenceResultater.put(konkurrence, resultat);
    }

    public void printKonkurrenceResultat()
    {
        System.out.println(konkurrenceResultater);
    }

    public static void udskrivTop5Svoemmer(List<KonkurrenceSvoemmer> svoemmere) {
        Map<Discipliner, List<Map.Entry<KonkurrenceSvoemmer, Double>>> resultater = new HashMap<>();
        for (Discipliner disciplin : Discipliner.values()) {
            resultater.put(disciplin, new ArrayList<>());
        }
        for (KonkurrenceSvoemmer svoemmer : svoemmere) {
            for (Map.Entry<Discipliner, Double> entry : svoemmer.getBestTimes().entrySet()) {
                Discipliner disciplin = entry.getKey();
                Double time = entry.getValue();
                resultater.get(disciplin).add(Map.entry(svoemmer, time));
            }
        }

        for (Discipliner disciplin : Discipliner.values()) {
            System.out.println("De top 5 Konkurrencesvømmere er: " + disciplin);

            List<Map.Entry<KonkurrenceSvoemmer, Double>> disciplinResultater = resultater.get(disciplin);
            disciplinResultater.sort(Map.Entry.comparingByValue());

            for (int i = 0; i < Math.min(5, disciplinResultater.size()); i++) {
                Map.Entry<KonkurrenceSvoemmer, Double> entry = disciplinResultater.get(i);
                KonkurrenceSvoemmer svoemmer = entry.getKey();
                Double time = entry.getValue();
                System.out.println(" " + (i+1) + ": " + svoemmer.getFuldeNavn() + " " + time);
            }

            if (disciplinResultater.isEmpty()) {
                System.out.println("Ingen resultater for denne disciplin");
            }
        }
    }
}
