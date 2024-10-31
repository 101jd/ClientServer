package server.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Authentificator {
    private Set<String> names;
    private Server server;
    public Authentificator(Server server){
        this.server = server;
        names = new HashSet<>();
    }

    public void addName(String name){
        names.add(name);
    }

    public void removeName(String name){
        names.remove(name);
    }

    public boolean checkName(String name){
        boolean b = !names.contains(name);
        if (b)
            names.add(name);
        return b;
    }
}
