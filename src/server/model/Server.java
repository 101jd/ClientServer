package server.model;

import client.model.Client;
import server.view.SView;

import java.util.ArrayList;
import java.util.List;

/**
 * Не пишу на сервер историю сообщений из-за этических соображений.
 * По принципу аськи или джаббера.
 * История в файлах у клиентов.
 */

public class Server {
    private boolean isOn;
    private List<Client> clients;
    private SView view;
    private Authentificator authentificator;

    public Server(SView view){
        clients = new ArrayList<>();
        this.view = view;
        this.isOn = false;
        this.authentificator = new Authentificator(this);
    }

    public void connectUser(Client client){
        if (clients.add(client)) {
            authentificator.addName(client.getName());
            view.connectUser(client);
        }
    }

    public void disconnectUser(Client client){
        if (clients.remove(client)) {
            authentificator.removeName(client.getName());
            view.disconnectUser(client);
        }
    }

    public String start() {
        return "Server started.";
    }

    public String stop() {
        while (!clients.isEmpty()){
            Client client = clients.get(0);
            client.getMessage("Server offline");
            client.disconnect();
            clients.remove(client);
        }
        return "Server stoped";
    }

    public boolean broadcast(String message){
        if (clients.isEmpty())
            return false;
        for (Client client : clients){
            client.getMessage(message);
        }
        return true;
    }

    public boolean checkName(String name){
        for (Client client : clients){
            if (!authentificator.checkName(client.getName()))
                return true;
        }
        return false;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public Authentificator getAuthentificator() {
        return authentificator;
    }

    public boolean isOn() {
        return isOn;
    }
}
