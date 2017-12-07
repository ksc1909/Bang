package com.online;

import com.enums.Event;

import java.io.*;
import java.net.Socket;
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
                    if (server.getReadyPlayers() == server.getNumberOfDeclaredPlayers())
                    {
                        server.sendToAll("All players are ready. Starting the game!");
                        server.startGame();
                    }
                    else if (server.getReadyPlayers() < server.getNumberOfDeclaredPlayers()){
                        System.out.println("Player is ready: " + nick );
                        server.incrementReadyPlayers();
                        server.sendToAllNumberOfReadyPlayers();
                    }
                }
                else if (msg.contains(Event.PLAYER_NICK.toString())){
                    nick =  msg.substring(12);
                    System.out.println("New Player connected, nick: " + nick);
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
