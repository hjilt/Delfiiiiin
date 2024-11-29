import java.util.*;

public class Klub {
    ArrayList<KonkurrenceSvoemmer> juniorHold = new ArrayList<>();
    ArrayList<KonkurrenceSvoemmer> seniorHold = new ArrayList<>();

    public void tilfoejMedlem(Medlem medlem) {

        if (medlem.erSenior()){

            seniorHold.add((KonkurrenceSvoemmer) medlem);
        }
        else {
            juniorHold.add((KonkurrenceSvoemmer) medlem);
        }
    }
}
