import edu.duke.FileResource;
import edu.duke.URLResource;
import java.lang.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class GladLibsUsingHashMap {
    private HashMap<String,ArrayList<String>> mymap;
    private ArrayList<String> track;
    private Random myRandom;

    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "datalong";

    public GladLibsUsingHashMap(){
        mymap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
        track = new ArrayList<String>();
    }

    public GladLibsUsingHashMap(String source){
        mymap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(source);
        myRandom = new Random();
        track = new ArrayList<String>();
    }
    private void initializeFromSource(String source){
        String[] labels = {"country","noun","animal","adjective","name","color","timeframe","fruit","verb"};
        for(String s:labels) {
            ArrayList<String> list = readIt(source + "/" + s + ".txt");
            mymap.put(s,list);
        }
    }
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    private String getSubstitute(String label) {
        if(label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        return randomFrom(mymap.get(label));
    }

    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        int index = track.indexOf(sub);
        if(index==-1){
            track.add(sub);
            return prefix+sub+suffix;
        }
        else{
            return processWord(w);
        }

    }

    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }

    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }

    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }

    public static void main(String args[]){
        GladLibsUsingHashMap a = new GladLibsUsingHashMap();
        System.out.println("\n");
        String story = a.fromTemplate("datalong/madtemplate2.txt");
        a.printOut(story, 60);
        System.out.println();
        System.out.println("Number of replacements: "+a.track.size());
    }
}
