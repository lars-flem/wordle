package wordle;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface iFileWriter {
    
    void writeToFile(String filename, WordleGame wordleGame);

    void readFromFile(String filename, WordleGame wordleGame)throws FileNotFoundException, IOException;
}
