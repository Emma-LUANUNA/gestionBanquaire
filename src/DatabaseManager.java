import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseManager {

    private static final String URL = "jdbc:mysql://localhost:3306/gestion_banque";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // Remplissez avec votre mot de passe

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void ajouterCompteBancaire(CompteBancaire compte) {
        String query = "INSERT INTO comptebancaire (numeroCompte, nomTitulaire, solde) VALUES (?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, compte.getNumeroCompte());
            preparedStatement.setString(2, compte.getNomTitulaire());
            preparedStatement.setDouble(3, compte.getSolde());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Compte bancaire ajouté avec succès !");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erreur lors de l'ajout du compte bancaire !");
        }
    }
}