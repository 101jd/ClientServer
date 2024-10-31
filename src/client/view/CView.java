package client.view;

import client.model.Client;

import javax.swing.*;

public interface CView {
    void getMessage(String message);
    void sendMessage(String message);
    void authorize(String name);
    void disconnect(Client client);
    void connect(Client client);
}
