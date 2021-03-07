//
// Created by TONY DE FREITAS on 24/02/2021.
//

#include <stdlib.h>
#include "blockchain.h"

void initBlockchain(Blockchain* blockchain, int difficulty, int length){
    blockchain->difficulty = difficulty;
    blockchain->currentBlock = 0;
    blockchain->blockchain = malloc(length * sizeof(Block));
}

char* getHashCodePredecessor(Blockchain blockchain, int indexCurrentBlock){
    return blockchain.blockchain[indexCurrentBlock-1].hashCode;
}