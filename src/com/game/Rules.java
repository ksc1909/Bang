package com.game;

import com.enums.Hero;
import com.cards.*;
import com.utility.Config;
import com.utility.Player;

import java.util.*;

public class Rules {
    private final Config config = Config.getConfig();
    private DeckOfPlayingCards deck;
    private DeckOfRoleCards roles;

    private List<Player> players;
    private List<Hero> heroList = config.getHeroList();

    private com.visualization.Player playerBox;

    public Rules() {
            playerBox = new com.visualization.Player();
            int noOfPlayers = 6;
            players  = new ArrayList<>();
            roles = new DeckOfRoleCards();


            roles.createDeck(noOfPlayers);
            roles.shuffle();

            deck = new DeckOfPlayingCards();
            deck.createDeck();
            deck.shuffle();

            Player p;
            int x=0;
            for(int i = 0; i < noOfPlayers; i++){
                p = new Player();
                p.number = i;
                p.role = roles.getRoleList().get(i);
                p.hero = heroList.get(i);
                for(int j=0;j<5;j++,x++){
                    p.playerCards.add(deck.getCardList().get(x));
                  }
                players.add(p);
            }

            playerBox.addCards(players.get(0).playerCards);
            System.out.println("Add cards to panel successful");
            playerBox.updateInfo(players.get(0).role, players.get(0).hero);
            System.out.println("Update info successful");

            for(int i=0;i<noOfPlayers;i++){
                players.get(i).print();
            }


    }
}
