import java.time.*;
import java.util.*;

public class KonkurrenceSvoemmer extends Medlem{
    private Map<Discipliner, Double> bestResults;
    private Map<String, String> konkurrenceResultater;
    public Discipliner[] discipliner;

    public KonkurrenceSvoemmer(String navn, String koen, LocalDate foedselsDato, boolean kontingentStatus, Discipliner[] discipliner)
    {
        super(navn, koen, foedselsDato, kontingentStatus);
        bestResults = new HashMap<>();
        konkurrenceResultater = new HashMap<>();
        this.discipliner = discipliner;
    }

    public void recordBestTime(Discipliner disciplin, double time)
    {
        if(!bestResults.containsKey(disciplin) || time < bestResults.get(disciplin))
            bestResults.put(disciplin, time);
    }

    public void printBestTimes()
    {
        System.out.println(bestResults);
    }

    public void addKonkurrenceResultat(String konkurrence, String resultat)
    {
        konkurrenceResultater.put(konkurrence, resultat);
    }

    public void printKonkurrenceResultat()
    {
        System.out.println(konkurrenceResultater);
    }
}
