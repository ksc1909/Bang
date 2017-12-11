package com.online;

import com.enums.Event;
import com.utility.Player;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServerHandler extends Thread{
    Socket socket;
    Server server;
    Scanner in;
    PrintWriter out;
    String nick;

    public ServerHandler(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
        nick = "";
        open();
    }

    public void send(String msg){
            out.println(msg);
            out.flush();
    }

    public void open(){
        try {
            in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        System.out.println("Server Handler is running");
        while(true) {
            if(in.hasNext()) {
                String msg = in.nextLine();
                if(msg.contains(Event.CHAT_MESSAGE.toString())){
                    System.out.println(nick + ": " + msg.substring(13));
                    server.sendToAll(nick + ": " + msg.substring(13));
                }
                else if (msg.contains(Event.PLAYER_READY.toString())){
                    server.incrementReadyPlayers();

                    if (server.getReadyPlayers() == server.getNumberOfDeclaredPlayers())
                    {
                        System.out.println("All players ready!");
                        server.sendToAll(Event.GAME_START.toString());
                        server.setRules();
                    }
                    else if (server.getReadyPlayers() < server.getNumberOfDeclaredPlayers()){
                        System.out.println("Player is ready: " + nick );
                        server.sendToAllNumberOfReadyPlayers();
                    }
                }
                else if (msg.contains(Event.PLAYER_NICK.toString())){
                    nick =  msg.substring(12);
                    server.sendToAll("New player " + nick + " has connected!");
                    System.out.println("Player set nick: " + nick);
                }
                else if (msg.contains(Event.PLAYER_EXIT.toString())){
                    System.out.println("Player " + nick + " has exited!");
                    server.sendToAll("Player " + nick + " has left");
                    server.decrementReadyPlayers();
                    server.sendToAllNumberOfReadyPlayers();
                    server.removePlayer(this);
                }


            }

        }
    }
}
