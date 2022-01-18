import java.io.*;

public class Reader{

    Cache objCache;

    public Reader(Cache objCache) throws IOException {
        this.objCache = objCache;
    }

    public void readLernsetFiles(String path) throws IOException {
        File folder = new File(path);

        for (final File fileEntry : folder.listFiles()) {
            readLernset(path + "\\" + fileEntry.getName());
        }
    }

    private void readLernset(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        Lernset lernset = new Lernset();
        String currentLine;
        while((currentLine = reader.readLine()) != null){
            var temp = currentLine.split("\\*");
            Card begriff = new Card(temp[1], temp[0]);
            lernset.addBegriff(begriff);
        }
        objCache.lernsets.add(lernset);

        reader.close();
    }
}