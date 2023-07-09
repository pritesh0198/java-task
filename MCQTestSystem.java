
// MCQ Test System
import java.util.*;
import java.util.Timer;

import javax.swing.*;
import java.awt.event.*;

public class MCQTestSystem {

    // A class to represent a user
    static class User {
        String profileName;
        String password;
        public int id;

        public User(String profileName, String password) {
            this.profileName = profileName;
            this.password = password;
        }
    }

    // A class to represent a question
    static class Question {
        int id;
        String text;
        String[] options;
        int answer;

        public Question(int id, String text, String[] options, int answer) {
            this.id = id;
            this.text = text;
            this.options = options;
            this.answer = answer;
        }
    }

    // A class to represent a result
    static class Result {
        int userId;
        int questionId;
        int userAnswer;

        public Result(int userId, int questionId, int userAnswer) {
            this.userId = userId;
            this.questionId = questionId;
            this.userAnswer = userAnswer;
        }
    }

    // A constant for the number of questions in the test
    public static final int NUM_QUESTIONS = 10;

    // A constant for the duration of the test in minutes
    public static final int TEST_DURATION = 30;

    // A list of users
    static List<User> users = new ArrayList<>();

    // A list of questions
    static List<Question> questions = new ArrayList<>();

    // A list of results
    static List<Result> results = new ArrayList<>();

    // A scanner for user input
    static Scanner sc = new Scanner(System.in);

    // A method to load some sample data
    static void loadData() {

        // Add some sample questions to the list
        questions.add(new Question(1, "What is the capital of India?",
                new String[] { "New Delhi", "Mumbai", "Kolkata", "Chennai" }, 1));
        questions.add(new Question(2, "What is the largest animal in the world?",
                new String[] { "Elephant", "Whale", "Giraffe", "Dinosaur" }, 2));
        questions.add(new Question(3, "What is the smallest country in the world?",
                new String[] { "Vatican City", "Monaco", "Maldives", "Singapore" }, 1));
        questions.add(new Question(4, "What is the most spoken language in the world?",
                new String[] { "English", "Spanish", "Chinese", "Hindi" }, 3));
        questions.add(new Question(5, "What is the name of the largest bone in the human body?",
                new String[] { "Femur", "Humerus", "Tibia", "Fibula" }, 1));
        questions.add(new Question(6, "What is the name of the largest desert in the world?",
                new String[] { "Sahara", "Gobi", "Arabian", "Antarctic" }, 4));
        questions.add(new Question(7, "What is the name of the longest river in the world?",
                new String[] { "Amazon", "Nile", "Yangtze", "Mississippi" }, 2));
        questions.add(new Question(8, "What is the name of the highest mountain in the world?",
                new String[] { "Everest", "K2", "Kilimanjaro", "Makalu" }, 1));
        questions.add(new Question(9, "What is the name of the smallest planet in the solar system?",
                new String[] { "Mercury", "Venus", "Earth", "Mars" }, 1));
        questions.add(new Question(10, "What is the name of the largest ocean in the world?",
                new String[] { "Atlantic", "Pacific", "Indian", "Arctic" }, 2));
    }

    // A method to display the main menu
    static void displayMenu() {
        System.out.println("Welcome to MCQ Test System");
        System.out.println("Please select an option:");
        System.out.println("1. Create Profile");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.print("Enter your selection: ");
        int choice = sc.nextInt();
        sc.nextLine(); // consume the newline

        switch (choice) {
            case 1:
                createProfile();
                break;
            case 2:
                login();
                break;
            case 3:
                System.out.println("Thank you for using MCQ Test System");
                return; // exit the program
            default:
                System.out.println("Invalid selection. Please try again.");
                displayMenu(); // display the menu again
                break;
        }
    }

    // A method to create a profile
    static void createProfile() {

        // Ask the user to enter a profile name and password
        System.out.println("Please enter a profile name and password");
        System.out.print("Profile name: ");
        String profileName = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();

        // Create a user object
        User user = new User(profileName, password);

        // Add the user to the list
        users.add(user);

        // Display the profile created message
        System.out.println("Profile created successfully.");
        displayMenu(); // display the main menu
    }

