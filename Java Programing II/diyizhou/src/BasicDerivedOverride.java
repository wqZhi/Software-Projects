import java.util.Scanner;

// ===== Code from file AnimalData.java =====
class AnimalData {
    private int ageYears;
    private String fullName;

    public void setName(String givenName) {
        fullName = givenName;
    }

    public void setAge(int numYears) {
        ageYears = numYears;
    }

    // Other parts omitted

    public void printAll() {
        System.out.print("Name: "  + fullName);
        System.out.print(", Age: " + ageYears);
    }
}
// ===== end =====

// ===== Code from file PetData.java =====
class PetData extends AnimalData {
    private int idNum;

    public void setID(int petID) {
        idNum = petID;
    }

    // FIXME: Add printAll() member function
    @Override
    public void printAll() {
        super.printAll();
        System.out.print(", ID: " + idNum);
    }

}

// ===== Code from file BasicDerivedOverride.java =====

public class BasicDerivedOverride {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        PetData userPet = new PetData();
        String userName;
        int userAge;
        int userID;

        userName = scnr.next();
        userAge = scnr.nextInt();
        userID = scnr.nextInt();

        userPet.setName(userName);
        userPet.setAge (userAge);
        userPet.setID  (userID);
        userPet.printAll();
        System.out.println("");
    }
}
// ===== end =====
