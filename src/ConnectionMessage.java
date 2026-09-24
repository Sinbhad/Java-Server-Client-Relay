import java.io.Serializable;

public class ConnectionMessage extends Message {
    private static final long serialVersionUID = 1L;
    String ipAddr;

    public ConnectionMessage(String userName, String message, String ipAddr){
        super(userName, message);
        this.ipAddr = ipAddr;
    }

    public void setIpAddr(String ipAddr){
        this.ipAddr = ipAddr;
    }

    public String getIpAddr(){
        return this.ipAddr;
    }
}
