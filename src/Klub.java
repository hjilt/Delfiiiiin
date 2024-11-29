import java.sql.SQLOutput;
import java.util.*;

public class Klub {
    private ArrayList<KonkurrenceSvoemmer> juniorHold = new ArrayList<>();
    private ArrayList<KonkurrenceSvoemmer> seniorHold = new ArrayList<>();

    public Klub() {
        juniorHold = new ArrayList<>();
        seniorHold = new ArrayList<>();
    }


    public void tilfoejMedlem(Medlem medlem) {
        if (medlem instanceof KonkurrenceSvoemmer svoemmer) {
            if (medlem.erSenior()) {
                seniorHold.add(svoemmer);
                System.out.println("Medlem er tilføjet til seniorholdet!");
            } else {
                juniorHold.add(svoemmer);
                System.out.println("Medlem er tilføjet til juniorholdet!");
            }
        } else {
            System.out.println("Bro er ikke konkurrence Svømmer. Prøv igen!");
        }
    }
}
