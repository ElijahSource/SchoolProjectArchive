//Elijah ******, Id give myself 100/100 i followed the rubric specifications and commented throughout have my own method and did everything that was asked.  
import java.util.*;
public class QueueBlank 
{
  //no code here
}
class Queue
{
   //attribute
   ArrayList<Integer> list;
   //constructor
   public Queue()
   {
     list = new ArrayList<Integer>();
   }
   public void enqueue(Integer num)
   {
      list.add(num);
   }
   public Integer dequeue()
   {
     return list.remove(0);
   }
   public String toString()
   {
     String s = "";
     Queue copy = new Queue();
     boolean b = false;
     while(!b)
     {
       try
       {
          int num = this.dequeue();
          copy.enqueue(num);
          s = s + " " + num;
       }
       catch(Exception e)
       {
          b = true;
       }
     }
     restore(copy);
     return s;
   }
   public int getMax()
   {
     Queue copy = new Queue();
     boolean b= false;
     int max = 0;
     while(!b)
     {
       try
       {
          int num = this.dequeue();
          if(num > max)
             max = num;
          copy.enqueue(num);   
          
       }
       catch(Exception e)
       {
         b = true;
       }
     }
     restore(copy);
     return max;
   }
   public void restore(Queue q)
   {
      boolean b = false;
      while(!b)
      {
         try
         {
            this.enqueue(q.dequeue());
         }
         catch(Exception e)
         {
            b = true;
         }
      }
   }
   //*******************implement the following methods***************
   //gets minimum number from list 
   public Integer getMin()
   {
    	 Queue copy = new Queue();
         boolean b= false;
         int min = 999;
         while(!b)
         {
           try
           {
              int num = this.dequeue();
              if(num < min)
                 min = num;
              copy.enqueue(num);   
              
           }
           catch(Exception e)
           {
             b = true;
           }
         }
         restore(copy);
         return min;       
   }
    //reverses order of list 
    public void reverseOrder()
    {
        Stack<Integer> s =  new Stack<Integer>();
        Boolean b = false;
        while(!b) 
        {
            try 
            {
            	while(!b)
            	{
             int num = this.dequeue();
             s.push(num);
             }
            }
            catch(Exception e) 
            {
                b = true;

            }
            b = false; 

            while(!b) 
            {
                try{
                 int num = s.pop();
                 this.enqueue(num);
                }
                catch(Exception e) {
                 b = true;
                }
            }
        }
    }
    //gets average
    public double getAverage()
    {
    	Queue copy = new Queue();
   	 	double sum = 0;
   	 	double count = 0;
    	//Declare a Queue called copy//to restore the original Queue
    	Boolean b = false;
    	while(!b)
    	{
    	     try
    	     {

    	    	 int num = this.dequeue();
    	    	 copy.enqueue(num);
    	    	 sum = sum+num;
    	    	 count++;   
    	    }
    	   catch(Exception e)
    	  {
    	       b = true;
    	  }
    	}
        restore(copy);
        return sum/count;    
    }
    
    public boolean isSorted()
    {
    	Queue q = new Queue ();
    	Boolean b = false;
    	Boolean sorted = true;
    	while(!b)
    	{
    	      try
    	      {
    	          Integer n1 =  this.dequeue();
    	          Integer n2 = this.dequeue();
    	          q.enqueue(n1);
    	          q.enqueue(n2);
    	          if(n1 > n2)
    	              sorted = false;
    	      }
    	     catch(Exception e)
    	    {
    	         b = true;
    	     } 
    	} 
    	restore(q);
    	return sorted;      
    }
  //**************************************************** 
 //My method is subtracting the lowest value of the list from the highest value of the list
    public int highestLowest()
    {
    	return getMax()-getMin();
    }
   }
class Driver
{
   public static void main(String[] args)
   {
	   Queue  m = new Queue();
	      m.enqueue(10);
	      m.enqueue(12);
	      m.enqueue(15);
	      m.enqueue(7);
	      m.enqueue(100);
	      m.enqueue(22);
	      System.out.println("The queue is : " + m);
	      m.reverseOrder();
	      System.out.println("The queue in the reverse order is: "+ 
	m    );
	      m.reverseOrder();
	      System.out.printf("Average = %.2f\n", m.getAverage());
	      System.out.println(m);
	      System.out.println("Max = " + m.getMax());
	      System.out.println("Max = " + m.getMin());
	      System.out.println("The list is sorted: "+ m.isSorted());          
   }
}
class yourDriver
{
   public static void main(String[] args)
   {
	   Queue  m = new Queue();
	      m.enqueue(20);
	      m.enqueue(32);
	      m.enqueue(52);
	      m.enqueue(2);
	      m.enqueue(97);
	      m.enqueue(10);
	      System.out.println("The queue is : " + m);
	      m.reverseOrder();
	      System.out.println("The queue in reverse order is: "+ m);
	      m.reverseOrder();
	      System.out.printf("Average = %.2f\n", m.getAverage());
	      System.out.println("Queue is back to its original state:"+m);
	      System.out.println("Max = " + m.getMax());
	      System.out.println("Min = " + m.getMin());
	      System.out.println("The list is sorted: "+ m.isSorted());
	      System.out.println("The highest number from the queue subtracted by the lowest number from the queue is:"+m.highestLowest());
   }
}