package client.model;

import client.view.CView;
import server.model.Authentificator;
import server.model.Server;

public class Client {
    private String name;
    private Server server;
    private CView view;
    private Authentificator authentificator;

    public Client(Server server, CView view){
        this.server = server;
        this.view = view;
        this.authentificator = server.getAuthentificator();
    }

    public String getName() {
        return name;
    }

    public void getMessage(String message) {
        view.getMessage(message);
    }

    public void setName(String name){

            this.name = name;

    }

    public boolean sendMessage(String message){
        return server.broadcast(message);
    }

    public boolean authorize(String name){
        return authentificator.checkName(name);

    }

//    public Handleable getHandler() {
//        return handler;
//    }

    public void disconnect(){
        this.view.disconnect(this);
    }
}
