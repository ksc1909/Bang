package com.online;

import com.visualization.Menu;

import java.io.*;
import java.net.Socket;

public class Client {
    Socket socket;
    ClientHandler clientHandler;
    Menu menu;

    public Client(Menu menu){
        this.menu = menu;
        System.out.println("Establishing connection...");
        try {
            socket = new Socket("localhost", 50464);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void start(){
            clientHandler = new ClientHandler(this,socket, menu);
            clientHandler.start();
    }

}