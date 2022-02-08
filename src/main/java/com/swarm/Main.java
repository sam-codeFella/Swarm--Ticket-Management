package com.swarm;

import com.swarm.util.TicketUtility;
import java.util.Scanner;

public class Main {

    private static int inputOption() {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Insert choice:");
            try {
                return input.nextInt();
            }
            catch (java.util.InputMismatchException e) {
                System.out.println("Please enter valid input format.. Only numbers");
                input.nextLine();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the Ticket State Management Console......");
        // there are a total of 5 states
        TicketUtility ticketUtility = new TicketUtility(); // i think this should be put in a function as well.
        ticketUtility.showOptions();

        int s = inputOption();
        boolean flag = true;

        while(flag){
            Scanner input = new Scanner(System.in);
            String name, id = null;
            switch(s){
                case 1:
                    System.out.println("Creating a new Ticket..");
                    System.out.println("Please enter the ticket Issue Name");
                    name = input.nextLine();
                    ticketUtility.addTicket(name);
                    break;

                case 2:
                    System.out.println("Setting a state of a Ticket");
                    System.out.println("Please enter ticket Id for more Information...");
                    id = input.nextLine();
                    ticketUtility.changeState(id);
                    break;

                case 3:
                    System.out.println("Describing an existing ticket");
                    System.out.println("Please enter ticket Id for more Information...");
                    id = input.nextLine();
                    ticketUtility.describeTicket(id);

                    break;

                case 4:
                    System.out.println("Options for state change for a ticket");
                    System.out.println("Please enter ticket Id for more Information...");
                    id = input.nextLine();
                    ticketUtility.showStateChangeOptions(id);
                    break;

                case 5:
                    System.out.println("List all existing tickets");
                    ticketUtility.listAllTickets();
                    break;

                case 6:
                    System.out.println("Exiting the application...");
                    flag = false;
                    break;

                default:
                    System.out.println("Wrong option.. Please try again !");
            }
            if(flag){
                System.out.println("********** ||||||||| **************");
                ticketUtility.showOptions();
                s = inputOption();
            }
        }

    }
}
