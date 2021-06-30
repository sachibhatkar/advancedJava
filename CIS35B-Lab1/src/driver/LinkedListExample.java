package driver;

import java.util.LinkedList;

public class LinkedListExample {
    public static void main(String[] args){

        // create object of linked list class
        LinkedList <String> list = new LinkedList<String>();

        // add element
        list.add("Sachi");
        list.add("Samika");
        list.addLast("Apple");
        list.addFirst("Banana");
        list.add(2,"three");

        System.out.println(list);
        list.remove(3);
        System.out.println(list);

        for(String word: list){
            System.out.println(word);
        }

    }
}
