public class Card {
    public String begriff;
    public String definition;
    public POOL pool;
    public int repeatetFalse;

    public Card(String begriff, String definition){
        pool = null;
        repeatetFalse = 0;
        this.begriff = begriff;
        this.definition = definition;
    }
}