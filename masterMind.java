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
    static ArrayList<Integer> position1Numbers = new ArrayList<>();
    static ArrayList<Integer> position2Numbers = new ArrayList<>();
    static ArrayList<Integer> position3Numbers = new ArrayList<>();
    static ArrayList<Integer> position4Numbers = new ArrayList<>();
    static ArrayList<Integer> position5Numbers = new ArrayList<>();
    static ArrayList<Integer> position6Numbers = new ArrayList<>();

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
    
    public static boolean isConsistent1(){
        // isConsistent1() is true if user is being consistent
        // this method is useful only for the part where the program guesses 000000...999999

        int sumOfCN=0; //sum of correct numbers
        for(int i=0;i<correctNumber.size();i++){
            sumOfCN += correctNumber.get(i);
        }
        if(sumOfCN>6){
            return false;
        }
        return true;
    }
    
    public static boolean allDigitsFound(){
        //works when all digits in the secret number has found
        int sumOfCN=0; //sum of correct numbers
        for(int i=0;i<correctNumber.size();i++){
            sumOfCN += correctNumber.get(i);
        }
        if(sumOfCN==6){
            return true;
        }
        return false;
    }
    
    public static void addFoundNumbers(){ //adds found numbers in position arrays
        for(int y=0;y<guesses.size();y++){
            int digit=0;
            if(correctNumber.get(y)>0){
                digit = Integer.valueOf(guesses.get(y))/111111;
                //for example, 333333/111111 = 3. Now program knows which number is used in guess
            }
            for(int m=1;m<=correctNumber.get(y);m++){
                position1Numbers.add(digit);
                position2Numbers.add(digit);
                position3Numbers.add(digit);
                position4Numbers.add(digit);
                position5Numbers.add(digit);
                position6Numbers.add(digit);
            }

        }
    }
    
    public static void main(String[] args) {
        
        for(int i=0;i<10;i++){
            for(int a=0;a<6;a++){
                guessedNumber += i;
            }
            System.out.println("My Guess is "+guessedNumber);
            getFeedback();
           if(!isConsistent1()){
               System.out.println("You are being inconsistent!! Please start all over again.");
               break;
           }
            if(isCorrect()){
                break;
            }
            guessedNumber = "";
           if(allDigitsFound()){
               break;
           }
        }

       addFoundNumbers();

       directHits.clear(); //clean those arraylists since we do not need previous records anymore
       indirectHits.clear();
       correctNumber.clear();
       guesses.clear();

        

        }

    }
