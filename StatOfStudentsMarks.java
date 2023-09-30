import java.util.*;
import java.io.*;

public class StatOfStudentsMarks {
    static String[] values;
    static String unitName;
    static String title;
  
    public StatOfStudentsMarks() {
        // Constructor
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String filename;
        
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
                continue;
            }
                values = line.split(",");
                records.add(Arrays.asList(values));
            }
        }
        
        unitName = records.get(0).get(0);
        String title= records.get(1).toString().replaceAll("\\[|\\]","");
        System.out.println("File Name: " + filename);
        System.out.println("unit Name:"+ unitName);
        title += ",Total Marks";
        System.out.println("Title: "+title);

        for (int i = 2; i < records.size(); i++) {
            List<String> studentData = records.get(i);
            String lastName = studentData.get(0);
            String firstName = studentData.get(1);
            String studentID = studentData.get(2);
            
            List<String>assignmentMarksList = new ArrayList<>();
            
            for(int j = 3;j< studentData.size();j++){
              String mark = studentData.get(j).replaceAll("\\[|\\]", "");
                // If any assignment mark is missing, replace with "0"
                mark = mark.isEmpty() ? "0" : mark;
                assignmentMarksList.add(mark);
            }  
            
            String assignmentMarks = String.join(",",assignmentMarksList);
            double totalMarks = calculateTotalMarks(assignmentMarksList);
            System.out.println(lastName +","+ firstName +","+ studentID +","+assignmentMarks +","+ totalMarks);
            
        }
    }
    
    public static double calculateTotalMarks(List<String>assignmentMarksList){
    double totalMarks = 0.0;
    for(String mark : assignmentMarksList){
      totalMarks += Double.parseDouble(mark);  
    }
    return totalMarks;
}
}

class Student {
    private String firstName;
    private String lastName;
    private String studentID;
    private double[] assignmentMarks;

    public Student(String firstName,String lastName, String studentID, double[] assignmentMarks) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentID = studentID;
        this.assignmentMarks = assignmentMarks;
    }

    public String getFirstName() {
        return firstName;
    }
    
     public String getLastName() {
        return firstName;
    }

    public String getId() {
        return studentID;
    }

    public double[] getAssignmentMarks() {
        return assignmentMarks;
    }
}

