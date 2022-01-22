import java.io.IOException;
import java.util.Scanner;

public class MainMenu {
    Cache objCache;
    Scanner scanner = new Scanner(System.in);
    public MainMenu(Cache objCache){
        this.objCache = objCache;
    }

    public void showMainMenu() throws IOException {
        while(true){
            printLernsets();
            int choice = chooseLernset();
            startLernset(choice);
        }
    }

    private void printLernsets()  {
        for(int i = 0; i < objCache.lernsets.size(); i++){
            printLernset(objCache.lernsets.get(i).getName(), i);
        }
    }

    private int chooseLernset(){
        System.out.println("Bitte wählen sie ein Lernset, dass sie die Gymi Prüfung im ersten Versuch bestehen!");
        return scanner.nextInt() - 1;
    }

    private void startLernset(int index) throws IOException {
        Learn learn = new Learn(objCache.lernsets.get(index));
        learn.startLearn();
    }

    private void printLernset(String name, int index){
        System.out.println((index + 1) + ". " + name);
    }
}
