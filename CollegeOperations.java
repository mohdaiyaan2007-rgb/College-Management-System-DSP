import java.io.*;
import java.util.Scanner;

public class CollegeOperations {

    static Scanner sc = new Scanner(System.in);

    static StudentNode studentHead = null;
    static CourseNode courseHead = null;

    static String[] admissionQueue = new String[50];
    static int queueFront = 0;
    static int queueBack = 0;

    static void addStudent() {
        System.out.println("\n--- Add Student ---");
        System.out.print("Enter Roll Number : ");
        int roll = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name        : ");
        String name = sc.nextLine();
        System.out.print("Enter Subject     : ");
        String sub = sc.nextLine();
        System.out.print("Enter Attendance% : ");
        int att = sc.nextInt();
        System.out.print("Enter Marks       : ");
        int mrk = sc.nextInt();
        sc.nextLine();

        StudentNode newNode = new StudentNode(roll, name, sub, att, mrk);

        if (studentHead == null) {
            studentHead = newNode;
        } else {
            StudentNode cnode = studentHead;
            while (cnode.next != null) {
                cnode = cnode.next;
            }
            cnode.next = newNode;
        }
        System.out.println("Student added successfully!");
    }

    static void viewStudents() {
        System.out.println("\n--- All Students ---");
        if (studentHead == null) {
            System.out.println("No students found.");
            return;
        }
        StudentNode cnode = studentHead;
        int count = 1;
        while (cnode != null) {
            System.out.println("Student " + count);
            System.out.println("Roll No    : " + cnode.rollNo);
            System.out.println("Name       : " + cnode.name);
            System.out.println("Subject    : " + cnode.subject);
            System.out.println("Attendance : " + cnode.attendance + "%");
            System.out.println("Marks      : " + cnode.marks);
            System.out.println("----------------------------");
            cnode = cnode.next;
            count++;
        }
    }

    static void checkLowAttendance() {
        System.out.println("\n--- Students with Attendance Below 75% ---");
        if (studentHead == null) {
            System.out.println("No students found.");
            return;
        }
        boolean found = false;
        StudentNode cnode = studentHead;
        while (cnode != null) {
            if (cnode.attendance < 75) {
                System.out.println("Roll No: " + cnode.rollNo + " | Name: " + cnode.name + " | Attendance: " + cnode.attendance + "%");
                found = true;
            }
            cnode = cnode.next;
        }
        if (found == false) {
            System.out.println("All students have attendance above 75%.");
        }
    }

    static void addCourse() {
        System.out.print("\nEnter course name to add: ");
        String name = sc.nextLine();

        CourseNode newNode = new CourseNode(name);

        if (courseHead == null) {
            courseHead = newNode;
        } else {
            CourseNode cnode = courseHead;
            while (cnode.next != null) {
                cnode = cnode.next;
            }
            cnode.next = newNode;
        }
        System.out.println("Course added: " + name);
    }

    static void viewCourses() {
        System.out.println("\n--- Registered Courses ---");
        if (courseHead == null) {
            System.out.println("No courses registered.");
            return;
        }
        CourseNode cnode = courseHead;
        int count = 1;
        while (cnode != null) {
            System.out.println(count + ". " + cnode.courseName);
            cnode = cnode.next;
            count++;
        }
    }

    static void applyAdmission() {
        System.out.print("\nEnter student name for admission: ");
        String name = sc.nextLine();
        admissionQueue[queueBack] = name;
        queueBack++;
        System.out.println(name + " added to admission queue.");
    }

    static void admitStudent() {
        System.out.println("\n--- Admit Next Student ---");
        if (queueFront == queueBack) {
            System.out.println("Admission queue is empty.");
            return;
        }
        System.out.println("Admitted: " + admissionQueue[queueFront]);
        queueFront++;
    }

    static void linearSearch() {
        System.out.print("\nEnter Roll Number to search: ");
        int target = sc.nextInt();
        sc.nextLine();

        if (studentHead == null) {
            System.out.println("No students found.");
            return;
        }

        boolean found = false;
        StudentNode cnode = studentHead;
        while (cnode != null) {
            if (cnode.rollNo == target) {
                System.out.println("Student Found!");
                System.out.println("Name       : " + cnode.name);
                System.out.println("Subject    : " + cnode.subject);
                System.out.println("Attendance : " + cnode.attendance + "%");
                System.out.println("Marks      : " + cnode.marks);
                found = true;
                break;
            }
            cnode = cnode.next;
        }

        if (found == false) {
            System.out.println("Student with Roll No " + target + " not found.");
        }
    }

