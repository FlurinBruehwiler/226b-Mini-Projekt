import java.io.IOException;

public class Starter {
    public static void main(String[] args) throws IOException {

        Cache objCache = new Cache();
        Reader reader = new Reader(objCache);
        reader.readLernsetFiles("E:\\Unity\\226b-Mini-Projekt\\Lernsets");
        for (Begriff begriff : objCache.lernsets.get(0).begriffe) {
            System.out.println("Begriff: " + begriff.begriff + ", Definition: " + begriff.definition);
        }
    }
}