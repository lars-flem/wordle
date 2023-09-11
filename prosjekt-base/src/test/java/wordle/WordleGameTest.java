package wordle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import wordle.WordleGame;

public class WordleGameTest {
    
    WordleGame wordleGame1=new WordleGame("norsk");
    WordleGame wordleGame2=new WordleGame("norsk");
    WordleGame wordleGame3=new WordleGame("norsk");
    WordleGame wordleGame4=new WordleGame("norsk");
    WordleGame wordleGame5=new WordleGame("norsk");
    ArrayList<WordleGame> wordlegames=new ArrayList<>(Arrays.asList(wordleGame1,wordleGame2,wordleGame3,wordleGame4));
    char green='G';
    char yellow='Y';
    char blank='B';
    
    public void setupWordlegames(){
        for (WordleGame wordleGame : wordlegames) {
            wordleGame.setPreviousGuesses(new ArrayList<>());
            wordleGame.setAllowedGuesses(6);
            wordleGame.setWin(false);
            wordleGame.setGameOver(false);
        }
    }

    @Test
    public void testConstructor(){

    }

    @Test
    @DisplayName("Tester at spillet endrer fargene som det skal for en farge")
    public void testCorrectColorsForOneColor(){
        setupWordlegames();
        wordleGame1.setFasit("xaxxx");
        wordleGame1.guess("pasta");

        char actualColor1=wordleGame1.getPreviousGuesses().get(0).getLetterBox(1).getColor();
        assertEquals(green, actualColor1);

        wordleGame2.setFasit("xxpxx");
        wordleGame2.guess("pasta");

        char actualColor2=wordleGame2.getPreviousGuesses().get(0).getLetterBox(0).getColor();
        assertEquals(yellow, actualColor2);

    }

    @Test
    @DisplayName("Tester at spillet endrer grønn med flere like bokstaver")
    public void testCorrectGreenColor(){
        setupWordlegames();
        wordleGame1.setFasit("aaaaa");
        wordleGame1.guess("pasta");

        char actualColor1=wordleGame1.getPreviousGuesses().get(0).getLetterBox(1).getColor();
        char actualColor2=wordleGame1.getPreviousGuesses().get(0).getLetterBox(4).getColor();
        assertEquals(green, actualColor1);
        assertEquals(green, actualColor2);

        ;
    }

    @Test
    @DisplayName("Tester at fargen blir gul i avanserte tilfeller")
    public void testCorrectYellowColor() {
        setupWordlegames();
        //At bokstaven ikke blir gul hvis det er to av den i fasiten
        wordleGame1.setFasit("axxxa");
        wordleGame1.guess("pasta");

        char actualColor1=wordleGame1.getPreviousGuesses().get(0).getLetterBox(1).getColor();
        char actualColor2=wordleGame1.getPreviousGuesses().get(0).getLetterBox(4).getColor();
        assertEquals(yellow, actualColor1);
        assertEquals(green, actualColor2);

        //Motsatt og
        wordleGame2.setFasit("xaxax");
        wordleGame2.guess("pasta");

        char actualColor3=wordleGame2.getPreviousGuesses().get(0).getLetterBox(1).getColor();
        char actualColor4=wordleGame2.getPreviousGuesses().get(0).getLetterBox(4).getColor();
        assertEquals(green, actualColor3);
        assertEquals(yellow, actualColor4);

        wordleGame3.setFasit("aaxxx");
        wordleGame3.guess("pasta");

        char actualColor5=wordleGame3.getPreviousGuesses().get(0).getLetterBox(1).getColor();
        char actualColor6=wordleGame3.getPreviousGuesses().get(0).getLetterBox(4).getColor();
        assertEquals(green, actualColor5);
        assertEquals(yellow, actualColor6);

        wordleGame4.setFasit("kkxxk");
        wordleGame4.guess("klikk");

        char actualColor7=wordleGame4.getPreviousGuesses().get(0).getLetterBox(0).getColor();
        char actualColor8=wordleGame4.getPreviousGuesses().get(0).getLetterBox(4).getColor();
        char actualColor9=wordleGame4.getPreviousGuesses().get(0).getLetterBox(3).getColor();
        assertEquals(green, actualColor7, actualColor8);
        assertEquals(yellow, actualColor9);

        wordleGame5.setFasit("kaste");
        wordleGame5.guess("flikk");

        char actualColor10=wordleGame5.getPreviousGuesses().get(0).getLetterBox(3).getColor();
        char actualColor11=wordleGame5.getPreviousGuesses().get(0).getLetterBox(4).getColor();
        assertEquals(yellow, actualColor10);
        assertEquals(blank, actualColor11);
    }

