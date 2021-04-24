//
// Created by TONY DE FREITAS on 24/04/2021.
//

#include <stdbool.h>
#include <stdlib.h>
#include "../blockchain.h"
#include "verification.h"

bool isGenesis(Block *block){
    if(strcmp(block->hashCode, "0") == 0
    && block->nonce == 0
    && block->txList->txNumber == 1
    && strcmp(block->txList->tx[0], "Genesis") == 0){
        return true;
    }
    return false;
}

bool isMerkleRoot(Block *block){
    return false;
}

bool isHashCode(Block *block){
    return false;
}

bool isWellChained(Blockchain *blockchain){
    return false;
}
