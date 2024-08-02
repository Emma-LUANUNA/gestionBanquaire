public class CompteCourant extends CompteBancaire {
    private double decouvertAutorise;

    public CompteCourant(String numeroCompte, String nomTitulaire, double solde, double decouvertAutorise) {
        super(numeroCompte, nomTitulaire, solde);
        this.decouvertAutorise = decouvertAutorise;
    }

    public double getDecouvertAutorise() {
        return decouvertAutorise;
    }

    public void setDecouvertAutorise(double decouvertAutorise) {
        this.decouvertAutorise = decouvertAutorise;
    }

    @Override
    public String toString() {
        return "CompteCourant{" +
                "numeroCompte='" + numeroCompte + '\'' +
                ", nomTitulaire='" + nomTitulaire + '\'' +
                ", solde=" + solde +
                ", decouvertAutorise=" + decouvertAutorise +
                '}';
    }
}