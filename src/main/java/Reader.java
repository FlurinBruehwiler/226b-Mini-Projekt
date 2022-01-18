import java.io.*;

public class Reader{

    Cache objCache;

    public Reader(Cache objCache) throws IOException {
        this.objCache = objCache;
    }

    public void readLernsetFiles(String path) throws IOException {
        File folder = new File(path);
        if(folder.listFiles() == null){
            System.out.println("null");
        }else {
            for (final File fileEntry : folder.listFiles()) {
                System.out.println(fileEntry.getName());
                readLernset(path + "\\" + fileEntry.getName());
            }
        }
    }

    private void readLernset(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        Lernset lernset = new Lernset();
        String currentLine;
        while((currentLine = reader.readLine()) != null){
            var temp = currentLine.split(" ");
            Begriff begriff = new Begriff(temp[0], temp[1]);
            lernset.addBegriff(begriff);
            objCache.lernsets.add(lernset);
        }

        reader.close();
    }
}