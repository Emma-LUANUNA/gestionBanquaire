import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class Banque {
    private Map<String, CompteBancaire> comptes = new HashMap<>();
    private static final String FILENAME = "comptes.json";

    public void ajouterCompte(CompteBancaire compte) {
        comptes.put(compte.getNumeroCompte(), compte);
    }

    public void supprimerCompte(String numeroCompte) {
        comptes.remove(numeroCompte);
    }

    public CompteBancaire rechercherCompteParNumero(String numeroCompte) {
        return comptes.get(numeroCompte);
    }

    public CompteBancaire rechercherCompteParNom(String nomTitulaire) {
        for (CompteBancaire compte : comptes.values()) {
            if (compte.getNomTitulaire().equalsIgnoreCase(nomTitulaire)) {
                return compte;
            }
        }
        return null;
    }

    public void listerComptesParLettre(char lettre) {
        for (CompteBancaire compte : comptes.values()) {
            if (compte.getNomTitulaire().charAt(0) == lettre) {
                System.out.println(compte);
            }
        }
    }

    public void afficherNombreComptesParType() {
        int nombreCourants = 0;
        int nombreEpargnes = 0;
        for (CompteBancaire compte : comptes.values()) {
            if (compte instanceof CompteCourant) {
                nombreCourants++;
            } else if (compte instanceof CompteEpargne) {
                nombreEpargnes++;
            }
        }
        System.out.println("Nombre de comptes courants: " + nombreCourants);
        System.out.println("Nombre de comptes épargne: " + nombreEpargnes);
    }

    public void afficherComptesParType(String type) {
        for (CompteBancaire compte : comptes.values()) {
            if ((type.equalsIgnoreCase("courant") && compte instanceof CompteCourant) ||
                    (type.equalsIgnoreCase("epargne") && compte instanceof CompteEpargne)) {
                System.out.println(compte);
            }
        }
    }

    public void afficherDetailsCompte(String numeroCompte) {
        CompteBancaire compte = rechercherCompteParNumero(numeroCompte);
        if (compte != null) {
            System.out.println(compte);
        } else {
            System.out.println("Compte non trouvé.");
        }
    }

    public void sauvegarderComptes() {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(FILENAME)) {
            gson.toJson(comptes, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void chargerComptes() {
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, CompteBancaire>>() {}.getType();
        try (FileReader reader = new FileReader(FILENAME)) {
            comptes = gson.fromJson(reader, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Ajoutez cette variable dans la classe Banque
    private DatabaseManager dbManager = new DatabaseManager();

    public void ajouterCompteBD(CompteBancaire compte) {
        try (Connection conn = dbManager.getConnection()) {
            String insertCompteBancaire = "INSERT INTO CompteBancaire (numeroCompte, nomTitulaire, solde) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertCompteBancaire)) {
                pstmt.setString(1, compte.getNumeroCompte());
                pstmt.setString(2, compte.getNomTitulaire());
                pstmt.setDouble(3, compte.getSolde());
                pstmt.executeUpdate();
            }

            if (compte instanceof CompteCourant) {
                String insertCompteCourant = "INSERT INTO CompteCourant (numeroCompte, decouvertAutorise) VALUES (?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(insertCompteCourant)) {
                    pstmt.setString(1, compte.getNumeroCompte());
                    pstmt.setDouble(2, ((CompteCourant) compte).getDecouvertAutorise());
                    pstmt.executeUpdate();
                }
            } else if (compte instanceof CompteEpargne) {
                String insertCompteEpargne = "INSERT INTO CompteEpargne (numeroCompte, tauxInteret) VALUES (?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(insertCompteEpargne)) {
                    pstmt.setString(1, compte.getNumeroCompte());
                    pstmt.setDouble(2, ((CompteEpargne) compte).getTauxInteret());
                    pstmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void chargerComptesBD() {
        try (Connection conn = dbManager.getConnection()) {
            String selectComptes = "SELECT * FROM CompteBancaire";
            try (PreparedStatement pstmt = conn.prepareStatement(selectComptes);
                 ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String numero = rs.getString("numeroCompte");
                    String nom = rs.getString("nomTitulaire");
                    double solde = rs.getDouble("solde");

                    CompteBancaire compte = null;
                    String selectCourant = "SELECT * FROM CompteCourant WHERE numeroCompte = ?";
                    try (PreparedStatement pstmtCourant = conn.prepareStatement(selectCourant)) {
                        pstmtCourant.setString(1, numero);
                        try (ResultSet rsCourant = pstmtCourant.executeQuery()) {
                            if (rsCourant.next()) {
                                double decouvert = rsCourant.getDouble("decouvertAutorise");
                                compte = new CompteCourant(numero, nom, solde, decouvert);
                            }
                        }
                    }

                    if (compte == null) {
                        String selectEpargne = "SELECT * FROM CompteEpargne WHERE numeroCompte = ?";
                        try (PreparedStatement pstmtEpargne = conn.prepareStatement(selectEpargne)) {
                            pstmtEpargne.setString(1, numero);
                            try (ResultSet rsEpargne = pstmtEpargne.executeQuery()) {
                                if (rsEpargne.next()) {
                                    double taux = rsEpargne.getDouble("tauxInteret");
                                    compte = new CompteEpargne(numero, nom, solde, taux);
                                }
                            }
                        }
                    }

                    if (compte != null) {
                        comptes.put(numero, compte);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}