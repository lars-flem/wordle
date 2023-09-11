package wordle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileWriter implements iFileWriter{


    public void writeToFile(String filename, WordleGame wordleGame){
        try {
            PrintWriter writer = new PrintWriter(filename);
            writer.println(wordleGame.getFasit());
            writer.println(wordleGame.getCategory());
            for (int j = 0; j < wordleGame.getPreviousGuesses().size(); j++) {
                String word=wordleGame.getPreviousGuesses().get(j).getWord();
                writer.println(word);
            }
            writer.flush();
            writer.close();

        } catch (IOException e) {
            wordleGame.setGuessInfoMessage("Fant ikke filen");
        }
        
    }

    public void readFromFile(String filename, WordleGame wordleGame) throws IOException{
        try {
            Scanner scanner=new Scanner(new File(filename));
            int count=0;
            if (scanner.hasNextLine()) {
                wordleGame.setPreviousGuesses(new ArrayList<>());
                wordleGame.setAllowedGuesses(6);
                wordleGame.setGameOver(false);
                wordleGame.setWin(false);        
            }
            while (scanner.hasNextLine()) {
                String line=scanner.nextLine();
                count+=1;
                if (count>2) {
                    wordleGame.guess(line);
                }
                if (count==1) {
                    wordleGame.setFasit(line);
                }
                if (count==2) {
                    wordleGame.setCategory(line);
                }
            }
            scanner.close();
        } catch (IOException e) {
            wordleGame.setGuessInfoMessage("Fant ikke filen");
            throw new IOException("Fant ikke filen");
        }
        
    
    }

}

    

