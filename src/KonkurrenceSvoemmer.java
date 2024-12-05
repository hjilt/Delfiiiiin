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

    public Discipliner[] getDiscipliner()
    {
        return discipliner;
    }

    public void udskrivBestTimes()
    {
        System.out.println("The best result: "+getFuldeNavn()+":");
        for (Discipliner disciplin : Discipliner.values())
        {
            if (bestResults.containsKey(disciplin))
            {
                System.out.printf("Tid: ", disciplin, bestResults.get(disciplin));
            }else {
                System.out.printf(" Resultater", disciplin);
            }

        }
    }
}
