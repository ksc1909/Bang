package com.utility;
import java.util.*;

public class Config {
    //Singleton
    private static Config config;

    //Cards
    private int maxNoOfSaloon = 1;
    private int maxNoOfRevCarabine = 1;
    private int maxNoOfGatling = 1;
    private int maxNoOfWellsFargo = 1;
    private int maxNoOfRemington = 1;
    private int maxNoOfWinchester = 1;
    private int maxNoOfLuneta = 1;
    private int maxNoOfDynamit = 1;
    private int maxNoOfIndianie = 2;
    private int maxNoOfDylizans = 2;
    private int maxNoOfVolcanic = 2;
    private int maxNoOfBarylka = 2;
    private int maxNoOfMustang = 2;
    private int maxNoOfSklep = 2;
    private int maxNoOfScofield = 3;
    private int maxNoOfWiezienie = 3;
    private int maxNoOfPojedynek = 3;
    private int maxNoOfKasiaBalou = 4;
    private int maxNoOfPanika = 4;
    private int maxNoOfPiwko = 6;
    private int maxNoOfPudlo = 12;
    private int maxNoOfBang = 25;

    //Game
    private int minNoOfPlayers = 4;
    private int maxNoOfPlayers = 7;
    private int maxNoOfCards = 80;


    private List<Tuple> cardMap = new ArrayList<>();
    private List<Hero> heroList = new ArrayList<>();


    public static Config getConfig() {
        if(config == null){
            config = new Config();
            return config;
        }
        else return config;
    }

    private Config() {

        cardMap.add(new Tuple(1, Action.Saloon));
        cardMap.add(new Tuple(1, Action.Rev_Carabine));
        cardMap.add(new Tuple(1, Action.Gatling));
        cardMap.add(new Tuple(1, Action.Wells_Fargo));
        cardMap.add(new Tuple(1, Action.Remington));
        cardMap.add(new Tuple(1, Action.Winchester));
        cardMap.add(new Tuple(1, Action.Luneta));
        cardMap.add(new Tuple(1, Action.Dynamit));

        cardMap.add(new Tuple(2, Action.Indianie));
        cardMap.add(new Tuple(2, Action.Dylizans));
        cardMap.add(new Tuple(2, Action.Volcanic));
        cardMap.add(new Tuple(2, Action.Barylka));
        cardMap.add(new Tuple(2, Action.Mustang));
        cardMap.add(new Tuple(2, Action.Sklep));

        cardMap.add(new Tuple(3, Action.Schofield));
        cardMap.add(new Tuple(3, Action.Wiezienie));
        cardMap.add(new Tuple(3, Action.Pojedynek));

        cardMap.add(new Tuple(4,Action.Kasia_Balou));
        cardMap.add(new Tuple(4, Action.Panika));

        cardMap.add(new Tuple(6, Action.Piwko));

        cardMap.add(new Tuple(12, Action.Pudlo));

        cardMap.add(new Tuple(25, Action.Bang));

        createHeroList();

    }

    private void createHeroList(){
        for(int i = 1; i <= 15; i++){
            heroList.add(Hero.values()[i]);
        }
    }

    public int getMaxNoOfSaloon() {
        return maxNoOfSaloon;
    }

    public int getMaxNoOfRevCarabine() {
        return maxNoOfRevCarabine;
    }

    public int getMaxNoOfGatling() {
        return maxNoOfGatling;
    }

    public int getMaxNoOfWellsFargo() {
        return maxNoOfWellsFargo;
    }

    public int getMaxNoOfRemington() {
        return maxNoOfRemington;
    }

    public int getMaxNoOfWinchester() {
        return maxNoOfWinchester;
    }

    public int getMaxNoOfLuneta() {
        return maxNoOfLuneta;
    }

    public int getMaxNoOfDynamit() {
        return maxNoOfDynamit;
    }

    public int getMaxNoOfIndianie() {
        return maxNoOfIndianie;
    }

    public int getMaxNoOfDylizans() {
        return maxNoOfDylizans;
    }

    public int getMaxNoOfVolcanic() {
        return maxNoOfVolcanic;
    }

    public int getMaxNoOfBarylka() {
        return maxNoOfBarylka;
    }

    public int getMaxNoOfMustang() {
        return maxNoOfMustang;
    }

    public int getMaxNoOfSklep() {
        return maxNoOfSklep;
    }

    public int getMaxNoOfScofield() {
        return maxNoOfScofield;
    }

    public int getMaxNoOfWiezienie() {
        return maxNoOfWiezienie;
    }

    public int getMaxNoOfPojedynek() {
        return maxNoOfPojedynek;
    }

    public int getMaxNoOfKasiaBalou() {
        return maxNoOfKasiaBalou;
    }

    public int getMaxNoOfPanika() {
        return maxNoOfPanika;
    }

    public int getMaxNoOfPiwko() {
        return maxNoOfPiwko;
    }

    public int getMaxNoOfPudlo() {
        return maxNoOfPudlo;
    }

    public int getMaxNoOfBang() {
        return maxNoOfBang;
    }

    public int getMinNoOfPlayers() {
        return minNoOfPlayers;
    }

    public int getMaxNoOfPlayers() {
        return maxNoOfPlayers;
    }

    public int getMaxNoOfCards() {
        return maxNoOfCards;
    }

    public List<Tuple> getCardMap() {
        return cardMap;
    }

    public List<Hero> getHeroList() {
        Collections.shuffle(heroList);
        return heroList;
    }
}
