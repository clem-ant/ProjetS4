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
    block->txList->tx[0] = "g give 10";
    block->txList->tx[1] = "g give 15";
    block->txList->tx[2] = "g give 11";
    block->txList->tx[3] = "g give 12";
    block->txList->tx[4] = "g give 13";
    block->txList->txNumber = 5;
    getMerkleRoot(block);
    printf("Merkle root : %s\n", block->hashMerkleTreeRoot);
    mining(block);
    printf("Hash du block : %s\n", block->hashCode);
    deleteBlockchain(blockchain);


    return 0;
}