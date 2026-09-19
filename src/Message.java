public class Message {
    String userName;
    String message;

    public Message(){
        userName = "";
        message = "";
    }

    public Message(String userName, String message){
        this.userName = userName;
        this. message = message;
    }

    void setUserName(String userName){
        this.userName = userName;
    }

    String getUserName(){
        return userName;
    }

    void setMessage(String message){
        this.message = message;
    }

    String getMessage(){
        return message;
    }
}
