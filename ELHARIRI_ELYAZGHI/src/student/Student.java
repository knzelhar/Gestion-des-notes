package student;

public class Student {
    private String code_apogee;
    private String dateOfBirth;
    private String firstName;
    private String lastName;
    private String className;
    private String phone;
    private String email;

    public Student(String CNE, String dateOfBirth, String firstName, String lastName, String className, String phone, String email) {
        this.code_apogee = CNE;
        this.dateOfBirth = dateOfBirth;
        this.firstName = firstName;
        this.lastName = lastName;
        this.className = className;
        this.phone = phone;
        this.email = email;
    }

    public String getCNE() {
        return code_apogee;
    }

    public String getDate() {
        return dateOfBirth;
    }

    public String getfirstName() {
        return firstName;
    }

    public String getlastName() {
        return lastName;
    }

    public String getclassName() {
        return className;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
}