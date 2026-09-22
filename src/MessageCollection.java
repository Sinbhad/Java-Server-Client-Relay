import lib.RobertCircularlyLinkedList;

public class MessageCollection {
    RobertCircularlyLinkedList<MessageThread> messageThreadList;
    Message message;

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

    boolean containsThreadName(String threadName){
        for(int i = 0; i < this.messageThreadList.getSize(); i++){
            MessageThread currentThread = this.messageThreadList.getValAtIndex(i);
            if(currentThread.getThreadName().equals(threadName)){
                return true;
            }
        }
        return false;
    }

    void addByUserName(Message message){
        for(int i = 0; i < messageThreadList.getSize(); i++){
            MessageThread currentThread = this.messageThreadList.getValAtIndex(i);
            if(currentThread.getThreadName().equals(message.getUserName())){
                currentThread.addMessage(message);
            }else{
                this.addMessageThread(new MessageThread(message));
            }
        }
    }
}
