import java.util.UUID;

public class Begriff {
    public UUID id;
    public String begriff;
    public String definition;

    public Begriff(String begriff, String definition){
        id = UUID.randomUUID();
        begriff = this.begriff;
        definition = this.definition;
    }
}