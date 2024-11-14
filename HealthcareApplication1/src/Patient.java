public class Patient extends Person{
    public String patientId;

    public String birthday;


    public Patient(String patientId,  String name, String birthday, String  contactNumber){
        super(name, contactNumber);
        this.patientId = patientId;
        this.birthday =birthday;

    }
    public char getPatientType(){
        return this.patientId.charAt(0);
    }
}
