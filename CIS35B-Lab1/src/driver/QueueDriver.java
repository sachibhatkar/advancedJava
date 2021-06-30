package driver;
import model.QueueCustomer;
import java.util.LinkedList;
import java.util.Queue;

public class QueueDriver {
    public static void main(String[] args){

        LinkedList<QueueCustomer> queue = new LinkedList<QueueCustomer>();

        // populate queue with objects.
        queue.add(new QueueCustomer("Sachi"));
        queue.add(new QueueCustomer("Samika"));
        queue.add(new QueueCustomer("kylie"));

        printQueue(queue);

    }

    static void printQueue(LinkedList<QueueCustomer> queue){
        QueueCustomer qc = queue.poll();
        qc.hasBeenServed();

        for(QueueCustomer line: queue){
            line.hasBeenServed();
        }
    }
}
