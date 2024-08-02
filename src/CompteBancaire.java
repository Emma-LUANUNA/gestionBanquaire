public class CompteBancaire {
    protected String numeroCompte;
    protected String nomTitulaire;
    protected double solde;

    public CompteBancaire(String numeroCompte, String nomTitulaire, double solde) {
        this.numeroCompte = numeroCompte;
        this.nomTitulaire = nomTitulaire;
        this.solde = solde;
    }

    public String getNumeroCompte() {
        return numeroCompte;
    }

    public String getNomTitulaire() {
        return nomTitulaire;
    }

    public double getSolde() {
        return solde;
    }

    public void setNomTitulaire(String nomTitulaire) {
        this.nomTitulaire = nomTitulaire;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    @Override
    public String toString() {
        return "CompteBancaire{" +
                "numeroCompte='" + numeroCompte + '\'' +
                ", nomTitulaire='" + nomTitulaire + '\'' +
                ", solde=" + solde +
                '}';
    }
}