package com.swarm.states.implementation;

import com.swarm.states.TicketState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestState implements TicketState {

    public final String stateName = "Test";
    private final List<String> availableStates = new ArrayList<>(
            Arrays.asList("Progress" , "Approval"));

    @Override
    public List<String> getAvailableStates() {
        return availableStates;
    }

    @Override
    public String getStateName() {
        return this.stateName;
    }
}
