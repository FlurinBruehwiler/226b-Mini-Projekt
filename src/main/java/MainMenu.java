import java.io.IOException;

public class MainMenu {
    Cache objCache;
    Input input = new Input();

    public MainMenu(Cache objCache){
        this.objCache = objCache;
    }

    /**
     * Handling der ganzen Lernset Auswahl.
     * @throws IOException
     */
    public void showMainMenu() throws IOException {
        while(true){
            printLernsets();
            int choice = chooseLernset(objCache.lernsets.size());
            startLernset(choice);
        }
    }

    /**
     * gibt alle Lernsets aus.
     */
    private void printLernsets()  {
        if(objCache.lernsets.size() == 0)
            System.out.println("Es gibt keine Validen Lernsets im angegebenen Ordner");

        for(int i = 0; i < objCache.lernsets.size(); i++){
            printLernset(objCache.lernsets.get(i).getName(), i);
        }
    }

    /**
     * User wählt Lernset aus.
     * @param maxNumber
     * @return
     */
    private int chooseLernset(int maxNumber){
        System.out.println("Bitte wählen sie ein Lernset");
        return input.getValidIntegerInput(maxNumber) - 1;
    }

    /**
     * Ruft lernen mit dem gewählten Lernset auf.
     * @param index
     * @throws IOException
     */
    private void startLernset(int index) throws IOException {
        Learn learn = new Learn(objCache.lernsets.get(index));
        learn.startLearn();
    }

    /**
     * gibt einzelnes Lernset aus.
     * @param name
     * @param index
     */
    private void printLernset(String name, int index){
        System.out.println((index + 1) + ". " + name);
    }
}
