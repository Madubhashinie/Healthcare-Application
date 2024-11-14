import java.util.*;

public class Controllers {
    public static ArrayList<Doctor> allDoctors = new ArrayList<>();
    public static ArrayList<Patient> allPatients = new ArrayList<>();
    public static int NUMBER_OF_SLOTS = 5;

    //public HashMap<String,Integer> sample =  new HashMap<>();
    public static void addDoctor(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your Name: ");
        String name = scanner.nextLine();
        System.out.println("Enter your birthday: ");
        String birthday = scanner.nextLine();
        System.out.println("Enter your Specialization: ");
        String specialization = scanner.nextLine();
        System.out.println("Enter your Contact: ");
        String contact = scanner.nextLine();

        Random random = new Random();
        Doctor tempDoctor = new Doctor(random.nextInt(), name, birthday, specialization, contact);
        allDoctors.add(tempDoctor);
    }

    public static Doctor getDoctorById(int id){
        for (Doctor doc: allDoctors){
            if(doc.doctorId == id){
                return doc;
            }
        }
        return null;
    }

    public  static  void  addPatient(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your Name: ");
        String name = scanner.nextLine();
        System.out.println("Enter your Id: ");
        String id = scanner.nextLine();
        System.out.println("Enter your birthday: ");
        String birthday = scanner.nextLine();
        System.out.println("Enter your Contact Number: ");
        String contact = scanner.nextLine();

        Patient tempDoctor = new Patient(id, name, birthday, contact);
        allPatients.add(tempDoctor);

    }

    public static Patient getPatientById(String id){
        for (Patient patient: allPatients){
            if(patient.patientId.equals(id)){
                return patient;
            }
        }
        return null;
    }

    public static void addDoctorAvailability(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the Doctor Id you want to add the Availability: ");
        for(Doctor doc: Controllers.allDoctors){
            System.out.println("Doctor Name: " + doc.name+ "   Doctor Id: "+ doc.doctorId);
        }
        int docId = scanner.nextInt();
        Doctor selectedDoc = Controllers.getDoctorById(docId);
        if(selectedDoc != null){
            selectedDoc.setAvailabilities();
        }
        else {
            System.out.println("Wrong Doctor Id");
        }
    }

    public static void bookAppointment(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Doctor's Id you want to make an appointment: ");
        int docId = scanner.nextInt();
        System.out.println("Enter your patient's Id: ");
        String patientId = scanner.nextLine();

        System.out.println("Enter the Day you want to add Appointment: ");
        String day = scanner.nextLine();
        System.out.println("Enter the Month you want to add Appointment: ");
        String month = scanner.nextLine();
        System.out.println("Enter the Year you want to add Appointment: ");
        String year = scanner.nextLine();

        Patient selectedPatient = getPatientById(patientId);
        Doctor selectedDoc = getDoctorById(docId);
        if(selectedDoc == null || selectedPatient == null){
            System.out.println("Invalid doctor or patient id");
            return;
        }
        Date appointmentDate = new Date(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
        Boolean isAvailable = checkAvailability(selectedDoc, appointmentDate);
        if(isAvailable){
            String timeOfTheBooking = getTimeForBooking(selectedDoc, appointmentDate);
            if(timeOfTheBooking == null){
                return;
            }
            System.out.println("Enter any Note: ");
            String note = scanner.nextLine();
            System.out.println("Press G for general Appointment, Press R for Referral Appointment: ");
            String type = scanner.next();

            if (type.equals("G")){
                GeneralAppointment tempAppointment = new GeneralAppointment(selectedDoc,selectedPatient,note,appointmentDate,timeOfTheBooking);
                selectedDoc.setAppointment(appointmentDate,tempAppointment);
            } else{
                System.out.println("Enter the referral doc Id: ");
                int refDocId = scanner.nextInt();
                if(getDoctorById(refDocId) == null){
                    System.out.println("Invalid Ref Doc");
                    return;
                }
                System.out.println("Enter Referral Doc NOtes: ");
                String refNotes = scanner.next();

                RefferalAppointment tempAppointment = new RefferalAppointment(selectedDoc,selectedPatient,note,appointmentDate,timeOfTheBooking, getDoctorById(refDocId));
                tempAppointment.setReferralDoctorNotes(refNotes);
                selectedDoc.setAppointment(appointmentDate,tempAppointment);
            }

            System.out.println("Your Booking is confirmed with Dr. " + selectedDoc.name);
            return;
        }

        System.out.println("Doctor Not available");


    }

    public static Boolean checkAvailability(Doctor selectedDoctor , Date dateOfBooking){
        for(Date day: selectedDoctor.getAvailabilities()){
            if(day.equals(dateOfBooking)){
                return true;
            }
        }
        return false;
    }

    public static String getTimeForBooking(Doctor selectedDoctor, Date dateOfBooking){
        for(Map.Entry<Date, ArrayList<Appointment>> appointment: selectedDoctor.allAppointments.entrySet()){
            if(appointment.getKey().equals(dateOfBooking)){
                int numberOfSlots = appointment.getValue().size();
                if(numberOfSlots > NUMBER_OF_SLOTS - 1){
                    return null;
                }else{
                    System.out.println("We can make a booking");
                    int time = 17 + numberOfSlots;
                    String strTime = time + ": 00";
                    return strTime;
                }

            }
        }
        return "17: 00";
    }



}
