
package managerstutea;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Menu {
    public static Teacher acc;
    public static Scanner sc = new Scanner(System.in);
    
    private static String DB_URL = "jdbc:sqlserver://localhost:1433;"
            + "databaseName=managerstudent;"
            + "encryte=true;trustServerCentificate=true";
    private static String USER_NAME = "nghieu";
    private static String PASSWORD = "201122121312";
    
    public static void menuStudent(Controller mn, Connection conn)
    {
        int choice;
        boolean check = true;
        while (check)
        {
            System.out.println("Danh sach hien hanh: ");
            for (Student st: mn.arrst)
            {
                System.out.println(st);
            }
            Controller.getDataMenu(conn, 3);
            System.out.println("Enter your choice....");
            choice = sc.nextInt();
            switch(choice)
            {
                case 1: 
                    mn.addStudent();
                    break;
                case 2:
                    mn.deleteStudent();
                    break;
                case 3:
                    mn.editStudent();
                    break;
                case 4:
                    mn.logout();
                    System.out.println("Logout successfully\n");
                    check = false;
                    break;
            }
        }
    }
    
    public static void menuTeacher(Controller mn, Connection conn)
    {
        int choice;
        boolean check = true;
        while (check)
        {
            System.out.println("\nDanh sach hien hanh: ");
            for (Teacher te: mn.giaovien)
            {
                System.out.println(te);
            }
            Controller.getDataMenu(conn, 2);
            System.out.println("Enter your choice....");
            choice = sc.nextInt();
            switch(choice)
            {
                case 1: 
                    mn.addTeacher();
                    break;
                case 2:
                    mn.deleteTeacher();
                    break;
                case 3:
                    mn.editTeacher();
                    break;
                case 4:
                    mn.logout();
                    System.out.println("Logout successfully\n");
                    check = false;
                    break;
                
            }
        }
    }
    public static void menuAll(Controller mn, Connection conn)
    {
        System.out.println("Ban la admin");
        Controller.getDataMenu(conn, 4);
        System.out.println("Enter your choice");
        int choice = sc.nextInt();
        if (choice == 1)
            Menu.menuStudent(mn, conn);
        else Menu.menuTeacher(mn, conn);
    }
    
    public static void findStudent(Controller mn, Connection conn)
    {
        Controller.getDataMenu(conn, 5);
        
        System.out.println("Enter your choice....");
            int choice = sc.nextInt();
            switch(choice)
            {
                case 1: 
                    System.out.println("Enter studentID you wanna find");
                    int ID = sc.nextInt();
                    Student st = new Student(ID);
                    for (Student a : mn.arrst)
                        if (a.equals(st))
                            System.out.println(a);
                    break;
                case 2:
                    System.out.println("Enter teacherID you wanna find");
                    ID = sc.nextInt();
                    Student s = new Student();
                    s.setTeacherID(ID);
                    for (Student a: mn.arrst)
                        if (a.getTeacherID() == (s.getTeacherID()))
                            System.out.println(a);
                    break;
                
                case 3:
                    System.out.println("Enter name you wanna find");
                    sc.nextLine();
                    String name = sc.nextLine();
                    Student stu = new Student(name);
                    for (Student a: mn.arrst)
                        if (a.equals(stu))
                            System.out.println(a);
                    break;
                
            }
    }
    public static void menuLog(Controller mn, Connection conn)
    {
            
            int choice;
            boolean check = true;
            while (check)
            {
                Controller.getDataMenu(conn, 1);
                System.out.println("Enter your choice....");
                choice = sc.nextInt();
                switch (choice)
                {
                    case 1: 
                        if (mn.login()) 
                        {
                            if (Integer.toString(Menu.acc.getTeacherID()).matches("11111(.*)"))
                            {
                                System.out.println("Login succesfully\n");
                                Menu.menuAll(mn, conn);
                            }
                        }
                        else System.err.println("Tai khoan hoac pass khong dung");
                        break;
                    case 2:
                        if (mn.login()) 
                        {
                            if (Integer.toString(Menu.acc.getTeacherID()).matches("11111(.*)") != true)
                            {
                                System.out.println("Login succesfully\n");
                                Menu.menuStudent(mn, conn);
                            }
                        }
                        else System.err.println("Tai khoan hoac pass khong dung");
                        break;
                    case 3:
                        System.err.println("Exit");
                        check = false;
                        break;
                    case 4: 
                        System.out.println("Login account admin");
                        if (mn.login())
                        {
                            if (Integer.toString(Menu.acc.getTeacherID()).matches("11111(.*)"))
                            {
                                System.out.println("Login succesfully\n");
                                System.out.println("Login account teacher");
                                if (mn.login())
                                    if (Integer.toString(Menu.acc.getTeacherID()).matches("11111(.*)") != true)
                                    {
                                        System.out.println("Login succesfully\n");
                                        Menu.findStudent(mn, conn);
                                    }
                                    else System.out.println("Error! Login by teacher");
                            }
                            else System.out.println("Error! Login by admin");
                                
                        }
                        break;
                }
            }
    }
    public static void main(String[] args) {
        try {
            Connection conn = Controller.getConnection(DB_URL, USER_NAME, PASSWORD);
            Controller c = new Controller();
            c.getDataAccTeacher(conn);
            c.getDataAccStudent(conn);
            
            Menu.menuLog(c, conn);
            
            c.save(conn);
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
