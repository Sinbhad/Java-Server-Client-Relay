package messaging;

public class ConnectionMessage extends Message {
    private static final long serialVersionUID = 1L;
    String ipAddr;
    String userType;

    public ConnectionMessage(String userName, String message, String ipAddr, String userType){
        super(userName, message);
        this.ipAddr = ipAddr;
        this.userType = userType;
    }

    public void setIpAddr(String ipAddr){
        this.ipAddr = ipAddr;
    }

    public String getIpAddr(){
        return this.ipAddr;
    }

    public void setUserType(String userType){
        this.userType = userType;
    }

    public String getUserType(){
        return userType;
    }
}
