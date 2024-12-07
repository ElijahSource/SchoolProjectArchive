//Elijah ******, id give myself 90/100 because ive followed the rubric as closely as possible everything runs correctly as instructed however i am turning this in late so i should be deducted the 10%
import java.util.*;
public class AirplaneBlank
{
}
class Person{
	protected String firstName;
	protected String lastName;
	protected String ticketID;
	
	//constructor
	public Person(String firstName, String lastName, String ticketID){
		this.firstName = firstName;
		this.lastName = lastName;
		this.ticketID = ticketID;
	}
	
	//setters 
	public void setFirst(String name){
		firstName = name;
	}
	public void setLast(String last){
		lastName = last;
	}
	public void setTicketID(String TicketID){
		ticketID = TicketID;
	}
	//getters
	public String getFirst(){
		return firstName;
	}
	public String getLast(){
		return lastName;
	}
	public String getTicketID(){
		return ticketID;
	}
	//to string
	public String toString(){
		String s = "\nfirst name:" + getFirst() + "\nlast name:" + getLast() + "\nticket id:" +getTicketID();
		return s;
	}
	public boolean equals(Object o){
		if(o instanceof Person){
			Person p = (Person)o;
			return this.lastName.equalsIgnoreCase(p.lastName);
		}
		return false;
	}
}

//passenger class
class Passenger extends Person{
	protected int seatNumber;
	protected String classType;
	
	//constructor
	public Passenger(String firstName, String lastName, String ticketID, int seatNumber, String classType){
		super(firstName, lastName, ticketID);
		this.seatNumber = seatNumber;
		this.classType = classType;
	}
	//setters
	public void setSeatNumber(int num){
		seatNumber = num;
	}
	public void setClass(String classT){
		classType = classT;
	}
	
	//getters
	public String getClassType(){
		return classType;
	}
	public int getSeatNumber(){
		return seatNumber;
	}
	//tostring
	public String toString(){
		String s = super.toString() + "\nSeat number:"+ String.valueOf(getSeatNumber()) +"\nClass type:"+ getClassType();
		return s;
	}
}

//interface
interface list {   
		public boolean add(Object o);
		public Object search(Object o);
		public boolean delete(Object o);
		public void printLast();
		public void takeOff();
	}

//airplane class
class Airplane implements list{
	//instance variables
	  public static int count = 0;
      private Passenger[] plane;
      public boolean takenOff;
      public int planeNum;
     
   //constructor
   public Airplane(int planeNum){
	   this.planeNum = planeNum;
	   plane = new Passenger[10];   
   }
   public int getPlaneNumber(){
	   return planeNum;
   }
   public void setPlaneNumber(int num){
	   planeNum = num;
   }
   public static int getCount(){
	   return count;
   }
   
	public boolean add(Object o) {
		if(o instanceof Passenger)
		{
			plane[count] = (Passenger)o;
			count++;
			return true;
		}
		return false;
	}

	public Object search(Object o) {
		boolean b = o instanceof String; 
		 if(!b) 
		 return null;
		 String name = (String)o; 
		 for(int i = 0; i < plane.length; i++)
		 {
		  if(plane[i]!= null && plane[i].getLast().equalsIgnoreCase(name))
		  { return plane[i];   //returning the found object } 
		 }
		}
		return null;//returning null if the object is not found
		}

	public boolean delete(Object o) {
		if(o instanceof String){
			String id = (String)o;
			for(int i = 0; i< count;i++){
				if(plane[i].getTicketID().equalsIgnoreCase(id)){
					plane[i]=null;
					return true;
				}
			}
		}
		return false;
	}

	public void printLast() {
		int i = 0;
		while(i < count){
			System.out.println(plane[i].getLast());
			i++;
		}
	}
	   public String toString(){ 
		   for(int i = 0; i< count; i++){
			   	System.out.println(plane[i].toString());
		   }
		return "";
		   }

	public void takeOff() {
		takenOff = true;
		//complete this method
	}
	
}

//Shell Driver
class Driver {
	public static void main(String[]args) {
		Scanner in = new Scanner(System.in);
		
		Airplane plane = new Airplane(20394);
		Passenger p1 = new Passenger("Bobby", "Smith", "123456789", 1, "First class");
		Passenger p2 = new Passenger("Johnny", "Apple", "987654321", 8, "Business class");
		Passenger p3 = new Passenger("Tom", "Jerry", "567123489", 32, "Economy class");
		Passenger p4 = new Passenger("Candy", "Cruz", "982134567", 15, "Premium Economy class");
		Passenger p5 = new Passenger("Kaloti", "Aaron", "762134589", 5, "First class");
		
		plane.add(p1);
		plane.add(p2);
		plane.add(p3);
		plane.add(p4);
		plane.takeOff();
		plane.add(p5);
		
		System.out.println("Here is the list of the passengers in this plane");
		System.out.println(plane + "\n");
		
		System.out.println("Testing the printLast method to display the last names");
		plane.printLast(); //prints the last name of the passenger sin the train
		System.out.println();
		   
		System.out.println("Testing the static method getCount");
		System.out.println("This plane has " + plane.getCount() + " Passengers\n");
		   
		System.out.print("Enter the last name of the passenger: ");
		String lastName = in.nextLine();
		System.out.println(plane.search(lastName));
		System.out.println();
		   
		System.out.println("Testing the delete method");
		System.out.print("Enter the last name of the passenger: ");
		String last = in.nextLine();
		plane.delete(last);
		System.out.println("Passenger " + last + " has been removed from the list\n");
		   
		System.out.println("Here is the updated list");
		System.out.println(plane);
		
	}
}

//My Driver
class MyDriver
{
   public static void main(String[] args)
   {
	   Scanner in = new Scanner(System.in);
	   
	   //creates passengers
	   Airplane plane2 = new Airplane(40002);
	   Passenger dad = new Passenger("Johnathan", "Jones", "123456785", 3, "Business class");
	   Passenger uncle = new Passenger("Timothy", "Vang", "123456786", 13, "Business class");
	   Passenger mom = new Passenger("Kelly", "Anne", "123456787", 32, "First class");
	   Passenger sister = new Passenger("Jessica", "Thorne", "123456788", 41, "First class");
	   Passenger brother = new Passenger("David", "Barry", "123456789", 21, "First class");
	   
	   //adds passengers to plane
	   plane2.add(dad);
	   plane2.add(uncle);
	   plane2.add(mom);
	   plane2.add(sister);
	   plane2.add(brother);
	   
	   //prints last names
	   plane2.printLast();
	   
	   //search
	   System.out.print("Enter the last name of the passenger you're searching: ");
	   String lastName = in.nextLine();
	   System.out.println(plane2.search(lastName));
	   System.out.println();
	   
	   //delete
	   System.out.println("Testing the delete method");
	   System.out.print("Enter the last name of the passenger you'd like to delete: ");
	   String last = in.nextLine();
	   plane2.delete(last);
	   System.out.println("Passenger " + last + " has been removed from the list\n");
	   
	   //updated list
	   System.out.println("Here is the updated list");
	   System.out.println(plane2);
	   
	   //takeoff
	   plane2.takeOff();
	   
	   //new passenger added
	   Passenger josh = new Passenger("joshua", "jinthony", "123456777", 14,"Business class");
	   plane2.add(josh);
	   
			   
	   
   }
   
}
