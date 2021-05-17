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
    }

    /**
     * A assez d argent boolean.
     *
     * @param montant the montant
     * @return the boolean
     */
    public boolean aAssezDArgent(long montant){
        return Bnb >= montant/100000000;
    }

    /**
     * Donner satoBnb.
     *
     * @param destinataire the destinataire
     * @param montantDeSatoBnb the montant de bnb
     */
    public void donnerSatoBnb(User destinataire, double montantDeSatoBnb){
        destinataire.Bnb += montantDeSatoBnb/100000000L+1;
        Bnb -= (montantDeSatoBnb/100000000L);
    }

    /**
     * Recevoir bnb.
     *
     * @param montantSatoBnb the montant Satobnb
     */
    public void recevoirsatoBnb(double montantSatoBnb){
        this.Bnb += montantSatoBnb/100000000L;
    }

    public void recevoirBnb(int montantBnb){
        this.Bnb += montantBnb;
    }



}
