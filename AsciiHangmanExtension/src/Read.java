import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.BufferedReader;

public class Read{

    public static void main(String[] args) {
        ArrayList<String> words = getValues();
      	
		String word = words.get((int) (Math.random() * words.size()));
		char[] letters;
		letters = word.toCharArray();
        System.out.println(letters);
    }

    public static ArrayList<String> getValues() {
        FileInputStream stream = null;
        try {
            stream = new FileInputStream("gwords.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String strLine;
        ArrayList<String> lines = new ArrayList<String>();
        try {
            while ((strLine = reader.readLine()) != null) {
                String lastWord = strLine.substring(strLine.lastIndexOf(" ")+1);
                lines.add(lastWord);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

}