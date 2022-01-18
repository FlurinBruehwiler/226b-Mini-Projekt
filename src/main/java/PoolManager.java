import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class PoolManager {
    Lernset lernset;
    LinkedList<Card> activeCards = new LinkedList<Card>();

    public PoolManager(Lernset lernset) {
        this.lernset = lernset;
        resetCards();
    }

    public void resetCards() {
        for (Card b : lernset.begriffe) {
            b.pool = POOL.nochNie;
        }

        for (int i = 0; i < 10; i++) {
            addCardToMultipleChoice();
        }
    }

    public Card nextCard() {
        Card begriff = activeCards.poll();
        activeCards.offer(begriff);

        return begriff;
    }

    public void moveBegriff(Card begriff, boolean result) {
        POOL poolToMove = POOL.nochNie;
        switch (begriff.pool) {
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
        if (begriff.repeatetFalse > 5) {
            poolToMove = POOL.repeatLater;
            begriff.repeatetFalse = 0;
        }
        moveBegriffToPool(begriff, poolToMove);
    }

    void moveBegriffToPool(Card begriff, POOL pool) {
        begriff.pool = pool;

        if (pool == POOL.finished) {
            addCardToMultipleChoice();
        }

        if (pool == POOL.multipleChoice) {
            activeCards.offer(begriff);
        }
    }

    ArrayList<Card> getPotentialCards() {
        ArrayList<Card> potentialCards = new ArrayList<>();

        for (Card b : lernset.begriffe) {
            if (b.pool != POOL.nochNie || b.pool != POOL.finished || b.pool != POOL.repeatLater) {
                potentialCards.add(b);
            }
        }

        return potentialCards;
    }

    ArrayList<Card> getCardsInPool(POOL pool) {
        ArrayList<Card> begriffeInPool = new ArrayList<>();
        for (Card c : lernset.begriffe) {
            if (c.pool == pool) {
                begriffeInPool.add(c);
            }
        }

        return begriffeInPool;
    }

    void addCardToMultipleChoice() {
        ArrayList<Card> potentialBegriffe = getCardsInPool(POOL.nochNie);
        potentialBegriffe.addAll(getCardsInPool(POOL.repeatLater));
        Random rand = new Random();
        int randomIndex = rand.nextInt(potentialBegriffe.size() - 1);
        moveBegriffToPool(potentialBegriffe.get(randomIndex), POOL.multipleChoice);
    }
}