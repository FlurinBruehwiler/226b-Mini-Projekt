import java.io.IOException;

public class Starter {
    public static void main(String[] args) throws IOException {
        Cache objCache = new Cache();
        Reader reader = new Reader(objCache);
        reader.readLernsetFiles("src/main/resources/test1.txt");

        MainMenu mainMenu = new MainMenu(objCache);
        mainMenu.showMainMenu();
    }
}