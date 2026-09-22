package lib;

import java.io.ObjectOutputStream;

public interface Routable<T> {
    void receiveMessage(T message);
    void sendMessage(T message, ObjectOutputStream outToClient);
}
