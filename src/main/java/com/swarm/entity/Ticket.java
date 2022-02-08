package com.swarm.entity;

import com.swarm.states.TicketState;

public class Ticket {

    private TicketState state;
    private String id;
    private String name;

    public Ticket(String name){
        this.name = name;
    }

    public TicketState getState() {
        return state;
    }

    public void setState(TicketState state) {
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
}