    // A method to login
    static void login() {

        // Ask the user to enter a profile name and password
        System.out.println("Please enter your profile name and password");
        System.out.print("Profile name: ");
        String profileName = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();

        // Find the user with the given profile name and password
        User user = null;
        for (User u : users) {
            if (u.profileName.equals(profileName) && u.password.equals(password)) {
                user = u;
                break;
            }
        }

        // Check if the user exists
        if (user == null) {
            System.out.println("Invalid profile name or password. Please try again.");
            login(); // login again
            return; // exit the method
        }

        // Display the login successful message
        System.out.println("Login successful");

        // Ask the user if they want to update their profile name and password
        System.out.println("Do you want to update your profile name and password? (y/n)");
        char answer = sc.nextLine().charAt(0);

        if (answer == 'y' || answer == 'Y') {
            updateProfile(user); // update the profile
        }

        // Start the test
        startTest(user);
    }

    // A method to update a profile
    static void updateProfile(User user) {

        // Ask the user to enter a new profile name and password
        System.out.println("Please enter a new profile name and password");
        System.out.print("Profile name: ");
        String profileName = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();

        // Update the user object with the new profile name and password
        user.profileName = profileName;
        user.password = password;

        // Display the profile updated message
        System.out.println("Profile updated successfully.");
    }

    // A method to start the test
    static void startTest(User user) {

        // Check if the list of questions is empty or has less than NUM_QUESTIONS
        // questions
        if (questions.isEmpty() || questions.size() < NUM_QUESTIONS) {
            System.out.println("Not enough questions for the test. Please contact admin.");
            return; // exit the method
        }

        // Shuffle the list of questions and select the first NUM_QUESTIONS questions
        // for the test
        Collections.shuffle(questions);
        questions.subList(NUM_QUESTIONS, questions.size()).clear();

        // Create a timer for the test duration
        Timer timer = new Timer();

        // Create a timer task to end the test after TEST_DURATION minutes
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("\nTime's up! The test is over.");
                endTest(user); // end the test and display the results
                timer.cancel(); // cancel the timer
            }
        };

        // Schedule the timer task to run after TEST_DURATION minutes
        timer.schedule(task, TEST_DURATION * 60 * 1000);

        // Display the instructions for the test
        System.out.println("The test consists of " + NUM_QUESTIONS + " multiple choice questions.");
        System.out.println("You have " + TEST_DURATION + " minutes to complete the test.");
        System.out.println("Enter 1, 2, 3 or 4 to select an option for each question.");
        System.out.println("The test will be auto-submitted after the time limit.");
        System.out.println("Press enter to start the test.");
        sc.nextLine(); // wait for the user to press enter

        // Loop through the list of questions and display them one by one
        for (Question question : questions) {

            // Display the question text and options
            System.out.println(question.text);
            for (int i = 0; i < 4; i++) {
                System.out.println((i + 1) + ". " + question.options[i]);
            }

            // Prompt the user to enter an answer
            System.out.print("Enter your answer: ");
            int userAnswer = sc.nextInt();
            sc.nextLine(); // consume the newline

            // Check if the user answer is valid and within the range
            if (userAnswer < 1 || userAnswer > 4) {
                System.out.println("Invalid answer. Please try again.");
                continue; // skip this iteration of the loop
            }

            // Create a result object
            Result result = new Result(user.id, question.id, userAnswer);

            // Add the result to the list
            results.add(result);
        }

        // Cancel the timer task as the user has completed the test before time limit
        task.cancel();

        // Display a message that the test is over
        System.out.println("The test is over.");

        // End the test and display the results
        endTest(user);
    }

    // A method to end the test and display the results
    static void endTest(User user) {

        // Initialize the number of correct answers and score
        int correct = 0;
        int score = 0;

        // Loop through the list of questions and compare the user answer with the
        // correct answer
        for (Question question : questions) {

            // Find the result with the matching question id
            Result result = null;
            for (Result r : results) {
                if (r.questionId == question.id) {
                    result = r;
                    break;
                }
            }

            // Check if the result exists and matches the correct answer
            if (result != null && result.userAnswer == question.answer) {
                correct++; // increment the number of correct answers
            }
        }

        // Calculate the score based on the number of correct answers
        score = correct * 10;

        // Display the number of correct answers and score
        System.out.println("You answered " + correct + " out of " + NUM_QUESTIONS + " questions correctly.");
        System.out.println("Your score is " + score + ".");

        // Display the main menu
        displayMenu();
    }

    public static void main(String[] args) {

        // Load some sample data
        loadData();

        // Display the main menu
        displayMenu();
    }

}
