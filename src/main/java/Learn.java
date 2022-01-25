import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Learn {
    PoolManager poolManager;
    Lernset lernset;
    Input input = new Input();
    LearnSchriftlich learnSchriftlich;
    LearnMultipleChoice learnMultipleChoice;

    public Learn(Lernset lernset){
        poolManager = new PoolManager(lernset);
        this.lernset = lernset;
        learnSchriftlich = new LearnSchriftlich(lernset);
        learnMultipleChoice = new LearnMultipleChoice(lernset, input);
    }

    public void startLearn() throws IOException {
        while(true){
            Card aktuellerBegriff = poolManager.nextCard();

            if(aktuellerBegriff == null){
                System.out.println("Du bist fertig!!!");
                return;
            }

            boolean result;
            if(aktuellerBegriff.pool == POOL.multipleChoice){
                result = learnMultipleChoice.multipleChoice(aktuellerBegriff);
            }else{
                result = learnSchriftlich.schriftlich(aktuellerBegriff);
            }

            if(result){
                System.out.println("Richtig!!!");
            }else{
                System.out.println("Das war falsch, richt währe gewesen: " + aktuellerBegriff.definition);
                System.out.print("Tippen sie die richtige Antwort ein: ");
            }

            Scanner newScanner = new Scanner(System.in);
            newScanner.nextLine();

            poolManager.moveBegriff(aktuellerBegriff, result);
        }
    }







}
