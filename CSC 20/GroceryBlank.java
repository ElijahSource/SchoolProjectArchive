//Elijah ******, id grade myself 90/100 i indented correctly followed the rubric as close as possible but im turning this in late so i should be deducted the ten %
public class GroceryBlank //<------ chnage this name to add your last name to it
{
   // no code here
} 


class Item implements Comparable<Object>{
	//instance variable
   private String food;
   private String expDate;
   private double price;
   //constructor
   public Item(String food, double price,String expDate) 
   {
	   this.food = food;
	   this.expDate = expDate;
	   this.price = price;
   }
   //getters
   public String getFood() 
   {
	   return food;
   }
   public String getExpDate() 
   {
	   return expDate;
   }
   public double getPrice() 
   {
	   return price;
   }
   //setters
   public void setFood(String newFood)
   {
	   food = newFood;
   }
   public void setPrice(double newPrice) 
   {
	   price = newPrice;
   }
   public void setExpDate(String newExp) 
   {
	   expDate = newExp;
   }
   //equals: two items are equal if they have the same food and have the  same price
	public boolean equals(Item other) 
	{
		String price1 = String.valueOf(this.price);
		String price2 = String.valueOf(other.price);
		return this.food.equals(other.food)&&price1.equals(price2);
	}
	
   //compare two items based on the instance varaible food
   public int compareTo(Object o)
   {
	  Item s =(Item)o;
	  return (this.food).compareTo(s.food);
   }
   
   //tostring
   public String toString() 
   {
   		return "Food:"+food+"\nPrice:"+price+"\nExpiration Date:"+expDate+"\n";
}
}
   

class ListNode{
	private Item i;
	private ListNode next;
	
	//constructors
	public ListNode(Item i) 
	{
		this.i = i;
	}
	public ListNode() 
	{
		
	}
	public ListNode(Item i, ListNode next)
	{
		this.i = i;
		this.next = next;
	}
	
	//getters
	public Item getItem() 
	{
		return i;
	}
	public ListNode getNext()
	{
		return next;
	}
	
	//setter
	public void setNext(ListNode n)
	{
		next = n;
		
	}
}

interface List{
   public void add(String food, double price, String expDate);
   public void add(int index, String food, double price, String expDate);
   public int indexOf(String food);
   public void remove(String food);
   public int size();
   public String toString();
   public Item get(int index);
   public Item mostExpensive();
}

class GroceryList implements List{
	//implement your code here
	private ListNode head;
	public static int size = 0;
	
	public void ItemList() 
	{
		head =null;
		//doublecheck
	}
	
	//adds item at end of list
   public void add(String food, double price, String expDate) 
   {
   		Item i = new Item(food, price, expDate);
   		ListNode curr = head;
   		
   		if(head == null)
   		{
   			head = new ListNode(i);
   			size++;
   			return;
   		}
   		
   		ListNode n = new ListNode(i);
   		
   		while(curr.getNext() != null)
   		{
   			curr = curr.getNext();
   		}
   		
   		curr.setNext(n);
   		
   		size++;
	   //complete this method
   	
   }

   //adds item at given index
   public void add(int index, String food, double price, String expDate) {
   	//complete this method
	   Item i = new Item(food, price, expDate);
	   if(index>size)
		   return;
	   
	   if(index == 0) 
	   {
		   ListNode n = new ListNode(i);
		   n.setNext(head);
		   head = n;
		   size++;
		   return;
	   }
	   
	   ListNode curr = head;
	   int a = 0;
	   
	   while(curr.getNext() != null && a< index -1) 
	   {
		   curr = curr.getNext();
		   a++;
	   }
	   
	   ListNode n = new ListNode(i);
	   
	   n.setNext(curr.getNext());
	   
	   curr.setNext(n);
	   
	   size++;
   }

   public int indexOf(String food) 
   {
   	//complete this method
	   if(head == null)
		   return -1;
	   
	   if(food.equals(head.getItem().getFood()))
      return 0;
	   
	   ListNode curr = head;
	   int index = 0;
	   while(curr!=head && index <= size) 
	   {
		   if(curr.getItem().getFood().equals(food))
			   return index;
		   curr = curr.getNext();
		   index++;   
	   }
	   return -1;
   }

