package com.swarm.util;

import com.swarm.entity.Ticket;
import com.swarm.states.implementation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TicketUtility {

    private static final Logger logger = LoggerFactory.getLogger(TicketUtility.class);

    public HashMap<String, Ticket> ticketHashMap = new HashMap<>();

    private String hashOfId(String input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : result) {
            sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }

    public void showOptions(){
        System.out.println("Here are the options....");
        System.out.println("1. Create a new Ticket ");
        System.out.println("2. Set State of a Ticket");
        System.out.println("3. Describe an existing ticket");
        System.out.println("4. Display state change options for a ticket");
        System.out.println("5. List all existing tickets");
        System.out.println("6. Exit the application");
    }

    public void showPossibleStates(Ticket ticket){
        List<String> possibleStates = ticket.getState().getAvailableStates();
        logger.info("Possible states are -->");
        for(String state : possibleStates){
            System.out.print("---");
            System.out.print(state);

        }
        System.out.println();
    }

    public void addTicket(String name){
        Ticket ticket = new Ticket(name);
        long currentTimestamp = System.currentTimeMillis();
        try {
            String id = hashOfId(name + String.valueOf(currentTimestamp)); 
            // reason for adding this logic is to make sure no 2 id's are the same.
            ticket.setId(id);
            ticketHashMap.put(id, ticket);
            ticket.setState(new OpenState());
            logger.info("The ticket is entered with state = Open and id = " + id);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public void showTicketDetails(Ticket ticket){
        System.out.println("*********************************************");
        System.out.println("Ticket state -->" + ticket.getState().getStateName());
        System.out.println("Ticket id    -->" + ticket.getId());
        System.out.println("Ticket name  -->" + ticket.getName());
        System.out.println("*********************************************");
    }

    public void describeTicket(String id){
        Ticket ticket = ticketHashMap.get(id);
        if(ticket == null){
            logger.info("No such ticket in the system with id -->" + id);
        }
        else{
            logger.info("The description of the ticket is...");
            showTicketDetails(ticket);
        }
    }

    public void transition(Ticket ticket , String result){
        if(result.equalsIgnoreCase("open")){
            ticket.setState(new OpenState());
        }
        else if(result.equalsIgnoreCase("progress")){
            ticket.setState(new ProgressState());
        }
        else if(result.equalsIgnoreCase("test")){
            ticket.setState(new TestState());
        }
        else if(result.equalsIgnoreCase("approval")){
            ticket.setState(new ApprovalState());
        }
        else if(result.equalsIgnoreCase("done")){
            ticket.setState(new DoneState());
        }
    }

    public void changeState(String id){
        Ticket ticket = ticketHashMap.get(id);
        Scanner change = new Scanner(System.in);
        if(ticket == null){
            logger.info("No such ticket in the system with id -->" + id);
        }
        else{
            logger.info("Ticket current state -->" + ticket.getState().getStateName());
            showPossibleStates(ticket);
            logger.info("Please enter state you would like to change into ?");
            String result = change.nextLine();
            if(!ticket.getState().getAvailableStates().contains(result)){ //won't this be object comparison , need a deep comparison here maybe.
               logger.info("Invalid state transition.");
               showPossibleStates(ticket);
            }
            else{
                logger.info("Valid state transition.");
                transition(ticket , result);
                logger.info("State of ticket changed");
            }

        }
    }

    public void showStateChangeOptions(String id){
        Ticket ticket = ticketHashMap.get(id);
        if(ticket == null){
            logger.info("No such ticket in the system with id -->" + id);
        }
        else{
            showPossibleStates(ticket);
        }
    }

    public void listAllTickets(){
        if(ticketHashMap.size() == 0){
            logger.info("No tickets present in the system");
        }
        for(Ticket ticket : ticketHashMap.values()){
            showTicketDetails(ticket);
        }
    }


}
