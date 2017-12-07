package com.cards;
import com.enums.Type;
import com.enums.Value;
import com.utility.Config;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeckOfPlayingCards {
    private final Config config = Config.getConfig();
    private List<Card> tempCardList;
    private List<Card> cardList;

    public void shuffle(){
        Collections.shuffle(cardList);
    }
    public void createDeck(){
        SecureRandom random = new SecureRandom();
        int index;

        // CREATE TYPES OF CARDS
        Card card;
        for(int i = 0; i < config.getMaxNoOfCards(); i++){
            card = new Card();
            if(i < 20) card.type = Type.Clubs;
            else if (i >= 20 && i < 40) card.type = Type.Diamonds;
            else if (i >= 40 && i < 60) card.type = Type.Hearts;
            else if (i >= 60) card.type = Type.Spades;

            card.value = Value.values()[i % 13];
            tempCardList.add(card);
        }

        // Create Types of cards
        for (int i = 0; i < 22; i++){
            for (int j = 0; j < config.getCardMap().get(i).value; j++){
                index = random.nextInt(tempCardList.size());
                card = tempCardList.get(index);
                card.action = config.getCardMap().get(i).action;

                cardList.add(card);
                tempCardList.remove(card);
            }
        }

    }
    public void printDeck(){
        for (int i = 0; i< cardList.size(); i++)
        {
            System.out.println(cardList.get(i).value + " " + cardList.get(i).type + " " + cardList.get(i).action );
        }
    }
    public DeckOfPlayingCards() {
        tempCardList = new ArrayList<>();
        cardList = new ArrayList<>();
    }

    public List<Card> getCardList() {
        return cardList;
    }
}
