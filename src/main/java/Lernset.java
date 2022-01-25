import java.util.ArrayList;

public class Lernset {
    private ArrayList<Card> begriffe;
    private String name;

    public Lernset(String name){
        begriffe = new ArrayList<Card>();
        this.name = name;
    }

    public Lernset(ArrayList<Card> _begriffArrayList, String name){
        begriffe = _begriffArrayList;
        this.name = name;
    }

    /**
     * fügt einen Begriff der ArrayList hinzu.
     * @param _begriff
     */
    public void addBegriff(Card _begriff){
        begriffe.add(_begriff);
    }

    /**
     * löscht einen Begriff aus der ArrayList.
     * @param _begriff
     */
    public void removeBegriff(Card _begriff){
        begriffe.remove(_begriff);
    }

    /**
     * gibt den Namen zurück
     * @return name
     */
    public String getName(){
        return name;
    }

    /**
     * holt den Begriff mithilfe des index.
     * @param index
     * @return spezifischer begriff
     */
    public Card getCard(int index){
        return begriffe.get(index);
    }

    /**
     * gibt alle Begriffe zurück
     * @return alle begriffe
     */
    public ArrayList<Card> getAllCards(){
        return begriffe;
    }
}