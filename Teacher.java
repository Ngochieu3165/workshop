
package managerstutea;

import java.util.Objects;


public class Teacher {
    private int teacherID;
    private String name;
    private String address;
    private String pass;
    private int phone;
    private String subject;

    public Teacher() {
    }

    public Teacher(int teacherID, String pass) {
        this.teacherID = teacherID;
        this.pass = pass;
    }

    public Teacher(int teacherID) {
        this.teacherID = teacherID;
    }
    
    
    public Teacher(int teacherID, String name, String address, String pass, int phone, String subject) {
        this.teacherID = teacherID;
        this.name = name;
        this.address = address;
        this.pass = pass;
        if (phone >= 1000000 && phone <= 9999999)
            this.phone = phone;
        this.subject = subject;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return String.format("%-10d%-10s%-10s%-10s%-10d%-10s",teacherID,name,address,pass,phone,subject);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Teacher other = (Teacher) obj;
        if (this.teacherID != other.teacherID) {
            return false;
        }
        return true;
    }
  

   
}
