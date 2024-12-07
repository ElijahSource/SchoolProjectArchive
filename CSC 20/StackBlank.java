//Elijah ****** self grade 100/100 followed rubric program runs correctly and is commented 
import java.util.*;
public class StackBlank
{
}
interface myStack
{
   public void push(String s);
   public String peek();
   public boolean isEmpty();
   public String pop();
}
class  Stack implements myStack  
{
     private ArrayList<String> s;
     int top;
     public Stack(){
    	 s = new ArrayList<String>();
    	 top = 0;
     }
     
     //pushes token to the top of stack
     public void push(String token){
    	 s.add(token);
    	 top++;
     }
     //pops element at top of stack
     public String pop(){
    	 if(!isEmpty()){
    		 top--;
    		 String token = s.get(top);
    		 s.remove(top);
    		 return token;
    	 }
    	 else
    	 return null;
     }
     //returns element at top of stack
     public String peek(){
    	 if(!isEmpty())
    		 return s.get(top-1);
    	 return null;
     }
     //checks if stack is empty
     public boolean isEmpty(){
    	 return s.size() == 0;
     }
     //puts information to string
     public String toString(){
    	 return s.toString();
     }
}

class Expression extends Stack
{
  private String exp;  // instance variable
  public Expression(String s)
  {
     exp = s;
  }
  
  //gets postfix from expression
  public String getPostfix()
  {
	  String postfix = " ";
	  Stack stack = new Stack();
	  StringTokenizer st = new StringTokenizer(exp, " ");
	  
	  while (st.hasMoreTokens()){
		  String token = st.nextToken();
		  
		  if(token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")){
			  int num = precedence(token);
			  if(num ==3){
				  while(!stack.isEmpty() && precedence(stack.peek()) >=3){
					  postfix += stack.pop() + " ";
				  }
			  }
			  else if (num ==2){
				  while(!stack.isEmpty() && precedence(stack.peek()) >=2){
					  postfix += stack.pop()+" ";
				  }
			  }
			  stack.push(token);
		  }
		  else
		  {
			  postfix += token+" ";
		  }
	  }
	  while(!stack.isEmpty()){
		  postfix += stack.pop() + " ";
	  }
   return postfix;
  }
  
  private static int precedence(String opr)
  {
	  if(opr.equals("*") || opr.equals("/"))
		  return 3;
	  else if(opr.equals("+") || opr.equals("-"))
		  return 2;
	  return 0;
  }
  
  //evaluates equation
  public int evalPostfix()
  {
       String post = this.getPostfix();
       Stack stack = new Stack();
       int result = 0;
       StringTokenizer st = new StringTokenizer(post, " ");
       while(st.hasMoreTokens()){
    	   String token = st.nextToken();
    	   if(!token.equals("+") && !token.equals("-") && !token.equals("*") && !token.equals("/")){
    		   stack.push(token);
    	   }
    	   else{
    		   int num1 = Integer.parseInt(stack.pop());
    		   
    		   int num2 = Integer.parseInt(stack.pop());
    		   
    		   result = calculate(num1, num2, token);
    		   stack.push("" + result + "");
    	   }
       } 
       return Integer.parseInt(stack.pop());
   }
    
     
  //calculates the final result
  private int calculate(int num1, int num2, String opr)
  {
	  if(opr.equals("*"))
		  return num1 * num2;
	  if(opr.equals("/"))
		  return num2/num1;
	  if(opr.equals("+"))
		  return num1 + num2;
	  if(opr.equals("-"))
		  return num2 - num1;
      return 0;
  }
  }

//shell driver
class ExpDrive
{
	  public static void main(String[] args)
	  {
	     
	     String s = "5 - 2";
	     ArrayList <String> exp = new ArrayList<String>();
	     exp.add("2 + 3 + 7 * 4 - 2 / 3");
	     exp.add("3 - 4 / 2 + 6 * 3");
	     exp.add("5 * 6 - 8 + 2 * 10");
	     exp.add("4 + 8 * 3 - 2 / 34");
	     exp.add("6 - 3 + 6 / 2 * 4 - 8");
	     for(int i = 0; i < exp.size(); i++)
	     {
	     
	        Expression e1 = new Expression(exp.get(i));
	        String post = e1.getPostfix();
	        int result = e1.evalPostfix();
	        System.out.println("Infix: "+ exp.get(i) + ",  postfix: " + post + " = " + result);
	     }

}
}

// my driver
class MyExpDrive
{
	  public static void main(String[] args)
	  {
	     
	     String s = "14 - 3";
	     ArrayList <String> exp = new ArrayList<String>();
	     exp.add("5 * 6 - 7 * 2 - 1 / 3");
	     exp.add("4 + 2 - 1 * 4 * 1");
	     exp.add("8 * 7 - 5 + 1 * 3");
	     exp.add("6 - 9 * 4 - 3 / 2");
	     exp.add("7 - 4 * 3 / 2 * 5 + 7");
	     for(int i = 0; i < exp.size(); i++)
	     {
	     
	        Expression e1 = new Expression(exp.get(i));
	        String post = e1.getPostfix();
	        int result = e1.evalPostfix();
	        System.out.println("Infix: "+ exp.get(i) + ",  postfix: " + post + " = " + result);
	     }
	  }
}