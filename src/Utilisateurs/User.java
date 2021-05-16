package Utilisateurs;

import HashUtil.HashUtil;

/**
 * The type User.
 *
 * @author Clément PAYET The type User.
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
    public User(String nom, String phraseHash, int Bnb, int salt){
        this.nom = nom; //Nom de l'user
        this.hashUser = HashUtil.applySha256(phraseHash+nom+Bnb+salt);
        this.Bnb = Bnb; //Nombre de bonobos sur son compte à la création.
    }

    /**
     * Gets bnb.
     *
     * @return the bnb
     */
    public int getBnb() {
        return Bnb;
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
