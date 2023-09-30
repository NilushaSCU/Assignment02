import java.util.*;
import java.io.*;

public class StatOfStudentsMarks {
    static String[] values;
    static String unitName;
    static String title;
    Private String studentList;
  
    public StatOfStudentsMarks() {
        // readFromFile(String[] args);
       calculateTotalMarks(studentList);
       printBelowThreshold(assignmentMarksList, scanner, studentList);
       printTopStudents(studentList);
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
       System.out.print("Enter the threshold for total marks: ");
        double threshold = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character
        List<String> studentList = new ArrayList<>();
        printBelowThreshold(studentList, threshold);  
    }
   
    
    public static double calculateTotalMarks(List<String>assignmentMarksList){
    double totalMarks = 0.0;
    for(String mark : assignmentMarksList){
      totalMarks += Double.parseDouble(mark);  
    }
    return totalMarks;
}

  public static void printBelowThreshold(List<String> studentList, double threshold) {
        System.out.println("Students with Total Marks Less Than " + threshold + ":");
        for (String student : studentList) {
            String[] studentData = student.split(",");
            double totalMarks = Double.parseDouble(studentData[4]);
            System.out.println("Total Marks for " + studentData[1] + " " + studentData[0] + ": " + totalMarks);
            if (totalMarks < threshold) {
                System.out.println(student);
    }
}
}
public static void printTopStudents(List<String> studentList) {
        // Find the top 5 students with the highest total marks
        List<String> topStudents = new ArrayList<>();
        for (String student : studentList) {
            if (topStudents.isEmpty()) {
                topStudents.add(student);
            } else {
                double totalMarks = Double.parseDouble(student.split(",")[4]);
                int index = 0;
                while (index < topStudents.size()) {
                    double topTotalMarks = Double.parseDouble(topStudents.get(index).split(",")[4]);
                    if (totalMarks > topTotalMarks) {
                        break;
                    }
                    index++;
                }
                if (index < 5) {
                    topStudents.add(index, student);
                    if (topStudents.size() > 5) {
                        topStudents.remove(5);
                    }
                }
            }
        }

        // Find the bottom 5 students with the lowest total marks
        List<String> bottomStudents = new ArrayList<>();
        for (String student : studentList) {
            if (bottomStudents.isEmpty()) {
                bottomStudents.add(student);
            } else {
                double totalMarks = Double.parseDouble(student.split(",")[4]);
                int index = 0;
                while (index < bottomStudents.size()) {
                    double bottomTotalMarks = Double.parseDouble(bottomStudents.get(index).split(",")[4]);
                    if (totalMarks < bottomTotalMarks) {
                        break;
                    }
                    index++;
                }
                if (index < 5) {
                    bottomStudents.add(index, student);
                    if (bottomStudents.size() > 5) {
                        bottomStudents.remove(5);
                    }
                }
            }
        }

        // Print the top 5 students with the highest total marks
        System.out.println("\nTop 5 Students with the Highest Total Marks:");
        for (String student : topStudents) {
            System.out.println(student);
        }

        // Print the bottom 5 students with the lowest total marks
        System.out.println("\nTop 5 Students with the Lowest Total Marks:");
        for (String student : bottomStudents) {
            System.out.println(student);
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


}
