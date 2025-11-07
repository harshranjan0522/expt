package com.example.studentmgmt;

import com.example.studentmgmt.config.AppConfig;
import com.example.studentmgmt.dao.StudentDAO;
import com.example.studentmgmt.model.Course;
import com.example.studentmgmt.model.Student;
import com.example.studentmgmt.service.FeeService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        StudentDAO dao = context.getBean(StudentDAO.class);
        FeeService feeService = context.getBean(FeeService.class);
        Course defaultCourse = context.getBean(Course.class);
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n==== Online Student Management ====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Pay Fee");
            System.out.println("6. Refund Fee");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1 -> {
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    Student s = new Student(name, defaultCourse, 10000);
                    dao.addStudent(s);
                    System.out.println("Student added successfully!");
                }
                case 2 -> {
                    List<Student> list = dao.getAllStudents();
                    for (Student st : list)
                        System.out.println(st.getStudentId() + " | " + st.getName() + " | " +
                                st.getCourse().getCourseName() + " | Balance: " + st.getBalance());
                }
                case 3 -> {
                    System.out.print("Enter ID to update: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    Student s = dao.getStudent(id);
                    if (s != null) {
                        System.out.print("Enter new name: ");
                        s.setName(sc.nextLine());
                        dao.updateStudent(s);
                        System.out.println("Updated successfully!");
                    } else System.out.println("Student not found!");
                }
                case 4 -> {
                    System.out.print("Enter ID to delete: ");
                    dao.deleteStudent(sc.nextInt());
                    System.out.println("Deleted successfully!");
                }
                case 5 -> {
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    System.out.print("Enter amount: ");
                    double amt = sc.nextDouble();
                    feeService.payFee(id, amt);
                }
                case 6 -> {
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    System.out.print("Enter amount: ");
                    double amt = sc.nextDouble();
                    feeService.refundFee(id, amt);
                }
                case 7 -> {
                    System.out.println("Exiting...");
                    context.close();
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }
}
