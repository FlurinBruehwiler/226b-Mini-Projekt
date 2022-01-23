import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {
    int getValidIntegerInput(int maxNumber){
        while(true){
            Scanner scan = new Scanner(System.in);
            int result = -1;
            try{
                result = scan.nextInt();
                if(result < 1 || result > maxNumber)
                    throw new InputMismatchException();
                return result;
            }catch (InputMismatchException err){
                System.out.println("Geben Sie bitte eine valide Zahl ein");
            }
        }
    }
}
