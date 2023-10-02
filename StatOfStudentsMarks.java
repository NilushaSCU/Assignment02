import java.util.*;
import java.io.*;


/**This program is designed to read student information from a file,
 * process the data, and provide options for viewing and analyzing it. 
 * It handles missing assignment scores by setting them to 0.0 and
 * offers a user-friendly menu for interacting with the data
 *
 * @author Nilusha
 * @version (2.0) 20/09/2023
 */

public class StatOfStudentsMarks{
    static String[] values;//Assign Array to store values
    static String unitName; //Assign variable to store unit name
    static String title;//Assign variable to store title
    static List<Student> studentList = new ArrayList<>(); //Assign List to store 

 public StatOfStudentsMarks() {
        //Constructor is the initialization of class
    }

    public static void main(String[] args) throws Exception {//main method
        Scanner scanner = new Scanner(System.in);
        String filename;
        
        //loop to check the validity of the input
        while(true){
        System.out.print("Enter the filename containing student data: ");
        filename = scanner.nextLine();
        
        try(BufferedReader bufferReader = new BufferedReader(new FileReader(filename))) {
            bufferReader.close();
            break;
        }catch (FileNotFoundException e){
            System.out.println("File not found.Please enter a valid file name");
        }
    }
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader bufferReader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = bufferReader.readLine()) != null) {
                if (line.trim().startsWith("#")){
                continue;//skip the lines starting with symbol"#"
            }
                String[] values = line.split(",");
                records.add(Arrays.asList(values));
            }
        }
        
        unitName = records.get(0).get(0);//extract unit name from the list named records
        title= records.get(1).toString().replaceAll("\\[|\\]","");//exact title
        System.out.println("File Name: " + filename);//display file name
        System.out.println("unit Name:"+ unitName);//display unit name
        title += ",Total Marks";
        System.out.println("Title: "+title);//display title with all parts
        
        // Read student information and create Student objects
        for (int i = 2; i < records.size(); i++) {
            List<String> studentData = records.get(i);
            String lastName = studentData.get(0).trim();
            String firstName = studentData.get(1).trim();
            String studentID = studentData.get(2).trim();
            
            List<Double>assignmentMarksList = new ArrayList<>();
            
    for (int j = 3; j < studentData.size(); j++) {
    String marks = studentData.get(j).trim();

    double mark;
    if (marks.isEmpty() || marks.equalsIgnoreCase("null")) {
        mark = 0.0;
    } else {
        try {
            mark = Double.parseDouble(marks);
        } catch (NumberFormatException e) {
            mark = 0.0; // Handle cases where parsing as a double fails
        }
    }

    assignmentMarksList.add(mark);
}  
            
           double[] assignmentMarks = new double[assignmentMarksList.size()];
            for (int k = 0; k < assignmentMarksList.size(); k++) {
                assignmentMarks[k] = assignmentMarksList.get(k);
            }
            
            //create new student object and add it to the studentList
            studentList.add(new Student(firstName, lastName, studentID, assignmentMarks));
        }
      
    
   //Menu loop
    while(true){
    System.out.println("\n***********************Menu*********************");
    System.out.println("....................................................");
    System.out.println("1:Print Student Data");
    System.out.println("2:Print Students Below Threshold Value");
    System.out.println("3:Print Top Students");
    System.out.println("4:Exit from program");
    int choice = scanner.nextInt();
    scanner.nextLine();
    
    switch (choice){
        case 1:
            printStudentData();
            break;
        case 2:
            System.out.print("Enter the threshold for total marks: ");
            double threshold = scanner.nextDouble();
            scanner.nextLine();
            printBelowThreshold(threshold);
            break;
        case 3:
            printTopStudents();
            break;
        case 4:
            System.out.println("You have exit successfully from the program.");
            System.exit(0);
        default:
            System.out.println("Invalid choice.Please enter a valid choice: ");
    }
    }
}

