import java.io.IOException;

public class Starter {
    public static void main(String[] args) throws IOException {

        Cache objCache = new Cache();
        Reader reader = new Reader(objCache);
        reader.readLernsetFiles("C:/Users/henrik.faeh/Documents/GitHub/226b-Mini-Projekt/Lernsets");


    }
}