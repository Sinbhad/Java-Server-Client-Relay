package lib;

import java.io.IOException;
import java.io.ObjectOutputStream;

public interface Routable<T> {
    void receiveMessage(T message, T messageCollection);
    void sendMessage(String userName, String body, ObjectOutputStream outToClient) throws IOException;
}
