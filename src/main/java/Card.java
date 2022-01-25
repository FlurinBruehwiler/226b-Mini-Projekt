public class Card {
    public String begriff;
    public String definition;
    public Pool pool;
    public int repeatetFalse;

    public Card(String begriff, String definition){
        pool = null;
        repeatetFalse = 0;
        this.begriff = begriff;
        this.definition = definition;
    }

    public void switchPool(boolean result){
        pool = pool.NextPool(result);
    }
}