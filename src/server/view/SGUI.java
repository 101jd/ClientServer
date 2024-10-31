package server.view;

import client.model.Client;
import server.controller.SController;
import server.model.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SGUI extends JFrame implements SView {
    private final int HEIGHT = 640;
    private final int WIDTH = 480;
    private final int POSX = 300;
    private final int POSY = 200;

    private Container container;

    private JButton startbtn;
    private JButton stopbtn;

    private JTextArea center;

    private SController controller;
    public SGUI(){
        super("Server");
        controller = new SController(this);

        setBounds(POSX, POSY, WIDTH, HEIGHT);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        center = new JTextArea();
        container = getContentPane();
        container.setBounds(this.getBounds());
        container.add(center);

        startbtn = new JButton("Start");
        stopbtn = new JButton("Stop");



        startbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start();
            }
        });
        stopbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stop();
            }
        });

        container.add(startbtn, BorderLayout.SOUTH);
    }

    @Override
    public void start() {

        controller.setOn(true);
        this.center.append(controller.start() + System.lineSeparator());

        this.container.remove(startbtn);
        this.container.add(stopbtn, BorderLayout.SOUTH);
    }

    @Override
    public void stop() {
        controller.setOn(false);
        this.center.append(controller.stop() + System.lineSeparator());

        this.container.remove(stopbtn);
        this.container.add(startbtn, BorderLayout.SOUTH);
    }

    public JTextArea getCenter() {
        return center;
    }

    @Override
    public void connectUser(Client client) {
        this.center.append("User " + client.getName() + " connected" + System.lineSeparator());
    }

    @Override
    public void disconnectUser(Client client) {
        this.center.append("User " + client.getName() + " disconnected" + System.lineSeparator());
    }

    public Server getServer(){
        return controller.getServer();
    }
}
