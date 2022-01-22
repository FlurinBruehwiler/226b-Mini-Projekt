import java.io.IOException;

public class Starter {
    public static void main(String[] args) throws IOException {
        Cache objCache = new Cache();
        Reader reader = new Reader(objCache);
        reader.readLernsetFiles("E:\\Unity\\226b-Mini-Projekt\\Lernsets");

        MainMenu mainMenu = new MainMenu(objCache);
        mainMenu.showMainMenu();
    }
}