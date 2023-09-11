package wordle;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Library {
    private String norskStreng="kaste,banan,kaffe,pasta";
    private String engelskStreng="girth,chair,title,charm,happy,crane";
    private String slængStreng="jalla,whack,kæzer,tæppe,konge,wolla,walla,bæder,spenn,schpa,baite,stæsj,tæsje,facke,fucke,mordi,fardi,shank,mader,mæber,toern,jette,gerro,punge,getto,jacke,kibbe,kevin,drill";
    private ArrayList<String> engelskOrdListe=new ArrayList<>();
    private ArrayList<String> norskOrdListe=new ArrayList<>();
    

    private String category;

    private List<String> norskList = new ArrayList<String>(Arrays.asList(norskStreng.split(",")));
    private List<String> engelskList = new ArrayList<String>(Arrays.asList(engelskStreng.split(",")));
    private List<String> slængList = new ArrayList<String>(Arrays.asList(slængStreng.split(",")));


    public Library(){
        lagEngelskOrdliste();
        lagNorskOrdliste();
    }

    public  Library(String category){
        validateCategory(category);
        this.category=category;
        lagEngelskOrdliste();
        lagNorskOrdliste();
    }

    private List<String> getWordList() {
        if(category.equals("norsk")){
            return norskList;
        }
        if(category.equals("engelsk")){
            return engelskList;
        }
        if(category.equals("slæng")){
            return slængList;
        }
        return null;
    }

    private void lagEngelskOrdliste(){
        try {
            Scanner scanner=new Scanner(new File("engelskOrdListe.txt"));        
            while (scanner.hasNextLine()) {
            String line=scanner.nextLine();
            engelskOrdListe.add(line);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Fant ikke filen");
        }
    }

    private void lagNorskOrdliste(){
        try {
            Scanner scanner=new Scanner(new File("norskOrdListe.txt"));        
            while (scanner.hasNextLine()) {
            String line=scanner.nextLine();
            String[] lineList=line.split(" ");
            for (String string : lineList) {
                norskOrdListe.add(string);
            }

            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Fant ikke filen");
        }
    }
    
    
    public ArrayList<String> getEngelskOrdListe() {
        return engelskOrdListe;
    }
    public ArrayList<String> getNorskOrdListe() {
        return norskOrdListe;
    }

    private void validateCategory(String category) {
        if(!category.equals("norsk") & !category.equals("engelsk") & !category.equals("slæng")){
            throw new IllegalArgumentException("Ikke en gyldig kategori");
        }
    }

    public List<String> getSlængCategory(){
        return slængList;
    }

    public String selectWord(String category){
        List<String> tmp=this.getWordList();
        int index = new Random().nextInt(tmp.size());
        String word=tmp.get(index);
        return word.toLowerCase();
    }

    public List<String> getNorskList() {
        return norskList;
    }


    public List<String> getEngelskList() {
        return engelskList;
    }

}