// Elijah ******, Id give myself 100/100 because the program runs as instructed has no problems, i followed the rubric as closely as possible, and have left comments throughout 
import java.util.*;
public class InstaBlank
{
}
class User implements Comparable {
   private String first;
   private String last;
   private String username;
   private boolean followBack;//set to true if you want to follow back the person
        
   //constructor
   public User(String first, String last, String username, boolean followBack){
	   this.first = first;
	   this.last = last;
	   this.username = username;
	   this.followBack = followBack;
   }
   
   //Getters
   public boolean getFollow()
   {
	   return this.followBack;
   }
   
   public String getFirst(){
	   return this.first;
   }
   
   public String getLast(){
	   return this.last;
   }
   
   public String getUsername()
   {
	   return this.username;
   }

   public int compareTo(Object o) 
   {
	   User a = (User)o;
	   return this.username.compareTo(a.username);
   }
   
   //Setters
   public boolean equals(User o) 
   {
	   if(o instanceof User)
	   {
		   User a = (User)o;
		   return this.first.equalsIgnoreCase(o.first) && this.last.equalsIgnoreCase(o.last);
	   }
	   
	   return false;
   }
   public void unfollow()
   {
	   this.followBack = false;
   }

   public void follow()
   {
	   this.followBack = true;
   }
   
   public void setFirst(String first){
	   this.first = first;
   }
   
   public void setLast(String last){
	   this.last = last;
   }
   
   //tostring
   public String toString(){
	   String following = "" + this.followBack;
	   
	   String s = "Username: " + this.username +"\nFirst Name: " + this.first +"\nLast Name: " + this.last + "\nFollowing: " + following.substring(0,1).toUpperCase() + following.substring(1) +"\n****************************\n";
	   return s;
   }
}

class SocialMedia
{
   private ArrayList<User> app;
     
   SocialMedia(){
      app = new ArrayList<User>();
   }
   
   public void followBack(String first, String last)
   {
	   String s = first + " " + last; 
	   
	   for(int i = 0; i < app.size(); i++) 
	   { 
		   String s1 = app.get(i).getFirst() + " " + app.get(i).getLast(); 
		   
		   if(s.equalsIgnoreCase(s1)) 
		   { 
			   app.get(i).follow();
		   } 
	   }
   }
   
   //follows user
   public boolean follow(String first, String last, String username, boolean followBack){
	   boolean b = search(first, last);
	   boolean followed = false;
	   User u = new User(first, last, username, followBack);
	   
	   if(b)
	   {
		   return false;
	   }
	   else if (app.size() == 0)
	   {
		   app.add(u);
		   followed = true;
	   }
	   
	   for(int i = 0; i < app.size(); i++)
	   {
		   if(u.compareTo(app.get(i)) < 0)
		   {
			   app.add(i, u);
			   followed = true;
			   break;
		   }
	   }
	   
	   if(!followed)
	   {
		   app.add(u);
		   followed = true;
	   }
	   
	   return followed;          	
   }
     
    //removes follower
   public boolean remove(String first, String last ){
	   for(int i = 0; i < app.size(); i++)
	   {
		   if(app.get(i).getFirst().equalsIgnoreCase(first) && app.get(i).getLast().equalsIgnoreCase(last))
		   {
    		  app.remove(i);
    		  return true;
		   }
	   }
	   
	   	return false;
   }
    
   //searches for follower 
   public boolean search(String first, String last) {
	   for(int i = 0; i < app.size(); i++)
	   {
		   if(first.equalsIgnoreCase(app.get(i).getFirst()) && last.equalsIgnoreCase(app.get(i).getLast()))
		   {
			   return true;
		   }
	   }
	   
	   return false;
   }
   
   public ArrayList<User>getList(){
	   return app;
   }
   
   public int followerCounts()
   {
	   return app.size();
   }
   
