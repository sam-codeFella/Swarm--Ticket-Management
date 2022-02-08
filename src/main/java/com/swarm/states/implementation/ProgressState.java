package com.swarm.states.implementation;

import com.swarm.entity.Ticket;
import com.swarm.states.TicketState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProgressState implements TicketState {

    public final String stateName = "Progress";
    private final List<String> availableStates = new ArrayList<>(
            Arrays.asList("Test"));

    @Override
    public List<String> getAvailableStates() {
        return availableStates;
    }

    @Override
    public String getStateName() {
        return this.stateName;
    }
}
