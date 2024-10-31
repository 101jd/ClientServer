package client.view;

import client.controller.CController;
import client.model.Client;
import client.model.FileHandler;
import client.model.Handleable;
import server.model.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class CGUI extends JFrame implements CView {

    private final int HEIGHT = 640;
    private final int WIDTH = 480;
    private final int POSX = 400;
    private final int POSY = 200;

    private CController controller;
    private Server server;
    private boolean isConnected;
    private boolean isAuth;

    private Handleable handler;

    private JTextArea center;
    private JTextField messagefld;
    private Container container;
    private GridLayout gridLayout;
    private JTextField namefld;
    private JButton sendbtn;
    private JButton authbtn;
    private JButton connectbtn;
    private JButton disconnectbtn;
    private JPanel north;
    private JPanel south;
    private JPanel bottom;

    public CGUI(Server server){
        super("Client");

        isConnected = false;
        isAuth = false;

        this.server = server;

        this.controller = new CController(server, this);
        this.handler = new FileHandler();


        setBounds(POSX, POSY, WIDTH, HEIGHT);
        setVisible(true);

        center = new JTextArea();
        namefld = new JTextField("Your name");
        messagefld = new JTextField();
        sendbtn = new JButton("Send");
        authbtn = new JButton("Auth");
        connectbtn = new JButton("Connect");
        disconnectbtn = new JButton("Disconnect");

        messagefld = new JTextField();
        gridLayout = new GridLayout(2, 1);

        container = getContentPane();

        north = new JPanel(new GridLayout(1, 2));
        north.add("Your name: ", namefld);
        north.add(authbtn);

        container.add(north, BorderLayout.NORTH);

        container.add(center, BorderLayout.CENTER);

        south = new JPanel(new GridLayout(2, 1));

        JPanel top = new JPanel(new GridLayout(1, 2));
        bottom = new JPanel(new GridLayout(1,1));

        top.add(messagefld);
        top.add(sendbtn);

        bottom.add(connectbtn);

        south.add(top);
        south.add(bottom);

        container.add(south, BorderLayout.SOUTH);

        sendbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = messagefld.getText();
                if (message != null)
                    sendMessage(message);
            }
        });

        authbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authorize(namefld.getText());
            }
        });

        connectbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connect(controller.getClient());
            }
        });

        disconnectbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                disconnect(controller.getClient());
            }
        });
    }

    @Override
    public void getMessage(String message) {
        if (isConnected){
            this.center.append(message + System.lineSeparator());
            this.handler.write(message + System.lineSeparator());
        }

    }

    @Override
    public void sendMessage(String message) {
        if (isConnected && server.isOn()){
            StringBuilder sb = new StringBuilder();
            sb.append(this.controller.getClient().getName())
                            .append(": ")
                                    .append(message);
            controller.sendMessage(sb.toString());
            this.messagefld.setText("");
        } else this.center.append("Connection failed" + System.lineSeparator());
    }

    @Override
    public void authorize(String name) {
        if (controller.authorize(name)) {
            controller.setName(name);
            isAuth = true;
            center.append("Your name is " + name + System.lineSeparator());
            this.handler.setPath(name);
            center.append(getHistory() + System.lineSeparator());
        }
        else {
            center.append("Name is busy" + System.lineSeparator());
            isAuth = false;
        }
    }

    @Override
    public void disconnect(Client client) {
        if (server.isOn()) {
            if (isConnected) {
                server.disconnectUser(client);
                this.center.append("Disconnected from server" + System.lineSeparator());
                this.isConnected = false;
                this.bottom.remove(disconnectbtn);
                this.bottom.add(connectbtn);
            }
        } else this.center.append("Server is off" + System.lineSeparator());
    }

    @Override
    public void connect(Client client) {
        if (server.isOn()) {
            if (!isConnected && isAuth) {
                server.connectUser(client);
                this.center.append("Connected to server" + System.lineSeparator());
                this.isConnected = true;
                this.bottom.remove(connectbtn);
                this.bottom.add(disconnectbtn);
            } else this.center.append("Failed auth" + System.lineSeparator());
        } else this.center.append("Server is off" + System.lineSeparator());
    }

    private String getHistory(){
        try {
            return this.handler.read();
        } catch (IOException e){
            return "History is clear" + System.lineSeparator();
        }
    }

}
