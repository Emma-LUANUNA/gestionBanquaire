# Gestion Banque

## Description
Gestion Banque est une application Java permettant de gérer différents types de comptes bancaires, y compris les comptes courants et les comptes d'épargne. L'application permet d'ajouter des comptes, d'effectuer des dépôts et des retraits, et de visualiser les soldes des comptes.

## Fonctionnalités
- **Ajouter un compte** : Créez des comptes courants et des comptes d'épargne avec des informations spécifiques telles que le numéro de compte, le nom du titulaire, le solde initial et le taux d'intérêt (pour les comptes d'épargne).
- **Consulter les comptes** : Affichez la liste des comptes existants avec leurs détails.
- **Effectuer des transactions** : Dépôts et retraits sur des comptes spécifiques.
- **Charger/Sauvegarder les données** : Chargez les données des comptes à partir d'un fichier JSON et sauvegardez les modifications.

## Instructions pour compiler et exécuter le projet

### Prérequis
- [Java Development Kit (JDK) 21](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)
- [Apache Maven](https://maven.apache.org/install.html) (si vous utilisez Maven pour la gestion des dépendances)

### Compilation et exécution

1. **Cloner le dépôt GitHub :**

    ```bash
    git clone https://github.com/Emma-LUANUNA/gestionBanquaire.git
    cd gestionBanquaire
    ```

2. **Compiler le projet :**

    Si vous utilisez Maven :
    ```bash
    mvn clean install
    ```

    Si vous utilisez un fichier `build.xml` pour Ant :
    ```bash
    ant compile
    ```

3. **Exécuter l'application :**

    Si vous utilisez Maven :
    ```bash
    mvn exec:java -Dexec.mainClass="Main"
    ```

    Si vous utilisez Ant :
    ```bash
    ant run
    ```

    Ou directement avec la commande Java :
    ```bash
    java -cp target/classes Main
    ```

### Exemple de fichier JSON pour les comptes

Créez un fichier `comptes.json` avec le contenu suivant pour tester le chargement des comptes :

```json
[
    {
        "type": "CompteCourant",
        "numeroCompte": "123",
        "nomTitulaire": "Alice",
        "solde": 1000.0
    },
    {
        "type": "CompteEpargne",
        "numeroCompte": "456",
        "nomTitulaire": "Bob",
        "solde": 2000.0,
        "tauxInteret": 0.02
    }
]
