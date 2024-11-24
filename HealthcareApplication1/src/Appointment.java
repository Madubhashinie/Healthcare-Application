
import java.util.Date;
import java.util.Scanner;

public class Appointment {
    Scanner scanner = new Scanner(System.in);
    private Doctor doctor;
    private Patient patient;
    private String notes;
    private Date date;
    private String time;

    public Appointment(Doctor doctor, Patient patient, String notes, Date date, String time){
        this.doctor =doctor;
        this.patient =patient;
        this.notes = notes;
        this.date = date;
        this.time = time;
    }

    public Doctor getDoctor() {
        return doctor;
    }
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
    public Patient getPatient() {
        return patient;
    }
    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }


}
