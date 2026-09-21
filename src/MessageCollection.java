import lib.RobertCircularlyLinkedList;

public class MessageCollection {
    RobertCircularlyLinkedList<MessageThread> messageThreadList;

    public MessageCollection(){
        this.messageThreadList = new RobertCircularlyLinkedList<>();
    }

    void setMessageThreadList(RobertCircularlyLinkedList<MessageThread> messageThreadList){
        this.messageThreadList = messageThreadList;
    }

    RobertCircularlyLinkedList<MessageThread> getMessageThreadList(){
        return this.messageThreadList;
    }

    void addMessageThread(MessageThread messageThread){
        this.messageThreadList.add(messageThread);
    }

    void removeMessageThread(MessageThread messageThread){
        this.messageThreadList.remove(messageThread);
    }

    int getMessageThreadCount(){
        return this.messageThreadList.getSize();
    }
}