    static void sortByMarks() {
        if (studentHead == null) {
            System.out.println("No students to sort.");
            return;
        }

        int count = 0;
        StudentNode cnode = studentHead;
        while (cnode != null) {
            count++;
            cnode = cnode.next;
        }

        int[] tempMarks = new int[count];
        cnode = studentHead;
        for (int i = 0; i < count; i++) {
            tempMarks[i] = cnode.marks;
            cnode = cnode.next;
        }

        mergeSort(tempMarks, 0, count - 1);

        System.out.print("Marks Sorted : ");
        for (int i = 0; i < count; i++) {
            System.out.print(tempMarks[i] + " ");
        }
        System.out.println();
        System.out.println("Highest Marks : " + tempMarks[count - 1]);
        System.out.println("Lowest Marks  : " + tempMarks[0]);
    }

    static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];
        for (int i = 0; i < n1; i++) {
            leftArr[i] = arr[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArr[j] = arr[mid + 1 + j];
        }
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }
        while (i < n1) { arr[k] = leftArr[i]; i++; k++; }
        while (j < n2) { arr[k] = rightArr[j]; j++; k++; }
    }

    static void sortByAttendance() {
        if (studentHead == null) {
            System.out.println("No students to sort.");
            return;
        }

        int count = 0;
        StudentNode cnode = studentHead;
        while (cnode != null) {
            count++;
            cnode = cnode.next;
        }

        int[] tempAtt = new int[count];
        cnode = studentHead;
        for (int i = 0; i < count; i++) {
            tempAtt[i] = cnode.attendance;
            cnode = cnode.next;
        }

        quickSort(tempAtt, 0, count - 1);

        System.out.print("Attendance Sorted : ");
        for (int i = 0; i < count; i++) {
            System.out.print(tempAtt[i] + "% ");
        }
        System.out.println();
        System.out.println("Highest Attendance: " + tempAtt[count - 1] + "%");
        System.out.println("Lowest Attendance : " + tempAtt[0] + "%");
    }

    static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    static void saveData() {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter("students.txt"));
            StudentNode s = studentHead;
            while (s != null) {
                pw.println(s.rollNo + "|" + s.name + "|" + s.subject + "|" + s.attendance + "|" + s.marks);
                s = s.next;
            }
            pw.close();

            pw = new PrintWriter(new FileWriter("courses.txt"));
            CourseNode c = courseHead;
            while (c != null) {
                pw.println(c.courseName);
                c = c.next;
            }
            pw.close();

            pw = new PrintWriter(new FileWriter("admission.txt"));
            pw.println(queueFront);
            pw.println(queueBack);
            for (int i = 0; i < admissionQueue.length; i++) {
                if (admissionQueue[i] != null) {
                    pw.println(i + "|" + admissionQueue[i]);
                }
            }
            pw.close();
            System.out.println("Data saved successfully!");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    static void loadData() {
        try {
            File f = new File("students.txt");
            if (f.exists()) {
                Scanner s = new Scanner(f);
                while (s.hasNextLine()) {
                    String line = s.nextLine();
                    if (line.trim().isEmpty()) continue;
                    String[] parts = line.split("\\|");
                    if (parts.length >= 5) {
                        int roll = Integer.parseInt(parts[0]);
                        String name = parts[1];
                        String sub = parts[2];
                        int att = Integer.parseInt(parts[3]);
                        int mrk = Integer.parseInt(parts[4]);

                        StudentNode newNode = new StudentNode(roll, name, sub, att, mrk);
                        if (studentHead == null) {
                            studentHead = newNode;
                        } else {
                            StudentNode cnode = studentHead;
                            while (cnode.next != null) {
                                cnode = cnode.next;
                            }
                            cnode.next = newNode;
                        }
                    }
                }
                s.close();
            }

            f = new File("courses.txt");
            if (f.exists()) {
                Scanner s = new Scanner(f);
                while (s.hasNextLine()) {
                    String line = s.nextLine();
                    if (line.trim().isEmpty()) continue;
                    CourseNode newNode = new CourseNode(line);
                    if (courseHead == null) {
                        courseHead = newNode;
                    } else {
                        CourseNode cnode = courseHead;
                        while (cnode.next != null) {
                            cnode = cnode.next;
                        }
                        cnode.next = newNode;
                    }
                }
                s.close();
            }

            f = new File("admission.txt");
            if (f.exists()) {
                Scanner s = new Scanner(f);
                if (s.hasNextLine()) queueFront = Integer.parseInt(s.nextLine());
                if (s.hasNextLine()) queueBack = Integer.parseInt(s.nextLine());
                while (s.hasNextLine()) {
                    String line = s.nextLine();
                    if (line.trim().isEmpty()) continue;
                    String[] parts = line.split("\\|", 2);
                    if (parts.length == 2) {
                        admissionQueue[Integer.parseInt(parts[0])] = parts[1];
                    }
                }
                s.close();
            }
        } catch (Exception e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }
}
