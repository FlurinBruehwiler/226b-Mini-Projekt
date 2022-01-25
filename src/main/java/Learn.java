import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Learn {
    PoolManager poolManager;
    Lernset lernset;
    Input input = new Input();

    public Learn(Lernset lernset){
        poolManager = new PoolManager(lernset);
        this.lernset = lernset;
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
                result = multipleChoice(aktuellerBegriff);
            }else{
                result = schriftlich(aktuellerBegriff);
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

    private boolean multipleChoice(Card card){
        System.out.println("----------------------------");
        System.out.println("------Multiple Choice-------");
        System.out.println("----------------------------\n");

        System.out.println("Begriff: " + card.begriff);
        System.out.println("Mögliche Definitionen: ");
        int correctCard = displayCards(card);

        System.out.print("Wähle die richtige Definition aus: ");

        int choice = input.getValidIntegerInput(4);
        return choice == correctCard;
    }


    private boolean schriftlich(Card card){
        System.out.println("----------------------------");
        System.out.println("--------Schriftlich---------");
        System.out.println("----------------------------\n");

        System.out.println("Begriff: " + card.begriff);
        System.out.print("Geben sie die Definition ein: ");

        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        return input == card.definition;
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

    private ArrayList<Card> getRandomBegriffe(Card begriffNotToInclude, int amount){
        Random rand = new Random();
        ArrayList<Card> begriffe = new ArrayList<>();

        while(begriffe.size() < amount){
            Card b = lernset.getCard(rand.nextInt(lernset.getAllCards().size() - 1));
            if(!begriffNotToInclude.equals(b)){
                begriffe.add(b);
            }
        }
        return begriffe;
    }
}
