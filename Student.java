class Student {
    private String firstName;
    private String lastName;
    private String studentID;
    private double[] assignmentMarks;

    public Student(String lastName,String firstName, String studentID, double[] assignmentMarks) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.studentID = studentID;
        this.assignmentMarks = assignmentMarks;
    }

     public String getLastName() {
        return lastName;
    }
    
    public String getFirstName() {
        return firstName;
    }
        
    public String getId() {
        return studentID;
    }

    public double[] getAssignmentMarks() {
        return assignmentMarks;
    }
}

