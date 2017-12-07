package com.cards;

import com.enums.Role;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DeckOfRoleCards {
    private List<Role> roleList;

    public DeckOfRoleCards() {

    }

    public void createDeck (int noOfPlayers){

    switch (noOfPlayers) {
        case 4:
            roleList = Arrays.asList(Role.Sheriff, Role.Renegade, Role.Killer, Role.Killer);
            break;
        case 5:
            roleList = Arrays.asList(Role.Sheriff, Role.Renegade, Role.Killer, Role.Killer, Role.Deputy);
            break;
        case 6:
            roleList = Arrays.asList(Role.Sheriff, Role.Renegade, Role.Killer,
                    Role.Killer, Role.Killer, Role.Deputy);
            break;
        case 7:
            roleList = Arrays.asList(Role.Sheriff, Role.Renegade, Role.Killer,
                    Role.Killer, Role.Killer, Role.Deputy, Role.Deputy);
            break;
    }
    }
    public void shuffle (){
        Collections.shuffle(roleList);
    }

    public List<Role> getRoleList() {
        return roleList;
    }
}
