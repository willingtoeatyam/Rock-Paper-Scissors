package rockpaperscissors;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
/*
* for each win, add 100. each draw, add 50
* if output is invalid, say so
* if option is !rating, print score*/
public class Main {
    static boolean exists = false;
    static File file = new File("rating.txt");
    static String computer;
    static boolean status = false;
    static int score = 0;
    static boolean stop = false;

    public static void main(String[] args) throws FileNotFoundException {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Hello, " + name);
        String optionList = scanner.nextLine();
        if (!optionList.equals("")){
            //do complex game
            String[] options = optionList.split(",");
            System.out.println("Okay, let's start");

            Scanner filescanner = new Scanner(file);
            score = 0;
            while (filescanner.hasNext()) {
                String playerName = filescanner.next();
                if (playerName.equals(name)) {
                    exists = true;
                    score = filescanner.nextInt();
                    break;
                }
            }
            while (!stop){
                complexGame(options);
            }
            filescanner.close();

        }else {
            System.out.println("Okay, let's start");

            Scanner filescanner = new Scanner(file);
            score = 0;
            while (filescanner.hasNext()) {
                String playerName = filescanner.next();
                if (playerName.equals(name)) {
                    exists = true;
                    score = filescanner.nextInt();
                    break;
                }
            }

            while (!stop) {
                String user = scanner.nextLine();
                if (!(user.equals("rock") || user.equals("paper") || user.equals("scissors") || user.equals("!exit") || user.equals("!rating"))) {
                    System.out.println("Invalid input");
                } else {
                    computerChoice();
                    //win or loss conditions
                    solveGame(user);
                    //output
                    outputResult(user);
                }
            }
            filescanner.close();
        }
    }
    public static void computerChoice(){
        Random random =  new Random();
        int choice = random.nextInt(3);

        if (choice == 0 ) {
            computer = "rock";
        } else if (choice == 1) {
            computer = "paper";
        } else {
            computer = "scissors";
        }
    }
    public static void solveGame(String user){
        switch (user) {
            case "rock" -> {
                if (computer.equals("paper")) {
                    status = false;
                    score += 0;
                } else if (computer.equals("scissors")) {
                    status = true;
                    score += 100;
                }
            }
            case "paper" -> {
                if (computer.equals("rock")) {
                    status = true;
                    score += 100;
                } else if (computer.equals("scissors")) {
                    status = false;
                    score += 0;
                }
            }
            case "scissors" -> {
                if (computer.equals("paper")) {
                    status = true;
                    score += 100;
                } else if (computer.equals("rock")) {
                    status = false;
                    score += 0;
                }
            }
        }
    }
    public static void outputResult(String user){
        if (user.equals("!exit")) {
            System.out.println("Bye!");
            stop = true;
        } else if (user.equals("!rating")) {
            System.out.println("Your rating: "+ score);
        } else if (user.equals(computer)) {
            System.out.println("There is a draw (" + computer + ")");
            score += 50;
        }else {
            if (status) {
                System.out.println("Well done. The computer chose " + computer + " and failed");
            } else {
                System.out.println("Sorry, but the computer chose " + computer);
            }
        }
    }
    public static void complexGame(String[] options){
        Scanner scanner = new Scanner(System.in);
        String user = scanner.next();

        ;
        for (String x: options){
            if (!(Arrays.asList(options).contains(user) || user.equals("!exit") || user.equals("!rating"))) {
                System.out.println("Invalid input");
            } else {
                break;
            }
        }

        String[] opposingOptions = new String[options.length - 1];
        int selected = 0;
        for (int i = 0; i < options.length; i++){
            if (options[i].equals(user)){
                selected = i;
                break;
            }
        }
        int j =0;
        for (int i = selected + 1; i < options.length; i++){
            opposingOptions[j++] = options[i];
        }
        for (int i = 0; i < selected; i++){
            opposingOptions[j++] = options[i];
        }

        complexComputerChoice(options);
        solveComplexGame(user, opposingOptions);
        outputResult(user);
    }
    public static void complexComputerChoice(String[] options){
        Random random =  new Random();
        int choice = random.nextInt(options.length);
        computer = options[choice];
    }
    public static void solveComplexGame(String user, String[] opposingOptions){
        for (int i = 0; i < opposingOptions.length/2; i++){
            if (computer.equals(opposingOptions[i])){
                status = false;
                break;
            }
        }
        for (int i = opposingOptions.length/2; i < opposingOptions.length; i++){
            if (computer.equals(opposingOptions[i])){
                status = true;
                score += 0;
                break;
            }
        }
    }
}