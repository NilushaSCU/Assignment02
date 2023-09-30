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

