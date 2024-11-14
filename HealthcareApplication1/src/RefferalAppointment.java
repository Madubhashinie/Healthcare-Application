import java.util.Date;

public class RefferalAppointment extends Appointment{
    Doctor referralDoc;
    String referralDocNote;
    public  RefferalAppointment(Doctor doctor, Patient patient, String notes, Date date, String time, Doctor refDoc ){
        super(doctor,patient,notes,date,time);
        this.referralDoc = refDoc;

    }

    public void setReferralDoctorNotes(String notes){
        this.referralDocNote = notes;
    }
    public void setReferralDoctorNotes(String[] notes){
        String allNotes = "";
        for (String s : notes){
            allNotes = allNotes + ". " + s;
        }
        this.referralDocNote = allNotes ;
    }

}
