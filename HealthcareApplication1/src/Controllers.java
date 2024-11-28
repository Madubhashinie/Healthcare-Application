import java.util.*;

public class Controllers {
    public static ArrayList<Doctor> allDoctors = new ArrayList<>();
    public static ArrayList<Patient> allPatients = new ArrayList<>();
    public static int NUMBER_OF_SLOTS = 5;

    //public HashMap<String,Integer> sample =  new HashMap<>();
    public static void addDoctor(){
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter Doctor's Name: ");
            String name = scanner.nextLine();
            System.out.println("Enter Doctor's birthday: ");
            String birthday = scanner.nextLine();
            System.out.println("Enter Doctor's Specialization: ");
            String specialization = scanner.nextLine();
            System.out.println("Enter Doctor's Contact: ");
            String contact = scanner.nextLine();


            Random random = new Random();
            Doctor tempDoctor = new Doctor(random.nextInt(), name, birthday, specialization, contact);
            allDoctors.add(tempDoctor);

        }catch (Exception e){
            System.out.println("Error Occurred while adding doctors"+ e.getMessage());
        }finally {
            scanner.close();
        }

    }

    public static Doctor getDoctorById(int id) throws InvalidDoctorIdException{
        for (Doctor doc: allDoctors){
            if(doc.getDoctorId() == id){
                return doc;
            }
        }
        throw new InvalidDoctorIdException("Doctor not found");
    }


    public  static  void  addPatient(){
        Scanner scanner = new Scanner(System.in);
        try {
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
        } catch (Exception e) {
            System.out.println("Error Occurred while adding patients"+ e.getMessage());
        }finally {
            scanner.close();
        }

    }

    public static Patient getPatientById(String id) throws  InvalidPatientIdException{
        for (Patient patient: allPatients){
            if(patient.getPatientId().equals(id)){
                return patient;
            }
        }
        throw new InvalidPatientIdException("Patient ID does not exist");
    }

    public static void addDoctorAvailability(){
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the Doctor Id you want to add the Availability: ");
            for (Doctor doc : Controllers.allDoctors) {
                System.out.println("Doctor Name: " + doc.getName() + "   Doctor Id: " + doc.getDoctorId());
            }
            int docId = scanner.nextInt();
            Doctor selectedDoc = Controllers.getDoctorById(docId);
            selectedDoc.setAvailabilities();
        }catch (InvalidDoctorIdException e){
            System.out.println(e.getMessage());
        }catch (Exception e){
            System.out.println("Error Occurred while adding availability"+ e.getMessage());
        }
    }

    public static void bookAppointment() {
        try{
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the Doctor Id you want to book appointment: ");
            int docId = scanner.nextInt();
            Doctor selectedDoc = getDoctorById(docId);

            System.out.println("Enter your patient's Id: ");
            String patientId = scanner.next();
            Patient selectedPatient = getPatientById(patientId);

            System.out.println("Enter the Day you want to add Appointment: ");
            String day = scanner.next();
            System.out.println("Enter the Month you want to add Appointment: ");
            String month = scanner.next();
            System.out.println("Enter the Year you want to add Appointment: ");
            String year = scanner.next();

            Date appointmentDate = new Date(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
            Boolean isAvailable = checkAvailability(selectedDoc, appointmentDate);

            if(isAvailable){
                String timeOfTheBooking = getTimeForBooking(selectedDoc, appointmentDate);
                if(timeOfTheBooking == null){
                    return;
                }
                System.out.println("Enter any Note: ");
                String note = scanner.next();
                System.out.println("Press G for general Appointment, Press R for Referral Appointment: ");
                String type = scanner.next();

                if (type.equals("G")){
                    GeneralAppointment tempAppointment = new GeneralAppointment(selectedDoc,selectedPatient,note,appointmentDate,timeOfTheBooking);
                    selectedDoc.setAppointment(appointmentDate,tempAppointment);
                } else{
                    System.out.println("Enter the referral doc Id: ");
                    int refDocId = scanner.nextInt();
                    getDoctorById(refDocId);
                    System.out.println("Enter Referral Doc NOtes: ");
                    String refNotes = scanner.next();

                    RefferalAppointment tempAppointment = new RefferalAppointment(selectedDoc,selectedPatient,note,appointmentDate,timeOfTheBooking, getDoctorById(refDocId));
                    tempAppointment.setReferralDoctorNotes(refNotes);
                    selectedDoc.setAppointment(appointmentDate,tempAppointment);
                }

                System.out.println("Your Booking is confirmed with Dr. " + selectedDoc.getName());
                return;
            } else {
                throw new BookingConflictException("Booking Failed");
            }
        } catch (InvalidDoctorIdException | InvalidPatientIdException | BookingConflictException e){
            System.out.println(e.getMessage());
        } catch (Exception e) {
        System.out.println("An unexpected error occurred: " + e.getMessage());
        }

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

    public static void selectedBooking() throws InvalidDoctorIdException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the doctor's ID to meet: ");
        int docId = scanner.nextInt();
        scanner.nextLine();

        Doctor selectedDoctor = getDoctorById(docId);
        System.out.println(selectedDoctor.allAppointments.toString());

    }



}
