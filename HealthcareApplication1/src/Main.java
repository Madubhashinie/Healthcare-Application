

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("Welcome to the Healthcare Application!");
            System.out.println(" ");
            Doctor sampleDoc = new Doctor(101,"Tharushi","12.15.2003","Cardiologist","0760223687");
            Patient samplePatient = new Patient("T-1", "Madubhashinie",  "11.11.2002", "0718394205");
            Controllers.allDoctors.add(sampleDoc);
            Controllers.allPatients.add(samplePatient);
            Menu.mainMenu();
        }catch (InvalidDoctorIdException e){
            System.out.println(e.getMessage());
        }catch (InvalidPatientIdException e) {
            System.out.println(e.getMessage());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }


    }
}