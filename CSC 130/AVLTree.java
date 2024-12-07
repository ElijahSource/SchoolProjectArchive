//Elijah ****** csc 130
import java.io.*;
import java.util.*;

class Node{
    int key;//initialize node variables
    int height;
    Node left;
    Node right;

    Node(int data){//create node default values
        key = data;
        height = 1;
        left = null;
        right = null;
    }
}

public class AVLTree{
    private Node root;

    public static void main(String[] args){
        AVLTree avlTree = new AVLTree();//create avltree 

        try{
            File file = new File("src/input.txt");//Initialize file to read from and scanner.  if error delete src/.  my error needs src/ but some dont.
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextInt()){//while there are more integers key = next integer and key is inserted into tree
                int key = scanner.nextInt();
                avlTree.root = avlTree.insert(avlTree.root, key);
            }

            scanner.close();//exit scanner
        } 
        catch (IOException e){//catch and throw errors
            e.printStackTrace();
        }

        try{
            File outputFile = new File("src/output.txt");//initialize output file and writer to said file.  if error delete src/.  my error needs src/ but some dont.
            FileWriter writer = new FileWriter(outputFile);

            avlTree.display(avlTree.root, writer);//display avl tree in level order to initialized file with writer

            writer.close();//close writer
        } 
        catch (IOException e){//catch and throw errors
            e.printStackTrace();
        }
    }

    private Node rebalance(Node node){//non recursive rebalance function
        node.height = Math.max(height(node.left), height(node.right)) + 1;//update height
        int balance = getBalance(node);

        //do rotations as necessary
        if (balance > 1){
            if (getBalance(node.left) >= 0){//if left heavy outside
                return rightRotate(node);//right rotate
            } 
            else{//if left heavy inside left rotate then right rotate
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
        } 
        else if(balance < -1) 
       {
            if (getBalance(node.right) <= 0){//if right heavy outside return left rotate
                return leftRotate(node);
            } 
            else{//if right heavy inside right rotate then left rotate
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
        }
        return node;//if no rotation return node
    }
    private Node insert(Node node,int key){//non tail recursive code for inserting a node
        if (node == null){//when insert happens set height to 0 for the node
            return new Node(key);
        }
        if (key < node.key){
            node.left = insert(node.left, key);
        } 
        else{
            node.right = insert(node.right, key);
        } 
        node = rebalance(node);//update heights and rebalance
        return node;
    }

    private Node leftRotate(Node x){
        Node y = x.right;
        Node T2 = y.left;
        
        y.left = x;//perform rotation
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;//update heights
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    private Node rightRotate(Node x){
        Node y = x.left;
        Node T2 = y.right;

        y.right = x;//perform rotation
        x.left = T2;
      
        x.height = Math.max(height(x.left), height(x.right)) + 1;//update heights
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    private int height(Node node){//return height
        if (node == null){
            return 0;
        }
        return node.height;
    }

    private int getBalance(Node node){//find balance value
        if (node == null){
            return 0;
        }
        return height(node.left)-height(node.right);
    }

    private void display(Node root, FileWriter writer)throws IOException{//display level order
        if (root == null){
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);//add root to queue 

        while (!queue.isEmpty()){//while queue isnt empty
            int levelSize = queue.size();  //find number of nodes at current level

            for (int i = 0; i < levelSize; i++){//for nodes in current level print 
                Node tempNode = queue.poll();//temp node = head of level queue  

                writer.write("Node: " + tempNode.key + ", Height: " + tempNode.height + ", Balance Factor: " + getBalance(tempNode) + " ");//print node, height, and balance factor

                if (tempNode.left != null){//if node has left child add left node to queue
                    queue.add(tempNode.left);
                }
                if (tempNode.right != null){//if node has right child add right node to queue
                    queue.add(tempNode.right);
                }
            }

            writer.write("\n");//insert page break for next level
        }
    }
}
