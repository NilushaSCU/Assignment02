
/**
 * Write a description of class Student here.
 *
 * Author:Nilusha
 * @version 2.1 
 */
public class Student
{
    private String firstName;
    private String studentId;
    private double[] marks;
    private double totalMarks;

    public Student(String fistName,String studentId,double[] marks,double totalMarks)
    {
        this.firstName = firstName;
        this.studentId = studentId;
        this.marks = marks;
        this.totalMarks = calculateTotalMarks();
    }

    public int calculateTotalMarks(){
        int sum = 0;
        for(double mark : marks){
            sum += mark;
        }
        return sum;
    }

    public String getName(){
        return firstName;
    }
    
    public String getStudentId(){
        return studentId;
    }
    
    public double[] getMarks(){
        return marks;
    }
    
    public double getTotalMarks(){
        return totalMarks;
    }
    
}
