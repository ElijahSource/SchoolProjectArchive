// H O M E W O R K   # 1 0
// C S C   1 5
// Student's Name Here: Elijah ******

package Main;

import java.util.StringTokenizer;

public class Homework10{
	public static void main(String[] args){
		// My code to dynamically create a text file (DO NOT MODIFY OR REMOVE!)
		EZFileWrite efw = new EZFileWrite("parse.txt");
		efw.writeLine("Shawshank Redemption*1994*Tim Robbins*2.36");
		efw.writeLine("The Godfather*1972*Al Pacino*2.92");
		efw.writeLine("Raging Bull*1980*Robert De Niro*2.15");
		efw.writeLine("Million Dollar Baby*2004*Hilary Swank*2.2");
		efw.writeLine("Straight Outta Compton*2015*Jason Mitchell*2.45");
		efw.saveFile();
		// End of test
		
		// TODO: Write your code to load the text file into memory, parse it, and display the data in a meaningful way...
		// (Use the instructions in the hand out to complete the assignment for full credit)
		
		EZFileRead efr = new EZFileRead("parse.txt");
		int lines = efr.getNumLines();
		int i = 0;
		
		String[] movies = new String[lines];
		int[] years =new int[lines]; 
		String[] stars = new String[lines];
		float[] runtimes = new float[lines];
		
		while(i < lines){
		String raw = efr.getLine(i);
		StringTokenizer st = new StringTokenizer(raw, "*");
		
		movies[i]=st.nextToken();
		years[i]= Integer.valueOf(st.nextToken());
		stars[i]=st.nextToken();
		runtimes[i]=Float.parseFloat(st.nextToken());
		i++;
		}
		
		int x = 0;
		System.out.println("-----MOVIES-----");
		while(x<lines){
			System.out.println(movies[x]);
			x++;
		}
		
		int y = 0;
		System.out.println("-----YEARS-----");
		while(y<lines){
			System.out.println(years[y]);
			y++;
		}
		
		int z=0;
		System.out.println("-----STARS-----");
		while(z<lines){
			System.out.println(stars[z]);
			z++;
		}
		
		int a=0;
		System.out.println("-----RUNTIMES-----");
		while(a<lines){
			System.out.println(runtimes[a]);
			a++;
		}
		

	}


}