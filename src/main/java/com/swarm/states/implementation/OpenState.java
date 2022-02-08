package com.swarm.states.implementation;

import com.swarm.entity.Ticket;
import com.swarm.states.TicketState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OpenState implements TicketState {

    private final String stateName = "Open";
    private final List<String> availableStates = new ArrayList<>(
            Arrays.asList("Progress"));

    @Override
    public List<String> getAvailableStates() {
        return availableStates;
    }

    @Override
    public String getStateName() {
        return this.stateName;
    }
}
