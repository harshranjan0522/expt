package c;

import java.util.*;

public class StudentMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentDAO dao = new StudentDAO();
        int choice;

        do {
            System.out.println("\n===== STUDENT MANAGEMENT MENU =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Department: ");
                    String dept = sc.nextLine();
                    System.out.print("Enter Marks: ");
                    double marks = sc.nextDouble();
                    Student s = new Student(id, name, dept, marks);
                    dao.addStudent(s);
                    break;

                case 2:
                    List<Student> list = dao.getAllStudents();
                    System.out.println("\n---------------------------------------------");
                    System.out.printf("%-10s %-20s %-15s %-10s%n", "ID", "Name", "Department", "Marks");
                    System.out.println("---------------------------------------------");
                    for (Student st : list) {
                        System.out.printf("%-10d %-20s %-15s %-10.2f%n",
                                st.getStudentID(), st.getName(), st.getDepartment(), st.getMarks());
                    }
                    break;

                case 3:
                    System.out.print("Enter Student ID to update: ");
                    int uid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter New Name: ");
                    String uname = sc.nextLine();
                    System.out.print("Enter New Department: ");
                    String udept = sc.nextLine();
                    System.out.print("Enter New Marks: ");
                    double umarks = sc.nextDouble();
                    Student us = new Student(uid, uname, udept, umarks);
                    dao.updateStudent(us);
                    break;

                case 4:
                    System.out.print("Enter Student ID to delete: ");
                    int did = sc.nextInt();
                    dao.deleteStudent(did);
                    break;

                case 5:
                    System.out.println("Exiting... Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 5);
        sc.close();
    }
}

