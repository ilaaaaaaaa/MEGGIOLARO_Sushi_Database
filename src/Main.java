import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws SQLException {
        Database db;
        try {
            db = new Database();
        } catch (SQLException ex) {
            System.err.println("Errore di connessione al database: " + ex.getMessage());
            System.exit(-1);
        }
    }
}