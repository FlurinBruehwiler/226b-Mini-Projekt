import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Learn {
    PoolManager poolManager;
    Lernset lernset;

    public Learn(Lernset lernset){
        poolManager = new PoolManager(lernset);
        this.lernset = lernset;
    }

    public void startLearn(){
        Scanner scan = new Scanner(System.in);
        while(true){
            Card aktuellerBegriff = poolManager.nextCard();
            if(aktuellerBegriff.pool == POOL.multipleChoice){
                multipleChoice(aktuellerBegriff, scan);
            }else{
                schriftlich(aktuellerBegriff, scan);
            }
        }
    }

    private void multipleChoice(Card card, Scanner scan){
        System.out.println("----------------------------");
        System.out.println("------Multiple Choice-------");
        System.out.println("----------------------------\n");

        System.out.println("Begriff: " + card.begriff);
        System.out.println("Mögliche Definitionen: ");
        int correctCard = displayCards(card);

        System.out.println("Wähle die richtige Definition aus");

        int choice = scan.nextInt();
        boolean result = choice == correctCard;

        if(result){
            System.out.println("Richtig!!!");
        }else{
            System.out.println("Das war falsch, richt währe gewesen: " + card.definition);
        }

        poolManager.moveBegriff(card, result);
    }

    private void schriftlich(Card card, Scanner scan){
        System.out.println("----------------------------");
        System.out.println("--------Schriftlich---------");
        System.out.println("----------------------------\n");

        System.out.println("Begriff: " + card.begriff);
        System.out.print("Geben sie die Definition ein: ");

        String input = scan.nextLine();
        boolean result = input == card.definition;

        if(result){
            System.out.println("Richtig!!!");
        }else{
            System.out.println("Das war falsch, richt währe gewesen: " + card.definition);
        }

        poolManager.moveBegriff(card, result);
    }

    private int displayCards(Card begriff){
        ArrayList<Card> begriffe = getRandomBegriffe(begriff, 3);
        begriffe.add(begriff);
        Collections.shuffle(begriffe);

        int correctCard = -1;

        for(int i = 0; i < begriffe.size(); i++){
            displayCard(begriffe.get(i), i);

            if(begriffe.get(i) == begriff){
                correctCard = i + 1;
            }
        }

        return correctCard;
    }

    private void displayCard(Card begriff, int index){
        System.out.println((index + 1) + ". " + begriff.definition);
    }

    ArrayList<Card> getRandomBegriffe(Card begriffNotToInclude, int amount){
        Random rand = new Random();
        ArrayList<Card> begriffe = new ArrayList<>();

        while(begriffe.size() < amount){
            Card b = lernset.begriffe.get(rand.nextInt(lernset.begriffe.size() - 1));
            if(!begriffNotToInclude.equals(b)){
                begriffe.add(b);
            }
        }
        return begriffe;
    }
}
