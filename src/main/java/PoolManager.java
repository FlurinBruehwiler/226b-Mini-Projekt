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

    /**
     * fügt karten wieder in den Umlauf der Pools.
     */
    public void resetCards() {
        for (Card b : lernset.getAllCards()) {
            b.pool = POOL.nochNie;
        }

        for (int i = 0; i < 10; i++) {
            addCardToMultipleChoice();
        }
    }

    /**
     * geht zur nächsten Karte/Begriff.
     * @return
     */
    public Card nextCard() {
        Card begriff = activeCards.poll();
        if(begriff != null){
            activeCards.offer(begriff);
        }

        return begriff;
    }

    /**
     * Begriff/Karte wird in einen anderen Pool verschoben.
     * @param begriff
     * @param result
     */
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
            poolToMove = POOL.nochNie;
            begriff.repeatetFalse = 0;
        }
        moveBegriffToPool(begriff, poolToMove);
    }

    /**
     * fügt Karten wieder hinzu.
     * @param begriff
     * @param pool
     */
    void moveBegriffToPool(Card begriff, POOL pool) {
        begriff.pool = pool;

        if (pool == POOL.finished) {
            addCardToMultipleChoice();
        }

        if (pool == POOL.multipleChoice) {
            activeCards.offer(begriff);
        }
    }

    /**
     * holt die Begriffe/Karten welche möglich sind.
     * @return mögliche Karten
     */
    ArrayList<Card> getPotentialCards() {
        ArrayList<Card> potentialCards = new ArrayList<>();

        for (Card b : lernset.getAllCards()) {
            if (b.pool != POOL.nochNie || b.pool != POOL.finished) {
                potentialCards.add(b);
            }
        }

        return potentialCards;
    }

    /**
     * holt alle Karten/Begriffe in einem Pool
     * @param pool
     * @return Begriffe in einem Pool
     */
    ArrayList<Card> getCardsInPool(POOL pool) {
        ArrayList<Card> begriffeInPool = new ArrayList<>();
        for (Card c : lernset.getAllCards()) {
            if (c.pool == pool) {
                begriffeInPool.add(c);
            }
        }

        return begriffeInPool;
    }

    /**
     * fügt Karte/Begriff zu Multiple Choice hinzu
     */
    void addCardToMultipleChoice() {
        ArrayList<Card> potentialBegriffe = getCardsInPool(POOL.nochNie);

        if(potentialBegriffe.size() == 0)
            return;

        Random rand = new Random();
        int randomIndex = rand.nextInt(potentialBegriffe.size() - 1);
        moveBegriffToPool(potentialBegriffe.get(randomIndex), POOL.multipleChoice);
    }
}