//Elijah ****** csc 130 11/28/23
import java.io.*;
import java.util.*;

class Node {//creating node class to take data
    int data;
    Node left, right;

    Node(int value) {
        data = value;
        left = null;
        right = null;
    }
}

class BinarySearchTree {
    Node root;

    BinarySearchTree() {
        root = null;
    }

    void insert(int key) {
        root = insertNode(root, key);//starts insert function with correct paramaters
    }

    Node insertNode(Node root, int key) {//recursive insert algorithm
        if (root == null) {//if list empty
            root = new Node(key);//set new node to root
            return root;
        }
        if (key < root.data)//if key less than root recursively insert on left
            root.left = insertNode(root.left, key);
        else if (key > root.data)//if key greater than root recursively insert on right
            root.right = insertNode(root.right, key);
        return root;
    }

    void delete(int key) {
        root = remove(root, key);
    }

	Node remove(Node root, int key) {//recursive algorithm for the deletion
        if (root == null) {//
            return new Node(key);
        }

        if (key < root.data) {//if key less than root recursively go through left
            root.left = remove(root.left, key);
        } 
        else if (key > root.data) {//if key greater than root recursively go through right
            root.right = remove(root.right, key);
        } 
        else { //node with only one child or no child
            if (root.left == null) {
                return root.right;
            }
            else if (root.right == null) {
                return root.left;
            }
            
            //node with two children: Get the inorder successor (smallest in the right subtree)
            root.data = minValue(root.right);
            //delete inorder successor
            root.right = remove(root.right, root.data);
        }
        return root;
    }

    int minValue(Node root) {//return minimum value by going to the left most and lowest node
        int minValue = root.data;
        while (root.left != null) {
            minValue = root.left.data;
            root = root.left;
        }
        return minValue;
    }

    void printPreorder(Node node, PrintWriter writer) {//print preorder
        if (node != null) {
            writer.print(node.data + " ");//print data 
            printPreorder(node.left, writer);//send left to have the same done
            printPreorder(node.right, writer);//send right to have the same done
        }
    }

    void printInorder(Node node, PrintWriter writer) {//print inorder 
        if (node != null) {
            printInorder(node.left, writer);//send left for print
            writer.print(node.data + " ");//print middle
            printInorder(node.right, writer);//send right for print
        }
    }

    void printPostorder(Node node, PrintWriter writer) {//print post order
        if (node != null) {
            printPostorder(node.left, writer);//send left to be printed
            printPostorder(node.right, writer);//send right to be printed
            writer.print(node.data + " ");//print node 
        }
    }

	void printBreadth(Node root, PrintWriter writer) {//breadth search format
        if (root == null) return;//if list empty return

        Queue<Node> queue = new LinkedList<>();//initialize queue
        queue.add(root);//add root to queue

        while (!queue.isEmpty()) {
            int nodeCount = queue.size();
            while (nodeCount > 0) {//while there are nodes in queue
                Node tempNode = queue.poll();
                writer.print(tempNode.data + " ");//print node and space
                
                if (tempNode.left != null)
                    queue.add(tempNode.left);
                
                if (tempNode.right != null)
                    queue.add(tempNode.right);
                
                nodeCount--;//decrement number of nodes in queue
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        try {
            File inputFile = new File("src/input.txt");//src needed for my editor if issues remove src/
            Scanner scanner = new Scanner(inputFile);//initializes scanning from input file

            BinarySearchTree tree = new BinarySearchTree();//initialize tree

            while (scanner.hasNext()) {
                if (scanner.hasNextInt()) {
                    int num = scanner.nextInt();
                    tree.insert(num);//insert next number to tree
                } 
                else {
                    String command = scanner.next();//if not int set to a string variable
                    if (command.equals("delete")) {//if string is delete keyword
                        int numToDelete = scanner.nextInt();
                        tree.delete(numToDelete);//delete next value
                    }
                }
            }

            scanner.close();

            File outputFile = new File("src/output.txt");//src needed for my editor if issues remove src/ 
            PrintWriter writer = new PrintWriter(outputFile);//intialize writing to output file

            writer.println("Preorder Traversal: ");//preorder
            tree.printPreorder(tree.root, writer);
            
            writer.println("\n\nInorder Traversal: ");//inorder
            tree.printInorder(tree.root, writer);
            
            writer.println("\n\nPostorder Traversal: ");//postorder
            tree.printPostorder(tree.root, writer);
            
            writer.println("\n\nBreadth Search Format: ");//breadth
            tree.printBreadth(tree.root, writer);

            writer.close();
            System.out.println("Output written to output.txt");//notification to console that program ran successfully

        } 
        catch (FileNotFoundException e) {//if exception print error
            e.printStackTrace();
        }
    }
}


