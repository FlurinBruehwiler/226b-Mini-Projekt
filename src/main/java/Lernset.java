import java.util.ArrayList;
import java.util.UUID;

public class Lernset {
    ArrayList<Begriff> begriffe;

    public Lernset(){
        begriffe = new ArrayList<Begriff>();
    }

    public Lernset(ArrayList<Begriff> _begriffArrayList){
        begriffe = _begriffArrayList;
    }

    public void addBegriff(Begriff _begriff){
        begriffe.add(_begriff);
    }

    public void removeBegriff(Begriff _begriff){
        begriffe.remove(_begriff);
    }

    public void removeBegriff(UUID id){
        Begriff _begriff = null;
        for (Begriff b: begriffe) {
            if(b.id == id){
                _begriff = b;
            }
        }
        if(_begriff != null){
            begriffe.remove(_begriff) ;
        }
    }
}