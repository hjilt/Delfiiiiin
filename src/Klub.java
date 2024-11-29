import java.sql.SQLOutput;
import java.util.*;

public class Klub {
    private ArrayList<KonkurrenceSvoemmer> juniorHold;
    private ArrayList<KonkurrenceSvoemmer> seniorHold;
    private ArrayList<Medlem> medlemmer;

    public Klub() {
        juniorHold = new ArrayList<>();
        seniorHold = new ArrayList<>();
        medlemmer = new ArrayList<>();
    }


    public void tilfoejMedlem(Medlem medlem) {
        if (medlem instanceof KonkurrenceSvoemmer svoemmer) {
            if (medlem.erSenior()) {
                seniorHold.add(svoemmer);
                medlemmer.add(medlem);
                System.out.println("Medlem er tilføjet til seniorholdet!");
            } else {
                juniorHold.add(svoemmer);
                medlemmer.add(medlem);
                System.out.println("Medlem er tilføjet til juniorholdet!");
            }
        } else {
            System.out.println("Bro er ikke konkurrence Svømmer. Prøv igen!");
            medlemmer.add(medlem);
        }
    }

    public ArrayList<Medlem> getMedlemmer() {
        return medlemmer;
    }
}
