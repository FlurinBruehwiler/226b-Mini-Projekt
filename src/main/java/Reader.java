public class Reader{

    public void readinLernsetFiles()


        String file ="C:\\Users\\henrik.faeh\\Documents\\GitHub\\226b-Mini-Projekt\\Lernsets\\test1.txt";

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String currentLine = reader.readLine();

        reader.close();


    }
}