//secret code = 023370
package cs101;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class masterMind {

    static int directHit;
    static int indirectHit;
    static int guessedNumber="";
    
    static ArrayList<Integer> directHits = new ArrayList<>();
    static ArrayList<Integer> indirectHits = new ArrayList<>();
    static ArrayList<Integer> correctNumber = new ArrayList<>();
    static ArrayList<String> guesses = new ArrayList<>();

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
        correctNumber.add(directHit+indirectHit);
    }
    public static boolean isCorrect(){
        if(indirectHit==0 && directHit==6){ //if number has found
            System.out.println("I Found Your Number!!! The Number is "+guessedNumber);
            return true;
        }
        guesses.add(guessedNumber);
        return false;
    }
    
    
    public static void main(String[] args) {
        
        for(int i=0;i<10;i++){
            for(int a=0;a<6;a++){
                guessedNumber += i;
            }
            System.out.println("My Guess is "+guessedNumber);
            getFeedback();
            if(isCorrect()){
                break;
            }
            guessedNumber = "";
        }

        
        

        }

    }
