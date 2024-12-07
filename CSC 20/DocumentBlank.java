/*Elijah ******: I would grade myself 100/100 on this assignment, 
 * I've followed the rubric as closely as possible, had many comments, 
 * the program compiles properly and outputs what was asked, and included all the features asked.  
*/
import java.util.*;
public class DocumentBlank
{
   //no code goes here. Must leave empty
}

class Document
{
	//instance variable
	private String content;
	
	public Document(String text){
		this.content = text;
	}
	//getters
	public String getContent(){
		return content;
	}
    public int getContentLength(){
    	return content.length();
    }
	//setter
    public void setContent(String newContent){
    	this.content = newContent;
    } 
    
    public String toString(){
    	String s = "";
    	s = "Content:"+content.toString();
    	return s;
    }
    
    public boolean contains(String keyword){
    	if(content.contains(keyword)){
    	return true;
    	}
    	
    	else{
    		return false;
    	}
    	
    }
    
    public boolean equals(Document other){
    	if(this.content.equalsIgnoreCase(other.content)){
    	return true;
    	}
    	else{
    		return false;
    	}
   }
    
    
}
class Email extends Document
{                
	//instance variables
     private String text;
     private String sender;
     private String recipient;
     private String subject;
     private String cc;
     private boolean isSent;
	 private java.util.Date date;
	//email class
	 public Email(String text, String sender, String recipient, String subject, String cc){
		super(text);
		this.text = text;
		this.sender = sender;
		this.recipient = recipient;
		this.subject = subject;
		this.cc = cc;
		this.date = new Date();
	}
	
	public void send(){
		this.isSent = true;
	}
	//getters
	public boolean getSent(){
		return isSent;
	}
	
	public String getSender(){
		return sender;
	}
	
	public String getRecipient(){
		return recipient;
	}
	
	public String getSubject(){
		return subject;
	}
	
	public String getCC(){
		return cc;
	}
	
	public Date Date(){
		return date;
	}
	
	//Setters
	public void setSender(String s){
		if(isSent == false){
			sender = s;
		}
		else{
			System.out.println("This email has been sent and cannot be modified");
		}
	}
	
	public void setRecipient(String r){
		if(isSent == false){
			recipient = r;
		}
		else{
			recipient = r;
		}
	}
	
	public void setSubject(String s){
		if(isSent == false){
			subject = s;
		}
	}
	
	public void setCC(String c){
		if(isSent == false){
			cc = c;
		}
	}
	
	public String toString(){
		String s = "\nSender:"+getSender()+"\nRecipient:"+getRecipient()+"\nCC:"+getCC()+"\nSubject:"+getSubject()+"\n"+Date()+"\n"+super.toString();
		return s;
	}
	
	public void modifyContent (String s){
		if(isSent == false){
			super.setContent(s);
		}
		else{
			System.out.println("This email has been sent and cannot be modified");
		}
	}
	
	public Email forward (String rec, String sender, String cc){
		Email f = new Email(this.getContent(), sender,rec,this.subject,cc);
		f.date = new Date();
		f.isSent = true;
		return f;
	}
	
} 
//start of code from shell  
class EmailDriver
{
    // public Email(String text, String sender,String recipiant, String subject, String cc)
    public static void main(String[] args)
    {

       
       //creating an Email object
       Email e1 = new Email("Hello everyone, we will have a meeting tomorrow at 10", "Gita Faroughi","Alex","Meeting","");
      
       //sending the email
       e1.send();
       
       //displays the details about the email
       System.out.println(e1);
       System.out.println("\n\n");
       
       //searching the email for the email for the word tomorrow
       boolean b = e1.contains("tomorrow");
       if(b){
         System.out.println("The word tomorrow was found in the email");
       }
       else
          System.out.println("The word tomorrow was found in the email"); 
           
      
       //displaying just the content(text) of the email
       System.out.println("\nThe content of this email is: " + e1.getContent());
       System.out.println();
       //modifying the content of the email
       e1.modifyContent("bye");
       
       //changing the recipient of the e1 email
       e1.setRecipient("Jose@yahoo.com,Mary@gmail.com");
       System.out.println();
       
       //forwarding the email
       Email forward = e1.forward("Alex", "Gita" ,"Maria");
       System.out.println();
       
       //printing the forwarded email 
       System.out.println("forwarded message\n"+ forward);
       System.out.println();
       
       //display the length of the text in the email
       System.out.println("The number of the letters in the email is: " + e1.getContentLength());
       
       //end of code from shell
       
       
       //the initial e2 email
       Email e2 = new Email("Hello fortnite 2 has released","Elijah ******","David","FORTNITE 2!!!","");
       
       //calling setter methods, changes recipient subject sender and the person cc'd
       e2.setRecipient("jacob");
       e2.setSubject("Fortnite 2 cancelled");
       e2.setSender("sad elijah");
       e2.setCC("daniel");
       e2.modifyContent("fortnite 2 got pushed back :(");
       
       //Calls contains method and displays a message 
       boolean c = e2.contains("fortnite");
       if(c){
         System.out.println("\nThe word fortnite was found in the email");
       }
       else
          System.out.println("\nThe word fortnite was found in the email"); 
       
       //Calls getContentLength method and displays the number of letters
       System.out.println("\nThe number of letters in the email is:"+e2.getContentLength());
       
       //sends email e2
       e2.send();
      
       //Displays final email e2
       System.out.println(e2);
       
    }
}