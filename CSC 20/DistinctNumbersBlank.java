/*Name: Elijah ******
 *Self grade: 100/100 i put several comments, followed naming conventions, the program runs properly, I've followed the rubric as closely as possible
 */
import java.util.*;
public class DistinctNumbersBlank
{
   public static void main(String[] args)
   {
	 int[] num = new int[10];//initialize array with 10 values
     System.out.println("Welcome to the distinct numbers. \nI will remove the repeated numbers and display the numbers you just enetered");
     num = getInput();//calls getInput method to fill array
     display(num);//calls display method to display text
   }
   public static int[] getInput()
   {
	   int[] num = new int[10];//declare array of integers with size 10
	   Scanner kb = new Scanner(System.in);//scanner object created
	   for(int i = 0; i< num.length; i++)
	   {
			System.out.print("Enter a number: ");
			int input = kb.nextInt();//takes input
			if(isDistinct(num, input))// calls isDistinct to filter input
			{
				num[i] = num[i--];
			}
			else
			{
				num[i] = input;
			}
	   }
       return num; 
   }
   public static void display(int[] num)//displays final values
   {
		System.out.print("\nI filtered out all the repeated numbers you entered and kept only one copy of each number\nHere is the list of your numbers\n{");
		for(int i = 0; i < num.length-1; i++)
		{
			System.out.print(num[i]+", ");//prints numbers
		}
		System.out.print(num[num.length-1]+"}");
    }
   public static boolean isDistinct(int[] v, int n)//filters indistinct values
   {
		for(int i = 0; i < v.length-1; i++)
		{
			if(n==v[i])
			{
				return true;
			}
		}
		return false;
   }
 }