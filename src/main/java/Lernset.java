import java.util.ArrayList;

public class Lernset {
    ArrayList<Card> begriffe;

    public Lernset(){
        begriffe = new ArrayList<Card>();
    }

    public Lernset(ArrayList<Card> _begriffArrayList){
        begriffe = _begriffArrayList;
    }

    public void addBegriff(Card _begriff){
        begriffe.add(_begriff);
    }

    public void removeBegriff(Card _begriff){
        begriffe.remove(_begriff);
    }
}