package BlockChain;
import java.util.Date;
import HashUtil.HashUtil;

public class Blocks {
    private int index = 1; //Block Genesis = 0
    private Date timeStamp = new Date(); //La date au moment de la création
    private String hashPrecedent; //Hash du block précédent de la chaine
    private String hashRootMerkle;
    private BlockChain blockChain;
    private String hashBlockCourant;
    private String listeTransaction = "";
    private int nbTranstaction;
    private int nonce = 0; //En cryptographie, un nonce est un nombre arbitraire destiné à être utilisé une seule fois. Il s'agit souvent d'un nombre aléatoire ou pseudo-aléatoire émis dans un protocole d'authentification pour garantir que les anciennes communications ne peuvent pas être réutilisées dans des attaques par rejeu

    public Blocks(BlockChain blockChain){
        this.index = index;
        this.blockChain = blockChain;
    }

    public String getHashPrecedent() {
        hashPrecedent = blockChain.getBlocks(index-1).hashBlockCourant;
        return hashPrecedent;
    }

    public String getHashBlockCourant() {
        return hashBlockCourant;
    }

    public String getListeTransaction() {
        return listeTransaction;
    }

    public String transaction(String message){
        listeTransaction += message;
        return hashing(message, blockChain.getDifficulte());
    }

    public String hashing(String message, int difficulte){ //TODO ajouter arbre de Merkle
        
        do {
            hashBlockCourant = HashUtil.applySha256(String.valueOf(nonce) + message + timeStamp + getHashPrecedent());
            nonce++;
        }while(!hashBlockCourant.matches("[0]{"+blockChain.getDifficulte()+"}(.*)"));
        return hashBlockCourant;
    }
    
    public List<String> merkleTree(){
        ArrayList<String> tree=new ArrayList<>;
        //On commence d'abord à ajouter les transactions en tant que des "leaves" de l'arbre de Merkle
        tree.add(tree.hash(listeTransaction));
        int levelOffset=0;
        
        //Parcourir chaque niveau, en nous arrêtant que lorsque nous atteignons la racine
        //(levelSize==1)
        
        for (int levelSize=listeTransaction.size(); levelSize>1; levelSize=(levelSize+1)/2) {
            //Pour chaque pair de nodes à ce niveau
            for (int left=0; left<levelSize; left+=2){
                //Le node de droite peut être le même que celui de la gauche dans le cas où nous n'avons pas assez de transactions
                int right = Math.min(left + 1, levelSize - 1);
				String tleft = tree.get(levelOffset + left);
				String tright = tree.get(levelOffset + right);
                tree.add(getHashBlockCourant(tleft + tright));
            }
            //Passez au niveau suivant
            levelOffset += levelSize;
        }
        return tree;
        
    }


}
