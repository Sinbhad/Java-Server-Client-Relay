package lib;
/*
    Custom implementation of a Queue to fit basic needs of current program,
    works with the custom implementation of the linked list found in the program

    Author: Robert Poley
 */
public class CustomQueue<T> {
    private int size;
    private CustomCircularlyLinkedList<T> storage;

    public void add(T data){
        //messages get added to the end of the list
        storage.add(data);
        this.size++;
    }

    public T peek(){
        if(storage.getValAtIndex(0) == null){
            return null;
        }
        return storage.getValAtIndex(0);
    }

    public T poll(){
        /*
        the data in front is taken care of,
        the rest moves back
        */
        T value = peek();
        if(value != null){
            storage.removeAt(0);
            size--;
        }
        return value;
    }

    public Boolean find(T data){
        return storage.find(data);
    }

    public void insert(T data, int index){
        storage.addAtIndex(index, data);
    }

    public void printLine(){
        storage.printAll();
    }

    public int getSize(){return size;}
}