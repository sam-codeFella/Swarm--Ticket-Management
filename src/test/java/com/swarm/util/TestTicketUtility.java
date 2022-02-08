package com.swarm.util;

import com.swarm.entity.Ticket;
import com.swarm.states.implementation.ApprovalState;
import com.swarm.states.implementation.OpenState;
import com.swarm.states.implementation.TestState;
import org.junit.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TestTicketUtility {

    private TicketUtility ticketUtility;
    private HashMap<String , String> idMap;

    private final List<String> availableStates_Open = new ArrayList<>(
            Arrays.asList("Progress"));

    private final List<String> availableStates_Approval = new ArrayList<>(
            Arrays.asList("Done" , "Open"));

    private final List<String> availableStates_Test = new ArrayList<>(
            Arrays.asList("Progress" , "Approval"));

    @Before
    public void setup(){
        ticketUtility = new TicketUtility();
        ticketUtility.addTicket("joe");
        ticketUtility.addTicket("harry");
        ticketUtility.addTicket("damir");
        //created 3 tickets.
        idMap = new HashMap<>();
    }

    public void setupState(){
        HashMap<String, Ticket>  ticketHashMap = ticketUtility.ticketHashMap;
        for(Ticket ticket : ticketHashMap.values()){
            if(ticket.getName().equalsIgnoreCase("joe")){
                ticket.setState(new TestState());
                idMap.put("joe",ticket.getId());
            } else if(ticket.getName().equalsIgnoreCase("harry")){
                ticket.setState(new ApprovalState());
                idMap.put("harry",ticket.getId());
            } else if (ticket.getName().equalsIgnoreCase("damir")) {
                ticket.setState(new OpenState());
                idMap.put("damir",ticket.getId());
            }
        }
    }


    @Test
    public void TestSize(){
        Assert.assertEquals(ticketUtility.ticketHashMap.size() , 3);
    }

    @Test
    public void TestStateChange(){
        setupState();
        boolean successFlag = true;
        HashMap<String, Ticket>  ticketHashMap = ticketUtility.ticketHashMap;
        //checking for joe.
        String joeId = idMap.get("joe"); //test state.
        List<String> availableStatesJoe = ticketHashMap.get(joeId).getState().getAvailableStates();
        for(String state : availableStatesJoe){
            if(!availableStates_Test.contains(state)){
                successFlag = false;
            }
        }

        //checking for harry
        String harryId = idMap.get("harry"); //test state.
        List<String> availableStatesHarry = ticketHashMap.get(harryId).getState().getAvailableStates();
        for(String state : availableStatesHarry){
            if(!availableStates_Approval.contains(state)){
                successFlag = false;
            }
        }

        //checking for damir
        String damirId = idMap.get("damir"); //test state.
        List<String> availableStatesDamir = ticketHashMap.get(damirId).getState().getAvailableStates();
        for(String state : availableStatesDamir){
            if(!availableStates_Open.contains(state)){
                successFlag = false;
            }
        }

        Assert.assertEquals(successFlag, true);
    }
}
