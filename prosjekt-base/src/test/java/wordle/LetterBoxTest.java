package wordle;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import wordle.LetterBox;


public class LetterBoxTest {
    private char green='G';
    private char yellow='Y';
    private char blank='B';
    private LetterBox letterBox1=new LetterBox('x', 'G');
    private LetterBox letterBox2=new LetterBox('x', 'Y');
    private LetterBox letterBox3=new LetterBox('x', 'B');

    



    @Test
    @DisplayName("Tester at konstruktøren innholder riktig validering")
    public void testConstructor(){

        assertEquals('X', letterBox1.getLetter());
        assertEquals('Y', letterBox2.getColor());

        assertThrows(IllegalArgumentException.class, () -> {
			LetterBox letterBox4=new LetterBox(',', 'g');
		}, "Skal ikke være mulig å sette boksatv som komma og en ugyldig farge");

        assertThrows(IllegalArgumentException.class, () -> {
			LetterBox letterBox5=new LetterBox('.', 'G');
		}, "Ikke en gyldig bokstav");

        assertThrows(IllegalArgumentException.class, () -> {
			LetterBox letterBox6=new LetterBox('k', 'g');
		}, "Ikke en gyldig farge");

        
    }

    @Test
    public void testGetColor(){
        LetterBox letterBox1=new LetterBox('x', 'G');
        Character tmp='Y';
        letterBox1.getColor().toUpperCase('Y');
        assertEquals('G', letterBox1.getColor());
    }

    //Kanskje teste flere getter og settere fordi de innholder validering.
    


}
