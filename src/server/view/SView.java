package server.view;

import client.model.Client;

import javax.swing.*;

public interface SView {
    void start();
    void stop();
    void connectUser(Client client);
    void disconnectUser(Client client);
}
