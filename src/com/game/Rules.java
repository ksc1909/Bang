package com.game;

import com.utility.*;

import java.util.*;

public class Rules {
    private final Config config = Config.getConfig();
    private DeckOfPlayingCards deck;
    private DeckOfRoleCards roles;

    private List<Player> players;
    private List<Hero> heroList = config.getHeroList();

    public Rules() {
            int noOfPlayers = 6;
            players  = new ArrayList<>();
            roles = new DeckOfRoleCards();


            roles.createRoleDeck(noOfPlayers);
            roles.shuffleRoleDeck();

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

            for(int i=0;i<noOfPlayers;i++){
                players.get(i).print();
            }


    }
}
