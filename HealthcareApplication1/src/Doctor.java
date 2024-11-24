import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class Doctor extends Person {
    Scanner scanner = new Scanner(System.in);
    private int doctorId;

    private String birthday;
    private String specialization;

    private ArrayList<Date> availabilities;
    public HashMap<Date, ArrayList<Appointment>> allAppointments;

    public Doctor(int doctorId, String name, String birthday, String specialization, String contactNumber){
        super(name,contactNumber);
        this.doctorId = doctorId;
        this.birthday = birthday;
        this.specialization = specialization;
        this.availabilities = new ArrayList<>();
        this.allAppointments = new HashMap<>();
    }

    public int getDoctorId() {
        return doctorId;
    }
    public String getBirthday() {
        return birthday;
    }
    public String getSpecialization() {
        return specialization;
    }


    public ArrayList<Date> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities() {
        try {
            System.out.println("Enter the Day you want to add Availability: ");
            int day = scanner.nextInt();
            System.out.println("Enter the Month you want to add Availability: ");
            int month = scanner.nextInt();
            System.out.println("Enter the Year you want to add Availability: ");
            int year = scanner.nextInt();

            if (day < 1 || day > 31 || month < 1 || month > 12 || year < 1900) {
                throw new IllegalArgumentException("Invalid Input Entered. Please enter valid day, month, and year.");
            }

            Date bookingDate = new Date(year, month, day);
            availabilities.add(bookingDate);

            for (Date d : availabilities) {
                System.out.println(d);
            }
        } catch (Exception e) {
            System.out.println("Error Occurred while setting availability "+e.getMessage());
        }
    }

    public void  setAppointment(Date date, Appointment appointment){
        try {
            ArrayList<Appointment> currentAppointmentForTheDate = allAppointments.get(date);
            if(currentAppointmentForTheDate == null){
                ArrayList<Appointment> tempArrayList = new ArrayList<>();
                tempArrayList.add(appointment);
                allAppointments.put(date,tempArrayList);
            }else {
                currentAppointmentForTheDate.add(appointment);
                allAppointments.put(date,currentAppointmentForTheDate);
            }
        } catch (Exception e) {
            System.out.println("Error Occurred while setting appointment "+e.getMessage());
        }

    }


    public boolean isPhysician(){
        try{
            if(this.specialization.endsWith("physician")){
                return true;
            }
            return false;
        }catch(Exception e){
            System.out.println("Error Occurred while checking physician "+e.getMessage());
            return false;
        }

    }


}
