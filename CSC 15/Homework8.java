/* H O M E W O R K   # 8
 * C S C   1 5
 * 
 * Student's Name Here: Elijah ******
 * 
 * Follow the directions on the Homework #8 hand out and submit this file via Canvas by the due date.
 * 
 * */

package Main;

public class Homework8{
	public static void main(String[] args){
		// TODO: Follow Homework #8 instructions for assignment and place code below...
		// Feel free to use the p() method to print text instead of System.out.println()
		
		// TODO: Write file data...
		
		//Outputs all lines to testwrite.txt
		EZFileWrite ezw = new EZFileWrite("testwrite.txt");
		int line = 1;
		while(line <= 5){
		ezw.writeLine("Test Line #" + line );
		line = line+1;
		}
		ezw.saveFile();
		
		//Displays testwrite to console 
		EZFileRead ezr = new EZFileRead("testwrite.txt");
		int lines = 0;
		while(lines <5){
		System.out.println(ezr.getLine(lines));
		lines++;
		}
		
		//Copies lines from testwrite to testwrite2
		EZFileWrite eza = new EZFileWrite("testwrite2.txt");
		int ezaezr = 0;
		while(ezaezr<=4){
			eza.writeLine(ezr.getLine(ezaezr));
			ezaezr++;
		}
		
		//Appends lines to testwrite2
		int ezaline = 1;
		while(ezaline<=3){
		eza.writeLine("Append line #"+ezaline);
		ezaline++;
		}
		eza.saveFile();
		

		//displays testwrite 2 to console 
		EZFileRead ezr2 = new EZFileRead("testwrite2.txt");
		int ezr2lines = 0;
		while(ezr2lines <8){
		System.out.println(ezr2.getLine(ezr2lines));
		ezr2lines++;
		}

	}
	

	public static void p(String out){
		System.out.println(out);
	}
}