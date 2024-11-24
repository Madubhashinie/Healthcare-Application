public class Person {
    private String name;
    private String contactNumber;

    public Person(String name, String contactNumber){
        this.name = name;
        this.contactNumber = contactNumber;
    }

    public String getName(){
        return name;
    }
    public String getContactNumber(){
        return contactNumber;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setContactNumber(String contactNumber){
        this.contactNumber = contactNumber;
    }
}
