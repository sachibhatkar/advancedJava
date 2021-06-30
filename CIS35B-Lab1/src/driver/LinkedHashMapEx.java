package driver;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;

public class LinkedHashMapEx {
    public static void main(String[] args){
        LinkedHashMap<String,Integer> phonebook = new LinkedHashMap<>();
        phonebook.put("Sachi", 12345);
        phonebook.put("Samika", 2468);
        phonebook.put("apple", 13579);

        for(Map.Entry<String, Integer> entry: phonebook.entrySet()){
            System.out.println(entry.getKey()+" "+entry.getValue());
        }

        Set entrySet = phonebook.entrySet();
        Iterator it = entrySet.iterator();

        while(it.hasNext()){
            System.out.println(it.next());
        }

    }
}
