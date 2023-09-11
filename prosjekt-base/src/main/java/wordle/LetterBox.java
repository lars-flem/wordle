package wordle;

import java.util.ArrayList;
import java.util.Arrays;

public class LetterBox {
    private Character color;
    private Character letter;


    public LetterBox(Character letter, Character color){
        if(!validatColor(color)){
            throw new IllegalArgumentException("Ikke en gyldig farge");
        }
        if(!validateLetter(letter)){
            throw new IllegalArgumentException("Ikke en gyldig bokstav");
        }
        letter=Character.toUpperCase(letter);
        this.letter=letter;
        this.color=color;
        

    }


    private boolean validateLetter(char letter){
        if (Character.isLetter(letter)) return true;     
        else return false;
    }


    private boolean validatColor(char color){
        ArrayList<Character> allowedColors = new ArrayList<Character>( Arrays. asList( 'B','G','Y') );
        if (allowedColors.contains(color)) return true;
        else return false;
    }

    public Character getColor() { 
        Character copy=color;
        return copy;
    }


    public void setColor(Character color) {
        if (!validatColor(color)) {
            throw new IllegalArgumentException("Ikke en gyldig farge");
        }
        this.color = color;


    }

    public void turnGreen(){
        this.color='G';
    }
    
    public void turnYellow(){
        this.color='Y';
    }

    public void turnBlank(){
        this.color='B';
    }
    public boolean isGreen(){ 
        if(color.equals('G')) return true;
        else return false;
    }
    
    public boolean isYellow(){ 
        if(color.equals('Y')) return true;
        else return false;
    }

    public Character getLetter() {
        return letter;
    }


    public void setLetter(Character letter) { 
        if(!validateLetter(letter)){
            throw new IllegalArgumentException("Ikke en gyldig bokstav");
        }
        this.letter = letter;
    }


    @Override
    public String toString() {
        return "["+letter+","+color+"]";
    }
    

}
