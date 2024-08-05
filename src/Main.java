import com.google.gson.Gson;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {



        Banque banque = new Banque();
        banque.chargerComptes();
        Scanner scanner = new Scanner(System.in);

        // Ajout de quelques comptes pour démonstration
        banque.ajouterCompte(new CompteCourant("123", "Alice", 1000, 500));
        banque.ajouterCompte(new CompteEpargne("456", "Bob", 2000, 0.02));

        // Exemple d'utilisation
        while (true) {
            System.out.println("1. Ajouter un compte bancaire");
            System.out.println("2. Supprimer un compte bancaire");
            System.out.println("3. Modifier un compte bancaire par son identifiant");
            System.out.println("4. Rechercher un compte bancaire par nom de titulaire");
            System.out.println("5. Lister les comptes bancaires en saisissant une lettre alphabétique");
            System.out.println("6. Afficher le nombre de comptes bancaires par type");
            System.out.println("7. Afficher les comptes par type");
            System.out.println("8. Afficher les détails d'un compte par son identifiant");
            System.out.println("9. Sauvegarder les comptes");
            System.out.println("10. Quitter");

            int choix = scanner.nextInt();
            scanner.nextLine(); // Consomme la nouvelle ligne

            CompteBancaire compte = null;
            switch (choix) {
                // ... (autres cas)

                case 1:
                    System.out.println("Type de compte (courant/epargne): ");
                    String type = scanner.nextLine();
                    System.out.println("Numéro de compte: ");
                    String numero = scanner.nextLine();
                    System.out.println("Nom du titulaire: ");
                    String nom = scanner.nextLine();
                    System.out.println("Solde: ");
                    double solde = scanner.nextDouble();

                    if (type.equalsIgnoreCase("courant")) {
                        System.out.println("Découvert autorisé: ");
                        double decouvert = scanner.nextDouble();
                        compte = new CompteCourant(numero, nom, solde, decouvert);
                    } else if (type.equalsIgnoreCase("epargne")) {
                        System.out.println("Taux d'intérêt: ");
                        double taux = scanner.nextDouble();
                        compte = new CompteEpargne(numero, nom, solde, taux);
                    }

                    if (compte != null) {
                        banque.ajouterCompte(compte);
                        DatabaseManager.ajouterCompteBancaire(compte);
                    }
                    break;

                // ... (autres cas)
            }
        }
    }
}