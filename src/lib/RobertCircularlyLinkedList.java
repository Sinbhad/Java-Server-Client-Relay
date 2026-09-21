package lib;

@SuppressWarnings("ALL")
/**
 * Custom generic implementation of a Circular Linked List
 */
public class RobertCircularlyLinkedList<T>{
    Node head;
    Node tail;

    /**
     * Retrieves the Node stored at the first memory address in the list
     * @return returns a node of any type
     */
    public Node <T> getHead(){
        return head;
    }

    /**
     * Breaks pointers to create a fresh list without making a new object
     */
    public void clear(){
        head = null;
        tail = null;
    }

    /**
     * Accepts a value of any type to be added to the list
     * @param value data of any type
     */
    public void add(T value) {
        //Temporary storage to pass the value along without breaking things
        Node newNode = new Node(value);

        //If the head is empty the data will be assigned as the head and tail to create the circle
        if (head == null) {
            head = newNode;
            tail = newNode;
            newNode.setNextNode(newNode);
            newNode.setLastNode(newNode);

        } else {
            //otherwise the head and tail will be updated to point to the new node
            tail.setNextNode(newNode);
            newNode.setLastNode(tail);

            newNode.setNextNode(head);
            head.setLastNode(newNode);

            //now the new node becomes the new tail
            tail = newNode;
        }
    }

    /**
     * Allows for a value to be added to the linked list at a given index as opposed to just adding to the end
     * @param index the address in which the value will be stored
     * @param value generic value that will be inserted in the list
     */
    public void addAtIndex(int index, T value) {
        int size = getSize();

        //Handle index out of bounds or adding beyond the end
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        //If adding to the end (or list is empty), use the existing add method
        if (index == size) {
            add(value);
            return;
        }


        //Placeholder for data assignment
        Node newNode = new Node(value);

        //Head case
        if (index == 0) {
            //Sets the new node in between the old head and tail
            newNode.setNextNode(head);
            newNode.setLastNode(tail);

            //Creates the new links from the old head and tail to the new head / tail
            head.setLastNode(newNode);
            tail.setNextNode(newNode);
            head = newNode;
        } else {
            //If the index is within range the following loop will move through the list until the desired index is reached
            Node currentNode = head;
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.getNextNode();
            }

            //Holder for old value
            Node prevNode = currentNode.getLastNode();

            //Wire up the new node to sit in between the old values
            newNode.setNextNode(currentNode);
            newNode.setLastNode(prevNode);
            prevNode.setNextNode(newNode);
            currentNode.setLastNode(newNode);
        }
    }

    /**
     * Method to print all values stored in the list
     */
    public void printAll(){
        if (head == null) return;
        Node tempNode = head;
        do{
            System.out.println(tempNode.getValue());
            tempNode = tempNode.getNextNode();
        }while(tempNode != head);
    }

    /**
     * Method to print all values stored in the list in reverse order
     */
    public void printReverse(){
        if (tail == null) return;
        Node tempNode = tail;
        do{
            System.out.println(tempNode.getValue());
            tempNode = tempNode.getLastNode();
        }while(tempNode != tail);
    }

    /**
     * Searches the list for a generic value at a given index and returns the datum
     * @param index the address to search
     * @return returns a generic value
     */
    public T getValAtIndex(int index){
        Node tempNode = head;
        for(int i = 0; i < index; i++){
            if(tempNode == null) return null;
            tempNode = tempNode.getNextNode();
        }
        return (T) tempNode.getValue();
    }

    /**
     * Searches the list for an instance of a node and returns the entire node as opposed to the value / data it holds
     * @param index the address to search
     * @return returns a Node type object from the list
     */
    public Node getNodeAtIndex(int index){
        Node tempNode = head;
        for(int i = 0; i < index; i++){
            if(tempNode == null) return null;
            tempNode = tempNode.getNextNode();
        }
        return tempNode;
    }


    /**
     * Allows for the removal of a node from any given index on the list
     * @param index the address in which the function will work on
     */
    public void removeAt(int index) {
        //Storage for size
        int size = getSize();

        //Base case, stop if any condition is met
        if (size == 0 || index < 0 || index >= size) return;

        Node tempNode = head;
        //Step through the list
        for (int i = 0; i < index; i++) {
            tempNode = tempNode.getNextNode();
        }

        //Edge case, if there is only one value, break any links
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            //Create the proper links
            Node prevNode = tempNode.getLastNode();
            Node nextNode = tempNode.getNextNode();

            prevNode.setNextNode(nextNode);
            nextNode.setLastNode(prevNode);

            if (tempNode == head) head = nextNode;
            if (tempNode == tail) tail = prevNode;
        }
    }

    /**
     * Searches for a given value using the Node's getNextNode() method to traverse the list
     * @param data generic data to be used in the search
     * @return boolean flag to alert user of search results
     */
    public boolean find(T data) {
        //Empty list, return
        if (head == null) return false;
        Node<T> currentNode = head;

        //Checks each nodes value against the target until it has been found or the head is reached
        do{
            if(currentNode.getValue().equals(data))
            {
                return true;
            }
            currentNode = currentNode.getNextNode();
        }while(currentNode != head);
        return false;
    }

    /**
     * Uses the logic from the find method to provde the location of a piece of data in the list
     * @param data generic data to be used in the search
     * @return returns the index of the found value
     */
    public int findIndex(T data){
        int index = 0;

        Node <T> currentNode = head;
        //Checks each nodes value against the target until it has been found or the head is reached
        do{
            if(currentNode.getValue().equals(data))
            {
                return index;
            }
            currentNode = currentNode.getNextNode();
            index++;
        }while(currentNode != head);

        return -1;
    }

    /**
     * Removes the first occurrence of a specific value from the list.
     * @param value The generic data to be removed
     */
    public void remove(T value) {
        if (head == null) return;

        Node<T> tempNode = head;
        boolean found = false;

        do {
            if (tempNode.getValue().equals(value)) {
                found = true;

                //Edge case, node in the list
                if (head == tail && head == tempNode) {
                    head = null;
                    tail = null;
                } else {
                    //Rewiring surrounding pointers
                    Node<T> prevNode = tempNode.getLastNode();
                    Node<T> nextNode = tempNode.getNextNode();

                    prevNode.setNextNode(nextNode);
                    nextNode.setLastNode(prevNode);

                    //Reassign head or tail if the removed node held those positions
                    if (tempNode == head) head = nextNode;
                    if (tempNode == tail) tail = prevNode;
                }
                break;
            }
            tempNode = tempNode.getNextNode();
        } while (tempNode != head);

        if (!found) {
            System.out.println("The given value '" + value + "' does not exist in the linked list\n\n");
        }
    }

    /**
     * Retrieves the amount of nodes stored in a given list
     * @return integer value of units stored
     */
    public int getSize(){
        if (head == null) return 0;
        Node tempNode = head;
        int size = 0;
        do{
            size++;
            tempNode = tempNode.getNextNode();
        }while(tempNode != head);
        return size;
    }

    /**
     * Retrieves the Node stored in the tail segment of the list
     * @return
     */
    public Node getLast(){
        return tail;
    }
}