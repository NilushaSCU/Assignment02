
/**This Student class represents a student's information and provides methods to access the details of the student.
 * 
 *
 * @author Nilusha
 * @version (2.1) 20/09/2023
 */

public class Student {
    private String firstName;//variable to store first name of the student
    private String lastName;//variable to store last name of the student
    private String studentID;//variable to store student ID of the student
    private double[] assignmentMarks;//array to store assignment marks of the student

    public Student(String lastName,String firstName, String studentID, double[] assignmentMarks) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.studentID = studentID;
        this.assignmentMarks = assignmentMarks;
    }
//method to get the last name of the student
     public String getLastName() {
        return lastName;
    }
    //method to get the first name of the student
    public String getFirstName() {
        return firstName;
    }
        //method to get the student ID of the student
    public String getId() {
        return studentID;
    }
//method to get the assignment marks of the student
    public double[] getAssignmentMarks() {
        return assignmentMarks;
    }
}

