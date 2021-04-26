#include <stdio.h>
#include <stdlib.h>
#include "block.h"
#include "blockchain.h"
#include "merkleTree.h"
#include "./Sha256/sha256.h"
#include "./Sha256/sha256_utils.h"
#include "Minage/mining_utils.h"
#include "./Minage/mining.h"
#include "./Verification/verification.h"
#include "cheaterTransaction.h"
#include "cheaterBlocks.h"

#define RED   "\x1B[31m"
#define GRN   "\x1B[32m"
#define YEL   "\x1B[33m"
#define RESET "\x1B[0m"

void printBlock(Block *block, int blockIndex){
    printf(YEL "[Block n°%d]\n", blockIndex);
    printf(RESET "| Date de creation : %s", block->timestamp);
    printf("| Hashcode predecessor : %s\n", block->hashCodePredecessor);
    printf("| Nonce : %d\n", block->nonce);
    printf("| Liste de transaction :\n");
    for (int i = 0; i < block->txList->txNumber; i++){
        printf("   | %d - %s\n", i, block->txList->tx[i]);
    }
    printf("| Hash merkle root : %s\n", block->hashMerkleTreeRoot);
    printf("| Hashcode du block : %s\n\n", block->hashCode);
}
void printBlockchain(Blockchain * blockchain){
    printf(YEL "[Contenu de la blockchain]\n");

    for(int i = 0; i < blockchain->blockCursor; i++){
        printBlock(blockchain->blocks[i], i);
    }
}


int main(int argc, char *argv[]) {
    Blockchain *blockchain = initBlockchain(4, 10);
    Block *block = createGenesis(blockchain);
    merkleTree(block);

    Block *block2 = addBlock(blockchain);
    addTx(block2, "g give 50");
    addTx(block2, "g give 13");
    addTx(block2, "g give 18");
    addTx(block2, "g give 20");

    merkleTree(block2);
    mining(block2);

    Block *block3 = addBlock(blockchain);
    addTx(block3, "g give 50");
    addTx(block3, "g give 13");
    addTx(block3, "g give 16");
    addTx(block3, "g give 20");

    merkleTree(block3);
    mining(block3);

    Block *block4 = addBlock(blockchain);
    addTx(block4, "g give 50");
    addTx(block4, "g give 19");
    addTx(block4, "g give 18");
    addTx(block4, "g give 20");

    merkleTree(block4);
    mining(block4);

    //blockchainIntegrity(blockchain);

    printBlockchain(blockchain);
    //deleteTransaction(blockchain, 2, 1);
    cheaterBlock(blockchain, 3);
    printBlockchain(blockchain);
    blockchainIntegrity(blockchain);
    deleteBlockchain(blockchain);
    return 0;
}