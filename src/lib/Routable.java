package lib;

import java.io.ObjectOutputStream;

public interface Routable<T> {
    void receiveMessage();
    void sendMessage(T message, ObjectOutputStream outToClient);
}
