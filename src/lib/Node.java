package lib;
@SuppressWarnings({"unchecked", "rawtypes"})
public class Node<T> {
    private Node prev;
    private Node next;
    private Object value;

    public Node(){
        this.prev = null;
        this.next = null;
        this.value = "";
    }

    public Node(T value){
        this.prev = null;
        this.next = null;
        this.value = value;
    }

    public void setNextNode(Node next){
        this.next = next;
    }

    public Node getNextNode(){
        return next;
    }

    public void setLastNode(Node prev){
        this.prev = prev;
    }

    public Node getLastNode(){
        return prev;
    }

    public void setNodeValue(T value){
        this.value = value;
    }

    public T getValue(){
        return (T)value;
    }

    public Boolean checkIfNextExists(){
        return this.getNextNode() != null;
    }


    public Node getLast() {
        Node nextNode;
        Node tempNode = this;
        while (tempNode.checkIfNextExists()) {
            nextNode = tempNode.getNextNode();
            tempNode = nextNode;
        }
        return tempNode;
    }
}