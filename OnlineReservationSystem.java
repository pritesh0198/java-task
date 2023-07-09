// Online Reservation System
import java.util.*;
import java.io.*;

public class OnlineReservationSystem {

    // A class to represent a passenger
    static class Passenger {
        String name;
        String email;
        String phone;
        int pnr;

        public Passenger(String name, String email, String phone, int pnr) {
            this.name = name;
            this.email = email;
            this.phone = phone;
            this.pnr = pnr;
        }
    }

    // A class to represent a train
    static class Train {
        int number;
        String name;
        String type;
        int seats;

        public Train(int number, String name, String type, int seats) {
            this.number = number;
            this.name = name;
            this.type = type;
            this.seats = seats;
        }
    }

    // A class to represent a reservation
    static class Reservation {
        Passenger passenger;
        Train train;
        String date;
        String from;
        String to;

        public Reservation(Passenger passenger, Train train, String date, String from, String to) {
            this.passenger = passenger;
            this.train = train;
            this.date = date;
            this.from = from;
            this.to = to;
        }
    }

    // A list of trains
    static List<Train> trains = new ArrayList<>();

    // A list of reservations
    static List<Reservation> reservations = new ArrayList<>();

    // A scanner for user input
    static Scanner sc = new Scanner(System.in);

    // A method to load some sample data
    static void loadData() {
        trains.add(new Train(101, "Rajdhani Express", "AC", 100));
        trains.add(new Train(102, "Shatabdi Express", "AC", 80));
        trains.add(new Train(103, "Duronto Express", "AC", 90));
        trains.add(new Train(104, "Garib Rath", "Non-AC", 120));
        trains.add(new Train(105, "Jan Shatabdi", "Non-AC", 100));
    }

    // A method to display the main menu
    static void displayMenu() {
        System.out.println("Welcome to Online Reservation System");
        
        while (true) {
            System.out.println("Please choose an option:");
            System.out.println("1. Reservation");
            System.out.println("2. Cancellation");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume the newline

            switch (choice) {
                case 1:
                    reservation();
                    break;
                case 2:
                    cancellation();
                    break;
                case 3:
                    System.out.println("Thank you for using Online Reservation System");
                    return; // exit the program
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    // A method to perform reservation
    static void reservation() {
        
        // Get the passenger details
        System.out.println("Please enter your details:");
        System.out.print("Name: ");
        String name = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Phone: ");
        String phone = sc.nextLine();

        
         // Generate a random PNR number between 1000 and 9999
         int pnr = (int)(Math.random() * 9000) + 1000;

         // Create a passenger object
         Passenger passenger = new Passenger(name, email, phone, pnr);

         // Get the train details
         System.out.println("Please enter the train details:");
         System.out.print("Train number: ");
         int number = sc.nextInt();
         sc.nextLine(); // consume the newline

         // Find the train with the given number
         Train train = null; 
         for (Train t : trains) {
             if (t.number == number) {
                 train = t; 
                 break; 
             }
         }

         // Check if the train exists and has seats available
         if (train == null) {
             System.out.println("Sorry, no such train found.");
             return; // exit the method
         }

         if (train.seats == 0) {
             System.out.println("Sorry, no seats available in this train.");
             return; // exit the method
         }

         // Get the date and destination details
         System.out.println("Please enter the date and destination details:");
         System.out.print("Date (dd/mm/yyyy): ");
         String date = sc.nextLine();
         System.out.print("From: ");
         String from = sc.nextLine();
         System.out.print("To: ");
         String to = sc.nextLine();

         // Create a reservation object
         Reservation reservation = new Reservation(passenger, train, date, from, to);

         // Add the reservation to the list
         reservations.add(reservation);

         // Reduce the seats in the train by 1
         train.seats--;

         // Display the confirmation message
         System.out.println("Reservation successful. Your PNR number is " + pnr);
    }

    // A method to perform cancellation
    static void cancellation() {
        
        // Get the PNR number
        System.out.println("Please enter your PNR number:");
        int pnr = sc.nextInt();
        sc.nextLine(); // consume the newline

        // Find the reservation with the given PNR number
        Reservation reservation = null;
        for (Reservation r : reservations) {
            if (r.passenger.pnr == pnr) {
                reservation = r;
                break;
            }
        }

        // Check if the reservation exists
        if (reservation == null) {
            System.out.println("Sorry, no such reservation found.");
            return; // exit the method
        }

        // Display the reservation details
        System.out.println("Your reservation details are:");
        System.out.println("Name: " + reservation.passenger.name);
        System.out.println("Email: " + reservation.passenger.email);
        System.out.println("Phone: " + reservation.passenger.phone);
        System.out.println("Train number: " + reservation.train.number);
        System.out.println("Train name: " + reservation.train.name);
        System.out.println("Train type: " + reservation.train.type);
        System.out.println("Date: " + reservation.date);
        System.out.println("From: " + reservation.from);
        System.out.println("To: " + reservation.to);

        // Ask for confirmation
        System.out.println("Are you sure you want to cancel this reservation? (y/n)");
        char answer = sc.nextLine().charAt(0);

        if (answer == 'y' || answer == 'Y') {
            // Remove the reservation from the list
            reservations.remove(reservation);

            // Increase the seats in the train by 1
            reservation.train.seats++;

            // Display the cancellation message
            System.out.println("Cancellation successful. Your PNR number is " + pnr);
        } else {
            // Display the cancellation aborted message
            System.out.println("Cancellation aborted. Your PNR number is " + pnr);
        }
    }

    public static void main(String[] args) {
        
        // Load some sample data
        loadData();

        // Display the main menu
        displayMenu();
    }
}
