package StudentManager;

import java.sql.Connection;
import java.sql.Statement;

public class CreateTable {
    public static void main(String[] args) throws Exception {
        Connection c = DBConnection.getConnection();

        String createStudentTable = """
            CREATE TABLE IF NOT EXISTS Student (
                student_id INT PRIMARY KEY,
                name VARCHAR(100),
                branch VARCHAR(50),
                year INT,
                phone VARCHAR(15),
                marks DOUBLE
            );
            """;

        Statement s = c.createStatement();
        s.executeUpdate(createStudentTable);

        System.out.println("Student Table Created Successfully");
    }
}