package cs101;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class masterMind {

    static int directHit;
    static int indirectHit;
    static String guessedNumber = "";

    static ArrayList<Integer> directHitsHistory = new ArrayList<>();
    static ArrayList<Integer> indirectHitsHistory = new ArrayList<>();
    static ArrayList<Integer> correctNumber = new ArrayList<>();
    static ArrayList<String> guesses = new ArrayList<>();
    static ArrayList<Integer> position1Numbers = new ArrayList<>();
    static ArrayList<Integer> position2Numbers = new ArrayList<>();
    static ArrayList<Integer> position3Numbers = new ArrayList<>();
    static ArrayList<Integer> position4Numbers = new ArrayList<>();
    static ArrayList<Integer> position5Numbers = new ArrayList<>();
    static ArrayList<Integer> position6Numbers = new ArrayList<>();

    static Random rand = new Random();

    // method to get feedback (input) from user (directHits,indirectHits)
    public static void getFeedback() {
        Scanner keyboard = new Scanner(System.in);

        String line;
        String[] lineVector;

        System.out.print("Enter feedback: ");
        line = keyboard.nextLine(); // reads 1,2 for example

        // separate all values by comma
        lineVector = line.split(",");

        // parsing the values to Integer
        directHit = Integer.parseInt(lineVector[0]);
        indirectHit = Integer.parseInt(lineVector[1]);

        directHitsHistory.add(directHit);
        indirectHitsHistory.add(indirectHit);
        correctNumber.add(directHit + indirectHit);
    }

    // if computer guesses the number correctly
    public static boolean isCorrect() {
        if (indirectHit == 0 && directHit == 6) { // if feedback (6,0)
            System.out.println("I found your number! It is " + guessedNumber);
            return true;
        }
        guesses.add(guessedNumber);
        return false;
    }

    public static boolean isConsistent1() {
        // isConsistent1() is true if user is being consistent with the feedbacks
        // this method is useful only for the part where the program guesses
        // 000000...999999

        int sumOfCN = 0; // sum of correct numbers
        for (int i = 0; i < directHitsHistory.size(); i++) { 
            sumOfCN += directHitsHistory.get(i); //sumOfCN = sumOfCN + directHitsHistory.get(i)
        }
        if (sumOfCN > 6) {
            return false;
        }
        return true;
    }

    public static boolean isConsistent2() { // correct number CANNOT be higher than 6
        for (int i = 0; i < correctNumber.size(); i++) {
            if (correctNumber.get(i) > 6) {
                return false;
            }
        }
        return true;
    }

    public static boolean isConsistent3() { // after first guesses, correct number CANNNOT be lower than 6
        for (int i = 0; i < correctNumber.size(); i++) {
            if (correctNumber.get(i) < 6) {
                return false;
            }
        }
        return true;
    }

    public static boolean allDigitsFound() {
        // works when all digits in the secret number is found

        int sumOfCN = 0; // sum of correct numbers
        for (int i = 0; i < correctNumber.size(); i++) {
            sumOfCN += correctNumber.get(i); //sumOfCN = sumOfCN + correctNumber.get(i)
        }
        if (sumOfCN == 6) {
            return true;
        }
        return false;
    }

    public static void addFoundNumbers() { // adds found numbers in position arrays
        int digit = 0;
        
        System.out.print("My guess is ");

        for (int y = 0; y < guesses.size(); y++) {
            if (correctNumber.get(y) > 0) {
                digit = Integer.valueOf(guesses.get(y)) / 111111;
                // for example, 333333/111111 = 3. Now program knows which number is used in
                // guess
            }

            for (int m = 1; m <= correctNumber.get(y); m++) {
                position1Numbers.add(digit);
                position2Numbers.add(digit);
                position3Numbers.add(digit);
                position4Numbers.add(digit);
                position5Numbers.add(digit);
                position6Numbers.add(digit);
                
            }
            
        }
        

        
    }
    public static void changeDigitPositions() {
    // Initialize variables to track the number of direct and indirect hits for each guess
    int directHit1 = 0;
    int indirectHit1 = 0;
    int directHit2 = 0;
    int indirectHit2 = 0;
    int directHit3 = 0;
    int indirectHit3 = 0;
    int directHit4 = 0;
    int indirectHit4 = 0;

    // Make the first guess and get the feedback
    String guess1 = position6Numbers.get(0).toString() + position6Numbers.get(1).toString() + position6Numbers.get(2).toString() + position6Numbers.get(3).toString() + position6Numbers.get(4).toString() + position6Numbers.get(5).toString();
    System.out.println("My guess is " + guess1);
    getFeedback();
    directHit1 = directHit;
    indirectHit1 = indirectHit;

    // Make the second guess and get the feedback
    int temp = position6Numbers.get(0);
    position6Numbers.set(0, position6Numbers.get(5));
    position6Numbers.set(5, temp);
    String guess2 = position6Numbers.get(0).toString() + position6Numbers.get(1).toString() + position6Numbers.get(2).toString() + 
    position6Numbers.get(3).toString() + position6Numbers.get(4).toString() + position6Numbers.get(5).toString();
    System.out.println("My guess is " + guess2);
    getFeedback();
    directHit2 = directHit;
    indirectHit2 = indirectHit;

    // Make the third guess and get the feedback
    if (directHit2 > directHit1) {
        temp = position6Numbers.get(1);
        position6Numbers.set(1, position6Numbers.get(2));
        position6Numbers.set(2, temp);
        String guess3 = position6Numbers.get(0).toString() + position6Numbers.get(1).toString() + position6Numbers.get(2).toString() + 
        position6Numbers.get(3).toString() + position6Numbers.get(4).toString() + position6Numbers.get(5).toString();
        System.out.println("My guess is " + guess3);
        getFeedback();
        directHit3 = directHit;
        indirectHit3 = indirectHit;
    } else {
        temp = position6Numbers.get(1);
        position6Numbers.set(1, position6Numbers.get(2));
        position6Numbers.set(2, temp);

        temp = position6Numbers.get(0);
        position6Numbers.set(0, position6Numbers.get(5));
        position6Numbers.set(5, temp);
        String guess3 = position6Numbers.get(0).toString() + position6Numbers.get(1).toString() + position6Numbers.get(2).toString() + 
        position6Numbers.get(3).toString() + position6Numbers.get(4).toString() + position6Numbers.get(5).toString();
    }
    // Make the fourth guess and get the feedback
    if (directHit3 > directHit2) {
        temp = position6Numbers.get(3);
        position6Numbers.set(3, position6Numbers.get(4));
        position6Numbers.set(4, temp);
        String guess4 = position6Numbers.get(0).toString() + position6Numbers.get(1).toString() + position6Numbers.get(2).toString() +
        position6Numbers.get(3).toString() + position6Numbers.get(4).toString() + position6Numbers.get(5).toString();
        System.out.println("My guess is " + guess4);
        getFeedback();
        directHit4 = directHit;
        indirectHit4 = indirectHit;
    } else {
        temp = position6Numbers.get(3);
        position6Numbers.set(3, position6Numbers.get(4));
        position6Numbers.set(4, temp);
        temp = position6Numbers.get(0);
        position6Numbers.set(0, position6Numbers.get(5));
        position6Numbers.set(5, temp);
        String guess4 = position6Numbers.get(0).toString() + position6Numbers.get(1).toString() + position6Numbers.get(2).toString() + 
        position6Numbers.get(3).toString() + position6Numbers.get(4).toString() + 
        position6Numbers.get(5).toString();
        System.out.println("My guess is " + guess4);
        getFeedback();
        directHit4 = directHit;
        indirectHit4 = indirectHit;
}
    }

    public static void main(String[] args) {
        
        for (int i = 0; i < 10; i++) { // for-loop to generate guesses --> 000000...999999
            for (int a = 0; a < 6; a++) {
                guessedNumber += i;
            }

            System.out.println("My guess is " + guessedNumber);
            getFeedback();
            if (!isConsistent1()) {
                System.out.println("You are being inconsistent!! Please start all over again.");
                break;
            }
            if (isCorrect()) {
                break;
            }
            guessedNumber = "";
            if (allDigitsFound()) {
                break;
            }
        }

        addFoundNumbers(); //123456
        
        directHitsHistory.clear(); // clean those arraylists since we do not need previous records anymore
        indirectHitsHistory.clear();
        correctNumber.clear();
        guesses.clear();

        changeDigitPositions();
        
        
        

    }

}
