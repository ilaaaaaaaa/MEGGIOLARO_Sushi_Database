import java.sql.*;

public class Database {
    // connetti
    // query
    private Connection connection;

    public  Database() throws SQLException {
        String url = "jdbc:sqlite:database/sushi.db";
        connection = DriverManager.getConnection(url);
        System.out.println("Connessione al database");
    }

    public String selectAll() {
        String result = "";

        // isValid controlla che la connessione sia ancora valida
        try {
            if (connection == null || !connection.isValid(5)){
                System.err.println("Errore di connessione al database");
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Errore di connessione al database");
            return null;
        }

        String query = "SELECT * FROM menu";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet piatti = statement.executeQuery(); // Dato che ritorna un insieme di dati
            // lo cicliamo
            while (piatti.next()) {
                result += piatti.getString("id") + "\t";
                result += piatti.getString("piatto") + "\t";
                result += piatti.getString("prezzo") + "\t";
                result += piatti.getString("quantita") + "\n";
            }
        } catch (SQLException e) {
            System.err.println("Errore di query: " + e.getMessage());
            return null;
        }
        return result;
    }

    public boolean insert(String nomePiatto, float prezzo, int quantita) {
        // isValid controlla che la connessione sia ancora valida
        try {
            if (connection == null || !connection.isValid(5)){
                System.err.println("Errore di connessione al database");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Errore di connessione al database");
            return false;
        }

        String query = "INSERT INTO menu(piatto, prezzo, quantita) VALUES (?, ?, ?)";
        System.out.println(query);

        // L'SQL injection si effettua difficilmente perché se si inseriscono altre istruzioni vengono incluse nel nome del piatto
        try {
            PreparedStatement stmt = connection.prepareStatement(query);

            stmt.setString(1, nomePiatto);
            stmt.setFloat(2, prezzo);
            stmt.setInt(3, quantita);

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Errore di query: " + e.getMessage());
            return false;
        }

        return true;
    }
}