   public int followingCounts()
   {
	   int followingCount = 0;
	   
	   for(int i = 0; i < app.size(); i++)
	   {
		   if(app.get(i).getFollow() == true)
		   {
			   followingCount++;
		   }
	   }
	   
	   return followingCount;     
   } 
   
   //tostring
   public String toString() 
   {
	   String s = "";
	   
	   for(int i = 0; i < app.size(); i++)
	   {
		   s = s + app.get(i).toString() + "\n";
	   }
	   
	   return s; 
   }
}

class MyDriver
{
   public static void main(String[] args)
   {
	   SocialMedia instagram = new SocialMedia();
	   
	   instagram.follow("Elijah", "White", "eli_jah", false);
	   instagram.follow("James", "Tyler", "Minions_fan", true);
	   instagram.follow("Jordan", "Smith", "Smithers", true);
	   
	   System.out.println("Your follower information");
	   System.out.println(instagram);
	   
	   instagram.follow("John", "Taylor", "JohnT", false);
	   
	   System.out.println("Your followers:");
	   System.out.println(instagram);
	   
	   System.out.println("Searching for Chris Anthony");
	   if(instagram.search("Chris", "Anthony"))
	   {
		   System.out.println("Chris Anthony was found");
	   }
	   else
	   {
		   System.out.println("The follower Chris Anthony was not found");
	   }

	   Scanner kb = new Scanner(System.in);
	   System.out.print("\nEnter the first and last name from the list bellow that you want to remove: ");   
	   String first = kb.next();
	   String last = kb.next();
	   System.out.println(first+" "+last);
	   instagram.remove(first,last);

	   System.out.println("\nHere is your updated follower information");
	   System.out.println(instagram);
	   
	   System.out.print("\nEnter the first and last name from the list bellow that you want to follow back: ");   
	   String newFirst = kb.next();
	   String newLast = kb.next();
	   instagram.followBack(newFirst, newLast);
	   
	   System.out.println("\nYour updated follower information");
	   System.out.println(instagram);
   }
}

class Driver
{
	public static void main(String[]args) 
	{
		SocialMedia myInsta = new SocialMedia();
	      
		/*Adding followers to your list*/
		/*the boolean field indicates whether you want to follow them back*/
		myInsta.follow("Matthew", "Philips", "MatPhil", true);
		myInsta.follow("Gary", "Kane",  "GKane", false); 
		myInsta.follow("Robert", "Kenny",  "RKenny", true); 
		myInsta.follow("Bill", "Fitch", "BillF",true);
		myInsta.follow("Trevor", "Schlulz", "TrevorS", false);
		
		/*Displaying your followers*/
		System.out.println("Your followers informations\n");
		System.out.println(myInsta);
		
		/*Unfollowing a user*/
		System.out.println("Removing Robert Kenny from your followers list");
		myInsta.remove("Robert", "Kenny");
	   		
		/*Displaying the list*/
		System.out.println("List of followers after removing Robert Kenny");
		System.out.println(myInsta);
	   		
		/*adding a new follower*/
		System.out.println("Adding Elon Musk to your list of followers");
		myInsta.follow("Elon", "Musk", "ElonM", true);
	      
		/*Dipslying the followers*/
		System.out.println("List of your followers:");
		System.out.println(myInsta);
	   	
		/*Searching for a follower*/
		System.out.println("Searching for Stonewall Jackson(StonW) in your followers list");
		
		if(myInsta.search("Jackson", "Stonewall") == false) 
		{
			System.out.println("Stonewall Jackson is not in your list of followers");
			System.out.println("\n***************************");   
			System.out.println("You are following " + myInsta.followerCounts() + " people");
	      
			System.out.println("You have " + myInsta.followingCounts() + " followers");  
			System.out.println(myInsta);
			Scanner kb = new Scanner(System.in);
			System.out.println("Enter the first and  last name from the list bellow that you want to follow back: ");
	      
			String first =kb.next();
			String last = kb.next();
			myInsta.followBack(first,last);
	      
			System.out.println(myInsta);
		}
	}
}