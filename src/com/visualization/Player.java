package com.visualization;

import com.cards.Card;
import com.enums.Hero;
import com.enums.Role;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Player extends JFrame implements ActionListener{
    private JLabel roleLabel;
    private JLabel heroLabel;
    private JLabel eqLabel;
    private JLabel cardLabel;
    private JPanel mainpanel;
    private JPanel cardPanel;
    private JLabel roleImgLabel;
    private JLabel heroImgLabel;

    public Player() {
        super("XD");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setSize(900,530);
        //this.setVisible(true);
        //this.setResizable(true);

        this.add(mainpanel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
    public void addCards(List<Card> cardList){
        JButton card;
        for(int i = 0; i < cardList.size(); i++){
            card = new JButton();
            card.setText(cardList.get(i).getAction().toString());
            cardPanel.add(card);
        }

    }
    public void updateCards(List<Card> cardList){

    }
    public void updateInfo(Role role, Hero hero){
        ImageIcon roleIcon;
        switch (role){
            case Sheriff:
                roleIcon = new ImageIcon("src/Images/szeryf.png");
                System.out.print("SH");
                break;
            case Renegade:
                roleIcon = new ImageIcon("src/Images/renegat.png");
                System.out.print("RE");
                break;
            case Deputy:
                roleIcon = new ImageIcon("src/Images/zastepca.png");
                System.out.print("DE");
                break;
            case Killer:
                roleIcon = new ImageIcon("src/Images/bandyta.png");
                System.out.print("BA");
                break;
                default:
                roleIcon = new ImageIcon("src/Images/szeryf.png");
                    System.out.print("NO");
                break;
        }
        roleImgLabel.setIcon(roleIcon);
       // roleLabel.setText("Your role: " + role);
        heroLabel.setText("Your hero: " + hero);
        eqLabel.setText("Your EQ: is empty ");
    }
}
