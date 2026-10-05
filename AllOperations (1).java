package StudentManager;

import java.sql.*;

public class AllOperations {
    public static final Connection c = DBConnection.getConnection();

    // 1. Add Student
    public static void addStudent(int id, String name, String branch, int year, String phone, double marks) {
        String query = "INSERT INTO Student VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = c.prepareStatement(query);
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, branch);
            ps.setInt(4, year);
            ps.setString(5, phone);
            ps.setDouble(6, marks);

            ps.executeUpdate();
            System.out.println("Student added successfully: " + name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 2. Search Student by ID
    public static void searchStudent(int id) {
        String query = "SELECT * FROM Student WHERE student_id = ?";
        try {
            PreparedStatement ps = c.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("\n--- STUDENT DETAILS FOUND ---");
                System.out.println("ID: " + rs.getInt("student_id") +
                                   " | Name: " + rs.getString("name") +
                                   " | Branch: " + rs.getString("branch") +
                                   " | Year: " + rs.getInt("year") +
                                   " | Phone: " + rs.getString("phone") +
                                   " | Marks: " + rs.getDouble("marks"));
                System.out.println("-----------------------------\n");
            } else {
                System.out.println("No student found with ID: " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 3. Update Student Details (Branch, Year, Phone, Marks)
    public static void updateStudentDetails(int id, String branch, int year, String phone, double marks) {
        String query = "UPDATE Student SET branch = ?, year = ?, phone = ?, marks = ? WHERE student_id = ?";
        try {
            PreparedStatement ps = c.prepareStatement(query);
            ps.setString(1, branch);
            ps.setInt(2, year);
            ps.setString(3, phone);
            ps.setDouble(4, marks);
            ps.setInt(5, id);

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Student details updated successfully for ID: " + id);
            } else {
                System.out.println("Student ID not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 4. View All Students
    public static void viewAllStudents() {
        String query = "SELECT * FROM Student";
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(query);
            System.out.println("\n--- ALL STUDENTS ---");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("student_id") +
                                   " | Name: " + rs.getString("name") +
                                   " | Branch: " + rs.getString("branch") +
                                   " | Year: " + rs.getInt("year") +
                                   " | Phone: " + rs.getString("phone") +
                                   " | Marks: " + rs.getDouble("marks"));
            }
            System.out.println("--------------------\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Main method to test operations
    public static void main(String[] args) {
        // Step 1: Add new students
        addStudent(101, "Alice Smith", "CSE", 2, "9876543210", 88.5);
        addStudent(102, "Bob Jones", "ECE", 3, "9123456780", 79.0);

        // Step 2: View all students
        viewAllStudents();

        // Step 3: Search student by ID
        searchStudent(101);

        // Step 4: Update details for ID 101
        updateStudentDetails(101, "CSE", 3, "9876543210", 92.0);

        // Step 5: View updated records
        searchStudent(101);
    }
}