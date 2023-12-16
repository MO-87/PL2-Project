package Course_Management_System;

import java.io.IOException;
import java.util.Scanner;

public class Menu {
    public static void Main_Menu() throws IOException {
        System.out.println("    --WELCOME TO YOUR COURSE MANAGEMENT SYSTEM!--   \n");
        System.out.println("1. Login.");
        System.out.println("2. Show Courses Available and its details.");
        System.out.println("0. Exit.\n");
        System.out.print("Enter your choice: ");
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        switch (choice){
            case 1:
                System.out.println("\n1. Login as a Student.");
                System.out.println("2. Login as an Instructor.");
                System.out.println("3. Login as an Admin.");
                System.out.println("0. Return to the Main Menu.\n");
                System.out.print("Enter your choice: ");
                choice = in.nextInt();
                switch (choice){
                    case 1:
                        Login_Menu(1);
                        break;
                    case 2:
                        Login_Menu(2);
                        break;
                    case 3:
                        Login_Menu(3);
                        break;
                    case 0:
                        Main_Menu();
                        break;
                    default:
                        System.out.println("Wrong Choice !\n");
                        Main_Menu();
                }
                break;
            case 2:
                System.out.println("Courses Page.");
                break;
            case 0:
                return;
            default:
                System.out.println("Wrong Choice !\n");
                Main_Menu();
        }
    }

    private static void Login_Menu(int type) throws IOException {
        Scanner inn = new Scanner(System.in);
        System.out.print("\nEnter your name: ");
        String name = inn.nextLine().trim();
        while (name.isEmpty())
        {
            System.out.println("Invalid name format, Try again.\n");
            System.out.print("Enter your name: ");
            name = inn.nextLine().trim();
        }
        System.out.print("Enter your password: ");

        String password = inn.nextLine().trim();
        while (password.isEmpty())
        {
            System.out.print("Invalid password format, Try again.\n");
            System.out.print("Enter your password: ");
            password = inn.nextLine().trim();
        }

        if(type == 1) {
            Student st = new Student(name, password);

            if(st.login(type, st)){
                System.out.println("\nLogged in successfully !\n");
                Student_Menu(st);
            }else{
                System.out.println("Wrong Name or Password ! Try again.\n");
                Main_Menu();
            }
        } else if (type == 2) {

            Instructor inst = new Instructor(name, password);

            if(inst.login(type, inst)) {

                System.out.println("\nLogged in successfully !\n");
                Instructor_Menu(inst);

            }else{
                System.out.println("Wrong Name or Password ! Try again.\n");
                Main_Menu();
            }
        } else if (type == 3) {
            Admin ad = new Admin(name, password);
            if(ad.login(type, ad)){
                System.out.println("\nLogged in successfully !\n");
                // Admin_Menu(ad);
            }else{
                System.out.println("Wrong Name or Password ! Try again.\n");
                Main_Menu();
            }
        }else {
            System.out.println("Invalid Choice !\n");
            Main_Menu();
        }
    }

    private static void Student_Menu(Student st) throws IOException {
        Scanner input = new Scanner(System.in);
        Scanner input2 = new Scanner(System.in);
        Scanner input3 = new Scanner(System.in);
        Scanner input4 = new Scanner(System.in);
        Scanner input5 = new Scanner(System.in);
        System.out.println("1. Show all courses you are enrolled in.");
        System.out.println("2. Show grade of a specific course.");
        System.out.println("3. Change your password.");
        System.out.println("4. Add a feedback to a specific course.");
        System.out.println("5. Enroll in a course.");
        System.out.println("6. Logout.\n");
        System.out.print("Enter your choice: ");
        int choice = input.nextInt();

        if(choice == 2){
            System.out.print("\nEnter the course name: ");
            String cName = input2.next().trim();
            if(st.showGrade(cName, st) != null) {
                System.out.println("Your grade in \"" + cName + "\" is: " + st.showGrade(cName, st) + "\n");
                Student_Menu(st);
            }
            else {
                System.out.println("You are not enrolled in this course or Invalid course name.");
                Student_Menu(st);
            }
        } else if (choice == 1) {
            String[] stInfo = st.showCourses(st).split("\n");
            String stCourses = String.join(",", stInfo);
            if(!stCourses.isEmpty()) {
                System.out.println("\nYour courses are : " + stCourses + "\n");
                Student_Menu(st);
            }else{
                System.out.println("\nYou are not enrolled in any courses yet !\n");
                Student_Menu(st);
            }
            Student_Menu(st);

        } else if (choice == 3) {
            System.out.print("\nEnter your new password: ");
            String password = input2.nextLine().trim();
            while (password.isEmpty())
            {
                System.out.print("Invalid password format, Try again.\n");
                System.out.print("Enter your new password: ");
                password = input2.nextLine().trim();
            }
            if(st.updateStudentPassword(password, st)) {
                System.out.println("\nYour password changed successfully.\n");
                Student_Menu(st);
            }
            else {
                System.out.println("\nFailed to change your password, Try again.\n");
                Student_Menu(st);
            }
        } else if (choice == 4) {
            System.out.print("\nEnter the course name that you want to give a feedback about: ");
            String cName = input3.next().trim();
            System.out.print("Enter your feedback: ");
            String feedback = input4.nextLine().trim();
            if(st.feedback(st, cName, feedback)){
                System.out.println("Feedback added successfully.\n");
                Student_Menu(st);
            }else{
                System.out.println("Failed to add the feedback, Try again.");
                Student_Menu(st);
            }
        } else if (choice == 6) {
            Main_Menu();
        } else if (choice == 5) {
            String[] stInfo = st.showCourses().split("\n");
            String stCourses = String.join(",", stInfo);
            System.out.println("The Courses available to enroll into: " + stCourses + "\n");
            System.out.print("\nEnter the course name you want to enroll into: ");
            String cName = input5.next().trim();
            System.out.println("\n" + st.addCourse(st, cName));
            Student_Menu(st);
        }
    }

    private static void Instructor_Menu(Instructor inst) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("1. Add grades to a specific student.");
        System.out.println("0. Logout.\n");
        System.out.print("Enter your choice: ");
        int choice = in.nextInt();

        if(choice == 1){
            System.out.print("\nEnter Student ID: ");
            String id = in.next();
            System.out.print("Enter the course name you want to add the grade to: ");
            String cName = in.next();
            System.out.print("Enter the grade: ");
            String grade = in.next();
            if(inst.addGrade(id, grade, cName)){
                System.out.println("\nGrade added successfully.\n");
                Instructor_Menu(inst);
            }else {
                System.out.println("You do not teach this course or invalid course name ! Try again.\n");
                Instructor_Menu(inst);
            }
        }else if (choice == 0) {
            Main_Menu();
        }else {
            System.out.println("Wrong Choice ! Try again.\n");
            Instructor_Menu(inst);
        }
    }
}
