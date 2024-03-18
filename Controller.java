package managerstutea;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    public ArrayList<Teacher> giaovien = new ArrayList();
    public ArrayList<Student> arrst = new ArrayList();
    
    public static Scanner sc = new Scanner(System.in);
    
    
    public void getDataAccTeacher(Connection conn) {
        //truy vấn dữ liệu từ cơ sở dữ liệu
        try {
            java.sql.Statement stsm = conn.createStatement();
            java.sql.ResultSet rs = stsm.executeQuery("SELECT * FROM Teacher");

            while (rs.next()) {
                giaovien.add(new Teacher(rs.getInt("teacherID"), rs.getString("name"), rs.getString("address"),
                                         rs.getString("pass"), rs.getInt("phone"), rs.getString("subject")));
            }
        } catch (Exception e) {
            System.err.println("Khong lay duoc data duoc");
        }
    }
    
    public void getDataAccStudent(Connection conn) {
        //truy vấn dữ liệu từ cơ sở dữ liệu
        try {
            java.sql.Statement stsm = conn.createStatement();
            java.sql.ResultSet rs = stsm.executeQuery("SELECT * FROM Student");

            while (rs.next()) {
                arrst.add(new Student(rs.getInt("studentID"), rs.getString("name"), rs.getString("address"),
                                      rs.getInt("phone"), rs.getInt("teacherID")));
            }
        } catch (Exception e) {
            System.err.println("Khong lay duoc data duoc");
        }
    }
    // Quy dinh admin la nhung account co id dang 11111_
    public void createAccAdmin()
    {
        
        Teacher s = new Teacher();
        System.out.println("Enter adminID: ");
        s.setTeacherID(sc.nextInt());
        if (giaovien.contains(s))
        {
            System.err.println("This admin existed!!!");
            return;
        }
        if (Integer.toString(s.getTeacherID()).matches("11111(.*)"))
        {
        
            System.out.println("Enter admin's pass: ");
            sc.nextLine();
            s.setPass(sc.nextLine());
            giaovien.add(s);
            return;
        }
        System.err.println("AdminID isn't right");
        
    }
    public void show()
    {
        for (Teacher a: this.giaovien)
            System.out.println(a.getTeacherID() +" "+ a.getPass());
    }
    public boolean login()
    {
        System.out.println("Danh sach tai khoan:");
        this.show();
        System.out.println("Enter your account");
        int newAcc = sc.nextInt();
        System.out.println("Enter your pass");
        sc.nextLine();
        Teacher at = new Teacher(newAcc, sc.next());
        Menu.acc = giaovien.get(giaovien.indexOf(at));
        return giaovien.contains(at);
    }
    public void logout()
    {
        Menu.acc = null;
    }
    
   // Student  
    public void addStudent()
    {
        Student s = new Student();
        System.out.println("Enter studentID: ");
        s.setStudentID(sc.nextInt());
        if (arrst.contains(s))
        {
            System.err.println("This student existed!!!");
            return;
        }
        System.out.println("Enter name: ");
        sc.nextLine();
        s.setName(sc.nextLine());
        System.out.println("Enter phone student: ");
        s.setPhone(sc.nextInt());
        System.out.println("Enter address student: ");
        sc.nextLine();
        s.setAddress(sc.nextLine());
        System.out.println("Enter teacherID");
        s.setTeacherID(sc.nextInt());
        arrst.add(s);
    }
    public void deleteStudent()
    {
        Student s = new Student();
        System.out.println("Enter id you wanna edit: ");
        s.setStudentID(sc.nextInt());
        if (arrst.contains(s))
        {
            arrst.remove(s);
            System.err.println("Delete successfull");
            return;
        }
        System.err.println("Not find this student in list!!!");

    }
    public void editStudent()
    {
        Student s = new Student();
        System.out.println("Enter id you wanna edit: ");
        s.setStudentID(sc.nextInt());
        if (arrst.contains(s))
        {
            System.out.println("Enter name you wanna change");
            arrst.get(arrst.indexOf(s)).setName(sc.next());
            sc.nextLine();
            System.out.println("Enter phone you wanna change");
            arrst.get(arrst.indexOf(s)).setPhone(sc.nextInt());
            System.out.println("Enter address you wanna change");
            arrst.get(arrst.indexOf(s)).setAddress(sc.next());
            System.out.println("Enter teacher you wanna change");
            arrst.get(arrst.indexOf(s)).setTeacherID(sc.nextInt());
            return;
        }
        System.err.println("Not find your id in list!!!");
    }
    //Teacher
    public void addTeacher()
    {
        
        Teacher s = new Teacher();
        System.out.println("Enter teacherID: ");
        s.setTeacherID(sc.nextInt());
        if (giaovien.contains(s))
        {
            System.err.println("This teacher existed!!!");
            return;
        }
        if (Integer.toString(s.getTeacherID()).matches("11111(.*)"))
        {
            System.out.println("Khong duoc dat teacherID trung form voi adminID");
            return;
        }
        System.out.println("Enter name: ");
        sc.nextLine();
        s.setName(sc.nextLine());
        System.out.println("Enter teacher's phone: ");
        s.setPhone(sc.nextInt());
        System.out.println("Enter teacher's address: ");
        sc.nextLine();
        s.setAddress(sc.nextLine());
        System.out.println("Enter teacher's pass: ");
        sc.nextLine();
        s.setPass(sc.nextLine());
        System.out.println("Enter teacher's subject");
        sc.nextLine();
        s.setSubject(sc.nextLine());
        giaovien.add(s);
    }
    public void deleteTeacher()
    {
        Teacher s = new Teacher();
        System.out.println("Enter id you wanna edit: ");
        s.setTeacherID(sc.nextInt());
        if (giaovien.contains(s))
        {
            giaovien.remove(s);
            return;
        }
        System.err.println("Not find this student in list!!!");

    }
    public void editTeacher()
    {
        Teacher s = new Teacher();
        System.out.println("Enter id you wanna edit: ");
        s.setTeacherID(sc.nextInt());
        if (giaovien.contains(s))
        {
            System.out.println("Enter name you wanna change");
            giaovien.get(giaovien.indexOf(s)).setName(sc.next());
            sc.nextLine();
            System.out.println("Enter phone you wanna change");
            giaovien.get(giaovien.indexOf(s)).setPhone(sc.nextInt());
            System.out.println("Enter address you wanna change");
            giaovien.get(giaovien.indexOf(s)).setAddress(sc.next());
            System.out.println("Enter teacher you wanna change");
            giaovien.get(giaovien.indexOf(s)).setTeacherID(sc.nextInt());
            return;
        }
        System.err.println("Not find your id in list!!!");
    }
    
    
    //SQL
    public void save(Connection conn) { // Luu data vao SQL 
        try {
            //xóa dữ liệu cũ trước khi chèn
            String deleteQuery = "DELETE FROM Teacher";
            PreparedStatement deleteStatement = conn.prepareStatement(deleteQuery);
            deleteStatement.execute();
            //câu lệnh SQL để chèn dữ liệu vào bảng
            String sql = "INSERT INTO Teacher (teacherID, name, address, pass, phone, subject) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            //lặp qua đối tượng người dùng và chèn dữ liệu vào cơ sở dữ liệu
            for (Teacher te : this.giaovien) {
                preparedStatement.setInt(1, te.getTeacherID());
                preparedStatement.setString(2, te.getName());
                preparedStatement.setString(3, te.getAddress());
                preparedStatement.setString(4, te.getPass());
                preparedStatement.setInt(5, te.getPhone());
                preparedStatement.setString(6, te.getSubject());

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Data duoc chen thanh cong");
                } else {
                    System.out.println("Khong co data nao duoc chen");
                }
            }
        } catch (Exception e) {
        }
        try {
            //xóa dữ liệu cũ trước khi chèn
            String deleteQuery = "DELETE FROM Student";
            PreparedStatement deleteStatement = conn.prepareStatement(deleteQuery);
            deleteStatement.execute();
            //câu lệnh SQL để chèn dữ liệu vào bảng
            String sql = "INSERT INTO Student (studentID, name, address, phone, teacherID) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            //lặp qua đối tượng người dùng và chèn dữ liệu vào cơ sở dữ liệu
            for (Student te : this.arrst) {
                preparedStatement.setInt(1, te.getStudentID());
                preparedStatement.setString(2, te.getName());
                preparedStatement.setString(3, te.getAddress());
                preparedStatement.setInt(4, te.getPhone());
                preparedStatement.setInt(5, te.getTeacherID());
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Data duoc chen thanh cong");
                } else {
                    System.out.println("Khong co data nao duoc chen");
                }
            }
        } catch (Exception e) {
        }
    }
    public static Connection getConnection(String dbURL, String userName, String password) {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(dbURL, userName, password);
            System.out.println("connect successfully!");
        } catch (Exception ex) {
            System.out.println("connection failure!");
            ex.printStackTrace();
        }
        return conn;
    }
    public static void getDataMenu(Connection conn, int x) {
        //truy vấn dữ liệu từ cơ sở dữ liệu
        try {
            java.sql.Statement stsm = conn.createStatement();
            java.sql.ResultSet rs = stsm.executeQuery("SELECT * FROM Menu");

            while (rs.next()) {
                String choose = rs.getString("Choose");
                int position = rs.getInt("Position");
                int id = rs.getInt("id");
                if (x == position)
                    System.out.println(id + "." + choose);
            }
        } catch (Exception e) {
            System.err.println("Khong lay duoc data duoc");
        }
    }
}
