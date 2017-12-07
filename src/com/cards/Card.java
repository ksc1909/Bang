package com.cards;

import com.enums.Action;
import com.enums.Type;
import com.enums.Value;

public class Card {
     Type type = Type.Unknown;
     Action action = Action.Unknown;
     Value value = Value.Unknown;

     public Card() {
          this.type = Type.Unknown;
          this.action = Action.Unknown;
          this.value = Value.Unknown;
     }
     public void print(){
          System.out.println("Value: " + value + " Type: " + type + " Action: " + action );
     }
     public String toString(){
          return (type + " " + value + " " + action);
     }

     public Action getAction() {
          return action;
     }
}
