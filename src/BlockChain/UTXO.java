package BlockChain;

import Utilisateurs.User;

public class UTXO {
    private User Owner; // propriétaire de l'UTXO
    private long amount; // montant de l'UTXO

    public UTXO(User owner, long amount) {
        this.Owner = owner;
        this.amount = amount;
    }


    public long getAmount() {
        return amount;
    }

    /**
     * Vérifie que l'UTXO dans la liste globale appartienne au donneur de l'échange
     * d'argent qu'on a generé
     *
     * @param user
     */
    public boolean belongsToOwner(User user) {
        if (this.Owner == user) {
            return true;
        } else {
            return false;
        }
    }


}
