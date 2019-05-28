//import what is needed
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * @author Lin Shi
 * Last Modified : May 27, 2019
 *
 * Overview:
 * this class runs the most of the huffman tree
 * it will encode and decode
 * encode using hashmap to find the keys
 * use priority queue to build tree
 * decode using the tree and some logic
 *
 */

public class HuffmanTree {

    //initialize what is needed
    private static final int R = 256;
    public Node parent;

    //initialize the hashmap
    HashMap<Character, String> map = new HashMap<>();

    //constructor
    public HuffmanTree() {
    }

    //create a private class of node
    private static class Node implements Comparable<Node> {

        //initialize char, the frequency, left and right
        private final char ch;
        private final int freq;
        private final Node left, right;

        //constructor so we can change it to type node
        public Node(char ch, int freq, Node left, Node right) {
            this.ch = ch;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }

        public int compareTo(Node that) {
            return this.freq - that.freq;
        }
    }

    // used to build the tree
    public void buildTree(int[] freq) {

        // use priority queue
        PriorityQueue<Node> queue = new PriorityQueue<>();

        //size of the ascii table to make sure everything is caught
        for (char i = 0; i < R; i++) {
            //insert if it is greater than 0
            if (freq[i] > 0) {
                queue.add(new Node(i, freq[i], null, null));
            }
        }

        //when the queue is only the size of 1
        if (queue.size() == 1) {
            //check what is there
            if (freq['\0'] == 0) {
                queue.add(new Node('\0', 0, null, null));
            } else {
                queue.add(new Node('\1', 0, null, null));
            }
        }

        //when the queue is greater than 1
        while (queue.size() > 1) {
            // get the left
            Node left = queue.peek();
            queue.poll();

            //get the right
            Node right = queue.peek();
            queue.poll();

            //define the parent
            parent = new Node('\0', left.freq + right.freq, left, right);
            queue.add(parent);
        }

        //call print code to print the code table
        printCode(parent, "");

        return;
    }

    //print the code table
    public void printCode(Node root, String s) {

        // when it is a leaf
        if (root.left == null && root.right == null) {
            //print the table
            System.out.println(root.ch + ": " + s);

            //put it in hash table
            map.put(root.ch, s);
            return;
        }

        //call printCode again
        printCode(root.left, s + "0");
        printCode(root.right, s + "1");
    }

    //print the encode message
    public String printMessage(String s) {
        //initialize what is needed
        String message = "";
        char[] array = s.toCharArray();

        //with a loop get the message
        for (int i = 0; i < array.length; i++) {
            message = (message + map.get(array[i]));
        }
        return message;
    }

    //the decode method
    public String decode(String s) {

        //initialize what is needed
        char[] array = s.toCharArray();
        int i = 0;
        String message = "";
        Node temper = parent;

        // a while loop to run through the tree
        while (i < array.length) {
            // when it is not leaf
            if (temper.left != null && temper.right != null) {

                //go to left
                if (array[i] == '0') {
                    temper = temper.left;
                    i++;

                    // go right
                } else {
                    temper = temper.right;
                    i++;
                }
            }

            //when it is a leaf
            if (temper.left == null && temper.right == null) {
                // save it in message and start from beginning
                message = message + temper.ch;
                temper = parent;
            }
        }
        return message;

    }



    //print frequency table
    public void printFrequency(int[] freq) {
        //a for loop for the size of map
        for(Character key : map.keySet()){

            //get the ascii number
            char temp = key;
            int temper = temp;

            //use the number and the key to print
            System.out.println(key + "\t" + freq[temper]);
        }
    }
}



