import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.DoubleProperty;

public class CompteBancaire {
    protected StringProperty numeroCompte;
    protected StringProperty nomTitulaire;
    protected DoubleProperty solde;

    public CompteBancaire(String numeroCompte, String nomTitulaire, double solde) {
        this.numeroCompte = new SimpleStringProperty(numeroCompte);
        this.nomTitulaire = new SimpleStringProperty(nomTitulaire);
        this.solde = new SimpleDoubleProperty(solde);
    }

    public String getNumeroCompte() {
        return numeroCompte.get();
    }

    public void setNumeroCompte(String numeroCompte) {
        this.numeroCompte.set(numeroCompte);
    }

    public StringProperty numeroCompteProperty() {
        return numeroCompte;
    }

    public String getNomTitulaire() {
        return nomTitulaire.get();
    }

    public void setNomTitulaire(String nomTitulaire) {
        this.nomTitulaire.set(nomTitulaire);
    }

    public StringProperty nomTitulaireProperty() {
        return nomTitulaire;
    }

    public double getSolde() {
        return solde.get();
    }

    public void setSolde(double solde) {
        this.solde.set(solde);
    }

    public DoubleProperty soldeProperty() {
        return solde;
    }
}