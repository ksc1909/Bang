package com.game;

import com.enums.Event;
import com.enums.Hero;
import com.cards.*;
import com.enums.Role;
import com.online.ServerHandler;
import com.utility.Config;
import com.utility.Player;

import java.util.*;

public class Rules {
    private final Config config = Config.getConfig();
    private DeckOfPlayingCards deck;
    private DeckOfRoleCards roles;

    private List<Hero> heroList = config.getHeroList();
    private List<Card> cardList;
    private List<Player> playerList;


    public Rules(List<ServerHandler> players) {
            int noOfPlayers = players.size();
            roles = new DeckOfRoleCards();
            deck = new DeckOfPlayingCards();
            playerList = new ArrayList<>();

            roles.createDeck(noOfPlayers);
            roles.shuffle();

            deck.createDeck();
            deck.shuffle();

            int playerNumber = 0, cardNumber = 0;
            Role role;
            Hero hero;
            Player p;
            for(ServerHandler handler: players){
                p = new Player();
                cardList = new ArrayList<>();
                role = roles.getRoleList().get(playerNumber);
                hero = heroList.get(playerNumber);

                for(int j=0;j<5;j++,cardNumber++){
                    cardList.add(deck.getCardList().get(cardNumber));
                }

                p.number = playerNumber;
                p.role = role;
                p.hero = hero;
                p.playerCards = cardList;

                playerList.add(p);


                handler.send(Event.PLAYER_INFO + ": " + Event.NUMBER + " " + playerNumber + " " +
                                    Event.ROLE.toString() + " " + role + " " +
                                    Event.HERO + " " + hero + " " + Event.CARDS + " " + cardList);
                playerNumber++;
            }

            for(int i=0;i<noOfPlayers;i++){
                playerList.get(i).print();
            }


    }
}
