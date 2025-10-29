import java.sql.SQLException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws SQLException {
        Database db = null;
        try {
            db = new Database();
        } catch (SQLException ex) {
            System.err.println("Errore di connessione al database: " + ex.getMessage());
            System.exit(-1);
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("Inserisci il nome del piatto da inserire: ");
        String nomePiatto = sc.nextLine();

        System.out.println("Inserisci il prezzo del piatto da inserire: ");
        float prezzo = sc.nextFloat();

        System.out.println("Inserisci la quantità del piatto da inserire: ");
        int quantita = sc.nextInt();
        sc.nextLine();

        if(db.insert(nomePiatto, prezzo, quantita)) {
            System.out.println("Piatto inserito con successo");
        }

        // if(db.insert("Udon frutti di mare", 5, 1))
       //     System.out.println("Piatto inserito con successo");

        System.out.println(db.selectAll());
    }
}