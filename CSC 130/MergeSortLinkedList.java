//Elijah ****** 11/6/2023 csc 130
class Node{//node for linked list
    int data;
    Node next;

    public Node(int data){
        this.data = data;
        this.next = null;
    }
}

class LinkedList{//single linked list for data structure
    Node head;

    public void append(int data){
        if (head == null){
            head = new Node(data);
        } 
        
        else{
            Node curr = head;
            
            while (curr.next != null){
                curr = curr.next;
            }
            
			curr.next = new Node(data);
        }
    }

    public void printList(){//function to print list
        Node curr = head;
        while (curr != null){//iterate through list printing data with a space after 
            System.out.print(curr.data + " ");//print each value + space
            curr = curr.next;
        }
        System.out.println();//print entire list 
    }
}

public class MergeSortLinkedList{
	
    public static LinkedList mergeSort(LinkedList list){//recursive merge sort function
    	if (list.head == null){
    		return null;//if list empty return null
    	}
        if (list.head.next == null){
            return list; //base case of single node
        }

        LinkedList left = new LinkedList();
        LinkedList right = new LinkedList();

        split(list, left, right);//split list in half

        mergeSort(left);//sort halves 
        mergeSort(right);

        list.head = merge(left.head, right.head);
        return list;
    }

    public static Node merge(Node a, Node b){//recursive merge function
        Node result = null;

        if (a == null){//if a empty return b base case
            return b;
        }
        if (b == null){//if b empty return a base case
            return a;
        }

        if (a.data <= b.data){//if a less than or equal to b 
            result = a;
            result.next = merge(a.next, b);//merge rest of list a with list b 
        } 
        else{//if a greater than or equal to b
            result = b;
            result.next = merge(a, b.next); // merge rest of list b with list a 
        }

        return result;//return merged result
    }

    public static void split(LinkedList list, LinkedList a, LinkedList b){//Function to split the list in half
        if (list.head == null || list.head.next == null){
            return; //if the source list is empty or has one node return
        }

        int nodeCount = countNodes(list);//set node count = to number of nodes 
        int middle = nodeCount / 2;//middle = number of nodes/2

        Node curr = list.head; 

        for (int i = 0; i < middle - 1; i++){//moves curr to the middle 
            curr = curr.next;
        }

        a.head = list.head; // sets a list 
        b.head = curr.next; // sets b list
        curr.next = null; // Separates lists
    }

    public static int countNodes(LinkedList list){// function to find middle of list by iterating through list
        int i = 0;
        Node curr = list.head;
        while (curr != null){
            i++;
            curr = curr.next;
        }
        return i;
    }

    public static void main(String[] args){
        LinkedList list = new LinkedList();//initialize linked list
        list.append(13);//adding values to list
        list.append(31);
        list.append(3);
        list.append(333);
        list.append(133);

        System.out.println("Original Linked List:");//original
        list.printList();

        mergeSort(list);//sorting list

        System.out.println("Sorted Linked List:");//sorted
        list.printList();
    }
}
