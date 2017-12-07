package com.online;

import com.exceptions.MaxConnReachedException;
import com.exceptions.ServerExistException;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    List<ServerHandler> players;
    ServerSocket serverSocket;
    Socket connection;
    private int connectedPlayers = 0;
    private int readyPlayers = 0;
    private int numberOfDeclaredPlayers = 1;
    private boolean gameStarted = false;

    public Server() throws ServerExistException {
        players = new ArrayList<>();
        try {
            serverSocket = new ServerSocket(50464);
        } catch (IOException e) {
            throw new ServerExistException("Server already exists!");
        }

    }

    public synchronized void sendToAll(String input){
        for (ServerHandler handler: players) {
            handler.send(input);
        }
    }
    public synchronized void sendToAllNumberOfReadyPlayers() {
        for (ServerHandler handler : players) {
            handler.send("Number of ready players ( " + readyPlayers +
                    " ) of declared ( " + numberOfDeclaredPlayers + " )");
        }
    }
    public synchronized void incrementReadyPlayers(){
        readyPlayers++;
    }
    public synchronized void decrementReadyPlayers(){
        readyPlayers--;
    }
    public synchronized void startGame(){
        if (!gameStarted)
            gameStarted = true;
    }
    public int getReadyPlayers(){
        return readyPlayers;
    }
    public void removePlayer(ServerHandler serverHandler){
        players.remove(serverHandler);
        connectedPlayers--;
    }

    public int getNumberOfDeclaredPlayers() {
        return numberOfDeclaredPlayers;
    }

    public void setNumberOfDeclaredPlayers(int numberOfDeclaredPlayers) {
        this.numberOfDeclaredPlayers = numberOfDeclaredPlayers;
    }

    public void start() throws MaxConnReachedException  {
        System.out.println("Server is ON!");
        while (true){
            try {
                System.out.println("Server is waiting for next client, actual number of connected " +
                        "players is " + connectedPlayers);
                    connection = serverSocket.accept();
                if ( connectedPlayers < numberOfDeclaredPlayers){
                    connectedPlayers++;
                    System.out.println("Client connected from " + connection.getInetAddress().getHostName());
                    ServerHandler handler = new ServerHandler(connection, this);
                    players.add(handler);
                    Thread t = new Thread(handler);
                    t.start();
                }
                else
                {
                    System.out.println("Max number of players reached! number of connected players: " + connectedPlayers);
                    connection.close();
                    throw new MaxConnReachedException();
                }
          } catch (IOException e) {
              e.printStackTrace();
          }
      }
    }
}
