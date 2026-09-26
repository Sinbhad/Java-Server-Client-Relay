package messaging;

import java.io.Serializable;

public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
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

    public String getUserName(){
        return userName;
    }

    void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
