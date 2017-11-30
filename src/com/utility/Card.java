package com.utility;

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
}
