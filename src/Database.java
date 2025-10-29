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

    // Metodo per il CREATE (Insert in SQL)
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
        System.out.println("Query per il create: " + query);
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

        // L'SQL injection si effettua difficilmente perché se si inseriscono altre istruzioni vengono incluse nel nome del piatto
        return true;
    }

    // Metodo per il READ (Select in SQL)
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
        System.out.println("Query per il read: " + query);
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

    // Metodo per l'UPDATE (Rimane Update in SQL)
    public boolean update(int id, String nomePiatto, float prezzo, int quantita) {

        // Controllo se la connessione è ancora valida
        try {
            if (connection == null || !connection.isValid(5)){
                System.err.println("Errore di connessione al database");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Errore di connessione al database");
            return false;
        }

        // Faccio l'update per ID (ma si potrebbe fare anche per il nome del piatto)
        String query = "UPDATE menu SET piatto = ?, prezzo = ?, quantita = ? WHERE id = ?";
        System.out.println("Query per l'update: " + query);

        // L'SQL injection si effettua difficilmente perché se si inseriscono altre istruzioni vengono incluse nel nome del piatto
        try {
            PreparedStatement stmt = connection.prepareStatement(query);

            stmt.setString(1, nomePiatto);
            stmt.setFloat(2, prezzo);
            stmt.setInt(3, quantita);
            stmt.setInt(4, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Errore di query: " + e.getMessage());
            return false;
        }

        return true;
    }

    // Metodo per il DELETE (Rimande Delete in SQL)
    public boolean delete(int id) {

        // Controllo se la connessione è ancora valida
        try {
            if (connection == null || !connection.isValid(5)) {
                System.err.println("Errore di connessione al database");
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Errore di connessione al database: " + e.getMessage());
            return false;
        }

        // Faccio il delete per ID (ma si potrebbe fare anche per il nome del piatto se non ci sono piatti duplicati)
        String query = "DELETE FROM menu WHERE id = ?";
        System.out.println("Query per il delete: " + query);

        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Errore di query: " + e.getMessage());
            return false;
        }

        return true;
    }

}
