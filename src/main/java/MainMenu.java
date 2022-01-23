import java.io.IOException;

public class MainMenu {
    Cache objCache;
    Input input = new Input();

    public MainMenu(Cache objCache){
        this.objCache = objCache;
    }

    public void showMainMenu() throws IOException {
        while(true){
            printLernsets();
            int choice = chooseLernset(objCache.lernsets.size());
            startLernset(choice);
        }
    }

    private void printLernsets()  {
        if(objCache.lernsets.size() == 0)
            System.out.println("Es gibt keine Validen Lernsets im angegebenen Ordner");

        for(int i = 0; i < objCache.lernsets.size(); i++){
            printLernset(objCache.lernsets.get(i).getName(), i);
        }
    }

    private int chooseLernset(int maxNumber){
        System.out.println("Bitte wählen sie ein Lernset");
        return input.getValidIntegerInput(maxNumber) - 1;
    }

    private void startLernset(int index) throws IOException {
        Learn learn = new Learn(objCache.lernsets.get(index));
        learn.startLearn();
    }

    private void printLernset(String name, int index){
        System.out.println((index + 1) + ". " + name);
    }
}
