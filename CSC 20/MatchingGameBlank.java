/*Name Elijah ******
 * Self grade 100/100 I believe I fully followed the rubric and have designed this game just as it was asked of us, I've also added comments throughout, 
 * followed proper naming conventions, kept the code indented correctly, and the program runs and compiles how its supposed to with no problem. 
 * I followed all of the requirements of the assignment as closely as possible.  
 */
import java.util.*;
public class MatchingGameBlank
{
  public static void main(String[] args)
  {
	 Scanner kb = new Scanner(System.in);
     String answer = "";                   
     Random rand = new Random();
     while (!answer.equalsIgnoreCase("q"))
     {
    	description();
        System.out.print("\nWhat is your name?: ");
        String name = kb.next();
        System.out.print("Hello "+name+" lets start playing\n"); 
        play(rand); 
        //Calls the description so that it will read the description, then asks the player their name and stores it for the welcome message, then starts the game
     }
  }
  public static int getRand(Random rand)
  {

	int number = rand.nextInt(9) + 1;
    return number;
    //retrieves random number between 1 and 9 inclusive
  }
  public static void play(Random rand)
  {
     Scanner kb = new Scanner(System.in);
     int total = 0;
     String answer = "";
     int n1= 0, n2 = 0, n3 = 0;
     while(!answer.equalsIgnoreCase("q"))
     {
        
    	 n1 = getRand(rand);
    	 n2 = getRand(rand);
    	 n3 = getRand(rand);
    	 System.out.printf("%d ,%d , %d\n", n1, n2, n3);
         //calls on getrand to generate 3 random numbers and prints them out     
       int match = match(n1,n2,n3);// calls match to check numbers against each other
       if (match == 2)
       {
    	  total = total+100;
          System.out.println("You got two matching numbers, you won 100 dollars");
       }   
       else if( match == 3)
       {
           total = total+300;
    	   System.out.println("You got three matching numbers, you won 300 dollars");            
       }    
       else
    	 System.out.println("Sorry no match");
     
       System.out.print("\nHit enter to continue or press q/Q to quit\n");
       answer = kb.nextLine(); 
       
             
     }
     System.out.println("\nTotal amount you won: " + total);
     System.out.println("\nGoodbye\n");
     //prints total the player won after they quit, loop restarts so the next player can play
  }
  public static int match(int n1, int n2, int n3)
  {
	  if(n1 == n2 && n2 == n3){
		  return 3;
	  }
	  else if(n1 == n2 && n2 != n3){
		  return 2;
	  }
	  else if(n2 == n3 && n1 != n2){
		  return 2;
	  }
	  else if(n1 == n3 && n2!=n3){
		  return 2;
		  } 
	  else{
		  return 0;
	  }
	  //checks the generated numbers against each other
  }
  public static void description()
  {
      System.out.print("Welcome to number matching game.  I will generate three random numbers for you.\nIf two of the numbers match you win 100$, if you get three matching numbers you will win 300$.");
	  //code to display the description of the program
  }
}