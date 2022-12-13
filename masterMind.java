//secret code = 023370
package cs101;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class masterMind {

    static int directHit;
    static int indirectHit;
    static int realNumber;
    
    static ArrayList<Integer> directHits = new ArrayList<Integer>();
    static ArrayList<Integer> indirectHits = new ArrayList<Integer>();
    static ArrayList<Integer> guesses = new ArrayList<Integer>();

    static Random rand = new Random();
    
    public static void  getFeedback(){ 
        Scanner keyboard = new Scanner(System.in);
        
        String line;
        String[] lineVector;

        System.out.print("Enter feedback (direct hits,indirect hits): ");
        line = keyboard.nextLine(); //reads 1,2 for example

        //separate all values by comma
        lineVector = line.split(",");

        //parsing the values to Integer
        directHit = Integer.parseInt(lineVector[0]);
        indirectHit = Integer.parseInt(lineVector[1]);

        directHits.add(directHit);
        indirectHits.add(indirectHit);
    }
    
    
    public static void main(String[] args) {
        
        System.out.println("My guess is 001122");
        
        getFeedback();

        
        

        }

    }
