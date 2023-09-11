package wordle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import wordle.FileWriter;
import wordle.Word;
import wordle.WordleGame;

public class FileWriteTest {
    
    private static FileWriter fileWriter=new FileWriter();
    private static WordleGame wordleGame1=new WordleGame("norsk");
    private static WordleGame wordleGame2=new WordleGame("engelsk");
    private static WordleGame wordleGame3=new WordleGame("slæng");

    @BeforeAll
    public static void setup(){
        wordleGame1.setFasit("pasta");
        wordleGame1.guess("kaste");
        wordleGame1.guess("taste");

    }

    @Test
    @DisplayName("Tester at tilstanden har blitt bevart")
        public void testConditionBeforeAndAfter() throws IOException{
        fileWriter.writeToFile("textFileWordle", wordleGame1);
        fileWriter.readFromFile("textFileWordle", wordleGame2);

        assertEquals(wordleGame1.getCategory(), wordleGame2.getCategory(),
            "Kategorien ble ikke bevart");

        assertEquals(wordleGame1.getFasit(), wordleGame2.getFasit(),
            "Fasiten ble ikke bevart");

        assertEquals(wordleGame1.getAllowedGuesses(),wordleGame2.getAllowedGuesses(),
            "AllowedGuesses ble ikke bevart");

        assertEquals(wordleGame1.isGameOver(), wordleGame2.isGameOver(),
            "Tilstanden ble ikke bevart");
        
        assertEquals(wordleGame1.isWin(), wordleGame2.isWin(),
            "Tilstanden ble ikke bevart");


        Iterator<Word>expectedGuesses=wordleGame1.getPreviousGuesses().iterator();
        Iterator<Word>actualGuesses=wordleGame1.getPreviousGuesses().iterator();

        while (expectedGuesses.hasNext()) {
            try {
                Word expectedGuess=expectedGuesses.next();
                Word actualGuess=actualGuesses.next();
                assertEquals(expectedGuess, actualGuess,
                    "Tilstanden ble ikke bevart riktig"); 
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e);
            }
        }
        assertThrows(IllegalArgumentException.class, () -> {
			wordleGame2.guess("chair");
		}, "Skal ikke kunne gjette engelsk ord, når kategorien er norsk");
    }

    @Test
    @DisplayName("Tester hva som skjer når man prøver å lese en ugyldig fil")
    public void testLoadingNonExistingFile(){
        assertThrows(IOException.class, () -> {
			fileWriter.readFromFile("nonExistingFile", wordleGame1);
		}, "Skal utløse uttak om det gis inn en ikke eskistrende fil");


    }    
}
