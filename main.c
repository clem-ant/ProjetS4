#include <stdio.h>
#include <stdlib.h>
#include "block.h"
#include "blockchain.h"
#include "merkleTree.h"
#include "./Sha256/sha256.h"
#include "./Sha256/sha256_utils.h"
#include "hash256.h"
#include "./Minage/minage.h"

/*
int minage(Block * block){
    int i = numberCharBlock(*block);
    int bufferSize = SHA256_BLOCK_SIZE;
    char *item = malloc((i+1) * sizeof(char));
    char hashRes[bufferSize*2 + 1]; // contiendra le hash en hexadécimal
    blockItemsToString(*block, item);
    printf("%d / ", block->nonce);
    hash256(item, hashRes);
    strcpy(block->hashCode, hashRes);
    free(item);
    for(int j = 0; j < 4; j++){
        if(hashRes[j] != '0'){
            block->nonce += 1;
            return 1;
        }
    }
    return 0;
}
*/

int main(int argc, char *argv[]) {
    Blockchain blockchain;
    initBlockchain(&blockchain, 4, 10);
    Block block;

    char hashCodePredecessor[64] = {"hdfsdsdfhjhjkhjkfghjkjkhfdsghjkfghjkgfhjkhjkdg"};
    initBlock(&block, hashCodePredecessor);
    block.txList.tx[0] = "g give 10";
    block.txList.tx[1] = "g give 15";
    block.txList.tx[2] = "g give 11";
    block.txList.tx[3] = "g give 12";
    block.txList.tx[4] = "g give 13";
    block.txNumber = 5;

    getMerkleRoot(&block);
    printf("Merkle root : %s", block.hashMerkleTreeRoot);
    mining(&block);
    return 0;
}