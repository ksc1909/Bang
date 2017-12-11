package com.online;
import com.enums.Event;
import com.enums.Hero;
import com.enums.MessageDestination;
import com.enums.Role;
import com.game.Rules;
import com.visualization.Menu;
import com.visualization.Player;
import com.visualization.WaitingRoom;
import jdk.nashorn.internal.scripts.JO;

import javax.print.attribute.standard.Destination;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler extends Thread{

    Menu menu;
    WaitingRoom waitingRoom;
    private Client client;
    private Socket socket;
    private Scanner in;
    PrintWriter out;
    Scanner chat;
    boolean playerReady = false;

    public ClientHandler(Client client, Socket socket , Menu menu) {
    this.client = client;
    this.socket = socket;
    this.menu = menu;
        try {
            in = new Scanner(socket.getInputStream());
            chat = new Scanner(System.in);
            out = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.println("You connected to server" );
        waitForAllPlayers();
        while(true){
            String input = chat.nextLine();

            out.println(input);
            out.flush();

            if(in.hasNext())
                System.out.println(in.nextLine());
        }
    }
    private void waitForAllPlayers(){
        waitingRoom = new WaitingRoom();
        addActionListeners();
        String message;
        while(true){
            if (in.hasNext()){
                message = in.nextLine();
                handleMessage(message);
            }
        }
    }
    private void handleMessage(String message){
        if(message.contains(Event.MAX_PLAYERS_REACHED.toString())) {
            waitingRoom.setVisible(false);
            JOptionPane.showMessageDialog(waitingRoom,"Max number of players reached. Disconnecting.");
            waitingRoom.dispose();
        }
        else if (message.contains(Event.PLAYER_CONNECTED.toString())){
            waitingRoom.getChatArea().append("Player connected!" + "\n");
            waitingRoom.getChatArea().setCaretPosition(waitingRoom.getChatArea().getDocument().getLength());
        }
        else if(message.contains(Event.GAME_START.toString())){
            waitingRoom.getChatArea().append("Game is starting!" + "\n");
            //waitingRoom.dispose();
            //new Player(waitingRoom.getNicknameTextField().toString());
        }
        else if ( message.contains(Event.PLAYER_INFO.toString())){
            int playerNumber = Integer.valueOf(message.substring(20,21));
            int spaceEndIndex = message.indexOf(" ", 27);
            String role = message.substring(27, spaceEndIndex);
            int spaceEndIndex2 = message.indexOf(" ", spaceEndIndex + 6);
            spaceEndIndex +=6;
            String hero = message.substring(spaceEndIndex, spaceEndIndex2);
            com.utility.Player p = new com.utility.Player();

            System.out.println("Number: " + playerNumber);
            System.out.println("Index1: " + spaceEndIndex);
            System.out.println("Index2: " + spaceEndIndex2);
            p.number = playerNumber;
            System.out.println("Role: " + role);
            p.role = Role.valueOf(role);
            System.out.println("Hero: " + hero);
            p.hero = Hero.valueOf(hero);

            System.out.println("Number " + p.number + " Role: " + p.role + " Hero " + p.hero);


        }
        else {
            waitingRoom.getChatArea().append(message + "\n");
            waitingRoom.getChatArea().setCaretPosition(waitingRoom.getChatArea().getDocument().getLength());
        }
    }
    private void sendMessage(Event type, MessageDestination destination){
        switch (destination){
            case SERVER:
                System.out.println("Send message to server");
                out.println(type);
                out.flush();
                break;
            case ALL_PLAYERS:
                if(type == Event.CHAT_MESSAGE){
                    System.out.println("Send chat message to all players");
                    out.println(type + " " + waitingRoom.getInputMessage().getText());
                    out.flush();
                    waitingRoom.getInputMessage().setText("");
                }
                else if (type == Event.PLAYER_NICK){
                    System.out.println("Send player nick to all players");
                    out.println(type + " " + waitingRoom.getNicknameTextField().getText());
                    out.flush();
                }
                else if (type == Event.PLAYER_EXIT){
                    System.out.println("Send player exit to all players");
                    out.println(type);
                    out.flush();
                }
        }
    }
    private void addActionListeners(){
        waitingRoom.getReadyButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(waitingRoom.getReadyCheckBox().isSelected()) {
                    sendMessage(Event.PLAYER_READY, MessageDestination.SERVER);
                    waitingRoom.getReadyButton().setEnabled(false);
                }
            }
        });
        waitingRoom.getReadyCheckBox().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                playerReady = true;
                waitingRoom.getReadyCheckBox().setEnabled(false);
                waitingRoom.getReadyButton().setEnabled(true);
            }
        });
        waitingRoom.getInputMessage().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    sendMessage(Event.CHAT_MESSAGE, MessageDestination.ALL_PLAYERS);
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        waitingRoom.getNicknameTextField().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    String nick = waitingRoom.getNicknameTextField().getText();
                    if(nick.length() > 0 && nick.length() <=9 ) {
                        waitingRoom.setFieldsAfterNickname();
                        sendMessage(Event.PLAYER_NICK, MessageDestination.ALL_PLAYERS);
                        waitingRoom.getNickLabel().setText("Your nick: ");
                    }
                    else
                        JOptionPane.showMessageDialog(null,"Type your nick (1-9 charackters");
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        waitingRoom.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                sendMessage(Event.PLAYER_EXIT, MessageDestination.ALL_PLAYERS);
            }
        });
    }

    public WaitingRoom getWaitingRoom() {
        return waitingRoom;
    }
}
