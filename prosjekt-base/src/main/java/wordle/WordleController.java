package wordle;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;


public class WordleController {
    private WordleGame wordleGame;
    private List<Word>wordList;
    private String category;
    private  String style="";


    @FXML 
    private Button norskKategori, slængKategori, engelskKategori, submitGuess, restart, save, load, regelKnapp;

    @FXML 
    private TextField guessTextField;

    @FXML 
    private Label guessInfo, winText;

    @FXML 
    private ImageView pokalBilde;
    
    @FXML
    private GridPane wordGrid;
    

    @FXML
    private void handleGuess(){
        guessInfo.setText("");
        String guessWord=guessTextField.getText();
        try { 
            wordleGame.guess(guessWord);
        } catch (IllegalArgumentException e) {
            String guessInfoMessage=e.getMessage();
            wordleGame.setGuessInfoMessage(guessInfoMessage);
        }
        updateWordGrid();
        guessInfo.setText(wordleGame.getGuessInfoMessage());
        guessInfo.setStyle("-fx-font: 24 arial;");
        guessTextField.clear();
        if (wordleGame.isWin()) {
            winText.visibleProperty().set(true);
            pokalBilde.visibleProperty().set(true);
        }
        if (wordleGame.isGameOver()) {
            submitGuess.disableProperty().set(true);
            save.disableProperty().set(true);
            load.disableProperty().set(true);
        }

    }
    

    @FXML
    private void handleCategoryNorsk(){
        this.category="norsk";
        norskKategori.disableProperty().set(true);
        engelskKategori.disableProperty().set(true);
        slængKategori.disableProperty().set(true);
        submitGuess.disableProperty().set(false);
        save.disableProperty().set(false);
        wordleGame=new WordleGame(category);
    }
    @FXML
    private void handleCategoryEngelsk(){
        this.category="engelsk";
        norskKategori.disableProperty().set(true);
        engelskKategori.disableProperty().set(true);
        slængKategori.disableProperty().set(true);
        submitGuess.disableProperty().set(false);
        save.disableProperty().set(false);
        wordleGame=new WordleGame(category);
    }
    @FXML
    private void handleCategorySlæng(){
        this.category="slæng";
        norskKategori.disableProperty().set(true);
        engelskKategori.disableProperty().set(true);
        slængKategori.disableProperty().set(true);
        submitGuess.disableProperty().set(false);
        save.disableProperty().set(false);
        wordleGame=new WordleGame(category);
    }

    @FXML
    private void handleSave() throws IOException{
        FileWriter fileWriter=new FileWriter();
        fileWriter.writeToFile("textFileWordle", wordleGame);
    }

    @FXML
    private void handleLoadPerviuosGame() throws IOException{
        try {
            wordleGame=new WordleGame("norsk");
            FileWriter fileWriter=new FileWriter();
            fileWriter.readFromFile("textFileWordle", wordleGame);
            updateWordGrid();
            norskKategori.disableProperty().set(true);
            engelskKategori.disableProperty().set(true);
            slængKategori.disableProperty().set(true);
            guessInfo.setText("");
            submitGuess.disableProperty().set(false);
            save.disableProperty().set(false);
        } catch (FileNotFoundException e) {
            guessInfo.setText("Fant ikke filen");
        }
    }

    @FXML
    private void handleRestart(){
        save.disableProperty().set(true);
        load.disableProperty().set(false);
        winText.visibleProperty().set(false);
        submitGuess.disableProperty().set(true);
        pokalBilde.visibleProperty().set(false);

        for (Node node : wordGrid.getChildren()) {
            if (node instanceof Label) {
                ((Label)node).setText("");
                ((Label)node).getStyleClass().clear();
                ((Label)node).setStyle("-fx-background-color: null;");
            }
        }
        norskKategori.disableProperty().set(false);
        engelskKategori.disableProperty().set(false);
        slængKategori.disableProperty().set(false);
        guessInfo.setText("");

    }
    

    private void updateWordGrid(){
        wordList=wordleGame.getPreviousGuesses();
        for (int i = 0; i < wordList.size(); i++) {
            for (int j = 0; j < 5; j++) {
                Word word=wordList.get(i);
                LetterBox letterBox=word.getLetterBox(j);
                Label label = new Label(letterBox.getLetter().toString());
                style+="-fx-font: 24 arial;";
                setColorOnBackground(j, i, label);
                label.setStyle(style);
                style="";
                wordGrid.add(label, j, i);
                label.setMaxWidth(Double.MAX_VALUE);
                label.setMaxHeight(Double.MAX_VALUE);
                label.setAlignment(Pos.CENTER);
                //guessInfo.setText(wordleGame.getGuessInfoMessage());
            }
        }
    }


    private void setColorOnBackground(int j, int i, Label label){
        Character color=wordList.get(i).getLetterBox(j).getColor();
        if (color.equals('G')) {
            style+="-fx-background-color: limegreen;";
        }
        if (color.equals('Y')) {
            style+="-fx-background-color: yellow;";
        }
        if (color.equals('B')) {
            style+="-fx-background-color: white;";
        }
    }
}
