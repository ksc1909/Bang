package com.visualization;

import com.exceptions.ServerExistException;
import com.online.Client;
import com.online.Server;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame implements ActionListener{
    private JButton playButton;
    private JButton rulesButton;
    private JButton exitButton;
    private JPanel mainPanel;

    public Menu() {
        super("Bang!");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(400,370);
        this.setLocationRelativeTo(null);

        addActionListeners();
        this.add(mainPanel);
    }

    private void addActionListeners(){
        playButton.addActionListener(this);
        rulesButton.addActionListener(this);
        exitButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e){
    if (e.getSource() == playButton){
        this.setVisible(false);
        try {
            Server server = new Server();
            Object[] declaredNumberOfPlayers = {"4", "5","6","7"};
            String choose = (String) JOptionPane.showInputDialog(null,
                    "Select number of players", "Number of players",
                    JOptionPane.PLAIN_MESSAGE, null, declaredNumberOfPlayers,"4");
            int numberOfDeclaredPlayers = Integer.valueOf(choose);
            System.out.println("Declared players: " + numberOfDeclaredPlayers);
            server.setNumberOfDeclaredPlayers(numberOfDeclaredPlayers);
            server.start();
        }
        catch (ServerExistException ex)
        {
            new Client(this).start();
        } finally{
            this.dispose();

        }

    }
    else if (e.getSource() == rulesButton){
    }
    else if (e.getSource() == exitButton){
        dispose();
        System.exit(0);
    }
    }
}
