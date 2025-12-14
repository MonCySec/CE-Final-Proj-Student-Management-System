package eacspace;
import java.util.Scanner;

public class Eacspace {

    public static void main(String[] args) {
        Scanner bsy = new Scanner(System.in);
       
        String[] studentNames = new String[15];
        int[] studentNumbers = new int[15];
        String[] subjects = new String[10];
        double[][] grades = new double[15][10];
       
        int totalStudents = 0;
        int totalSubjects = 0;
       
        boolean run = true;

        System.out.println("Welcome to EACSpace, What would you like to do today?");
       
        while(run){
        System.out.println("\n===== MAIN MENU =====");
        System.out.println("[1] Register Students");
        System.out.println("[2] Register Subjects");
        System.out.println("[3] Encode Grades");
        System.out.println("[4] View Class Performance");
        System.out.println("[5] View Student Performance");
        System.out.println("[6] Search Student");
        System.out.println("[0] Exit");
        System.out.print("Enter choice: ");
       
        int choice = bsy.nextInt();
        bsy.nextLine();
       
        switch (choice){
           
            case 1:
                totalStudents = studentRegister(bsy, studentNames, studentNumbers, totalStudents);
            break;
           
            case 2:
                totalSubjects = subjectRegister(bsy, subjects, totalSubjects);
                break;
               
            case 3:
                gradesEncoder(bsy, grades, studentNames, subjects, studentNumbers, totalStudents, totalSubjects);
                break;

            case 4:
                classPerformance(bsy, grades, studentNames, subjects, totalStudents, totalSubjects);
                break;

            case 5:
                studentPerformance(bsy, grades, studentNames, subjects, studentNumbers, totalStudents, totalSubjects);
                break;

            case 6:
                studentList(bsy, studentNumbers, studentNames, totalStudents);
                break;

            case 0:
                System.out.println("Exiting EACspace...");
                run = false;  // exit loop
                break;

            default:
                System.out.println("You have made an invalid input. Please try again.");
                break;

        }
        }
    }
   
    public static int studentRegister(Scanner in,String[]names, int[] numbers, int totalStudents){
       
        System.out.println("Enter the amount of students to enroll: ");
        int studentCount = in.nextInt();
        in.nextLine();
        
        if(studentCount <= 0){
            System.out.println("No students added.");
            return totalStudents;
        }

        System.out.println("Registering Students...");
       
        if((totalStudents + studentCount) > 15){
            System.out.println("This class is full! you can only enroll a maximum of 15 students.");
            System.out.println("Currently Enrolled: "+totalStudents);
            System.out.println("Slots remaining: "+(15 - totalStudents));
            return totalStudents;
        }
       
        for(int i = 0; i <studentCount; i++){
        System.out.println("Enter Student Name: ");
        names[totalStudents] = in.nextLine();
       
        System.out.println("Enter Student Number: ");
        numbers[totalStudents] = in.nextInt();
        in.nextLine();
       
        totalStudents++;
        }

        System.out.println("Students have been successfully enrolled!");
        return totalStudents;
    }
           
    public static int subjectRegister(Scanner in,String[]subjects, int totalSubjects){
       
        System.out.println("Enter the amount of subjects to encode: ");
        int subCount = in.nextInt();
        in.nextLine();
       
        
        if(subCount <= 0){
            System.out.println("No subjects added.");
            return totalSubjects;
        }

       
        if(totalSubjects + subCount> 10){
            System.out.println("This students curriculum is full! you can only add a maximum of 10 subjects.");
            System.out.println("Current Subjects Encoded: "+totalSubjects);
            System.out.println("Slots Remaining: "+(10 - totalSubjects));
            return totalSubjects;
        }
        
       System.out.println("Registering Subjects...");
       
       for(int i = 0; i<subCount; i++){
           String code = "";
           String name = "";
           
        // Asks User to input Subject name
        for (; name.isEmpty(); ) {
            System.out.print("Enter Subject Name: ");
            name = in.nextLine().trim();

            if (name.isEmpty()) {
                System.out.println("Subject name cannot be empty.");
            }
        // Asks User to input Subject Code
        for (; code.isEmpty(); ) {
            System.out.print("Enter Subject Code (e.g. IT101): ");
            code = in.nextLine().trim();
            
            if (code.isEmpty()) {
                System.out.println("Subject code cannot be empty.");
            }
        }
}
            subjects[totalSubjects] = code + " - " + name;
        totalSubjects++;
    }

    System.out.println("Subjects have been successfully registered!");
    return totalSubjects;
}

 
   
