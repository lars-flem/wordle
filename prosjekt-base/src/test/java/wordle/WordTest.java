package wordle;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import wordle.Word;

public class WordTest {
    

    @Test
    public void testConstructor(){
        Word word= new Word("halla");
        
        assertThrows(IllegalArgumentException.class, () -> {
			Word word2=new Word("d3iii");
		}, "Skal ikke være mulig å skrive tall");

        assertThrows(IllegalArgumentException.class, () -> {
			Word word2=new Word("diii");
		}, "For kort ord");

        assertThrows(IllegalArgumentException.class, () -> {
			Word word2=new Word("");
		}, "Skal ikke være mulig å skriv inn tomt ord");
    }





}
