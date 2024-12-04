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
            } else {
                juniorHold.add(svoemmer);
                medlemmer.add(medlem);
            }
        } else {
            medlemmer.add(medlem);
        }
    }

    public ArrayList<Medlem> getMedlemmer() {
        return medlemmer;
    }
}
