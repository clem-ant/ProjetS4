//
// Created by TONY DE FREITAS on 24/02/2021.
//

#include <stdlib.h>
#include "blockchain.h"

Blockchain *initBlockchain(int difficulty, int length){
    Blockchain *blockchain = malloc(sizeof(Blockchain));
    blockchain->difficulty = difficulty;
    blockchain->currentBlock = 0;
    blockchain->length = length;
    blockchain->blocks = malloc(length * sizeof(Block));
    return blockchain;
}

char* getHashCodePredecessor(Blockchain blockchain, int indexCurrentBlock){
    return blockchain.blocks[indexCurrentBlock-1].hashCode;
}