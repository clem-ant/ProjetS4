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

    blockchainIntegrity(blockchain);
    deleteBlockchain(blockchain);
    return 0;
}