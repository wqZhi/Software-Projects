// ===== Code from file PersonData.java =====
class PersonData {
    private int ageYears;
    private String lastName;

    public void setName(String userName) {
        lastName  = userName;
    }

    public void setAge(int numYears) {
        ageYears = numYears;
    }

    // Other parts omitted

    public void printAll() {
        System.out.print("Name: " + lastName);
        System.out.print(", Age: "  + ageYears);
    }
}
// ===== end =====

// ===== Code from file StudentData.java =====
class StudentData extends PersonData {
    private int idNum;

    public void setID(int studentId) {
        idNum = studentId;
    }

    public int getID() {
        return idNum;
    }
}
// ===== end =====

// ===== Code from file StudentDerivationFromPerson.java =====
public class StudentDerivationFromPerson {
    public static void main(String[] args) {
        StudentData courseStudent = new StudentData();

        courseStudent.setName("Smith");
        courseStudent.setAge(20);
        courseStudent.setID(9999);

        courseStudent.printAll();

        System.out.print(", ID: " + courseStudent.getID());
    }
}
// ===== end =====
