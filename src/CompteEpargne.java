public class CompteEpargne extends CompteBancaire {
    private double tauxInteret;

    public CompteEpargne(String numeroCompte, String nomTitulaire, double solde, double tauxInteret) {
        super(numeroCompte, nomTitulaire, solde);
        this.tauxInteret = tauxInteret;
    }

    public double getTauxInteret() {
        return tauxInteret;
    }

    public void setTauxInteret(double tauxInteret) {
        this.tauxInteret = tauxInteret;
    }

    @Override
    public String toString() {
        return "CompteEpargne{" +
                "numeroCompte='" + numeroCompte + '\'' +
                ", nomTitulaire='" + nomTitulaire + '\'' +
                ", solde=" + solde +
                ", tauxInteret=" + tauxInteret +
                '}';
    }
}