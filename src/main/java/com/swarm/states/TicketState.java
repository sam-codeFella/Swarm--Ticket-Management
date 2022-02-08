package com.swarm.states;

import java.util.List;

public interface TicketState {

    public String getStateName();

    public List<String> getAvailableStates();
}
