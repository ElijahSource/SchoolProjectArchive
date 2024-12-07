/* H O M E W O R K   # 7
 * C S C   1 5
 * 
 * Student's Name Here: Elijah ****** 
 * 
 * Follow the directions on the Homework #7 hand out and submit this file via Canvas by the due date.
 * 
 * */

package Main;

/* Create a class that handles keeping track of a video game's score (according to specifications on the hand out)
 * 
 * Fill in the fields, constructor body, and method bodies to complete the assignment. */

public class Gamescore{
	private int startScore;
	
	public Gamescore(int startScore){
	}
	
	public int getScore(){
		return startScore;
	}
	
	public void increaseScore(int amt){
		startScore = startScore + amt;
	}
	
	public void decreaseScore(int amt){
		startScore = startScore - amt;
	}
	
	public void resetScore(){
		startScore = 0;
	}
	
	public void setScore(int value){
		startScore = value;
	}
	
	public String toString(){
		return("Score:"+String.valueOf(startScore));
	}
}