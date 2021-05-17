package BlockChain;

import Utilisateurs.User;

public class Exchanges {
    private User giver;
    private User receiver;
    private long amount;

    public Exchanges(User giver, User receiver, long amount) {
        this.giver=giver;
        this.receiver=receiver;
        this.amount=amount;
    }

    public Exchanges(User giver) {
        this.giver = giver;
    }

    public User getReceiver() {
        return receiver;
    }

    public User getGiver() {
        return giver;
    }

    public long getAmount() {
        return amount;
    }

}
