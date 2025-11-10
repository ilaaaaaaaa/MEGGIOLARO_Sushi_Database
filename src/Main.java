import javax.xml.crypto.Data;
import java.util.Scanner;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Database db = null;

        try {
            db = Database.getInstance();
        } catch (SQLException e) {
            System.out.println("Errore di connessione al database!");
            return;
        }

        int scelta = -1;

        while (scelta != 0) {
            // MENU'
            System.out.println("\n--- MENU SUSHI ---");
            System.out.println("1. Inserisci piatto (CREATE)");
            System.out.println("2. Mostra tutti i piatti (READ)");
            System.out.println("3. Modifica piatto (UPDATE)");
            System.out.println("4. Elimina piatto (DELETE)");
            System.out.println("0. Esci");
            System.out.print("Scegli: ");
            scelta = sc.nextInt();
            sc.nextLine();

            // Scelta dell'utente
            if (scelta == 1) {
                // Nome
                System.out.print("Nome piatto: ");
                String nome = sc.nextLine();

                // Prezzo
                System.out.print("Prezzo: ");
                float prezzo = sc.nextFloat();

                // Quantità
                System.out.print("Quantità: ");
                int quantita = sc.nextInt();

                if (db.insert(nome, prezzo, quantita))
                    System.out.println("Piatto inserito correttamente!");
            }
            else if (scelta == 2) {
                System.out.println("\n--- Elenco piatti ---");
                String result = db.selectAll();

                if (result == null || result.isEmpty())
                    System.out.println("Nessun piatto trovato.");
                else
                    System.out.println(result);
            }
            else if (scelta == 3) {
                // Id
                System.out.print("ID del piatto da modificare: ");
                int id = sc.nextInt();
                sc.nextLine();

                // Nome
                System.out.print("Nuovo nome: ");
                String nome = sc.nextLine();

                // Prezzo
                System.out.print("Nuovo prezzo: ");
                float prezzo = sc.nextFloat();

                // Quantità
                System.out.print("Nuova quantità: ");
                int quantita = sc.nextInt();

                if (db.update(id, nome, prezzo, quantita))
                    System.out.println("Piatto aggiornato!");
            }
            else if (scelta == 4) {
                // Id
                System.out.print("ID del piatto da eliminare: ");
                int id = sc.nextInt();

                if (db.delete(id))
                    System.out.println("Piatto eliminato!");
            }
            else if (scelta == 0) {
                System.out.println("Uscita dal programma...");
            }
            else {
                System.out.println("Scelta non valida!");
            }
        }

        sc.close();
    }
}
