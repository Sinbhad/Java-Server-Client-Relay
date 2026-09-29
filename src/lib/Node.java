package lib;
public class Node<T> {
    private Node<T> prev;
    private Node<T> next;
    private T value;

    public Node(){
        this.prev = null;
        this.next = null;
        this.value = null;
    }

    public Node(T value){
        this.prev = null;
        this.next = null;
        this.value = value;
    }

    public void setNextNode(Node<T> next){
        this.next = next;
    }

    public Node<T> getNextNode(){
        return next;
    }

    public void setLastNode(Node<T> prev){
        this.prev = prev;
    }

    public Node<T> getLastNode(){
        return prev;
    }

    public void setNodeValue(T value){
        this.value = value;
    }

    public T getValue(){
        return value;
    }

    public Boolean checkIfNextExists(){
        return this.getNextNode() != null;
    }


    public Node<T> getLast() {
        Node<T> nextNode;
        Node<T> tempNode = this;
        while (tempNode.checkIfNextExists()) {
            nextNode = tempNode.getNextNode();
            tempNode = nextNode;
        }
        return tempNode;
    }
}