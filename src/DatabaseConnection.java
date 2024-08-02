import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/gestion_banque";
    private static final String USER = "root";
    private static final String PASSWORD = ""; // Remplissez avec votre mot de passe

    public static void main(String[] args) {
        Connection connection = null;

        try {
            // Chargement du driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Établissement de la connexion
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            if (connection != null) {
                System.out.println("Connexion réussie !");
            } else {
                System.out.println("Échec de la connexion !");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Driver non trouvé !");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Erreur de connexion !");
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}