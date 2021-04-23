#include <stdio.h>
#include <stdlib.h>
#include "block.h"
#include "blockchain.h"
#include "merkleTree.h"
#include "./Sha256/sha256.h"
#include "./Sha256/sha256_utils.h"
#include "Minage/mining_utils.h"
#include "./Minage/mining.h"

int main(int argc, char *argv[]) {
    Blockchain *blockchain = initBlockchain(4, 10);
    Block *block = addBlock(blockchain);
    addTx(block, "g give 10");
    addTx(block, "g give 15");
    addTx(block, "g give 12");
    addTx(block, "g give 50");

    getMerkleRoot(block);
    printf("Merkle root : %s\n", block->hashMerkleTreeRoot);
    mining(block);
    printf("Hash du block : %s\n", block->hashCode);
    deleteBlockchain(blockchain);


    return 0;
}