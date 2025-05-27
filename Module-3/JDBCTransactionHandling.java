import java.sql.*;

public class JDBCTransactionHandling
 {
    public static void main(String[] args) {
        AccountDAO dao = new AccountDAO();
        dao.transferMoney(1, 2, 500);
    }
}
class AccountDAO {
    private final String url = "jdbc:sqlite:students.db";

    public void transferMoney(int fromAccountId, int toAccountId, double amount) {
        String debitQuery = "UPDATE accounts SET balance = balance - ? WHERE id = ?";
        String creditQuery = "UPDATE accounts SET balance = balance + ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url)) {
            Class.forName("org.sqlite.JDBC");
            conn.setAutoCommit(false);

            try (PreparedStatement debitStmt = conn.prepareStatement(debitQuery);
                 PreparedStatement creditStmt = conn.prepareStatement(creditQuery)) {
                debitStmt.setDouble(1, amount);
                debitStmt.setInt(2, fromAccountId);
                debitStmt.executeUpdate();
                creditStmt.setDouble(1, amount);
                creditStmt.setInt(2, toAccountId);
                creditStmt.executeUpdate();

                conn.commit();
            } catch (Exception e) {
                conn.rollback();
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