//Method to print student data
public static void printStudentData() {
        System.out.println("\nStudent Data:");
        System.out.println(title);
        System.out.println();//Add an empty line

        for (Student student : studentList) {
            System.out.print(student.getLastName() + "," + student.getFirstName() + "," + student.getId());
            double[] assignmentMarks = student.getAssignmentMarks();
            for (double mark : assignmentMarks) {
                System.out.print("," + mark);
            }
            double totalMarks = calculateTotalMarks(assignmentMarks);
            System.out.println("," + totalMarks);
            System.out.println(); // Add an empty line after each student
        }
    }
   
    //Method to print student list with total below threshold value
public static void printBelowThreshold( double threshold) {
        System.out.println("Students with Total Marks Less Than " + threshold + ":");
        System.out.println(title);
        System.out.println();//Add an empty line
        
        for (Student student : studentList) {
            double totalMarks = calculateTotalMarks(student.getAssignmentMarks());
            if (totalMarks < threshold) {
                System.out.print(student.getLastName() + "," + student.getFirstName() + "," + student.getId());
                double[] assignmentMarks = student.getAssignmentMarks();
                for (double mark : assignmentMarks) {
                    System.out.print("," + mark);
                }
                System.out.println("," + totalMarks);
                System.out.println();//Add an empty line
            }
        }
    }
 
    //Method to print top 5 students with highest total and top 5 students with lowest total
public static void printTopStudents() {
        System.out.println("\nTop 5 Students with the Highest Total Marks:");
        System.out.println();//Add an empty line
        System.out.println(title);

        List<Student> topStudents = findTopStudents(5);

        for (Student student : topStudents) {
            System.out.println(student.getLastName() + ", " +student.getFirstName() + ", " +"ID: " + student.getId() + ", " +"Total Marks: " + calculateTotalMarks(student.getAssignmentMarks()));
            System.out.println();//Add an empty line
        }

        System.out.println("\nTop 5 Students with the Lowest Total Marks:");
        System.out.println();//Add an empty line
        System.out.println(title);
        
        List<Student> lowestStudents = findlowestStudents(5);

        for (Student student : lowestStudents) {
           System.out.println(student.getLastName() + ", " +student.getFirstName() + ", " +"ID: " + student.getId() + ", " +"Total Marks: " + calculateTotalMarks(student.getAssignmentMarks()));
           System.out.println();//Add an empty line
        }
    }

    //method to find and return top students with highest total marks  
    public static List<Student> findTopStudents(int num) {
        List<Student> topStudents = new ArrayList<>();
        for (Student student : studentList) {
            double totalMarks = calculateTotalMarks(student.getAssignmentMarks());

            if (topStudents.size() < num) {
                topStudents.add(student);
                continue;
            }

            for (int i = 0; i < num; i++) {
                double existingTotalMarks = calculateTotalMarks(topStudents.get(i).getAssignmentMarks());
                if (totalMarks > existingTotalMarks) {
                    topStudents.add(i, student);
                    topStudents.remove(num);
                    break;
                }
            }
        }
        return topStudents;
    }

    //method to find and return top students with lowest total marks 
    public static List<Student> findlowestStudents(int num) {
        List<Student> lowestStudents = new ArrayList<>();
        for (Student student : studentList) {
            double totalMarks = calculateTotalMarks(student.getAssignmentMarks());

            if (lowestStudents.size() < num) {
                lowestStudents.add(student);
                continue;
            }

            for (int i = 0; i < num; i++) {
                double existingTotalMarks = calculateTotalMarks(lowestStudents.get(i).getAssignmentMarks());
                if (totalMarks < existingTotalMarks) {
                    lowestStudents.add(i, student);
                    lowestStudents.remove(num);
                    break;
                }
            }
        }
        return lowestStudents;
    }

    //method to calculate total marks of each student in the file
    public static double calculateTotalMarks(double[] assignmentMarks){
    double totalMarks = 0.0;
    for(double mark : assignmentMarks){
      totalMarks += mark;  
    }
    return totalMarks;
}
}