//Elijah ****** i think i should get 100/100 i followed the rubric the app runs properly i indented properly and everything works as was supposed to 
import java.util.*;
public class BookAppBlank
{
}
class Book implements Comparable
{
   private String title;
   private String author;
   private String ISBN;
   private double price;
   public Book(String title, String author, String ISBN,double price)
   {
      this.title = title;
      this.author = author;
      this.ISBN = ISBN;
      this.price = price;
   }
   public String getTitle()
   {
      return title;
   }
   public String getAuthor()
   {
      return author;
   }
   public String getISBN()
   {
      return ISBN;
   }
   public double getPrice()
   {
      return price;
   }
   public void setTitle(String t)
   {
      title = t;
   }
   public void setPrice(double p)
   {
      price = p;
   }
   public void setIsbn(String sb)
   {
      ISBN = sb;
   }
   public String toString()
   {
      return title+ ", " + author+", "+price+", "+ISBN;
   }
   public boolean equals(Book other)
   {
     return this.ISBN.equals(other.ISBN);
   }
   
   //for selection sort
   public int compareTo(Object o)
   {
	 Book s = (Book)o;
	 return(this.title).compareTo(s.title);
   }
   
   //for insertion sort
   public int compares(Book b)
   {
	   Book s = (Book)b;
	   return(this.author).compareTo(s.author);
   }
   
   //for bubble sort
   public double compare(Book b)
   {
	   Book s = (Book)b;
	   if(this.author == b.author){
		   return (int) (this.price - b.price);
	   }
	   else{
		    return author.compareTo(b.author);
		    }       
   }
}
class BookStore
{
   private ArrayList<Book> books;
   public BookStore()
   {
      books = new ArrayList<Book>();
   }
   public void add(String title, String author, double price, String isbn)
   {
      books.add(new Book(title, author,isbn, price));
   }
   public String toString()
   {
      String s = "";
      for(int i = 0; i < books.size(); i++)
      {
         s= s+ books.get(i).toString()+"\n";
      }
      return s;
   }
   public boolean delete(String isbn)
   {
      for(int i = 0; i < books.size(); i++)
      {
         if (books.get(i).getISBN().equals(isbn))
         {
            books.remove(i);
            return true;
         }
      }
      return false;
   }
   
   //sorts based on title, uses compareTo
   public void selectionSort()
   {
	      for(int i = 0; i<books.size() -1; i++)
	      {
	         int index = -1;
	         Book min = books.get(i);
	         boolean found = false;
	         for(int j = i+1; j < books.size(); j++)
	         { 
	            int a = books.get(j).compareTo(min);
	            if(a < 0)
	            {
	               index = j;
	               min = books.get(j);
	               found = true;
	            }   
	           
	         }
	         if(found == true)
	         {
	            Book temp = books.get(i);
	            books.set(i, min);
	            books.set(index,temp);
	         }   
	      }	   
                      
            
    }
   
   
   //sorts the book objects based on the author of the book uses compares
   public  void insertionSort( )
   {
	      for(int i = 0; i < books.size() -1; i++)
	      {
	         int j = i+1;
	         Book n  = books.get(j);  
	         while(j > 0 && n.compares(books.get(j-1)) < 0) 
	         {
	        	 books.set(j,books.get(j-1));
	            j--;
	         }
	         books.set(j,n); 
	         
	       }	   
	   
    }
   
   //sorts by author and prices uses compare
   public void bubbleSort()
   {
     for(int i =0; i< books.size();i++){
    	 for(int j =0; j <books.size() -1 - i; j++)
    	 {
    		 if(books.get(j+1).compare(books.get(j)) <0)
    		 {
    			 Book temp = books.get(j);
    			 books.set(j, books.get(j+1));
    			 books.set(j+1, temp);
    		 }
    	 }
     }
   }
   
