public class CollegeManagementSystem {

    public static void main(String[] args) {

        CollegeOperations.loadData();
        int choice;
        String cont;

        do {
            System.out.println("\n========== COLLEGE MANAGEMENT SYSTEM  ==========");
            System.out.println("--- Student Records (Linked List) ---");
            System.out.println("1.  Add Student");
            System.out.println("2.  View All Students");
            System.out.println("3.  Check Low Attendance");
            System.out.println("--- Course Registration  ---");
            System.out.println("4.  Add Course");
            System.out.println("5.  View All Courses");
            System.out.println("--- Admission (Queue) ---");
            System.out.println("6.  Apply for Admission");
            System.out.println("7.  Admit Next Student");
            System.out.println("--- Search ---");
            System.out.println("8.  Search by Roll No ");
            System.out.println("--- Sort ---");
            System.out.println("9.  Sort by Marks");
            System.out.println("10. Sort by Attendance ");
            System.out.println("11. Exit");
            System.out.println("================================");
            System.out.print("Enter your choice: ");
            choice = CollegeOperations.sc.nextInt();
            CollegeOperations.sc.nextLine();

            switch (choice) {
                case 1:
                    CollegeOperations.addStudent();
                    break;
                case 2:
                    CollegeOperations.viewStudents();
                    break;
                case 3:
                    CollegeOperations.checkLowAttendance();
                    break;
                case 4:
                    CollegeOperations.addCourse();
                    break;
                case 5:
                    CollegeOperations.viewCourses();
                    break;
                case 6:
                    CollegeOperations.applyAdmission();
                    break;
                case 7:
                    CollegeOperations.admitStudent();
                    break;
                case 8:
                    CollegeOperations.linearSearch();
                    break;
                case 9:
                    CollegeOperations.sortByMarks();
                    break;
                case 10:
                    CollegeOperations.sortByAttendance();
                    break;
                case 11:
                    CollegeOperations.saveData();
                    System.out.println("Thank you! Exiting the system.");
                    break;
                default:
                    System.out.println("Wrong choice. Please try again.");
            }

            if (choice != 11) {
                System.out.print("Do you want to continue? (y/Y): ");
                cont = CollegeOperations.sc.nextLine();
            } else {
                cont = "n";
            }
        } while (cont.equals("y") || cont.equals("Y"));
    }
}
