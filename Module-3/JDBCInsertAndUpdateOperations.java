import java.sql.*;

public class  JDBCInsertAndUpdateOperations{
    public static void main(String[] args) {
        StudentDAO dao = new StudentDAO();
        dao.insertStudent(1, "Alice", 20);
        dao.updateStudentAge(1, 21);
    }
}

class StudentDAO {
    private final String url = "jdbc:sqlite:students.db";

    public void insertStudent(int id, String name, int age) {
        String query = "INSERT INTO students (id, name, age) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            Class.forName("org.sqlite.JDBC");
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setInt(3, age);
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateStudentAge(int id, int newAge) {
        String query = "UPDATE students SET age = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            Class.forName("org.sqlite.JDBC");
            pstmt.setInt(1, newAge);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