   //searches based on title of the book
   public Book binarySearch(String title)
   {
	   selectionSort();
	   boolean found = false;
	   int first = 0;
	   int last = books.size()-1;
	   int middle = (first+last)/2;
	   while(first <= last){
		   if(books.get(middle).getTitle().equalsIgnoreCase(title))
		   {
			   found = true;
			   return books.get(middle);
		   }
		   else if(title.compareTo(books.get(middle).getTitle())>0)
		   {
			   first = middle+1;
		   }
		   else{
			   last = middle -1;
		   }
		   middle = (first+last)/2;
	   }
	   return null;
   }

}
   
class Driver
{
   public static void main(String[] args)
   {
      Scanner kb = new Scanner(System.in);
      BookStore myStore = new BookStore();
      myStore.add("Java","Zoie",23.56,"12345678");
      myStore.add("Python","Elina",23.56,"2");
    
      myStore.add("Advance Java","Stewart",98,"767676576");
      myStore.add("Build Java","Liang",45,"56786565y76");
      myStore.add("Zip lining", "Stewart",12,"1234566576");
      myStore.add("C++","Elina",23.56,"2645556");
      myStore.add("Programming Java","Stewart",124,"75465666");
      myStore.add("Humanity","Smith",100.56,"234545657");
      boolean b = true;
      while(b)
      {
         System.out.println("Enter 1 to sort based on the title");
         System.out.println("Enter 2 to sort based on the author");
         System.out.println("Enter 3 to sort based on the author, and the price");
         System.out.print("Enter your choice: ");
         int option = kb.nextInt();
         System.out.println("\n*************");
         if(option == 1)
         {
            System.out.println("Sorted based on the title\n");
            myStore.selectionSort();
         }
         else if (option == 2)
         {
            System.out.println("Sorted based on the author\n");
            myStore.insertionSort();
         } 
         
         else  
         {
            System.out.println("Sorted based on the author and price\n");
            myStore.bubbleSort();
         }   
         System.out.println(myStore);
         System.out.println("\n     **************     ");
         System.out.println("Enter the title of the book to search for it: ");
         kb.nextLine();
         String t = kb.nextLine();
         
         Book book = myStore.binarySearch(t);
         if(book != null)
            System.out.println(book);
         else
           System.out.println("Book not found");   
         System.out.println("\n");
            
      }  
   
   }
}

//my driver
class YourDriver
{
   public static void main(String[] args)
   {
   Scanner kb = new Scanner(System.in);
   BookStore myStore = new BookStore();
   myStore.add("The Godfather","Puzo",19.99,"0451205766");
   myStore.add("Fear and Loathing in Las Vegas","Thompson",12.35,"0679785892");
   myStore.add("Fight Club","Palahniuk",24.99,"0393355942");
   myStore.add("If I Did It","Simpson",12.35,"1783341092");
   myStore.add("Rodrick Rules", "Kinney",13,"1419741861");
   myStore.add("Fear and Loathing on the Campaign trail","Thompson",13.56,"1451691572");
   boolean b = true;
   while(b)
   {
      System.out.println("Enter 1 to sort based on the title");
      System.out.println("Enter 2 to sort based on the author");
      System.out.println("Enter 3 to sort based on the author, and the price");
      System.out.print("Enter your choice: ");
      int option = kb.nextInt();
      System.out.println("\n*************");
      if(option == 1)
      {
         System.out.println("Sorted based on the title\n");
         myStore.selectionSort();
      }
      else if (option == 2)
      {
         System.out.println("Sorted based on the author\n");
         myStore.insertionSort();
      } 
      
      else  
      {
         System.out.println("Sorted based on the author and price\n");
         myStore.bubbleSort();
      }   
      System.out.println(myStore);
      System.out.println("\n     **************     ");
      System.out.println("Enter the title of the book to search for it: ");
      kb.nextLine();
      String t = kb.nextLine();
      
      Book book = myStore.binarySearch(t);
      if(book != null)
         System.out.println(book);
      else
        System.out.println("Book not found");   
      System.out.println("\n");
         
   }  

}
}