import edu.duke.FileResource;

import java.io.File;
import java.util.ArrayList;

public class MacbethCharacterNames {
    private ArrayList<String> characterNames;
    private ArrayList<Integer> count;
    public MacbethCharacterNames(){
        characterNames = new ArrayList<String>();
        count = new ArrayList<Integer>();
    }
    public void update (String person){
        int index = characterNames.indexOf(person);
        if(index==-1){
            characterNames.add(person);
            count.add(1);
        }
        else{
            int value = count.get(index);
            count.set(index,value+1);
        }
    }
    public void findAllCharacters(){
        FileResource fr = new FileResource();
        for(String s:fr.words()){
            if(s.endsWith(".")){
                update(s);
            }
        }
    }
    public int findIndexOfMax(){
        int max=0;
        int maxindex=0;
        for(int k=0;k<characterNames.size();k++){
            if(count.get(k)> max){
                max=count.get(k);
                maxindex = k;
            }
        }
        return maxindex;
    }
    void characterWithNumParts(int num1,int num2){
        for(int k=0;k<characterNames.size();k++){
            if(count.get(k)>=num1 && count.get(k)<=num2) {
                System.out.println(characterNames.get(k) + " " + count.get(k));
            }
        }
    }
    public static void main(String args[]){
        MacbethCharacterNames a = new MacbethCharacterNames();
        a.findAllCharacters();
        for(int k=0;k<a.characterNames.size();k++){
            System.out.println(a.characterNames.get(k)+" "+a.count.get(k));
        }
        int maxindex = a.findIndexOfMax();
        System.out.println("THe word that occurs most often and its count are: "+a.characterNames.get(maxindex)+" "+a.count.get(maxindex));
        a.characterWithNumParts(10,15);
    }
    }

