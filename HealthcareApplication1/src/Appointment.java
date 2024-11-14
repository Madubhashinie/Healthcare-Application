
import java.util.Date;
import java.util.Scanner;

public class Appointment {
    Scanner scanner = new Scanner(System.in);
    Doctor doctor;
    Patient patient;
    String notes;
    Date date;
    String time;

    public Appointment(Doctor doctor, Patient patient, String notes, Date date, String time){
        this.doctor =doctor;
        this.patient =patient;
        this.notes = notes;
        this.date = date;
        this.time = time;
    }


}
