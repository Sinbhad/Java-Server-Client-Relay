package lib;

import java.io.ObjectOutputStream;

public interface Routable<T> {
    void receiveMessage(T message, T messageCollection);
    void sendMessage(T message, ObjectOutputStream outToClient);
}
