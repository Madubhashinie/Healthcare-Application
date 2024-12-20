import java.util.Scanner;

public class Menu {
    static Scanner scanner = new Scanner(System.in);

    public static void mainMenu() throws InvalidDoctorIdException{
                DatabaseInitializer.initializeDatabase();
        while(true){
            System.out.println("If you are a hospital administrator please press 1, If you are a patient please press 2, Press 3 to exit");
            int userType = scanner.nextInt();
            if(userType == 1){
                adminMenu();
            } else if (userType == 2) {
                patientMenu();
            } else if (userType == 3) {
                System.out.println();
                System.out.println("Thank you for using our Health System !");
                System.exit(0);

                break;
            }else {
                System.out.println("Invalid Input");
            }
        }
    }

    public static void adminMenu() throws InvalidDoctorIdException{
        while (true){
            System.out.println("Press 1 to add a doctor, press 2 to add a doctor availability, press 3 to exit");
            int userObjective = scanner.nextInt();
            if(userObjective == 1){
                Controllers.addDoctor();
                for (Doctor doc: Controllers.allDoctors){
                    System.out.println(doc.getName());
                }
            }else if (userObjective == 2){
                Controllers.addDoctorAvailability();

            } else if (userObjective == 3) {
                break;
            }else{
                System.out.println("Invalid");
            }
        }
    }

    public static void patientMenu() throws  InvalidDoctorIdException {
        while (true){
            System.out.println("Press 1 to view doctors, press 2 to book an appointment, press 3 to view a selected doctor's booking, press 4 to register yourself and press 5 to exit");
            int userObjective = scanner.nextInt();
            if(userObjective == 1){
                for (Doctor doc: Controllers.allDoctors){
                    System.out.println("Doctor Name: " + doc.getName() + "  Doctor Id: " + doc.getDoctorId());
                }
            } else if (userObjective == 2) {
                Controllers.bookAppointment();
            } else if (userObjective == 3) {
                System.out.println("View a Selected Doctor's booking");
                Controllers.selectedBooking();

            } else if (userObjective == 4) {

                Controllers.addPatient();
            } else if (userObjective == 5) {
                break;
            }else{
                System.out.println("Invalid");
            }
        }
    }
}
