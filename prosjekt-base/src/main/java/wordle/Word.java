package wordle;

import java.util.ArrayList;



public class Word {
    private String word;

    private ArrayList<LetterBox> wordList = new ArrayList<>();

    public Word(String word){
        validateWord(word);
        for (Character letter : word.toCharArray()) {
            LetterBox tmp= new LetterBox(letter, 'B');
            wordList.add(tmp);
        }
        this.word=word;
        
    }

    public String getWord() {
        return word;
    }


    public void setWord(String word) {
        validateWord(word);
        this.word = word;
    }


    public ArrayList<LetterBox> getWordList() {
        return wordList;
    }


    

    public LetterBox getLetterBox(int i){
        if (i>5 & i<0) {
            throw new IllegalArgumentException("Ikke gylig indeks for en LetterBox");
        }
        LetterBox tmp=wordList.get(i);
        return tmp;
    }



    public static void validateWord(String word){
        //Hvis det ikke er i library
        if(!(word.length()==5)){
            throw new IllegalArgumentException("Ikke riktig lengde på ordet");
        }
        for (Character letter : word.toCharArray()) {
            if (!Character.isLetter(letter)) {
                throw new IllegalArgumentException("Ikke et gyldig ord");
            }
        }
    }
    
    
    @Override
    public String toString() {
        return  wordList+"\n";
    }

}