    @Test
    @DisplayName("Tester spillet fungerer som det skal")
    public void testGameFunctionality(){
        setupWordlegames();
        wordleGame1.setFasit("kaste");
        for (int i = 0; i < 6; i++) {
            wordleGame1.guess("pasta");
        }
        assertFalse(wordleGame1.isWin(),
         "Skal tape når man ikke klarer å gjette ordet på allowedGuesses");
        wordleGame1.guess("kaste");
        assertFalse(wordleGame1.isWin(),
        "Kan ikke påvirke spillet etter allowedGuesses er oppbrukt, selv om man gjetter riktig");

        wordleGame2.setFasit("kaste");
        for (int i = 0; i < 5; i++) {
            wordleGame2.guess("pasta");
        }
        wordleGame2.guess("kaste");
        assertTrue(wordleGame2.isWin(),
         "Skal vinne hvis man gjetter riktig på eller under allowedGuesses");
        wordleGame2.guess("pasta");
        assertTrue(wordleGame2.isWin(),
        "Kan ikke påvirke spillet etter allowedGuesses er oppbrukt");

        wordleGame3.setFasit("pasta");
        wordleGame3.guess("pasta");
        assertTrue(wordleGame3.isWin(),
         "Skal vinne hvis man gjetter riktig på eller under allowedGuesses");

        wordleGame4.setFasit("pasta");
        wordleGame4.guess("pasta");
        assertTrue(wordleGame4.isWin());

        wordleGame4.guess("kaste");
        assertEquals(1, wordleGame4.getPreviousGuesses().size());
    }

    @Test
    public void validGuessCheck(){
        assertThrows(IllegalArgumentException.class, () -> {
			wordleGame1.guess("plen");;
		}, "Ikke gyldig gjett, ikke langt nok");
        assertEquals(6, wordleGame1.getAllowedGuesses(),"Et ugylig gjett skal ikke telle som et gjett");
        assertEquals(new ArrayList<>(), wordleGame1.getPreviousGuesses(),"Et ugylig gjett skal ikke telle som et gjett");

        wordleGame2.setCategory("slæng");
        wordleGame2.guess("wolla");
        assertThrows(IllegalArgumentException.class, () -> {
			wordleGame2.guess("kaste");;
		}, "Ikke et slæng ord");

        wordleGame3.setCategory("engelsk");
        wordleGame3.guess("fresh");
        assertThrows(IllegalArgumentException.class, () -> {
			wordleGame3.guess("kaste");;
		}, "Ikke et engelsk ord");

        wordleGame4.setCategory("norsk");
        wordleGame4.guess("kaste");
        assertThrows(IllegalArgumentException.class, () -> {
			wordleGame4.guess("crane");;
		}, "Ikke et norskt ord");


    }

    @Test
    @DisplayName("Sjekker at man ikke kan sette en fasit som ikke er et ord")
    public void setFasitTest(){
        assertThrows(IllegalArgumentException.class, () -> {
			wordleGame1.setFasit("xxxx");;;;
		}, "Ikke riktig lengde på ordet");

        assertThrows(IllegalArgumentException.class, () -> {
			wordleGame1.setFasit("");;;;
		}, "Ikke riktig lengde på ordet");

        assertThrows(IllegalArgumentException.class, () -> {
			wordleGame1.setFasit("chv..b");;;;
		}, "Ikke et gyldig ord");
    }






}
