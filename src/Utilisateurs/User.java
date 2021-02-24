package Utilisateurs;

public class User {
    private String nom;
    private String hashUser;
    private int Bnb;

    public User(String nom, String hashUser, int Bnb){
        this.nom = nom;
        this.hashUser = hashUser;
        this.Bnb = Bnb;
    }

    public String getHashUser() {
        return hashUser;
    }

    public void donnerArgent(User destinataire, int montantDeBnb){
        if(Bnb >= montantDeBnb) {
            destinataire.Bnb += montantDeBnb;
            Bnb -= montantDeBnb;
        }

    }

}