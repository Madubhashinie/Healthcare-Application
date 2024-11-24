public class Patient extends Person{
    private String patientId;

    public String birthday;


    public Patient(String patientId,  String name, String birthday, String  contactNumber){
        super(name, contactNumber);
        this.patientId = patientId;
        this.birthday =birthday;

    }
    public String getPatientId() {
        return this.patientId;
    }
    public char getPatientType(){

        return this.patientId.charAt(0);
    }
}