   //removes specified food from list
   public void remove(String food) 
   {
   	
	   if(head == null)
		   return;
	   if(head.getItem().getFood().equals(food));
	       head =head.getNext();
	   ListNode pre = head;
	   ListNode curr = head;
	   
	   while(curr != null && !(curr.getItem().getFood().equals(food))) 
	   {
		   pre = curr;
		   curr = curr.getNext();
	   }
	   
	   if(curr != null && !(curr.getItem().getFood().equals(food))) 
	   {
		   pre.setNext(null);
		   size--;
		   System.out.println("The last node is removed");
		   
	   }
	   
	   else if (curr == null)
		   System.out.println("song not found");
	   //complete this method
	   else
	   {
		   pre.setNext(curr.getNext());
		   size--;
		   System.out.println("A node in the middle is removed");
	   }
   }

   public int size() 
   {
      return size+1;
   }

   //to string
   public String toString()
   {
	   if(head == null)
		   return"";
	   ListNode curr = head;
	   String s = "";
	   
	   while(curr != null)
	   {
		   s = s + curr.getItem().toString()+"\n";
		   curr = curr.getNext();
	   }
	   return s;
   }
   
   //returns item at given index 
   public Item get(int pos) {
	   if(head == null)
		   return null;
	   
	   if(pos>size)
		   return null;
	   
	   ListNode curr = head;
	   int index = 0;
	   
	   while(curr!= null && pos != index)
	   {
		   index++;
		   curr = curr.getNext();
	   }
	   
	   if(curr == null)
		   return null;
	   
	   return curr.getItem();
		   
	  
   }

   //finds mostexpensive item
   public Item mostExpensive() {
	  if(head == null)
		  return null;
	  ListNode curr = head;
	  Item mostExpensive = curr.getItem();
	  while(curr != null)
	  {
		  if(curr.getItem().getPrice()>mostExpensive.getPrice()) 
			  mostExpensive = curr.getItem();
		  curr = curr.getNext();
		  
	  }
	return mostExpensive;
   
   }
   
}
	


//Once you have completeed all the classes run your code with the given driver, then write your own driver
class Driver {
	public static void main(String []args) {
		GroceryList list = new GroceryList();
		
		list.add("Bread", 5.99, "3/20/2022");
		list.add("Milk", 3.99, "2/1/2002");
		list.add("Chips", 2.99, "12/30/2025");
		list.add("Rice", 35.50, "8/15/2030");
		
		System.out.println("Here is the list of food items");
		System.out.println(list);
		
		System.out.println("Here is the most expensive item on the list");
		System.out.println(list.mostExpensive());
		
		System.out.println("Removing Milk from the list and adding a new expensive item on the list in the 2nd node");
		list.remove("Milk");
		list.add(1, "Truffle", 800, "4/20/2050");
		System.out.println(list);
		
		System.out.println("Testing the mostExpensive method to see what is the most expensive item now");
		System.out.println(list.mostExpensive());
		
		System.out.println("Testing the get method to get the item at the 3rd node");
		System.out.println(list.get(2));
		
	}
}
/*
implement your own driver and test all the methods. Your driver must be very similar to the provided one*/

//my driver
class MyDriver
{
   public static void main(String[] args)
   {
		GroceryList list2 = new GroceryList();
		
		list2.add("Chocolate", 1.99, "5/22/2003");
		list2.add("Ham", 23.99, "3/21/2004");
		list2.add("Potatos", 4.78, "11/26/2005");
		list2.add("Tomatos", 5.55, "9/10/2003");
		
		System.out.println("Here is the list of food items");
		System.out.println(list2);
		
		System.out.println("Here is the most expensive item on the list");
		System.out.println(list2.mostExpensive());
		
		System.out.println("Removing Chocolate from the list and adding a new expensive item on the list in the 2nd node");
		list2.remove("Chocolate");
		list2.add(1, "Turkey", 100, "4/20/2004");
		System.out.println(list2);
		
		System.out.println("Testing the mostExpensive method to see what is the most expensive item now");
		System.out.println(list2.mostExpensive());
		
		System.out.println("Testing the get method to get the item at the 4th node");
		System.out.println(list2.get(3));
		
   }
}
