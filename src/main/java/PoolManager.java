import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class PoolManager {
    Lernset lernset;
    LinkedList<Begriff> activeCards = new LinkedList<Begriff>();

    public PoolManager(Lernset lernset){
        lernset = this.lernset;
        resetCards();
    }

    public void resetCards(){
        for(Begriff b : lernset.begriffe){
            b.pool = POOL.nochNie;
        }

        for(int i = 0; i < 10; i++) {
            addCardToMultipleChoice();
        }
    }

    public Begriff nextCard(){
        Begriff begriff = activeCards.poll();
        activeCards.offer(begriff);

        return begriff;
    }

    void moveBegriff(Begriff begriff, boolean result){
        POOL poolToMove = POOL.nochNie;
        switch (begriff.pool){
            case multipleChoice:
                poolToMove = result ? POOL.schriftlich : POOL.multipleChoice;
                break;
            case schriftlich:
                poolToMove = result ? POOL.finished : POOL.schriftlichFalse;
                begriff.repeatetFalse += result ? 0 : 1;
                break;
            case schriftlichFalse:
                poolToMove = result ? POOL.finished : POOL.schriftlich;
                begriff.repeatetFalse += result ? 0 : 1;
                break;
        }
        if(begriff.repeatetFalse > 5){
            poolToMove = POOL.repeatLater;
            begriff.repeatetFalse = 0;
        }
        moveBegriffToPool(begriff, poolToMove);
    }

    void moveBegriffToPool(Begriff begriff, POOL pool){
        begriff.pool = pool;

        if(pool == POOL.finished){
            addCardToMultipleChoice();
        }

        if(pool == POOL.multipleChoice){
            activeCards.offer(begriff);
        }
    }

    ArrayList<Begriff> getPotentialCards(){
        ArrayList<Begriff> potentialCards = new ArrayList<>();

        for(Begriff b : lernset.begriffe){
            if(b.pool != POOL.nochNie || b.pool != POOL.finished || b.pool != POOL.repeatLater){
                potentialCards.add(b);
            }
        }

        return potentialCards;
    }

    ArrayList<Begriff> getCardsInPool(POOL pool){


        return null;
    }

    void addCardToMultipleChoice(){
        ArrayList<Begriff> potentialBegriffe = getCardsInPool(POOL.nochNie);
        potentialBegriffe.addAll(getCardsInPool(POOL.repeatLater));
        Random rand = new Random();
        int randomIndex = rand.nextInt(potentialBegriffe.size() - 1);
        moveBegriffToPool(potentialBegriffe.get(randomIndex), POOL.multipleChoice);
    }
}