package client.controller;

import client.model.Client;
import client.model.Handleable;
import client.view.CView;
import server.model.Server;

public class CController {
    private Client client;
    private CView view;

    public CController(Server server, CView view) {
        this.view = view;
        this.client = new Client(server, this.view);

    }

    public void setName(String name) {
        client.setName(name);
    }

    public Client getClient() {
        return client;
    }

    public void sendMessage(String message){
        client.sendMessage(message);
    }

    public boolean authorize(String name){
        return client.authorize(name);
    }
}
