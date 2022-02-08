package com.swarm.states.implementation;

import com.swarm.entity.Ticket;
import com.swarm.states.TicketState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ApprovalState implements TicketState {

    public final String stateName = "Approval";
    private final List<String> availableStates = new ArrayList<>(
            Arrays.asList("Done" , "Open"));

    @Override
    public String getStateName() {
        return this.stateName;
    }

    @Override
    public List<String> getAvailableStates() {
        return availableStates;
    }


}
