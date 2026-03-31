public class StudentNode {
    int rollNo;
    String name;
    String subject;
    int attendance;
    int marks;
    StudentNode next;

    public StudentNode(int rollNo, String name, String subject, int attendance, int marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.subject = subject;
        this.attendance = attendance;
        this.marks = marks;
        this.next = null;
    }
}
