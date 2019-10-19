import java.util.*;
import edu.duke.*;
public class WordsWithArrays {
    StorageResource myWords;
    public WordsWithArrays(){
        myWords = new StorageResource();
    }
    public void readWords(){
        myWords.clear();
        FileResource resource = new FileResource();
        for(String word : resource.words()){
            myWords.add(word.toLowerCase());
        }
    }
    public boolean contains (String list[],String word){
        for (int k=0;k<list.length;k++){
            if(list[k].equals(word)){
                return true;
            }
        }
        return  false;
    }
    public int numberOfUniqueWords(){
        int numStored =0;
        String [] words = new String [myWords.size()];
        for(String s :myWords.data()){
            if(!contains(words,s)){
                words[numStored]=s;
                numStored++;
            }
        }
        return numStored;
    }
    public static void main(String args[]){
        WordsWithArrays a = new WordsWithArrays();
        a.readWords();
        System.out.println("number of words read: "+a.myWords.size());
        int unique = a.numberOfUniqueWords();
        System.out.println("number of unique word: "+unique);
    }
}
