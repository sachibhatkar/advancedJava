package model;

public class QueueCustomer {
    private Boolean served;
    private String name;

    public QueueCustomer(String name){
        this.name = name;
        served = false;
    }

    public void hasBeenServed(){
        served = true;
        System.out.println(name+" has been served");
    }


    // cmake sure obj mem# isn't printed.
    public String toString(){
        return name;
    }


}
