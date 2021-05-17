package BlockChain;
import java.util.ArrayList;
import java.util.Date;



public class Transaction {
    private Date timestamp;
    private int nbInputs;
    private int nbOutputs;
    private ArrayList<TxInputs> inputList = new ArrayList<>();
    private ArrayList<TxOutputs> outputList = new ArrayList<>();
    private String comment;


    public Transaction(String comment) {
        this.timestamp = new Date();
        this.nbInputs = 0;
        this.nbOutputs = 0;
        this.comment = comment;
    }
    public String getComment(){
        return this.comment;
    }

    public int getNbOutputs() {
        return nbOutputs;
    }

    /**
     * Rajoute une Input dans la liste d'input dans la transaction
     *
     * @param input
     */
    public void addInput(TxInputs input){
        inputList.add(input);
        this.nbInputs++;
    }

    /**
     * Rajoute une Output dans la liste d'output dans la transaction
     *
     * @param output
     */
    public void addOutput(TxOutputs output){
        outputList.add(output);
        this.nbOutputs++;
    }

    /**
     * Calcul le montant total dans les Inputs
     *
     * @return inputTotal
     */
    public long getInputTotal() {
        long inputTotal = 0;
        for (TxInputs inputs : inputList) {
            inputTotal+= inputs.getAmount();
        }
        return inputTotal;
    }

    /**
     * genere les outputs, (la tx en elle meme + le change) pour les mettre dans
     * la listeOutput, l'UTXO de change sera stocke dans la liste global d'UTXO de la blockchain
     *
     * @param exchanges
     * @param blockChain
     */
    public void generateOutputs(Exchanges exchanges,BlockChain blockChain){
        TxOutputs tx =new TxOutputs(exchanges.getAmount());
        TxOutputs change=new TxOutputs((this.getInputTotal())-(tx.getAmount()));
        this.addOutput(tx);
        this.addOutput(change);
        UTXO utxo=new UTXO(exchanges.getGiver(),change.getAmount());
        blockChain.addUTXO(utxo); //rajoute utxo change dans liste global
        }
    }


