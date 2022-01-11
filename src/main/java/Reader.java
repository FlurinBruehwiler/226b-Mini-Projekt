import java.io.*;

public class Reader{

    Cache objCache;

    public Reader(Cache objCache) throws IOException {
        objCache = this.objCache;
    }

    public void readLernsetFiles(String path) throws IOException {
        File folder = new File(path);
        for (final File fileEntry : folder.listFiles()) {
            readLernset(path + "\\" + fileEntry.getName());
        }
    }

    private void readLernset(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String currentLine = reader.readLine();
        var temp = currentLine.split("\t");
        Begriff begriff = new Begriff(temp[0], temp[1]);
        Lernset lernset = new Lernset();
        lernset.addBegriff(begriff);
        objCache.lernsets.add(lernset);
        reader.close();
    }
}