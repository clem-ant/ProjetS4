#include <stdio.h>
#include <stdlib.h>
#include "block.h"
#include "blockchain.h"
#include "./Sha256/sha256.h"
#include "./Sha256/sha256_utils.h"
#include "hash256.h"

int minage(Block * block){
    int i = numberCharBlock(*block);
    int bufferSize = SHA256_BLOCK_SIZE;
    char *item = malloc((i+1) * sizeof(char));
    char hashRes[bufferSize*2 + 1]; // contiendra le hash en hexadécimal
    blockItemsToString(*block, item);
    printf("%d / ", block->nonce);
    hash256(item, hashRes);
    free(item);
    for(int j = 0; j < 5; j++){
        if(hashRes[j] != '0'){
            block->nonce += 1;
            return 1;
        }
    }
    return 0;
}

int main() {
    Blockchain blockchain;
    initBlockchain(&blockchain, 4, 10);
    Block block;
    char hashCodePredecessor[64] = {"hdfsdsdfhjhjkhjkfghjkjkhfdsghjkfghjkgfhjkhjkdg"};
    initBlock(&block, hashCodePredecessor);
    int i;
    do {
        i = minage(&block);
    }while(i);
    return 0;
}
