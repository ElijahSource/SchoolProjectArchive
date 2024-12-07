//Elijah ******, CSC 130, 9/20/2023
import java.io.*;
class Node
{//Node class setters and getters
    String data;
    Node next;
    public Node(String data) {
        this.data = data;
        this.next = null;
    }
    void setData(String data){
        this.data=data;
    }
    String getData( ){
        return data;
    }

    void setNext(Node next)
    {
        this.next=next;
    }
    Node getNext()
    {
        return next;
    }
}

class LinkedList
{
    private Node head;

    public LinkedList()
    {
        this.head = null;
    }

    void add(String data)//Function for adding anything to linkedlist
    {
        Node newNode = new Node(data);
        if (head == null)
        {
            head = newNode;
        }
        else
        {
            Node curr = head;
            while (curr.getNext() != null)
            {
                curr = curr.getNext(); //Gets next value while there is still a next value to get stops when there isn't
            }
            curr.setNext(newNode);
        }
    }


    void traverseLinkedList()//traverse function
    {
        Node curr = head;
        while (curr != null)
        {
            System.out.print(curr.getData()+" ");//Prints name with a space then sets node for next name
            curr = curr.getNext();
        }
    }

    public LinkedList split()//Splits list into two equal halves
    {
        LinkedList myList2 = new LinkedList();
        if (head == null || head.getNext() == null) {
            return myList2;
        }

        Node curr = head;
        int size = 0;
        while (curr != null) {
            size++;//Runs through list adding to a variable which tracks the # of items
            curr = curr.getNext();
        }

        int halfSize = size/2;//halves list size so we know midpoint to stop

        curr = head;

        for (int i = 0; i<halfSize - 1; i++) {
            curr = curr.getNext();
        }

        myList2.head = curr.next;
        curr.next=null;
        return myList2;
    }
    void merge(LinkedList list2)//Recombines lists
    {
        if(head==null)
        {
            head=list2.head;
        }
        else if(list2.head!=null)
        {
            Node curr=head;
            while (curr.getNext()!=null)
            {
                curr=curr.getNext();
            }
            curr.setNext(list2.head);
        }
    }
}

class Driver
{
    public static void main(String[] args) {
        LinkedList myList1 = new LinkedList();//Initialization of list 1
        try (BufferedReader reader = new BufferedReader(new FileReader("src/input.txt")))//!!!If there are problems you may have to delete "src/" and leave "input.txt".  Would throw error in intellij ide if written as just "input.txt" will throw errors in notepad++ if set to src/input.txt.//Buffered reader to read from file.
        {
            String line;
            while ((line = reader.readLine()) != null)//while theres a line to read add it to the list
            {
                myList1.add(line);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        System.out.println("\nFull List:");//in this block Displaying each version of the list and calling the functions to change the list.
        myList1.traverseLinkedList();//Print full list

        LinkedList myList2 = myList1.split();//Call to split method
        System.out.println("\n\nSplit list part 1:");
        myList1.traverseLinkedList();//Traverse split list first half

        System.out.println("\n\nSplit list part 2:");
        myList2.traverseLinkedList();//Traverse split list second half

        myList1.merge(myList2);//Call to merge list
        System.out.println("\n\nMerged complete list:");
        myList1.traverseLinkedList();//traverse full merged list
    }
}