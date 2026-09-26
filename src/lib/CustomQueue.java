package lib;

import messaging.Message;

/*
    Custom implementation of a Queue to fit basic needs of current program,
    works with the custom implementation of the linked list found in the program

    Author: Robert Poley
 */
@SuppressWarnings("unchecked")
public class CustomQueue<T> {
    private Message message;
    private int size;
    private CustomCircularlyLinkedList<Message> messages;

    public void add(T message){
        //messages get added to the end of the list
        messages.add((Message) message);
        size++;
    }

    public T peek(){
        if(messages.getValAtIndex(0) == null){
            return null;
        }
        return (T) messages.getValAtIndex(0);
    }

    public T poll(){
        /*
        the data in front is taken care of,
        the rest moves back
        */
        T value = peek();
        if(value != null){
            messages.removeAt(0);
            size--;
        }
        return value;
    }

    public Boolean find(Message message){
        if(!messages.find(message)){
            return false;
        }
        return true;
    }

    public void insert(Message message, int index){
        messages.addAtIndex(index, message);
    }

    public void printLine(){
        messages.printAll();
    }
}