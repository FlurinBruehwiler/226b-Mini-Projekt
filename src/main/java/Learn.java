import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Learn {
    PoolManager poolManager;
    Lernset lernset;

    public Learn(Lernset lernset){
        poolManager = new PoolManager(lernset);
        lernset = this.lernset;
    }

    public void startLearn(){
        Scanner scan = new Scanner(System.in);
        while(true){
            Begriff aktuellerBegriff = poolManager.nextCard();
            if(aktuellerBegriff.pool == POOL.multipleChoice){
                multipleChoice(aktuellerBegriff, scan);
            }else{
                schriftlich(aktuellerBegriff, scan);
            }
        }
    }

    private void multipleChoice(Begriff begriff, Scanner scan){
        System.out.println("----------------------------");
        System.out.println("------Multiple Choice-------");
        System.out.println("----------------------------");
        displayCards(begriff);

    }

    private void schriftlich(Begriff begriff, Scanner scan){
        System.out.println("----------------------------");
        System.out.println("--------Schriftlich---------");
        System.out.println("----------------------------");
    }

    private void displayCards(Begriff begriff){
        ArrayList<Begriff> begriffe = getRandomBegriffe(begriff, 3);
        begriffe.add(begriff);
        Collections.shuffle(begriffe);

        for(int i = 0; i < begriffe.size(); i++){
            displayCard(begriffe.get(i), i);
        }
    }

    private void displayCard(Begriff begriff, int index){
        System.out.println((index + 1) + ". " + begriff.definition);
    }

    ArrayList<Begriff> getRandomBegriffe(Begriff begriffNotToInclude, int amount){
        Random rand = new Random();
        ArrayList<Begriff> begriffe = new ArrayList<>();

        while(begriffe.size() < amount){
            Begriff b = lernset.begriffe.get(rand.nextInt(lernset.begriffe.size() - 1));
            if(!begriffNotToInclude.equals(b)){
                begriffe.add(b);
            }
        }
        return begriffe;
    }
}
