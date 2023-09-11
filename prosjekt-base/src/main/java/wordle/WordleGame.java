package wordle;


import java.util.ArrayList;
import java.util.stream.Collectors;


public class WordleGame {
    private String fasit;
    private Word fasitList;
    private ArrayList<Word> previousGuesses=new ArrayList<>();
    private int allowedGuesses=6;
    private boolean gameOver=false;
    private boolean win=false;
    private int guesses;
    private String category;
    private String guessInfoMessage;
    
    
    public WordleGame(String category){
        this.fasit=selectWord(category);
        this.fasitList=new Word(fasit);
        this.category=category;
    }

    public void guess(String guess){
        guessInfoMessage="";
        guess=guess.toLowerCase();
        validateWord(guess);
        if (!gameOver) {
            Word guessWord=new Word(guess);
            guessWord=checkWord(guessWord);
            previousGuesses.add(guessWord);
            allowedGuesses-=1;
            guesses+=1;
            System.out.println(previousGuesses);
            System.out.println(fasit);
            if (fasit.equals(guess)) {
                win=true;
                gameOver=true;
                System.out.println("Gratulerer du vant!");
                guessInfoMessage="Gratulerer du vant!";
            }
            if(allowedGuesses==0){
                gameOver=true;
            }
            if (gameOver & !win) {
            System.out.println("Du tapte :( , ordet var "+fasit);
            guessInfoMessage="Du tapte :( , ordet var "+fasit;
            }
        }
        else{
            if (win) {
                System.out.println("Du vant! Du brukte "+guesses+" gjett.");
                guessInfoMessage="Du vant! Du brukte "+guesses+" gjett.";   
            }
            else{ 
                System.out.println("Du tapte");
                guessInfoMessage="Du tapte";
            }
        }
    }
    

    
    public boolean isWin() {
        return win;
    }


    private void validateWord(String word){
        Library library=new Library();
        Word.validateWord(word);
        if (category.equals("norsk")) {
            if (!library.getNorskOrdListe().contains(word.toUpperCase())) {
                throw new IllegalArgumentException("Ikke et norsk ord");
            } 
        }
        if (category.equals("engelsk")) {
            if (!library.getEngelskOrdListe().contains(word)) {
                throw new IllegalArgumentException("Ikke et engelsk ord");
            }            
        }
        if (category.equals("slæng")) {
            if (!library.getSlængCategory().contains(word)) {
                throw new IllegalArgumentException("Ikke et slæng ord");
            }            
        }
    }

    private Word checkWord(Word guessWord){
        for (int j = 0; j < 5; j++) {
            if (guessWord.getLetterBox(j).getLetter().equals(fasitList.getLetterBox(j).getLetter())){
                guessWord.getLetterBox(j).turnGreen();
                fasitList.getLetterBox(j).turnGreen();
            }              
        }
        for (int i = 0; i < 5; i++) {
            if(!fasitList.getLetterBox(i).isGreen()){
                for (int j = 0; j < 5; j++) {
                    if (fasitList.getLetterBox(j).getLetter()==guessWord.getLetterBox(i).getLetter() & !fasitList.getLetterBox(j).isGreen()) {
                        if (!guessWord.getLetterBox(i).isGreen() & 
                            allowedNumeberOfLettersToTurnYellow(i)>=numberOfLettersTurnedYellow(guessWord,guessWord.getLetterBox(i).getLetter())) {
                            guessWord.getLetterBox(i).turnYellow();
                        }
                    }
                }
            }
        }
        fasitList.getWordList().stream().forEach(x->x.turnBlank());
        return guessWord;
    }


    private int allowedNumeberOfLettersToTurnYellow(int i) {
        return fasitList.getWordList().stream().
            filter(letterBox->letterBox.getLetter()==i & !letterBox.isGreen()).
            collect(Collectors.toList()).size();
    }

    private int numberOfLettersTurnedYellow(Word word, char letter) {
        return word.getWordList().stream().
            filter(letterBox->letterBox.isYellow() & letterBox.getLetter().equals(letter)).
            collect(Collectors.toList()).size();
    }

    private String selectWord(String category){
        Library library=new Library(category);
        return library.selectWord(category);
    }


    public String getFasit() {
        return fasit;
    }


    public void setFasit(String fasit) {
        Word fasitWord=new Word(fasit);
        this.fasit = fasit;
        this.fasitList=new  Word(fasit);
    }


    public ArrayList<Word> getPreviousGuesses() {
        return previousGuesses;
    }


    public void setPreviousGuesses(ArrayList<Word> previousGuesses) {
        this.previousGuesses = previousGuesses;
    }

    public String getGuessInfoMessage() {
        return guessInfoMessage;
    }

    public void setGuessInfoMessage(String guessInfoMessage) {
        this.guessInfoMessage = guessInfoMessage;
    }

    public int getAllowedGuesses() {
        return allowedGuesses;
    }

    public void setAllowedGuesses(int allowedGuesses) {
        this.allowedGuesses = allowedGuesses;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
