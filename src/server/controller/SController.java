package server.controller;

import server.model.Server;
import server.view.SView;

public class Controller {
    SView sView;
    Server server;
    public Controller(SView sView) {
        this.sView = sView;
        server = new Server(this.sView);
    }


    public String start() {
        return server.start();
    }

    public String stop() {
        return server.stop();
    }

    public void setOn(boolean b) {
        server.setOn(b);
    }
}
