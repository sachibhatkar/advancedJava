package driver;

import java.util.HashMap;

public class HashMapExample {
    public static void main(String[] args){
        HashMap<String, Integer> phonebook = new HashMap<>();
        phonebook.put("Sachi", 12345);
        phonebook.put("Samika", 67891);
        phonebook.put("apple", 24768);

        if(phonebook.containsKey("Sachi")){
            phonebook.remove("Sachi");
        }

        System.out.println(phonebook);



    }
}
