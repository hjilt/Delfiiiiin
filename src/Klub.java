import java.util.*;

public class Klub {
    ArrayList<String> Medlemmer = new ArrayList<>();
    ArrayList<Medlem> juniorHold = new ArrayList<>();
    ArrayList<Medlem> seniorHold = new ArrayList<>();

    public void tilfoejMedlem(Medlem medlem) {
        if (medlem.erSenior()){

            seniorHold.add(medlem);
        }
        else {
            juniorHold.add(medlem);
        }
    }



}
