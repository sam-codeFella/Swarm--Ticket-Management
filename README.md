# Swarm-Ticket-Management
Ticket State Management Console Application


** Design Considerations ** 

1. The consideration is as follows 
2. Entities -> Ticket and TicketState
3. Now TicketState can have multiple Implementations i.e Open , Progress , Test , Approval , Done.
4. Each implementation might have a seperate functionality w.r.t them. 
Hence we have created an interface which is part of the Ticket and is implemented by these States as Concrete classes. 
This allows us to encapsulate various state based behaviour in their respective implementations. 
In this method , we achieve seperation of concerns w.r.t to Ticket , TicketState along with it's implementations. 
By initialising the TicketState with the desired State , the behaviour of Ticket is also changed.

** Storage **

1. Use of HashMap reduces the indexing time to O(1) allowing for quick retrieval of tickets. 


** Functionalities ** 
1. Allows one to add possible state transitions in their concrete class in a simple manner while keeping the baseline principles in check. 
2. Allows for future enhancements as each state can be defined seperately. 
3. The id generated and stored in the HashMap is chosen as key. Why ? These keys are unique at all time. 
4. Id = SHA1(name + currentEpoch(second)) -> this will always resolve to a unique value even for the same ticket issue name because it's hash function includes the 
            time of creation as well. 
          This allows one to have multiple tickets with the same name but different States if the situation demands so. 
          


*** How to run *** 

1. Prerequistes -> Java 8 , Maven. 
1. Clone this repository
2. Run "mvn clean install"
3. Traverse to the "./target" directory and execute the jar -> Swarm-1.0-SNAPSHOT-jar-with-dependencies.jar
4. java -jar ./target/Swarm-1.0-SNAPSHOT-jar-with-dependencies.jar 
