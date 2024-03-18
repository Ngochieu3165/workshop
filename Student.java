
package managerstutea;

public class Student {
    private int studentID;
    private String name;
    private String address;
    private int phone;
    private int teacherID;

    public Student() {
    }

    public Student(int studentID) {
        this.studentID = studentID;
    }

    public Student(String name) {
        this.name = name;
    }

    public Student(int studentID, String name, String address, int phone, int teacherID) {
        this.studentID = studentID;
        this.name = name;
        this.address = address;
        if (phone >= 1000000 && phone <= 9999999)
            this.phone = phone;
        else this.phone = 0;
        this.teacherID = teacherID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
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

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    @Override
    public String toString() {
        return String.format("%-10d%-10s%-10s%-10d%-10d",studentID,name,address,phone,teacherID);
    }

    @Override
    public boolean equals(Object obj) {
        final Student other = (Student) obj;
        if (this.studentID != other.studentID) {
            return false;
        }
        return true;
    }
    
    
}

