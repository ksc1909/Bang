package com.utility;

import java.util.ArrayList;
import java.util.List;

public class Player {
    public int number;
    public Hero hero;
    public Life life;
    public Role role;
    public Range shootingRange;
    public Range escapeRange;
    public List<Card> playerCards;

    public Player() {
        hero = Hero.Unknown;
        life = Life.Unknown;
        role = Role.Unknown;
        shootingRange = Range.Unknown;
        escapeRange = Range.Unknown;
        playerCards = new ArrayList<>();
    }
    public void print(){
        System.out.println("Player no: " + number + " Role: " +  role + " Hero: " + hero);
        System.out.println("========== CARDS ==========");
        for (int i = 0; i< playerCards.size(); i++)
        {
            playerCards.get(i).print();
        }
        System.out.println("======== END CARDS ========");
    }
}
