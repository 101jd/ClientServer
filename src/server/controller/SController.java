package server.controller;

import server.model.Server;
import server.view.SView;

public class SController {
    private SView sView;
    private Server server;
    public SController(SView sView) {
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

    public Server getServer() {
        return server;
    }
}
