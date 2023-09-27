import java.util.Scanner;
import java.io.File;
import java.io.IOException;

/**
 * Write a description of class StatisticsOfAssignment here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class StatisticsOfAssignment extends Student
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
}