    public static void gradesEncoder(Scanner in,double[][] grades,String[] names,String[] subjects,int[] numbers, int totalStudents, int totalSubjects){
        System.out.println("Encoding Grades...");
       
      if (totalStudents == 0 || totalSubjects == 0) {
        System.out.println("There are currently no students or subjects registered yet.");
        return;
    }

    // Display student list
    System.out.println("\nChoose a student to encode grades for:");
    for (int i = 0; i < totalStudents; i++) {
        System.out.println("[" + (i + 1) + "] " + names[i]);
    }

    System.out.print("Enter student number: ");
    int choice = in.nextInt();
    in.nextLine(); // clear buffer

    int studentIndex = choice - 1;
    
    //search student number
    for (int i = 0; i < totalStudents; i++) {
    if (numbers[i] == choice) {
        studentIndex = i;
        break;
    }
}

if (studentIndex == -1) {
    System.out.println("Student not found.");
    return;
}

    System.out.println("\nEncoding grades for: " + names[studentIndex]);

    // Enter grades for each subject
    for (int j = 0; j < totalSubjects; j++) {
        System.out.print("Enter grade for " + subjects[j] + ": ");
        grades[studentIndex][j] = in.nextDouble();
    }
    in.nextLine(); // clear buffer

    System.out.println("\nGrades have been successfully encoded!");
}

   
    public static void classPerformance(Scanner in,double[][] grades,String[] names,String[] subjects, int totalStudents, int totalSubjects){
       
        if (totalStudents == 0 || totalSubjects == 0) {
        System.out.println("There are currently no students or subjects registered yet.");
        return;
    }

    // Display subject averages
    for (int j = 0; j < totalSubjects; j++) {
        double sum = 0;

        for (int i = 0; i < totalStudents; i++) {
            sum += grades[i][j];
        }
       
        System.out.println("Evaluating Class Performance...");

        double average = sum / totalStudents;
        System.out.printf("Average for %s: %.2f%n", subjects[j], average);
    }

    // Compute overall class average
    double totalSum = 0;
    int count = totalStudents * totalSubjects;

    for (int i = 0; i < totalStudents; i++) {
        for (int j = 0; j < totalSubjects; j++) {
            totalSum += grades[i][j];
        }
    }

    System.out.printf("\nOverall Class Average: %.2f%n", (totalSum / count));
}
       
   
    public static void studentPerformance(Scanner in,double[][] grades,String[] names,String[] subjects,int[] numbers,  int totalStudents, int totalSubjects){

if (totalStudents == 0 || totalSubjects == 0) {
        System.out.println("There are currently no students or subjects registered yet.");
        return;
    }

    // Ask for student number (index-based)
    System.out.print("Enter student number: ");
    int studentNum = in.nextInt();
    in.nextLine();
 

    int studentIndex = -1;

    // Search student using a loop
    for (int i = 0; i < totalStudents; i++) {
        if (numbers[i] == studentNum) {
            studentIndex = i;
            break;
        }
    }

    if (studentIndex == -1) {
        System.out.println("Student not found.");
        return;
    }
    System.out.println("Evaluating Student Performance...");
    System.out.println("\nStudent Name: " + names[studentIndex]);
    System.out.println("\nSubjects and Grades:");

    double sum = 0;

    // Display subjects and grades
    for (int j = 0; j < totalSubjects; j++) {
        System.out.println(subjects[j] + ": " + grades[studentIndex][j]);
        sum += grades[studentIndex][j];
    }

    // Compute general average
    double average = sum / totalSubjects;
    System.out.printf("\nGeneral Average: %.2f%n", average);

    // Remarks
    if (average >= 75) {
        System.out.println("Remarks: PASSED");
    } else {
        System.out.println("Remarks: FAILED");
    }
}
   
    public static void studentList(Scanner in,int[] numbers,String[] names,int totalStudents){
       
 if (totalStudents == 0) {
        System.out.println("There are currently no students registered yet.");
        return;
    }

    // Ask for student number
    System.out.print("Enter Student Number: ");
    int num = in.nextInt();
    in.nextLine();

    System.out.println("Searching student...");

    // Search the array
    for (int i = 0; i < totalStudents; i++) {
        if (numbers[i] == num) {

            // If found: show basic information
            System.out.println("Student Found!");
            System.out.println("Name: " + names[i]);
            System.out.println("Student Number: " + numbers[i]);
            return;
        }
    }

    // If not found
    System.out.println("Student not found.");
    }

}