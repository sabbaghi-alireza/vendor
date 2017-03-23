package vendor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alireza on 3/23/2017.
 */
public class Machine {

    public static List<Integer> denominationList = new ArrayList<>();

    public static void main(String[] args) {
        if (args == null || args.length == 0) {
            System.out.println("No denomination parameter !");
            System.exit(0);
        }

        try {
            validateDenominations(args);
        } catch (Exception e) {
            System.out.println("Input denomination is not a valid integer !");
            System.exit(0);
        }

        Engine vendorEngine = new Engine();

        try {
            vendorEngine.process(denominationList);
        }catch (NotValidDenominationException e){
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    private static void validateDenominations(String[] args) throws Exception {
        for (String arg :
                args) {
            denominationList.add(Integer.parseInt(arg));
        }
    }

}
