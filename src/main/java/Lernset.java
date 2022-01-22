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

    public void addBegriff(Card _begriff){
        begriffe.add(_begriff);
    }

    public void removeBegriff(Card _begriff){
        begriffe.remove(_begriff);
    }

    public String getName(){
        return name;
    }

    public Card getCard(int index){
        return begriffe.get(index);
    }

    public ArrayList<Card> getAllCards(){
        return begriffe;
    }
}