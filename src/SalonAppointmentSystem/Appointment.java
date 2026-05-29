package SalonAppointmentSystem;

import java.util.ArrayList;
import java.util.Scanner;

class Booking {
    private String appointmentId;
    private String clientName;
    private String serviceType;
    private String timeSlot; 
    private double price;

    public Booking(String appointmentId, String clientName, String serviceType, String timeSlot, double price) {
        this.appointmentId = appointmentId;
        this.clientName = clientName;
        this.serviceType = serviceType;
        this.timeSlot = timeSlot;
        this.price = price;
    }

    public String getAppointmentId() { return appointmentId; }
    public String getClientName() { return clientName; }
    public void setClientName(String clientName) { this.clientName = clientName; }
    public String getServiceType() { return serviceType; }
    public void setServiceType(String serviceType) { this.serviceType = serviceType; }
    public String getTimeSlot() { return timeSlot; }
    public void setTimeSlot(String timeSlot) { this.timeSlot = timeSlot; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return "Booking ID: " + appointmentId + 
               " | Client: " + clientName + 
               " | Service: " + serviceType + 
               " | Time: " + timeSlot + 
               " | Price: Birr" + price;
    }
}


public class Appointment {
    private static ArrayList<Booking> appointmentList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static int idCounter = 100; 

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n    2SAB SALON & SPA SCHEDULER    ");
            System.out.println("1. Book New Appointment");
            System.out.println("2. View All Scheduled Appointments");
            System.out.println("3. Reschedule/Update Appointment");
            System.out.println("4. Cancel/Delete Appointment");
            System.out.println("5. Exit System");
            System.out.print("Please select an option (1-5): ");

            while (!scanner.hasNextInt()) {
                System.out.print("Invalid entry. Choose a number between 1 and 5: ");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1: bookAppointment(); break;
                case 2: viewAppointments(); break;
                case 3: rescheduleAppointment(); break;
                case 4: cancelAppointment(); break;
                case 5: System.out.println("Shutting down scheduler. Have a great day!"); break;
                default: System.out.println("Invalid option! Please try again.");
            }
        } while (choice != 5);
    }

    private static void bookAppointment() {
        System.out.println("\n--- Book New Appointment ---");
        System.out.print("Enter Client Name: ");
        String name = scanner.nextLine();

        System.out.println("Select Service Type:");
        System.out.println("1. Haircut & Styling (500.00 Birr)");
        System.out.println("2. Luxury Facial & Skincare (1500.00 Birr)");
        System.out.println("3. Gel Manicure & Pedicure (2000.00 Birr)");
        System.out.println("4. Full Body Spa Massage (4500.00 Birr)");
        System.out.print("Choice: ");
        
        int serviceChoice = scanner.nextInt();
        scanner.nextLine(); 
        
        String service = "";
        double price = 0.0;
        
        switch (serviceChoice) {
            case 1: service = "Haircut & Styling"; price = 500.00; break;
            case 2: service = "Luxury Facial & Skincare"; price = 1500.00; break;
            case 3: service = "Gel Manicure & Pedicure"; price = 2000.00; break;
            case 4: service = "Full Body Spa Massage"; price = 4500.00; break;
            default: service = "Standard Salon Service"; price = 1500.00; break;
        }

        System.out.print("Enter Preferred Time Slot (e.g., 10:00 AM, 03:30 PM): ");
        String timeSlot = scanner.nextLine();

     
        for (Booking app : appointmentList) {
            if (app.getTimeSlot().equalsIgnoreCase(timeSlot)) {
                System.out.println("Warning: This time slot is already booked! Please select a different time.");
                return;
            }
        }

        idCounter++;
        String generatedId = "APT" + idCounter;
        appointmentList.add(new Booking(generatedId, name, service, timeSlot, price));
        System.out.println("Success! Appointment booked successfully. Booking ID: " + generatedId);
    }

    private static void viewAppointments() {
        if (appointmentList.isEmpty()) {
            System.out.println("\nThe appointment book is completely empty for today.");
            return;
        }
        System.out.println("\n--- Current Appointments Scheduled ---");
        for (Booking app : appointmentList) {
            System.out.println(app);
        }
    }

    private static void rescheduleAppointment() {
        System.out.print("\nEnter the Booking ID to reschedule (e.g., APT101): ");
        String id = scanner.nextLine();
        
        for (Booking app : appointmentList) {
            if (app.getAppointmentId().equalsIgnoreCase(id)) {
                System.out.print("Enter New Time Slot: ");
                app.setTimeSlot(scanner.nextLine());
                System.out.println("Appointment time updated successfully!");
                return;
            }
        }
        System.out.println("Error: Booking ID not found.");
    }

    private static void cancelAppointment() {
        System.out.print("\nEnter the Booking ID to cancel: ");
        String id = scanner.nextLine();
        
        for (Booking app : appointmentList) {
            if (app.getAppointmentId().equalsIgnoreCase(id)) {
                appointmentList.remove(app);
                System.out.println("Appointment cancelled successfully.");
                return;
            }
        }
        System.out.println("Error: Booking ID not found.");
    }
}