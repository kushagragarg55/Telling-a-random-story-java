import edu.duke.*;

import java.util.HashMap;

public class WordFrequencyUsingHashMap {
    HashMap<String,Integer> map;
    public WordFrequencyUsingHashMap(){
        map = new HashMap<String, Integer>();
    }
    public void findUnique(){
        FileResource resource = new FileResource();
        for(String s:resource.words()){
            s= s.toLowerCase();
            if(!map.containsKey(s)){
                map.put(s,1);
            }else{
                map.put(s,map.get(s)+1);
            }
        }
    }
    public void printWords(){
        for(String s:map.keySet()){
            if(map.get(s)>=500)
                System.out.println(map.get(s)+"  "+s);
        }
        System.out.println(map.size());
    }
    public static void main(String args[]){
        WordFrequencyUsingHashMap a = new WordFrequencyUsingHashMap();
        a.findUnique();
        a.printWords();
    }
}
