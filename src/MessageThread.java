import lib.CustomCircularlyLinkedList;

public class MessageThread {
    String threadName;
    CustomCircularlyLinkedList<Message> messageList;
    int messageCount;

    public MessageThread(){
        this.threadName = "";
        this.messageList = new CustomCircularlyLinkedList<>();
        this.messageCount = 0;
    }

    public MessageThread(Message message){
        this.threadName = message.getUserName();
        this.messageList = new CustomCircularlyLinkedList<>();
        this.messageList.add(message);
        this.messageCount = messageList.getSize();
    }

    void setThreadName(String threadName){
        this.threadName = threadName;
    }

    String getThreadName(){
        return this.threadName;
    }

    void setMessageList(CustomCircularlyLinkedList<Message> messageList){
        this.messageList = messageList;
        this.messageCount = messageList.getSize();
    }

    CustomCircularlyLinkedList<Message> getMessageList(){
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
