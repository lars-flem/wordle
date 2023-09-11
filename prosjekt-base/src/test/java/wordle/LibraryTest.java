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

import wordle.Library;


public class LibraryTest {
    

    Library library=new Library();


    @Test
    @DisplayName("Tester om kategorien i konstroktøren blir validert")
    public void testConstructor(){
        Library library2=new Library("norsk");
        Library library3=new Library("engelsk");
        Library library4=new Library("slæng");
        Library library5=new Library("slæng");

        assertThrows(IllegalArgumentException.class, () -> {
			Library library6=new Library("kaalndweni");
		}, "Ikke en gyldig kategori");

        assertThrows(IllegalArgumentException.class, () -> {
			Library library7=new Library("");
		}, "Ikke en gyldig kategori");
    }

    @Test
    @DisplayName("Tester at fasitordene er i de ulike ordlistene")
    public void testWordList(){
        for (String word : library.getNorskList()) {
            assertTrue(library.getNorskOrdListe().contains(word.toUpperCase()));
        }
        for (String word : library.getEngelskList()) {
            assertTrue(library.getEngelskOrdListe().contains(word));
        }
    }
}
