//Elijah ****** 10/25/2023 csc 130
class Queue{//custom queue class
    private Node front;
    private Node rear;
    private int size;

    public Queue(){
        front = null;
        rear = null;
        size = 0;
    }

    private static class Node{//node class
        private int data;
        private Node next;

        
        public Node(int data){
            this.data = data;
            this.next = null;
        }

        public int getData(){
            return data;
        }

        public Node getNext(){
            return next;
        }

        public void setNext(Node next){
            this.next = next;
        }
    }
    

    public void enqueue(int item){
        Node newNode = new Node(item);
        if (isEmpty()){
            front = newNode;
            rear = newNode;
        } 
        else{
            rear.setNext(newNode);
            rear = newNode;
        }
        size++;//increases size variable as queue grows 
    }

    public int dequeue(){
        int data = front.getData();
        front = front.getNext();
        size--;//decreases size if queue shrinks
        if (isEmpty()){
            rear = null;
        }
        return data;
    }

    public int peek(){
        return front.getData();
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public int findMax(){//find max in queue for radix sort
        int max = front.getData();
        Node curr = front.getNext();

        while (curr != null){
            int currData = curr.getData();
            if (currData > max){//if greater swap to max iterate through queue
                max = currData;
            }
            curr = curr.getNext();
        }

        return max;
    }
    public void reverse(){
        Node prev = null;
        Node curr = front;
        Node next;

        while (curr != null){
            next = curr.getNext();
            curr.setNext(prev);
            prev = curr;
            curr = next;
        }

        Node temp = front;
        front = rear;
        rear = temp;
    }

}

class Radix{
    static void countSort(Queue queue, int exp){//count sort for use in radix sort function
        Queue[] output = new Queue[10]; //creates 10 queues for each digit
        
        for (int i = 0; i < 10; i++){
            output[i] = new Queue();
        }

        while (!queue.isEmpty()){//distributes elements into the output queues
            int item = queue.dequeue();
            int digit = (item / exp) % 10;
            output[digit].enqueue(item);
        }

        for (int i = 0; i < 10; i++){//copies elements back to the original queue in order
            while (!output[i].isEmpty()){
                queue.enqueue(output[i].dequeue());
            }
        }
    }

    static void radixsort(Queue queue){
        int m = queue.findMax();

        for (int exp = 1; m / exp > 0; exp *= 10){//iterates through radix sort algorithm changing digit and then countsorting
            countSort(queue, exp);
        }
    }

    static void print(Queue queue){//iterates through queue and prints
        while (!queue.isEmpty()){
            System.out.print(queue.dequeue() + " ");
        }
    }
    
    static void reset(Queue queue){//adds initial values to empty queue
        queue.enqueue(270);
        queue.enqueue(35);
        queue.enqueue(96);
        queue.enqueue(42);
        queue.enqueue(502);
        queue.enqueue(24);
        queue.enqueue(2);
        queue.enqueue(66);
    }
}

public class Driver{
	public static void main(String[] args){
        Queue queue = new Queue();//initializes queue
        Radix.reset(queue);//adds initial values to queue 
        
        System.out.println("Original list: ");
        Radix.print(queue);//prints unsorted list 
        System.out.println("\n");
        
        Radix.reset(queue);
        Radix.radixsort(queue);//sort ascending 
        System.out.println("Ascending order: ");
        Radix.print(queue);//print ascending
        System.out.println("\n");
        
        Radix.reset(queue);//resets to initial values
        Radix.radixsort(queue);//sorts ascending
        queue.reverse();//reverses sorted queue
        
        System.out.println("descending order: ");
        Radix.print(queue);//print descending
        
    }
}
