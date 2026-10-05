package StudentManager;

import java.sql.Connection;
import java.sql.Statement;

public class CreateDataBase {
    public static void main(String[] args) throws Exception {
        Connection c = DBConnection.getConnection();
        String database = "CREATE DATABASE IF NOT EXISTS StudentDB";
        Statement s = c.createStatement();
        s.executeUpdate(database);
        System.out.println("Database Created Successfully");
    }
}