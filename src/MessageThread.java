import lib.RobertCircularlyLinkedList;

public class MessageThread {
    String threadName;
    RobertCircularlyLinkedList<Message> messageList;
    int messageCount;

    public MessageThread(){
        this.threadName = "";
        this.messageList = new RobertCircularlyLinkedList<>();
        this.messageCount = 0;
    }

    public MessageThread(String threadName, RobertCircularlyLinkedList<Message> messageList){
        this.threadName = threadName;
        this.messageList = messageList;
        this.messageCount = messageList.getSize();
    }

    void setThreadName(String threadName){
        this.threadName = threadName;
    }

    String getThreadName(){
        return this.threadName;
    }

    void setMessageList(RobertCircularlyLinkedList<Message> messageList){
        this.messageList = messageList;
        this.messageCount = messageList.getSize();
    }

    RobertCircularlyLinkedList<Message> getMessageList(){
        return this.messageList;
    }

    void setMessageCount(int messageCount){
        this.messageCount = messageCount;
    }

    int getMessageCount(){
        return this.messageCount;
    }

    void addMessage(Message message){
        this.messageList.add(message);
    }
}
