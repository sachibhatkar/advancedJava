package driver;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;


public class ListTest {
    public static void main(String[] args){
        LinkedListTest llt = new LinkedListTest();
        llt.linkedListEx();

        TestArrayList tal = new TestArrayList();
        tal.arrayList();

    }

}
 class LinkedListTest{
    protected void linkedListEx(){
        // create queue object from linkedlist class
        LinkedList queue = new LinkedList();
        // add elements sachi
        queue.addFirst("sachi");
        // make samika first element
        queue.addFirst("samika");
        // print queue
        System.out.println(queue);
        // remove element samika
        queue.remove(0);
        System.out.println(queue);

    }
 }

 class TestArrayList{
    protected void arrayList(){
        // create List object from list interface; list interface contains ArrayList Class
        List list = new ArrayList();
        // add elements into list
        list.add("apple");
        list.add("banana");
        // Print list
        System.out.println(list);
        // get first element from list
        System.out.println(list.get(0));
    }
 }
