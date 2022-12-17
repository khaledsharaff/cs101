import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static String position1;
    static String position2;
 //   static String position3;
   // static String position4;
    static String position5;
    static String position6;
    static int directHit;
    static int indirectHit;
    static String guessedNumber="";
    static ArrayList<Integer> directHits = new ArrayList<>();
    static ArrayList<Integer> indirectHits = new ArrayList<>();
    static ArrayList<Integer> correctNumber = new ArrayList<>();
    static ArrayList<String> guesses = new ArrayList<>();
    static ArrayList<Integer> position1Numbers = new ArrayList<>();

    public static String numberGenerator() {
        Random rand = new Random();
        int count=0;
        String number = "";

        for(int x=0;x<6;x++) {

            int digit = position1Numbers.get(rand.nextInt(position1Numbers.size()));
            for (int i = 0; i < position1Numbers.size(); i++) {
                if (position1Numbers.get(i) == digit) {
                    position1Numbers.remove(i);
                    break;
                }
            }
            number = number + digit;
        }
        guesses.add(number);
        return number;
    }

    public static void  getFeedback(){

        String line;
        String[] lineVector;

        Scanner keyboard = new Scanner(System.in);
        line = keyboard.nextLine(); //read 1,2

        //separate all values by comma
        lineVector = line.split(",");

        //parsing the values to Integer
        directHit=Integer.parseInt(lineVector[0]);
        indirectHit=Integer.parseInt(lineVector[1]);

        directHits.add(directHit);
        indirectHits.add(indirectHit);
        correctNumber.add(directHit+indirectHit);
    }
    public static boolean isConsistent1(){
        // isConsistent1() is true if user is being consistent
        // this method is useful only for the part where the program guesses 000000...999999

        int sumOfCN=0; //sum of correct numbers
        for(int i=0;i<directHits.size();i++){
            sumOfCN += directHits.get(i);
        }
        if(sumOfCN>6){
            return false;
        }
        return true;
    }
    public static boolean isConsistent2(){ //correctnumber can never be higher than 6
        for(int i=0;i<correctNumber.size();i++){
            if(correctNumber.get(i)>6){
                return false;
            }
        }
        return true;
    }
    public static boolean isConsistent3(){ //after first guesses, correctnumber cannot be lower than 6
        for(int i=0;i<correctNumber.size();i++){
            if(correctNumber.get(i)<6){
                return false;
            }
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

    public static boolean isCorrect(){
        if(directHit==6 && indirectHit==0){ //if number has found
            System.out.println("I Found Your Number!!! The Number is "+guessedNumber);
            return true;
        }
        guesses.add(guessedNumber);
        return false;
    }

    public static void addFoundNumbers(){
        for(int y=0;y<guesses.size();y++){
            int digit=0;
            if(correctNumber.get(y)>0){
                digit = Integer.valueOf(guesses.get(y))/111111;
                //for example, 333333/111111 = 3. Now program knows which number is used in guess
            }
            for(int m=1;m<=correctNumber.get(y);m++){
                position1Numbers.add(digit);
            }

        }
    }

    public static void main(String[] args) {


        for (int i = 0; i < 10; i++) {
            for (int a = 0; a < 6; a++) {
                guessedNumber += i;
            }
            System.out.println("My Guess is " + guessedNumber);
            getFeedback();
            if (!isConsistent1()) {
                System.out.println("You are being inconsistent!! Please start all over again.");
                break;
            }
            if (!isConsistent2()) {
                System.out.println("This is a six digit number! Sum of directhits and indirecthits cannot be higher than 6! Please start all over again.");
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


        addFoundNumbers();

        directHits.clear();           //clean those arraylists since we do not need previous records anymore
        indirectHits.clear();
        correctNumber.clear();
        guesses.clear();

        guessedNumber = numberGenerator();
        System.out.println(guessedNumber);

        getFeedback();

        guessedNumber = "";
        guessedNumber = guessedNumber + guesses.get(0).charAt(5) + guesses.get(0).charAt(1) + guesses.get(0).charAt(2) +
                guesses.get(0).charAt(3) + guesses.get(0).charAt(4) + guesses.get(0).charAt(0);

        guesses.add(guessedNumber);

        System.out.println(guessedNumber);

        getFeedback();

        boolean condition = true;
        if (directHits.get(0) - directHits.get(1) != 2 && directHits.get(1) - directHits.get(0) != 2) {
            condition = false;
        }

        while (!condition) {
            guesses.clear();
            directHits.clear();
            indirectHits.clear();
            guessedNumber = numberGenerator();
            System.out.println(guessedNumber);

            getFeedback();

            guessedNumber = "";
            guessedNumber = guessedNumber + guesses.get(0).charAt(5) + guesses.get(0).charAt(1) + guesses.get(0).charAt(2) +
                    guesses.get(0).charAt(3) + guesses.get(0).charAt(4) + guesses.get(0).charAt(0);

            guesses.add(guessedNumber);

            System.out.println(guessedNumber);

            getFeedback();
            
            if (directHits.get(0) - directHits.get(1) != 2 && directHits.get(1) - directHits.get(0) != 2) {
                condition = false;
            }
        }

        if (directHits.get(1) - directHits.get(0) == 2) {
            char position1 = guesses.get(1).charAt(0);
            char position6 = guesses.get(1).charAt(5);
            guesses.clear();
            directHits.clear();
            indirectHits.clear();
            for (int i = 0; i < position1Numbers.size(); i++) {
                if (position1Numbers.get(i) == position1) {
                    position1Numbers.remove(i);
                    break;
                }
            }
            for (int i = 0; i < position1Numbers.size(); i++) {
                if (position1Numbers.get(i) == position6) {
                    position1Numbers.remove(i);
                    break;
                }
            }


        } else if (directHits.get(0) - directHits.get(1) == 2) {
            char position1 = guesses.get(0).charAt(0);
            char position6 = guesses.get(0).charAt(5);
            guesses.clear();
            directHits.clear();
            indirectHits.clear();
            for (int i = 0; i < position1Numbers.size(); i++) {
                if (position1Numbers.get(i) == position1) {
                    position1Numbers.remove(i);
                    break;
                }
            }
            for (int i = 0; i < position1Numbers.size(); i++) {
                if (position1Numbers.get(i) == position6) {
                    position1Numbers.remove(i);
                    break;
                }
            }
        }
        guessedNumber = "";
        guessedNumber = guessedNumber + position1 + numberGenerator() + position6;
        System.out.println(guessedNumber);

        while (!condition) {
            guesses.clear();
            directHits.clear();
            indirectHits.clear();
            numberGenerator();
            getFeedback();
        }

        if (directHits.get(1) - directHits.get(0) == 2) {
            char position2 = guesses.get(1).charAt(1);
            char position5 = guesses.get(1).charAt(4);
            guesses.clear();
            directHits.clear();
            indirectHits.clear();
            for (int i = 0; i < position1Numbers.size(); i++) {
                if (position1Numbers.get(i) == position2) {
                    position1Numbers.remove(i);
                    break;
                }
            }
            for (int i = 0; i < position1Numbers.size(); i++) {
                if (position1Numbers.get(i) == position5) {
                    position1Numbers.remove(i);
                    break;
                }
            }


        } else if (directHits.get(0) - directHits.get(1) == 2) {
            char position2 = guesses.get(0).charAt(1);
            char position5 = guesses.get(0).charAt(4);
            guesses.clear();
            directHits.clear();
            indirectHits.clear();
            for (int i = 0; i < position1Numbers.size(); i++) {
                if (position1Numbers.get(i) == position2) {
                    position1Numbers.remove(i);
                    break;
                }
            }
            for (int i = 0; i < position1Numbers.size(); i++) {
                if (position1Numbers.get(i) == position5) {
                    position1Numbers.remove(i);
                    break;
                }
            }
        }
        guesses.clear();
        guessedNumber = guessedNumber + position1 + position2 + numberGenerator() + position5 + position6;

        isCorrect();

        char position4=guesses.get(0).charAt(2);
        char position3=guesses.get(0).charAt(3);

        System.out.println(""+position1+position2+position3+position4+position5+position6);

    }
    }
