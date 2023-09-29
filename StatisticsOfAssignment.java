import java.util.Scanner;
import java.io.File;
import java.io.IOException;

/**
 * Write a description of class StatisticsOfAssignment here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class StatisticsOfAssignment 
{
   

    /**
     * Constructor for objects of class StatisticsOfAssignment
     */
    public StatisticsOfAssignment()
    {
      readFromFile(); 
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void readFromFile(){
    try{
       File myFile = new File("prog5001_students_grade_2022.csv");
       Scanner myScanner = new Scanner(myFile);
       while(myScanner.hasNextLine()){
           String line = myScanner.nextLine();
           System.out.println(line);
       }
       myScanner.close();
    }catch(IOException e){
    System.out.println("This is an error");
    e.printStackTrace();
    }
}

    public void printStudentData(){
    
    
    }
    
    public void printStudentsBelowThreshold(){
    
    
    }
    
    public void printTopStudents(){
    
    
    }
    public void displayMenu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Menu");
        System.out.println("Select a function from the following options:");
        System.out.println("**********************************************");
        System.out.println("1.Print Student Data");
        System.out.println("2.Print students below a threshold");
        System.out.println("3.Print top 5 students");
        while (scanner.hasNextLine()){
            int userChoice = scanner.nextInt();
            executeOption(userChoice);
        }
        scanner.close();
    }
    
    public void executeOption(int userChoice){
    if (userChoice == 1){
        printStudentData();
    }else if (userChoice == 2){
        printStudentsBelowThreshold();
    }else if(userChoice == 3){
        printTopStudents();
    }
    }
}