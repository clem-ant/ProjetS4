package Utilisateurs;

import HashUtil.HashUtil;

/**
 * @author Clément PAYET
 * The type User.
 */
public class User {
    private final String nom;
    private final String hashUser;
    private int Bnb;

    /**
     * Instantiates a new User.
     *
     * @param nom        the nom
     * @param phraseHash the phrase hash
     * @param Bnb        the bnb
     */
    public User(String nom, String phraseHash, int Bnb){
        this.nom = nom; //Nom de l'user
        this.hashUser = HashUtil.applySha256(phraseHash+nom+Bnb);
        this.Bnb = Bnb; //Nombre de bonobos sur son compte à la création.
    }

    /**
     * Gets nom.
     *
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Gets hash user public.
     *
     * @return the hash user public
     */
    public String getHashUserPublic() {
        return hashUser;
    } //TODO creer hash secret

    /**
     * A assez d argent boolean.
     *
     * @param montant the montant
     * @return the boolean
     */
    public boolean aAssezDArgent(int montant){
        return Bnb >= montant;
    }

    /**
     * Donner bnb.
     *
     * @param destinataire the destinataire
     * @param montantDeBnb the montant de bnb
     */
    public void donnerBnb(User destinataire, int montantDeBnb){
        if(Bnb >= montantDeBnb) {
            destinataire.Bnb += montantDeBnb;
            Bnb -= montantDeBnb;
        }else{
            System.out.println("Vous n'avez pas assez d'argent" + this.getNom());
            Bnb += montantDeBnb; //On ajoute des Bnb car il faut faire des echanges quand meme
            donnerBnb(destinataire, montantDeBnb);
        }
    }

    /**
     * Recevoir bnb.
     *
     * @param montantBnb the montant bnb
     */
    public void recevoirBnb(int montantBnb){
        this.Bnb += montantBnb;
    }



}
