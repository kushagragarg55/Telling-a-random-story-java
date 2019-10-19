import java.util.*;
import java.io.*;
import edu.duke.*;
import java.lang.*;

public class WordFrequencies {
    private ArrayList <String> mywords;
    private ArrayList <Integer> myFreqs;
    public WordFrequencies(){
        mywords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    public void findUnique(){
        FileResource resource = new FileResource();
        for(String s:resource.words()){
            s= s.toLowerCase();
            int index = mywords.indexOf(s);//to check if the word is already in the array list
            if(index==-1){
                mywords.add(s);
                myFreqs.add(1);
            }
            else{
                int value = myFreqs.get(index);
                myFreqs.set(index,value+1);
            }
        }
    }
    public int findIndexOfMax(){
        int max=0;
        int maxindex=0;
        for(int k=0;k<myFreqs.size();k++){
            if(myFreqs.get(k)> max){
                max=myFreqs.get(k);
                maxindex = k;
            }
        }
        return maxindex;
    }
    public static void main(String args[]){
        WordFrequencies a = new WordFrequencies();
        a.findUnique();
        System.out.println("#unique words: "+a.mywords.size());
        for(int k=0;k<a.mywords.size();k++){
            System.out.println(a.myFreqs.get(k)+"\t"+a.mywords.get(k));
        }
        int maxindex = a.findIndexOfMax();
        System.out.println("THe word that occurs most often and its count are: "+a.mywords.get(maxindex)+" "+a.myFreqs.get(maxindex));
    }


}
