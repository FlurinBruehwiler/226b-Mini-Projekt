import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public abstract class LearnBasis {
    Lernset lernset;
    public LearnBasis(Lernset lernset){
        this.lernset = lernset;
    }

    /**
     * dient für multiple choice.
     * @param begriff
     * @return
     */
    public int displayCards(Card begriff){
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

    /**
     * gibt die Wörter aus mit dem index.
     * @param begriff
     * @param index
     */
    public void displayCard(Card begriff, int index){
        System.out.println((index + 1) + ". " + begriff.definition);
    }

    /**
     * generiert randomBegriffe
     * @param begriffNotToInclude
     * @param amount
     * @return
     */
    public ArrayList<Card> getRandomBegriffe(Card begriffNotToInclude, int amount){
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
