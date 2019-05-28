//import what is needed
import java.io.*;
import java.util.Scanner;

/**
 * @author Lin Shi
 * Last Modified: May 27, 2019
 *
 * Overview:
 * this is the driver for the huffman Tree
 * it will read the file and write the output to output.txt
 * it will print the original message, frequency table, code table, and encoded message to console
 * it will print the output line to output.txt
 *
 * Instruction:
 * The code will only accept one line of message, since the directions
 * said it was a message.
 */


public class Demo {

    // main function
    public static void main(String[] args){

        //create a instance of class
        HuffmanTree code = new HuffmanTree();

        //create what is needed
        char[] charInput = new char[10000];
        int[] freq = new int[256];
        String s = "";

        //initialize files
        File inputFile = new File("input.txt");
        File outputFile = new File("output.txt");

        // a try catch to read the line
        try {
            Scanner input = new Scanner(inputFile);
            while(input.hasNextLine()){
                s = input.nextLine();
                charInput = s.toCharArray();
            }

            // create a frequency table, add everytime that ascii number if called
            for(int i = 0; i< charInput.length; i ++){
                freq[charInput[i]]++;
            }

            //close file
            input.close();
            //catch
        } catch(Exception e) {
            e.printStackTrace();
        }

        // all format
        System.out.println("Original Message:");
        System.out.println(s + "\n");

        // it will build tree and print code table
        System.out.println("Code Table: ");
        code.buildTree(freq);

        System.out.println("\nFrequency Table: ");
        code.printFrequency(freq);

        String message = code.printMessage(s);
        System.out.println("\nEncode Message to binary: ");
        System.out.println(message);

        //the decode
        String temp = code.decode(message);
        // a try catch to write to output file
        try{
            PrintWriter output = new PrintWriter(outputFile);
            output.println(temp);
            output.close();
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }


    }
}
