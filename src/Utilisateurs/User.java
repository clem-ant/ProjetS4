package Utilisateurs;

import HashUtil.HashUtil;

public class User {
    private String nom;
    private String hashUser;
    private int Bnb;

    public User(String nom, String phraseHash, int Bnb){
        this.nom = nom; //Nom de l'user
        this.hashUser = HashUtil.applySha256(phraseHash+nom+Bnb);
        this.Bnb = Bnb; //Nombre de bonobos sur son compte à la création.
    }

    public String getHashUserPublic() {
        return hashUser;
    } //TODO creer hash secret

    public void donnerArgent(User destinataire, int montantDeBnb){
        if(Bnb >= montantDeBnb) {
            destinataire.Bnb += montantDeBnb;
            Bnb -= montantDeBnb;
        }

    }

}
